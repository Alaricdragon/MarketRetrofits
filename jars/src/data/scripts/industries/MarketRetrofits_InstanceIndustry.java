package data.scripts.industries;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.*;
import com.fs.starfarer.api.campaign.listeners.FleetEventListener;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.ResourceDepositsCondition;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.fleets.RouteManager;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import com.fs.starfarer.campaign.econ.Market;
import data.scripts.industries.Lists.MarketRetrofits_IndustryList;
import data.scripts.industries.Lists.MarketRetrofits_IndustryMasterList;

import java.awt.*;

public class MarketRetrofits_InstanceIndustry extends MarketRetrofits_DefaltInstanceIndustry{
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
        industryGroup.defaultInstance.apply();
        //industryGroup.defaultInstance.apply();
        //get whatever industry should be used and use its methods here
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        industryGroup.defaultInstance.addAICoreSection(tooltip,mode);
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        industryGroup.defaultInstance.addAICoreSection(tooltip,coreId,mode);
    }
    @Override
    protected void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        industryGroup.defaultInstance.addAlphaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        industryGroup.defaultInstance.addBetaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        industryGroup.defaultInstance.addGammaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        industryGroup.defaultInstance.addGroundDefensesImpactSection(tooltip,bonus,commodities);
    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        industryGroup.defaultInstance.addImproveDesc(info,mode);
    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        industryGroup.defaultInstance.addImprovedSection(mode,tooltip,expanded);
    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        industryGroup.defaultInstance.addInstalledItemsSection(mode,tooltip,expanded);
    }
    @Override
    protected boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        return industryGroup.defaultInstance.addNonAICoreInstalledItems(mode,tooltip,expanded);
    }
    @Override
    protected void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        industryGroup.defaultInstance.addPostDemandSection(tooltip,hasDemand,mode);
    }
    @Override
    protected void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        industryGroup.defaultInstance.addPostDescriptionSection(tooltip,mode);
    }
    @Override
    protected void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        industryGroup.defaultInstance.addPostSupplySection(tooltip,hasSupply,mode);
    }
    @Override
    protected void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        industryGroup.defaultInstance.addPostUpkeepSection(tooltip,mode);
    }
    @Override
    protected void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        industryGroup.defaultInstance.addRightAfterDescriptionSection(tooltip,mode);
    }
    @Override
    protected void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        industryGroup.defaultInstance.addStabilityPostDemandSection(tooltip,hasDemand,mode);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        return industryGroup.defaultInstance.adjustCommodityDangerLevel(commodityId,level);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        return industryGroup.defaultInstance.adjustItemDangerLevel(itemId,data,level);
    }
    @Override
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        return industryGroup.defaultInstance.adjustMarineTokensToRaidItem(itemId,data,marineTokens);
    }
    @Override
    public void	advance(float amount){
        industryGroup.defaultInstance.advance(amount);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        industryGroup.defaultInstance.apply(withIncomeUpdate);
    }
    @Override
    protected void	applyAICoreModifiers(){
        industryGroup.defaultInstance.applyAICoreModifiers();
    }
    @Override
    protected void	applyAICoreToIncomeAndUpkeep(){
        industryGroup.defaultInstance.applyAICoreToIncomeAndUpkeep();
    }
    @Override
    protected void	applyAlphaCoreModifiers(){
        industryGroup.defaultInstance.applyAlphaCoreModifiers();
    }
    @Override
    protected void	applyAlphaCoreSupplyAndDemandModifiers(){
        industryGroup.defaultInstance.applyAlphaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyBetaCoreModifiers(){
        industryGroup.defaultInstance.applyBetaCoreModifiers();
    }
    @Override
    protected void	applyBetaCoreSupplyAndDemandModifiers(){
        industryGroup.defaultInstance.applyBetaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        industryGroup.defaultInstance.applyDeficitToProduction(index,deficit,commodities);
    }
    @Override
    protected void	applyGammaCoreModifiers(){
        industryGroup.defaultInstance.applyGammaCoreModifiers();
    }
    @Override
    protected void	applyGammaCoreSupplyAndDemandModifiers(){
        industryGroup.defaultInstance.applyGammaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyImproveModifiers(){
        industryGroup.defaultInstance.applyImproveModifiers();
    }
    @Override
    protected void	applyIncomeAndUpkeep(float sizeOverride){
        industryGroup.defaultInstance.applyIncomeAndUpkeep(sizeOverride);
    }
    @Override
    protected void	applyNoAICoreModifiers(){
        industryGroup.defaultInstance.applyNoAICoreModifiers();
    }
    @Override
    protected void	buildingFinished(){
        industryGroup.defaultInstance.buildingFinished();
    }
    @Override
    //public static void	buildNextInQueue(MarketAPI market)
    public boolean	canBeDisrupted(){
        return industryGroup.defaultInstance.canBeDisrupted();
    }
    @Override
    public void	cancelUpgrade(){
        industryGroup.defaultInstance.cancelUpgrade();
    }
    @Override
    public boolean	canDowngrade(){
        return industryGroup.defaultInstance.canDowngrade();
    }
    @Override
    public boolean	canImprove(){
        return industryGroup.defaultInstance.canImprove();
    }
    @Override
    protected boolean canImproveToIncreaseProduction(){
        return industryGroup.defaultInstance.canImproveToIncreaseProduction();
    }
    @Override
    public boolean	canInstallAICores(){
        return industryGroup.defaultInstance.canInstallAICores();
    }
    @Override
    public boolean	canShutDown(){
        return industryGroup.defaultInstance.canShutDown();
    }
    @Override
    public boolean	canUpgrade(){
        return industryGroup.defaultInstance.canUpgrade();
    }
    @Override
    public void	clearUnmodified(){
        industryGroup.defaultInstance.clearUnmodified();
    }
    /*@Override//HERE
    protected BaseIndustry clone(){
        return industryGroup.defaultInstance.clone();
    }*/
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        industryGroup.defaultInstance.createTooltip(mode,tooltip,expanded);
    }
    @Override
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        industryGroup.defaultInstance.demand(index,commodityId,quantity,desc);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity){
        industryGroup.defaultInstance.demand(commodityId,quantity);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        industryGroup.defaultInstance.demand(commodityId,quantity,desc);
    }
    @Override
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        industryGroup.defaultInstance.demand(modId,commodityId,quantity,desc);
    }
    @Override
    protected void	disruptionFinished(){
        industryGroup.defaultInstance.disruptionFinished();
    }
    @Override
    public void	doPostSaveRestore(){
        industryGroup.defaultInstance.doPostSaveRestore();
    }
    @Override
    public void	doPreSaveCleanup(){
        industryGroup.defaultInstance.doPreSaveCleanup();
    }
    @Override
    public void	downgrade(){
        industryGroup.defaultInstance.downgrade();
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        industryGroup.defaultInstance.finishBuildingOrUpgrading();
    }
    @Override
    public CargoAPI generateCargoForGatheringPoint(java.util.Random random){
        return industryGroup.defaultInstance.generateCargoForGatheringPoint(random);
    }
    @Override
    public java.lang.String	getAICoreId(){
        return industryGroup.defaultInstance.getAICoreId();
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        return industryGroup.defaultInstance.getAllDeficit();
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        return industryGroup.defaultInstance.getAllDeficit(commodityIds);
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        return industryGroup.defaultInstance.getAllDemand();
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        return industryGroup.defaultInstance.getAllSupply();
    }
    @Override
    protected int	getBaseStabilityMod(){
        return industryGroup.defaultInstance.getBaseStabilityMod();
    }
    @Override
    public float	getBaseUpkeep(){
        return industryGroup.defaultInstance.getBaseUpkeep();
    }
    @Override
    public float	getBuildCost(){
        return industryGroup.defaultInstance.getBuildCost();
    }
    @Override
    public java.lang.Float	getBuildCostOverride(){
        return industryGroup.defaultInstance.getBuildCostOverride();
    }
    @Override
    public java.lang.String	getBuildOrUpgradeDaysText(){
        return industryGroup.defaultInstance.getBuildOrUpgradeDaysText();
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        return industryGroup.defaultInstance.getBuildOrUpgradeProgress();
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        return industryGroup.defaultInstance.getBuildOrUpgradeProgressText();
    }
    @Override
    public float	getBuildProgress(){
        return industryGroup.defaultInstance.getBuildProgress();
    }
    @Override
    public float	getBuildTime(){
        return industryGroup.defaultInstance.getBuildTime();
    }
    @Override
    public java.lang.String	getCanNotShutDownReason(){
        return industryGroup.defaultInstance.getCanNotShutDownReason();
    }
    @Override
    protected CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        return industryGroup.defaultInstance.getCargoForInteractionMode(mode);
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        return industryGroup.defaultInstance.getCargoTitleForGatheringPoint();
    }
    @Override
    /*public static float	getCommodityEconUnitMult(float size){
        return industryGroup.defaultInstance.getComm
    }*/
    public java.lang.String	getCurrentImage(){
        return industryGroup.defaultInstance.getCurrentImage();
    }
    @Override
    public java.lang.String	getCurrentName(){
        return industryGroup.defaultInstance.getCurrentName();
    }
    @Override
    protected float	getDeficitMult(java.lang.String... commodities){
        return industryGroup.defaultInstance.getDeficitMult(commodities);
    }
    @Override
    /*public static java.lang.String	getDeficitText(java.lang.String commodityId){
        return industryGroup.defaultInstance.getDeficitText();
    }*/
    public MutableCommodityQuantity	getDemand(java.lang.String id){
        return industryGroup.defaultInstance.getDemand(id);
    }
    @Override
    public MutableStat getDemandReduction(){
        return industryGroup.defaultInstance.getDemandReduction();
    }
    @Override
    public MutableStat	getDemandReductionFromOther(){
        return industryGroup.defaultInstance.getDemandReductionFromOther();
    }
    @Override
    protected java.lang.String	getDescriptionOverride(){
        return industryGroup.defaultInstance.getDescriptionOverride();
    }
    @Override
    public float	getDisruptedDays(){
        return industryGroup.defaultInstance.getDisruptedDays();
    }
    @Override
    public java.lang.String	getDisruptedKey(){
        return industryGroup.defaultInstance.getDisruptedKey();
    }
    /*@Override//HERE
    public java.lang.String	getId(){
        return industryGroup.defaultInstance.getId();
    }*/
    @Override
    public float	getImproveBonusXP(){
        return industryGroup.defaultInstance.getImproveBonusXP();
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        return industryGroup.defaultInstance.getImproveDialogTitle();
    }
    @Override
    protected java.lang.String	getImprovementsDescForModifiers(){
        return industryGroup.defaultInstance.getImprovementsDescForModifiers();
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        return industryGroup.defaultInstance.getImproveMenuText();
    }
    @Override
    protected int	getImproveProductionBonus(){
        return industryGroup.defaultInstance.getImproveProductionBonus();
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        return industryGroup.defaultInstance.getImproveSoundId();
    }
    @Override
    public int	getImproveStoryPoints(){
        return industryGroup.defaultInstance.getImproveStoryPoints();
    }
    @Override
    public MutableStat	getIncome(){
        return industryGroup.defaultInstance.getIncome();
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        return industryGroup.defaultInstance.getInstallableItems();
    }
    /*//HERE
    @Override
    public MarketAPI	getMarket(){
        return industryGroup.defaultInstance.getMarket();
    }*/
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        return industryGroup.defaultInstance.getMaxDeficit(commodityIds);
    }
    @Override
    protected java.lang.String	getModId(){
        return industryGroup.defaultInstance.getModId();
    }
    @Override
    protected java.lang.String	getModId(int index){
        return industryGroup.defaultInstance.getModId(index);
    }
    @Override
    public java.lang.String	getNameForModifier(){
        return industryGroup.defaultInstance.getNameForModifier();
    }
    @Override
    public float	getPatherInterest(){
        return industryGroup.defaultInstance.getPatherInterest();
    }
    @Override
    public float	getSizeMult(){
        return industryGroup.defaultInstance.getSizeMult();
    }
    @Override
    /*public static float getSizeMult(float size){
        return industryGroup.defaultInstance.getSizeMult(size);
    }*/
    public IndustrySpecAPI getSpec(){
        return industryGroup.defaultInstance.getSpec();
    }
    @Override
    public SpecialItemData	getSpecialItem(){
        return industryGroup.defaultInstance.getSpecialItem();
    }
    @Override
    protected Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        return industryGroup.defaultInstance.getStabilityAffectingDeficit();
    }
    @Override
    protected int	getStabilityPenalty(){
        return industryGroup.defaultInstance.getStabilityPenalty();
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        return industryGroup.defaultInstance.getSupply(id);
    }
    @Override
    public MutableStat	getSupplyBonus(){
        return industryGroup.defaultInstance.getSupplyBonus();
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
        return industryGroup.defaultInstance.getSupplyBonusFromOther();
    }
    @Override
    public float	getTooltipWidth(){
        return industryGroup.defaultInstance.getTooltipWidth();
    }
    @Override
    public java.lang.String	getUnavailableReason(){
        return industryGroup.defaultInstance.getUnavailableReason();
    }
    @Override
    public MutableStat	getUpkeep(){
        return industryGroup.defaultInstance.getUpkeep();
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        return industryGroup.defaultInstance.getVisibleInstalledItems();
    }
    @Override
    protected boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        return hasPostDemandSection(hasDemand,mode);
    }
    @Override
    public void	init(java.lang.String id, MarketAPI market){
        industryGroup.defaultInstance.init(id,market);
    }
    @Override
    public void	initWithParams(java.util.List<java.lang.String> params){
        industryGroup.defaultInstance.initWithParams(params);
    }
    @Override
    //Used when loading market from an economy .json file.
    protected boolean	isAICoreId(java.lang.String str){
        return industryGroup.defaultInstance.isAICoreId(str);
    }
    @Override
    public boolean	isAvailableToBuild(){
        return industryGroup.defaultInstance.isAvailableToBuild();
    }
    @Override
    public boolean	isBuilding(){
        return industryGroup.defaultInstance.isBuilding();
    }
    @Override
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        return industryGroup.defaultInstance.isDemandLegal(com);
    }
    @Override
    public boolean	isDisrupted(){
        return industryGroup.defaultInstance.isDisrupted();
    }
    @Override
    public boolean	isFunctional(){
        return industryGroup.defaultInstance.isFunctional();
    }
    @Override
    //Building and not upgrading.
    public boolean	isHidden(){
        return industryGroup.defaultInstance.isHidden();
    }
    @Override
    public boolean	isImproved(){
        return industryGroup.defaultInstance.isImproved();
    }
    @Override
    public boolean	isIndustry(){
        return industryGroup.defaultInstance.isIndustry();
    }
    @Override
    public boolean	isOther(){
        return industryGroup.defaultInstance.isOther();
    }
    @Override
    public boolean	isStructure(){
        return industryGroup.defaultInstance.isStructure();
    }
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        return industryGroup.defaultInstance.isSupplyLegal(com);
    }
    @Override
    public boolean	isTooltipExpandable(){
        return industryGroup.defaultInstance.isTooltipExpandable();
    }
    @Override
    public boolean	isUpgrading(){
        return industryGroup.defaultInstance.isUpgrading();
    }
    @Override
    //Upgrading, but not the initial building process.
    protected void	modifyStabilityWithBaseMod(){
        industryGroup.defaultInstance.modifyStabilityWithBaseMod();
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        industryGroup.defaultInstance.notifyBeingRemoved(mode,forUpgrade);
    }
    @Override
    public void	notifyColonyRenamed(){
        industryGroup.defaultInstance.notifyColonyRenamed();
    }
    @Override
    protected void	notifyDisrupted(){
        industryGroup.defaultInstance.notifyDisrupted();
    }
    @Override
    protected java.lang.Object	readResolve(){
        return industryGroup.defaultInstance.readResolve();
    }
    @Override
    public void	reapply(){
        industryGroup.defaultInstance.reapply();
    }
    @Override
    //Calls unapply() and then reapply().
    protected void	sendBuildOrUpgradeMessage(){
        industryGroup.defaultInstance.sendBuildOrUpgradeMessage();
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        industryGroup.defaultInstance.setAICoreId(aiCoreId);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        industryGroup.defaultInstance.setBuildCostOverride(buildCostOverride);
    }
    @Override
    public void	setBuildProgress(float buildProgress){
        industryGroup.defaultInstance.setBuildProgress(buildProgress);
    }
    @Override
    public void	setDisrupted(float days){
        industryGroup.defaultInstance.setDisrupted(days);
    }
    @Override
    public void	setDisrupted(float days, boolean useMax){
        industryGroup.defaultInstance.setDisrupted(days,useMax);
    }
    @Override
    public void	setHidden(boolean hidden){
        industryGroup.defaultInstance.setHidden(hidden);
    }
    @Override
    public void	setImproved(boolean improved){
        industryGroup.defaultInstance.setImproved(improved);
    }
    @Override
    public void	setSpecialItem(SpecialItemData special){
        industryGroup.defaultInstance.setSpecialItem(special);
    }
    @Override
    public boolean	showShutDown(){
        return industryGroup.defaultInstance.showShutDown();
    }
    @Override
    public boolean	showWhenUnavailable(){
        return industryGroup.defaultInstance.showWhenUnavailable();
    }
    @Override
    public void	startBuilding(){
        industryGroup.defaultInstance.startBuilding();
    }
    @Override
    public void	startUpgrading(){
        industryGroup.defaultInstance.startUpgrading();
    }
    @Override
    public void	supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        industryGroup.defaultInstance.supply(index,commodityId,quantity,desc);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity){
        industryGroup.defaultInstance.supply(commodityId,quantity);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        industryGroup.defaultInstance.supply(commodityId,quantity,desc);
    }
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        industryGroup.defaultInstance.supply(modId,commodityId,quantity,desc);
    }
    @Override
    public void	unapply(){
        industryGroup.defaultInstance.unapply();
    }
    @Override
    protected void	unmodifyStabilityWithBaseMod(){
        industryGroup.defaultInstance.unmodifyStabilityWithBaseMod();
    }
    @Override
    protected void	updateAICoreToSupplyAndDemandModifiers(){
        industryGroup.defaultInstance.updateAICoreToSupplyAndDemandModifiers();
    }
    @Override
    protected void	updateImprovementSupplyAndDemandModifiers(){
        industryGroup.defaultInstance.updateImprovementSupplyAndDemandModifiers();
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        industryGroup.defaultInstance.updateIncomeAndUpkeep();
    }
    @Override
    protected void	updateSupplyAndDemandModifiers(){
        industryGroup.defaultInstance.updateSupplyAndDemandModifiers();
    }
    @Override
    protected void	upgradeFinished(Industry previous){
        industryGroup.defaultInstance.upgradeFinished(previous);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        return industryGroup.defaultInstance.wantsToUseSpecialItem(data);
    }
    @Override
    //Return false if already using one of that type, unless the other one is better.
    protected java.lang.Object	writeReplace() {
        return industryGroup.defaultInstance.writeReplace();
    }

    //HERE implemented functions
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        industryGroup.defaultInstance.modifyIncoming(market,incoming);
    }

    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        industryGroup.defaultInstance.reportFleetDespawnedToListener(fleet,reason,param);
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {
        industryGroup.defaultInstance.reportBattleOccurred(fleet,primaryWinner,battle);
    }

    @Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        return industryGroup.defaultInstance.spawnFleet(route);
    }

    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        return industryGroup.defaultInstance.shouldCancelRouteAfterDelayCheck(route);
    }

    @Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        return industryGroup.defaultInstance.shouldRepeat(route);
    }

    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {
        industryGroup.defaultInstance.reportAboutToBeDespawnedByRouteManager(route);
    }

}