package data.scripts.customMarketFounding;

import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.util.Misc;

import java.util.ArrayList;
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
    public SectorEntityToken planate=null;
    public MarketRetrofits_MarketFounder(String ID,String name){
        this.ID=ID;
        this.name = name;
    }
    public MarketRetrofits_MarketFounder(String ID,String name,int order){
        this.ID=ID;
        this.name = name;
        this.order = order;
    }
    public boolean canEstablishOutpost(SectorEntityToken planet){
        return true;
    }
    public void getOutpostFoundOption(InteractionDialogAPI dialog, SectorEntityToken planet){//List<Misc.Token> params, Map<String, MemoryAPI> memoryMap){
        dialog.getOptionPanel().addOption(getOptionText(dialog, planet),this.ID);
    }
    public String getOptionText(InteractionDialogAPI dialog, SectorEntityToken planet){//List<Misc.Token> params, Map<String, MemoryAPI> memoryMap){
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
    public void getOutpostFoundingDescription(InteractionDialogAPI dialog,SectorEntityToken planet){
        dialog.getTextPanel().addPara("description test.");
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


    protected InteractionDialogAPI dialog;
    protected TextPanelAPI text;
    protected OptionPanelAPI options;
    public final static String backOption="BACK",foundMarketOption="Found Market";
    public void foundMarket(){

    }
    public void back(){

    }
    @Override
    public void init(InteractionDialogAPI dialog) {
        this.dialog = dialog;
        this.options = dialog.getOptionPanel();
        this.text = dialog.getTextPanel();
        dialog.getVisualPanel().setVisualFade(0.25F, 0.25F);
        this.getOutpostFoundingDescription(dialog,planate);


        this.options.clearOptions();
        this.options.addOption("BACK",backOption);
        this.options.addOption("Found Market",foundMarketOption);
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        String optionData2 = (String)optionData;
        try {
            if(optionData2.equals(backOption)){
                MarketRetrofits_MarketFounderMasterList.dialog.Dialog.init(dialog);
                return;
            }
            if(optionData2.equals(foundMarketOption)){
                MarketRetrofits_customMarketFounder_dialog.openMarketScreen(planate,this);
                return;
            }
        } catch (Exception var7) {
            this.text.addPara(var7.toString());

        }
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
