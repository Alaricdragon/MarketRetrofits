package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

import java.awt.Color;

public class MarketRetrofit_SpaceportInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_SpaceportInstance(String name, float orderT) {
        super(name, orderT);
    }


    public static float OFFICER_PROB_MOD = 0.1f;
    public static float OFFICER_PROB_MOD_MEGA = 0.2f;

    public static float UPKEEP_MULT_PER_DEFICIT = 0.1f;

    public static final float BASE_ACCESSIBILITY = 0.5f;
    public static final float MEGAPORT_ACCESSIBILITY = 0.8f;

    public static final float ALPHA_CORE_ACCESSIBILITY = 0.2f;
    public static final float IMPROVE_ACCESSIBILITY = 0.2f;

//	public static final float BASE_ACCESSIBILITY = 0f;
//	public static final float MEGAPORT_ACCESSIBILITY = 0.2f;

    @Override
    public void apply() {
        super.apply(true);

        int size = market.getSize();

        boolean megaport = Industries.MEGAPORT.equals(CurrentIndustry.getId());
        int extraSize = 0;
        if (megaport) extraSize = 2;

        CurrentIndustry.demand(Commodities.FUEL, size - 2 + extraSize);
        CurrentIndustry.demand(Commodities.SUPPLIES, size - 2 + extraSize);
        CurrentIndustry.demand(Commodities.SHIPS, size - 2 + extraSize);

        CurrentIndustry.supply(Commodities.CREW, size - 1 + extraSize);


        String desc = CurrentIndustry.getNameForModifier();

        Pair<String, Integer> deficit = getUpkeepAffectingDeficit();

        if (deficit.two > 0) {
            float loss = getUpkeepPenalty(deficit);
            CurrentIndustry.getUpkeep().modifyMult("deficit", 1f + loss, getDeficitText(deficit.one));
        } else {
            CurrentIndustry.getUpkeep().unmodifyMult("deficit");
        }

        market.setHasSpaceport(true);

        float a = BASE_ACCESSIBILITY;
        if (megaport) {
            a = MEGAPORT_ACCESSIBILITY;
        }
        if (a > 0) {
            market.getAccessibilityMod().modifyFlat(CurrentIndustry.getModId(0), a, desc);
        }

        float officerProb = OFFICER_PROB_MOD;
        if (megaport) officerProb = OFFICER_PROB_MOD_MEGA;
        market.getStats().getDynamic().getMod(Stats.OFFICER_PROB_MOD).modifyFlat(CurrentIndustry.getModId(0), officerProb);
        //market.getStats().getDynamic().getMod(Stats.OFFICER_IS_MERC_PROB_MOD).modifyFlat(getModId(0), officerProb);

        if (!CurrentIndustry.isFunctional()) {
//			if (isDisrupted() && !isBuilding()) {
//				market.getAccessibilityMod().modifyFlat(getModId(2), -1f, "Spaceport operations disrupted");
//				supply(Commodities.CREW, size - 1 + extraSize);
//			} else {
            supply.clear();
            CurrentIndustry.unapply();
            market.setHasSpaceport(true);
//			}
        }
    }

    @Override
    public void unapply() {
        super.unapply();

        market.setHasSpaceport(false);
        market.getAccessibilityMod().unmodifyFlat(CurrentIndustry.getModId(0));
        market.getAccessibilityMod().unmodifyFlat(CurrentIndustry.getModId(1));
        market.getAccessibilityMod().unmodifyFlat(CurrentIndustry.getModId(2));

        market.getStats().getDynamic().getMod(Stats.OFFICER_PROB_MOD).unmodifyFlat(CurrentIndustry.getModId(0));
        //market.getStats().getDynamic().getMod(Stats.OFFICER_IS_MERC_PROB_MOD).unmodifyFlat(getModId(0));
    }

    public float getUpkeepPenalty(Pair<String, Integer> deficit) {
        float loss = deficit.two * UPKEEP_MULT_PER_DEFICIT;
        if (loss < 0) loss = 0;
        return loss;
    }

    public Pair<String, Integer> getUpkeepAffectingDeficit() {
        return CurrentIndustry.getMaxDeficit(Commodities.FUEL, Commodities.SUPPLIES, Commodities.SHIPS);
    }
    @Override
    public boolean hasPostDemandSection(boolean hasDemand, IndustryTooltipMode mode) {
        //return mode == IndustryTooltipMode.NORMAL && isFunctional();
        return mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional();
    }

    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, IndustryTooltipMode mode) {
        //if (mode == IndustryTooltipMode.NORMAL && isFunctional()) {
        if (mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional()) {
            MutableStat fake = new MutableStat(0);

            boolean megaport = Industries.MEGAPORT.equals(CurrentIndustry.getId());
            String desc = CurrentIndustry.getNameForModifier();
            float a = BASE_ACCESSIBILITY;
            if (megaport) {
                a = MEGAPORT_ACCESSIBILITY;
            }
            if (a > 0) {
                fake.modifyFlat(CurrentIndustry.getModId(0), a, desc);
            }
            float total = a;
//			Pair<String, Integer> deficit = getAccessibilityAffectingDeficit();
//			float loss = getAccessibilityPenalty(deficit);
//			if (deficit.two > 0) {
//				fake.modifyFlat(getModId(1), -loss, getDeficitText(deficit.one));
//			}
//
//			float total = a - loss;
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

            float bonus = getPopulationGrowthBonus();
            tooltip.addPara("Population growth: %s", opad, h, "+" + (int)bonus);
//			tooltip.addStatModGrid(400, 50, opad, pad, fake, new StatModValueGetter() {
//				public String getPercentValue(StatMod mod) {
//					return null;
//				}
//				public String getMultValue(StatMod mod) {
//					return null;
//				}
//				public Color getModColor(StatMod mod) {
//					if (mod.value < 0) return Misc.getNegativeHighlightColor();
//					return null;
//				}
//				public String getFlatValue(StatMod mod) {
//					String prefix = mod.value >= 0 ? "+" : "";
//					return prefix + (int)Math.round(mod.value * 100f) + "%";
//				}
//			});

        }
    }

    public float getPopulationGrowthBonus() {
        boolean megaport = Industries.MEGAPORT.equals(CurrentIndustry.getId());
        float bonus = 2;
        if (megaport) {
            bonus = market.getSize();
        }
        return bonus;
    }
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        float bonus = getPopulationGrowthBonus();

        incoming.getWeight().modifyFlat(CurrentIndustry.getModId(), bonus, CurrentIndustry.getNameForModifier());
    }


    @Override
    public void applyAlphaCoreModifiers() {
        market.getAccessibilityMod().modifyFlat(CurrentIndustry.getModId(2), ALPHA_CORE_ACCESSIBILITY, "Alpha core (" + CurrentIndustry.getNameForModifier() + ")");
    }

    @Override
    public void applyNoAICoreModifiers() {
        market.getAccessibilityMod().unmodifyFlat(CurrentIndustry.getModId(2));
    }

    @Override
    public void applyAlphaCoreSupplyAndDemandModifiers() {
        demandReduction.modifyFlat(CurrentIndustry.getModId(0), DEMAND_REDUCTION, "Alpha core");
    }
    @Override
    public void addAlphaCoreDescription(TooltipMakerAPI tooltip, AICoreDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        String pre = "Alpha-level AI core currently assigned. ";
        if (mode == AICoreDescriptionMode.MANAGE_CORE_DIALOG_LIST || mode == AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            pre = "Alpha-level AI core. ";
        }
        float a = ALPHA_CORE_ACCESSIBILITY;
        String aStr = "" + (int)Math.round(a * 100f) + "%";

        if (mode == AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
            text.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                            "Increases accessibility by %s.", 0f, highlight,
                    "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                    aStr);
            tooltip.addImageWithText(opad);
            return;
        }

        tooltip.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                        "Increases accessibility by %s.", opad, highlight,
                "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                aStr);

    }

    @Override
    public boolean canImprove() {
        return true;
    }

    public void applyImproveModifiers() {
        // have to use a custom id - "spaceport_improve" - so that it's the same modifier when upgraded to megaport
        if (CurrentIndustry.isImproved()) {
            market.getAccessibilityMod().modifyFlat("spaceport_improve", IMPROVE_ACCESSIBILITY,
                    CurrentIndustry.getImprovementsDescForModifiers() + " (" + CurrentIndustry.getNameForModifier() + ")");
        } else {
            market.getAccessibilityMod().unmodifyFlat("spaceport_improve");
        }
    }

    public void addImproveDesc(TooltipMakerAPI info, ImprovementDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        float a = IMPROVE_ACCESSIBILITY;
        String aStr = "" + (int)Math.round(a * 100f) + "%";

        if (mode == ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            info.addPara("Accessibility increased by %s.", 0f, highlight, aStr);
        } else {
            info.addPara("Increases accessibility by %s.", 0f, highlight, aStr);
        }

        info.addSpacer(opad);
        super.addImproveDesc(info, mode);
    }


}
