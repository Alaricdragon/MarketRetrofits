package data.scripts.supplyDemandLibary.old;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

import java.util.ArrayList;

public class old_crewReplacer_SupplyDemandLists {
    public static ArrayList<old_crewReplacer_SupplyDemandSet> list= new ArrayList<>();
    public static void runAll(MarketAPI market){
        for(old_crewReplacer_SupplyDemandSet set:list){
            set.applyMarket(market,false);
        }
    }
    public static void addOrMergeRuleSet(old_crewReplacer_SupplyDemandSet set){
        for(old_crewReplacer_SupplyDemandSet set2:list) {
            if (set2.name.equals(set.name)){
                //mergesets?
                set.merge(set2);
                return;
            }
        }
        list.add(set);
    }
    public static void removeRuleSet(String name){
        for(old_crewReplacer_SupplyDemandSet set:list) {
            if (set.name.equals(name)){
                list.remove(set);
                return;
            }
        }
    }
    public static void setRuleSetOnOrOff(String name,boolean onOrOff){
        for(old_crewReplacer_SupplyDemandSet set:list) {
            if (set.name.equals(name)){
                set.onOff = onOrOff;
            }
        }
    }
    public static old_crewReplacer_SupplyDemandSet getRuleSet(String name){
        for(old_crewReplacer_SupplyDemandSet set:list){
            if(set.name.equals(name)){
                return set;
            }
        }
        return null;
    }
}
