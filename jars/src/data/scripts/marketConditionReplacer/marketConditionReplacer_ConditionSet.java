package data.scripts.marketConditionReplacer;

import java.util.ArrayList;

public class marketConditionReplacer_ConditionSet {
    /*this will hold all the possibly conditions for a single market condition.
    * requires functions to add and replace possabilitys...*/
    public ArrayList<marketConditionReplacer_Condition> possabilitys = new ArrayList<>();
    public marketConditionReplacer_Condition base = null;
    public int ID;
    public marketConditionReplacer_ConditionSet(marketConditionReplacer_Condition base,int ID){
        this.base = base;
        this.ID = ID;
    }
}
