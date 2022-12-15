package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

public class MarketRetrofit_CryosanctumInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_CryosanctumInstance(String name, float orderT) {
        super(name, orderT);
    }

    @Override
    public void apply() {
        super.apply(false);

        int size = 6;

        CurrentIndustry.applyIncomeAndUpkeep(size);

        demand(Commodities.SUPPLIES, size - 3);
        demand(Commodities.ORGANICS, size - 3);

        supply(Commodities.ORGANS, size);


        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.ORGANICS, Commodities.SUPPLIES);
        // that's right.
        if (deficit.two > 0) deficit.two = -1;

        CurrentIndustry.applyDeficitToProduction(1, deficit, Commodities.ORGANS);

        if (!isFunctional()) {
            supply.clear();
        }
    }


    @Override
    public void unapply() {
        super.unapply();
    }


    @Override
    public boolean isAvailableToBuild() {
        return false;
    }
    @Override
    public boolean showWhenUnavailable() {
        return false;
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
