package data.scripts.industries;

import com.fs.starfarer.api.campaign.CargoAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.*;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Pair;

import java.util.List;
import java.util.Random;

public class MarketRetrofit_BaseIndustry extends BaseIndustry implements MarketImmigrationModifier{
    protected String Industry = "";
    @Override
    public void apply() {
        //super.apply();
        //get whatever industry should be used and use its methods here
    }

    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
    }
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        super.addAICoreSection(tooltip,mode);
    }
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        super.addAICoreSection(tooltip,coreId,mode);
    }
    protected void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        super.addAlphaCoreDescription(tooltip,mode);
    }
    protected void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        super.addBetaCoreDescription(tooltip,mode);
    }
    protected void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        super.addGammaCoreDescription(tooltip,mode);
    }
    protected void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        super.addGroundDefensesImpactSection(tooltip,bonus,commodities);
    }
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        super.addImproveDesc(info,mode);
    }
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        super.addImprovedSection(mode,tooltip,expanded);
    }
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        super.addInstalledItemsSection(mode,tooltip,expanded);
    }
    @Override
    protected boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        return super.addNonAICoreInstalledItems(mode,tooltip,expanded);
    }
    protected void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        super.addPostDemandSection(tooltip,hasDemand,mode);
    }
    protected void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        super.addPostDescriptionSection(tooltip,mode);
    }
    protected void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        super.addPostSupplySection(tooltip,hasSupply,mode);
    }
    protected void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        super.addPostUpkeepSection(tooltip,mode);
    }
    protected void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        super.addRightAfterDescriptionSection(tooltip,mode);
    }
    protected void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        super.addStabilityPostDemandSection(tooltip,hasDemand,mode);
    }
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        return super.adjustCommodityDangerLevel(commodityId,level);
    }
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        return super.adjustItemDangerLevel(itemId,data,level);
    }
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        return super.adjustMarineTokensToRaidItem(itemId,data,marineTokens);
    }
    public void	advance(float amount){
        super.advance(amount);
    }
    public void	apply(boolean withIncomeUpdate){
        super.apply(withIncomeUpdate);
    }
    protected void	applyAICoreModifiers(){
        super.applyAICoreModifiers();
    }
    protected void	applyAICoreToIncomeAndUpkeep(){
        super.applyAICoreToIncomeAndUpkeep();
    }
    protected void	applyAlphaCoreModifiers(){
        super.applyAlphaCoreModifiers();
    }
    protected void	applyAlphaCoreSupplyAndDemandModifiers(){
        super.applyAlphaCoreSupplyAndDemandModifiers();
    }
    protected void	applyBetaCoreModifiers(){
        super.applyBetaCoreModifiers();
    }
    protected void	applyBetaCoreSupplyAndDemandModifiers(){
        super.applyBetaCoreSupplyAndDemandModifiers();
    }
    protected void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        super.applyDeficitToProduction(index,deficit,commodities);
    }
    protected void	applyGammaCoreModifiers(){
        super.applyGammaCoreModifiers();
    }
    protected void	applyGammaCoreSupplyAndDemandModifiers(){
        super.applyGammaCoreSupplyAndDemandModifiers();
    }
    protected void	applyImproveModifiers(){
        super.applyImproveModifiers();
    }
    protected void	applyIncomeAndUpkeep(float sizeOverride){
        super.applyIncomeAndUpkeep(sizeOverride);
    }
    protected void	applyNoAICoreModifiers(){
        super.applyNoAICoreModifiers();
    }
    protected void	buildingFinished(){
        super.buildingFinished();
    }
    //public static void	buildNextInQueue(MarketAPI market)
    public boolean	canBeDisrupted(){
        return super.canBeDisrupted();
    }
    public void	cancelUpgrade(){
        super.cancelUpgrade();
    }
    @Override
    public boolean	canDowngrade(){
        return super.canDowngrade();
    }
    public boolean	canImprove(){
        return super.canImprove();
    }
    protected boolean canImproveToIncreaseProduction(){
        return super.canImproveToIncreaseProduction();
    }
    public boolean	canInstallAICores(){
        return super.canInstallAICores();
    }
    public boolean	canShutDown(){
        return super.canShutDown();
    }
    public boolean	canUpgrade(){
        return super.canUpgrade();
    }
    public void	clearUnmodified(){
        super.clearUnmodified();
    }
    protected BaseIndustry	clone(){
        return super.clone();
    }
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        super.createTooltip(mode,tooltip,expanded);
    }
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        super.demand(index,commodityId,quantity,desc);
    }
    public void	demand(java.lang.String commodityId, int quantity){
        super.demand(commodityId,quantity);
    }
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        super.demand(commodityId,quantity,desc);
    }
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        super.demand(modId,commodityId,quantity,desc);
    }
    protected void	disruptionFinished(){
        super.disruptionFinished();
    }
    public void	doPostSaveRestore(){
        super.doPostSaveRestore();
    }
    public void	doPreSaveCleanup(){
        super.doPreSaveCleanup();
    }
    public void	downgrade(){
        super.downgrade();
    }
    public void	finishBuildingOrUpgrading(){
        super.finishBuildingOrUpgrading();
    }
    public CargoAPI	generateCargoForGatheringPoint(java.util.Random random){
        return super.generateCargoForGatheringPoint(random);
    }
    public java.lang.String	getAICoreId(){
        return super.getAICoreId();
    }
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        return super.getAllDeficit();
    }
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        return super.getAllDeficit(commodityIds);
    }
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        return super.getAllDemand();
    }
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        return super.getAllSupply();
    }
    protected int	getBaseStabilityMod(){
        return super.getBaseStabilityMod();
    }
    public float	getBaseUpkeep(){
        return super.getBaseUpkeep();
    }
    public float	getBuildCost(){
        return super.getBuildCost();
    }
    public java.lang.Float	getBuildCostOverride(){
        return super.getBuildCostOverride();
    }
    public java.lang.String	getBuildOrUpgradeDaysText(){
        return super.getBuildOrUpgradeDaysText();
    }
    public float	getBuildOrUpgradeProgress(){
        return super.getBuildOrUpgradeProgress();
    }
    public java.lang.String	getBuildOrUpgradeProgressText(){
        return super.getBuildOrUpgradeProgressText();
    }
    public float	getBuildProgress(){
        return super.getBuildProgress();
    }
    public float	getBuildTime(){
        return super.getBuildTime();
    }
    public java.lang.String	getCanNotShutDownReason(){
        return super.getCanNotShutDownReason();
    }
    protected CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        return super.getCargoForInteractionMode(mode);
    }
    public java.lang.String	getCargoTitleForGatheringPoint(){
        return super.getCargoTitleForGatheringPoint();
    }
    /*public static float	getCommodityEconUnitMult(float size){
        return super.getComm
    }*/
    public java.lang.String	getCurrentImage(){
        return super.getCurrentImage();
    }
    public java.lang.String	getCurrentName(){
        return super.getCurrentName();
    }
    protected float	getDeficitMult(java.lang.String... commodities){
        return super.getDeficitMult(commodities);
    }
    /*public static java.lang.String	getDeficitText(java.lang.String commodityId){
        return super.getDeficitText();
    }*/
    public MutableCommodityQuantity	getDemand(java.lang.String id){
        return super.getDemand(id);
    }
    public MutableStat	getDemandReduction(){
        return super.getDemandReduction();
    }
    public MutableStat	getDemandReductionFromOther(){
        return super.getDemandReductionFromOther();
    }
    protected java.lang.String	getDescriptionOverride(){
        return super.getDescriptionOverride();
    }
    public float	getDisruptedDays(){
        return super.getDisruptedDays();
    }
    public java.lang.String	getDisruptedKey(){
        return super.getDisruptedKey();
    }
    public java.lang.String	getId(){
        return super.getId();
    }
    public float	getImproveBonusXP(){
        return super.getImproveBonusXP();
    }
    public java.lang.String	getImproveDialogTitle(){
        return super.getImproveDialogTitle();
    }
    protected java.lang.String	getImprovementsDescForModifiers(){
        return super.getImprovementsDescForModifiers();
    }
    public java.lang.String	getImproveMenuText(){
        return super.getImproveMenuText();
    }
    protected int	getImproveProductionBonus(){
        return super.getImproveProductionBonus();
    }
    public java.lang.String	getImproveSoundId(){
        return super.getImproveSoundId();
    }
    public int	getImproveStoryPoints(){
        return super.getImproveStoryPoints();
    }
    public MutableStat	getIncome(){
        return super.getIncome();
    }
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        return super.getInstallableItems();
    }
    public MarketAPI	getMarket(){
        return super.getMarket();
    }
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        return super.getMaxDeficit(commodityIds);
    }
    protected java.lang.String	getModId(){
        return super.getModId();
    }
    protected java.lang.String	getModId(int index){
        return super.getModId(index);
    }
    public java.lang.String	getNameForModifier(){
        return super.getNameForModifier();
    }
    public float	getPatherInterest(){
        return super.getPatherInterest();
    }
    public float	getSizeMult(){
        return super.getSizeMult();
    }
    /*public static float getSizeMult(float size){
        return super.getSizeMult(size);
    }*/
    public IndustrySpecAPI	getSpec(){
        return super.getSpec();
    }
    public SpecialItemData	getSpecialItem(){
        return super.getSpecialItem();
    }
    protected Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        return super.getStabilityAffectingDeficit();
    }
    protected int	getStabilityPenalty(){
        return super.getStabilityPenalty();
    }
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        return super.getSupply(id);
    }
    public MutableStat	getSupplyBonus(){
        return super.getSupplyBonus();
    }
    public MutableStat	getSupplyBonusFromOther() {
        return super.getSupplyBonusFromOther();
    }
    public float	getTooltipWidth(){
        return super.getTooltipWidth();
    }
    public java.lang.String	getUnavailableReason(){
        return super.getUnavailableReason();
    }
    public MutableStat	getUpkeep(){
        return super.getUpkeep();
    }
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        return super.getVisibleInstalledItems();
    }
    protected boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        return hasPostDemandSection(hasDemand,mode);
    }
    public void	init(java.lang.String id, MarketAPI market){
        super.init(id,market);
    }
    public void	initWithParams(java.util.List<java.lang.String> params){
        super.initWithParams(params);
    }
    //Used when loading market from an economy .json file.
    protected boolean	isAICoreId(java.lang.String str){
        return super.isAICoreId(str);
    }
    public boolean	isAvailableToBuild(){
        return super.isAvailableToBuild();
    }
    public boolean	isBuilding(){
        return super.isBuilding();
    }
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        return super.isDemandLegal(com);
    }
    public boolean	isDisrupted(){
        return super.isDisrupted();
    }
    public boolean	isFunctional(){
        return super.isFunctional();
    }
    //Building and not upgrading.
    public boolean	isHidden(){
        return super.isHidden();
    }
    public boolean	isImproved(){
        return super.isImproved();
    }
    public boolean	isIndustry(){
        return super.isIndustry();
    }
    public boolean	isOther(){
        return super.isOther();
    }
    public boolean	isStructure(){
        return super.isStructure();
    }
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        return super.isSupplyLegal(com);
    }
    public boolean	isTooltipExpandable(){
        return super.isTooltipExpandable();
    }
    public boolean	isUpgrading(){
        return super.isUpgrading();
    }
    //Upgrading, but not the initial building process.
    protected void	modifyStabilityWithBaseMod(){
        super.modifyStabilityWithBaseMod();
    }
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        super.notifyBeingRemoved(mode,forUpgrade);
    }
    public void	notifyColonyRenamed(){
        super.notifyColonyRenamed();
    }
    protected void	notifyDisrupted(){
        super.notifyDisrupted();
    }
    protected java.lang.Object	readResolve(){
        return super.readResolve();
    }
    public void	reapply(){
        super.reapply();
    }
    //Calls unapply() and then reapply().
    protected void	sendBuildOrUpgradeMessage(){
        super.sendBuildOrUpgradeMessage();
    }
    public void	setAICoreId(java.lang.String aiCoreId){
        super.setAICoreId(aiCoreId);
    }
    public void	setBuildCostOverride(float buildCostOverride){
        super.setBuildCostOverride(buildCostOverride);
    }
    public void	setBuildProgress(float buildProgress){
        super.setBuildProgress(buildProgress);
    }
    public void	setDisrupted(float days){
        super.setDisrupted(days);
    }
    public void	setDisrupted(float days, boolean useMax){
        super.setDisrupted(days,useMax);
    }
    public void	setHidden(boolean hidden){
        super.setHidden(hidden);
    }
    public void	setImproved(boolean improved){
        super.setImproved(improved);
    }
    public void	setSpecialItem(SpecialItemData special){
        super.setSpecialItem(special);
    }
    public boolean	showShutDown(){
        return super.showShutDown();
    }
    public boolean	showWhenUnavailable(){
        return super.showWhenUnavailable();
    }
    public void	startBuilding(){
        super.startBuilding();
    }
    public void	startUpgrading(){
        super.startUpgrading();
    }
    public void	supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        super.supply(index,commodityId,quantity,desc);
    }
    public void	supply(java.lang.String commodityId, int quantity){
        super.supply(commodityId,quantity);
    }
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        super.supply(commodityId,quantity,desc);
    }
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        super.supply(modId,commodityId,quantity,desc);
    }
    public void	unapply(){
        super.unapply();
    }
    protected void	unmodifyStabilityWithBaseMod(){
        super.unmodifyStabilityWithBaseMod();
    }
    protected void	updateAICoreToSupplyAndDemandModifiers(){
        super.updateAICoreToSupplyAndDemandModifiers();
    }
    protected void	updateImprovementSupplyAndDemandModifiers(){
        super.updateImprovementSupplyAndDemandModifiers();
    }
    public void	updateIncomeAndUpkeep(){
        super.updateIncomeAndUpkeep();
    }
    protected void	updateSupplyAndDemandModifiers(){
        super.updateSupplyAndDemandModifiers();
    }
    protected void	upgradeFinished(Industry previous){
        super.upgradeFinished(previous);
    }
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        return super.wantsToUseSpecialItem(data);
    }
    //Return false if already using one of that type, unless the other one is better.
    protected java.lang.Object	writeReplace() {
        return super.writeReplace();
    }
    /*extends BaseIndustry{
    @Override
    public void initWithParams(List<String> params) {

    }

    @Override
    public MarketAPI getMarket() {
        return null;
    }

    @Override
    public void apply() {

    }

    @Override
    public void unapply() {

    }

    @Override
    public void reapply() {

    }

    @Override
    public void advance(float amount) {

    }

    @Override
    public List<MutableCommodityQuantity> getAllSupply() {
        return null;
    }

    @Override
    public List<MutableCommodityQuantity> getAllDemand() {
        return null;
    }

    @Override
    public MutableCommodityQuantity getSupply(String id) {
        return null;
    }

    @Override
    public MutableCommodityQuantity getDemand(String id) {
        return null;
    }

    @Override
    public MutableStat getIncome() {
        return null;
    }

    @Override
    public MutableStat getUpkeep() {
        return null;
    }

    @Override
    public void init(String id, MarketAPI market) {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public IndustrySpecAPI getSpec() {
        return null;
    }

    @Override
    public Pair<String, Integer> getMaxDeficit(String... commodityIds) {
        return null;
    }

    @Override
    public List<Pair<String, Integer>> getAllDeficit(String... commodityIds) {
        return null;
    }

    @Override
    public List<Pair<String, Integer>> getAllDeficit() {
        return null;
    }

    @Override
    public void doPreSaveCleanup() {

    }

    @Override
    public void doPostSaveRestore() {

    }

    @Override
    public String getCurrentImage() {
        return null;
    }

    @Override
    public String getCurrentName() {
        return null;
    }

    @Override
    public boolean isBuilding() {
        return false;
    }

    @Override
    public boolean isUpgrading() {
        return false;
    }

    @Override
    public void startBuilding() {

    }

    @Override
    public void finishBuildingOrUpgrading() {

    }

    @Override
    public void startUpgrading() {

    }

    @Override
    public float getBuildOrUpgradeProgress() {
        return 0;
    }

    @Override
    public float getBuildTime() {
        return 0;
    }

    @Override
    public float getBuildCost() {
        return 0;
    }

    @Override
    public float getBaseUpkeep() {
        return 0;
    }

    @Override
    public boolean isAvailableToBuild() {
        return false;
    }

    @Override
    public boolean showWhenUnavailable() {
        return false;
    }

    @Override
    public String getUnavailableReason() {
        return null;
    }

    @Override
    public String getBuildOrUpgradeProgressText() {
        return null;
    }

    @Override
    public boolean isFunctional() {
        return false;
    }

    @Override
    public boolean isTooltipExpandable() {
        return false;
    }

    @Override
    public float getTooltipWidth() {
        return 0;
    }

    @Override
    public void createTooltip(IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded) {

    }

    @Override
    public void updateIncomeAndUpkeep() {

    }

    @Override
    public String getAICoreId() {
        return null;
    }

    @Override
    public void setAICoreId(String aiCoreId) {

    }

    @Override
    public void supply(String modId, String commodityId, int quantity, String desc) {

    }

    @Override
    public void downgrade() {

    }

    @Override
    public void cancelUpgrade() {

    }

    @Override
    public boolean showShutDown() {
        return false;
    }

    @Override
    public boolean canShutDown() {
        return false;
    }

    @Override
    public String getCanNotShutDownReason() {
        return null;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public boolean canDowngrade() {
        return false;
    }

    @Override
    public void addAICoreSection(TooltipMakerAPI tooltip, AICoreDescriptionMode mode) {

    }

    @Override
    public void addAICoreSection(TooltipMakerAPI tooltip, String coreId, AICoreDescriptionMode mode) {

    }

    @Override
    public boolean isSupplyLegal(CommodityOnMarketAPI com) {
        return false;
    }

    @Override
    public boolean isDemandLegal(CommodityOnMarketAPI com) {
        return false;
    }

    @Override
    public MutableStat getDemandReduction() {
        return null;
    }

    @Override
    public MutableStat getSupplyBonus() {
        return null;
    }

    @Override
    public List<SpecialItemData> getVisibleInstalledItems() {
        return null;
    }

    @Override
    public List<InstallableIndustryItemPlugin> getInstallableItems() {
        return null;
    }

    @Override
    public void notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade) {

    }

    @Override
    public boolean isHidden() {
        return false;
    }

    @Override
    public void setDisrupted(float days) {

    }

    @Override
    public void setDisrupted(float days, boolean useMax) {

    }

    @Override
    public float getDisruptedDays() {
        return 0;
    }

    @Override
    public boolean isDisrupted() {
        return false;
    }

    @Override
    public boolean canBeDisrupted() {
        return false;
    }

    @Override
    public float getPatherInterest() {
        return 0;
    }

    @Override
    public String getCargoTitleForGatheringPoint() {
        return null;
    }

    @Override
    public CargoAPI generateCargoForGatheringPoint(Random random) {
        return null;
    }

    @Override
    public SpecialItemData getSpecialItem() {
        return null;
    }

    @Override
    public void setSpecialItem(SpecialItemData special) {

    }

    @Override
    public String getNameForModifier() {
        return null;
    }

    @Override
    public boolean wantsToUseSpecialItem(SpecialItemData data) {
        return false;
    }

    @Override
    public boolean isIndustry() {
        return false;
    }

    @Override
    public boolean isStructure() {
        return false;
    }

    @Override
    public boolean isOther() {
        return false;
    }

    @Override
    public String getBuildOrUpgradeDaysText() {
        return null;
    }

    @Override
    public void notifyColonyRenamed() {

    }

    @Override
    public boolean canImprove() {
        return false;
    }

    @Override
    public boolean isImproved() {
        return false;
    }

    @Override
    public void setImproved(boolean improved) {

    }

    @Override
    public String getImproveMenuText() {
        return null;
    }

    @Override
    public void addImproveDesc(TooltipMakerAPI info, ImprovementDescriptionMode mode) {

    }

    @Override
    public int getImproveStoryPoints() {
        return 0;
    }

    @Override
    public float getImproveBonusXP() {
        return 0;
    }

    @Override
    public String getImproveSoundId() {
        return null;
    }

    @Override
    public String getImproveDialogTitle() {
        return null;
    }

    @Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(String commodityId, MarketCMD.RaidDangerLevel level) {
        return null;
    }

    @Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(String itemId, String data, MarketCMD.RaidDangerLevel level) {
        return null;
    }

    @Override
    public int adjustMarineTokensToRaidItem(String itemId, String data, int marineTokens) {
        return 0;
    }

    @Override
    public boolean canInstallAICores() {
        return false;
    }

    @Override
    public MutableStat getDemandReductionFromOther() {
        return null;
    }

    @Override
    public MutableStat getSupplyBonusFromOther() {
        return null;
    }

    @Override
    public void setHidden(boolean hidden) {

    }*/

}
