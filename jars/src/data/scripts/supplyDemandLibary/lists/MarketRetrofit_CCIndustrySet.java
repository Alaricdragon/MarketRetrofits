package data.scripts.supplyDemandLibary.lists;

import com.fs.starfarer.api.campaign.econ.Industry;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCBase;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSetBase;

import java.util.ArrayList;

public class MarketRetrofit_CCIndustrySet {
    public String industry = "";
    public boolean connected = false;
    private ArrayList<MarketRetrofit_CCSetBase> sets = new ArrayList<>();
    private ArrayList<Integer> links = new ArrayList<>();
    private ArrayList<MarketRetrofit_CCBase> changes = new ArrayList<>();
    MarketRetrofit_CCIndustrySet(String industryID){
        industry = industryID;
    }
    public void addSet(MarketRetrofit_CCSetBase set){
        addChangesToList(set);
        sets.add(set);
    }
    public void removeSet(MarketRetrofit_CCSetBase set){
        removeChangesFromList(set);
        sets.remove(set);
    }
    public void apply(Industry industry){
        boolean[] apply = getActiveSets(industry);
        int a = 0;
        for(MarketRetrofit_CCBase change: changes){
            if(apply[links.get(a)]) {
                change.apply(industry, getID(change,sets.get(links.get(a))));
            }
            a++;
        }
    }
    public void unApply(Industry industry){
        int a = 0;
        for(MarketRetrofit_CCBase change: changes){
            change.unApply(industry,getID(change,sets.get(links.get(a))));
            a++;
        }
    }
    private void addChangesToList(MarketRetrofit_CCSetBase set){
        int pos = sets.size();
        ArrayList<MarketRetrofit_CCBase> newChanges = set.getAllChanges();
        //HERE not tested. needs to organize from lowest number to highest. might already do so, but i dont know.
        for(MarketRetrofit_CCBase change: newChanges){
            //System.out.println("processing crew: " + a + "with a priority of: " + Crews.get(a).crewPriority);
            float priority = change.order;
            int min = 0;
            int max = changes.size();
            boolean done = false;
            float look;
            //float temp;
            while(max != min){
                look = (min + max) / 2;
                //temp = changes.get((int)look).order;
                float priority1 = changes.get((int)look).order;
                if(priority < priority1){
                    int last = min;
                    min = (int)look;
                    if(last == min){
                        min++;
                    }
                }else if(priority > priority1){
                    max = (int)look;
                }else{
                    links.add((int)look,pos);
                    changes.add((int)look,change);
                    done = true;
                    break;
                }
            }
            if(!done){
                if(max == changes.size()){
                    float priority1 = changes.get(max - 1).order;
                    if(priority > priority1){
                        changes.add(max,change);
                        links.add(max,pos);
                        //crewPriority.add(temp);
                    }else{
                        changes.add(max - 1,change);
                        links.add(max - 1,pos);
                    }
                }else{
                    float priority1 = changes.get(max).order;
                    if(priority > priority1){
                        changes.add(max + 1,change);
                        links.add(max + 1,pos);
                    }else{
                        changes.add(max,change);
                        links.add(max,pos);
                    }
                }
            }
        }
    }
    private void removeChangesFromList(MarketRetrofit_CCSetBase set){
        //HERE not yet implemented
    }
    private boolean[] getActiveSets(Industry industry){
        boolean[] activeSets = new boolean[sets.size()];
        for(int a = 0; a < sets.size(); a++){
            activeSets[a] = sets.get(a).active(industry);
        }
        return activeSets;
    }
    private boolean[] wasActive(){
        //HERE. this might need to be applyed in some way. no idea how though.
        boolean[] activeSets = new boolean[sets.size()];
        for(int a = 0; a < sets.size(); a++){
            activeSets[a] = true;
        }
        return activeSets;
    }
    private String getID(MarketRetrofit_CCBase a, MarketRetrofit_CCSetBase b){
        return "" + a.ID + "_" + b.ID;
    }
}
