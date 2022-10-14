package data.scripts.supplyDemandLibary.lists;

import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSetBase;

import java.util.ArrayList;

public class MarketRetrofit_CCMasterList {
    private static ArrayList<MarketRetrofit_CCIndustrySet> IndustrySets = new ArrayList<>();
    private static MarketRetrofit_CCIndustrySet GeneralSets = new MarketRetrofit_CCIndustrySet("");
    private static ArrayList<MarketRetrofit_CCSetBase> sets = new ArrayList<>();
    public MarketRetrofit_CCSetBase getSet(String ID){
        for(MarketRetrofit_CCSetBase set:sets){
            if (set.ID.equals(ID)){
                return set;
            }
        }
        return null;
    }

    private static void addSetToSets(MarketRetrofit_CCSetBase set){
        sets.add(set);
    }
    public static void addSetToGenral(MarketRetrofit_CCSetBase set){
        GeneralSets.addSet(set);
    }
    public static void addSetToIndustrySet(String industryName, MarketRetrofit_CCSetBase set){
        boolean done = false;
        for(MarketRetrofit_CCIndustrySet a : IndustrySets){
            if(a.industry.equals(industryName)){
                done = true;
                a.addSet(set);
                break;
            }
        }
        if(!done){
            MarketRetrofit_CCIndustrySet a = new MarketRetrofit_CCIndustrySet(industryName);
            IndustrySets.add(a);
        }
        addSetToSets(set);
    }
    public static MarketRetrofit_CCIndustrySet getIndustrySet(String industry){
        for (MarketRetrofit_CCIndustrySet a:IndustrySets){
            if (a.industry.equals(industry)){
                return a;
            }
        }
        return null;
    }
    public static MarketRetrofit_CCIndustrySet getGeneralSets(){
        return GeneralSets;
    }
}
