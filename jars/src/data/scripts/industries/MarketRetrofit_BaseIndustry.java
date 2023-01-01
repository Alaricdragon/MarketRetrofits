package data.scripts.industries;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MarketRetrofit_BaseIndustry extends BaseIndustry implements MarketImmigrationModifier, FleetEventListener, RouteManager.RouteFleetSpawner{
    /* this is the industry base for all instance industry.
    * this is what an user would extend if they wanted to there industry to support this mod*/
    //public String Industry = "";
    private MarketRetorfits_ExstraData exstraData = new MarketRetorfits_ExstraData();
    private MarketRetorfits_ExstraData exstraDataTemp = new MarketRetorfits_ExstraData();
    private ArrayList<MarketRetrofits_DefaltInstanceIndustry> pastIndustrys = new ArrayList<>();
    private MarketRetrofits_DefaltInstanceIndustry CurrentInstance = null;
    public String MarketRetrofits_IndustryID(){
        return this.getSpec().getId();//would that even work?
    }
    public Object getData(String dataName){
        //pastIndustrys.add(CurrentInstance);
        //if(pastIndustrys.size() <= 1){
            return getDataPrivate(dataName);
        //}
        //return pastIndustrys.get(pastIndustrys.size() - 1).getData(dataName);
    }
    public void setData(String dataName,Object data) {
        //pastIndustrys.remove(pastIndustrys.size() - 1);
        //if(pastIndustrys.size() == 0) {
            setDataPrivate(dataName, data);
        //}
        //pastIndustrys.get(pastIndustrys.size() - 1).setData(dataName,data);
    }
    private Object getDataPrivate(String dataName){
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
                return exstraData.getData(dataName);
        }
    }
    private void setDataPrivate(String dataName,Object data){
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
                exstraData.addData(dataName,data);
        }
    }
    public MarketRetrofits_DefaltInstanceIndustry getActiveInstance(){
        /*how this works:
        * i will sometimes reset currentInstnace. when that happens, i will get a new one with this code.
        * all other times, i will simply return the rememberd instance.
        * also this will reduce calculation a little.*/
        if(CurrentInstance == null){
            return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this);
        }
        return CurrentInstance;
    }
    public MarketRetorfits_ExstraData getExstraData(){
        return exstraData;
    }

    /*
    public Object getDataOther(String dataName){
        return HeldData.getData(dataName);
    }
    public void setDataOther(String dataName,Object data){
        HeldData.addData(dataName,data);
    }*/
    @Override
    public void apply() {
        //super.apply(true);//done like this in normal industry
        getActiveInstance().apply();
        getActiveInstance().IndustryDataCleanup(this);
        //getActiveInstance().apply();
        //get whatever industry should be used and use its methods here
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        getActiveInstance().addAICoreSection(tooltip,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        //description
        getActiveInstance().addAICoreSection(tooltip,coreId,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        getActiveInstance().addAlphaCoreDescription(tooltip,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        getActiveInstance().addBetaCoreDescription(tooltip,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        getActiveInstance().addGammaCoreDescription(tooltip,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        //description
        getActiveInstance().addGroundDefensesImpactSection(tooltip,bonus,commodities);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        //description
        getActiveInstance().addImproveDesc(info,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        getActiveInstance().addImprovedSection(mode,tooltip,expanded);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        getActiveInstance().addInstalledItemsSection(mode,tooltip,expanded);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        boolean re = getActiveInstance().addNonAICoreInstalledItems(mode,tooltip,expanded);
        getActiveInstance().IndustryDataCleanup(this);
        return re;

    }
    @Override
    public void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        //blank by default
        getActiveInstance().addPostDemandSection(tooltip,hasDemand,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        getActiveInstance().addPostDescriptionSection(tooltip,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        //description
        getActiveInstance().addPostSupplySection(tooltip,hasSupply,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        getActiveInstance().addPostUpkeepSection(tooltip,mode);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        getActiveInstance().addRightAfterDescriptionSection(tooltip,mode);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        //description
        getActiveInstance().addStabilityPostDemandSection(tooltip,hasDemand,mode);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        //empty
        MarketCMD.RaidDangerLevel re = getActiveInstance().adjustCommodityDangerLevel(commodityId,level);
        getActiveInstance().IndustryDataCleanup(this);
        return re;

    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        //empty
        MarketCMD.RaidDangerLevel re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).adjustItemDangerLevel(itemId, data, level);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        //empty
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).adjustMarineTokensToRaidItem(itemId, data, marineTokens);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public void	advance(float amount){
        //super.advance(amount);//required in all seen instances
        getActiveInstance().advance(amount);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        //super.apply(true);//unknown requirements. still want to run it though.
        getActiveInstance().apply(withIncomeUpdate);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyAICoreModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().applyAICoreModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyAICoreToIncomeAndUpkeep(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().applyAICoreToIncomeAndUpkeep();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyAlphaCoreModifiers(){
        //empty
        getActiveInstance().applyAlphaCoreModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyAlphaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().applyAlphaCoreSupplyAndDemandModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyBetaCoreModifiers(){
        //empty
        getActiveInstance().applyBetaCoreModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyBetaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().applyBetaCoreSupplyAndDemandModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().applyDeficitToProduction(index,deficit,commodities);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyGammaCoreModifiers(){
        //empty
        getActiveInstance().applyGammaCoreModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyGammaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().applyGammaCoreSupplyAndDemandModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyImproveModifiers(){
        //empty
        getActiveInstance().applyImproveModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyIncomeAndUpkeep(float sizeOverride){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().applyIncomeAndUpkeep(sizeOverride);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	applyNoAICoreModifiers(){
        //empty
        getActiveInstance().applyNoAICoreModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	buildingFinished(){
        //super.buildingFinished();//always run this
        getActiveInstance().buildingFinished();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    //public static void	buildNextInQueue(MarketAPI market)
    public boolean	canBeDisrupted(){
        //allow. this is as intended.
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canBeDisrupted();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public void	cancelUpgrade(){
        //super.cancelUpgrade();//always run this
        getActiveInstance().cancelUpgrade();
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public boolean	canDowngrade(){
        //super.canDowngrade();//always run this
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canDowngrade();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	canImprove(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canImprove();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean canImproveToIncreaseProduction(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canImproveToIncreaseProduction();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	canInstallAICores(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canInstallAICores();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	canShutDown(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canShutDown();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	canUpgrade(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).canUpgrade();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public void	clearUnmodified(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().clearUnmodified();
        getActiveInstance().IndustryDataCleanup(this);
    }/*///HERE
    @Override
    public BaseIndustry	clone(){
        return getActiveInstance().clone();
    }/**/
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        getActiveInstance().createTooltip(mode,tooltip,expanded);
        getActiveInstance().IndustryDataCleanup(this);
    }/**///HERE what the hell do i even make of this?
    @Override
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        getActiveInstance().demand(index,commodityId,quantity,desc);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity){
        getActiveInstance().demand(commodityId,quantity);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        getActiveInstance().demand(commodityId,quantity,desc);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        getActiveInstance().demand(modId,commodityId,quantity,desc);
        getActiveInstance().IndustryDataCleanup(this);
    }/**/
    @Override
    public void	disruptionFinished(){
        //empty
        getActiveInstance().disruptionFinished();
        getActiveInstance().IndustryDataCleanup(this);
    }
    /**////HERE
    @Override
    public void	doPostSaveRestore(){
        //super.doPostSaveRestore();
        getActiveInstance().doPostSaveRestore();
    }
    @Override
    public void	doPreSaveCleanup(){
        //super.doPreSaveCleanup();
        getActiveInstance().doPreSaveCleanup();
    }/**/
    @Override
    public void	downgrade(){
        //super.downgrade();
        getActiveInstance().downgrade();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        //super.finishBuildingOrUpgrading();
        getActiveInstance().finishBuildingOrUpgrading();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public CargoAPI	generateCargoForGatheringPoint(java.util.Random random){
        //as intended
        CargoAPI re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).generateCargoForGatheringPoint(random);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /**///HERE
    @Override
    public java.lang.String	getAICoreId(){
        String re = getActiveInstance().getAICoreId();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        List<Pair<String, Integer>> re = getActiveInstance().getAllDeficit();
        getActiveInstance().IndustryDataCleanup(this);
        return re;

    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        List<Pair<String, Integer>> re = getActiveInstance().getAllDeficit(commodityIds);;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }

    @Override
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        List<MutableCommodityQuantity> re = getActiveInstance().getAllDemand();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        List<MutableCommodityQuantity> re = getActiveInstance().getAllSupply();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }/**/
    @Override
    public int	getBaseStabilityMod(){
        //as inteded
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBaseStabilityMod();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public float	getBaseUpkeep(){
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBaseUpkeep();
        getActiveInstance().IndustryDataCleanup(this);
        return re;

    }
    /**///HERE
    @Override
    public float	getBuildCost(){
        float re = getActiveInstance().getBuildCost();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }

    @Override
    public java.lang.Float	getBuildCostOverride(){
        float re = getActiveInstance().getBuildCostOverride();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
     /**/
    @Override
    public java.lang.String	getBuildOrUpgradeDaysText(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBuildOrUpgradeDaysText();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBuildOrUpgradeProgress();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getBuildOrUpgradeProgressText();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }/*//HERE
    @Override
    public float	getBuildProgress(){
        return getActiveInstance().getBuildProgress();
    }
    @Override
    public float	getBuildTime(){
        return getActiveInstance().getBuildTime();
    }*/
    @Override
    public java.lang.String	getCanNotShutDownReason(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCanNotShutDownReason();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        //APPLY SUPER FROM CLASS AS DEFAULT
        CargoAPI re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCargoForInteractionMode(mode);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCargoTitleForGatheringPoint();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    /*/public static float	getCommodityEconUnitMult(float size){
        return getActiveInstance().getComm
    }/**/
    public java.lang.String	getCurrentImage(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCurrentImage();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getCurrentName(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getCurrentName();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public float	getDeficitMult(java.lang.String... commodities){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getDeficitMult(commodities);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /*/@Override
    public static java.lang.String	getDeficitText(java.lang.String commodityId){
        return getActiveInstance().getDeficitText();
    }/**/
    /**///HERE
    @Override
    public MutableCommodityQuantity	getDemand(java.lang.String id){
        MutableCommodityQuantity re = getActiveInstance().getDemand(id);;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
     /**/
    /**/
    @Override
    public MutableStat	getDemandReduction(){
        MutableStat re = getActiveInstance().getDemandReduction();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public MutableStat	getDemandReductionFromOther(){
        MutableStat re = getActiveInstance().getDemandReductionFromOther();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
     /**/
    @Override
    public java.lang.String	getDescriptionOverride(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getDescriptionOverride();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public float	getDisruptedDays(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getDisruptedDays();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /**///HERE
    @Override
    public java.lang.String	getDisruptedKey(){
        String re = getActiveInstance().getDisruptedKey();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }/**/
/**/
    @Override
    public java.lang.String getId(){
        String re = getActiveInstance().getId();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }/**/
    @Override
    public float	getImproveBonusXP(){
        //as intended
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveBonusXP();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveDialogTitle();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getImprovementsDescForModifiers(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImprovementsDescForModifiers();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveMenuText();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public int	getImproveProductionBonus(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveProductionBonus();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveSoundId();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public int	getImproveStoryPoints(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getImproveStoryPoints();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public MutableStat	getIncome(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getIncome();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        List<InstallableIndustryItemPlugin> re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getInstallableItems();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /**///HERE
    @Override
    public MarketAPI	getMarket(){
        MarketAPI re = getActiveInstance().getMarket();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /**/
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        //HERE utility function
        Pair<String, Integer> re = getActiveInstance().getMaxDeficit(commodityIds);;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getModId(){
        //HERE utility function
        String re = getActiveInstance().getModId();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getModId(int index){
        //HERE utility function
        String re = getActiveInstance().getModId(index);;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getNameForModifier(){
        //HERE utility function
        String re = getActiveInstance().getNameForModifier();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;

    }/**/
    @Override
    public float	getPatherInterest(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getPatherInterest();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public float	getSizeMult(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSizeMult();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /*
    @Override
    public static float getSizeMult(float size){
        //????
        return getActiveInstance().getSizeMult(size);
    }*/

    @Override
    public IndustrySpecAPI	getSpec(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        IndustrySpecAPI re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSpec();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public SpecialItemData	getSpecialItem(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        SpecialItemData re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSpecialItem();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        Pair<String, Integer> re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getStabilityAffectingDeficit();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public int	getStabilityPenalty(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        int re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getStabilityPenalty();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableCommodityQuantity re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSupply(id);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonus(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSupplyBonus();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getSupplyBonusFromOther();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public float	getTooltipWidth(){
        //as intended
        float re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getTooltipWidth();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.lang.String	getUnavailableReason(){
        //as intended
        String re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getUnavailableReason();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public MutableStat	getUpkeep(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getUpkeep();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        List<SpecialItemData> re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).getVisibleInstalledItems();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        //as intended
        boolean re = getActiveInstance().hasPostDemandSection(hasDemand, mode);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /**///HERE
    @Override
    public void	init(java.lang.String idT, MarketAPI marketT){
        this.market = marketT;
        this.id = idT;
        //defaultInstance = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(marketT,idT,this);
        //defaultInstance = (MarketRetrofits_DefaltInstanceIndustry) defaultInstance.clone();
        //defaultInstance = defaultInstance.assign(defaultInstance, defaultInstance);
        //defaultInstance = structuredClone(defaultInstance);
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(marketT,idT,this).init(idT,marketT);
        getActiveInstance().IndustryDataCleanup(this);

    }
    @Override
    public void	initWithParams(java.util.List<java.lang.String> params){
        //this.market = marketT;
        //this.id = idT;
        getActiveInstance().initWithParams(params);
        getActiveInstance().IndustryDataCleanup(this);

    }/**/
    @Override
    //Used when loading market from an economy .json file.
    public boolean	isAICoreId(java.lang.String str){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isAICoreId(str);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	isAvailableToBuild(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isAvailableToBuild();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	isBuilding(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isBuilding();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isDemandLegal(com);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	isDisrupted(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isDisrupted();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	isFunctional(){
        ////APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isFunctional();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    //Building and not upgrading.
    public boolean	isHidden(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isHidden();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /**/
    @Override
    public boolean	isImproved(){
        boolean re = getActiveInstance().isImproved();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	isIndustry(){
        boolean re = getActiveInstance().isIndustry();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	isOther(){
        boolean re = getActiveInstance().isOther();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	isStructure(){
        boolean re = getActiveInstance().isStructure();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }/**/
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isSupplyLegal(com);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	isTooltipExpandable(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).isTooltipExpandable();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /**/
    @Override
    public boolean	isUpgrading(){
        boolean re = getActiveInstance().isUpgrading();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }/**/
    @Override
    //Upgrading, but not the initial building process.
    public void	modifyStabilityWithBaseMod(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().modifyStabilityWithBaseMod();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().notifyBeingRemoved(mode,forUpgrade);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	notifyColonyRenamed(){
        //as intended
        getActiveInstance().notifyColonyRenamed();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	notifyDisrupted(){
        //as intended
        getActiveInstance().notifyDisrupted();
        getActiveInstance().IndustryDataCleanup(this);
    }
    /**/
    @Override
    public java.lang.Object	readResolve(){
        Object re = getActiveInstance().readResolve();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public void	reapply(){
        getActiveInstance().reapply();
        getActiveInstance().IndustryDataCleanup(this);

    }/**/
    @Override
    public void	sendBuildOrUpgradeMessage(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().sendBuildOrUpgradeMessage();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().setAICoreId(aiCoreId);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().setBuildCostOverride(buildCostOverride);
        getActiveInstance().IndustryDataCleanup(this);
    }
    /**/
    @Override
    public void	setBuildProgress(float buildProgress){
        getActiveInstance().setBuildProgress(buildProgress);
        getActiveInstance().IndustryDataCleanup(this);
    }
    /**/
    @Override
    public void	setDisrupted(float days){
        getActiveInstance().setDisrupted(days);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	setDisrupted(float days, boolean useMax){
        getActiveInstance().setDisrupted(days,useMax);
        getActiveInstance().IndustryDataCleanup(this);
    }

    @Override
    public void	setHidden(boolean hidden){
        getActiveInstance().setHidden(hidden);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	setImproved(boolean improved){
        getActiveInstance().setImproved(improved);
        getActiveInstance().IndustryDataCleanup(this);
    }/**/
    @Override
    public void	setSpecialItem(SpecialItemData special){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().setSpecialItem(special);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public boolean	showShutDown(){
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).showShutDown();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public boolean	showWhenUnavailable(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).showWhenUnavailable();
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    @Override
    public void	startBuilding(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().startBuilding();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	startUpgrading(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().startUpgrading();
        getActiveInstance().IndustryDataCleanup(this);
    }
    /**/@Override
    public void	supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        getActiveInstance().supply(index,commodityId,quantity,desc);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity){
        getActiveInstance().supply(commodityId,quantity);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        getActiveInstance().supply(commodityId,quantity,desc);
        getActiveInstance().IndustryDataCleanup(this);
    }/**/
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().supply(modId,commodityId,quantity,desc);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	unapply(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().unapply();
        getActiveInstance().IndustryDataCleanup(this);
    }
    /**/
    @Override
    public void	unmodifyStabilityWithBaseMod(){
        getActiveInstance().unmodifyStabilityWithBaseMod();
        getActiveInstance().IndustryDataCleanup(this);

    }/**/
    @Override
    public void	updateAICoreToSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().updateAICoreToSupplyAndDemandModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	updateImprovementSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().updateImprovementSupplyAndDemandModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().updateIncomeAndUpkeep();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	updateSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().updateSupplyAndDemandModifiers();
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public void	upgradeFinished(Industry previous){
        //APPLY SUPER FROM CLASS AS DEFAULT
        getActiveInstance().upgradeFinished(previous);
        getActiveInstance().IndustryDataCleanup(this);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).wantsToUseSpecialItem(data);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }
    /**/
    @Override
    //Return false if already using one of that type, unless the other one is better.

    public java.lang.Object	writeReplace() {
        Object re = getActiveInstance().writeReplace();;
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }/**/

    //HERE apply all implementations
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        //as intended
        getActiveInstance().modifyIncoming(market,incoming);
        getActiveInstance().IndustryDataCleanup(this);
    }

    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        //as intended
        getActiveInstance().reportFleetDespawnedToListener(fleet,reason,param);
        getActiveInstance().IndustryDataCleanup(this);
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {
        //as intended
        getActiveInstance().reportBattleOccurred(fleet,primaryWinner,battle);
        getActiveInstance().IndustryDataCleanup(this);

    }

    @Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        //as intended
        CampaignFleetAPI re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).spawnFleet(route);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }

    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).shouldCancelRouteAfterDelayCheck(route);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }

    @Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        //as intended
        boolean re = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(), getId(), this).shouldRepeat(route);
        getActiveInstance().IndustryDataCleanup(this);
        return re;
    }

    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {
        //as intended
        getActiveInstance().reportAboutToBeDespawnedByRouteManager(route);
        getActiveInstance().IndustryDataCleanup(this);
    }
}
