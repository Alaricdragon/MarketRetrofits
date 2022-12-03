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
        super.apply(true);//done like this in normal industry
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).apply();
        //MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).apply();
        //get whatever industry should be used and use its methods here
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addAICoreSection(tooltip,mode);
    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addAICoreSection(tooltip,coreId,mode);
    }
    @Override
    protected void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addAlphaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addBetaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addGammaCoreDescription(tooltip,mode);
    }
    @Override
    protected void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addGroundDefensesImpactSection(tooltip,bonus,commodities);
    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addImproveDesc(info,mode);
    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addImprovedSection(mode,tooltip,expanded);
    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addInstalledItemsSection(mode,tooltip,expanded);
    }
    @Override
    protected boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addNonAICoreInstalledItems(mode,tooltip,expanded);
    }
    @Override
    protected void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        //blank by default
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addPostDemandSection(tooltip,hasDemand,mode);
    }
    @Override
    protected void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addPostDescriptionSection(tooltip,mode);
    }
    @Override
    protected void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addPostSupplySection(tooltip,hasSupply,mode);
    }
    @Override
    protected void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addPostUpkeepSection(tooltip,mode);
    }
    @Override
    protected void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addRightAfterDescriptionSection(tooltip,mode);
    }
    @Override
    protected void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).addStabilityPostDemandSection(tooltip,hasDemand,mode);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        //empty
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).adjustCommodityDangerLevel(commodityId,level);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level){
        //empty
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).adjustItemDangerLevel(itemId,data,level);
    }
    @Override
    //Includes nonecon "commodities" such as AI cores.
    public int	adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens){
        //empty
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).adjustMarineTokensToRaidItem(itemId,data,marineTokens);
    }
    @Override
    public void	advance(float amount){
        super.advance(amount);//required in all seen instances
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).advance(amount);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        super.apply(true);//unknown requirements. still want to run it though.
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).apply(withIncomeUpdate);
    }
    @Override
    protected void	applyAICoreModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyAICoreModifiers();
    }
    @Override
    protected void	applyAICoreToIncomeAndUpkeep(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyAICoreToIncomeAndUpkeep();
    }
    @Override
    protected void	applyAlphaCoreModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyAlphaCoreModifiers();
    }
    @Override
    protected void	applyAlphaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyAlphaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyBetaCoreModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyBetaCoreModifiers();
    }
    @Override
    protected void	applyBetaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyBetaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyDeficitToProduction(index,deficit,commodities);
    }
    @Override
    protected void	applyGammaCoreModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyGammaCoreModifiers();
    }
    @Override
    protected void	applyGammaCoreSupplyAndDemandModifiers(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyGammaCoreSupplyAndDemandModifiers();
    }
    @Override
    protected void	applyImproveModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyImproveModifiers();
    }
    @Override
    protected void	applyIncomeAndUpkeep(float sizeOverride){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyIncomeAndUpkeep(sizeOverride);
    }
    @Override
    protected void	applyNoAICoreModifiers(){
        //empty
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).applyNoAICoreModifiers();
    }
    @Override
    protected void	buildingFinished(){
        super.buildingFinished();//always run this
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).buildingFinished();
    }
    @Override
    //public static void	buildNextInQueue(MarketAPI market)
    public boolean	canBeDisrupted(){
        //allow. this is as intended.
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).canBeDisrupted();
    }
    @Override
    public void	cancelUpgrade(){
        super.cancelUpgrade();//always run this
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).cancelUpgrade();
    }
    @Override
    public boolean	canDowngrade(){
        super.canDowngrade();//always run this
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).canDowngrade();
    }
    @Override
    public boolean	canImprove(){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).canImprove();
    }
    @Override
    protected boolean canImproveToIncreaseProduction(){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).canImproveToIncreaseProduction();
    }
    @Override
    public boolean	canInstallAICores(){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).canInstallAICores();
    }
    @Override
    public boolean	canShutDown(){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).canShutDown();
    }
    @Override
    public boolean	canUpgrade(){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).canUpgrade();
    }
    @Override
    public void	clearUnmodified(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).clearUnmodified();
    }/*//HERE
    @Override
    protected BaseIndustry	clone(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).clone();
    }*/
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        //description
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).createTooltip(mode,tooltip,expanded);
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
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        super.finishBuildingOrUpgrading();
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).finishBuildingOrUpgrading();
    }
    @Override
    public CargoAPI	generateCargoForGatheringPoint(java.util.Random random){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).generateCargoForGatheringPoint(random);
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
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBaseStabilityMod();
    }
    @Override
    public float	getBaseUpkeep(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBaseUpkeep();
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
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBuildOrUpgradeDaysText();
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBuildOrUpgradeProgress();
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getBuildOrUpgradeProgressText();
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
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getCanNotShutDownReason();
    }
    @Override
    protected CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getCargoForInteractionMode(mode);
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getCargoTitleForGatheringPoint();
    }
    @Override
    /*public static float	getCommodityEconUnitMult(float size){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getComm
    }*/
    public java.lang.String	getCurrentImage(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getCurrentImage();
    }
    @Override
    public java.lang.String	getCurrentName(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getCurrentName();
    }
    @Override
    protected float	getDeficitMult(java.lang.String... commodities){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getDeficitMult(commodities);
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
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getDescriptionOverride();
    }
    @Override
    public float	getDisruptedDays(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getDisruptedDays();
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
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getImproveBonusXP();
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getImproveDialogTitle();
    }
    @Override
    protected java.lang.String	getImprovementsDescForModifiers(){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getImprovementsDescForModifiers();
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getImproveMenuText();
    }
    @Override
    protected int	getImproveProductionBonus(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getImproveProductionBonus();
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        //as intended
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getImproveSoundId();
    }
    @Override
    public int	getImproveStoryPoints(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getImproveStoryPoints();
    }
    @Override
    public MutableStat	getIncome(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getIncome();
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        //APPLY SUPER FROM CLASS AS DEFAULT
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getInstallableItems();
    }
    /*//HERE
    @Override
    public MarketAPI	getMarket(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getMarket();
    }*/
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getMaxDeficit(commodityIds);
    }
    @Override
    protected java.lang.String	getModId(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getModId();
    }
    @Override
    protected java.lang.String	getModId(int index){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getModId(index);
    }
    @Override
    public java.lang.String	getNameForModifier(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getNameForModifier();
    }
    @Override
    public float	getPatherInterest(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getPatherInterest();
    }
    @Override
    public float	getSizeMult(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getSizeMult();
    }
    /*public static float getSizeMult(float size){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getSizeMult(size);
    }*/

    @Override
    public IndustrySpecAPI	getSpec(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getSpec();
    }
    @Override
    public SpecialItemData	getSpecialItem(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getSpecialItem();
    }
    @Override
    protected Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getStabilityAffectingDeficit();
    }
    @Override
    protected int	getStabilityPenalty(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getStabilityPenalty();
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getSupply(id);
    }
    @Override
    public MutableStat	getSupplyBonus(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getSupplyBonus();
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getSupplyBonusFromOther();
    }
    @Override
    public float	getTooltipWidth(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getTooltipWidth();
    }
    @Override
    public java.lang.String	getUnavailableReason(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getUnavailableReason();
    }
    @Override
    public MutableStat	getUpkeep(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getUpkeep();
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).getVisibleInstalledItems();
    }
    @Override
    protected boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        return hasPostDemandSection(hasDemand,mode);
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
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isAICoreId(str);
    }
    @Override
    public boolean	isAvailableToBuild(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isAvailableToBuild();
    }
    @Override
    public boolean	isBuilding(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isBuilding();
    }
    @Override
    //Building OR upgrading.
    public boolean	isDemandLegal(CommodityOnMarketAPI com){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isDemandLegal(com);
    }
    @Override
    public boolean	isDisrupted(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isDisrupted();
    }
    @Override
    public boolean	isFunctional(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isFunctional();
    }
    @Override
    //Building and not upgrading.
    public boolean	isHidden(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isHidden();
    }
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
    }
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isSupplyLegal(com);
    }
    @Override
    public boolean	isTooltipExpandable(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isTooltipExpandable();
    }
    @Override
    public boolean	isUpgrading(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).isUpgrading();
    }
    @Override
    //Upgrading, but not the initial building process.
    protected void	modifyStabilityWithBaseMod(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).modifyStabilityWithBaseMod();
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).notifyBeingRemoved(mode,forUpgrade);
    }
    @Override
    public void	notifyColonyRenamed(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).notifyColonyRenamed();
    }
    @Override
    protected void	notifyDisrupted(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).notifyDisrupted();
    }
    @Override
    protected java.lang.Object	readResolve(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).readResolve();
    }
    @Override
    public void	reapply(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).reapply();
    }
    @Override
    //Calls unapply() and then reapply().
    protected void	sendBuildOrUpgradeMessage(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).sendBuildOrUpgradeMessage();
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setAICoreId(aiCoreId);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBuildCostOverride(buildCostOverride);
    }
    @Override
    public void	setBuildProgress(float buildProgress){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setBuildProgress(buildProgress);
    }
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
    }
    @Override
    public void	setSpecialItem(SpecialItemData special){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).setSpecialItem(special);
    }
    @Override
    public boolean	showShutDown(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).showShutDown();
    }
    @Override
    public boolean	showWhenUnavailable(){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).showWhenUnavailable();
    }
    @Override
    public void	startBuilding(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).startBuilding();
    }
    @Override
    public void	startUpgrading(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).startUpgrading();
    }
    @Override
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
    }
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).supply(modId,commodityId,quantity,desc);
    }
    @Override
    public void	unapply(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).unapply();
    }
    @Override
    protected void	unmodifyStabilityWithBaseMod(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).unmodifyStabilityWithBaseMod();
    }
    @Override
    protected void	updateAICoreToSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).updateAICoreToSupplyAndDemandModifiers();
    }
    @Override
    protected void	updateImprovementSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).updateImprovementSupplyAndDemandModifiers();
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).updateIncomeAndUpkeep();
    }
    @Override
    protected void	updateSupplyAndDemandModifiers(){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).updateSupplyAndDemandModifiers();
    }
    @Override
    protected void	upgradeFinished(Industry previous){
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).upgradeFinished(previous);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).wantsToUseSpecialItem(data);
    }
    @Override
    //Return false if already using one of that type, unless the other one is better.
    protected java.lang.Object	writeReplace() {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).writeReplace();
    }

    //HERE apply all implementations
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).modifyIncoming(market,incoming);
    }

    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).reportFleetDespawnedToListener(fleet,reason,param);
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).reportBattleOccurred(fleet,primaryWinner,battle);

    }

    @Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).spawnFleet(route);
    }

    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).shouldCancelRouteAfterDelayCheck(route);
    }

    @Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        return MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).shouldRepeat(route);
    }

    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {
        MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(getMarket(),getId(),this).reportAboutToBeDespawnedByRouteManager(route);
    }


    //HERE data handling for things and stuff: (Never mind, i cant pull data down. how would i even know were to pull from anyways?)
    public FleetEventListener returnFleetListiner(){
        return this;
    }
}
