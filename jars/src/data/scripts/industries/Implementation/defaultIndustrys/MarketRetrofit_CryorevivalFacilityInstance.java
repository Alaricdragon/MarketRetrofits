package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.ColonyOtherFactorsListener;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;
import org.lwjgl.util.vector.Vector2f;



import java.awt.Color;

public class MarketRetrofit_CryorevivalFacilityInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_CryorevivalFacilityInstance(String name, float orderT) {
        super(name, orderT);
    }


    public static float ALPHA_CORE_BONUS = 1f;
    public static float IMPROVE_BONUS = 1f;

    public static float MIN_BONUS_MULT = 0.1f;
    public static float MAX_BONUS_DIST_LY = 10f;

    public static float MAX_BONUS_WHEN_UNMET_DEMAND = 0.5f;
    @Override
    public void apply() {
        super.apply(true);

        //int size = market.getSize();
        //demand(Commodities.HEAVY_MACHINERY, size);
        CurrentIndustry.demand(Commodities.ORGANICS, 10);
    }


    @Override
    public void unapply() {
        super.unapply();
    }
    @Override
    public boolean hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode) {
        return mode != Industry.IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional();
    }

    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        if (mode != Industry.IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional()) {
            Color h = Misc.getHighlightColor();
            float opad = 10f;

            float bonus = getImmigrationBonus();
            float max = getMaxImmigrationBonus();

            float distMult = getDistancePopulationMult(market.getLocationInHyperspace());
            float demandMult = getDemandMetPopulationMult();

            Pair<SectorEntityToken, Float> p = getNearestCryosleeper(market.getLocationInHyperspace(), true);
            if (p != null) {
                String dStr = "" + Misc.getRoundedValueMaxOneAfterDecimal(p.two);
                String lights = "light-years";
                if (dStr.equals("1")) lights = "light-year";
                tooltip.addPara("Distance to nearest cryosleeper: %s " + lights + ", growth bonus multiplier: %s.",
                        opad, h,
                        "" + Misc.getRoundedValueMaxOneAfterDecimal(p.two),
                        "" + (int)Math.round(distMult * 100f) + "%");
            }
            if (mode != Industry.IndustryTooltipMode.NORMAL) {
                tooltip.addPara("If any demand is unmet, " +
                                "the maximum growth bonus is reduced by %s.",
                        opad, h,
                        "" + (int)Math.round(MAX_BONUS_WHEN_UNMET_DEMAND * 100f) + "%");
            } else {
                tooltip.addPara("%s growth bonus multiplier based on met demand. If any demand is unmet, " +
                                "the maximum bonus is reduced by %s.",
                        opad, h,
                        "" + (int)Math.round(demandMult * 100f) + "%",
                        "" + (int)Math.round(MAX_BONUS_WHEN_UNMET_DEMAND * 100f) + "%");
            }


            tooltip.addPara("Population growth: %s (max for colony size: %s)", opad, h, "+" + Math.round(bonus), "+" + Math.round(max));
        }
    }


    @Override
    public boolean isAvailableToBuild() {
        //if (Global.getSettings().isDevMode()) return true;

        return getDistancePopulationMult(market.getLocationInHyperspace()) > 0;

//		StarSystemAPI system = market.getStarSystem();
//		if (system == null) return false;
//		for (SectorEntityToken entity : system.getEntitiesWithTag(Tags.CRYOSLEEPER)) {
//			if (entity.getMemoryWithoutUpdate().contains("$usable")) {
//				return true;
//			}
//		}
//		return false;
    }


    @Override
    public boolean showWhenUnavailable() {
        return false;
    }


    @Override
    public String getUnavailableReason() {
        return "Requires in-system cryosleeper"; // unused since not shown when unavailable
    }
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        if (CurrentIndustry.isFunctional()) {
            incoming.add(Factions.SLEEPER, getImmigrationBonus() * 2f);
            incoming.getWeight().modifyFlat(CurrentIndustry.getModId(), getImmigrationBonus(), CurrentIndustry.getNameForModifier());

            if (Commodities.ALPHA_CORE.equals(CurrentIndustry.getAICoreId())) {
                incoming.getWeight().modifyFlat(CurrentIndustry.getModId(1), (int)(getImmigrationBonus() * ALPHA_CORE_BONUS),
                        "Alpha core (" + CurrentIndustry.getNameForModifier() + ")");
            }
            if (CurrentIndustry.isImproved()) {
                incoming.getWeight().modifyFlat(CurrentIndustry.getModId(2), (int)(getImmigrationBonus() * IMPROVE_BONUS),
                        CurrentIndustry.getImprovementsDescForModifiers() + " (" + CurrentIndustry.getNameForModifier() + ")");
            }
        }
    }


    @Override
    public void applyAlphaCoreModifiers() {
    }

    @Override
    public void applyNoAICoreModifiers() {
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
        float a = getImmigrationBonus() * ALPHA_CORE_BONUS;
        String str = "+" + (int)Math.round(a);

        if (mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
            text.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                            "%s population growth.", 0f, highlight,
                    "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                    str);
            tooltip.addImageWithText(opad);
            return;
        }

        tooltip.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                        "%s population growth.", opad, highlight,
                "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                str);

    }


    @Override
    public boolean canImprove() {
        return true;
    }
    @Override
    public void addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        float a = getImmigrationBonus() * IMPROVE_BONUS;
        String str = "" + (int)Math.round(a);

        if (mode == Industry.ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            info.addPara("Population growth increased by %s.", 0f, highlight,str);
        } else {
            info.addPara("Increases population growth by %s.", 0f, highlight,str);
        }

        info.addSpacer(opad);
        super.addImproveDesc(info, mode);
    }


    public static Pair<SectorEntityToken, Float> getNearestCryosleeper(Vector2f locInHyper, boolean usable) {
        SectorEntityToken nearest = null;
        float minDist = Float.MAX_VALUE;

        for (SectorEntityToken entity : Global.getSector().getCustomEntitiesWithTag(Tags.CRYOSLEEPER)) {
            if (!usable || entity.getMemoryWithoutUpdate().contains("$usable")) {
                float dist = Misc.getDistanceLY(locInHyper, entity.getLocationInHyperspace());
                if (Math.round(dist * 10f) <= MAX_BONUS_DIST_LY * 10f) {
                    dist = MAX_BONUS_DIST_LY;
                }
                if (dist < minDist) {
                    minDist = dist;
                    nearest = entity;
                }
            }
        }

        if (nearest == null) return null;

        return new Pair<SectorEntityToken, Float>(nearest, minDist);
    }


    public static float getDistancePopulationMult(Vector2f locInHyper) {
        Pair<SectorEntityToken, Float> p = getNearestCryosleeper(locInHyper, true);
        if (p == null) return 0f;
        if (p.two > MAX_BONUS_DIST_LY) return 0f;


        float f = 1f - p.two / MAX_BONUS_DIST_LY;
        if (f < 0f) f = 0f;
        if (f > 1f) f = 1f;

        float mult = MIN_BONUS_MULT + (1f - MIN_BONUS_MULT) * f;

        return mult;
    }

    public float getDemandMetPopulationMult() {
        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.ORGANICS);
        float demand = getDemand(Commodities.ORGANICS).getQuantity().getModifiedValue();
        float def = deficit.two;
        if (def > demand) def = demand;

        float mult = 1f;
        if (def > 0 && demand > 0) {
            mult = (demand - def) / demand;
            mult *= MAX_BONUS_WHEN_UNMET_DEMAND;
        }
        return mult;
    }

    public float getImmigrationBonus() {
        return getMaxImmigrationBonus() * getDemandMetPopulationMult() * getDistancePopulationMult(market.getLocationInHyperspace());
    }

    public float getMaxImmigrationBonus() {
        return CurrentIndustry.getSizeMult() * 10f;
    }


    public static class CryosleeperFactor implements ColonyOtherFactorsListener {
        public boolean isActiveFactorFor(SectorEntityToken entity) {
            return getNearestCryosleeper(entity.getLocationInHyperspace(), true) != null;
        }

        public void printOtherFactors(TooltipMakerAPI text, SectorEntityToken entity) {
            float distMult = getDistancePopulationMult(entity.getLocationInHyperspace());

            Pair<SectorEntityToken, Float> p = getNearestCryosleeper(entity.getLocationInHyperspace(), true);
            if (p != null) {
                Color h = Misc.getHighlightColor();
                float opad = 10f;

                String dStr = "" + Misc.getRoundedValueMaxOneAfterDecimal(p.two);
                String lights = "light-years";
                if (dStr.equals("1")) lights = "light-year";

                if (p.two > MAX_BONUS_DIST_LY) {
                    text.addPara("The nearest cryosleeper is located in the " +
                                    p.one.getContainingLocation().getNameWithLowercaseType() + ", %s " + lights + " away. The maximum " +
                                    "range at which sleepers can be safely brought over for revival is %s light-years.",
                            opad, h,
                            "" + Misc.getRoundedValueMaxOneAfterDecimal(p.two),
                            "" + (int)MAX_BONUS_DIST_LY);
                } else {
                    text.addPara("The nearest cryosleeper is located in the " +
                                    p.one.getContainingLocation().getNameWithLowercaseType() + ", %s " + lights + " away, allowing " +
                                    "a Cryorevival Facility built here to operate at %s effectiveness.",
                            opad, h,
                            "" + Misc.getRoundedValueMaxOneAfterDecimal(p.two),
                            "" + (int)Math.round(distMult * 100f) + "%");
                }
            }
        }
    }

}
