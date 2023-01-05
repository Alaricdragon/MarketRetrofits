package data.scripts.industries;

import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.*;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.fleets.RouteManager;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.Lists.MarketRetrofits_IndustryMasterList;

import java.util.List;

public class MarketRetrofits_InstanceIndustry extends MarketRetrofits_DefaltInstanceIndustry {
    /* this is the stored data of an industry. this is what a user will extend if they want to add an instanced industry.
    *  requires:
    *  mimics of every industry function, that all try to run the base industry
    * */

    public MarketRetrofits_InstanceIndustry(String name,float orderT) {
        super(name, orderT);
    }
    @Override
    public void applyToIndustry(String industry){
        //apply to industry here.
        MarketRetrofits_IndustryMasterList.addInstance(industry,this);
        industryGroup = MarketRetrofits_IndustryMasterList.getInstance(industry);
    }




    @Override
    public void apply() {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.apply(true);//done like this in normal industry
        CurrentIndustry.applyDataToInstance(a);
        a.apply();
        CurrentIndustry.getDataFromInstance(a);
        //a.apply();
        //get whatever industry should be used and use its methods here
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addAICoreSection(tooltip,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addAICoreSection(tooltip,coreId,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addAlphaCoreDescription(tooltip,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addBetaCoreDescription(tooltip,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addGammaCoreDescription(tooltip,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addGroundDefensesImpactSection(tooltip,bonus,commodities);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addImproveDesc(info,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addImprovedSection(mode,tooltip,expanded);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addInstalledItemsSection(mode,tooltip,expanded);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.addNonAICoreInstalledItems(mode,tooltip,expanded);
        CurrentIndustry.getDataFromInstance(a);
        return re;

    }
    @Override
    public void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //blank by default
        CurrentIndustry.applyDataToInstance(a);
        a.addPostDemandSection(tooltip,hasDemand,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addPostDescriptionSection(tooltip,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addPostSupplySection(tooltip,hasSupply,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addPostUpkeepSection(tooltip,mode);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addRightAfterDescriptionSection(tooltip,mode);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.addStabilityPostDemandSection(tooltip,hasDemand,mode);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        MarketCMD.RaidDangerLevel re = a.adjustCommodityDangerLevel(commodityId,level);
        CurrentIndustry.getDataFromInstance(a);
        return re;

    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        MarketCMD.RaidDangerLevel re = a.adjustItemDangerLevel(itemId, data, level);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        int re = a.adjustMarineTokensToRaidItem(itemId, data, marineTokens);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public void	advance(float amount){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.advance(amount);//required in all seen instances
        CurrentIndustry.applyDataToInstance(a);
        a.advance(amount);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.apply(true);//unknown requirements. still want to run it though.
        CurrentIndustry.applyDataToInstance(a);
        a.apply(withIncomeUpdate);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyAICoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.applyAICoreModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyAICoreToIncomeAndUpkeep(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.applyAICoreToIncomeAndUpkeep();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyAlphaCoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        a.applyAlphaCoreModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyAlphaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.applyAlphaCoreSupplyAndDemandModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyBetaCoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        a.applyBetaCoreModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyBetaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.applyBetaCoreSupplyAndDemandModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.applyDeficitToProduction(index,deficit,commodities);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyGammaCoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        a.applyGammaCoreModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyGammaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.applyGammaCoreSupplyAndDemandModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyImproveModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        a.applyImproveModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyIncomeAndUpkeep(float sizeOverride){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.applyIncomeAndUpkeep(sizeOverride);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	applyNoAICoreModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        a.applyNoAICoreModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	buildingFinished(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.buildingFinished();//always run this
        CurrentIndustry.applyDataToInstance(a);
        a.buildingFinished();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public boolean	canBeDisrupted(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //allow. this is as intended.
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.canBeDisrupted();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public void	cancelUpgrade(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.cancelUpgrade();//always run this
        CurrentIndustry.applyDataToInstance(a);
        a.cancelUpgrade();
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public boolean	canDowngrade(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.canDowngrade();//always run this
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.canDowngrade();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canImprove(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.canImprove();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean canImproveToIncreaseProduction(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.canImproveToIncreaseProduction();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canInstallAICores(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.canInstallAICores();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canShutDown(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.canShutDown();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canUpgrade(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        boolean re = a.canUpgrade();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public void	clearUnmodified(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.clearUnmodified();
        CurrentIndustry.getDataFromInstance(a);
    }//HERE
    /*
    @Override
    public BaseIndustry clone(){
    //HERE. this is not active. what is this?!?!
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        return a.clone();

    }*/
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //description
        CurrentIndustry.applyDataToInstance(a);
        a.createTooltip(mode,tooltip,expanded);
        CurrentIndustry.getDataFromInstance(a);
    }//HERE what the hell do i even make of this?
    @Override
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.demand(index,commodityId,quantity,desc);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.demand(commodityId,quantity);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.demand(commodityId,quantity,desc);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.demand(modId,commodityId,quantity,desc);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	disruptionFinished(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //empty
        CurrentIndustry.applyDataToInstance(a);
        a.disruptionFinished();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	doPostSaveRestore(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.doPostSaveRestore();
        CurrentIndustry.applyDataToInstance(a);
        a.doPostSaveRestore();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	doPreSaveCleanup(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.doPreSaveCleanup();
        CurrentIndustry.applyDataToInstance(a);
        a.doPreSaveCleanup();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	downgrade(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.downgrade();
        CurrentIndustry.applyDataToInstance(a);
        a.downgrade();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //super.finishBuildingOrUpgrading();
        CurrentIndustry.applyDataToInstance(a);
        a.finishBuildingOrUpgrading();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public CargoAPI	generateCargoForGatheringPoint(java.util.Random random){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        CargoAPI re = a.generateCargoForGatheringPoint(random);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getAICoreId(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getAICoreId();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        List<Pair<String, Integer>> re = a.getAllDeficit();
        CurrentIndustry.getDataFromInstance(a);
        return re;

    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        List<Pair<String, Integer>> re = a.getAllDeficit(commodityIds);;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }

    @Override
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        List<MutableCommodityQuantity> re = a.getAllDemand();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        List<MutableCommodityQuantity> re = a.getAllSupply();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getBaseStabilityMod(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as inteded
        CurrentIndustry.applyDataToInstance(a);
        int re = a.getBaseStabilityMod();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBaseUpkeep(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getBaseUpkeep();
        CurrentIndustry.getDataFromInstance(a);
        return re;

    }
    @Override
    public float	getBuildCost(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getBuildCost();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }

    @Override
    public java.lang.Float	getBuildCostOverride(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getBuildCostOverride();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getBuildOrUpgradeDaysText(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getBuildOrUpgradeDaysText();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getBuildOrUpgradeProgress();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getBuildOrUpgradeProgressText();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildProgress(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getBuildProgress();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildTime(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getBuildTime();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCanNotShutDownReason(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getCanNotShutDownReason();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        CargoAPI re = a.getCargoForInteractionMode(mode);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getCargoTitleForGatheringPoint();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCurrentImage(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getCurrentImage();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCurrentName(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getCurrentName();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getDeficitMult(java.lang.String... commodities){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getDeficitMult(commodities);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableCommodityQuantity	getDemand(java.lang.String id){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        MutableCommodityQuantity re = a.getDemand(id);;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getDemandReduction(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        MutableStat re = a.getDemandReduction();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getDemandReductionFromOther(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        MutableStat re = a.getDemandReductionFromOther();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }

    @Override
    public java.lang.String	getDescriptionOverride(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getDescriptionOverride();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getDisruptedDays(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getDisruptedDays();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getDisruptedKey(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getDisruptedKey();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String getId(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getId();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getImproveBonusXP(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getImproveBonusXP();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getImproveDialogTitle();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImprovementsDescForModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        String re = a.getImprovementsDescForModifiers();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getImproveMenuText();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getImproveProductionBonus(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        int re = a.getImproveProductionBonus();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getImproveSoundId();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getImproveStoryPoints(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        int re = a.getImproveStoryPoints();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getIncome(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        MutableStat re = a.getIncome();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        List<InstallableIndustryItemPlugin> re = a.getInstallableItems();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MarketAPI	getMarket(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        MarketAPI re = a.getMarket();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //HERE utility function
        CurrentIndustry.applyDataToInstance(a);
        Pair<String, Integer> re = a.getMaxDeficit(commodityIds);;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getModId(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //HERE utility function
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getModId();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getModId(int index){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //HERE utility function
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getModId(index);;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getNameForModifier(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //HERE utility function
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getNameForModifier();;
        CurrentIndustry.getDataFromInstance(a);
        return re;

    }
    @Override
    public float	getPatherInterest(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getPatherInterest();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getSizeMult(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getSizeMult();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public IndustrySpecAPI	getSpec(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        IndustrySpecAPI re = a.getSpec();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public SpecialItemData	getSpecialItem(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        SpecialItemData re = a.getSpecialItem();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        Pair<String, Integer> re = a.getStabilityAffectingDeficit();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getStabilityPenalty(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        int re = a.getStabilityPenalty();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        MutableCommodityQuantity re = a.getSupply(id);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonus(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        MutableStat re = a.getSupplyBonus();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        MutableStat re = a.getSupplyBonusFromOther();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getTooltipWidth(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        float re = a.getTooltipWidth();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getUnavailableReason(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        String re = a.getUnavailableReason();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getUpkeep(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        MutableStat re = a.getUpkeep();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        List<SpecialItemData> re = a.getVisibleInstalledItems();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.hasPostDemandSection(hasDemand, mode);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public void	init(java.lang.String idT, MarketAPI marketT){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.init(idT,marketT);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	initWithParams(java.util.List<java.lang.String> params){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.initWithParams(params);
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    //Used when loading market from an economy .json file.
    public boolean	isAICoreId(java.lang.String str){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isAICoreId(str);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isAvailableToBuild(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isAvailableToBuild();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isBuilding(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isBuilding();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isDemandLegal(com);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isDisrupted(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = a.isDisrupted();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isFunctional(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        ////APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isFunctional();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    //Building and not upgrading.
    public boolean	isHidden(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isHidden();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isImproved(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isImproved();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isIndustry(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isIndustry();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isOther(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isOther();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isStructure(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isStructure();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isSupplyLegal(com);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isTooltipExpandable(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isTooltipExpandable();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isUpgrading(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.isUpgrading();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    //Upgrading, but not the initial building process.
    public void	modifyStabilityWithBaseMod(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.modifyStabilityWithBaseMod();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.notifyBeingRemoved(mode,forUpgrade);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	notifyColonyRenamed(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        a.notifyColonyRenamed();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	notifyDisrupted(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        a.notifyDisrupted();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public java.lang.Object	readResolve(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        Object re = a.readResolve();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public void	reapply(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.reapply();
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	sendBuildOrUpgradeMessage(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.sendBuildOrUpgradeMessage();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.setAICoreId(aiCoreId);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //APPLY SUPER FROM CLASS AS DEFAULT
        CurrentIndustry.applyDataToInstance(a);
        a.setBuildCostOverride(buildCostOverride);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	setBuildProgress(float buildProgress){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.setBuildProgress(buildProgress);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	setDisrupted(float days){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.setDisrupted(days);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	setDisrupted(float days, boolean useMax){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.setDisrupted(days,useMax);
        CurrentIndustry.getDataFromInstance(a);
    }

    @Override
    public void	setHidden(boolean hidden){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.setHidden(hidden);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	setImproved(boolean improved){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.setImproved(improved);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	setSpecialItem(SpecialItemData special){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.setSpecialItem(special);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public boolean	showShutDown(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //as intended
        boolean re = a.showShutDown();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	showWhenUnavailable(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = a.showWhenUnavailable();
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    public void	startBuilding(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.startBuilding();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	startUpgrading(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.startUpgrading();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.supply(index,commodityId,quantity,desc);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.supply(commodityId,quantity);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.supply(commodityId,quantity,desc);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.supply(modId,commodityId,quantity,desc);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	unapply(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.unapply();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	unmodifyStabilityWithBaseMod(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        a.unmodifyStabilityWithBaseMod();
        CurrentIndustry.getDataFromInstance(a);

    }
    @Override
    public void	updateAICoreToSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateAICoreToSupplyAndDemandModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	updateImprovementSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateImprovementSupplyAndDemandModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateIncomeAndUpkeep();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	updateSupplyAndDemandModifiers(){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateSupplyAndDemandModifiers();
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public void	upgradeFinished(Industry previous){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.upgradeFinished(previous);
        CurrentIndustry.getDataFromInstance(a);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = a.wantsToUseSpecialItem(data);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }
    @Override
    //Return false if already using one of that type, unless the other one is better.

    public java.lang.Object	writeReplace() {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        Object re = a.writeReplace();;
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }

    //HERE apply all implementations
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        CurrentIndustry.applyDataToInstance(a);
        //as intended
        a.modifyIncoming(market,incoming);
        CurrentIndustry.getDataFromInstance(a);
    }

    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        a.reportFleetDespawnedToListener(fleet,reason,param);
        CurrentIndustry.getDataFromInstance(a);
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        a.reportBattleOccurred(fleet,primaryWinner,battle);
        CurrentIndustry.getDataFromInstance(a);

    }

    @Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        CampaignFleetAPI re = a.spawnFleet(route);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }

    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.shouldCancelRouteAfterDelayCheck(route);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }

    @Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        boolean re = a.shouldRepeat(route);
        CurrentIndustry.getDataFromInstance(a);
        return re;
    }

    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {
        MarketRetrofits_DefaltInstanceIndustry a = industryGroup.defaultInstance;
        //as intended
        CurrentIndustry.applyDataToInstance(a);
        a.reportAboutToBeDespawnedByRouteManager(route);
        CurrentIndustry.getDataFromInstance(a);
    }
}