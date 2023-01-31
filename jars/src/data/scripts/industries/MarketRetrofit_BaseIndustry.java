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
import data.scripts.MarketRetrofits_Logger;
import data.scripts.industries.Lists.MarketRetrofits_IndustryMasterList;
import data.scripts.industries.base.MarketRetrofit_IndustryDataExstange;

import java.util.ArrayList;
import java.util.List;


public class MarketRetrofit_BaseIndustry extends MarketRetrofit_IndustryDataExstange implements MarketImmigrationModifier, FleetEventListener, RouteManager.RouteFleetSpawner{
    /* this is the industry base for all instance industry.
    * this is what an user would extend if they wanted to there industry to support this mod*/
    //public String Industry = "";
    private final static boolean BaseIndustryLogs = Global.getSettings().getBoolean("MarketRetrofits_BaseIndustryLogs");
    //private MarketRetorfits_ExstraData exstraData = new MarketRetorfits_ExstraData();//moved into industryDataExstange
    //private MarketRetorfits_ExstraData exstraDataTemp = new MarketRetorfits_ExstraData();//never used
    private ArrayList<MarketRetrofits_DefaltInstanceIndustry> pastIndustrys = new ArrayList<>();
    private MarketRetrofits_DefaltInstanceIndustry CurrentInstance = null;

    private MarketRetrofits_DefaltInstanceIndustry defaltInstance = null;
    private ArrayList<MarketRetrofits_DefaltInstanceIndustry> instanceIndusrys = null;
    public String MarketRetrofits_IndustryID(){
        return this.getSpec().getId();//would that even work?
    }

    private void gatherPossableInstances(){
        defaltInstance = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).copyDefaltIndustry();
        instanceIndusrys = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).copySets();
    }
    private void resetPossableInstances(){
        resetActiveInstanceIfRequired();
        defaltInstance = null;
        instanceIndusrys = null;
    }
    private void resetActiveInstanceIfRequired(){
        if(pastIndustrys.size() == 0){
            CurrentInstance = null;
        }
    }

    public MarketRetrofits_DefaltInstanceIndustry getActiveInstance(){
        /*how this works:
        * i will sometimes reset currentInstnace. when that happens, i will get a new one with this code.
        * all other times, i will simply return the rememberd instance.
        * also this will reduce calculation a little.*/
        if(defaltInstance == null|| instanceIndusrys == null){
            gatherPossableInstances();
        }
        if(CurrentInstance == null){
            CurrentInstance = MarketRetrofits_IndustryMasterList.getInstance(MarketRetrofits_IndustryID()).getActiveInstance(defaltInstance,instanceIndusrys,super.getMarket(),super.getId(),this);
        }
        return CurrentInstance;
    }

    public void applyDataToInstance(MarketRetrofits_DefaltInstanceIndustry a){
        MarketRetrofits_Logger.logging("running applyDataToInstance in: " + this,this,BaseIndustryLogs);
        MarketRetrofits_Logger.logging("    size: " + pastIndustrys.size(),this,BaseIndustryLogs);
        a.CurrentIndustry = this;
        if(pastIndustrys.size() == 0){
            MarketRetrofits_Logger.logging("    getting data from BaseIndustry",this,BaseIndustryLogs);
            a.IndustryDataGet(this,this);
            pastIndustrys.add(a);
            return;
        }
        MarketRetrofits_DefaltInstanceIndustry b = pastIndustrys.get(pastIndustrys.size() - 1);
        if(b != a) {
            MarketRetrofits_Logger.logging("    getting data from past industry",this,BaseIndustryLogs);
            b.setExtraDataToIndustry(b.exstraData);
            a.IndustryDataGet(b);
        }else {
            MarketRetrofits_Logger.logging("    skiping getting data. data is the same",this,BaseIndustryLogs);
        }
        //}
        pastIndustrys.add(a);
    }
    public void getDataFromInstance(MarketRetrofits_DefaltInstanceIndustry a){
        MarketRetrofits_Logger.logging("running getDataFromInstance in: " + this,this,BaseIndustryLogs);
        MarketRetrofits_Logger.logging("    size: " + pastIndustrys.size(),this,BaseIndustryLogs);
        a.CurrentIndustry = this;

        if(pastIndustrys.size() <= 1){
            MarketRetrofits_Logger.logging("    setting data to BaseIndustry",this,BaseIndustryLogs);
            a.IndustryDataCleanup(this);
            pastIndustrys.remove(pastIndustrys.size() - 1);
            return;
        }
        MarketRetrofits_DefaltInstanceIndustry b = pastIndustrys.get(pastIndustrys.size() - 1);
        if(b != a) {
            MarketRetrofits_Logger.logging("    setting data to pastIndustry",this,BaseIndustryLogs);
            b.setExtraDataToIndustry(b.exstraData);
            a.IndustryDataCleanup(b);
        }else{
            MarketRetrofits_Logger.logging("    skiping data set. data is the same",this,BaseIndustryLogs);
        }
        pastIndustrys.remove(pastIndustrys.size() - 1);
    }

    public void applyDataToInstanceOLD(MarketRetrofits_DefaltInstanceIndustry a){
        MarketRetrofits_Logger.logging("running applyDataToInstance in: " + this,this,BaseIndustryLogs);
        MarketRetrofits_Logger.logging("    size: " + pastIndustrys.size(),this,BaseIndustryLogs);
        a.CurrentIndustry = this;
        if(pastIndustrys.size() != 0){
            pastIndustrys.get(pastIndustrys.size() - 1).IndustryDataCleanup(this);
        }
        MarketRetrofits_Logger.logging("    getting data from BaseIndustry",this,BaseIndustryLogs);
        a.IndustryDataGet(this,this);
        pastIndustrys.add(a);
    }
    public void getDataFromInstanceOLD(MarketRetrofits_DefaltInstanceIndustry a){
        //0-1-2-3-2-1-2-3
        MarketRetrofits_Logger.logging("running getDataFromInstance in: " + this,this,BaseIndustryLogs);
        MarketRetrofits_Logger.logging("    size: " + pastIndustrys.size(),this,BaseIndustryLogs);
        //a.CurrentIndustry = this;
        //if(pastIndustrys.size() > 1){
            //pastIndustrys.get(pastIndustrys.size() - 1).
        //}
        MarketRetrofits_Logger.logging("    setting data to BaseIndustry",this,BaseIndustryLogs);
        a.IndustryDataCleanup(this);
        pastIndustrys.remove(pastIndustrys.size() - 1);
    }
    @Override
    public void apply() {
        MarketRetrofits_Logger.logging("running class: apply()",this, BaseIndustryLogs);
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
        MarketRetrofits_Logger.logging("running class: addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addAICoreSection(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_Logger.logging("running class: addAICoreSection(TooltipMakerAPI tooltip, java.lang.String coreId, Industry.AICoreDescriptionMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addAICoreSection(tooltip,coreId,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_Logger.logging("running class: addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addAlphaCoreDescription(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_Logger.logging("running class: addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addBetaCoreDescription(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode){
        MarketRetrofits_Logger.logging("running class: addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addGammaCoreDescription(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities){
        MarketRetrofits_Logger.logging("running class: addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, java.lang.String... commodities)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addGroundDefensesImpactSection(tooltip,bonus,commodities);
        getDataFromInstance(a);

    }
    @Override
    public void	addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode){
        MarketRetrofits_Logger.logging("running class: addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addImproveDesc(info,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_Logger.logging("running class: addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addImprovedSection(mode,tooltip,expanded);
        getDataFromInstance(a);

    }
    @Override
    public void	addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_Logger.logging("running class: addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addInstalledItemsSection(mode,tooltip,expanded);
        getDataFromInstance(a);

    }
    @Override
    public boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_Logger.logging("running class: addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        boolean re = a.addNonAICoreInstalledItems(mode,tooltip,expanded);
        getDataFromInstance(a);
        return re;

    }
    @Override
    public void	addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_Logger.logging("running class: addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //blank by default
        applyDataToInstance(a);
        a.addPostDemandSection(tooltip,hasDemand,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_Logger.logging("running class: addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addPostDescriptionSection(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode){
        MarketRetrofits_Logger.logging("running class: addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addPostSupplySection(tooltip,hasSupply,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_Logger.logging("running class: addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addPostUpkeepSection(tooltip,mode);
        getDataFromInstance(a);

    }
    @Override
    public void	addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode){
        MarketRetrofits_Logger.logging("running class: addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addRightAfterDescriptionSection(tooltip,mode);
        getDataFromInstance(a);
    }
    @Override
    public void	addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_Logger.logging("running class: addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.addStabilityPostDemandSection(tooltip,hasDemand,mode);
        getDataFromInstance(a);
    }
    @Override
    //Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level){
        MarketRetrofits_Logger.logging("running class: adjustCommodityDangerLevel(java.lang.String commodityId, MarketCMD.RaidDangerLevel level)",this, BaseIndustryLogs);
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
        MarketRetrofits_Logger.logging("running class: adjustItemDangerLevel(java.lang.String itemId, java.lang.String data, MarketCMD.RaidDangerLevel level)",this, BaseIndustryLogs);
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
        MarketRetrofits_Logger.logging("running class: adjustMarineTokensToRaidItem(java.lang.String itemId, java.lang.String data, int marineTokens)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        int re = a.adjustMarineTokensToRaidItem(itemId, data, marineTokens);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	advance(float amount){
        MarketRetrofits_Logger.logging("running class: advance(float amount)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.advance(amount);//required in all seen instances
        applyDataToInstance(a);
        a.advance(amount);
        getDataFromInstance(a);
    }
    @Override
    public void	apply(boolean withIncomeUpdate){
        MarketRetrofits_Logger.logging("running class: apply(boolean withIncomeUpdate)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.apply(true);//unknown requirements. still want to run it though.
        applyDataToInstance(a);
        a.apply(withIncomeUpdate);
        getDataFromInstance(a);
    }
    @Override
    public void	applyAICoreModifiers(){
        MarketRetrofits_Logger.logging("running class: applyAICoreModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyAICoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyAICoreToIncomeAndUpkeep(){
        MarketRetrofits_Logger.logging("running class: applyAICoreToIncomeAndUpkeep()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyAICoreToIncomeAndUpkeep();
        getDataFromInstance(a);
    }
    @Override
    public void	applyAlphaCoreModifiers(){
        MarketRetrofits_Logger.logging("running class: applyAlphaCoreModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyAlphaCoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyAlphaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_Logger.logging("running class: applyAlphaCoreSupplyAndDemandModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyAlphaCoreSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyBetaCoreModifiers(){
        MarketRetrofits_Logger.logging("running class: applyBetaCoreModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyBetaCoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyBetaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_Logger.logging("running class: applyBetaCoreSupplyAndDemandModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyBetaCoreSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities){
        MarketRetrofits_Logger.logging("running class: applyDeficitToProduction(int index, Pair<java.lang.String,java.lang.Integer> deficit, java.lang.String... commodities)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyDeficitToProduction(index,deficit,commodities);
        getDataFromInstance(a);
    }
    @Override
    public void	applyGammaCoreModifiers(){
        MarketRetrofits_Logger.logging("running class: applyGammaCoreModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyGammaCoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyGammaCoreSupplyAndDemandModifiers(){
        MarketRetrofits_Logger.logging("running class: applyGammaCoreSupplyAndDemandModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyGammaCoreSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyImproveModifiers(){
        MarketRetrofits_Logger.logging("running class: applyImproveModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyImproveModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	applyIncomeAndUpkeep(float sizeOverride){
        MarketRetrofits_Logger.logging("running class: applyIncomeAndUpkeep(float sizeOverride)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.applyIncomeAndUpkeep(sizeOverride);
        getDataFromInstance(a);
    }
    @Override
    public void	applyNoAICoreModifiers(){
        MarketRetrofits_Logger.logging("running class: applyNoAICoreModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.applyNoAICoreModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	buildingFinished(){
        MarketRetrofits_Logger.logging("running class: buildingFinished()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.buildingFinished();//always run this
        applyDataToInstance(a);
        a.buildingFinished();
        getDataFromInstance(a);
    }
    @Override
    public boolean	canBeDisrupted(){
        MarketRetrofits_Logger.logging("running class: canBeDisrupted()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //allow. this is as intended.
        applyDataToInstance(a);
        boolean re = a.canBeDisrupted();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	cancelUpgrade(){
        MarketRetrofits_Logger.logging("running class: cancelUpgrade()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.cancelUpgrade();//always run this
        applyDataToInstance(a);
        a.cancelUpgrade();
        getDataFromInstance(a);

    }
    @Override
    public boolean	canDowngrade(){
        MarketRetrofits_Logger.logging("running class: canDowngrade()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.canDowngrade();//always run this
        applyDataToInstance(a);
        boolean re = a.canDowngrade();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canImprove(){
        MarketRetrofits_Logger.logging("running class: canImprove()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.canImprove();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean canImproveToIncreaseProduction(){
        MarketRetrofits_Logger.logging("running class: canImproveToIncreaseProduction()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.canImproveToIncreaseProduction();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canInstallAICores(){
        MarketRetrofits_Logger.logging("running class: canInstallAICores()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.canInstallAICores();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canShutDown(){
        MarketRetrofits_Logger.logging("running class: canShutDown()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.canShutDown();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	canUpgrade(){
        MarketRetrofits_Logger.logging("running class: canUpgrade()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        boolean re = a.canUpgrade();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	clearUnmodified(){
        MarketRetrofits_Logger.logging("running class: clearUnmodified()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.clearUnmodified();
        getDataFromInstance(a);
    }
    @Override
    public BaseIndustry	clone(){
        MarketRetrofits_Logger.logging("running class: clone()",this, BaseIndustryLogs);
        //HERE. what how does this work? who no defalt instance
        resetPossableInstances();
        return super.clone();
        /*
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        return a.clone();*/
    }
    @Override
    public void	createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded){
        MarketRetrofits_Logger.logging("running class: createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //description
        applyDataToInstance(a);
        a.createTooltip(mode,tooltip,expanded);
        getDataFromInstance(a);
    }
    @Override
    public void	demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_Logger.logging("running class: demand(int index, java.lang.String commodityId, int quantity, java.lang.String desc)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.demand(index,commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity){
        MarketRetrofits_Logger.logging("running class: demand(java.lang.String commodityId, int quantity)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.demand(commodityId,quantity);
        getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_Logger.logging("running class: demand(java.lang.String commodityId, int quantity, java.lang.String desc)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.demand(commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_Logger.logging("running class: demand(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.demand(modId,commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	disruptionFinished(){
        MarketRetrofits_Logger.logging("running class: disruptionFinished()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //empty
        applyDataToInstance(a);
        a.disruptionFinished();
        getDataFromInstance(a);
    }
    @Override
    public void	doPostSaveRestore(){
        MarketRetrofits_Logger.logging("running class: doPostSaveRestore()",this, BaseIndustryLogs);
        //HERE: reseting instances.
        //resetPossableInstances();
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.doPostSaveRestore();
        applyDataToInstance(a);
        a.doPostSaveRestore();
        getDataFromInstance(a);
    }
    @Override
    public void	doPreSaveCleanup(){
        MarketRetrofits_Logger.logging("running class: doPreSaveCleanup()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.doPreSaveCleanup();
        applyDataToInstance(a);
        a.doPreSaveCleanup();
        getDataFromInstance(a);
    }
    @Override
    public void	downgrade(){
        MarketRetrofits_Logger.logging("running class: downgrade()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.downgrade();
        applyDataToInstance(a);
        a.downgrade();
        getDataFromInstance(a);
    }
    @Override
    public void	finishBuildingOrUpgrading(){
        MarketRetrofits_Logger.logging("running class: finishBuildingOrUpgrading()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //super.finishBuildingOrUpgrading();
        applyDataToInstance(a);
        a.finishBuildingOrUpgrading();
        getDataFromInstance(a);
    }
    @Override
    public CargoAPI	generateCargoForGatheringPoint(java.util.Random random){
        MarketRetrofits_Logger.logging("running class: generateCargoForGatheringPoint(java.util.Random random)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        CargoAPI re = a.generateCargoForGatheringPoint(random);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getAICoreId(){
        MarketRetrofits_Logger.logging("running class: getAICoreId()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getAICoreId();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(){
        MarketRetrofits_Logger.logging("running class: getAllDeficit()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        List<Pair<String, Integer>> re = a.getAllDeficit();
        getDataFromInstance(a);
        return re;

    }
    @Override
    public java.util.List<Pair<java.lang.String,java.lang.Integer>>	getAllDeficit(java.lang.String... commodityIds){
        MarketRetrofits_Logger.logging("running class: getAllDeficit(java.lang.String... commodityIds)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        List<Pair<String, Integer>> re = a.getAllDeficit(commodityIds);;
        getDataFromInstance(a);
        return re;
    }

    @Override
    public java.util.List<MutableCommodityQuantity>	getAllDemand(){
        MarketRetrofits_Logger.logging("running class: getAllDemand()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        List<MutableCommodityQuantity> re = a.getAllDemand();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<MutableCommodityQuantity>	getAllSupply(){
        MarketRetrofits_Logger.logging("running class: getAllSupply()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        List<MutableCommodityQuantity> re = a.getAllSupply();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getBaseStabilityMod(){
        MarketRetrofits_Logger.logging("running class: getBaseStabilityMod()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as inteded
        applyDataToInstance(a);
        int re = a.getBaseStabilityMod();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBaseUpkeep(){
        MarketRetrofits_Logger.logging("running class: getBaseUpkeep()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        float re = a.getBaseUpkeep();
        getDataFromInstance(a);
        return re;

    }
    @Override
    public float	getBuildCost(){
        MarketRetrofits_Logger.logging("running class: getBuildCost()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        float re = a.getBuildCost();
        getDataFromInstance(a);
        return re;
    }

    @Override
    public java.lang.Float	getBuildCostOverride(){
        MarketRetrofits_Logger.logging("running class: getBuildCostOverride()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        float re = a.getBuildCostOverride();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getBuildOrUpgradeDaysText(){
        MarketRetrofits_Logger.logging("running class: getBuildOrUpgradeDaysText()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getBuildOrUpgradeDaysText();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildOrUpgradeProgress(){
        MarketRetrofits_Logger.logging("running class: getBuildOrUpgradeProgress()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getBuildOrUpgradeProgress();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getBuildOrUpgradeProgressText(){
        MarketRetrofits_Logger.logging("running class: getBuildOrUpgradeProgressText()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getBuildOrUpgradeProgressText();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildProgress(){
        MarketRetrofits_Logger.logging("running class: getBuildProgress()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        float re = a.getBuildProgress();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getBuildTime(){
        MarketRetrofits_Logger.logging("running class: getBuildTime()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        float re = a.getBuildTime();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCanNotShutDownReason(){
        MarketRetrofits_Logger.logging("running class: getCanNotShutDownReason()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getCanNotShutDownReason();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public CargoAPI	getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode){
        MarketRetrofits_Logger.logging("running class: getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        CargoAPI re = a.getCargoForInteractionMode(mode);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCargoTitleForGatheringPoint(){
        MarketRetrofits_Logger.logging("running class: getCargoTitleForGatheringPoint()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getCargoTitleForGatheringPoint();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCurrentImage(){
        MarketRetrofits_Logger.logging("running class: getCurrentImage()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getCurrentImage();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getCurrentName(){
        MarketRetrofits_Logger.logging("running class: getCurrentName()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getCurrentName();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getDeficitMult(java.lang.String... commodities){
        MarketRetrofits_Logger.logging("running class: getDeficitMult(java.lang.String... commodities)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getDeficitMult(commodities);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableCommodityQuantity	getDemand(java.lang.String id){
        MarketRetrofits_Logger.logging("running class: getDemand(java.lang.String id)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        MutableCommodityQuantity re = a.getDemand(id);;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getDemandReduction(){
        MarketRetrofits_Logger.logging("running class: getDemandReduction()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        MutableStat re = a.getDemandReduction();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getDemandReductionFromOther(){
        MarketRetrofits_Logger.logging("running class: getDemandReductionFromOther()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        MutableStat re = a.getDemandReductionFromOther();;
        getDataFromInstance(a);
        return re;
    }

    @Override
    public java.lang.String	getDescriptionOverride(){
        MarketRetrofits_Logger.logging("running class: getDescriptionOverride()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getDescriptionOverride();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getDisruptedDays(){
        MarketRetrofits_Logger.logging("running class: getDisruptedDays()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getDisruptedDays();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getDisruptedKey(){
        MarketRetrofits_Logger.logging("running class: getDisruptedKey()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getDisruptedKey();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String getId(){
        MarketRetrofits_Logger.logging("running class: getId()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getId();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getImproveBonusXP(){
        MarketRetrofits_Logger.logging("running class: getImproveBonusXP()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        float re = a.getImproveBonusXP();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveDialogTitle(){
        MarketRetrofits_Logger.logging("running class: getImproveDialogTitle()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        String re = a.getImproveDialogTitle();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImprovementsDescForModifiers(){
        MarketRetrofits_Logger.logging("running class: getImprovementsDescForModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        String re = a.getImprovementsDescForModifiers();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveMenuText(){
        MarketRetrofits_Logger.logging("running class: getImproveMenuText()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getImproveMenuText();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getImproveProductionBonus(){
        MarketRetrofits_Logger.logging("running class: getImproveProductionBonus()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        int re = a.getImproveProductionBonus();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getImproveSoundId(){
        MarketRetrofits_Logger.logging("running class: getImproveSoundId()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getImproveSoundId();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getImproveStoryPoints(){
        MarketRetrofits_Logger.logging("running class: getImproveStoryPoints()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        int re = a.getImproveStoryPoints();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getIncome(){
        MarketRetrofits_Logger.logging("running class: getIncome()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableStat re = a.getIncome();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<InstallableIndustryItemPlugin>	getInstallableItems(){
        MarketRetrofits_Logger.logging("running class: getInstallableItems()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        List<InstallableIndustryItemPlugin> re = a.getInstallableItems();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MarketAPI getMarket(){
        MarketRetrofits_Logger.logging("running class: getMarket()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        MarketAPI re = a.getMarket();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getMaxDeficit(java.lang.String... commodityIds){
        MarketRetrofits_Logger.logging("running class: getMaxDeficit(java.lang.String... commodityIds)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        Pair<String, Integer> re = a.getMaxDeficit(commodityIds);;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getModId(){
        MarketRetrofits_Logger.logging("running class: getModId()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getModId();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getModId(int index){
        MarketRetrofits_Logger.logging("running class: getModId(int index)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getModId(index);;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getNameForModifier(){
        MarketRetrofits_Logger.logging("running class: getNameForModifier()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        String re = a.getNameForModifier();;
        getDataFromInstance(a);
        return re;

    }
    @Override
    public float	getPatherInterest(){
        MarketRetrofits_Logger.logging("running class: getPatherInterest()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getPatherInterest();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getSizeMult(){
        MarketRetrofits_Logger.logging("running class: getSizeMult()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        float re = a.getSizeMult();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public IndustrySpecAPI	getSpec(){
        MarketRetrofits_Logger.logging("running class: getSpec()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        IndustrySpecAPI re = a.getSpec();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public SpecialItemData	getSpecialItem(){
        MarketRetrofits_Logger.logging("running class: getSpecialItem()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        SpecialItemData re = a.getSpecialItem();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public Pair<java.lang.String,java.lang.Integer>	getStabilityAffectingDeficit(){
        MarketRetrofits_Logger.logging("running class: getStabilityAffectingDeficit()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        Pair<String, Integer> re = a.getStabilityAffectingDeficit();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public int	getStabilityPenalty(){
        MarketRetrofits_Logger.logging("running class: getStabilityPenalty()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        int re = a.getStabilityPenalty();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableCommodityQuantity	getSupply(java.lang.String id){
        MarketRetrofits_Logger.logging("running class: getSupply(java.lang.String id)",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableCommodityQuantity re = a.getSupply(id);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonus(){
        MarketRetrofits_Logger.logging("running class: getSupplyBonus()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableStat re = a.getSupplyBonus();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getSupplyBonusFromOther() {
        MarketRetrofits_Logger.logging("running class: getSupplyBonusFromOther()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableStat re = a.getSupplyBonusFromOther();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public float	getTooltipWidth(){
        MarketRetrofits_Logger.logging("running class: getTooltipWidth()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        float re = a.getTooltipWidth();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.lang.String	getUnavailableReason(){
        MarketRetrofits_Logger.logging("running class: getUnavailableReason()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        String re = a.getUnavailableReason();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public MutableStat	getUpkeep(){
        MarketRetrofits_Logger.logging("running class: getUpkeep()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        MutableStat re = a.getUpkeep();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public java.util.List<SpecialItemData>	getVisibleInstalledItems(){
        MarketRetrofits_Logger.logging("running class: getVisibleInstalledItems()",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        List<SpecialItemData> re = a.getVisibleInstalledItems();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode){
        MarketRetrofits_Logger.logging("running class: hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode)",this, BaseIndustryLogs);
    MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.hasPostDemandSection(hasDemand, mode);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	init(java.lang.String idT, MarketAPI marketT){
        MarketRetrofits_Logger.logging("running class: init(java.lang.String idT, MarketAPI marketT)",this, BaseIndustryLogs);
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
        MarketRetrofits_Logger.logging("running class: initWithParams(java.util.List<java.lang.String> params)",this, BaseIndustryLogs);
        //super.initWithParams(params);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.initWithParams(params);
        getDataFromInstance(a);

    }
    @Override
    //Used when loading market from an economy .json file.
    public boolean	isAICoreId(java.lang.String str){
        MarketRetrofits_Logger.logging("running class: isAICoreId(java.lang.String str)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.isAICoreId(str);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isAvailableToBuild(){
        MarketRetrofits_Logger.logging("running class: isAvailableToBuild()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        boolean re = a.isAvailableToBuild();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isBuilding(){
        MarketRetrofits_Logger.logging("running class: isBuilding()",this, BaseIndustryLogs);
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
        MarketRetrofits_Logger.logging("running class: isDemandLegal(CommodityOnMarketAPI com)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.isDemandLegal(com);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isDisrupted(){
        MarketRetrofits_Logger.logging("running class: isDisrupted()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        boolean re = a.isDisrupted();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isFunctional(){
        MarketRetrofits_Logger.logging("running class: isFunctional()",this, BaseIndustryLogs);
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
        MarketRetrofits_Logger.logging("running class: isHidden()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        boolean re = a.isHidden();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isImproved(){
        MarketRetrofits_Logger.logging("running class: isImproved()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isImproved();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isIndustry(){
        MarketRetrofits_Logger.logging("running class: isIndustry()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isIndustry();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isOther(){
        MarketRetrofits_Logger.logging("running class: isOther()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isOther();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isStructure(){
        MarketRetrofits_Logger.logging("running class: isStructure()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isStructure();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isSupplyLegal(CommodityOnMarketAPI com){
        MarketRetrofits_Logger.logging("running class: isSupplyLegal(CommodityOnMarketAPI com)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.isSupplyLegal(com);
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isTooltipExpandable(){
        MarketRetrofits_Logger.logging("running class: isTooltipExpandable()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.isTooltipExpandable();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	isUpgrading(){
        MarketRetrofits_Logger.logging("running class: isUpgrading()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        boolean re = a.isUpgrading();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    //Upgrading, but not the initial building process.
    public void	modifyStabilityWithBaseMod(){
        MarketRetrofits_Logger.logging("running class: modifyStabilityWithBaseMod()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.modifyStabilityWithBaseMod();
        getDataFromInstance(a);
    }
    @Override
    public void	notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade){
        MarketRetrofits_Logger.logging("running class: notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.notifyBeingRemoved(mode,forUpgrade);
        getDataFromInstance(a);
    }
    @Override
    public void	notifyColonyRenamed(){
        MarketRetrofits_Logger.logging("running class: notifyColonyRenamed()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.notifyColonyRenamed();
        getDataFromInstance(a);
    }
    @Override
    public void	notifyDisrupted(){
        MarketRetrofits_Logger.logging("running class: notifyDisrupted()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.notifyDisrupted();
        getDataFromInstance(a);
    }
    @Override
    public java.lang.Object	readResolve(){
        MarketRetrofits_Logger.logging("running class: readResolve()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        Object re = a.readResolve();;
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	reapply(){
        MarketRetrofits_Logger.logging("running class: reapply()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.reapply();
        getDataFromInstance(a);
        resetActiveInstanceIfRequired();//HERE. reseting some random data hopefully?
    }
    @Override
    public void	sendBuildOrUpgradeMessage(){
        MarketRetrofits_Logger.logging("running class: sendBuildOrUpgradeMessage()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.sendBuildOrUpgradeMessage();
        getDataFromInstance(a);
    }
    @Override
    public void	setAICoreId(java.lang.String aiCoreId){
        MarketRetrofits_Logger.logging("running class: setAICoreId(java.lang.String aiCoreId)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.setAICoreId(aiCoreId);
        getDataFromInstance(a);
    }
    @Override
    public void	setBuildCostOverride(float buildCostOverride){
        MarketRetrofits_Logger.logging("running class: setBuildCostOverride(float buildCostOverride)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //APPLY SUPER FROM CLASS AS DEFAULT
        applyDataToInstance(a);
        a.setBuildCostOverride(buildCostOverride);
        getDataFromInstance(a);
    }
    @Override
    public void	setBuildProgress(float buildProgress){
        MarketRetrofits_Logger.logging("running class: setBuildProgress(float buildProgress)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setBuildProgress(buildProgress);
        getDataFromInstance(a);
    }
    @Override
    public void	setDisrupted(float days){
        MarketRetrofits_Logger.logging("running class: setDisrupted(float days)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setDisrupted(days);
        getDataFromInstance(a);
    }
    @Override
    public void	setDisrupted(float days, boolean useMax){
        MarketRetrofits_Logger.logging("running class: setDisrupted(float days, boolean useMax)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setDisrupted(days,useMax);
        getDataFromInstance(a);
    }

    @Override
    public void	setHidden(boolean hidden){
        MarketRetrofits_Logger.logging("running class: setHidden(boolean hidden)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setHidden(hidden);
        getDataFromInstance(a);
    }
    @Override
    public void	setImproved(boolean improved){
        MarketRetrofits_Logger.logging("running class: setImproved(boolean improved)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.setImproved(improved);
        getDataFromInstance(a);
    }
    @Override
    public void	setSpecialItem(SpecialItemData special){
        MarketRetrofits_Logger.logging("running class: setSpecialItem(SpecialItemData special)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.setSpecialItem(special);
        getDataFromInstance(a);
    }
    @Override
    public boolean	showShutDown(){
        MarketRetrofits_Logger.logging("running class: showShutDown()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //as intended
        boolean re = a.showShutDown();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public boolean	showWhenUnavailable(){
        MarketRetrofits_Logger.logging("running class: showWhenUnavailable()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        boolean re = a.showWhenUnavailable();
        getDataFromInstance(a);
        return re;
    }
    @Override
    public void	startBuilding(){
        MarketRetrofits_Logger.logging("running class: startBuilding()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.startBuilding();
        getDataFromInstance(a);
    }
    @Override
    public void	startUpgrading(){
        MarketRetrofits_Logger.logging("running class: startUpgrading()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.startUpgrading();
        getDataFromInstance(a);
    }
    @Override
    public void	supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_Logger.logging("running class: supply(int index, java.lang.String commodityId, int quantity, java.lang.String desc)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.supply(index,commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity){
        MarketRetrofits_Logger.logging("running class: supply(java.lang.String commodityId, int quantity)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.supply(commodityId,quantity);
        getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_Logger.logging("running class: supply(java.lang.String commodityId, int quantity, java.lang.String desc)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.supply(commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc){
        MarketRetrofits_Logger.logging("running class: supply(java.lang.String modId, java.lang.String commodityId, int quantity, java.lang.String desc)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.supply(modId,commodityId,quantity,desc);
        getDataFromInstance(a);
    }
    @Override
    public void	unapply(){
        MarketRetrofits_Logger.logging("running class: unapply()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.unapply();
        getDataFromInstance(a);
        resetActiveInstanceIfRequired();//HERE reseting some data hopefully.
    }
    @Override
    public void	unmodifyStabilityWithBaseMod(){
        MarketRetrofits_Logger.logging("running class: unmodifyStabilityWithBaseMod()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        a.unmodifyStabilityWithBaseMod();
        getDataFromInstance(a);

    }
    @Override
    public void	updateAICoreToSupplyAndDemandModifiers(){
        MarketRetrofits_Logger.logging("running class: updateAICoreToSupplyAndDemandModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateAICoreToSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	updateImprovementSupplyAndDemandModifiers(){
        MarketRetrofits_Logger.logging("running class: updateImprovementSupplyAndDemandModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateImprovementSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	updateIncomeAndUpkeep(){
        MarketRetrofits_Logger.logging("running class: updateIncomeAndUpkeep()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateIncomeAndUpkeep();
        getDataFromInstance(a);
    }
    @Override
    public void	updateSupplyAndDemandModifiers(){
        MarketRetrofits_Logger.logging("running class: updateSupplyAndDemandModifiers()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.updateSupplyAndDemandModifiers();
        getDataFromInstance(a);
    }
    @Override
    public void	upgradeFinished(Industry previous){
        MarketRetrofits_Logger.logging("running class: upgradeFinished(Industry previous)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //APPLY SUPER FROM CLASS AS DEFAULT
        a.upgradeFinished(previous);
        getDataFromInstance(a);
    }
    @Override
    public boolean	wantsToUseSpecialItem(SpecialItemData data){
        MarketRetrofits_Logger.logging("running class: wantsToUseSpecialItem(SpecialItemData data)",this, BaseIndustryLogs);
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
        MarketRetrofits_Logger.logging("running class: writeReplace()",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        Object re = a.writeReplace();;
        getDataFromInstance(a);
        return re;
    }

    //HERE apply all implementations
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        MarketRetrofits_Logger.logging("running class: modifyIncoming(MarketAPI market, PopulationComposition incoming)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        applyDataToInstance(a);
        //as intended
        a.modifyIncoming(market,incoming);
        getDataFromInstance(a);
    }

    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        MarketRetrofits_Logger.logging("running class: reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.reportFleetDespawnedToListener(fleet,reason,param);
        getDataFromInstance(a);
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {
        MarketRetrofits_Logger.logging("running class: reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.reportBattleOccurred(fleet,primaryWinner,battle);
        getDataFromInstance(a);

    }

    @Override
    public CampaignFleetAPI spawnFleet(RouteManager.RouteData route) {
        MarketRetrofits_Logger.logging("running class: spawnFleet(RouteManager.RouteData route)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        CampaignFleetAPI re = a.spawnFleet(route);
        getDataFromInstance(a);
        return re;
    }

    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route) {
        MarketRetrofits_Logger.logging("running class: shouldCancelRouteAfterDelayCheck(RouteManager.RouteData route)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.shouldCancelRouteAfterDelayCheck(route);
        getDataFromInstance(a);
        return re;
    }

    @Override
    public boolean shouldRepeat(RouteManager.RouteData route) {
        MarketRetrofits_Logger.logging("running class: shouldRepeat(RouteManager.RouteData route)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        boolean re = a.shouldRepeat(route);
        getDataFromInstance(a);
        return re;
    }

    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route) {
        MarketRetrofits_Logger.logging("running class: reportAboutToBeDespawnedByRouteManager(RouteManager.RouteData route)",this, BaseIndustryLogs);
        MarketRetrofits_DefaltInstanceIndustry a = getActiveInstance();
        //as intended
        applyDataToInstance(a);
        a.reportAboutToBeDespawnedByRouteManager(route);
        getDataFromInstance(a);
    }
}
