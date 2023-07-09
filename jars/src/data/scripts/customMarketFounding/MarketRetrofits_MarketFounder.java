package data.scripts.customMarketFounding;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.util.Misc;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MarketRetrofits_MarketFounder implements InteractionDialogPlugin {
    public String name = "";
    public String ID = "";
    public int order = 0;
    public boolean skipDescriptionIfOnlyOption = false;
    public boolean canFoundWithHostileActivity = false;
    public boolean canFoundWithoutJumpPonits = false;
    public MarketRetrofits_MarketFounder(String ID,String name){
        this.ID=ID;
        this.name = name;
    }
    public MarketRetrofits_MarketFounder(String ID,String name,int order){
        this.ID=ID;
        this.name = name;
        this.order = order;
    }
    public boolean canEstablishOutpost(){
        return true;
    }
    public void getOutpostFoundOption(InteractionDialogAPI dialog, String planet){//List<Misc.Token> params, Map<String, MemoryAPI> memoryMap){
        dialog.getOptionPanel().addOption(getOptionText(dialog, planet),this.ID);
    }
    public String getOptionText(InteractionDialogAPI dialog, String planet){//List<Misc.Token> params, Map<String, MemoryAPI> memoryMap){
        return name;
    }
    public boolean showOutpostFoundingDescription(){
        return false;
    }
    public boolean canFoundWithHostileActivity(){
        return this.canFoundWithHostileActivity;
    }
    public boolean canFoundWithoutJumpPonits(){
        return this.canFoundWithoutJumpPonits;
    }
    public boolean skipDescriptionIfOnlyOption(){
        return this.skipDescriptionIfOnlyOption;
    }
    public void getOutpostFoundingDescription(InteractionDialogAPI dialog,String planet){

    }
    public Map<String, Integer> getOutpostConsumed() {
        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        result.put(Commodities.CREW, 1000);
        result.put(Commodities.HEAVY_MACHINERY, 200);
        result.put(Commodities.SUPPLIES, 200);
        return result;
    }
    public void runCodeAfterFoundingMarket(){

    }



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
