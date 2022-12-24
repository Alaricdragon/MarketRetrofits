package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.CommodityOnMarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;

import java.awt.*;

public class MarketRetrofit_HeavyIndustryInstance extends MarketRetrofits_DefaltInstanceIndustry {
    public MarketRetrofit_HeavyIndustryInstance(String name, float orderT) {
        super(name, orderT);
    }

    public static float ORBITAL_WORKS_QUALITY_BONUS = 0.2f;

    public static String POLLUTION_ID = Conditions.POLLUTION;
    public static float DAYS_BEFORE_POLLUTION = 0f;
    public static float DAYS_BEFORE_POLLUTION_PERMANENT = 90f;

    @Override
    public void apply() {
        super.apply(true);

        int size = market.getSize();

        boolean works = Industries.ORBITALWORKS.equals(CurrentIndustry.getId());
        int shipBonus = 0;
        float qualityBonus = 0;
        if (works) {
            //shipBonus = 2;
            qualityBonus = ORBITAL_WORKS_QUALITY_BONUS;
        }

        CurrentIndustry.demand(Commodities.METALS, size);
        CurrentIndustry.demand(Commodities.RARE_METALS, size - 2);

        CurrentIndustry.supply(Commodities.HEAVY_MACHINERY, size - 2);
        CurrentIndustry.supply(Commodities.SUPPLIES, size - 2);
        CurrentIndustry.supply(Commodities.HAND_WEAPONS, size - 2);
        CurrentIndustry.supply(Commodities.SHIPS, size - 2);
        if (shipBonus > 0) {
            CurrentIndustry.supply(1, Commodities.SHIPS, shipBonus, "Orbital works");
        }

        Pair<String, Integer> deficit = CurrentIndustry.getMaxDeficit(Commodities.METALS, Commodities.RARE_METALS);
        int maxDeficit = size - 3; // to allow *some* production so economy doesn't get into an unrecoverable state
        if (deficit.two > maxDeficit) deficit.two = maxDeficit;

        CurrentIndustry.applyDeficitToProduction(2, deficit,
                Commodities.HEAVY_MACHINERY,
                Commodities.SUPPLIES,
                Commodities.HAND_WEAPONS,
                Commodities.SHIPS);

//		if (market.getId().equals("chicomoztoc")) {
//			System.out.println("efwefwe");
//		}

        if (qualityBonus > 0) {
            market.getStats().getDynamic().getMod(Stats.PRODUCTION_QUALITY_MOD).modifyFlat(CurrentIndustry.getModId(1), qualityBonus, "Orbital works");
        }

        float stability = market.getPrevStability();
        if (stability < 5) {
            float stabilityMod = (stability - 5f) / 5f;
            stabilityMod *= 0.5f;
            //market.getStats().getDynamic().getMod(Stats.PRODUCTION_QUALITY_MOD).modifyFlat(getModId(0), stabilityMod, "Low stability at production source");
            market.getStats().getDynamic().getMod(Stats.PRODUCTION_QUALITY_MOD).modifyFlat(CurrentIndustry.getModId(0), stabilityMod, CurrentIndustry.getNameForModifier() + " - low stability");
        }

        if (!CurrentIndustry.isFunctional()) {
            supply.clear();
            CurrentIndustry.unapply();
        }
    }

    @Override
    public void unapply() {
        super.unapply();

        market.getStats().getDynamic().getMod(Stats.PRODUCTION_QUALITY_MOD).unmodifyFlat(CurrentIndustry.getModId(0));
        market.getStats().getDynamic().getMod(Stats.PRODUCTION_QUALITY_MOD).unmodifyFlat(CurrentIndustry.getModId(1));
    }


    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, IndustryTooltipMode mode) {
        //if (mode == IndustryTooltipMode.NORMAL && isFunctional()) {
        if (mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional()) {
            boolean works = Industries.ORBITALWORKS.equals(CurrentIndustry.getId());
            if (works) {
                float total = ORBITAL_WORKS_QUALITY_BONUS;
                String totalStr = "+" + (int)Math.round(total * 100f) + "%";
                Color h = Misc.getHighlightColor();
                if (total < 0) {
                    h = Misc.getNegativeHighlightColor();
                    totalStr = "" + (int)Math.round(total * 100f) + "%";
                }
                float opad = 10f;
                if (total >= 0) {
                    tooltip.addPara("Ship quality: %s", opad, h, totalStr);
                    tooltip.addPara("*Quality bonus only applies for the largest ship producer in the faction.",
                            Misc.getGrayColor(), opad);
                }
            }
        }
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
    public boolean canImproveToIncreaseProduction() {
        return true;
    }

    @Override
    public boolean wantsToUseSpecialItem(SpecialItemData data) {
        if (special != null && Items.CORRUPTED_NANOFORGE.equals(special.getId()) &&
                data != null && Items.PRISTINE_NANOFORGE.equals(data.getId())) {
            return true;
        }
        return super.wantsToUseSpecialItem(data);
    }

    public boolean permaPollution = false;
    public boolean addedPollution = false;
    public float daysWithNanoforge = 0f;

    private static String permaPollutionName = "permaPollution";
    private static String addedPollutionName = "addedPollution";
    private static String daysWithNanoforgeName = "daysWithNanoforge";

    @Override
    public void advance(float amount) {
        super.advance(amount);
        if (special != null) {
            float days = Global.getSector().getClock().convertToDays(amount);
            daysWithNanoforge = (float) CurrentIndustry.exstraData.getFloat(daysWithNanoforgeName);
            daysWithNanoforge += days;
            CurrentIndustry.exstraData.addData(daysWithNanoforgeName,daysWithNanoforge);
            updatePollutionStatus();
        }
    }

    public void updatePollutionStatus() {
        if (!market.hasCondition(Conditions.HABITABLE)) return;
        permaPollution = (boolean) CurrentIndustry.exstraData.getBoolean(permaPollutionName);
        addedPollution = (boolean) CurrentIndustry.exstraData.getBoolean(addedPollutionName);
        daysWithNanoforge = (float) CurrentIndustry.exstraData.getFloat(daysWithNanoforgeName);
        if (special != null) {
            if (!addedPollution && daysWithNanoforge >= DAYS_BEFORE_POLLUTION) {
                if (market.hasCondition(POLLUTION_ID)) {
                    permaPollution = true;
                } else {
                    market.addCondition(POLLUTION_ID);
                    addedPollution = true;
                }
            }
            if (addedPollution && !permaPollution) {
                if (daysWithNanoforge > DAYS_BEFORE_POLLUTION_PERMANENT) {
                    permaPollution = true;
                }
            }
        } else if (addedPollution && !permaPollution) {
            market.removeCondition(POLLUTION_ID);
            addedPollution = false;
        }
        CurrentIndustry.exstraData.addData(permaPollutionName,permaPollution);
        CurrentIndustry.exstraData.addData(addedPollutionName,addedPollution);
    }

    @Override
    public void setSpecialItem(SpecialItemData special) {
        super.setSpecialItem(special);

        updatePollutionStatus();
    }



//	@Override
//	public List<InstallableIndustryItemPlugin> getInstallableItems() {
//		ArrayList<InstallableIndustryItemPlugin> list = new ArrayList<InstallableIndustryItemPlugin>();
//		list.add(new GenericInstallableItemPlugin(this));
//		return list;
//	}
}
