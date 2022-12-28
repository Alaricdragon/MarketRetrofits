package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.econ.ResourceDepositsCondition;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Items;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetorfits_ExstraData;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

import java.awt.*;

public class MarketRetrofit_MiningInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_MiningInstance(String name, float orderT) {
        super(name, orderT);
    }
    public boolean shownPlasmaNetVisuals = false;
    private static String shownPlasmaNetVisualsName = "shownPlasmaNetVisuals";
    @Override
    public void getExtraDataFromIndustry(MarketRetorfits_ExstraData extraData){
        shownPlasmaNetVisuals = extraData.getBoolean(shownPlasmaNetVisualsName);
    }
    @Override
    public void setExtraDataToIndustry(MarketRetorfits_ExstraData extraData){
        extraData.addData(shownPlasmaNetVisualsName,shownPlasmaNetVisuals);
    }
    @Override
    public void apply() {
        super.apply(true);

        int size = market.getSize();

        CurrentIndustry.demand(Commodities.HEAVY_MACHINERY, size - 3);
        CurrentIndustry.demand(Commodities.DRUGS, size);

        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.HEAVY_MACHINERY);
        CurrentIndustry.applyDeficitToProduction(0, deficit,
                Commodities.ORE,
                Commodities.RARE_ORE,
                Commodities.ORGANICS,
                Commodities.VOLATILES);

        if (!CurrentIndustry.isFunctional()) {
            supply.clear();
        }
    }


    @Override
    public void unapply() {
        super.unapply();
    }
    @Override
    public boolean hasPostDemandSection(boolean hasDemand, IndustryTooltipMode mode) {
        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.DRUGS);
        if (deficit.two <= 0) return false;
        //return mode == IndustryTooltipMode.NORMAL && isFunctional();
        return mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional();
    }

    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, IndustryTooltipMode mode) {
        //if (mode == IndustryTooltipMode.NORMAL && isFunctional()) {
        if (mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional()) {
            Color h = Misc.getHighlightColor();
            float opad = 10f;
            float pad = 3f;

            Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.DRUGS);
            if (deficit.two > 0) {
                tooltip.addPara(getDeficitText(Commodities.DRUGS) + ": %s units. Reduced colony growth.", pad, h, "" + deficit.two);
            }
        }
    }


    @Override
    public boolean isAvailableToBuild() {
        if (!super.isAvailableToBuild()) return false;

        for (MarketConditionAPI mc : market.getConditions()) {
            String commodity = ResourceDepositsCondition.COMMODITY.get(mc.getId());
            if (commodity != null) {
                String industry = ResourceDepositsCondition.INDUSTRY.get(commodity);
                if (CurrentIndustry.getId().equals(industry)) return true;
            }
        }
        return false;
    }

    @Override
    public String getUnavailableReason() {
        if (!super.isAvailableToBuild()) return super.getUnavailableReason();

        return "Requires resource deposits";
    }
    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.DRUGS);
        if (deficit.two > 0) {
            incoming.getWeight().modifyFlat(CurrentIndustry.getModId(), -deficit.two, "Mining: drug shortage");
        }
    }

    @Override
    public String getCurrentImage() {
        float size = market.getSize();
        if (size <= SIZE_FOR_SMALL_IMAGE) {
            return Global.getSettings().getSpriteName("industry", "mining_low");
        }
        return super.getCurrentImage();
    }

    @Override
    public float getPatherInterest() {
        return 1f + super.getPatherInterest();
    }

    @Override
    public boolean canImproveToIncreaseProduction() {
        return true;
    }


    public void applyVisuals(PlanetAPI planet) {
        if (planet == null) return;
        planet.getSpec().setShieldTexture2(Global.getSettings().getSpriteName("industry", "plasma_net_texture"));
        planet.getSpec().setShieldThickness2(0.15f);
        //planet.getSpec().setShieldColor2(new Color(255,255,255,175));
        planet.getSpec().setShieldColor2(new Color(255,255,255,255));
        planet.applySpecChanges();
        shownPlasmaNetVisuals = true;
    }
    public void unapplyVisuals(PlanetAPI planet) {
        if (planet == null) return;
        planet.getSpec().setShieldTexture2(null);
        planet.getSpec().setShieldThickness2(0f);
        planet.getSpec().setShieldColor2(null);
        planet.applySpecChanges();
        shownPlasmaNetVisuals = false;
    }
    @Override
    public void setSpecialItem(SpecialItemData special) {
        super.setSpecialItem(special);
        if (shownPlasmaNetVisuals && (special == null || !special.getId().equals(Items.PLASMA_DYNAMO))) {
            unapplyVisuals(market.getPlanetEntity());
        }

        if (special != null && special.getId().equals(Items.PLASMA_DYNAMO)) {
            applyVisuals(market.getPlanetEntity());
        }

    }



}
