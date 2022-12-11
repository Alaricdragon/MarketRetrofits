package data.scripts.industries;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.*;
import com.fs.starfarer.api.campaign.listeners.FleetEventListener;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.fleets.RouteManager;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.Lists.MarketRetrofits_IndustryMasterList;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class MarketRetrofit_BaseIndustry extends BaseIndustry implements MarketImmigrationModifier, FleetEventListener, RouteManager.RouteFleetSpawner{
    /* this is the industry base for all instance industry.
    * this is what an user would extend if they wanted to there industry to support this mod*/
    //protected String Industry = "";

    public String MarketRetrofits_IndustryID(){
        return "";
    }
    public Object getData(String dataName){
        //building a list of data here
        /*
        this.aiCoreId;
        this.buildCostOverride;
        this.building;
        this.buildProgress;
        this.buildProgress;

        this.buildTime;
        this.currTooltipMode;
        this.demand;
        this.demandReduction;

        this.demandReductionFromOther;
        this.dKey;
        this.hasInstallableItems;
        this.hiddenOverride;
        this.id;
        this.improved;

        this.income;
        this.spec;
        this.special;
        this.supply;
        this.supplyBonus;
        this.supplyBonusFromOther;
        this.upgradeId;
        this.upkeep;
        this.wasDisrupted;*/
        switch (dataName){
            case "market":
                return market;
            case "aiCoreId":
                return aiCoreId;
            case "buildCostOverride":
                return buildCostOverride;
            case "building":
                return building;
            case "buildProgress":
                return buildProgress;
            case "buildTime":
                return buildTime;
            case "currTooltipMode":
                return currTooltipMode;
            case "demand":
                return demand;
            case "demandReduction":
                return demandReduction;
            case "demandReductionFromOther":
                return demandReductionFromOther;
            case "dKey":
                return dKey;
            case "hasInstallableItems":
                return hasInstallableItems;
            case "hiddenOverride":
                return hiddenOverride;
            case "id":
                return id;
            case "improved":
                return improved;
            case "income":
                return income;
            case "spec":
                return spec;
            case "special":
                return special;
            case "supply":
                return supply;
            case "supplyBonus":
                return supplyBonus;
            case "supplyBonusFromOther":
                return supplyBonusFromOther;
            case "upgradeId":
                return upgradeId;
            case "upkeep":
                return upkeep;
            case "wasDisrupted":
                return wasDisrupted;
            default:
                return getDataOther(dataName);
        }
    }
    public void setData(String dataName,Object data){
        switch (dataName){
            case "market":
                market = (MarketAPI) data;
            case "aiCoreId":
                aiCoreId = (String) data;
            case "buildCostOverride":
                buildCostOverride = (Float) data;
            case "building":
                building = (boolean) data;
            case "buildProgress":
                buildProgress = (float) data;
            case "buildTime":
                buildTime = (float) data;
            case "currTooltipMode":
                currTooltipMode = (IndustryTooltipMode) data;
            case "demand":
                demand = (Map<String, MutableCommodityQuantity>) data;
            case "demandReduction":
                demandReduction = (MutableStat) data;
            case "demandReductionFromOther":
                demandReductionFromOther = (MutableStat) data;
            case "dKey":
                dKey = (String) data;
            case "hasInstallableItems":
                hasInstallableItems = (Boolean) data;
            case "hiddenOverride":
                hiddenOverride = (Boolean) data;
            case "id":
                id = (String) data;
            case "improved":
                improved = (Boolean) data;
            case "income":
                income = (MutableStat) data;
            case "spec":
                spec = (IndustrySpecAPI) data;
            case "special":
                special = (SpecialItemData) data;
            case "supply":
                supply = (Map<String, MutableCommodityQuantity>) data;
            case "supplyBonus":
                supplyBonus = (MutableStat) data;
            case "supplyBonusFromOther":
                supplyBonusFromOther = (MutableStat) data;
            case "upgradeId":
                upgradeId = (String) data;
            case "upkeep":
                upkeep = (MutableStat) data;
            case "wasDisrupted":
                wasDisrupted = (boolean) data;
            default:
                setDataOther(dataName,data);
        }
    }
    private Object getDataOther(String dataName){
        return null;
    }
    private void setDataOther(String dataName,Object data){

    }
    @Override
    public void apply() {
        super.apply(true);//done like this in normal industry
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).apply();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        //MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).apply();
        //get whatever industry should be used and use its methods here
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addAICoreSection(tooltip,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addAICoreSection(tooltip,coreId,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addAlphaCoreDescription(tooltip,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addBetaCoreDescription(tooltip,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addGammaCoreDescription(tooltip,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addGroundDefensesImpactSection(tooltip,bonus,commodities);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addImproveDesc(info,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addImprovedSection(mode,tooltip,expanded);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addInstalledItemsSection(mode,tooltip,expanded);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addNonAICoreInstalledItems(mode,tooltip,expanded);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;

    }
    @Override
    protected void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        //blank by default
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addPostDemandSection(tooltip,hasDemand,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addPostDescriptionSection(tooltip,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addPostSupplySection(tooltip,hasSupply,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addPostUpkeepSection(tooltip,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    protected void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addRightAfterDescriptionSection(tooltip,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addStabilityPostDemandSection(tooltip,hasDemand,mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        //empty
        MarketCMD.RaidDangerLevel re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).adjustCommodityDangerLevel(commodityId,level);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;

    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        //empty
        MarketCMD.RaidDangerLevel re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).adjustItemDangerLevel(itemId, data, level);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        //empty
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).adjustMarineTokensToRaidItem(itemId, data, marineTokens);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public void	advance(float amount){
        super.advance(amount);//required in all seen instances
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).advance(amount);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        super.apply(true);//unknown requirements. still want to run it though.
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).apply(withIncomeUpdate);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyAICoreModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyAICoreModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyAICoreToIncomeAndUpkeep(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyAICoreToIncomeAndUpkeep();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyAlphaCoreModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyAlphaCoreModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyAlphaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyAlphaCoreSupplyAndDemandModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyBetaCoreModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyBetaCoreModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyBetaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyBetaCoreSupplyAndDemandModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyDeficitToProduction(index,deficit,commodities);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyGammaCoreModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyGammaCoreModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyGammaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyGammaCoreSupplyAndDemandModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyImproveModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyImproveModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyIncomeAndUpkeep(float sizeOverride){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyIncomeAndUpkeep(sizeOverride);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	applyNoAICoreModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyNoAICoreModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	buildingFinished(){
        super.buildingFinished();//always run this
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).buildingFinished();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    //public static void	buildNextInQueue(MarketAPI market)
    public boolean	canBeDisrupted(){
        //allow. this is as intended.
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canBeDisrupted();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public void	cancelUpgrade(){
        super.cancelUpgrade();//always run this
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).cancelUpgrade();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }
    @Override
    public boolean	canDowngrade(){
        super.canDowngrade();//always run this
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canDowngrade();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	canImprove(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canImprove();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    protected boolean canImproveToIncreaseProduction(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canImproveToIncreaseProduction();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	canInstallAICores(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canInstallAICores();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	canShutDown(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canShutDown();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	canUpgrade(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canUpgrade();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public void	clearUnmodified(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).clearUnmodified();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }/*//HERE
    @Override
    protected BaseIndustry	clone(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).clone();
    }*/
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).createTooltip(mode,tooltip,expanded);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }/*//HERE what the hell do i even make of this?
    @Override
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).demand(index,commodityId,quantity,desc);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).demand(commodityId,quantity);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).demand(commodityId,quantity,desc);
    }
    @Override
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).demand(modId,commodityId,quantity,desc);
    }*/
    @Override
    protected void	disruptionFinished(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).disruptionFinished();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    /*//HERE
    @Override
    public void	doPostSaveRestore(){
        super.doPostSaveRestore();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).doPostSaveRestore();
    }
    @Override
    public void	doPreSaveCleanup(){
        super.doPreSaveCleanup();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).doPreSaveCleanup();
    }*/
    @Override
    public void	downgrade(){
        super.downgrade();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).downgrade();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        super.finishBuildingOrUpgrading();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).finishBuildingOrUpgrading();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public CargoAPI	generateCargoForGatheringPoint(java.util.Random random){
        //as intended
        CargoAPI re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).generateCargoForGatheringPoint(random);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*//HERE
    @Override
    public java.lang.String	getAICoreId(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getAICoreId();
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getAllDeficit();
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getAllDeficit(commodityIds);
    }

    @Override
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getAllDemand();
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getAllSupply();
    }*/
    @Override
    protected int	getBaseStabilityMod(){
        //as inteded
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBaseStabilityMod();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public float	getBaseUpkeep(){
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBaseUpkeep();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;

    }
    /*//HERE
    @Override
    public float	getBuildCost(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBuildCost();
    }

    @Override
    public java.lang.Float	getBuildCostOverride(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBuildCostOverride();
    }
     */
    @Override
    public java.lang.String	getBuildOrUpgradeDaysText(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBuildOrUpgradeDaysText();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBuildOrUpgradeProgress();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBuildOrUpgradeProgressText();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }/*//HERE
    @Override
    public float	getBuildProgress(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBuildProgress();
    }
    @Override
    public float	getBuildTime(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBuildTime();
    }*/
    @Override
    public java.lang.String	getCanNotShutDownReason(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCanNotShutDownReason();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    protected CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        //APPLY SUPER FROM CLASS AS DEFAULT
        CargoAPI re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCargoForInteractionMode(mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCargoTitleForGatheringPoint();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    /*public static float	getCommodityEconUnitMult(float size){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getComm
    }*/
    public java.lang.String	getCurrentImage(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCurrentImage();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.lang.String	getCurrentName(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCurrentName();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    protected float	getDeficitMult(java.lang.String... commodities){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getDeficitMult(commodities);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*@Override
    public static java.lang.String	getDeficitText(java.lang.String commodityId){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getDeficitText();
    }*/
    /*//HERE
    @Override
    public MutableCommodityQuantity	getDemand(java.lang.String id){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getDemand(id);
    }
     */
    /*
    @Override
    public MutableStat	getDemandReduction(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getDemandReduction();
    }
    @Override
    public MutableStat	getDemandReductionFromOther(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getDemandReductionFromOther();
    }
     */
    @Override
    protected java.lang.String	getDescriptionOverride(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getDescriptionOverride();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public float	getDisruptedDays(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getDisruptedDays();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*//HERE
    @Override
    public java.lang.String	getDisruptedKey(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getDisruptedKey();
    }*/
/*
    @Override
    public java.lang.String getId(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getId();
    }*/
    @Override
    public float	getImproveBonusXP(){
        //as intended
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveBonusXP();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveDialogTitle();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    protected java.lang.String	getImprovementsDescForModifiers(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImprovementsDescForModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveMenuText();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    protected int	getImproveProductionBonus(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveProductionBonus();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveSoundId();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public int	getImproveStoryPoints(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveStoryPoints();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public MutableStat	getIncome(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getIncome();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        List<InstallableIndustryItemPlugin> re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getInstallableItems();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*//HERE
    @Override
    public MarketAPI	getMarket(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getMarket();
    }*/
    /*
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        //HERE utility function
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getMaxDeficit(commodityIds);
    }
    @Override
    protected java.lang.String	getModId(){
        //HERE utility function
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getModId();
    }
    @Override
    protected java.lang.String	getModId(int index){
        //HERE utility function
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getModId(index);
    }
    @Override
    public java.lang.String	getNameForModifier(){
        //HERE utility function
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getNameForModifier();

    }*/
    @Override
    public float	getPatherInterest(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getPatherInterest();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public float	getSizeMult(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSizeMult();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*
    @Override
    public static float getSizeMult(float size){
        //????
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getSizeMult(size);
    }*/

    @Override
    public IndustrySpecAPI	getSpec(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        IndustrySpecAPI re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSpec();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public SpecialItemData	getSpecialItem(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        SpecialItemData re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSpecialItem();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    protected Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        Pair<String, Integer> re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getStabilityAffectingDeficit();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    protected int	getStabilityPenalty(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getStabilityPenalty();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableCommodityQuantity re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSupply(id);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonus(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSupplyBonus();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSupplyBonusFromOther();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public float	getTooltipWidth(){
        //as intended
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getTooltipWidth();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.lang.String	getUnavailableReason(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getUnavailableReason();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public MutableStat	getUpkeep(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getUpkeep();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        List<SpecialItemData> re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getVisibleInstalledItems();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    protected boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        //as intended
        boolean re = hasPostDemandSection(hasDemand, mode);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*//HERE
    @Override
    public void	init(java.lang.String idT, MarketAPI marketT){
        this.market = marketT;
        this.id = idT;
        //defaultInstance = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(marketT,idT,this);
        //defaultInstance = (MarketRetrofits_DefaltInstanceIndustry) defaultInstance.clone();
        //defaultInstance = defaultInstance.assign(defaultInstance, defaultInstance);
        //defaultInstance = structuredClone(defaultInstance);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(marketT,idT,this).init(idT,marketT);
    }
    @Override
    public void	initWithParams(java.util.List<java.lang.String> params){
        //this.market = marketT;
        //this.id = idT;
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).initWithParams(params);
    }*/
    @Override
    //Used when loading market from an economy .json file.
    protected boolean	isAICoreId(java.lang.String str){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isAICoreId(str);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	isAvailableToBuild(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isAvailableToBuild();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	isBuilding(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isBuilding();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isDemandLegal(com);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	isDisrupted(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isDisrupted();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	isFunctional(){
        ////APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isFunctional();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    //Building and not upgrading.
    public boolean	isHidden(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isHidden();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*
    @Override
    public boolean	isImproved(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isImproved();
    }
    @Override
    public boolean	isIndustry(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isIndustry();
    }
    @Override
    public boolean	isOther(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isOther();
    }
    @Override
    public boolean	isStructure(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isStructure();
    }*/
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isSupplyLegal(com);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	isTooltipExpandable(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isTooltipExpandable();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*
    @Override
    public boolean	isUpgrading(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isUpgrading();
    }*/
    @Override
    //Upgrading, but not the initial building process.
    protected void	modifyStabilityWithBaseMod(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).modifyStabilityWithBaseMod();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).notifyBeingRemoved(mode,forUpgrade);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	notifyColonyRenamed(){
        //as intended
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).notifyColonyRenamed();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	notifyDisrupted(){
        //as intended
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).notifyDisrupted();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    /*
    @Override
    protected java.lang.Object	readResolve(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).readResolve();
    }
    @Override
    public void	reapply(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).reapply();
    }*/
    @Override
    protected void	sendBuildOrUpgradeMessage(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).sendBuildOrUpgradeMessage();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setAICoreId(aiCoreId);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBuildCostOverride(buildCostOverride);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    /*
    @Override
    public void	setBuildProgress(float buildProgress){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBuildProgress(buildProgress);
    }*/
    /*
    @Override
    public void	setDisrupted(float days){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setDisrupted(days);
    }
    @Override
    public void	setDisrupted(float days, boolean useMax){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setDisrupted(days,useMax);
    }

    @Override
    public void	setHidden(boolean hidden){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setHidden(hidden);
    }
    @Override
    public void	setImproved(boolean improved){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setImproved(improved);
    }*/
    @Override
    public void	setSpecialItem(SpecialItemData special){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setSpecialItem(special);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public boolean	showShutDown(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).showShutDown();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public boolean	showWhenUnavailable(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).showWhenUnavailable();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    @Override
    public void	startBuilding(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).startBuilding();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	startUpgrading(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).startUpgrading();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    /*@Override
    public void	supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).supply(index,commodityId,quantity,desc);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).supply(commodityId,quantity);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).supply(commodityId,quantity,desc);
    }*/
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).supply(modId,commodityId,quantity,desc);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	unapply(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).unapply();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    /*
    @Override
    protected void	unmodifyStabilityWithBaseMod(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).unmodifyStabilityWithBaseMod();
    }*/
    @Override
    protected void	updateAICoreToSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).updateAICoreToSupplyAndDemandModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	updateImprovementSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).updateImprovementSupplyAndDemandModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).updateIncomeAndUpkeep();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	updateSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).updateSupplyAndDemandModifiers();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    protected void	upgradeFinished(Industry previous){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).upgradeFinished(previous);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).wantsToUseSpecialItem(data);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }
    /*
    @Override
    //Return false if already using one of that type, unless the other one is better.

    protected java.lang.Object	writeReplace() {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).writeReplace();
    }*/

    //HERE apply all implementations
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        //as intended
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).modifyIncoming(market,incoming);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }

    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        //as intended
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).reportFleetDespawnedToListener(fleet,reason,param);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {
        //as intended
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).reportBattleOccurred(fleet,primaryWinner,battle);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);

    }

    @Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        //as intended
        CampaignFleetAPI re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).spawnFleet(route);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }

    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).shouldCancelRouteAfterDelayCheck(route);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }

    @Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).shouldRepeat(route);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
        return re;
    }

    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {
        //as intended
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).reportAboutToBeDespawnedByRouteManager(route);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBaseDataToIndustry(this);
    }
}
