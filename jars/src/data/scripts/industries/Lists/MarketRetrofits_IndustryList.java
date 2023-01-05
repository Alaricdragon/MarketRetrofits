package data.scripts.industries.Lists;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import data.scripts.industries.MarketRetrofit_BaseIndustry;
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
    public MarketRetrofits_DefaltInstanceIndustry getActiveInstance(MarketAPI market, String IDT, MarketRetrofit_BaseIndustry industry){
        for(MarketRetrofits_DefaltInstanceIndustry a: sets){
            if(a.canImply(market,industry)){
                //a.init(IDT,market);
                //a.IndustryDataGet(industry);
                //a.getBaseDataFromIndustry(industry);
                //a.getExtraDataFromIndustry(industry.getExstraData());
                return a;
            }
        }
        //defaultInstance.init(IDT,market);
        //defaultInstance.IndustryDataGet(industry);
        /*
        defaultInstance.getBaseDataFromIndustry(industry);
        defaultInstance.getExtraDataFromIndustry(industry.getExstraData());*/
        return defaultInstance;
    }
}
