package data.scripts.supplyDemandLibary.base;

import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.combat.StatBonus;

public class MarketRetrofit_CCBase extends MutableStat {
    /*
        to do list:
        swap out the float modifiers with an mutable state modifiers.
        also make it an option to change that. not required.
     */
    public String ID ="";
    public float order = 0;
    public float modifiers;
    public MarketRetrofit_CCBase(String name, float orderT){
        super(0);
        ID = name;
        order = orderT;
    }
    public boolean active(){
        return true;
    }
    public void apply(Industry industry,String ID){

    }
    public void unApply(Industry industry,String ID){

    }
    //HERE. no clue if this is working at all.
    public float applyMutableStat(float input){
        //this.setBaseValue(input);
        //return this.getModifiedValue();
        setBaseValue(input);
        return getModifiedValue();
        //applyMods();
        //this.applyMods()
        //StatBonus c = new StatBonus();

        //float a = this.getFlatMod();
        //float b = this.getMult();
        //return (input + a) * b;//getModifiedValue();
    }
}
