package data.scripts.industries;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.econ.ResourceDepositsCondition;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.Lists.MarketRetrofits_IndustryList;
import data.scripts.industries.Lists.MarketRetrofits_IndustryMasterList;

import java.awt.*;

public class MarketRetrofits_InstanceIndustry {
    /* this is the stored data of an industry. this is what a user will extend if they want to add an instanced industry.
    *  requires:
    *  mimics of every industry function, that all try to run the base industry
    * */
    public String ID;
    public float order;
    public MarketRetrofits_IndustryList industryGroup = null;
    MarketRetrofits_InstanceIndustry(String name,float orderT) {
        ID = name;
        order = orderT;
    }
    public void applyToIndustry(String industry){
        //apply to industry here.
        MarketRetrofits_IndustryMasterList.addInstance(industry,this);
        industryGroup = MarketRetrofits_IndustryMasterList.getInstance(industry);
    }
    public void applyAsDefaltIndustry(String industry){
        //apply to industry here.
        MarketRetrofits_IndustryMasterList.setDefaltInstance(industry,this);
        industryGroup = MarketRetrofits_IndustryMasterList.getInstance(industry);
    }
    //this class requires every single function held in baseIndustry, as well as an an function to determon if its active or not.
    protected boolean Active = true;
    public boolean canImply(MarketAPI market){
        return Active;
    }

    /*from here on out, there is only functions that replace BaseIndustry functions*/
    public void apply(){

    }
    public void unapply(){
    }
    protected boolean hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        return false;
    }
    protected void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
    }


    public boolean isAvailableToBuild() {
        return false;
    }

    public String getUnavailableReason() {
        return null;
    }

    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
    }

    public String getCurrentImage() {
        return null;//super.getCurrentImage();
    }

    public float getPatherInterest() {
        return 0f;
    }
    protected boolean canImproveToIncreaseProduction() {
        return true;
    }



    public void applyVisuals(PlanetAPI planet) {
    }

    public void unapplyVisuals(PlanetAPI planet) {
    }
    public void setSpecialItem(SpecialItemData special) {
    }



}
