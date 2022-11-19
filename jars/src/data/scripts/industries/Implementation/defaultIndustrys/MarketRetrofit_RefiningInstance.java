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


        demand(Commodities.HEAVY_MACHINERY, size - 2); // have to keep it low since it can be circular
        demand(Commodities.ORE, size + 2);
        demand(Commodities.RARE_ORE, size);

        supply(Commodities.METALS, size);
        supply(Commodities.RARE_METALS, size - 2);

        Pair<String, Integer> deficit = getMaxDeficit(Commodities.HEAVY_MACHINERY, Commodities.ORE);
        applyDeficitToProduction(1, deficit, Commodities.METALS);

        deficit = getMaxDeficit(Commodities.HEAVY_MACHINERY, Commodities.RARE_ORE);
        applyDeficitToProduction(1, deficit, Commodities.RARE_METALS);

        if (!isFunctional()) {
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
    protected boolean canImproveToIncreaseProduction() {
        return true;
    }

}
