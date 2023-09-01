package data.scripts.customMarketFounding;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.util.Misc;
import data.scripts.MarketRetrofits_Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MarketRetrofits_MarketFounder implements InteractionDialogPlugin {
    public String name = "";
    public String ID = "";
    public float order = 0;
    public boolean skipOptionSelectionIfOnlyOption = false;
    public boolean canFoundWithHostileActivity = false;
    public boolean canFoundWithoutJumpPonits = false;
    public boolean MarketFounderHasDescription = false;
    public boolean showOptionIfUnavalable = true;
    public String MarketFounderDescription = "description to be added latter. if you are a modder, try changeing this market founders 'MarketFounderDescription' or 'MarketFounderHasDescription' variables.";
    public SectorEntityToken planate=null;
    public MarketRetrofits_MarketFounder(String ID,String name){
        this.ID=ID;
        this.name = name;
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(this);
    }
    public MarketRetrofits_MarketFounder(String ID,String name,float order){
        this.ID=ID;
        this.name = name;
        this.order = order;
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(this);
    }
    public void getOutpostFoundOption(InteractionDialogAPI dialog, SectorEntityToken planet){//List<Misc.Token> params, Map<String, MemoryAPI> memoryMap){
        dialog.getOptionPanel().addOption(getOptionText(dialog, planet),this.ID);
    }
    public String getOptionText(InteractionDialogAPI dialog, SectorEntityToken planet){//List<Misc.Token> params, Map<String, MemoryAPI> memoryMap){
        return name;
    }
    public boolean showOutpostFoundingDescription(SectorEntityToken planet){
        return this.MarketFounderHasDescription;
    }
    public boolean skipOptionSelectionIfOnlyOption(SectorEntityToken planet){
        return this.skipOptionSelectionIfOnlyOption;
    }
    public void getOutpostFoundingDescription(InteractionDialogAPI dialog,SectorEntityToken planet){
        dialog.getTextPanel().addPara(this.MarketFounderDescription);
    }
    public boolean showOptionIfUnavalable(SectorEntityToken planet){
        return showOptionIfUnavalable;
    }
    public boolean showOption(SectorEntityToken planet,boolean hostileActivity,boolean cutOffFromHyperspace){
        return this.canEstablishAMarketHere(planet,hostileActivity,cutOffFromHyperspace) || showOptionIfUnavalable(planet);
    }
    public void addOption(SectorEntityToken planet, OptionPanelAPI options, InteractionDialogAPI dialog){
        options.addOption(getOptionText(dialog,planet),ID);
        if(!this.canEstablishAMarketHere(planet,MarketRetrofits_MarketFounderMasterList.hostilesTemp,MarketRetrofits_MarketFounderMasterList.noJumpTemp)) {
            options.setEnabled(ID, false);
        }
    }
    public boolean wasOptionSelected(Object optionData){
        try {
            return (String) optionData == this.ID;
        }catch (Exception e){
            return false;
        }
    }
    public boolean canFoundWithHostileActivity(SectorEntityToken planet){
        return this.canFoundWithHostileActivity;
    }
    public boolean canFoundWithoutJumpPonits(SectorEntityToken planet){
        return this.canFoundWithoutJumpPonits;
    }
    public boolean canEstablishAMarketHere(SectorEntityToken planet,boolean hostileActivity,boolean cutOffFromHyperspace){
        return (!hostileActivity || canFoundWithHostileActivity(planet)) && (!cutOffFromHyperspace || canFoundWithoutJumpPonits(planet));
    }
    public Map<String, Integer> getOutpostConsumed(SectorEntityToken planet) {
        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        result.put(Commodities.CREW, 1000);
        result.put(Commodities.HEAVY_MACHINERY, 200);
        result.put(Commodities.SUPPLIES, 200);
        return result;
    }
    public void runCodeAfterFoundingMarket(SectorEntityToken planet){

    }


    protected InteractionDialogAPI dialog;
    protected TextPanelAPI text;
    protected OptionPanelAPI options;
    public final static String backOption="BACK",foundMarketOption="Found Market";
    public String backOptionName = Global.getSettings().getString("MarketREtrofits_CustomMarketFounder_BaseMarketBackOption"), foundMarketOptionName = Global.getSettings().getString("MarketREtrofits_CustomMarketFounder_BaseMarketEstablishOption");
    @Override
    public void init(InteractionDialogAPI dialog) {
        MarketRetrofits_Logger.logging("running dialog of market founder ID of: "+this.ID,this,true);
        this.dialog = dialog;
        this.options = dialog.getOptionPanel();
        this.text = dialog.getTextPanel();
        dialog.getVisualPanel().setVisualFade(0.25F, 0.25F);
        this.getOutpostFoundingDescription(dialog,planate);


        this.options.clearOptions();
        this.options.addOption(this.foundMarketOptionName,foundMarketOption);
        this.options.addOption(this.backOptionName,backOption);
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        String optionData2 = (String)optionData;
        try {
            if(optionData2.equals(backOption)){
                MarketRetrofits_MarketFounderMasterList.dialog.Dialog = MarketRetrofits_customMarketFounder_dialog.myself;
                MarketRetrofits_MarketFounderMasterList.dialog.Dialog.init(dialog);
                return;
            }
            if(optionData2.equals(foundMarketOption)){
                dialog.getOptionPanel().clearOptions();
                MarketRetrofits_MarketFounderMasterList.foundMarket(this);//MarketRetrofits_customMarketFounder_dialog.openMarketScreen(planate,this);
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
