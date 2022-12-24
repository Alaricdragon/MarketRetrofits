package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

public class MarketRetrofit_RefiningInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_RefiningInstance(String name, float orderT) {
        super(name, orderT);
    }
    @Override
    public void apply() {
        super.apply(true);

        int size = market.getSize();


        CurrentIndustry.demand(Commodities.HEAVY_MACHINERY, size - 2); // have to keep it low since it can be circular
        CurrentIndustry.demand(Commodities.ORE, size + 2);
        CurrentIndustry.demand(Commodities.RARE_ORE, size);

        CurrentIndustry.supply(Commodities.METALS, size);
        CurrentIndustry.supply(Commodities.RARE_METALS, size - 2);

        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.HEAVY_MACHINERY, Commodities.ORE);
        CurrentIndustry.applyDeficitToProduction(1, deficit, Commodities.METALS);

        deficit = CurrentIndustry.getMaxDeficit(Commodities.HEAVY_MACHINERY, Commodities.RARE_ORE);
        CurrentIndustry.applyDeficitToProduction(1, deficit, Commodities.RARE_METALS);

        if (!CurrentIndustry.isFunctional()) {
            supply.clear();
        }
    }


    @Override
    public void unapply() {
        super.unapply();
    }

    @Override
    public float getPatherInterest() {
        return 2f + super.getPatherInterest();
    }

    @Override
    public boolean canImproveToIncreaseProduction() {
        return true;
    }

}
