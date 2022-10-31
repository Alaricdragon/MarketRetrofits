package data.scripts.industries.Lists;

import data.scripts.industries.MarketRetrofits_InstanceIndustry;

import java.util.ArrayList;

public class MarketRetrofits_IndustryMasterList {
    private static ArrayList<MarketRetrofits_IndustryList> Industries = new ArrayList<>();
    public static void addInstance(String industry, MarketRetrofits_InstanceIndustry instance){
        for(MarketRetrofits_IndustryList a: Industries){
            if(a.Industry.equals(industry)){
                a.addInstanceIndustry(instance);
            }
        }
        MarketRetrofits_IndustryList b = new MarketRetrofits_IndustryList(industry);
        b.addInstanceIndustry(instance);
    }
    public static MarketRetrofits_IndustryList getInstance(String industry){
        for(MarketRetrofits_IndustryList a: Industries){
            if(a.equals(industry)){
                return a;
            }
        }
        return null;
    }
    public static void setDefaltInstance(String industry, MarketRetrofits_InstanceIndustry instance){
        for(MarketRetrofits_IndustryList a: Industries){
            if(a.Industry.equals(industry)){
                a.defaultInstance = instance;
            }
        }
        MarketRetrofits_IndustryList b = new MarketRetrofits_IndustryList(industry);
        b.defaultInstance = instance;
    }
}
