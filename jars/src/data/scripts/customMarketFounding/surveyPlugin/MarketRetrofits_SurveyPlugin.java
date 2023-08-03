package data.scripts.customMarketFounding.surveyPlugin;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.plugins.SurveyPlugin;
import data.scripts.customMarketFounding.MarketRetrofits_MarketFounder;

import java.util.LinkedHashMap;
import java.util.Map;

public class MarketRetrofits_SurveyPlugin implements SurveyPlugin {
    public static SurveyPlugin plugin = new MarketRetrofits_SurveyPlugin_Base();
    public static Map<String, Integer> costArray = null;
    @Override
    public void init(CampaignFleetAPI fleet, PlanetAPI planet) {
        plugin.init(fleet,planet);
    }

    @Override
    public Map<String, Integer> getRequired() {
        return plugin.getRequired();
    }

    @Override
    public Map<String, Integer> getConsumed() {
        return plugin.getConsumed();
    }

    @Override
    public String getImageCategory() {
        return plugin.getImageCategory();
    }

    @Override
    public String getImageKey() {
        return plugin.getImageKey();
    }

    @Override
    public MutableStat getCostMult() {
        return plugin.getCostMult();
    }

    @Override
    public long getXP() {
        return plugin.getXP();
    }

    @Override
    public long getBaseXPForCondition(String conditionId) {
        return plugin.getBaseXPForCondition(conditionId);
    }

    @Override
    public MutableStat getXPMult() {
        return plugin.getXPMult();
    }

    @Override
    public String getSurveyDataType(PlanetAPI planet) {
        return plugin.getSurveyDataType(planet);
    }

    @Override
    public Map<String, Integer> getOutpostConsumed() {
        //CrewReplacer_Log.loging("getOutpostConsumed",this,true);
        Map<String, Integer> result = costArray;//new LinkedHashMap<String, Integer>();
        /*
        result.put(Commodities.CREW, 9999);
        result.put(Commodities.HEAVY_MACHINERY, 1);
        result.put(Commodities.SUPPLIES, 4);
        result.put(Commodities.METALS, 200);*/

        return result;
    }
}
