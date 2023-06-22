package data.scripts.combatabilityPatches;

import com.fs.starfarer.api.Global;
import data.scripts.combatabilityPatches.crewReplacer.MarketRetrofits_CrewReplacer_patch;

public class MarketRetrofits_InitCombatabilityPatches {
    public static String[] modNames = {
            "aaacrew_replacer",
    };
    public static MarketRetrofits_PatchBase[] patches = {
           //new MarketRetrofits_normadicSurvival_init(),
            new MarketRetrofits_CrewReplacer_patch(),
    };
    public static void onApplicationLoad() {
        for(int a = 0; a < modNames.length; a++) {
            if (Global.getSettings().getModManager().isModEnabled(modNames[a])) {
                //CrewReplacer_Log.loging("Crew Replacer is attempting to add a compatibility patch for the mod '" + modNames[a] + "' ...", MarketRetrofits_InitCombatabilityPatches.class, true);
                patches[a].apply();
            }
        }
    }
    public static void onGameLoad(boolean newGame) {
        if(Global.getSettings().getModManager().isModEnabled(modNames[0])) {
        }
    }
}
