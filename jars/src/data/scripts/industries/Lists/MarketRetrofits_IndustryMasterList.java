package data.scripts.industries.Lists;

import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;
import data.scripts.industries.MarketRetrofits_InstanceIndustry;

import java.util.ArrayList;

public class MarketRetrofits_IndustryMasterList {
    private static ArrayList<MarketRetrofits_IndustryList> Industries = new ArrayList<>();
    public static void addInstance(String industry, MarketRetrofits_InstanceIndustry instance){
        //final Logger LOG = Global.getLogger(new MarketRetrofits_IndustryMasterList().getClass());
        for(MarketRetrofits_IndustryList a: Industries){
            if(a.Industry.equals(industry)){
                a.addInstanceIndustry(instance);
                //LOG.info("adding item to industry named: " + industry);
                return;
            }
        }
        //LOG.info("creating new industry named: " + industry);
        MarketRetrofits_IndustryList b = new MarketRetrofits_IndustryList(industry);
        Industries.add(b);
        b.addInstanceIndustry(instance);
    }
    public static MarketRetrofits_IndustryList getInstance(String industry){
        if(industry.equals("")) {
            int[] c = {};
            c[1] = 1;
        }
        //final Logger LOG = Global.getLogger(new MarketRetrofits_IndustryMasterList().getClass());

        for(MarketRetrofits_IndustryList a: Industries){
            if(a.Industry.equals(industry)){
                //LOG.info("successfully got industry named: " + industry);
                return a;
            }
        }
        //LOG.info("failed to get industry named: " + industry);
        int[] c = {};
        c[2] = 1;
        return null;
    }
    public static void setDefaltInstance(String industry, MarketRetrofits_DefaltInstanceIndustry instance){
        for(MarketRetrofits_IndustryList a: Industries){
            if(a.Industry.equals(industry)){
                a.defaultInstance = instance;
            }
        }
        MarketRetrofits_IndustryList b = new MarketRetrofits_IndustryList(industry);
        Industries.add(b);
        b.defaultInstance = instance;
    }
}
