package data.scripts.customMarketFounding;

import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import java.util.Map;

public class MarketRetrofits_customMarketFounder_dialog implements InteractionDialogPlugin{
    public String plannet = "";//this is a string as a temp untill i can change it to whatever a world is.
    public boolean noJumpTemp = MarketRetrofits_MarketFounderMasterList.noJumpTemp;
    public boolean hostilesTemp = MarketRetrofits_MarketFounderMasterList.hostilesTemp;
    public MarketRetrofits_customMarketFounder_dialog(String planet){
        this.plannet=planet;
    }
    //
    @Override
    public void init(InteractionDialogAPI dialog) {

    }

    @Override
    public void optionSelected(String optionText, Object optionData) {

    }

    @Override
    public void optionMousedOver(String optionText, Object optionData) {

    }

    @Override
    public void advance(float amount) {

    }

    @Override
    public void backFromEngagement(EngagementResultAPI battleResult) {

    }

    @Override
    public Object getContext() {
        return null;
    }

    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return null;
    }
}
