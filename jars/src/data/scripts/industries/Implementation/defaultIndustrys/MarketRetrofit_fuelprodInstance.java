package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

public class MarketRetrofit_fuelprodInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_fuelprodInstance(String name, float orderT) {
        super(name, orderT);
    }

    @Override
    public void apply() {
        super.apply(true);
        supplyBonus.modifyFlat(getModId(2), market.getAdmin().getStats().getDynamic().getValue(Stats.FUEL_SUPPLY_BONUS_MOD, 0), "Administrator");

        int size = market.getSize();

        demand(Commodities.VOLATILES, size);
        demand(Commodities.HEAVY_MACHINERY, size - 2);

        supply(Commodities.FUEL, size - 2);

        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.VOLATILES);

        CurrentIndustry.applyDeficitToProduction(1, deficit, Commodities.FUEL);

        if (!isFunctional()) {
            supply.clear();
        }
    }


    @Override
    public void unapply() {
        super.unapply();
    }


    @Override
    public String getCurrentImage() {
        if (getSpecialItem() != null) {
            return Global.getSettings().getSpriteName("industry", "advanced_fuel_prod");
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

//	@Override
//	public List<InstallableIndustryItemPlugin> getInstallableItems() {
//		ArrayList<InstallableIndustryItemPlugin> list = new ArrayList<InstallableIndustryItemPlugin>();
//		list.add(new GenericInstallableItemPlugin(this));
//		return list;
//	}

    @Override
    public boolean canImproveToIncreaseProduction() {
        return true;
    }
}
