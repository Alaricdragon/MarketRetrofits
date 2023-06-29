package data.scripts.starfarer.api.impl.campaign.rulemd;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.util.Misc;
import data.scripts.customMarketFounding.MarketRetrofits_MarketFounder;
import data.scripts.customMarketFounding.MarketRetrofits_MarketFounderMasterList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarketRetrofits_customMarketFounding_MarketTypeList extends BaseCommandPlugin {
    @Override
    public boolean execute(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap) {
        MarketRetrofits_MarketFounderMasterList.activateMarketFoundingListDialog(dialog);
        return false;
    }
}


