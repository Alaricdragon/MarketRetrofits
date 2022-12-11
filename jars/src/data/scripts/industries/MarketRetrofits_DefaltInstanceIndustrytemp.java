package data.scripts.industries;

import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.*;
import com.fs.starfarer.api.campaign.listeners.FleetEventListener;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.fleets.RouteManager;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.Lists.MarketRetrofits_IndustryList;
import data.scripts.industries.Lists.MarketRetrofits_IndustryMasterList;

public class MarketRetrofits_DefaltInstanceIndustrytemp extends BaseIndustry {// implements MarketImmigrationModifier, FleetEventListener, RouteManager.RouteFleetSpawner{
    /*this is the class that an industry would extend, if and only if said industry wants to be the default industry for an industry type.*/
    public String ID;
    public float order;
    public MarketRetrofits_IndustryList industryGroup = null;
    Industry industry;

    public void applyIndustry(Industry industryT) {
        industry = industryT;
    }

    public Industry getIndustry() {
        return this.industry;
    }

    public MarketRetrofits_DefaltInstanceIndustrytemp(String name, float orderT) {
        ID = name;
        order = orderT;
    }

    //@Override
    public void applyToIndustry(String industry) {
        //apply to industry here.
        //MarketRetrofits_IndustryMasterList.setDefaltInstance(industry, this);//HERE this was not commited out
        industryGroup = MarketRetrofits_IndustryMasterList.getInstance(industry);
    }

    //this class requires every single function held in baseIndustry, as well as an an function to determon if its active or not.
    protected boolean Active = true;

    public boolean canImply(MarketAPI market) {
        return Active;
    }
    /*
    public void setMarket(MarketAPI newMarket){
        this.market = newMarket;
    }*/












    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        super.addAICoreSection(tooltip,mode);
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        super.addAICoreSection(tooltip,coreId,mode);
    }
    @Override
    protected void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        super.addAlphaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        super.addBetaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        super.addGammaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        super.addGroundDefensesImpactSection(tooltip,bonus,commodities);
    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        super.addImproveDesc(info,mode);
    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        super.addImprovedSection(mode,tooltip,expanded);
    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        super.addInstalledItemsSection(mode,tooltip,expanded);
    }
    @Override
    protected boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        return super.addNonAICoreInstalledItems(mode,tooltip,expanded);
    }
    @Override
    protected void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        super.addPostDemandSection(tooltip,hasDemand,mode);
    }
    @Override
    protected void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        super.addPostDescriptionSection(tooltip,mode);
    }
    @Override
    protected void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        super.addPostSupplySection(tooltip,hasSupply,mode);
    }
    @Override
    protected void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        super.addPostUpkeepSection(tooltip,mode);
    }
    @Override
    protected void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        super.addRightAfterDescriptionSection(tooltip,mode);
    }
    @Override
    protected void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        super.addStabilityPostDemandSection(tooltip,hasDemand,mode);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        return super.adjustCommodityDangerLevel(commodityId,level);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        return super.adjustItemDangerLevel(itemId,data,level);
    }
    @Override
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        return super.adjustMarineTokensToRaidItem(itemId,data,marineTokens);
    }
    @Override
    public void	advance(float amount){
        super.advance(amount);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        super.apply(withIncomeUpdate);
    }
    @Override
    protected void	applyAICoreModifiers(){
        super.applyAICoreModifiers();
    }
    @Override
    protected void	applyAICoreToIncomeAndUpkeep(){
        super.applyAICoreToIncomeAndUpkeep();
    }
    @Override
    protected void	applyAlphaCoreModifiers(){
        super.applyAlphaCoreModifiers();
    }
    @Override
    protected void	applyAlphaCoreSupplyAndDemandModifiers(){
        super.applyAlphaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyBetaCoreModifiers(){
        super.applyBetaCoreModifiers();
    }
    @Override
    protected void	applyBetaCoreSupplyAndDemandModifiers(){
        super.applyBetaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyDeficitToProduction(int index, Pair<String, Integer> deficit, java.lang.String... commodities){
        super.applyDeficitToProduction(index,deficit,commodities);
    }
    @Override
    protected void	applyGammaCoreModifiers(){
        super.applyGammaCoreModifiers();
    }
    @Override
    protected void	applyGammaCoreSupplyAndDemandModifiers(){
        super.applyGammaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyImproveModifiers(){
        super.applyImproveModifiers();
    }
    @Override
    protected void	applyIncomeAndUpkeep(float sizeOverride){
        super.applyIncomeAndUpkeep(sizeOverride);
    }
    @Override
    protected void	applyNoAICoreModifiers(){
        super.applyNoAICoreModifiers();
    }
    @Override
    protected void	buildingFinished(){
        super.buildingFinished();
    }
    @Override
    //public static void	buildNextInQueue(MarketAPI market)
    public boolean	canBeDisrupted(){
        return super.canBeDisrupted();
    }
    @Override
    public void	cancelUpgrade(){
        super.cancelUpgrade();
    }
    @Override
    public boolean	canDowngrade(){
        return super.canDowngrade();
    }
    @Override
    public boolean	canImprove(){
        return super.canImprove();
    }
    @Override
    protected boolean canImproveToIncreaseProduction(){
        return super.canImproveToIncreaseProduction();
    }
    @Override
    public boolean	canInstallAICores(){
        return super.canInstallAICores();
    }
    @Override
    public boolean	canShutDown(){
        return super.canShutDown();
    }
    @Override
    public boolean	canUpgrade(){
        return super.canUpgrade();
    }
    @Override
    public void	clearUnmodified(){
        super.clearUnmodified();
    }
    @Override
    protected BaseIndustry clone(){
        return super.clone();
    }
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        super.createTooltip(mode,tooltip,expanded);
    }
    @Override
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        super.demand(index,commodityId,quantity,desc);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity){
        super.demand(commodityId,quantity);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        super.demand(commodityId,quantity,desc);
    }
    @Override
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        super.demand(modId,commodityId,quantity,desc);
    }
    @Override
    protected void	disruptionFinished(){
        super.disruptionFinished();
    }
    @Override
    public void	doPostSaveRestore(){
        super.doPostSaveRestore();
    }
    @Override
    public void	doPreSaveCleanup(){
        super.doPreSaveCleanup();
    }
    @Override
    public void	downgrade(){
        super.downgrade();
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        super.finishBuildingOrUpgrading();
    }
    @Override
    public CargoAPI generateCargoForGatheringPoint(java.util.Random random){
        return super.generateCargoForGatheringPoint(random);
    }
    @Override
    public java.lang.String	getAICoreId(){
        return super.getAICoreId();
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        return super.getAllDeficit();
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        return super.getAllDeficit(commodityIds);
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        return super.getAllDemand();
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        return super.getAllSupply();
    }
    @Override
    protected int	getBaseStabilityMod(){
        return super.getBaseStabilityMod();
    }
    @Override
    public float	getBaseUpkeep(){
        return super.getBaseUpkeep();
    }
    @Override
    public float	getBuildCost(){
        return super.getBuildCost();
    }
    @Override
    public java.lang.Float	getBuildCostOverride(){
        return super.getBuildCostOverride();
    }
    @Override
    public java.lang.String	getBuildOrUpgradeDaysText(){
        return super.getBuildOrUpgradeDaysText();
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        return super.getBuildOrUpgradeProgress();
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        return super.getBuildOrUpgradeProgressText();
    }
    @Override
    public float	getBuildProgress(){
        return super.getBuildProgress();
    }
    @Override
    public float	getBuildTime(){
        return super.getBuildTime();
    }
    @Override
    public java.lang.String	getCanNotShutDownReason(){
        return super.getCanNotShutDownReason();
    }
    @Override
    protected CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        return super.getCargoForInteractionMode(mode);
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        return super.getCargoTitleForGatheringPoint();
    }
    @Override
    /*public static float	getCommodityEconUnitMult(float size){
        return super.getComm
    }*/
    public java.lang.String getCurrentImage() {
        return super.getCurrentImage();
    }

    @Override
    public java.lang.String getCurrentName() {
        return super.getCurrentName();
    }

    @Override
    protected float getDeficitMult(java.lang.String... commodities) {
        return super.getDeficitMult(commodities);
    }

    @Override
    /*public static java.lang.String	getDeficitText(java.lang.String commodityId){
        return super.getDeficitText();
    }*/
    public MutableCommodityQuantity getDemand(java.lang.String id) {
        return super.getDemand(id);
    }

    @Override
    public MutableStat getDemandReduction() {
        return super.getDemandReduction();
    }

    @Override
    public MutableStat getDemandReductionFromOther() {
        return super.getDemandReductionFromOther();
    }

    @Override
    protected java.lang.String getDescriptionOverride() {
        return super.getDescriptionOverride();
    }

    @Override
    public float getDisruptedDays() {
        return super.getDisruptedDays();
    }

    @Override
    public java.lang.String getDisruptedKey() {
        return super.getDisruptedKey();
    }
    /*//HERE
    @Override
    public java.lang.String	getId(){
        return super.getId();
    }*/

    @Override
    public float	getImproveBonusXP(){
        return super.getImproveBonusXP();
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        return super.getImproveDialogTitle();
    }
    @Override
    protected java.lang.String	getImprovementsDescForModifiers(){
        return super.getImprovementsDescForModifiers();
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        return super.getImproveMenuText();
    }
    @Override
    protected int	getImproveProductionBonus(){
        return super.getImproveProductionBonus();
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        return super.getImproveSoundId();
    }
    @Override
    public int	getImproveStoryPoints(){
        return super.getImproveStoryPoints();
    }
    @Override
    public MutableStat	getIncome(){
        return super.getIncome();
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        return super.getInstallableItems();
    }
    /*@Override//HERE
    public MarketAPI	getMarket(){
        return super.getMarket();
    }*/
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        return super.getMaxDeficit(commodityIds);
    }
    @Override
    protected java.lang.String	getModId(){
        return super.getModId();
    }
    @Override
    protected java.lang.String	getModId(int index){
        return super.getModId(index);
    }
    @Override
    public java.lang.String	getNameForModifier(){
        return super.getNameForModifier();
    }
    @Override
    public float	getPatherInterest(){
        return super.getPatherInterest();
    }
    @Override
    public float	getSizeMult(){
        return super.getSizeMult();
    }
    @Override
    /*public static float getSizeMult(float size){
        return super.getSizeMult(size);
    }*/
    public IndustrySpecAPI getSpec(){
        return super.getSpec();
    }
    @Override
    public SpecialItemData getSpecialItem(){
        return super.getSpecialItem();
    }
    @Override
    protected Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        return super.getStabilityAffectingDeficit();
    }
    @Override
    protected int	getStabilityPenalty(){
        return super.getStabilityPenalty();
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        return super.getSupply(id);
    }
    @Override
    public MutableStat	getSupplyBonus(){
        return super.getSupplyBonus();
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
        return super.getSupplyBonusFromOther();
    }
    @Override
    public float	getTooltipWidth(){
        return super.getTooltipWidth();
    }
    @Override
    public java.lang.String	getUnavailableReason(){
        return super.getUnavailableReason();
    }
    @Override
    public MutableStat	getUpkeep(){
        return super.getUpkeep();
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        return super.getVisibleInstalledItems();
    }
    @Override
    protected boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        return super.hasPostDemandSection(hasDemand,mode);
    }
    @Override
    public void	init(java.lang.String id, MarketAPI market){
        super.init(id,market);
    }
    @Override
    public void	initWithParams(java.util.List<java.lang.String> params){
        super.initWithParams(params);
    }

    @Override
    public void apply() {

    }

    @Override
    //Used when loading market from an economy .json file.
    protected boolean	isAICoreId(java.lang.String str){
        return super.isAICoreId(str);
    }
    @Override
    public boolean	isAvailableToBuild(){
        /*if(market == null){
            float a[] = {};
            a[1] = 2;
        }*/

        if (market.hasTag(Tags.MARKET_NO_INDUSTRIES_ALLOWED)) return false;
        return market.hasIndustry(Industries.POPULATION) && !getId().equals(Industries.POPULATION);
        //return super.isAvailableToBuild();
    }
    @Override
    public boolean	isBuilding(){
        return super.isBuilding();
    }
    @Override
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        return super.isDemandLegal(com);
    }
    @Override
    public boolean	isDisrupted(){
        return super.isDisrupted();
    }
    @Override
    public boolean	isFunctional(){
        return super.isFunctional();
    }
    @Override
    //Building and not upgrading.
    public boolean	isHidden(){
        return super.isHidden();
    }
    @Override
    public boolean	isImproved(){
        return super.isImproved();
    }
    @Override
    public boolean	isIndustry(){
        return super.isIndustry();
    }
    @Override
    public boolean	isOther(){
        return super.isOther();
    }
    @Override
    public boolean	isStructure(){
        return super.isStructure();
    }
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        return super.isSupplyLegal(com);
    }
    @Override
    public boolean	isTooltipExpandable(){
        return super.isTooltipExpandable();
    }
    @Override
    public boolean	isUpgrading(){
        return super.isUpgrading();
    }
    @Override
    //Upgrading, but not the initial building process.
    protected void	modifyStabilityWithBaseMod(){
        super.modifyStabilityWithBaseMod();
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        super.notifyBeingRemoved(mode,forUpgrade);
    }
    @Override
    public void	notifyColonyRenamed(){
        super.notifyColonyRenamed();
    }
    @Override
    protected void	notifyDisrupted(){
        super.notifyDisrupted();
    }
    @Override
    protected java.lang.Object	readResolve(){
        return super.readResolve();
    }
    @Override
    public void	reapply(){
        super.reapply();
    }
    @Override
    //Calls unapply() and then reapply().
    protected void	sendBuildOrUpgradeMessage(){
        super.sendBuildOrUpgradeMessage();
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        super.setAICoreId(aiCoreId);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        super.setBuildCostOverride(buildCostOverride);
    }
    @Override
    public void	setBuildProgress(float buildProgress){
        super.setBuildProgress(buildProgress);
    }
    @Override
    public void	setDisrupted(float days){
        super.setDisrupted(days);
    }
    @Override
    public void	setDisrupted(float days, boolean useMax){
        super.setDisrupted(days,useMax);
    }
    @Override
    public void	setHidden(boolean hidden){
        super.setHidden(hidden);
    }
    @Override
    public void	setImproved(boolean improved){
        super.setImproved(improved);
    }
    @Override
    public void	setSpecialItem(SpecialItemData special){
        super.setSpecialItem(special);
    }
    @Override
    public boolean	showShutDown(){
        return super.showShutDown();
    }
    @Override
    public boolean	showWhenUnavailable(){
        return super.showWhenUnavailable();
    }
    @Override
    public void	startBuilding(){
        super.startBuilding();
    }
    @Override
    public void	startUpgrading(){
        super.startUpgrading();
    }
    @Override
    public void	supply(int index, String commodityId, int quantity, String desc){
        super.supply(index,commodityId,quantity,desc);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity){
        super.supply(commodityId,quantity);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        super.supply(commodityId,quantity,desc);
    }
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        super.supply(modId,commodityId,quantity,desc);
    }
    @Override
    public void	unapply(){
        super.unapply();
    }
    @Override
    protected void	unmodifyStabilityWithBaseMod(){
        super.unmodifyStabilityWithBaseMod();
    }
    @Override
    protected void	updateAICoreToSupplyAndDemandModifiers(){
        super.updateAICoreToSupplyAndDemandModifiers();
    }
    @Override
    protected void	updateImprovementSupplyAndDemandModifiers(){
        super.updateImprovementSupplyAndDemandModifiers();
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        super.updateIncomeAndUpkeep();
    }
    @Override
    protected void	updateSupplyAndDemandModifiers(){
        super.updateSupplyAndDemandModifiers();
    }
    @Override
    protected void	upgradeFinished(Industry previous){
        super.upgradeFinished(previous);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        return super.wantsToUseSpecialItem(data);
    }
    @Override
    //Return false if already using one of that type, unless the other one is better.
    protected java.lang.Object	writeReplace() {
        return super.writeReplace();
    }


    //HERE
    //overwriten this data or something i dont remember lol
    //@Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
    }
    //@Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
    }

    //@Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {

    }

    //@Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        return null;
    }

    //@Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        return false;
    }

    //@Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        return false;
    }

    //@Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {

    }
}