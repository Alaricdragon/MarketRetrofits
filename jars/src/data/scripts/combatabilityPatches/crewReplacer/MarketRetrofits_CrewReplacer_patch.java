package data.scripts.combatabilityPatches.crewReplacer;

import com.fs.starfarer.api.Global;
import data.scripts.MarketRetrofits_Constants;
import data.scripts.combatabilityPatches.MarketRetrofits_PatchBase;
import data.scripts.customMarketFounding.MarketRetrofits_CustomMarketFounderType;
import data.scripts.customMarketFounding.MarketRetrofits_MarketFounderMasterList;
import data.scripts.customMarketFounding.MarketRetrofits_customMarketFounder_BaseMarket;
import data.scripts.customMarketFounding.surveyPlugin.MarketRetrofits_SurveyPlugin;
import data.scripts.customMarketFounding.surveyPlugin.MarketRetrofits_SurveyPlugin_CrewReplacer;

public class MarketRetrofits_CrewReplacer_patch extends MarketRetrofits_PatchBase {
    @Override
    public void apply() {
        MarketRetrofits_SurveyPlugin.plugin = new MarketRetrofits_SurveyPlugin_CrewReplacer();
        new MarketRetrofits_CrewReplacerPatch_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket", Global.getSettings().getString("MarketREtrofits_CustomMarketFounder_BaseMarketName"));
        //MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounderType(new MarketRetrofits_CustomMarketFounderType(MarketRetrofits_Constants.BaseMarketFounderID));
    }
}
