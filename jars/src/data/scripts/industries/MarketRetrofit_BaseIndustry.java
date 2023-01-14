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
import data.scripts.MarketRetrofits_Logger;
import data.scripts.industries.Lists.MarketRetrofits_IndustryMasterList;
import data.scripts.industries.base.MarketRetrofit_IndustryDataExstange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MarketRetrofit_BaseIndustry extends MarketRetrofit_IndustryDataExstange implements MarketImmigrationModifier, FleetEventListener, RouteManager.RouteFleetSpawner{
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

    public MarketRetrofits_DefaltInstanceIndustry getActiveInstance(){
        /*how this works:
        * i will sometimes reset currentInstnace. when that happens, i will get a new one with this code.
        * all other times, i will simply return the rememberd instance.
        * also this will reduce calculation a little.*/
        if(CurrentInstance == null){
            CurrentInstance = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(super.getMarket(),super.getId(),this);
        }
        return CurrentInstance;
    }

    public void applyDataToInstance(MarketRetrofits_DefaltInstanceIndustry a){
        //MarketRetrofits_Logger.logging("running applyDataToInstance in: " + this,this);
        //MarketRetrofits_Logger.logging("    size: " + pastIndustrys.size(),this);
        a.CurrentIndustry = this;
        a.exstraData = this.exstraData;
        if(pastIndustrys.size() == 0){
            //MarketRetrofits_Logger.logging("    getting data from BaseIndustry",this);
            //readData("from base industry: ");
            a.IndustryDataGet(this,this);
            pastIndustrys.add(a);
            return;
        }
        MarketRetrofits_DefaltInstanceIndustry b = pastIndustrys.get(pastIndustrys.size() - 1);
        //MarketRetrofits_Logger.logging("    getting data from: " + b,this);
        //if(!b.equals(a)) {
            a.IndustryDataGet(b);
        //}
        pastIndustrys.add(a);
    }
    public void getDataFromInstance(MarketRetrofits_DefaltInstanceIndustry a){
        //MarketRetrofits_Logger.logging("running getDataFromInstance in: " + this,this);
        //MarketRetrofits_Logger.logging("    size: " + pastIndustrys.size(),this);
        a.CurrentIndustry = this;
        a.exstraData = this.exstraData;

        if(pastIndustrys.size() <= 1){
            //MarketRetrofits_Logger.logging("    setting data to BaseIndustry",this);
            a.IndustryDataCleanup(this);
            if(pastIndustrys.size() != 0) {
                pastIndustrys.remove(pastIndustrys.size() - 1);
            }
            return;
        }
        MarketRetrofits_DefaltInstanceIndustry b = pastIndustrys.get(pastIndustrys.size() - 1);
        //MarketRetrofits_Logger.logging("    setting data to: " + b,this);
        //if(!b.equals(a)) {
            a.IndustryDataCleanup(b);
        //}
        pastIndustrys.remove(pastIndustrys.size() - 1);
    }
    @Override
    public void apply() {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.apply(true);//done like this in normal industry
        applyDataToInstance(a);
        a.apply();
        getDataFromInstance(a);
        //a.apply();
        //get whatever industry should be used and use its methods here
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addAICoreSection(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addAICoreSection(tooltip,coreId,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addAlphaCoreDescription(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addBetaCoreDescription(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addGammaCoreDescription(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addGroundDefensesImpactSection(tooltip,bonus,commodities);
        getDataFromInstance(a);

    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addImproveDesc(info,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addImprovedSection(mode,tooltip,expanded);
        getDataFromInstance(a);

    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addInstalledItemsSection(mode,tooltip,expanded);
        getDataFromInstance(a);

    }
    @Override
    public boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        boolean re = a.addNonAICoreInstalledItems(mode,tooltip,expanded);
        getDataFromInstance(a);
        return re;

    }
    @Override
    public void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //blank by default
        applyDataToInstance(a);
        a.addPostDemandSection(tooltip,hasDemand,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addPostDescriptionSection(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addPostSupplySection(tooltip,hasSupply,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addPostUpkeepSection(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addRightAfterDescriptionSection(tooltip,mode);
        getDataFromInstance(a);
    }
    @Override
    public void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addStabilityPostDemandSection(tooltip,hasDemand,mode);
        getDataFromInstance(a);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        MarketCMD.RaidDangerLevel re = a.adjustCommodityDangerLevel(commodityId,level);
        getDataFromInstance(a);
        return re;

    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        MarketCMD.RaidDangerLevel re = a.adjustItemDangerLevel(itemId, data, level);
        getDataFromInstance(a);
        return re;
    }
    @Override
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        int re = a.adjustMarineTokensToRaidItem(itemId, data, marineTokens);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	advance(float amount){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.advance(amount);//required in all seen instances
        applyDataToInstance(a);
        a.advance(amount);
        getDataFromInstance(a);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.apply(true);//unknown requirements. still want to run it though.
        applyDataToInstance(a);
        a.apply(withIncomeUpdate);
        getDataFromInstance(a);
    }
    @Override
    public void	applyAICoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyAICoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyAICoreToIncomeAndUpkeep(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyAICoreToIncomeAndUpkeep();
        getDataFromInstance(a);
    }
    @Override
    public void	applyAlphaCoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyAlphaCoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyAlphaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyAlphaCoreSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyBetaCoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyBetaCoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyBetaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyBetaCoreSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyDeficitToProduction(index,deficit,commodities);
        getDataFromInstance(a);
    }
    @Override
    public void	applyGammaCoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyGammaCoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyGammaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyGammaCoreSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyImproveModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyImproveModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyIncomeAndUpkeep(float sizeOverride){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyIncomeAndUpkeep(sizeOverride);
        getDataFromInstance(a);
    }
    @Override
    public void	applyNoAICoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyNoAICoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	buildingFinished(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.buildingFinished();//always run this
        applyDataToInstance(a);
        a.buildingFinished();
        getDataFromInstance(a);
    }
    @Override
    public boolean	canBeDisrupted(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //allow. this is as intended.
        applyDataToInstance(a);
        boolean re = a.canBeDisrupted();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	cancelUpgrade(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.cancelUpgrade();//always run this
        applyDataToInstance(a);
        a.cancelUpgrade();
        getDataFromInstance(a);

    }
    @Override
    public boolean	canDowngrade(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.canDowngrade();//always run this
        applyDataToInstance(a);
        boolean re = a.canDowngrade();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canImprove(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.canImprove();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean canImproveToIncreaseProduction(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.canImproveToIncreaseProduction();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canInstallAICores(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.canInstallAICores();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canShutDown(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.canShutDown();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canUpgrade(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        boolean re = a.canUpgrade();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	clearUnmodified(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.clearUnmodified();
        getDataFromInstance(a);
    }
    @Override
    public BaseIndustry	clone(){
        //HERE. what how does this work? who no defalt instance
        return super.clone();
        /*
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        return a.clone();*/
    }
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.createTooltip(mode,tooltip,expanded);
        getDataFromInstance(a);
    }
    @Override
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.demand(index,commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.demand(commodityId,quantity);
        getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.demand(commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.demand(modId,commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	disruptionFinished(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.disruptionFinished();
        getDataFromInstance(a);
    }
    @Override
    public void	doPostSaveRestore(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.doPostSaveRestore();
        applyDataToInstance(a);
        a.doPostSaveRestore();
        getDataFromInstance(a);
    }
    @Override
    public void	doPreSaveCleanup(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.doPreSaveCleanup();
        applyDataToInstance(a);
        a.doPreSaveCleanup();
        getDataFromInstance(a);
    }
    @Override
    public void	downgrade(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.downgrade();
        applyDataToInstance(a);
        a.downgrade();
        getDataFromInstance(a);
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.finishBuildingOrUpgrading();
        applyDataToInstance(a);
        a.finishBuildingOrUpgrading();
        getDataFromInstance(a);
    }
    @Override
    public CargoAPI	generateCargoForGatheringPoint(java.util.Random random){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        CargoAPI re = a.generateCargoForGatheringPoint(random);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getAICoreId(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getAICoreId();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        List<Pair<String, Integer>> re = a.getAllDeficit();
        getDataFromInstance(a);
        return re;

    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        List<Pair<String, Integer>> re = a.getAllDeficit(commodityIds);;
        getDataFromInstance(a);
        return re;
    }

    @Override
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        List<MutableCommodityQuantity> re = a.getAllDemand();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        List<MutableCommodityQuantity> re = a.getAllSupply();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getBaseStabilityMod(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as inteded
        applyDataToInstance(a);
        int re = a.getBaseStabilityMod();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBaseUpkeep(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        float re = a.getBaseUpkeep();
        getDataFromInstance(a);
        return re;

    }
    @Override
    public float	getBuildCost(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        float re = a.getBuildCost();
        getDataFromInstance(a);
        return re;
    }

    @Override
    public java.lang.Float	getBuildCostOverride(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        float re = a.getBuildCostOverride();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getBuildOrUpgradeDaysText(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getBuildOrUpgradeDaysText();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getBuildOrUpgradeProgress();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getBuildOrUpgradeProgressText();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildProgress(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        float re = a.getBuildProgress();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildTime(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        float re = a.getBuildTime();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCanNotShutDownReason(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getCanNotShutDownReason();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        CargoAPI re = a.getCargoForInteractionMode(mode);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getCargoTitleForGatheringPoint();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCurrentImage(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getCurrentImage();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCurrentName(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getCurrentName();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getDeficitMult(java.lang.String... commodities){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getDeficitMult(commodities);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableCommodityQuantity	getDemand(java.lang.String id){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        MutableCommodityQuantity re = a.getDemand(id);;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getDemandReduction(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        MutableStat re = a.getDemandReduction();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getDemandReductionFromOther(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        MutableStat re = a.getDemandReductionFromOther();;
        getDataFromInstance(a);
        return re;
    }

    @Override
    public java.lang.String	getDescriptionOverride(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getDescriptionOverride();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getDisruptedDays(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getDisruptedDays();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getDisruptedKey(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getDisruptedKey();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String getId(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getId();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getImproveBonusXP(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        float re = a.getImproveBonusXP();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getImproveDialogTitle();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImprovementsDescForModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        String re = a.getImprovementsDescForModifiers();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getImproveMenuText();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getImproveProductionBonus(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        int re = a.getImproveProductionBonus();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getImproveSoundId();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getImproveStoryPoints(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        int re = a.getImproveStoryPoints();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getIncome(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableStat re = a.getIncome();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        List<InstallableIndustryItemPlugin> re = a.getInstallableItems();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MarketAPI getMarket(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        MarketAPI re = a.getMarket();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //HERE utility function
        applyDataToInstance(a);
        Pair<String, Integer> re = a.getMaxDeficit(commodityIds);;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getModId(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //HERE utility function
        applyDataToInstance(a);
        String re = a.getModId();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getModId(int index){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //HERE utility function
        applyDataToInstance(a);
        String re = a.getModId(index);;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getNameForModifier(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //HERE utility function
        applyDataToInstance(a);
        String re = a.getNameForModifier();;
        getDataFromInstance(a);
        return re;

    }
    @Override
    public float	getPatherInterest(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getPatherInterest();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getSizeMult(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getSizeMult();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public IndustrySpecAPI	getSpec(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        IndustrySpecAPI re = a.getSpec();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public SpecialItemData	getSpecialItem(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        SpecialItemData re = a.getSpecialItem();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        Pair<String, Integer> re = a.getStabilityAffectingDeficit();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getStabilityPenalty(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        int re = a.getStabilityPenalty();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableCommodityQuantity re = a.getSupply(id);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonus(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = a.getSupplyBonus();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableStat re = a.getSupplyBonusFromOther();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getTooltipWidth(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        float re = a.getTooltipWidth();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getUnavailableReason(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getUnavailableReason();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getUpkeep(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableStat re = a.getUpkeep();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        List<SpecialItemData> re = a.getVisibleInstalledItems();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.hasPostDemandSection(hasDemand, mode);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	init(java.lang.String idT, MarketAPI marketT){
        //super.init(idT,marketT);
        this.market = marketT;
        this.id = idT;
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.init(idT,marketT);
        getDataFromInstance(a);

    }
    @Override
    public void	initWithParams(java.util.List<java.lang.String> params){
        //super.initWithParams(params);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.initWithParams(params);
        getDataFromInstance(a);

    }
    @Override
    //Used when loading market from an economy .json file.
    public boolean	isAICoreId(java.lang.String str){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.isAICoreId(str);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isAvailableToBuild(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        boolean re = a.isAvailableToBuild();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isBuilding(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        boolean re = a.isBuilding();
        getDataFromInstance(a);
        return re;
    }
    @Override
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.isDemandLegal(com);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isDisrupted(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = a.isDisrupted();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isFunctional(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        ////APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        boolean re = a.isFunctional();
        getDataFromInstance(a);
        return re;
    }
    @Override
    //Building and not upgrading.
    public boolean	isHidden(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        boolean re = a.isHidden();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isImproved(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isImproved();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isIndustry(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isIndustry();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isOther(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isOther();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isStructure(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isStructure();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.isSupplyLegal(com);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isTooltipExpandable(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.isTooltipExpandable();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isUpgrading(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isUpgrading();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    //Upgrading, but not the initial building process.
    public void	modifyStabilityWithBaseMod(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.modifyStabilityWithBaseMod();
        getDataFromInstance(a);
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.notifyBeingRemoved(mode,forUpgrade);
        getDataFromInstance(a);
    }
    @Override
    public void	notifyColonyRenamed(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.notifyColonyRenamed();
        getDataFromInstance(a);
    }
    @Override
    public void	notifyDisrupted(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.notifyDisrupted();
        getDataFromInstance(a);
    }
    @Override
    public java.lang.Object	readResolve(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        Object re = a.readResolve();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	reapply(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.reapply();
        getDataFromInstance(a);

    }
    @Override
    public void	sendBuildOrUpgradeMessage(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.sendBuildOrUpgradeMessage();
        getDataFromInstance(a);
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.setAICoreId(aiCoreId);
        getDataFromInstance(a);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.setBuildCostOverride(buildCostOverride);
        getDataFromInstance(a);
    }
    @Override
    public void	setBuildProgress(float buildProgress){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setBuildProgress(buildProgress);
        getDataFromInstance(a);
    }
    @Override
    public void	setDisrupted(float days){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setDisrupted(days);
        getDataFromInstance(a);
    }
    @Override
    public void	setDisrupted(float days, boolean useMax){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setDisrupted(days,useMax);
        getDataFromInstance(a);
    }

    @Override
    public void	setHidden(boolean hidden){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setHidden(hidden);
        getDataFromInstance(a);
    }
    @Override
    public void	setImproved(boolean improved){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setImproved(improved);
        getDataFromInstance(a);
    }
    @Override
    public void	setSpecialItem(SpecialItemData special){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.setSpecialItem(special);
        getDataFromInstance(a);
    }
    @Override
    public boolean	showShutDown(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //as intended
        boolean re = a.showShutDown();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	showWhenUnavailable(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = a.showWhenUnavailable();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	startBuilding(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.startBuilding();
        getDataFromInstance(a);
    }
    @Override
    public void	startUpgrading(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.startUpgrading();
        getDataFromInstance(a);
    }
    @Override
    public void	supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.supply(index,commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.supply(commodityId,quantity);
        getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.supply(commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.supply(modId,commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	unapply(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.unapply();
        getDataFromInstance(a);
    }
    @Override
    public void	unmodifyStabilityWithBaseMod(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.unmodifyStabilityWithBaseMod();
        getDataFromInstance(a);

    }
    @Override
    public void	updateAICoreToSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateAICoreToSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	updateImprovementSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateImprovementSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateIncomeAndUpkeep();
        getDataFromInstance(a);
    }
    @Override
    public void	updateSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	upgradeFinished(Industry previous){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.upgradeFinished(previous);
        getDataFromInstance(a);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = a.wantsToUseSpecialItem(data);
        getDataFromInstance(a);
        return re;
    }
    @Override
    //Return false if already using one of that type, unless the other one is better.

    public java.lang.Object	writeReplace() {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        Object re = a.writeReplace();;
        getDataFromInstance(a);
        return re;
    }

    //HERE apply all implementations
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //as intended
        a.modifyIncoming(market,incoming);
        getDataFromInstance(a);
    }

    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.reportFleetDespawnedToListener(fleet,reason,param);
        getDataFromInstance(a);
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.reportBattleOccurred(fleet,primaryWinner,battle);
        getDataFromInstance(a);

    }

    @Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        CampaignFleetAPI re = a.spawnFleet(route);
        getDataFromInstance(a);
        return re;
    }

    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.shouldCancelRouteAfterDelayCheck(route);
        getDataFromInstance(a);
        return re;
    }

    @Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.shouldRepeat(route);
        getDataFromInstance(a);
        return re;
    }

    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.reportAboutToBeDespawnedByRouteManager(route);
        getDataFromInstance(a);
    }
}
