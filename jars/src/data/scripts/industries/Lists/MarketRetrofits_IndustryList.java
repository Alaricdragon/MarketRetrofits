package data.scripts.industries.Lists;

import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;
import data.scripts.industries.MarketRetrofits_InstanceIndustry;

import java.util.ArrayList;

public class MarketRetrofits_IndustryList {
    public String Industry = "";
    public ArrayList<MarketRetrofits_DefaltInstanceIndustry> sets = new ArrayList<>();
    public MarketRetrofits_DefaltInstanceIndustry defaultInstance;
    public MarketRetrofits_IndustryList(String IndustryT){
        Industry = IndustryT;
    }

    public void addInstanceIndustry(MarketRetrofits_InstanceIndustry Instance){
        /*
            need to get the Instance priority, and add it to the master list.
         */
    }
    public MarketRetrofits_DefaltInstanceIndustry getInstance(String name){
        for(MarketRetrofits_DefaltInstanceIndustry a: sets){
            if(a.ID.equals(name)){
                return a;
            }
        }
        return null;
    }
    public boolean removeInstance(String name){
        for(MarketRetrofits_DefaltInstanceIndustry a: sets){
            if(a.ID.equals(name)){
                sets.remove(a);
                return true;
            }
        }
        return false;
    }
    public MarketRetrofits_DefaltInstanceIndustry getActiveInstance(MarketAPI market, String IDT, Industry industry){
        for(MarketRetrofits_DefaltInstanceIndustry a: sets){
            if(a.canImply(market)){
                a.init(IDT,market);
                a.applyIndustry(industry);
                return a;
            }
        }
        defaultInstance.init(IDT,market);
        defaultInstance.applyIndustry(industry);
        return defaultInstance;
    }
}
