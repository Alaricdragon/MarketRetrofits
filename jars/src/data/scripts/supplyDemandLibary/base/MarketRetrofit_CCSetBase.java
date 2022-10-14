package data.scripts.supplyDemandLibary.base;

import com.fs.starfarer.api.campaign.econ.Industry;

import java.util.ArrayList;

public class MarketRetrofit_CCSetBase {
    public boolean active = true;
    public String ID;
    private ArrayList<MarketRetrofit_CCBase> items = new ArrayList<>();
    public MarketRetrofit_CCSetBase(String name){
        ID = name;
    }
    public boolean active(Industry industry){
        return active;
    }
    public void addChange(MarketRetrofit_CCBase change){
        items.add(change);
    }
    public MarketRetrofit_CCBase getChange(String name){
        for(MarketRetrofit_CCBase a : items){
            if(a.ID.equals(name)){
                return a;
            }
        }
        return null;
    }
    public boolean removeChange(String name){
        for(MarketRetrofit_CCBase a : items){
            if(a.ID.equals(name)){
                items.remove(a);
                return true;
            }
        }
        return false;
    }
    public ArrayList<MarketRetrofit_CCBase> getAllChanges(){
        return items;
    }
}
