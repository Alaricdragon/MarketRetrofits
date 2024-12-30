package data.scripts.customMarketFounding;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.listeners.PlayerColonizationListener;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.customMarketFounding.surveyPlugin.MarketRetrofits_SurveyPlugin;

public class MarketRetrofits_customMarketFounder_MarketFoundingListiner implements PlayerColonizationListener {
    public static MarketRetrofits_MarketFounder marketFounder=null;
    public static void addListiner(){
        Global.getSector().getListenerManager().addListener(new MarketRetrofits_customMarketFounder_MarketFoundingListiner(),true);
    }
    @Override
    public void reportPlayerColonizedPlanet(PlanetAPI planet) {
        SectorEntityToken world = MarketRetrofits_MarketFounderMasterList.Planet;
        if(world == null || marketFounder == null) return;
        if (planet.equals(world)){
            MarketRetrofits_Logger.logging("running found a market code...",this,true);
            marketFounder.runCodeAfterFoundingMarket(world);
            resetMarketFounder();
            //run after condtitions of founding a marekt
        }else{
            MarketRetrofits_Logger.logging("NOT running found a market code...",this,true);
            resetMarketFounder();
        }
    }
    @Override
    public void reportPlayerAbandonedColony(MarketAPI colony) {

    }
    public static void resetMarketFounder(){
        MarketRetrofits_Logger.logging("reseting the market founder",new MarketRetrofits_MarketFounderMasterList(),true);
        marketFounder = null;
        //MarketRetrofits_SurveyPlugin.costArray = null;
        MarketRetrofits_SurveyPlugin.activeFounder = null;
        MarketRetrofits_SurveyPlugin.planet = null;
    }
}
