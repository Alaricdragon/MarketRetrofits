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
            if(a.InstanceID.equals(name)){
                return a;
            }
        }
        return null;
    }
    public boolean removeInstance(String name){
        for(MarketRetrofits_DefaltInstanceIndustry a: sets){
            if(a.InstanceID.equals(name)){
                sets.remove(a);
                return true;
            }
        }
        return false;
    }
    public MarketRetrofits_DefaltInstanceIndustry getActiveInstance(MarketAPI market, String IDT, MarketRetrofit_BaseIndustry industry){
        return getActiveInstance(defaultInstance,sets,market,IDT,industry);
    }
    public ArrayList<MarketRetrofits_DefaltInstanceIndustry> copySets(){
        ArrayList<MarketRetrofits_DefaltInstanceIndustry> output = new ArrayList<MarketRetrofits_DefaltInstanceIndustry>();
        for(MarketRetrofits_DefaltInstanceIndustry a:sets){
            output.add(a.CloneInstance());
        }
        return output;
    }
    public MarketRetrofits_DefaltInstanceIndustry copyDefaltIndustry(){
        return defaultInstance.CloneInstance();
    }
    public static MarketRetrofits_DefaltInstanceIndustry getActiveInstance(MarketRetrofits_DefaltInstanceIndustry defalt, ArrayList<MarketRetrofits_DefaltInstanceIndustry> instance,MarketAPI market, String IDT, MarketRetrofit_BaseIndustry industry){
        for(MarketRetrofits_DefaltInstanceIndustry a: instance){
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
        return defalt;
    }
}
