package data.scripts.industries;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.comm.CommMessageAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.InstallableIndustryItemPlugin;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketImmigrationModifier;
import com.fs.starfarer.api.campaign.econ.MutableCommodityQuantity;
import com.fs.starfarer.api.campaign.impl.items.GenericSpecialItemPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.DebugFlags;
import com.fs.starfarer.api.impl.campaign.econ.impl.*;
import com.fs.starfarer.api.impl.campaign.fleets.RouteManager;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.ids.Sounds;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.ids.Strings;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.intel.BaseIntelPlugin;
import com.fs.starfarer.api.impl.campaign.intel.MessageIntel;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import com.fs.starfarer.api.ui.Alignment;
import com.fs.starfarer.api.ui.IconRenderMode;
import com.fs.starfarer.api.ui.LabelAPI;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.industries.Lists.MarketRetrofits_IndustryList;
import data.scripts.industries.Lists.MarketRetrofits_IndustryMasterList;
import data.scripts.industries.base.MarketRetrofit_IndustryDataExstange;
//import sun.jvm.hotspot.oops.Mark;

public class MarketRetrofits_DefaltInstanceIndustry extends MarketRetrofit_IndustryDataExstange {
/*
    public static int SIZE_FOR_SMALL_IMAGE = 3;
    public static int SIZE_FOR_LARGE_IMAGE = 6;

    public static float UPKEEP_MULT = 0.75f;
    public static int DEMAND_REDUCTION = 1;
    public static int SUPPLY_BONUS = 1;

    public static int DEFAULT_IMPROVE_SUPPLY_BONUS = 1;
    public static final String BASE_VALUE_TEXT = "Base value for colony size";*/

    /*this is the class that an industry would extend, if and only if said industry wants to be the default industry for an industry type.*/
    public String InstanceID;
    public float order;
    public MarketRetrofits_IndustryList industryGroup = null;
    //public MarketRetrofit_BaseIndustry CurrentIndustry;
    public MarketRetrofits_DefaltInstanceIndustry CloneInstance(){
        return (MarketRetrofits_DefaltInstanceIndustry) this.clone();
    }
    public void IndustryDataCleanup(MarketRetrofit_IndustryDataExstange industryT){
        setBaseDataToIndustry(industryT);
        setExtraDataToIndustry(industryT.getExstraData());
        setOtherDataIternal(industryT);
        readData("  set instance data to:");
        //MarketRetrofits_Logger.logging("IndustryDataCleanup: reading data after cleanup",this);
        //readData("");
    }
    //secondary, so i dont have to set a CurrentIndustry in BaseIndustry
    public void IndustryDataGet(MarketRetrofit_IndustryDataExstange industryT,MarketRetrofit_BaseIndustry CurrentIndustryT){
        getBaseDataFromIndustry(industryT);
        getExtraDataFromIndustry(industryT.getExstraData());
        getOtherDataIternal(industryT.getExstraData(),CurrentIndustryT);
        readData("  set instance data to:");
        //MarketRetrofits_Logger.logging("IndustryDataGet: reading data after getting said data",this);
        //readData("");
    }
    //primary, for other instances.
    public void IndustryDataGet(MarketRetrofit_IndustryDataExstange industryT){
        IndustryDataGet(industryT,industryT.CurrentIndustry);
        /*
        getBaseDataFromIndustry(industryT);
        getExtraDataFromIndustry(industryT.getExstraData());
        getOtherDataIternal(industryT.getExstraData(),industryT.CurrentIndustry);*/
    }

    public void getExtraDataFromIndustry(MarketRetorfits_ExstraData extraData){
        //interface thing
    }
    public void setExtraDataToIndustry(MarketRetorfits_ExstraData extraData){
        //interface thing
    }
    private void getOtherDataIternal(MarketRetorfits_ExstraData extraDataT, MarketRetrofit_BaseIndustry CurrentIndustryT){
        exstraData = extraDataT;
        CurrentIndustry = CurrentIndustryT;
    }
    private void setOtherDataIternal(MarketRetrofit_IndustryDataExstange industryT){
        industryT.exstraData = exstraData;
        industryT.CurrentIndustry = CurrentIndustry;
    }
    public MarketRetrofit_BaseIndustry getIndustry() {
        return this.CurrentIndustry;//why does this exsist?
    }

    public MarketRetrofits_DefaltInstanceIndustry(String name, float orderT) {
        //super(name,orderT);
        InstanceID = name;
        order = orderT;
    }

    //@Override

    public void applyToIndustry(String industry) {
        //apply to industry here.
        MarketRetrofits_IndustryMasterList.setDefaltInstance(industry, this);
        industryGroup = MarketRetrofits_IndustryMasterList.getInstance(industry);
    }

    //this class requires every single function held in baseIndustry, as well as an an function to determon if its active or not.
    public boolean Active = true;

    public boolean canImply(MarketAPI market,MarketRetrofit_BaseIndustry industry) {
        return Active;
    }

    /*base code from here on out*/

    public static String getDeficitText(String commodityId) {
        //no data to overwrite
        if (commodityId == null) {
            return "Various shortages";
        }
        CommoditySpecAPI spec = Global.getSettings().getCommoditySpec(commodityId);
        return Misc.ucFirst(spec.getName().toLowerCase() + " shortage");
    }

    // want to have some ability to add random supply/demand to industries
    // e.g. market condition adding Volturnian Lobster supply to Volturn's Farming/Aquaculture
    /*
    public Map<String, MutableCommodityQuantity> supply = new LinkedHashMap<String, MutableCommodityQuantity>();
    public Map<String, MutableCommodityQuantity> demand = new LinkedHashMap<String, MutableCommodityQuantity>();

    public MutableStat income = new MutableStat(0f);
    public MutableStat upkeep = new MutableStat(0f);
    public MarketAPI market;

    public String id;

    public float buildProgress = 0f;
    public float buildTime = 1f;
    public boolean building = false;
    public Boolean improved = null;
    public String upgradeId = null;

    public transient IndustrySpecAPI spec = null;

    public String aiCoreId = null;
//	public int demandReduction = 0;
//	public int supplyBonus = 0;

    public MutableStat demandReduction = new MutableStat(0);
    public MutableStat supplyBonus = new MutableStat(0);

    public transient MutableStat demandReductionFromOther = new MutableStat(0);
    public transient MutableStat supplyBonusFromOther = new MutableStat(0);*/

    @Override
    public MutableStat getDemandReduction() {
        //DATA CHANGE
        return (MutableStat) demandReduction;
    }
    @Override
    public MutableStat getSupplyBonus() {
        //DATA CHANGE
        return (MutableStat) supplyBonus;
    }
    @Override
    public MutableStat getDemandReductionFromOther() {
        //DATA CHANGE

        if (demandReductionFromOther == null) {
            //industry.setData("demandReductionFromOther",new MutableStat(0));
            demandReductionFromOther = new MutableStat(0);
        }
        return demandReductionFromOther;
    }
    @Override
    public MutableStat getSupplyBonusFromOther() {
        //DATA CHANGE
        if (supplyBonusFromOther == null) {
            //industry.setData("supplyBonusFromOther",new MutableStat(0));
            supplyBonusFromOther = new MutableStat(0);
        }
        return supplyBonusFromOther;
    }
    @Override
    public void init(String id, MarketAPI market) {
        //DATA CHANGE
        //industry.setData("id",id);
        this.id = id;
        this.market = market;
        CurrentIndustry.readResolve();
    }

    //private transient String modId;
    //private transient String [] modIds;
    @Override
    public Object readResolve() {
        spec = Global.getSettings().getIndustrySpec(id);

        if (buildTime < 1f) buildTime = 1f;

        modId = "ind_" + id;
        modIds = new String[10];
        for (int i = 0; i < modIds.length; i++) {
            modIds[i] = modId + "_" + i;
        }

        if (demandReduction == null) demandReduction = new MutableStat(0);
        if (supplyBonus == null) supplyBonus = new MutableStat(0);

        if (supply != null) {
            for (String id : new ArrayList<String>(supply.keySet())) {
                MutableCommodityQuantity stat = supply.get(id);
                stat.getQuantity().unmodifyFlat("ind_sb");
            }
        }
        if (demand != null) {
            for (String id : new ArrayList<String>(demand.keySet())) {
                MutableCommodityQuantity stat = demand.get(id);
                stat.getQuantity().unmodifyFlat("ind_dr");
            }
        }
        setBaseDataToIndustry(CurrentIndustry);
        return CurrentIndustry;
    }
    @Override
    public Object writeReplace() {
//		BaseIndustry copy = clone();
//		copy.supply = null;
//		copy.demand = null;
//		return copy;
        CurrentIndustry.clearUnmodified();
        setBaseDataToIndustry(CurrentIndustry);
        return CurrentIndustry;    }

    @Override
    public void apply(boolean withIncomeUpdate) {
        CurrentIndustry.updateSupplyAndDemandModifiers();

        if (withIncomeUpdate) {
            CurrentIndustry.updateIncomeAndUpkeep();
        }

        CurrentIndustry.applyAICoreModifiers();
        CurrentIndustry.applyImproveModifiers();

        if (CurrentIndustry instanceof MarketImmigrationModifier) {
            market.addTransientImmigrationModifier((MarketImmigrationModifier) CurrentIndustry);
        }

        if (special != null) {
            InstallableItemEffect effect = ItemEffectsRepo.ITEM_EFFECTS.get(special.getId());
            if (effect != null) {
                List<String> unmet = effect.getUnmetRequirements(CurrentIndustry);
                if (unmet == null || unmet.isEmpty()) {
                    effect.apply(CurrentIndustry);
                } else {
                    effect.unapply(CurrentIndustry);
                }
            }
        }
    }
    @Override
    public void unapply() {
        CurrentIndustry.applyNoAICoreModifiers();

        Boolean wasImproved = improved;
        improved = null;
        CurrentIndustry.applyImproveModifiers(); // to unapply them
        improved = wasImproved;

        if (CurrentIndustry instanceof MarketImmigrationModifier) {
            market.removeTransientImmigrationModifier((MarketImmigrationModifier) CurrentIndustry);
        }

        if (special != null) {
            InstallableItemEffect effect = ItemEffectsRepo.ITEM_EFFECTS.get(special.getId());
            if (effect != null) {
                effect.unapply(CurrentIndustry);
            }
        }
    }
    @Override
    public void applyAICoreModifiers() {
        if (aiCoreId == null) {
            CurrentIndustry.applyNoAICoreModifiers();
            return;
        }
        boolean alpha = aiCoreId.equals(Commodities.ALPHA_CORE);
        boolean beta = aiCoreId.equals(Commodities.BETA_CORE);
        boolean gamma = aiCoreId.equals(Commodities.GAMMA_CORE);
        if (alpha) CurrentIndustry.applyAlphaCoreModifiers();
        else if (beta) CurrentIndustry.applyBetaCoreModifiers();
        else if (gamma) CurrentIndustry.applyGammaCoreModifiers();
    }
    @Override
    public void applyAlphaCoreModifiers() {}
    @Override
    public void applyBetaCoreModifiers() {}
    @Override
    public void applyGammaCoreModifiers() {}
    @Override
    public void applyNoAICoreModifiers() {}


    @Override
    public String getModId() {
        //return "ind_" + id;
        //MarketRetrofits_Logger.logging("my mod id outputed is: " + modId,this);
        return modId;
    }
    @Override
    public String getModId(int index) {
        //return "ind_" + id + "_" + index;
        //MarketRetrofits_Logger.logging("my mod id outputed is: " + modIds[index],this);
        return modIds[index];
    }
    @Override
    public void demand(String commodityId, int quantity) {
        CurrentIndustry.demand(0, commodityId, quantity, BASE_VALUE_TEXT);
    }
    @Override
    public void demand(String commodityId, int quantity, String desc) {
        CurrentIndustry.demand(0, commodityId, quantity, desc);
    }
    @Override
    public void demand(int index, String commodityId, int quantity, String desc) {
        CurrentIndustry.demand(CurrentIndustry.getModId(index), commodityId, quantity, desc);
    }
    @Override
    public void demand(String modId, String commodityId, int quantity, String desc) {
//		if (commodityId != null && commodityId.equals("organics") && getId().contains("military")) {
//			System.out.println("wefwefwe");
//		}
        //quantity -= demandReduction;
        // want to apply negative numbers here so they add up with anything coming in from market conditions
        if (quantity == 0) {
            CurrentIndustry.getDemand(commodityId).getQuantity().unmodifyFlat(modId);
        } else {
            CurrentIndustry.getDemand(commodityId).getQuantity().modifyFlat(modId, quantity, desc);
        }

        if (quantity > 0) {
            if (!demandReduction.isUnmodified()) {
                CurrentIndustry.getDemand(commodityId).getQuantity().modifyFlat("ind_dr", -demandReduction.getModifiedInt());
            } else {
                CurrentIndustry.getDemand(commodityId).getQuantity().unmodifyFlat("ind_dr");
            }
        }
    }
    @Override
    public void supply(String commodityId, int quantity) {
        CurrentIndustry.supply(0, commodityId, quantity, BASE_VALUE_TEXT);
    }
    @Override
    public void supply(String commodityId, int quantity, String desc) {
        CurrentIndustry.supply(0, commodityId, quantity, desc);
    }
    @Override
    public void supply(int index, String commodityId, int quantity, String desc) {
        CurrentIndustry.supply(CurrentIndustry.getModId(index), commodityId, quantity, desc);
    }
    @Override
    public void supply(String modId, String commodityId, int quantity, String desc) {
//		if (this instanceof Mining && market.getName().equals("Medea") &&
//				Commodities.VOLATILES.equals(commodityId)) {
//			System.out.println("efwefwe");
//		}

        //quantity += supplyBonus; doesn't work gets applied multiple times potentially
        // want to apply negative numbers here so they add up with anything coming in from market conditions
        if (quantity == 0) {
            CurrentIndustry.getSupply(commodityId).getQuantity().unmodifyFlat(modId);
        } else {
            CurrentIndustry.getSupply(commodityId).getQuantity().modifyFlat(modId, quantity, desc);
        }

        if (quantity > 0) {
            //if (!getSupply(commodityId).getQuantity().isUnmodified()) {
            if (!supplyBonus.isUnmodified()) {
                CurrentIndustry.getSupply(commodityId).getQuantity().modifyFlat("ind_sb", supplyBonus.getModifiedInt());
            } else {
                CurrentIndustry.getSupply(commodityId).getQuantity().unmodifyFlat("ind_sb");
            }
        }
        //getSupply(commodityId).getQuantity().unmodifyFlat("ind_sb");
    }
    @Override
    public void applyDeficitToProduction(int index, Pair<String, Integer> deficit, String ... commodities) {
        for (String commodity : commodities) {
//			if (this instanceof Mining && market.getName().equals("Louise")) {
//				System.out.println("efwefwe");
//			}
            if (CurrentIndustry.getSupply(commodity).getQuantity().isUnmodified()) continue;
            CurrentIndustry.supply(index, commodity, -deficit.two, CurrentIndustry.getDeficitText(deficit.one));
        }
    }

//	public static float getIncomeStabilityMult(float stability) {
//		if (stability <= 5) {
//			return Math.max(0, stability / 5f);
//		}
//		return 1f + (stability - 5f) * .1f;
//	}
@Override
    public void updateIncomeAndUpkeep() {
        CurrentIndustry.applyIncomeAndUpkeep(-1);
    }
    @Override
    public void applyIncomeAndUpkeep(float sizeOverride) {
        float size = market.getSize();
        if (sizeOverride >= 0) size = sizeOverride;
        float sizeMult = CurrentIndustry.getSizeMult(size);
        sizeMult = Math.max(1, sizeMult - 2);

        float stabilityMult = market.getIncomeMult().getModifiedValue();
        float upkeepMult = market.getUpkeepMult().getModifiedValue();
//		if (hazardMultOverride >= 0) {
//			upkeepMult = hazardMultOverride;
//		}
        //MarketRetrofits_Logger.logging("running applyIncomeAndUpkeep...",this);
        //MarketRetrofits_Logger.logging("    CurentIndustry: " + CurrentIndustry,this);
        //MarketRetrofits_Logger.logging("    CurrentIndustry.getSpec(): " + CurrentIndustry.getSpec(),this);
        //MarketRetrofits_Logger.logging("    second test CurrentIndustry.getSpec(): " + CurrentIndustry.getSpec(),this);
        //MarketRetrofits_Logger.logging("    CurrentIndustry.getSpec().getIncome(): " + CurrentIndustry.getSpec().getIncome(),this);
        int income = (int) (CurrentIndustry.getSpec().getIncome() * sizeMult);
        if (income != 0) {
            CurrentIndustry.getIncome().modifyFlatAlways("ind_base", income, "Base value");
            CurrentIndustry.getIncome().modifyMultAlways("ind_stability", stabilityMult, "Market income multiplier");
        } else {
            CurrentIndustry.getIncome().unmodifyFlat("ind_base");
            CurrentIndustry.getIncome().unmodifyMult("ind_stability");
        }


        int upkeep = (int) (CurrentIndustry.getSpec().getUpkeep() * sizeMult);
        if (upkeep != 0) {
            CurrentIndustry.getUpkeep().modifyFlatAlways("ind_base", upkeep, "Base value");
            CurrentIndustry.getUpkeep().modifyMultAlways("ind_hazard", upkeepMult, "Market upkeep multiplier");
        } else {
            CurrentIndustry.getUpkeep().unmodifyFlat("ind_base");
            CurrentIndustry.getUpkeep().unmodifyMult("ind_hazard");
        }

        CurrentIndustry.applyAICoreToIncomeAndUpkeep();

        if (!CurrentIndustry.isFunctional()) {
            CurrentIndustry.getIncome().unmodifyFlat("ind_base");
            CurrentIndustry.getIncome().unmodifyMult("ind_stability");
        }
    }

//	public static float getUpkeepMult(MarketAPI market) {
//		return getUpkeepHazardMult(market.getHazardValue());
//	}
//	public static float getUpkeepHazardMult(float hazard) {
//		float hazardMult = hazard;
//		if (hazardMult < 0) hazardMult = 0;
//		return hazardMult;
//	}
@Override
    public float getBuildTime() {
        return CurrentIndustry.getSpec().getBuildTime();
    }

    //public Float buildCostOverride = null;
    @Override
    public Float getBuildCostOverride() {
        return buildCostOverride;
    }
    @Override
    public void setBuildCostOverride(float buildCostOverride) {
        this.buildCostOverride = buildCostOverride;
    }
    @Override
    public float getBuildCost() {
        if (buildCostOverride != null) return buildCostOverride;
        return CurrentIndustry.getSpec().getCost();
    }
    @Override
    public float getBaseUpkeep() {
        float size = market.getSize();
        float sizeMult = CurrentIndustry.getSizeMult(size);
        sizeMult = Math.max(1, sizeMult - 2);
        return CurrentIndustry.getSpec().getUpkeep() * sizeMult;
    }

//	public float getActualUpkeep() {
//		return getBaseUpkeep() * market.getUpkeepMult().getModifiedValue();
//	}

    //public boolean wasDisrupted = false;
    @Override
    public void advance(float amount) {
        boolean disrupted = CurrentIndustry.isDisrupted();
        if (!disrupted && wasDisrupted) {
            CurrentIndustry.disruptionFinished();
        }
        wasDisrupted = disrupted;

//		if (disrupted) {
//			//if (DebugFlags.COLONY_DEBUG) {
//				String key = getDisruptedKey();
//				market.getMemoryWithoutUpdate().unset(key);
//			//}
//		}

        if (building && !disrupted) {
            float days = Global.getSector().getClock().convertToDays(amount);
            //DebugFlags.COLONY_DEBUG = true;
            if (DebugFlags.COLONY_DEBUG) {
                days *= 100f;
            }
            buildProgress += days;

            if (buildProgress >= buildTime) {
                CurrentIndustry.finishBuildingOrUpgrading();
            }
        }

    }
    @Override
    public void notifyDisrupted() {

    }
    @Override
    public void disruptionFinished() {

    }
    @Override
    public boolean isBuilding() {
        return building;
    }
    @Override
    public boolean isFunctional() {
        if (CurrentIndustry.isDisrupted()) return false;
        return !(CurrentIndustry.isBuilding() && !CurrentIndustry.isUpgrading());
    }
    @Override
    public boolean isUpgrading() {
        return building && upgradeId != null;
    }
    @Override
    public float getBuildOrUpgradeProgress() {
        if (CurrentIndustry.isDisrupted()) {
            return 0f;
        }
        if (!CurrentIndustry.isBuilding()) return 0f;

        return Math.min(1f, buildProgress / buildTime);
    }
    @Override
    public String getBuildOrUpgradeDaysText() {
        if (CurrentIndustry.isDisrupted()) {
            int left = (int) CurrentIndustry.getDisruptedDays();
            if (left < 1) left = 1;
            String days = "days";
            if (left == 1) days = "day";

            return "" + left + " " + days + "";
        }

        int left = (int) (buildTime - buildProgress);
        if (left < 1) left = 1;
        String days = "days";
        if (left == 1) days = "day";

        return left + " " + days;
    }
    @Override
    public String getBuildOrUpgradeProgressText() {
        if (CurrentIndustry.isDisrupted()) {
            int left = (int) CurrentIndustry.getDisruptedDays();
            if (left < 1) left = 1;
            String days = "days";
            if (left == 1) days = "day";

            return "Disrupted: " + left + " " + days + " left";
        }

        int left = (int) (buildTime - buildProgress);
        if (left < 1) left = 1;
        String days = "days";
        if (left == 1) days = "day";

//		if (isBuilding() && !isUpgrading()) {
//			//return left + " " + days;
//			return "building: " + (int)Math.round(buildProgress / buildTime * 100f) + "%";
//		}

        if (CurrentIndustry.isUpgrading()) {
            return "Upgrading: " + left + " " + days + " left";
        } else {
            return "Building: " + left + " " + days + " left";
        }
    }
    @Override
    public void startBuilding() {
        building = true;
        buildProgress = 0;
        upgradeId = null;

        buildTime = spec.getBuildTime();
        CurrentIndustry.unapply();
    }
    @Override
    public void finishBuildingOrUpgrading() {
        building = false;
        buildProgress = 0;
        buildTime = 1f;

        //MarketRetrofits_Logger.logging("getting data for finished building...",this);
        //MarketRetrofits_Logger.logging("pre upgradeId is: " + upgradeId,this);
        if (upgradeId != null) {
            market.removeIndustry(CurrentIndustry.getId(), null, true);
            /*MarketRetrofits_Logger.logging("ID is: " + CurrentIndustry.getId(),this);
            MarketRetrofits_Logger.logging("market: " + market.getName(),this);
            MarketRetrofits_Logger.logging("upgradeId is: " + upgradeId,this);
            if(upgradeId == null){
                MarketRetrofits_Logger.logging(" == nothing",this);
            }
            if(upgradeId.equals(null)){
                MarketRetrofits_Logger.logging(" .equals nothing",this);
            }
            if(upgradeId.equals("null")){
                MarketRetrofits_Logger.logging(" .eqyals null",this);
            }*/
            market.addIndustry(upgradeId);
            MarketRetrofit_BaseIndustry industryTemp = (MarketRetrofit_BaseIndustry) market.getIndustry(upgradeId);//HERE. this was BaseIndustry
            industryTemp.setAICoreId(getAICoreId());
            industryTemp.setImproved(CurrentIndustry.isImproved());
            industryTemp.upgradeFinished(CurrentIndustry);
            industryTemp.reapply();
        } else {
            CurrentIndustry.buildingFinished();
            CurrentIndustry.reapply();
        }
    }
    @Override
    public void startUpgrading() {
        building = true;
        buildProgress = 0;
        upgradeId = CurrentIndustry.getSpec().getUpgrade();


        IndustrySpecAPI upgrade = Global.getSettings().getIndustrySpec(upgradeId);
        buildTime = upgrade.getBuildTime();
    }
    @Override
    public void cancelUpgrade() {
        building = false;
        buildProgress = 0;
        upgradeId = null;
    }
    @Override
    public void downgrade() {
        building = true;
        buildProgress = 0;
        upgradeId = CurrentIndustry.getSpec().getDowngrade();
        CurrentIndustry.finishBuildingOrUpgrading();
    }

    @Override
    public void reapply() {
        CurrentIndustry.unapply();
        CurrentIndustry.apply();
    }
    @Override
    public void buildingFinished() {
        CurrentIndustry.sendBuildOrUpgradeMessage();
        CurrentIndustry.buildNextInQueue(market);
    }
    //@Override
    public static void buildNextInQueue(MarketAPI market) {
        ConstructionQueue.ConstructionQueueItem next = null;
        Iterator<ConstructionQueue.ConstructionQueueItem> iter = market.getConstructionQueue().getItems().iterator();
        while (iter.hasNext()) {
            next = iter.next();
            iter.remove();

            Industry ind = market.instantiateIndustry(next.id);
            int num = Misc.getNumIndustries(market);
            int max = Misc.getMaxIndustries(market);
            if (ind.isAvailableToBuild() && (num <= max || !ind.isIndustry())) { // <= because num includes what's queued
                break;
            } else {
                if (market.isPlayerOwned()) {
                    MessageIntel intel = new MessageIntel(ind.getCurrentName() + " at " + market.getName(), Misc.getBasePlayerColor());
                    intel.addLine(BaseIntelPlugin.BULLET + "Construction aborted");

                    int refund = next.cost;
                    Global.getSector().getPlayerFleet().getCargo().getCredits().add(refund);
                    intel.addLine(BaseIntelPlugin.BULLET + "%s refunded",
                            Misc.getTextColor(),
                            new String [] {Misc.getDGSCredits(refund)}, Misc.getHighlightColor());
                    intel.setIcon(Global.getSector().getPlayerFaction().getCrest());
                    intel.setSound(BaseIntelPlugin.getSoundStandardUpdate());
                    Global.getSector().getCampaignUI().addMessage(intel, CommMessageAPI.MessageClickAction.COLONY_INFO, market);
                }
                next = null;
            }
        }

        if (next != null) {
            market.addIndustry(next.id);
            Industry ind = market.getIndustry(next.id);
            ind.startBuilding();
            if (ind instanceof BaseIndustry) {
                ((BaseIndustry)ind).setBuildCostOverride(next.cost);
            }

            if (market.isPlayerOwned()) {
                MessageIntel intel = new MessageIntel(ind.getCurrentName() + " at " + market.getName(), Misc.getBasePlayerColor());
                intel.addLine(BaseIntelPlugin.BULLET + "Construction started");
                intel.setIcon(Global.getSector().getPlayerFaction().getCrest());
                intel.setSound(BaseIntelPlugin.getSoundStandardUpdate());
                Global.getSector().getCampaignUI().addMessage(intel, CommMessageAPI.MessageClickAction.COLONY_INFO, market);
            }
        }
    }
    @Override
    public void upgradeFinished(Industry previous) {
        CurrentIndustry.sendBuildOrUpgradeMessage();

        CurrentIndustry.setSpecialItem(previous.getSpecialItem());
    }
    @Override
    public void sendBuildOrUpgradeMessage() {
        if (market.isPlayerOwned()) {
            MessageIntel intel = new MessageIntel(CurrentIndustry.getCurrentName() + " at " + market.getName(), Misc.getBasePlayerColor());
            intel.addLine(BaseIntelPlugin.BULLET + "Construction completed");
            intel.setIcon(Global.getSector().getPlayerFaction().getCrest());
            intel.setSound(BaseIntelPlugin.getSoundStandardUpdate());
            Global.getSector().getCampaignUI().addMessage(intel, CommMessageAPI.MessageClickAction.COLONY_INFO, market);
        }
    }
    @Override
    public void notifyBeingRemoved(MarketAPI.MarketInteractionMode mode, boolean forUpgrade) {
        if (aiCoreId != null && !forUpgrade) {
            CargoAPI cargo = CurrentIndustry.getCargoForInteractionMode(mode);
            if (cargo != null) {
                cargo.addCommodity(aiCoreId, 1);
            }
        }

        if (special != null && !forUpgrade) {
            CargoAPI cargo = CurrentIndustry.getCargoForInteractionMode(mode);
            if (cargo != null) {
                cargo.addSpecial(special, 1);
            }
        }
    }
    @Override
    public CargoAPI getCargoForInteractionMode(MarketAPI.MarketInteractionMode mode) {
        CargoAPI cargo = null;
        if (mode == null) return null;

        if (mode == MarketAPI.MarketInteractionMode.REMOTE) {
            cargo = Misc.getStorageCargo(market);
        } else {
            cargo = Global.getSector().getPlayerFleet().getCargo();
        }
        return cargo;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public IndustrySpecAPI getSpec() {
        //MarketRetrofits_Logger.logging("getSpec...",this);
        //MarketRetrofits_Logger.logging("id gotting as: " + id,this);
        //MarketRetrofits_Logger.logging("spec scaned as: " + spec,this);
        //MarketRetrofits_Logger.logging("setting gotting as: " + Global.getSettings().getIndustrySpec(id),this);
        //MarketRetrofits_Logger.logging("spec is: " + spec,this);
        //MarketRetrofits_Logger.logging("id is: " + id,this);

        if (spec == null) spec = Global.getSettings().getIndustrySpec(id);
        //MarketRetrofits_Logger.logging("spec is: " + spec,this);
        //MarketRetrofits_Logger.logging("id is: " + super.id,this);
        //MarketRetrofits_Logger.logging("id is: " + id,this);
        return spec;
    }
    @Override
    public void clearUnmodified() {
        if (supply != null) {
            for (String id : new ArrayList<String>(supply.keySet())) {
                MutableCommodityQuantity stat = supply.get(id);
                if (stat != null && (stat.getQuantity().isUnmodified() || stat.getQuantity().getModifiedValue() <= 0)) {
                    supply.remove(id);
                }
            }
        }
        if (demand != null) {
            for (String id : new ArrayList<String>(demand.keySet())) {
                MutableCommodityQuantity stat = demand.get(id);
                if (stat != null && (stat.getQuantity().isUnmodified() || stat.getQuantity().getModifiedValue() <= 0)) {
                    demand.remove(id);
                }
            }
        }
    }
    @Override
    public List<MutableCommodityQuantity> getAllDemand() {
        List<MutableCommodityQuantity> result = new ArrayList<MutableCommodityQuantity>();
        for (MutableCommodityQuantity q : demand.values()) {
            if (q.getQuantity().getModifiedValue() > 0) {
                result.add(q);
            }
        }
        return result;
    }
    @Override
    public List<MutableCommodityQuantity> getAllSupply() {
        List<MutableCommodityQuantity> result = new ArrayList<MutableCommodityQuantity>();
        for (MutableCommodityQuantity q : supply.values()) {
            if (q.getQuantity().getModifiedValue() > 0) {
                result.add(q);
            }
        }
        return result;
    }
    @Override
    public MutableCommodityQuantity getSupply(String id) {
        MutableCommodityQuantity stat = supply.get(id);
        if (stat == null) {
            stat = new MutableCommodityQuantity(id);
            supply.put(id, stat);
        }
        return stat;
    }
    @Override
    public MutableCommodityQuantity getDemand(String id) {
        MutableCommodityQuantity stat = demand.get(id);
        if (stat == null) {
            stat = new MutableCommodityQuantity(id);
            demand.put(id, stat);
        }
        return stat;
    }
    @Override
    public MutableStat getIncome() {
        return income;
    }
    @Override
    public MutableStat getUpkeep() {
        return upkeep;
    }
    @Override
    public MarketAPI getMarket() {
        return market;
    }

    @Override
    public void apply() {

    }

//	public String getMaxDeficitCommodity(String ... commodityIds) {
//		int max = 0;
//		String result = null;
//		for (String id : commodityIds) {
//			int demand = (int) getDemand(id).getQuantity().getModifiedValue();
//			CommodityOnMarketAPI com = market.getCommodityData(id);
//			int available = com.getAvailable();
//
//			int deficit = Math.max(demand - available, 0);
//			if (deficit > max) {
//				max = deficit;
//				result = id;
//			}
//		}
//		return result;
//	}
//
//	public int getMaxDeficit(String ... commodityIds) {
//		int max = 0;
//		for (String id : commodityIds) {
//			int demand = (int) getDemand(id).getQuantity().getModifiedValue();
//			CommodityOnMarketAPI com = market.getCommodityData(id);
//			int available = com.getAvailable();
//
//			int deficit = Math.max(demand - available, 0);
//			if (deficit > max) max = deficit;
//		}
//		return max;
//	}
@Override
    public Pair<String, Integer> getMaxDeficit(String ... commodityIds) {
        Pair<String, Integer> result = new Pair<String, Integer>();
        result.two = 0;
        for (String id : commodityIds) {
            int demand = (int) CurrentIndustry.getDemand(id).getQuantity().getModifiedValue();
            CommodityOnMarketAPI com = market.getCommodityData(id);
            int available = com.getAvailable();

            int deficit = Math.max(demand - available, 0);
            if (deficit > result.two) {
                result.one = id;
                result.two = deficit;
            }
        }
        return result;
    }
    @Override
    public List<Pair<String, Integer>> getAllDeficit() {
        List<String> commodities = new ArrayList<String>();
        for (MutableCommodityQuantity curr : demand.values()) {
            commodities.add(curr.getCommodityId());
        }
        return CurrentIndustry.getAllDeficit(commodities.toArray(new String[0]));
    }
    @Override
    public List<Pair<String, Integer>> getAllDeficit(String ... commodityIds) {
        List<Pair<String, Integer>> result = new ArrayList<Pair<String,Integer>>();
        for (String id : commodityIds) {
            int demand = (int) CurrentIndustry.getDemand(id).getQuantity().getModifiedValue();
            CommodityOnMarketAPI com = market.getCommodityData(id);
            int available = com.getAvailable();

            int deficit = Math.max(demand - available, 0);
            if (deficit > 0) {
                Pair<String, Integer> curr = new Pair<String, Integer>();
                curr.one = id;
                curr.two = deficit;
                result.add(curr);
            }
        }
        return result;
    }
    @Override
    public float getSizeMult() {
        return CurrentIndustry.getSizeMult(market.getSize());
    }

    public static float getCommodityEconUnitMult(float size) {
        if (size <= 0) return 0f;
//		if (size == 1) return 0.2f;
//		if (size == 2) return 0.3f;
//		if (size == 3) return 0.5f;
        return 1f;
    }
    public static float getSizeMult(float size) {
        if (size <= 0) return 0f;
//		if (size == 1) return 0.2f;
//		if (size == 2) return 0.5f;
//		return size - 2f;
        return size;

//		if (size <= 1) return 0.2f;
//		if (size == 2) return 0.3f;
//		if (size == 3) return 0.5f;
//		return size - 3f;

//		if (size == 4) return 2f;
//		if (size == 5) return 4f;
//		if (size == 6) return 8f;
//		if (size == 7) return 16f;
//		if (size == 8) return 32f;
//		if (size == 9) return 64f;
//		if (size == 10) return 128f;
//
//		return (float) Math.pow(2, size - 3);
    }

    @Override
    public void doPreSaveCleanup() {
        supply = null;
        demand = null;
        income = null;
        upkeep = null;
    }
    @Override
    public void doPostSaveRestore() {
        supply = new LinkedHashMap<String, MutableCommodityQuantity>();
        demand = new LinkedHashMap<String, MutableCommodityQuantity>();

        income = new MutableStat(0f);
        upkeep = new MutableStat(0f);
    }

    @Override
    public String getCurrentImage() {
        return CurrentIndustry.getSpec().getImageName();
    }
    @Override
    public String getCurrentName() {
        return CurrentIndustry.getSpec().getName();
    }
    @Override
    public boolean isAvailableToBuild() {
        if (market.hasTag(Tags.MARKET_NO_INDUSTRIES_ALLOWED)) return false;
        return market.hasIndustry(Industries.POPULATION) && !CurrentIndustry.getId().equals(Industries.POPULATION);
    }
    @Override
    public boolean showWhenUnavailable() {
        if (market.hasTag(Tags.MARKET_NO_INDUSTRIES_ALLOWED)) return false;
        return true;
    }
    @Override
    public String getUnavailableReason() {
        return "Can not be built";
    }
    @Override
    public boolean isTooltipExpandable() {
        return false;
    }
    @Override
    public float getTooltipWidth() {
        return 400f;
    }

    //public transient Industry.IndustryTooltipMode currTooltipMode = null;
    @Override
    public void createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded) {
        currTooltipMode = mode;

        float pad = 3f;
        float opad = 10f;

        FactionAPI faction = market.getFaction();
        Color color = faction.getBaseUIColor();
        Color dark = faction.getDarkUIColor();
        Color grid = faction.getGridUIColor();
        Color bright = faction.getBrightUIColor();

        Color gray = Misc.getGrayColor();
        Color highlight = Misc.getHighlightColor();
        Color bad = Misc.getNegativeHighlightColor();


        MarketAPI copy = market.clone();
        // the copy is a shallow copy and its conditions point to the original market
        // so, make it share the suppressed conditions list, too, otherwise
        // e.g. SolarArray will suppress conditions in the original market and the copy will still apply them
        copy.setSuppressedConditions(market.getSuppressedConditions());
        copy.setRetainSuppressedConditionsSetWhenEmpty(true);
        market.setRetainSuppressedConditionsSetWhenEmpty(true);
        MarketAPI orig = market;

        //int numBeforeAdd = Misc.getNumIndustries(market);

        market = copy;
        boolean needToAddIndustry = !market.hasIndustry(CurrentIndustry.getId());
        //addDialogMode = true;
        if (needToAddIndustry) market.getIndustries().add(CurrentIndustry);

        if (mode != Industry.IndustryTooltipMode.NORMAL) {
            market.clearCommodities();
            for (CommodityOnMarketAPI curr : market.getAllCommodities()) {
                curr.getAvailableStat().setBaseValue(100);
            }
        }

//		if (addDialogMode) {
//			market.reapplyConditions();
//			apply();
//		}
        market.reapplyConditions();
        CurrentIndustry.reapply();

        String type = "";
        if (CurrentIndustry.isIndustry()) type = " - Industry";
        if (CurrentIndustry.isStructure()) type = " - Structure";

        tooltip.addTitle(CurrentIndustry.getCurrentName() + type, color);

        String desc = spec.getDesc();
        String override = CurrentIndustry.getDescriptionOverride();
        if (override != null) {
            desc = override;
        }
        desc = Global.getSector().getRules().performTokenReplacement(null, desc, market.getPrimaryEntity(), null);

        tooltip.addPara(desc, opad);

//		Industry inProgress = Misc.getCurrentlyBeingConstructed(market);
//		if ((mode == IndustryTooltipMode.ADD_INDUSTRY && inProgress != null) ||
//				(mode == IndustryTooltipMode.UPGRADE && inProgress != null)) {
//			//tooltip.addPara("Another project (" + inProgress.getCurrentName() + ") in progress", bad, opad);
//			//tooltip.addPara("Already building: " + inProgress.getCurrentName() + "", bad, opad);
//			tooltip.addPara("Another construction in progress: " + inProgress.getCurrentName() + "", bad, opad);
//		}

        //tooltip.addPara("Type: %s", opad, gray, highlight, type);
        if (CurrentIndustry.isIndustry() && (mode == Industry.IndustryTooltipMode.ADD_INDUSTRY ||
                mode == Industry.IndustryTooltipMode.UPGRADE ||
                mode == Industry.IndustryTooltipMode.DOWNGRADE)
        ) {

            int num = Misc.getNumIndustries(market);
            int max = Misc.getMaxIndustries(market);


            // during the creation of the tooltip, the market has both the current industry
            // and the upgrade/downgrade. So if this upgrade/downgrade counts as an industry, it'd count double if
            // the current one is also an industry. Thus reduce num by 1 if that's the case.
            if (CurrentIndustry.isIndustry()) {
                if (mode == Industry.IndustryTooltipMode.UPGRADE) {
                    for (Industry curr : market.getIndustries()) {
                        if (CurrentIndustry.getSpec().getId().equals(curr.getSpec().getUpgrade())) {
                            if (curr.isIndustry()) {
                                num--;
                            }
                            break;
                        }
                    }
                } else if (mode == Industry.IndustryTooltipMode.DOWNGRADE) {
                    for (Industry curr : market.getIndustries()) {
                        if (CurrentIndustry.getSpec().getId().equals(curr.getSpec().getDowngrade())) {
                            if (curr.isIndustry()) {
                                num--;
                            }
                            break;
                        }
                    }
                }
            }

            Color c = gray;
            c = Misc.getTextColor();
            Color h1 = highlight;
            Color h2 = highlight;
            if (num > max) {// || (num >= max && mode == IndustryTooltipMode.ADD_INDUSTRY)) {
                //c = bad;
                h1 = bad;
                num--;

                tooltip.addPara("Maximum number of industries reached", bad, opad);
            }
            //tooltip.addPara("Maximum of %s industries on a colony of this size. Currently: %s.",
//			LabelAPI label = tooltip.addPara("Maximum industries for a colony of this size: %s. Industries: %s. ",
//					opad, c, h1, "" + max, "" + num);
//			label.setHighlightColors(h2, h1);
        }



        CurrentIndustry.addRightAfterDescriptionSection(tooltip, mode);

        if (CurrentIndustry.isDisrupted()) {
            int left = (int) CurrentIndustry.getDisruptedDays();
            if (left < 1) left = 1;
            String days = "days";
            if (left == 1) days = "day";

            tooltip.addPara("Operations disrupted! %s " + days + " until return to normal function.",
                    opad, Misc.getNegativeHighlightColor(), highlight, "" + left);
        }

        if (DebugFlags.COLONY_DEBUG || market.isPlayerOwned()) {
            if (mode == Industry.IndustryTooltipMode.NORMAL) {
                if (CurrentIndustry.getSpec().getUpgrade() != null && !CurrentIndustry.isBuilding()) {
                    tooltip.addPara("Click to manage or upgrade", Misc.getPositiveHighlightColor(), opad);
                } else {
                    tooltip.addPara("Click to manage", Misc.getPositiveHighlightColor(), opad);
                }
                //tooltip.addPara("Click to manage", market.getFaction().getBrightUIColor(), opad);
            }
        }

        if (mode == Industry.IndustryTooltipMode.QUEUED) {
            tooltip.addPara("Click to remove or adjust position in queue", Misc.getPositiveHighlightColor(), opad);
            tooltip.addPara("Currently queued for construction. Does not have any impact on the colony.", opad);

            int left = (int) (CurrentIndustry.getSpec().getBuildTime());
            if (left < 1) left = 1;
            String days = "days";
            if (left == 1) days = "day";
            tooltip.addPara("Requires %s " + days + " to build.", opad, highlight, "" + left);

            //return;
        } else if (!CurrentIndustry.isFunctional() && mode == Industry.IndustryTooltipMode.NORMAL) {
            tooltip.addPara("Currently under construction and not producing anything or providing other benefits.", opad);

            int left = (int) (buildTime - buildProgress);
            if (left < 1) left = 1;
            String days = "days";
            if (left == 1) days = "day";
            tooltip.addPara("Requires %s more " + days + " to finish building.", opad, highlight, "" + left);
        }


        if (!CurrentIndustry.isAvailableToBuild() &&
                (mode == Industry.IndustryTooltipMode.ADD_INDUSTRY ||
                        mode == Industry.IndustryTooltipMode.UPGRADE ||
                        mode == Industry.IndustryTooltipMode.DOWNGRADE)) {
            String reason = CurrentIndustry.getUnavailableReason();
            if (reason != null) {
                tooltip.addPara(reason, bad, opad);
            }
        }

        boolean category = CurrentIndustry.getSpec().hasTag(Industries.TAG_PARENT);

        if (!category) {
            int credits = (int) Global.getSector().getPlayerFleet().getCargo().getCredits().get();
            String creditsStr = Misc.getDGSCredits(credits);
            if (mode == Industry.IndustryTooltipMode.UPGRADE || mode == Industry.IndustryTooltipMode.ADD_INDUSTRY) {
                int cost = (int) CurrentIndustry.getBuildCost();
                String costStr = Misc.getDGSCredits(cost);

                int days = (int) CurrentIndustry.getBuildTime();
                String daysStr = "days";
                if (days == 1) daysStr = "day";

                LabelAPI label = null;
                if (mode == Industry.IndustryTooltipMode.UPGRADE) {
                    label = tooltip.addPara("%s and %s " + daysStr + " to upgrade. You have %s.", opad,
                            highlight, costStr, "" + days, creditsStr);
                } else {
                    label = tooltip.addPara("%s and %s " + daysStr + " to build. You have %s.", opad,
                            highlight, costStr, "" + days, creditsStr);
                }
                label.setHighlight(costStr, "" + days, creditsStr);
                if (credits >= cost) {
                    label.setHighlightColors(highlight, highlight, highlight);
                } else {
                    label.setHighlightColors(bad, highlight, highlight);
                }
            } else if (mode == Industry.IndustryTooltipMode.DOWNGRADE) {
                if (CurrentIndustry.getSpec().getUpgrade() != null) {
                    float refundFraction = Global.getSettings().getFloat("industryRefundFraction");

                    //int cost = (int) (getBuildCost() * refundFraction);
                    IndustrySpecAPI spec = Global.getSettings().getIndustrySpec(CurrentIndustry.getSpec().getUpgrade());
                    int cost = (int) (spec.getCost() * refundFraction);
                    String refundStr = Misc.getDGSCredits(cost);

                    tooltip.addPara("%s refunded for downgrade.", opad, highlight, refundStr);
                }
            }


            CurrentIndustry.addPostDescriptionSection(tooltip, mode);

            if (!CurrentIndustry.getIncome().isUnmodified()) {
                int income = CurrentIndustry.getIncome().getModifiedInt();
                tooltip.addPara("Monthly income: %s", opad, highlight, Misc.getDGSCredits(income));
                tooltip.addStatModGrid(250, 65, 10, pad, CurrentIndustry.getIncome(), true, new TooltipMakerAPI.StatModValueGetter() {
                    public String getPercentValue(MutableStat.StatMod mod) {return null;}
                    public String getMultValue(MutableStat.StatMod mod) {return null;}
                    public Color getModColor(MutableStat.StatMod mod) {return null;}
                    public String getFlatValue(MutableStat.StatMod mod) {
                        return Misc.getWithDGS(mod.value) + Strings.C;
                    }
                });
            }

            if (!CurrentIndustry.getUpkeep().isUnmodified()) {
                int upkeep = CurrentIndustry.getUpkeep().getModifiedInt();
                tooltip.addPara("Monthly upkeep: %s", opad, highlight, Misc.getDGSCredits(upkeep));
                tooltip.addStatModGrid(250, 65, 10, pad, CurrentIndustry.getUpkeep(), true, new TooltipMakerAPI.StatModValueGetter() {
                    public String getPercentValue(MutableStat.StatMod mod) {return null;}
                    public String getMultValue(MutableStat.StatMod mod) {return null;}
                    public Color getModColor(MutableStat.StatMod mod) {return null;}
                    public String getFlatValue(MutableStat.StatMod mod) {
                        return Misc.getWithDGS(mod.value) + Strings.C;
                    }
                });
            }

            CurrentIndustry.addPostUpkeepSection(tooltip, mode);

            boolean hasSupply = false;
            for (MutableCommodityQuantity curr : supply.values()) {
                int qty = curr.getQuantity().getModifiedInt();
                if (qty <= 0) continue;
                hasSupply = true;
                break;
            }
            boolean hasDemand = false;
            for (MutableCommodityQuantity curr : demand.values()) {
                int qty = curr.getQuantity().getModifiedInt();
                if (qty <= 0) continue;
                hasDemand = true;
                break;
            }

            float maxIconsPerRow = 10f;
            if (hasSupply) {
                tooltip.addSectionHeading("Production", color, dark, Alignment.MID, opad);
                tooltip.beginIconGroup();
                tooltip.setIconSpacingMedium();
                float icons = 0;
                for (MutableCommodityQuantity curr : supply.values()) {
                    int qty = curr.getQuantity().getModifiedInt();
                    //if (qty <= 0) continue;

                    int normal = qty;
                    if (normal > 0) {
                        tooltip.addIcons(market.getCommodityData(curr.getCommodityId()), normal, IconRenderMode.NORMAL);
                    }

                    int plus = 0;
                    int minus = 0;
                    for (MutableStat.StatMod mod : curr.getQuantity().getFlatMods().values()) {
                        if (mod.value > 0) {
                            plus += (int) mod.value;
                        } else if (mod.desc != null && mod.desc.contains("shortage")) {
                            minus += (int) Math.abs(mod.value);
                        }
                    }
                    minus = Math.min(minus, plus);
                    if (minus > 0 && mode == Industry.IndustryTooltipMode.NORMAL) {
                        tooltip.addIcons(market.getCommodityData(curr.getCommodityId()), minus, IconRenderMode.DIM_RED);
                    }
                    icons += normal + Math.max(0, minus);
                }
                int rows = (int) Math.ceil(icons / maxIconsPerRow);
                rows = 3;
                tooltip.addIconGroup(32, rows, opad);


            }
//			else if (!isFunctional() && mode == IndustryTooltipMode.NORMAL) {
//				tooltip.addPara("Currently under construction and not producing anything or providing other benefits.", opad);
//			}

            CurrentIndustry.addPostSupplySection(tooltip, hasSupply, mode);

            if (hasDemand || CurrentIndustry.hasPostDemandSection(hasDemand, mode)) {
                tooltip.addSectionHeading("Demand & effects", color, dark, Alignment.MID, opad);
            }
            if (hasDemand) {
                tooltip.beginIconGroup();
                tooltip.setIconSpacingMedium();
                float icons = 0;
                for (MutableCommodityQuantity curr : demand.values()) {
                    int qty = curr.getQuantity().getModifiedInt();
                    if (qty <= 0) continue;

                    CommodityOnMarketAPI com = orig.getCommodityData(curr.getCommodityId());
                    int available = com.getAvailable();

                    int normal = Math.min(available, qty);
                    int red = Math.max(0, qty - available);

                    if (mode != Industry.IndustryTooltipMode.NORMAL) {
                        normal = qty;
                        red = 0;
                    }
                    if (normal > 0) {
                        tooltip.addIcons(com, normal, IconRenderMode.NORMAL);
                    }
                    if (red > 0) {
                        tooltip.addIcons(com, red, IconRenderMode.DIM_RED);
                    }
                    icons += normal + Math.max(0, red);
                }
                int rows = (int) Math.ceil(icons / maxIconsPerRow);
                rows = 3;
                rows = 1;
                tooltip.addIconGroup(32, rows, opad);
            }

            CurrentIndustry.addPostDemandSection(tooltip, hasDemand, mode);

            if (!needToAddIndustry) {
                //addAICoreSection(tooltip, AICoreDescriptionMode.TOOLTIP);
                CurrentIndustry.addInstalledItemsSection(mode, tooltip, expanded);
                CurrentIndustry.addImprovedSection(mode, tooltip, expanded);
            }

            tooltip.addPara("*Shown production and demand values are already adjusted based on current market size and local conditions.", gray, opad);
        }

        if (needToAddIndustry) {
            CurrentIndustry.unapply();
            market.getIndustries().remove(CurrentIndustry);
        }
        market = orig;
        market.setRetainSuppressedConditionsSetWhenEmpty(null);
        if (!needToAddIndustry) {
            CurrentIndustry.reapply();
        }
    }
    @Override
    public void addInstalledItemsSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded) {
        float opad = 10f;

        FactionAPI faction = market.getFaction();
        Color color = faction.getBaseUIColor();
        Color dark = faction.getDarkUIColor();

        LabelAPI heading = tooltip.addSectionHeading("Items", color, dark, Alignment.MID, opad);

        boolean addedSomething = false;
        if (aiCoreId != null) {
            Industry.AICoreDescriptionMode aiCoreDescMode = Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP;
            CurrentIndustry.addAICoreSection(tooltip, aiCoreId, aiCoreDescMode);
            addedSomething = true;
        }
        addedSomething |= CurrentIndustry.addNonAICoreInstalledItems(mode, tooltip, expanded);

        if (!addedSomething) {
            heading.setText("No items installed");
            //tooltip.addPara("None.", opad);
        }
    }
    @Override
    public boolean addNonAICoreInstalledItems(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded) {
        if (special == null) return false;

        float opad = 10f;
        SpecialItemSpecAPI spec = Global.getSettings().getSpecialItemSpec(special.getId());

        TooltipMakerAPI text = tooltip.beginImageWithText(spec.getIconName(), 48);
        InstallableItemEffect effect = ItemEffectsRepo.ITEM_EFFECTS.get(special.getId());
        effect.addItemDescription(CurrentIndustry, text, special, InstallableIndustryItemPlugin.InstallableItemDescriptionMode.INDUSTRY_TOOLTIP);
        tooltip.addImageWithText(opad);

        return true;
    }

//	public List<SpecialItemData> getVisibleInstalledItems() {
//		return new ArrayList<SpecialItemData>();
//	}
@Override
    public List<SpecialItemData> getVisibleInstalledItems() {
        List<SpecialItemData> result = new ArrayList<SpecialItemData>();
        if (special != null) {
            result.add(special);
        }
        return result;
    }
    @Override
    public boolean wantsToUseSpecialItem(SpecialItemData data) {
        if (special != null) return false;
        SpecialItemSpecAPI spec = Global.getSettings().getSpecialItemSpec(data.getId());
//		String industry = spec.getParams();
//		//String industry = ItemEffectsRepo.ITEM_TO_INDUSTRY.get(data.getId());
//		return getId().equals(industry);
        String [] industries = spec.getParams().split(",");
        Set<String> all = new HashSet<String>();
        for (String ind: industries) all.add(ind.trim());
        return all.contains(CurrentIndustry.getId());
    }

//	public boolean wantsToUseSpecialItem(SpecialItemData data) {
//		return false;
//	}

    @Override
    public void addAICoreSection(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode) {
        CurrentIndustry.addAICoreSection(tooltip, aiCoreId, mode);
    }
    @Override
    public void addAICoreSection(TooltipMakerAPI tooltip, String coreId, Industry.AICoreDescriptionMode mode) {
        float opad = 10f;

        FactionAPI faction = market.getFaction();
        Color color = faction.getBaseUIColor();
        Color dark = faction.getDarkUIColor();

//		if (mode == AICoreDescriptionMode.TOOLTIP) {
//			tooltip.addSectionHeading("AI Core", color, dark, Alignment.MID, opad);
//		}

//		if (mode == AICoreDescriptionMode.TOOLTIP || mode == AICoreDescriptionMode.MANAGE_CORE_TOOLTIP) {
        if (mode == Industry.AICoreDescriptionMode.MANAGE_CORE_TOOLTIP) {
            if (coreId == null) {
                tooltip.addPara("No AI core currently assigned. Click to assign an AI core from your cargo.", opad);
                return;
            }
        }

        boolean alpha = coreId.equals(Commodities.ALPHA_CORE);
        boolean beta = coreId.equals(Commodities.BETA_CORE);
        boolean gamma = coreId.equals(Commodities.GAMMA_CORE);

        if (alpha) {
            CurrentIndustry.addAlphaCoreDescription(tooltip, mode);
        } else if (beta) {
            CurrentIndustry.addBetaCoreDescription(tooltip, mode);
        } else if (gamma) {
            CurrentIndustry.addGammaCoreDescription(tooltip, mode);
        }
    }
    @Override
    public void addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        String pre = "Alpha-level AI core currently assigned. ";
        if (mode == Industry.AICoreDescriptionMode.MANAGE_CORE_DIALOG_LIST || mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            pre = "Alpha-level AI core. ";
        }
        if (mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP || mode == Industry.AICoreDescriptionMode.MANAGE_CORE_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
            text.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                            "Increases production by %s unit.", 0f, highlight,
                    "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                    "" + SUPPLY_BONUS);
            tooltip.addImageWithText(opad);
            return;
        }

        tooltip.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                        "Increases production by %s unit.", opad, highlight,
                "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                "" + SUPPLY_BONUS);
    }
    @Override
    public void addBetaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        String pre = "Beta-level AI core currently assigned. ";
        if (mode == Industry.AICoreDescriptionMode.MANAGE_CORE_DIALOG_LIST || mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            pre = "Beta-level AI core. ";
        }
        if (mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP || mode == Industry.AICoreDescriptionMode.MANAGE_CORE_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
            text.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit.", opad, highlight,
                    "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION);
            tooltip.addImageWithText(opad);
            return;
        }

        tooltip.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit.", opad, highlight,
                "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION);
    }
    @Override
    public void addGammaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        String pre = "Gamma-level AI core currently assigned. ";
        if (mode == Industry.AICoreDescriptionMode.MANAGE_CORE_DIALOG_LIST || mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            pre = "Gamma-level AI core. ";
        }
        if (mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP || mode == Industry.AICoreDescriptionMode.MANAGE_CORE_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
//			text.addPara(pre + "Reduces upkeep cost by %s.", opad, highlight,
//					"" + (int)((1f - UPKEEP_MULT) * 100f) + "%");
//			tooltip.addImageWithText(opad);
            text.addPara(pre + "Reduces demand by %s unit.", opad, highlight,
                    "" + DEMAND_REDUCTION);
            tooltip.addImageWithText(opad);
            return;
        }

//		tooltip.addPara(pre + "Reduces upkeep cost by %s.", opad, highlight,
//				"" + (int)((1f - UPKEEP_MULT) * 100f) + "%");
        tooltip.addPara(pre + "Reduces demand by %s unit.", opad, highlight,
                "" + DEMAND_REDUCTION);
    }

    @Override
    public void addPostSupplySection(TooltipMakerAPI tooltip, boolean hasSupply, Industry.IndustryTooltipMode mode) {

    }
    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {

    }
    @Override
    public void addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode) {

    }
    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode) {

    }
    @Override
    public void addPostUpkeepSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode) {

    }
    @Override
    public String getAICoreId() {
        return aiCoreId;
    }
    @Override
    public void setAICoreId(String aiCoreId) {
        this.aiCoreId = aiCoreId;
    }
    @Override
    public void applyAICoreToIncomeAndUpkeep() {
        if (aiCoreId == null || Commodities.GAMMA_CORE.equals(aiCoreId)) {
            CurrentIndustry.getUpkeep().unmodifyMult("ind_core");
            return;
        }

        float mult = UPKEEP_MULT;
        String name = "AI Core assigned";
        if (aiCoreId.equals(Commodities.ALPHA_CORE)) {
            name = "Alpha Core assigned";
        } else if (aiCoreId.equals(Commodities.BETA_CORE)) {
            name = "Beta Core assigned";
        } else if (aiCoreId.equals(Commodities.GAMMA_CORE)) {
            name = "Gamma Core assigned";
        }

        CurrentIndustry.getUpkeep().modifyMult("ind_core", mult, name);
    }
    @Override
    public void updateAICoreToSupplyAndDemandModifiers() {
        if (aiCoreId == null) {
            return;
        }

        boolean alpha = aiCoreId.equals(Commodities.ALPHA_CORE);
        boolean beta = aiCoreId.equals(Commodities.BETA_CORE);
        boolean gamma = aiCoreId.equals(Commodities.GAMMA_CORE);

        if (alpha) {
//			supplyBonus.modifyFlat(getModId(0), SUPPLY_BONUS, "Alpha core");
//			demandReduction.modifyFlat(getModId(0), DEMAND_REDUCTION, "Alpha core");
            CurrentIndustry.applyAlphaCoreSupplyAndDemandModifiers();
        } else if (beta) {
            //demandReduction.modifyFlat(getModId(0), DEMAND_REDUCTION, "Beta core");
            CurrentIndustry.applyBetaCoreSupplyAndDemandModifiers();
        } else if (gamma) {
            //demandReduction = DEMAND_REDUCTION;
            //demandReduction.modifyFlat(getModId(0), DEMAND_REDUCTION, "Gamma core");
            CurrentIndustry.applyGammaCoreSupplyAndDemandModifiers();
        }
    }
    @Override
    public void applyAlphaCoreSupplyAndDemandModifiers() {
        supplyBonus.modifyFlat(CurrentIndustry.getModId(0), SUPPLY_BONUS, "Alpha core");
        demandReduction.modifyFlat(CurrentIndustry.getModId(0), DEMAND_REDUCTION, "Alpha core");
    }
    @Override
    public void applyBetaCoreSupplyAndDemandModifiers() {
        demandReduction.modifyFlat(CurrentIndustry.getModId(0), DEMAND_REDUCTION, "Beta core");
    }
    @Override
    public void applyGammaCoreSupplyAndDemandModifiers() {
        demandReduction.modifyFlat(CurrentIndustry.getModId(0), DEMAND_REDUCTION, "Gamma core");
    }
    @Override
    public void updateSupplyAndDemandModifiers() {

//		if (this instanceof Mining && market.getName().equals("Louise")) {
//			System.out.println("efwefwe");
//		}
//		supplyBonus = 0;
//		demandReduction = 0;
        supplyBonus.unmodify();
        demandReduction.unmodify();

        CurrentIndustry.updateAICoreToSupplyAndDemandModifiers();

        CurrentIndustry.updateImprovementSupplyAndDemandModifiers();

//		supplyBonus += market.getAdmin().getStats().getDynamic().getValue(Stats.SUPPLY_BONUS_MOD, 0);
//		demandReduction += market.getAdmin().getStats().getDynamic().getValue(Stats.DEMAND_REDUCTION_MOD, 0);
        supplyBonus.modifyFlat(CurrentIndustry.getModId(1), market.getAdmin().getStats().getDynamic().getValue(Stats.SUPPLY_BONUS_MOD, 0), "Administrator");
        demandReduction.modifyFlat(CurrentIndustry.getModId(1), market.getAdmin().getStats().getDynamic().getValue(Stats.DEMAND_REDUCTION_MOD, 0), "Administrator");

        if (supplyBonusFromOther != null) {
            supplyBonus.applyMods(supplyBonusFromOther);
        }
        if (demandReductionFromOther != null) {
            demandReduction.applyMods(demandReductionFromOther);
        }

//		if (supplyBonusFromOther != null) {
//			supplyBonusFromOther.unmodify();
//		}
//		if (demandReductionFromOther != null) {
//			demandReductionFromOther.unmodify();
//		}

    }

    @Override
    public boolean showShutDown() {
        return true;
    }
    @Override
    public boolean canShutDown() {
        return true;
    }
    @Override
    public String getCanNotShutDownReason() {
        return null;
    }
    @Override
    public boolean canUpgrade() {
        return true;
    }
    @Override
    public boolean canDowngrade() {
        return true;
    }
    @Override
    public String getDescriptionOverride() {
        return null;
    }
    @Override
    public String getNameForModifier() {
        return Misc.ucFirst(CurrentIndustry.getCurrentName().toLowerCase());
    }
    @Override
    public boolean isDemandLegal(CommodityOnMarketAPI com) {
        return !com.isIllegal();
    }
    @Override
    public boolean isSupplyLegal(CommodityOnMarketAPI com) {
        return !com.isIllegal();
    }
    @Override
    public boolean isAICoreId(String str) {
        Set<String> cores = new HashSet<String>();
        cores.add(Commodities.ALPHA_CORE);
        cores.add(Commodities.BETA_CORE);
        cores.add(Commodities.GAMMA_CORE);
        return cores.contains(str);
    }
    @Override
    public void initWithParams(List<String> params) {
        for (String str : params) {
            if (CurrentIndustry.isAICoreId(str)) {
                CurrentIndustry.setAICoreId(str);
                break;
            }
        }

        for (String str : params) {
            SpecialItemSpecAPI spec = Global.getSettings().getSpecialItemSpec(str);
            if (spec == null) continue;

            String [] industries = spec.getParams().split(",");
            Set<String> all = new HashSet<String>();
            for (String ind : industries) all.add(ind.trim());
            if (all.contains(CurrentIndustry.getId())) {
                CurrentIndustry.setSpecialItem(new SpecialItemData(str, null));
            }
        }
    }
    @Override
    public boolean hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode) {
        return false;
    }

    @Override
    public int getBaseStabilityMod() {
        return 0;
    }
    @Override
    public void modifyStabilityWithBaseMod() {
        int stabilityMod = CurrentIndustry.getBaseStabilityMod();
        int stabilityPenalty = CurrentIndustry.getStabilityPenalty();
        if (stabilityPenalty > stabilityMod) {
            stabilityPenalty = stabilityMod;
        }
        stabilityMod -= stabilityPenalty;
        if (stabilityMod > 0) {
            market.getStability().modifyFlat(CurrentIndustry.getModId(), stabilityMod, CurrentIndustry.getNameForModifier());
        }
//		else if (stabilityMod < 0) {
//			String str = getDeficitText(getStabilityAffectingDeficit().one);
//			market.getStability().modifyFlat(getModId(), stabilityMod, getNameForModifier() + " (" + str.toLowerCase() + ")");
//		}
    }
    @Override
    public void unmodifyStabilityWithBaseMod() {
        market.getStability().unmodifyFlat(CurrentIndustry.getModId());
    }
    @Override
    public Pair<String, Integer> getStabilityAffectingDeficit() {
        return new Pair<String, Integer>(Commodities.SUPPLIES, 0);
    }
    @Override
    public int getStabilityPenalty() {
        float deficit = CurrentIndustry.getStabilityAffectingDeficit().two;
        if (deficit < 0) deficit = 0;
        return (int) Math.round(deficit);
    }

    @Override
    public void addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        Color h = Misc.getHighlightColor();
        float opad = 10f;

        MutableStat fake = new MutableStat(0);
        int stabilityMod = CurrentIndustry.getBaseStabilityMod();
        int stabilityPenalty = CurrentIndustry.getStabilityPenalty();

        if (stabilityPenalty > stabilityMod) {
            stabilityPenalty = stabilityMod;
        }

        String str = CurrentIndustry.getDeficitText(CurrentIndustry.getStabilityAffectingDeficit().one);
        fake.modifyFlat("1", stabilityMod, CurrentIndustry.getNameForModifier());
        if (stabilityPenalty != 0) {
            fake.modifyFlat("2", -stabilityPenalty, str);
        }

        int total = stabilityMod - stabilityPenalty;
        String totalStr = "+" + total;
        if (total < 0) {
            totalStr = "" + total;
            h = Misc.getNegativeHighlightColor();
        }
        float pad = 3f;
        if (total >= 0) {
            tooltip.addPara("Stability bonus: %s", opad, h, totalStr);
        } else {
            tooltip.addPara("Stability penalty: %s", opad, h, totalStr);
        }
        tooltip.addStatModGrid(400, 35, opad, pad, fake, new TooltipMakerAPI.StatModValueGetter() {
            public String getPercentValue(MutableStat.StatMod mod) {
                return null;
            }
            public String getMultValue(MutableStat.StatMod mod) {
                return null;
            }
            public Color getModColor(MutableStat.StatMod mod) {
                if (mod.value < 0) return Misc.getNegativeHighlightColor();
                return null;
            }
            public String getFlatValue(MutableStat.StatMod mod) {
                return null;
            }
        });
    }
    @Override
    public void setHidden(boolean hidden) {
        if (hidden) hiddenOverride = true;
        else hiddenOverride = null;
    }

    //public Boolean hiddenOverride = null;
    @Override
    public boolean isHidden() {
        if (hiddenOverride != null) return hiddenOverride;
        return false;
    }

    public transient String dKey = null;
    @Override
    public String getDisruptedKey() {
        if (dKey != null) return dKey;
        dKey = "$core_disrupted_" + CurrentIndustry.getClass().getSimpleName();
        return dKey;
    }
    @Override
    public void setDisrupted(float days) {
        CurrentIndustry.setDisrupted(days, false);
    }
    @Override
    public void setDisrupted(float days, boolean useMax) {
        if (!CurrentIndustry.canBeDisrupted()) return;

        boolean was = CurrentIndustry.isDisrupted();
        String key = CurrentIndustry.getDisruptedKey();

        MemoryAPI memory = market.getMemoryWithoutUpdate();
        float dur = days;
        if (useMax) {
            dur = Math.max(memory.getExpire(key), dur);
        }

        if (dur <= 0) {
            memory.unset(key);
        } else {
            memory.set(key, true, dur);
        }

        if (!was) {
            CurrentIndustry.notifyDisrupted();
        }
    }
    @Override
    public float getDisruptedDays() {
        String key = CurrentIndustry.getDisruptedKey();
        float dur = market.getMemoryWithoutUpdate().getExpire(key);
        if (dur < 0) dur = 0;
        return dur;
    }
    @Override
    public boolean canBeDisrupted() {
        return true;
    }
    @Override
    public boolean isDisrupted() {
        String key = CurrentIndustry.getDisruptedKey();
        return market.getMemoryWithoutUpdate().is(key, true);
    }
    @Override
    public float getPatherInterest() {
        float interest = 0;
        if (Commodities.ALPHA_CORE.equals(aiCoreId)) {
            interest += 4f;
        } else if (Commodities.BETA_CORE.equals(aiCoreId)) {
            interest += 2f;
        } else if (Commodities.GAMMA_CORE.equals(aiCoreId)) {
            interest += 1f;
        }

        if (special != null) {
            SpecialItemSpecAPI spec = Global.getSettings().getSpecialItemSpec(special.getId());
            if (spec != null) {
                if (spec.hasTag(Items.TAG_PATHER1)) interest += 1;
                else if (spec.hasTag(Items.TAG_PATHER2)) interest += 2;
                else if (spec.hasTag(Items.TAG_PATHER4)) interest += 4;
                else if (spec.hasTag(Items.TAG_PATHER6)) interest += 6;
                else if (spec.hasTag(Items.TAG_PATHER8)) interest += 8;
                else if (spec.hasTag(Items.TAG_PATHER10)) interest += 10;
            }
        }

        return interest;
    }
    @Override
    public CargoAPI generateCargoForGatheringPoint(Random random) {
        return null;
    }
    @Override
    public String getCargoTitleForGatheringPoint() {
        return CurrentIndustry.getCurrentName();
    }


    //public SpecialItemData special = null;
    @Override
    public SpecialItemData getSpecialItem() {
        return special;
    }
    @Override
    public void setSpecialItem(SpecialItemData special) {
        //if (special == null && this.special != null) {
        if (this.special != null) {
            InstallableItemEffect effect = ItemEffectsRepo.ITEM_EFFECTS.get(this.special.getId());
            if (effect != null) {
                effect.unapply(CurrentIndustry);
            }
        }
        this.special = special;
    }
    @Override
    public float getDeficitMult(String ... commodities) {
        float deficit = CurrentIndustry.getMaxDeficit(commodities).two;
        float demand = 0f;

        for (String id : commodities) {
            demand = Math.max(demand, CurrentIndustry.getDemand(id).getQuantity().getModifiedInt());
        }

        if (deficit < 0) deficit = 0f;
        if (demand < 1) {
            demand = 1;
            deficit = 0f;
        }

        float mult = (demand - deficit) / demand;
        if (mult < 0) mult = 0;
        if (mult > 1) mult = 1;
        return mult;
    }

    @Override
    public void addGroundDefensesImpactSection(TooltipMakerAPI tooltip, float bonus, String ...commodities) {
        Color h = Misc.getHighlightColor();
        float opad = 10f;

        MutableStat fake = new MutableStat(1);

        fake.modifyFlat("1", bonus, CurrentIndustry.getNameForModifier());

        if (commodities != null) {
            float mult = CurrentIndustry.getDeficitMult(commodities);
            //mult = 0.89f;
            if (mult != 1) {
                String com = CurrentIndustry.getMaxDeficit(commodities).one;
                fake.modifyFlat("2", -(1f - mult) * bonus, CurrentIndustry.getDeficitText(com));
            }
        }

        float total = Misc.getRoundedValueFloat(fake.getModifiedValue());
        String totalStr = Strings.X + total;
        if (total < 1f) {
            h = Misc.getNegativeHighlightColor();
        }
        float pad = 3f;
        tooltip.addPara("Ground defense strength: %s", opad, h, totalStr);
        tooltip.addStatModGrid(400, 35, opad, pad, fake, new TooltipMakerAPI.StatModValueGetter() {
            public String getPercentValue(MutableStat.StatMod mod) {
                return null;
            }
            public String getMultValue(MutableStat.StatMod mod) {
                return null;
            }
            public Color getModColor(MutableStat.StatMod mod) {
                if (mod.value < 0) return Misc.getNegativeHighlightColor();
                return null;
            }
            public String getFlatValue(MutableStat.StatMod mod) {
                String r = Misc.getRoundedValue(mod.value);
                if (mod.value >= 0) return "+" + r;
                return r;
            }
        });
    }

    @Override
    public boolean isIndustry() {
        //MarketRetrofits_Logger.logging("do I have a current industry?: " + CurrentIndustry,this);
        //MarketRetrofits_Logger.logging("do I have a spec on that industry?: " + CurrentIndustry.getSpec(),this);
        //MarketRetrofits_Logger.logging("do i have a hasTage on that spec?: " + CurrentIndustry.getSpec().hasTag(Industries.TAG_INDUSTRY),this);
        return CurrentIndustry.getSpec().hasTag(Industries.TAG_INDUSTRY);
    }
    @Override
    public boolean isStructure() {
        return CurrentIndustry.getSpec().hasTag(Industries.TAG_STRUCTURE);
    }
    @Override
    public boolean isOther() {
        return !CurrentIndustry.isIndustry() && !CurrentIndustry.isStructure();
    }
    @Override
    public void notifyColonyRenamed() {

    }
    @Override
    public boolean canImprove() {
        return CurrentIndustry.canImproveToIncreaseProduction();
    }
    @Override
    public float getImproveBonusXP() {
        return 0;
    }
    @Override
    public String getImproveMenuText() {
        return "Make improvements...";
    }
    @Override
    public int getImproveStoryPoints() {
        int base = Global.getSettings().getInt("industryImproveBase");
        return base * (int) Math.round(Math.pow(2, Misc.getNumImprovedIndustries(market)));

        //return 1 + Misc.getNumImprovedIndustries(market);
    }

//	private transient String iKey = null;
//	public String getImprovedKey() {
//		if (iKey != null) return iKey;
//		iKey = "$core_improved_" + getClass().getSimpleName();
//		return iKey;
//	}
@Override
    public boolean isImproved() {
        return improved != null && improved;
    }
    @Override
    public void setImproved(boolean improved) {
        if (!improved) {
            this.improved = null;
        } else {
            this.improved = improved;
        }
    }
    @Override
    public void applyImproveModifiers() {

    }
    @Override
    public void addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode) {
        float initPad = 0f;
        float opad = 10f;
        boolean addedSomething = false;
        if (CurrentIndustry.canImproveToIncreaseProduction()) {
            String unit = "unit";
            if (CurrentIndustry.getImproveProductionBonus() != 1) {
                unit = "units";
            }
            if (mode == Industry.ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
                info.addPara("Production increased by %s " + unit + ".", initPad, Misc.getHighlightColor(),
                        "" + CurrentIndustry.getImproveProductionBonus());
            } else {
                info.addPara("Increases production by %s " + unit + ".", initPad, Misc.getHighlightColor(),
                        "" + CurrentIndustry.getImproveProductionBonus());

            }
            initPad = opad;
            addedSomething = true;
        }

        if (mode != Industry.ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            //		info.addPara("Each improved industry at a colony raises the cost to improve " +
            //				"another industry by one " + Misc.STORY + " point.", initPad,
            //				Misc.getStoryOptionColor(), Misc.STORY + " point");
            //		info.addPara("Each improved industry at a colony doubles the number of " +
            //				"" + Misc.STORY + " points required to improve an additional industry.", initPad,
            //				Misc.getStoryOptionColor(), Misc.STORY + " points");
            //		info.addPara("Each improved industry or structure at a colony doubles the number of " +
            //				"" + Misc.STORY + " points required to improve an additional industry or structure.", initPad,
            //				Misc.getStoryOptionColor(), Misc.STORY + " points");
            info.addPara("Each improvement made at a colony doubles the number of " +
                            "" + Misc.STORY + " points required to make an additional improvement.", initPad,
                    Misc.getStoryOptionColor(), Misc.STORY + " points");
            addedSomething = true;
        }
        if (!addedSomething) {
            info.addSpacer(-opad);
        }
    }
    @Override
    public String getImproveDialogTitle() {
        return "Improving " + CurrentIndustry.getSpec().getName();
    }
    @Override
    public String getImproveSoundId() {
        return Sounds.STORY_POINT_SPEND_INDUSTRY;
    }
    @Override
    public boolean canImproveToIncreaseProduction() {
        return false;
    }
    @Override
    public int getImproveProductionBonus() {
        return DEFAULT_IMPROVE_SUPPLY_BONUS;
    }
    @Override
    public String getImprovementsDescForModifiers() {
        return "Improvements";
    }
    @Override
    public void updateImprovementSupplyAndDemandModifiers() {
        if (!CurrentIndustry.canImproveToIncreaseProduction()) return;
        if (!CurrentIndustry.isImproved()) return;

        int bonus = CurrentIndustry.getImproveProductionBonus();
        if (bonus <= 0) return;

        supplyBonus.modifyFlat(CurrentIndustry.getModId(3), bonus, CurrentIndustry.getImprovementsDescForModifiers());
    }
    @Override
    public void addImprovedSection(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded) {

        if (!CurrentIndustry.isImproved()) return;

        float opad = 10f;


        tooltip.addSectionHeading("Improvements made", Misc.getStoryOptionColor(),
                Misc.getStoryDarkColor(), Alignment.MID, opad);

        tooltip.addSpacer(opad);
        CurrentIndustry.addImproveDesc(tooltip, Industry.ImprovementDescriptionMode.INDUSTRY_TOOLTIP);

//		String noun = "industry";
//		if (isStructure()) noun = "structure";
//		tooltip.addPara("You've made improvements to this " + noun + ".",
//						Misc.getStoryOptionColor(), opad);

    }

    @Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(String commodityId, MarketCMD.RaidDangerLevel level) {
        return level;
    }
    @Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(String itemId, String data, MarketCMD.RaidDangerLevel level) {
        return level;
    }
    @Override
    public int adjustMarineTokensToRaidItem(String itemId, String data, int marineTokens) {
        return marineTokens;
    }

    @Override
    public boolean canInstallAICores() {
        return true;
    }

//	public List<InstallableIndustryItemPlugin> getInstallableItems() {
//	return new ArrayList<InstallableIndustryItemPlugin>();
//}

    //public transient Boolean hasInstallableItems = null;
    @Override
    public List<InstallableIndustryItemPlugin> getInstallableItems() {
        boolean found = false;
        if (hasInstallableItems != null) {
            found = hasInstallableItems;
        } else {
            OUTER: for (SpecialItemSpecAPI spec : Global.getSettings().getAllSpecialItemSpecs()) {
                if (spec.getParams() == null || spec.getParams().isEmpty()) continue;
                if (spec.getNewPluginInstance(null) instanceof GenericSpecialItemPlugin) {
                    for (String id : spec.getParams().split(",")) {
                        id = id.trim();
                        if (id.equals(CurrentIndustry.getId())) {
                            found = true;
                            break OUTER;
                        }
                    }
                }
            }
            hasInstallableItems = found;
        }
        ArrayList<InstallableIndustryItemPlugin> list = new ArrayList<InstallableIndustryItemPlugin>();
        if (found) {
            list.add(new GenericInstallableItemPlugin(CurrentIndustry));
        }
        return list;
    }
    @Override
    public float getBuildProgress() {
        return buildProgress;
    }
    @Override
    public void setBuildProgress(float buildProgress) {
        this.buildProgress = buildProgress;
    }




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
