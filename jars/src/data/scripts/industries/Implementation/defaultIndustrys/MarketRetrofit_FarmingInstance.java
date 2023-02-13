package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MarketConditionAPI;
import com.fs.starfarer.api.impl.campaign.econ.ResourceDepositsCondition;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.Planets;
import com.fs.starfarer.api.impl.campaign.population.PopulationComposition;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

import java.util.HashSet;
import java.util.Set;

public class MarketRetrofit_FarmingInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_FarmingInstance(String name, float orderT) {
        super(name, orderT);
    }

    public static Set<String> AQUA_PLANETS = new HashSet<String>();

    static {
        AQUA_PLANETS.add(Planets.PLANET_WATER);
    }
    @Override
    public void apply() {
        super.apply(true);

        int size = market.getSize();
        boolean aquaculture = Industries.AQUACULTURE.equals(CurrentIndustry.getId());

        if (aquaculture) {
            CurrentIndustry.demand(0, Commodities.HEAVY_MACHINERY, size, BASE_VALUE_TEXT);
        } else {
            CurrentIndustry.demand(0, Commodities.HEAVY_MACHINERY, size - 3, BASE_VALUE_TEXT);
        }

        //supply(3, Commodities.LOBSTER, 5, "Hack");

        // ResourceDepositsCondition sets base value
        // makes more sense for Mining where mining doesn't have to check for existence of resource conditions

//		int deficit = getMaxDeficit(Commodities.HEAVY_MACHINERY);
//		supply(1, Commodities.FOOD, -deficit, getDeficitText(Commodities.HEAVY_MACHINERY));
//		supply(1, Commodities.ORGANICS, -deficit, getDeficitText(Commodities.HEAVY_MACHINERY));

        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.HEAVY_MACHINERY);
        //applyDeficitToProduction(0, deficit, Commodities.FOOD, Commodities.ORGANICS);
        CurrentIndustry.applyDeficitToProduction(0, deficit, Commodities.FOOD);


        if (!CurrentIndustry.isFunctional()) {
            supply.clear();
        }
    }


    @Override
    public void unapply() {
        super.unapply();
    }


    @Override
    public boolean isAvailableToBuild() {
        if (!super.isAvailableToBuild()) return false;
        boolean aquaculture = Industries.AQUACULTURE.equals(CurrentIndustry.getId());
        boolean canAquaculture = market.getPlanetEntity() != null &&
                AQUA_PLANETS.contains(market.getPlanetEntity().getTypeId());
        if (aquaculture != canAquaculture) return false;

        for (MarketConditionAPI mc : market.getConditions()) {
            String commodity = ResourceDepositsCondition.COMMODITY.get(mc.getId());
            if (commodity != null) {
                String industry = ResourceDepositsCondition.INDUSTRY.get(commodity);
                if (Industries.FARMING.equals(industry)) return true;
            }
        }
        return false;
    }


    @Override
    public boolean showWhenUnavailable() {
        boolean aquaculture = Industries.AQUACULTURE.equals(CurrentIndustry.getId());
        boolean canAquaculture = market.getPlanetEntity() != null &&
                AQUA_PLANETS.contains(market.getPlanetEntity().getTypeId());
        if (aquaculture != canAquaculture) return false;

        return super.showWhenUnavailable();
    }


    @Override
    public String getUnavailableReason() {
        if (!super.isAvailableToBuild()) return super.getUnavailableReason();
        return "Requires farmland";
    }


    @Override
    public void createTooltip(Industry.IndustryTooltipMode mode, TooltipMakerAPI tooltip, boolean expanded) {
        super.createTooltip(mode, tooltip, expanded);
//
//		int size = market.getSize();
//		boolean aquaculture = Industries.AQUACULTURE.equals(getId());
//
//		int machinery = size - 3;
//		if (aquaculture) {
//			machinery = size;
//		}
//		if (machinery < 0) machinery = 0;
//
//		float pad = 3f;
//		float opad = 10f;
//
//		FactionAPI faction = market.getFaction();
//		Color color = faction.getBaseUIColor();
//		Color dark = faction.getDarkUIColor();
//		Color grid = faction.getGridUIColor();
//		Color bright = faction.getBrightUIColor();
//
//		tooltip.addSectionHeading("Produces", color, dark, Alignment.MID, opad);
//		tooltip.beginIconGroup();
//		tooltip.setIconSpacingMedium();
//		for (MutableCommodityQuantity curr : supply.values()) {
//			int qty = curr.getQuantity().getModifiedInt();
//			if (qty <= 0) continue;
//			tooltip.addIcons(market.getCommodityData(curr.getCommodityId()), qty, IconRenderMode.NORMAL);
//		}
//		tooltip.addIconGroup(opad);
//
//		tooltip.addSectionHeading("Requires", color, dark, Alignment.MID, opad);
//		tooltip.beginIconGroup();
//		tooltip.setIconSpacingMedium();
//		for (MutableCommodityQuantity curr : demand.values()) {
//			int qty = curr.getQuantity().getModifiedInt();
//			if (qty <= 0) continue;
//			tooltip.addIcons(market.getCommodityData(curr.getCommodityId()), qty, IconRenderMode.NORMAL);
//		}
//		tooltip.addIconGroup(opad);

    }

    @Override
    public void modifyIncoming(MarketAPI market, PopulationComposition incoming) {
        incoming.add(Factions.LUDDIC_CHURCH, 10f);
    }


    @Override
    public String getCurrentImage() {
        boolean aquaculture = Industries.AQUACULTURE.equals(CurrentIndustry.getId());
        if (aquaculture) {
            return super.getCurrentImage();
        }
        float size = market.getSize();
        //PlanetAPI planet = market.getPlanetEntity();
        if (size <= SIZE_FOR_SMALL_IMAGE) {
            return Global.getSettings().getSpriteName("industry", "farming_low");
        }
        if (size >= SIZE_FOR_LARGE_IMAGE) {
            return Global.getSettings().getSpriteName("industry", "farming_high");
        }
        return Global.getSettings().getSpriteName("industry", "farming_med");
        //return super.getCurrentImage();
    }

    @Override
    public boolean canImproveToIncreaseProduction() {
        return true;
    }


    @Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(String commodityId, MarketCMD.RaidDangerLevel level) {
        boolean aquaculture = Industries.AQUACULTURE.equals(CurrentIndustry.getId());
        if (aquaculture) return level;
        return level.prev();
    }

    @Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(String itemId, String data, MarketCMD.RaidDangerLevel level) {
        boolean aquaculture = Industries.AQUACULTURE.equals(CurrentIndustry.getId());
        if (aquaculture) return level;
        return level.prev();
    }


}
