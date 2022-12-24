package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.ids.Strings;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

import java.awt.Color;

public class MarketRetrofit_GroundDefensesInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_GroundDefensesInstance(String name, float orderT) {
        super(name, orderT);
    }


    public static float DEFENSE_BONUS_BASE = 1f;
    public static float DEFENSE_BONUS_BATTERIES = 2f;

    public static float IMPROVE_DEFENSE_BONUS = 0.25f;

    @Override
    public void apply() {
        super.apply(true);

        int size = market.getSize();
        boolean hb = Industries.HEAVYBATTERIES.equals(CurrentIndustry.getId());

        CurrentIndustry.demand(Commodities.SUPPLIES, size);
        CurrentIndustry.demand(Commodities.MARINES, size);
        CurrentIndustry.demand(Commodities.HAND_WEAPONS, size - 2);

        //supply(Commodities.MARINES, size - 2);

//		Pair<String, Integer> deficit = getMaxDeficit(Commodities.HAND_WEAPONS);
//		applyDeficitToProduction(1, deficit, Commodities.MARINES);

        CurrentIndustry.modifyStabilityWithBaseMod();

        float mult = getDeficitMult(Commodities.SUPPLIES, Commodities.MARINES, Commodities.HAND_WEAPONS);
        String extra = "";
        if (mult != 1) {
            String com = CurrentIndustry.getMaxDeficit(Commodities.SUPPLIES, Commodities.MARINES, Commodities.HAND_WEAPONS).one;
            extra = " (" + getDeficitText(com).toLowerCase() + ")";
        }
        float bonus = hb ? DEFENSE_BONUS_BATTERIES : DEFENSE_BONUS_BASE;
        market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD)
                .modifyMult(CurrentIndustry.getModId(), 1f + bonus * mult, CurrentIndustry.getNameForModifier() + extra);
//		market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD)
//		.modifyPercent(getModId(), bonus * mult * 100f, getNameForModifier() + extra);


        if (!CurrentIndustry.isFunctional()) {
            supply.clear();
            CurrentIndustry.unapply();
        }

    }

    @Override
    public void unapply() {
        super.unapply();

        CurrentIndustry.unmodifyStabilityWithBaseMod();

        market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).unmodifyMult(CurrentIndustry.getModId());
        //market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).unmodifyPercent(getModId());
        //market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).unmodifyFlat(getModId());
    }

    @Override
    public String getCurrentImage() {
        boolean batteries = Industries.HEAVYBATTERIES.equals(CurrentIndustry.getId());
        if (batteries) {
            PlanetAPI planet = market.getPlanetEntity();
            if (planet == null || planet.isGasGiant()) {
                return Global.getSettings().getSpriteName("industry", "heavy_batteries_orbital");
            }
        }
        return super.getCurrentImage();
    }
    @Override
    public boolean isDemandLegal(CommodityOnMarketAPI com) {
        return true;
    }
    @Override
    public boolean isSupplyLegal(CommodityOnMarketAPI com) {
        return true;
    }

    @Override
    public boolean hasPostDemandSection(boolean hasDemand, Industry.IndustryTooltipMode mode) {
        //return mode == IndustryTooltipMode.NORMAL && isFunctional();
        return mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional();
    }

    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, IndustryTooltipMode mode) {
        //if (mode == IndustryTooltipMode.NORMAL && isFunctional()) {
        if (mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional()) {
            CurrentIndustry.addStabilityPostDemandSection(tooltip, hasDemand, mode);

            boolean hb = Industries.HEAVYBATTERIES.equals(CurrentIndustry.getId());
            float bonus = hb ? DEFENSE_BONUS_BATTERIES : DEFENSE_BONUS_BASE;
            CurrentIndustry.addGroundDefensesImpactSection(tooltip, bonus, Commodities.SUPPLIES, Commodities.MARINES, Commodities.HAND_WEAPONS);
        }
    }

    @Override
    public int getBaseStabilityMod() {
        return 1;
    }

    @Override
    public Pair<String, Integer> getStabilityAffectingDeficit() {
        return CurrentIndustry.getMaxDeficit(Commodities.SUPPLIES, Commodities.MARINES, Commodities.HAND_WEAPONS);
    }


    public static float ALPHA_CORE_BONUS = 0.5f;
    @Override
    public void applyAlphaCoreModifiers() {
        market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).modifyMult(
                CurrentIndustry.getModId(1), 1f + ALPHA_CORE_BONUS, "Alpha core (" + CurrentIndustry.getNameForModifier() + ")");
    }

    @Override
    public void applyNoAICoreModifiers() {
        market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).unmodifyMult(CurrentIndustry.getModId(1));
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
        float a = ALPHA_CORE_BONUS;
        //String str = Strings.X + (int)Math.round(a * 100f) + "%";
        String str = Strings.X + (1f + a) + "";

        if (mode == AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
            text.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                            "Increases ground defenses by %s.", 0f, highlight,
                    "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                    str);
            tooltip.addImageWithText(opad);
            return;
        }

        tooltip.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                        "Increases ground defenses by %s.", opad, highlight,
                "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                str);

    }


    @Override
    public boolean canImprove() {
        return true;
    }
    @Override
    public void applyImproveModifiers() {
        if (CurrentIndustry.isImproved()) {
            market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).modifyMult("ground_defenses_improve",
                    1f + IMPROVE_DEFENSE_BONUS,
                    CurrentIndustry.getImprovementsDescForModifiers() + " (" + CurrentIndustry.getNameForModifier() + ")");
        } else {
            market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).unmodifyMult("ground_defenses_improve");
        }
    }
    @Override
    public void addImproveDesc(TooltipMakerAPI info, ImprovementDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        float a = IMPROVE_DEFENSE_BONUS;
        String str = Strings.X + (1f + a) + "";

        if (mode == ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            info.addPara("Ground defenses increased by %s.", 0f, highlight, str);
        } else {
            info.addPara("Increases ground defenses by %s.", 0f, highlight, str);
        }

        info.addSpacer(opad);
        super.addImproveDesc(info, mode);
    }

    @Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(String commodityId, MarketCMD.RaidDangerLevel level) {
        return level.next();
    }

    @Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(String itemId, String data, MarketCMD.RaidDangerLevel level) {
        return level.next();
    }

}
