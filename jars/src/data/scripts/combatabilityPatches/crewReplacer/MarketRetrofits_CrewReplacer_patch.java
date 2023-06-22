package data.scripts.combatabilityPatches.crewReplacer;

import data.scripts.combatabilityPatches.MarketRetrofits_PatchBase;
import data.scripts.customMarketFounding.surveyPlugin.MarketRetrofits_SurveyPlugin;
import data.scripts.customMarketFounding.surveyPlugin.MarketRetrofits_SurveyPlugin_CrewReplacer;

public class MarketRetrofits_CrewReplacer_patch extends MarketRetrofits_PatchBase {
    @Override
    public void apply() {
        MarketRetrofits_SurveyPlugin.plugin = new MarketRetrofits_SurveyPlugin_CrewReplacer();
    }
}
