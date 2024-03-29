package com.fs.starfarer.api.impl.campaign.rulecmd;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.HostileFleetNearbyAndAware;
import com.fs.starfarer.api.util.Misc;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.customMarketFounding.MarketRetrofits_MarketFounderMasterList;

import java.util.List;
import java.util.Map;

public class MarketRetrofits_customMarketFounding_CanIFoundAMarketHere extends BaseCommandPlugin {
    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        MarketRetrofits_Logger.logging("runing a thing.",this,true);
        boolean cutOff = params.get(0).getBoolean(memoryMap);
        boolean hostileActivity = params.get(1).getBoolean(memoryMap);
        hostileActivity = new HostileFleetNearbyAndAware().execute(ruleId, dialog, params, memoryMap);
        MarketRetrofits_Logger.logging("jumpPoints, hostileActivity: "+cutOff+", "+hostileActivity,this,true);
        MarketRetrofits_MarketFounderMasterList.hostilesTemp = hostileActivity;
        MarketRetrofits_MarketFounderMasterList.noJumpTemp = cutOff;
        return MarketRetrofits_MarketFounderMasterList.getFoundableMarkets(dialog.getInteractionTarget()).size() != 0;
    }
}
