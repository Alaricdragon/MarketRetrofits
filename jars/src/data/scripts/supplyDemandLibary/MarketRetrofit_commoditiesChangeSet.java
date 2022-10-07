package data.scripts.supplyDemandLibary;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_commoditiesChangeBase;

import java.util.ArrayList;

public class MarketRetrofit_commoditiesChangeSet {
    public boolean active = true;
    public String ID;
    private ArrayList<MarketRetrofit_commoditiesChangeBase> items = new ArrayList<>();
    public MarketRetrofit_commoditiesChangeSet(String name){
        ID = name;
    }
    public boolean active(MarketAPI market){
        return active;
    }
    public void addChange(MarketRetrofit_commoditiesChangeBase change){
        items.add(change);
    }
    public MarketRetrofit_commoditiesChangeBase getChange(String name){
        for(MarketRetrofit_commoditiesChangeBase a : items){
            if(a.ID.equals(name)){
                return a;
            }
        }
        return null;
    }
    public boolean removeChange(String name){
        for(MarketRetrofit_commoditiesChangeBase a : items){
            if(a.ID.equals(name)){
                items.remove(a);
                return true;
            }
        }
        return false;
    }
}
