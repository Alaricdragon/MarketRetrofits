package data.scripts.marketConditionReplacer;

import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class marketConditionReplacer_Condition {
    /*this acts as the 'filling' to the sell of marketConditionReplacer.
    * this also requires all the functions a normal market condition has.*/
    public String ID;
    public int order;
    public marketConditionReplacer_Condition(String ID,int order){
        this.ID = ID;
        this.order = order;
    }
    public boolean canApply(MarketAPI market){
        return false;
    }
}
