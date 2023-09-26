package data.scripts.marketConditionReplacer;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import data.scripts.marketconditions.MarketRetrofit_hiddenCondition;

import java.util.ArrayList;

public class marketConditonReplacer_MasterList {
    public static ArrayList<marketConditionReplacer_ConditionSet> sets = new ArrayList<>();
    public static marketConditionReplacer_ConditionSet getOrCreateSet(String marketConditionID){
        for(int a = 0; a < sets.size(); a++){
            if(sets.get(a).ID.equals(marketConditionID)){
                return sets.get(a);
            }
        }
        marketConditionReplacer_ConditionSet a;
        a = new marketConditionReplacer_ConditionSet(marketConditionID);
        sets.add(a);
        return a;
    }
    public static boolean doesSetExsist(String marketConditionID){
        for(int a = 0; a < sets.size(); a++){
            if(sets.get(a).ID.equals(marketConditionID)){
                return true;
            }
        }
        return false;
    }
    public static void applyToMarket(MarketAPI market){
        for (int a = 0; a < marketConditonReplacer_MasterList.sets.size(); a++){
            marketConditionReplacer_ConditionSet b = marketConditonReplacer_MasterList.sets.get(a);
            if (b.isThisSetActive(market)){
                b.checkCondition(market);
                b.activateMarketCondition(market);
            }else{
                b.disactavateMarketCondition(market);
            }
        }
    }
    /*this class is requires to organize and manage every set available to marketConditionReplacer.*/
}
