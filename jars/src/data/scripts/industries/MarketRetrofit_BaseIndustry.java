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
import java.util.Random;

public class MarketRetrofit_BaseIndustry extends BaseIndustry implements MarketImmigrationModifier, FleetEventListener, RouteManager.RouteFleetSpawner{
    /* this is the industry base for all instance industry.
    * this is what an user would extend if they wanted to there industry to support this mod*/
    //protected String Industry = "";
    public String MarketRetrofits_IndustryID(){
        return "";
    }
    @Override
    public void apply() {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).apply();
        //MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).apply();
        //get whatever industry should be used and use its methods here
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addAICoreSection(tooltip,mode);
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addAICoreSection(tooltip,coreId,mode);
    }
    @Override
    protected void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addAlphaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addBetaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addGammaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addGroundDefensesImpactSection(tooltip,bonus,commodities);
    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addImproveDesc(info,mode);
    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addImprovedSection(mode,tooltip,expanded);
    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addInstalledItemsSection(mode,tooltip,expanded);
    }
    @Override
    protected boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addNonAICoreInstalledItems(mode,tooltip,expanded);
    }
    @Override
    protected void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addPostDemandSection(tooltip,hasDemand,mode);
    }
    @Override
    protected void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addPostDescriptionSection(tooltip,mode);
    }
    @Override
    protected void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addPostSupplySection(tooltip,hasSupply,mode);
    }
    @Override
    protected void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addPostUpkeepSection(tooltip,mode);
    }
    @Override
    protected void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addRightAfterDescriptionSection(tooltip,mode);
    }
    @Override
    protected void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).addStabilityPostDemandSection(tooltip,hasDemand,mode);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).adjustCommodityDangerLevel(commodityId,level);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).adjustItemDangerLevel(itemId,data,level);
    }
    @Override
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).adjustMarineTokensToRaidItem(itemId,data,marineTokens);
    }
    @Override
    public void	advance(float amount){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).advance(amount);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).apply(withIncomeUpdate);
    }
    @Override
    protected void	applyAICoreModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyAICoreModifiers();
    }
    @Override
    protected void	applyAICoreToIncomeAndUpkeep(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyAICoreToIncomeAndUpkeep();
    }
    @Override
    protected void	applyAlphaCoreModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyAlphaCoreModifiers();
    }
    @Override
    protected void	applyAlphaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyAlphaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyBetaCoreModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyBetaCoreModifiers();
    }
    @Override
    protected void	applyBetaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyBetaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyDeficitToProduction(index,deficit,commodities);
    }
    @Override
    protected void	applyGammaCoreModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyGammaCoreModifiers();
    }
    @Override
    protected void	applyGammaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyGammaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyImproveModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyImproveModifiers();
    }
    @Override
    protected void	applyIncomeAndUpkeep(float sizeOverride){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyIncomeAndUpkeep(sizeOverride);
    }
    @Override
    protected void	applyNoAICoreModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).applyNoAICoreModifiers();
    }
    @Override
    protected void	buildingFinished(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).buildingFinished();
    }
    @Override
    //public static void	buildNextInQueue(MarketAPI market)
    public boolean	canBeDisrupted(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).canBeDisrupted();
    }
    @Override
    public void	cancelUpgrade(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).cancelUpgrade();
    }
    @Override
    public boolean	canDowngrade(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).canDowngrade();
    }
    @Override
    public boolean	canImprove(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).canImprove();
    }
    @Override
    protected boolean canImproveToIncreaseProduction(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).canImproveToIncreaseProduction();
    }
    @Override
    public boolean	canInstallAICores(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).canInstallAICores();
    }
    @Override
    public boolean	canShutDown(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).canShutDown();
    }
    @Override
    public boolean	canUpgrade(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).canUpgrade();
    }
    @Override
    public void	clearUnmodified(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).clearUnmodified();
    }
    @Override
    protected BaseIndustry	clone(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).clone();
    }
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).createTooltip(mode,tooltip,expanded);
    }
    @Override
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).demand(index,commodityId,quantity,desc);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).demand(commodityId,quantity);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).demand(commodityId,quantity,desc);
    }
    @Override
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).demand(modId,commodityId,quantity,desc);
    }
    @Override
    protected void	disruptionFinished(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).disruptionFinished();
    }
    @Override
    public void	doPostSaveRestore(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).doPostSaveRestore();
    }
    @Override
    public void	doPreSaveCleanup(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).doPreSaveCleanup();
    }
    @Override
    public void	downgrade(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).downgrade();
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).finishBuildingOrUpgrading();
    }
    @Override
    public CargoAPI	generateCargoForGatheringPoint(java.util.Random random){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).generateCargoForGatheringPoint(random);
    }
    @Override
    public java.lang.String	getAICoreId(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getAICoreId();
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getAllDeficit();
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getAllDeficit(commodityIds);
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getAllDemand();
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getAllSupply();
    }
    @Override
    protected int	getBaseStabilityMod(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBaseStabilityMod();
    }
    @Override
    public float	getBaseUpkeep(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBaseUpkeep();
    }
    @Override
    public float	getBuildCost(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBuildCost();
    }
    @Override
    public java.lang.Float	getBuildCostOverride(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBuildCostOverride();
    }
    @Override
    public java.lang.String	getBuildOrUpgradeDaysText(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBuildOrUpgradeDaysText();
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBuildOrUpgradeProgress();
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBuildOrUpgradeProgressText();
    }
    @Override
    public float	getBuildProgress(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBuildProgress();
    }
    @Override
    public float	getBuildTime(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getBuildTime();
    }
    @Override
    public java.lang.String	getCanNotShutDownReason(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getCanNotShutDownReason();
    }
    @Override
    protected CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getCargoForInteractionMode(mode);
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getCargoTitleForGatheringPoint();
    }
    @Override
    /*public static float	getCommodityEconUnitMult(float size){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getComm
    }*/
    public java.lang.String	getCurrentImage(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getCurrentImage();
    }
    @Override
    public java.lang.String	getCurrentName(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getCurrentName();
    }
    @Override
    protected float	getDeficitMult(java.lang.String... commodities){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getDeficitMult(commodities);
    }
    @Override
    /*public static java.lang.String	getDeficitText(java.lang.String commodityId){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getDeficitText();
    }*/
    public MutableCommodityQuantity	getDemand(java.lang.String id){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getDemand(id);
    }
    @Override
    public MutableStat	getDemandReduction(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getDemandReduction();
    }
    @Override
    public MutableStat	getDemandReductionFromOther(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getDemandReductionFromOther();
    }
    @Override
    protected java.lang.String	getDescriptionOverride(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getDescriptionOverride();
    }
    @Override
    public float	getDisruptedDays(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getDisruptedDays();
    }
    @Override
    public java.lang.String	getDisruptedKey(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getDisruptedKey();
    }

    @Override
    public java.lang.String getId(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getId();
    }
    @Override
    public float	getImproveBonusXP(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getImproveBonusXP();
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getImproveDialogTitle();
    }
    @Override
    protected java.lang.String	getImprovementsDescForModifiers(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getImprovementsDescForModifiers();
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getImproveMenuText();
    }
    @Override
    protected int	getImproveProductionBonus(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getImproveProductionBonus();
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getImproveSoundId();
    }
    @Override
    public int	getImproveStoryPoints(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getImproveStoryPoints();
    }
    @Override
    public MutableStat	getIncome(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getIncome();
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getInstallableItems();
    }
    @Override
    public MarketAPI	getMarket(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getMarket();
    }
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getMaxDeficit(commodityIds);
    }
    @Override
    protected java.lang.String	getModId(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getModId();
    }
    @Override
    protected java.lang.String	getModId(int index){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getModId(index);
    }
    @Override
    public java.lang.String	getNameForModifier(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getNameForModifier();
    }
    @Override
    public float	getPatherInterest(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getPatherInterest();
    }
    @Override
    public float	getSizeMult(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getSizeMult();
    }
    /*public static float getSizeMult(float size){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getSizeMult(size);
    }*/

    @Override
    public IndustrySpecAPI	getSpec(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getSpec();
    }
    @Override
    public SpecialItemData	getSpecialItem(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getSpecialItem();
    }
    @Override
    protected Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getStabilityAffectingDeficit();
    }
    @Override
    protected int	getStabilityPenalty(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getStabilityPenalty();
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getSupply(id);
    }
    @Override
    public MutableStat	getSupplyBonus(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getSupplyBonus();
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getSupplyBonusFromOther();
    }
    @Override
    public float	getTooltipWidth(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getTooltipWidth();
    }
    @Override
    public java.lang.String	getUnavailableReason(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getUnavailableReason();
    }
    @Override
    public MutableStat	getUpkeep(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getUpkeep();
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).getVisibleInstalledItems();
    }
    @Override
    protected boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        return hasPostDemandSection(hasDemand,mode);
    }
    @Override
    public void	init(java.lang.String id, MarketAPI market){
        this.market = market;
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).init(id,market);
    }
    @Override
    public void	initWithParams(java.util.List<java.lang.String> params){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).initWithParams(params);
    }
    @Override
    //Used when loading market from an economy .json file.
    protected boolean	isAICoreId(java.lang.String str){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isAICoreId(str);
    }
    @Override
    public boolean	isAvailableToBuild(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isAvailableToBuild();
    }
    @Override
    public boolean	isBuilding(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isBuilding();
    }
    @Override
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isDemandLegal(com);
    }
    @Override
    public boolean	isDisrupted(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isDisrupted();
    }
    @Override
    public boolean	isFunctional(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isFunctional();
    }
    @Override
    //Building and not upgrading.
    public boolean	isHidden(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isHidden();
    }
    @Override
    public boolean	isImproved(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isImproved();
    }
    @Override
    public boolean	isIndustry(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isIndustry();
    }
    @Override
    public boolean	isOther(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isOther();
    }
    @Override
    public boolean	isStructure(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isStructure();
    }
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isSupplyLegal(com);
    }
    @Override
    public boolean	isTooltipExpandable(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isTooltipExpandable();
    }
    @Override
    public boolean	isUpgrading(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).isUpgrading();
    }
    @Override
    //Upgrading, but not the initial building process.
    protected void	modifyStabilityWithBaseMod(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).modifyStabilityWithBaseMod();
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).notifyBeingRemoved(mode,forUpgrade);
    }
    @Override
    public void	notifyColonyRenamed(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).notifyColonyRenamed();
    }
    @Override
    protected void	notifyDisrupted(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).notifyDisrupted();
    }
    @Override
    protected java.lang.Object	readResolve(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).readResolve();
    }
    @Override
    public void	reapply(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).reapply();
    }
    @Override
    //Calls unapply() and then reapply().
    protected void	sendBuildOrUpgradeMessage(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).sendBuildOrUpgradeMessage();
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).setAICoreId(aiCoreId);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).setBuildCostOverride(buildCostOverride);
    }
    @Override
    public void	setBuildProgress(float buildProgress){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).setBuildProgress(buildProgress);
    }
    @Override
    public void	setDisrupted(float days){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).setDisrupted(days);
    }
    @Override
    public void	setDisrupted(float days, boolean useMax){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).setDisrupted(days,useMax);
    }
    @Override
    public void	setHidden(boolean hidden){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).setHidden(hidden);
    }
    @Override
    public void	setImproved(boolean improved){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).setImproved(improved);
    }
    @Override
    public void	setSpecialItem(SpecialItemData special){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).setSpecialItem(special);
    }
    @Override
    public boolean	showShutDown(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).showShutDown();
    }
    @Override
    public boolean	showWhenUnavailable(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).showWhenUnavailable();
    }
    @Override
    public void	startBuilding(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).startBuilding();
    }
    @Override
    public void	startUpgrading(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).startUpgrading();
    }
    @Override
    public void	supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).supply(index,commodityId,quantity,desc);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).supply(commodityId,quantity);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).supply(commodityId,quantity,desc);
    }
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).supply(modId,commodityId,quantity,desc);
    }
    @Override
    public void	unapply(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).unapply();
    }
    @Override
    protected void	unmodifyStabilityWithBaseMod(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).unmodifyStabilityWithBaseMod();
    }
    @Override
    protected void	updateAICoreToSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).updateAICoreToSupplyAndDemandModifiers();
    }
    @Override
    protected void	updateImprovementSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).updateImprovementSupplyAndDemandModifiers();
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).updateIncomeAndUpkeep();
    }
    @Override
    protected void	updateSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).updateSupplyAndDemandModifiers();
    }
    @Override
    protected void	upgradeFinished(Industry previous){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).upgradeFinished(previous);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).wantsToUseSpecialItem(data);
    }
    @Override
    //Return false if already using one of that type, unless the other one is better.
    protected java.lang.Object	writeReplace() {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).writeReplace();
    }

    //HERE apply all implementations
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).modifyIncoming(market,incoming);
    }

    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).reportFleetDespawnedToListener(fleet,reason,param);
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).reportBattleOccurred(fleet,primaryWinner,battle);

    }

    @Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).spawnFleet(route);
    }

    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).shouldCancelRouteAfterDelayCheck(route);
    }

    @Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).shouldRepeat(route);
    }

    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(market).reportAboutToBeDespawnedByRouteManager(route);
    }

}
