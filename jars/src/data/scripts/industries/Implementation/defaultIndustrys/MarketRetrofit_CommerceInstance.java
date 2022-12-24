package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommoditySpecAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.SubmarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Submarkets;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;


import java.awt.Color;

public class MarketRetrofit_CommerceInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_CommerceInstance(String name, float orderT) {
        super(name, orderT);
    }



    public static float BASE_BONUS = 25f;
    public static float ALPHA_CORE_BONUS = 25f;
    public static float IMPROVE_BONUS = 25f;

    public static float STABILITY_PELANTY = 3f;

    //public transient CargoAPI savedCargo = null;
    public transient SubmarketAPI saved = null;
    private static String savedName = "saved";
    @Override
    public void apply() {
        super.apply(true);
        saved = (SubmarketAPI) CurrentIndustry.exstraData.getData(savedName);
        if (CurrentIndustry.isFunctional() && market.isPlayerOwned()) {
            SubmarketAPI open = market.getSubmarket(Submarkets.SUBMARKET_OPEN);
            if (open == null) {
                if (saved != null) {
                    market.addSubmarket(saved);
                } else {
                    market.addSubmarket(Submarkets.SUBMARKET_OPEN);
                    SubmarketAPI sub = market.getSubmarket(Submarkets.SUBMARKET_OPEN);
                    sub.setFaction(Global.getSector().getFaction(Factions.INDEPENDENT));
                    Global.getSector().getEconomy().forceStockpileUpdate(market);
                }

//				if (savedCargo != null) {
//					open = market.getSubmarket(Submarkets.SUBMARKET_OPEN);
//					if (open != null) {
//						open.getCargo().clear();
//						open.getCargo().addAll(savedCargo);
//						if (open.getPlugin() instanceof BaseSubmarketPlugin) {
//							BaseSubmarketPlugin base = (BaseSubmarketPlugin) open.getPlugin();
//							base.setSinceLastCargoUpdate(0);
//							base.setSinceSWUpdate(0);
//						}
//					}
//				}
            }
        } else if (market.isPlayerOwned()) {
            market.removeSubmarket(Submarkets.SUBMARKET_OPEN);
        }

        //modifyStabilityWithBaseMod();
        market.getStability().modifyFlat(CurrentIndustry.getModId(), -STABILITY_PELANTY, CurrentIndustry.getNameForModifier());

        market.getIncomeMult().modifyPercent(CurrentIndustry.getModId(0), BASE_BONUS, CurrentIndustry.getNameForModifier());

        if (!CurrentIndustry.isFunctional()) {
            CurrentIndustry.unapply();
        }
    }


    @Override
    public void unapply() {
        super.unapply();

        if (market.isPlayerOwned()) {
            SubmarketAPI open = market.getSubmarket(Submarkets.SUBMARKET_OPEN);
            CurrentIndustry.exstraData.addData(savedName,open);
            //saved = open;
//			if (open.getPlugin() instanceof BaseSubmarketPlugin) {
//				BaseSubmarketPlugin base = (BaseSubmarketPlugin) open.getPlugin();
//				if (base.getSinceLastCargoUpdate() < 30) {
//					savedCargo = open.getCargo();
//				}
//			}
            market.removeSubmarket(Submarkets.SUBMARKET_OPEN);
        }

        market.getStability().unmodifyFlat(CurrentIndustry.getModId());

        market.getIncomeMult().unmodifyPercent(CurrentIndustry.getModId(0));
    }
    @Override
    public void addStabilityPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        Color h = Misc.getHighlightColor();
        float opad = 10f;

        float a = BASE_BONUS;
        String aStr = "+" + (int)Math.round(a * 1f) + "%";
        tooltip.addPara("Colony income: %s", opad, h, aStr);

        h = Misc.getNegativeHighlightColor();
        tooltip.addPara("Stability penalty: %s", opad, h, "" + -(int)STABILITY_PELANTY);
    }

    @Override
    public void addRightAfterDescriptionSection(TooltipMakerAPI tooltip, Industry.IndustryTooltipMode mode) {
        if (market.isPlayerOwned() || currTooltipMode == Industry.IndustryTooltipMode.ADD_INDUSTRY) {
            tooltip.addPara("Adds an independent \'Open Market\' that the colony's owner is able to trade with.", 10f);
        }
    }

    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, Industry.IndustryTooltipMode mode) {
        if (mode != Industry.IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional()) {
            CurrentIndustry.addStabilityPostDemandSection(tooltip, hasDemand, mode);
        }
    }
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        incoming.add(Factions.TRITACHYON, 10f);
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
    public String getCurrentImage() {
        float size = market.getSize();
        if (size <= SIZE_FOR_SMALL_IMAGE) {
            return Global.getSettings().getSpriteName("industry", "commerce_low");
        }
        if (size >= SIZE_FOR_LARGE_IMAGE) {
            return Global.getSettings().getSpriteName("industry", "commerce_high");
        }

        return super.getCurrentImage();
    }


    //market.getIncomeMult().modifyMult(id, INCOME_MULT, "Industrial planning");
    @Override
    public void applyAlphaCoreModifiers() {
        market.getIncomeMult().modifyPercent(CurrentIndustry.getModId(1), ALPHA_CORE_BONUS, "Alpha core (" + CurrentIndustry.getNameForModifier() + ")");
    }

    @Override
    public void applyNoAICoreModifiers() {
        market.getIncomeMult().unmodifyPercent(CurrentIndustry.getModId(1));
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
        float a = ALPHA_CORE_BONUS;
        String str = "" + (int) Math.round(a) + "%";

        if (mode == Industry.AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
            text.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                            "Increases colony income by %s.", 0f, highlight,
                    "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                    str);
            tooltip.addImageWithText(opad);
            return;
        }

        tooltip.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                        "Increases colony income by %s.", opad, highlight,
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
            market.getIncomeMult().modifyPercent(CurrentIndustry.getModId(2), IMPROVE_BONUS,
                    CurrentIndustry.getImprovementsDescForModifiers() + " (" + CurrentIndustry.getNameForModifier() + ")");
        } else {
            market.getIncomeMult().unmodifyPercent(CurrentIndustry.getModId(2));
        }
    }
    @Override
    public void addImproveDesc(TooltipMakerAPI info, Industry.ImprovementDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        float a = IMPROVE_BONUS;
        String aStr = "" + (int)Math.round(a * 1f) + "%";

        if (mode == Industry.ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            info.addPara("Colony income increased by %s.", 0f, highlight, aStr);
        } else {
            info.addPara("Increases colony income by %s.", 0f, highlight, aStr);
        }

        info.addSpacer(opad);
        super.addImproveDesc(info, mode);
    }

}
