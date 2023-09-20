package data.scripts.marketConditionReplacer;

import com.fs.starfarer.api.campaign.econ.MarketAPI;
import data.scripts.marketconditions.MarketRetrofits_marketConditionReplacer_DefaltCondition;

import java.util.ArrayList;

public class marketConditionReplacer_ConditionSet {
    /*this will hold all the possibly conditions for a single market condition.
    * requires functions to add and replace possabilitys...*/
    public ArrayList<marketConditionReplacer_Condition> possabilitys = new ArrayList<>();
    public marketConditionReplacer_Condition base = null;//base is no longer required. i can now get the base off of the ID of this set.
    public String ID;
    public boolean isConditionSet = false;
    public marketConditionReplacer_ConditionSet(/*marketConditionReplacer_Condition base,*/String ID){
        //this.base = base;
        this.ID = ID;
    }
    public marketConditionReplacer_ConditionSet(marketConditionReplacer_Condition base,String ID){
        this.base = base;
        this.ID = ID;
    }
    protected void addConditionInOrder(marketConditionReplacer_Condition condition){
        possabilitys.add(condition);
    }
    public void addOrReplacePossableConitions(marketConditionReplacer_Condition condition){
        for(int a = 0; a < possabilitys.size(); a++){
            if(possabilitys.get(a).ID.equals(condition.ID)){
                possabilitys.remove(a);
                break;
            }
        }
        addConditionInOrder(condition);
    }
    public void removePossableCondition(String ID){
        for(int a = 0; a < possabilitys.size(); a++){
            if(possabilitys.get(a).ID.equals(ID)){
                possabilitys.remove(a);
                return;
            }
        }

    }
    public marketConditionReplacer_Condition getPossableCondition(String ID){
        for(int a = 0; a < possabilitys.size(); a++){
            if(possabilitys.get(a).ID.equals(ID)){
                return possabilitys.get(a);
            }
        }
        return null;
    }
    public String getActiveMarketConditon(MarketAPI market){
        if (this.isConditionSet){
            MarketRetrofits_marketConditionReplacer_DefaltCondition a = (MarketRetrofits_marketConditionReplacer_DefaltCondition) market.getCondition(this.ID);
            return a.activeConditionID;
        }
        for(int b = 0; b < possabilitys.size(); b++){
            if (market.hasCondition(possabilitys.get(b).ID)){
                return possabilitys.get(b).ID;
            }
        }
        return ID;
    }
    public void activateMarketCondition(MarketAPI market){
        for(int a = 0; a < possabilitys.size(); a++){
            if(possabilitys.get(a).canApply(market)){
                possabilitys.get(a);
                if (!market.hasCondition(possabilitys.get(a).ID)){
                    String temp = getActiveMarketConditon(market);
                    if (temp != null) {
                        market.removeCondition(temp);
                    }
                    market.addCondition(possabilitys.get(a).ID);
                    if (this.isConditionSet){
                        MarketRetrofits_marketConditionReplacer_DefaltCondition d = (MarketRetrofits_marketConditionReplacer_DefaltCondition) market.getCondition(this.ID);
                        d.activeConditionID = possabilitys.get(a).ID;
                    }
                }
                return;
            }
        }
        actavateBaseMarketCondition(market);
    }
    public void actavateBaseMarketCondition(MarketAPI market){
        if (this.isConditionSet){
            if (!market.hasCondition(this.base.ID)){
                market.addCondition(this.base.ID);
            }
            return;
        }
        if (!market.hasCondition(this.ID)){
            market.addCondition(this.ID);
        }
    }
    public boolean isThisSetActive(MarketAPI market){
        if (this.isConditionSet){
            return market.hasCondition(this.ID);
        }
        for(int b = 0; b < possabilitys.size(); b++){
            if(market.hasCondition(possabilitys.get(b).ID)){
                return true;
            }
        }
        return market.hasCondition(this.ID);
    }
}
