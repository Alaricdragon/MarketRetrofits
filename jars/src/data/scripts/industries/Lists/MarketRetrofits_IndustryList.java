package data.scripts.industries.Lists;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import data.scripts.industries.MarketRetrofits_InstanceIndustry;

import java.util.ArrayList;

public class MarketRetrofits_IndustryList {
    public String Industry = "";
    public ArrayList<MarketRetrofits_InstanceIndustry> sets = new ArrayList<>();
    public MarketRetrofits_InstanceIndustry defaultInstance;
    public MarketRetrofits_IndustryList(String IndustryT){
        Industry = IndustryT;
    }

    public void addInstanceIndustry(MarketRetrofits_InstanceIndustry Instance){
        /*
            need to get the Instance priority, and add it to the master list.
         */
    }
    public MarketRetrofits_InstanceIndustry getInstance(String name){
        for(MarketRetrofits_InstanceIndustry a: sets){
            if(a.ID.equals(name)){
                return a;
            }
        }
        return null;
    }
    public boolean removeInstance(String name){
        for(MarketRetrofits_InstanceIndustry a: sets){
            if(a.ID.equals(name)){
                sets.remove(a);
                return true;
            }
        }
        return false;
    }
    public MarketRetrofits_InstanceIndustry getActiveInstance(MarketAPI market){
        for(MarketRetrofits_InstanceIndustry a: sets){
            if(a.canImply(market)){
                return a;
            }
        }
        return defaultInstance;
    }
}
