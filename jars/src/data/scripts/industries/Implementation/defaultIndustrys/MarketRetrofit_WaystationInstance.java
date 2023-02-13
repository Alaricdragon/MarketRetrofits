package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SubmarketPlugin;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.submarkets.LocalResourcesSubmarketPlugin;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;


import java.awt.Color;

public class MarketRetrofit_WaystationInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_WaystationInstance(String name, float orderT) {
        super(name, orderT);
    }


    public static float UPKEEP_MULT_PER_DEFICIT = 0.1f;
    public static final float BASE_ACCESSIBILITY = 0.1f;

    public static final float IMPROVE_ACCESSIBILITY = 0.2f;

    public static final float ALPHA_CORE_ACCESSIBILITY = 0.2f;

    @Override
    public void apply() {
        super.apply(true);

        int size = market.getSize();

        CurrentIndustry.demand(Commodities.FUEL, size);
        CurrentIndustry.demand(Commodities.SUPPLIES, size);
        CurrentIndustry.demand(Commodities.CREW, size);

        String desc = CurrentIndustry.getNameForModifier();

//		Pair<String, Integer> deficit = getUpkeepAffectingDeficit();
//		if (deficit.two > 0) {
//			float loss = getUpkeepPenalty(deficit);
//			getUpkeep().modifyMult("deficit", 1f + loss, getDeficitText(deficit.one));
//		} else {
//			getUpkeep().unmodifyMult("deficit");
//		}

        market.setHasWaystation(true);

        float a = BASE_ACCESSIBILITY;
        if (a > 0) {
            market.getAccessibilityMod().modifyFlat(CurrentIndustry.getModId(0), a, desc);
        }

        if (market.isPlayerOwned()) {
            SubmarketPlugin sub = Misc.getLocalResources(market);
            if (sub instanceof LocalResourcesSubmarketPlugin) {
                LocalResourcesSubmarketPlugin lr = (LocalResourcesSubmarketPlugin) sub;
                float mult = Global.getSettings().getFloat("stockpileMultExcess");
                lr.getStockpilingBonus(Commodities.FUEL).modifyFlat(CurrentIndustry.getModId(0), size * mult);
                lr.getStockpilingBonus(Commodities.SUPPLIES).modifyFlat(CurrentIndustry.getModId(0), size * mult);
                lr.getStockpilingBonus(Commodities.CREW).modifyFlat(CurrentIndustry.getModId(0), size * mult);
            }
        }


        if (!CurrentIndustry.isFunctional()) {
            supply.clear();
            CurrentIndustry.unapply();
        }
    }

    @Override
    public void unapply() {
        super.unapply();

        market.setHasWaystation(false);
        market.getAccessibilityMod().unmodifyFlat(CurrentIndustry.getModId(0));
        market.getAccessibilityMod().unmodifyFlat(CurrentIndustry.getModId(1));
        market.getAccessibilityMod().unmodifyFlat(CurrentIndustry.getModId(2));

        if (market.isPlayerOwned()) {
            SubmarketPlugin sub = Misc.getLocalResources(market);
            if (sub instanceof LocalResourcesSubmarketPlugin) {
                LocalResourcesSubmarketPlugin lr = (LocalResourcesSubmarketPlugin) sub;
                // base bonuses
                lr.getStockpilingBonus(Commodities.FUEL).unmodifyFlat(CurrentIndustry.getModId(0));
                lr.getStockpilingBonus(Commodities.SUPPLIES).unmodifyFlat(CurrentIndustry.getModId(0));
                lr.getStockpilingBonus(Commodities.CREW).unmodifyFlat(CurrentIndustry.getModId(0));
            }
        }
    }

    public float getUpkeepPenalty(Pair<String, Integer> deficit) {
        float loss = deficit.two * UPKEEP_MULT_PER_DEFICIT;
        if (loss < 0) loss = 0;
        return loss;
    }

    public Pair<String, Integer> getUpkeepAffectingDeficit() {
        return CurrentIndustry.getMaxDeficit(Commodities.FUEL, Commodities.SUPPLIES, Commodities.CREW);
    }


    @Override
    public void addPostDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode) {
        if (!market.isPlayerOwned()) return;

        float opad = 10f;

//		tooltip.addPara("As long as demand is met, allows the colony to stockpile fuel, supplies, and crew, even " +
//						"if it does not produce them locally.", opad);
    }
    @Override
    public boolean hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode) {
        return mode != Industry.IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional();
    }

    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        if (mode != Industry.IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional()) {
            MutableStat fake = new MutableStat(0);

            String desc = CurrentIndustry.getNameForModifier();
            float a = BASE_ACCESSIBILITY;
            if (a > 0) {
                fake.modifyFlat(CurrentIndustry.getModId(0), a, desc);
            }
            float total = a;
            String totalStr = "+" + (int)Math.round(total * 100f) + "%";
            Color h = Misc.getHighlightColor();
            if (total < 0) {
                h = Misc.getNegativeHighlightColor();
                totalStr = "" + (int)Math.round(total * 100f) + "%";
            }
            float opad = 10f;
            float pad = 3f;
            if (total >= 0) {
                tooltip.addPara("Accessibility bonus: %s", opad, h, totalStr);
            } else {
                tooltip.addPara("Accessibility penalty: %s", opad, h, totalStr);
            }

            tooltip.addPara("As long as demand is met, allows the colony to stockpile fuel, supplies, and crew, even " +
                    "if it does not produce them locally. The stockpile levels exceed those generated by equivalent local production.", opad);
        }
    }

    @Override
    public void applyAlphaCoreModifiers() {
        if (market.isPlayerOwned()) {
            SubmarketPlugin sub = Misc.getLocalResources(market);
            if (sub instanceof LocalResourcesSubmarketPlugin) {
                float bonus = market.getSize() * Global.getSettings().getFloat("stockpileMultExcess");
                LocalResourcesSubmarketPlugin lr = (LocalResourcesSubmarketPlugin) sub;
                lr.getStockpilingBonus(Commodities.FUEL).modifyFlat(CurrentIndustry.getModId(1), bonus);
                lr.getStockpilingBonus(Commodities.SUPPLIES).modifyFlat(CurrentIndustry.getModId(1), bonus);
                lr.getStockpilingBonus(Commodities.CREW).modifyFlat(CurrentIndustry.getModId(1), bonus);
            }
        }
    }

    @Override
    public void applyNoAICoreModifiers() {
        if (market.isPlayerOwned()) {
            SubmarketPlugin sub = Misc.getLocalResources(market);
            if (sub instanceof LocalResourcesSubmarketPlugin) {
                LocalResourcesSubmarketPlugin lr = (LocalResourcesSubmarketPlugin) sub;
                lr.getStockpilingBonus(Commodities.FUEL).unmodifyFlat(CurrentIndustry.getModId(1));
                lr.getStockpilingBonus(Commodities.SUPPLIES).unmodifyFlat(CurrentIndustry.getModId(1));
                lr.getStockpilingBonus(Commodities.CREW).unmodifyFlat(CurrentIndustry.getModId(1));
            }
        }
    }

    @Override
    public void applyAlphaCoreSupplyAndDemandModifiers() {
        demandReduction.modifyFlat(CurrentIndustry.getModId(0), DEMAND_REDUCTION, "Alpha core");
    }
    @Override
    public void addAlphaCoreDescription(TooltipMakerAPI tooltip, Industry.AICoreDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        String pre = "Alpha-level AI core currently assigned. ";
        if (mode == Industry.AICoreDescriptionMode.MANAGE_CORE_DIALOG_LIST || mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            pre = "Alpha-level AI core. ";
        }
        float a = ALPHA_CORE_ACCESSIBILITY;
        String aStr = "" + (int)Math.round(a * 100f) + "%";

        if (mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
            text.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                            "Greatly increases stockpiles.", 0f, highlight,
                    "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                    aStr);
            tooltip.addImageWithText(opad);
            return;
        }

        tooltip.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                        "Greatly increases stockpiles.", opad, highlight,
                "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                aStr);

    }

    @Override
    public boolean isAvailableToBuild() {
        return market.hasSpaceport();
    }
    @Override
    public String getUnavailableReason() {
        return "Requires a functional spaceport";
    }


    @Override
    public boolean canImprove() {
        return true;
    }
    @Override
    public void applyImproveModifiers() {
        if (CurrentIndustry.isImproved()) {
            market.getAccessibilityMod().modifyFlat(CurrentIndustry.getModId(3), IMPROVE_ACCESSIBILITY,
                    CurrentIndustry.getImprovementsDescForModifiers() + " (" + CurrentIndustry.getNameForModifier() + ")");
        } else {
            market.getAccessibilityMod().unmodifyFlat(CurrentIndustry.getModId(3));
        }
    }
    @Override
    public void addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        float a = IMPROVE_ACCESSIBILITY;
        String aStr = "" + (int)Math.round(a * 100f) + "%";

        if (mode == Industry.ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            info.addPara("Accessibility increased by %s.", 0f, highlight, aStr);
        } else {
            info.addPara("Increases accessibility by %s.", 0f, highlight, aStr);
        }

        info.addSpacer(opad);
        super.addImproveDesc(info, mode);
    }
}
