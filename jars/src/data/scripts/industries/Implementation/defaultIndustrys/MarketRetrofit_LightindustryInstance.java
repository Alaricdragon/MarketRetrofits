package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

public class MarketRetrofit_LightindustryInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_LightindustryInstance(String name, float orderT) {
        super(name, orderT);
    }

    @Override
    public void apply() {
        super.apply(true);

        int size = market.getSize();

        CurrentIndustry.demand(Commodities.ORGANICS, size);

        CurrentIndustry.supply(Commodities.DOMESTIC_GOODS, size);
        //supply(Commodities.SUPPLIES, size - 3);

        //if (!market.getFaction().isIllegal(Commodities.LUXURY_GOODS)) {
        if (!market.isIllegal(Commodities.LUXURY_GOODS)) {
            CurrentIndustry.supply(Commodities.LUXURY_GOODS, size - 2);
        } else {
            CurrentIndustry.supply(Commodities.LUXURY_GOODS, 0);
        }
        //if (!market.getFaction().isIllegal(Commodities.DRUGS)) {
        if (!market.isIllegal(Commodities.DRUGS)) {
            CurrentIndustry.supply(Commodities.DRUGS, size - 2);
        } else {
            CurrentIndustry.supply(Commodities.DRUGS, 0);
        }

        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.ORGANICS);

        CurrentIndustry.applyDeficitToProduction(1, deficit,
                Commodities.DOMESTIC_GOODS,
                Commodities.LUXURY_GOODS,
                //Commodities.SUPPLIES,
                Commodities.DRUGS);

        if (!CurrentIndustry.isFunctional()) {
            supply.clear();
        }
    }


    @Override
    public void unapply() {
        super.unapply();
    }

    @Override
    public String getCurrentImage() {
        float size = market.getSize();
        PlanetAPI planet = market.getPlanetEntity();
        if (planet == null || planet.isGasGiant()) {
            if (size <= SIZE_FOR_SMALL_IMAGE) {
                return Global.getSettings().getSpriteName("industry", "light_industry_orbital_low");
            }
            if (size >= SIZE_FOR_LARGE_IMAGE) {
                return Global.getSettings().getSpriteName("industry", "light_industry_orbital_high");
            }
            return Global.getSettings().getSpriteName("industry", "light_industry_orbital");
        }
        else
        {
            if (size <= SIZE_FOR_SMALL_IMAGE) {
                return Global.getSettings().getSpriteName("industry", "light_industry_low");
            }
            if (size >= SIZE_FOR_LARGE_IMAGE) {
                return Global.getSettings().getSpriteName("industry", "light_industry_high");
            }
        }

        return super.getCurrentImage();
    }

    @Override
    public boolean canImproveToIncreaseProduction() {
        return true;
    }

}
