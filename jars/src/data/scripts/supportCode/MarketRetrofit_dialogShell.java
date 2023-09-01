package data.scripts.supportCode;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;

import java.util.Map;

public class MarketRetrofit_dialogShell implements InteractionDialogPlugin {
    public InteractionDialogPlugin Dialog;
    @Override
    public void init(InteractionDialogAPI dialog) {
        Dialog.init(dialog);
    }
    @Override
    public void optionSelected(String optionText, Object optionData) {
        Dialog.optionSelected(optionText,optionData);
    }
    @Override
    public void optionMousedOver(String optionText, Object optionData) {
        Dialog.optionMousedOver(optionText,optionData);
    }
    @Override
    public void advance(float amount) {
        Dialog.advance(amount);
    }
    @Override
    public void backFromEngagement(EngagementResultAPI battleResult) {
        Dialog.backFromEngagement(battleResult);
    }
    @Override
    public Object getContext() {
        return Dialog.getContext();
    }
    @Override
    public Map<String, MemoryAPI> getMemoryMap() {
        return Dialog.getMemoryMap();
    }
}
