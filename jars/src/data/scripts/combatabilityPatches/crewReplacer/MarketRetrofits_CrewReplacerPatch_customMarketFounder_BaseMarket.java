package data.scripts.combatabilityPatches.crewReplacer;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import data.scripts.CrewReplacer_Log;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.crewReplacer_Job;
import data.scripts.crewReplacer_Main;
import data.scripts.customMarketFounding.MarketRetrofits_customMarketFounder_BaseMarket;
import data.scripts.replacementscripts.CrewReplacer_SurveyPluginImpl;
import data.scripts.shadowCrew.CrewReplacer_HideShowdoCrew_2;

import java.util.LinkedHashMap;
import java.util.Map;

public class MarketRetrofits_CrewReplacerPatch_customMarketFounder_BaseMarket extends MarketRetrofits_customMarketFounder_BaseMarket {
    public MarketRetrofits_CrewReplacerPatch_customMarketFounder_BaseMarket(String ID, String name) {
        super(ID, name);
    }

    public static final String[] showdows = CrewReplacer_HideShowdoCrew_2.getShowdos("survey");
    public static final String[] showdows2 = CrewReplacer_HideShowdoCrew_2.getShowdos("survey2");

    public static final String crewJob2 = "colony_crew";
    public static final String supplyJob2 = "colony_supply";
    public static final String heavy_matchnearyJob2 = "colony_heavyMachinery";

    @Override
    public Map<String, Integer> getOutpostConsumed(SectorEntityToken planet) {
        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        CrewReplacer_HideShowdoCrew_2.removeShowdoCrewFromPlayersFleet(showdows);
        CrewReplacer_HideShowdoCrew_2.removeShowdoCrewFromPlayersFleet(showdows2);
        crewReplacer_Job a = crewReplacer_Main.getJob(crewJob2);
        crewReplacer_Job b = crewReplacer_Main.getJob(heavy_matchnearyJob2);
        crewReplacer_Job c = crewReplacer_Main.getJob(supplyJob2);
        CrewReplacer_HideShowdoCrew_2.addShowdoCrewToPlayerFleet(showdows[0],(int)a.getAvailableCrewPower(Global.getSector().getPlayerFleet().getCargo()));
        CrewReplacer_HideShowdoCrew_2.addShowdoCrewToPlayerFleet(showdows[1],(int)b.getAvailableCrewPower(Global.getSector().getPlayerFleet().getCargo()));
        CrewReplacer_HideShowdoCrew_2.addShowdoCrewToPlayerFleet(showdows2[0],(int)c.getAvailableCrewPower(Global.getSector().getPlayerFleet().getCargo()));
        result.put(showdows[0], 1000);
        result.put(showdows[1], 100);
        result.put(showdows2[0], 200);
        MarketRetrofits_Logger.logging("I AM GETTING THE NUMBERS I FUCKING NEED!!!!",this,true);
        return result;
    }

    public int costCrew = 1000, costHM = 100, costSupply = 200;
    @Override
    public void runCodeAfterFoundingMarket(SectorEntityToken planet) {
        crewReplacer_Job a = crewReplacer_Main.getJob(crewJob2);
        crewReplacer_Job b = crewReplacer_Main.getJob(heavy_matchnearyJob2);
        crewReplacer_Job c = crewReplacer_Main.getJob(supplyJob2);
        a.automaticlyGetAndApplyCrewLost(Global.getSector().getPlayerFleet().getCargo(),costCrew,costCrew);
        b.automaticlyGetAndApplyCrewLost(Global.getSector().getPlayerFleet().getCargo(),costHM,costHM);
        c.automaticlyGetAndApplyCrewLost(Global.getSector().getPlayerFleet().getCargo(),costSupply,costSupply);
        CrewReplacer_Log.loging("removing crew because you made a market. or so i think.",this,true);
        CrewReplacer_HideShowdoCrew_2.removeShowdoCrewFromPlayersFleet(CrewReplacer_SurveyPluginImpl.showdows);
        CrewReplacer_HideShowdoCrew_2.removeShowdoCrewFromPlayersFleet(CrewReplacer_SurveyPluginImpl.showdows2);
        super.runCodeAfterFoundingMarket(planet);
        MarketRetrofits_Logger.logging("I AM REMOVING THE NUMBERS I FUCKING NEED!!!!",this,true);
    }
}
