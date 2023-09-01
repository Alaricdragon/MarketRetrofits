package data.scripts.customMarketFounding;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.MarketRetrofits_Constants;
import data.scripts.MarketRetrofits_Logger;

import java.util.ArrayList;
import java.util.Map;

public class MarketRetrofits_customMarketFounder_dialog implements InteractionDialogPlugin{
    public static MarketRetrofits_customMarketFounder_dialog myself;
    protected InteractionDialogAPI dialog;
    protected TextPanelAPI text;
    protected OptionPanelAPI options;
    public int page = 0;
    public int pageLength = 5;
    //public int depth = 0;
    //public int sellected = 0;
    public SectorEntityToken planet;//this is a string as a temp untill i can change it to whatever a world is.
    public boolean noJumpTemp = MarketRetrofits_MarketFounderMasterList.noJumpTemp;
    public boolean hostilesTemp = MarketRetrofits_MarketFounderMasterList.hostilesTemp;
    protected static final String backOption="BACK",exitOption="EXIT",nextOption="NEXT";
    /*todo list:
    *  what do i want to do here? simple:
    * 1) create a list of available markets (if any are available).
    * 2) allow you to click on a given market as a option, to load its description, or if description is disabled, simply load the vanilla 'found a market'
    * 2.a) if a given market option is the only option, and this market has its */
    public MarketRetrofits_customMarketFounder_dialog(SectorEntityToken planet){
        this.planet =planet;
    }
    protected void populateOptions() {
        ArrayList<MarketRetrofits_MarketFounder> b = MarketRetrofits_MarketFounderMasterList.getFoundableMarketsForDialog(planet);
        this.options.clearOptions();
        for(int a = pageLength*page; a < b.size() && a < (pageLength*(page+1)); a++){
            //this.options.addOption(b.get(a).getOptionText(dialog,planet),b.get(a).ID);
            b.get(a).addOption(planet,options,dialog);
            //MarketRetrofits_Logger.logging("adding market founding option page: "+b.get(a).getOptionText(dialog,planet),this,MarketRetrofits_Constants.CustomMarketFounderLogs);
        }
        if((page+1) * pageLength < b.size()) {
            this.addNextOption();
        }
        this.addBackOption();
    }
    protected void exitDialog(){
        this.dialog.dismiss();
    }
    protected void back(){
        if (page == 0) {
            this.exitDialog();
        } else {
            page--;
            this.populateOptions();
        }
    }
    /*public static void openMarketScreen(SectorEntityToken planet,MarketRetrofits_MarketFounder founder){
        //OpenCoreTab CARGO OPEN
    }*/
    protected void runMarketFoundingPage(MarketRetrofits_MarketFounder option){
        MarketRetrofits_Logger.logging("trying to found a market",this,MarketRetrofits_Constants.CustomMarketFounderLogs);
        if(!option.showOutpostFoundingDescription(planet)){
            MarketRetrofits_Logger.logging("running 'open market screen'",this,MarketRetrofits_Constants.CustomMarketFounderLogs);
            MarketRetrofits_MarketFounderMasterList.foundMarket(option);//openMarketScreen(planet,option);
            return;
        }
        MarketRetrofits_Logger.logging("running 'description of market type'",this,MarketRetrofits_Constants.CustomMarketFounderLogs);
        option.planate = planet;
        MarketRetrofits_MarketFounderMasterList.dialog.Dialog = option;
        MarketRetrofits_MarketFounderMasterList.dialog.init(dialog);
    }
    /*protected void showPaginatedMenu(){
        this.options.clearOptions();
        ArrayList<MarketRetrofits_MarketFounder> b = MarketRetrofits_MarketFounderMasterList.getFoundableMarketsInOrder();
        for(int a = pageLength*page; a < b.size() && a < (pageLength*page)+page; a++){
            b.get(a).getOptionText(dialog,planet);
        }
        this.addBackOption();
    }*/

    protected void addNextOption(){
        this.options.addOption("NEXT",nextOption);
    }
    protected void addBackOption(){
        if (page == 0){
            addExitOption();
            return;
        }
        this.options.addOption("BACK",backOption);
    }
    protected void addExitOption(){
        this.options.addOption("Exit",exitOption);
    }
    public void setData(InteractionDialogAPI dialog){
        myself = this;
        this.dialog = dialog;
        this.options = dialog.getOptionPanel();
        this.text = dialog.getTextPanel();
    }
    @Override
    public void init(InteractionDialogAPI dialog) {
        this.setData(dialog);
        dialog.getVisualPanel().setVisualFade(0.25F, 0.25F);
        this.text.addParagraph("You cafullys consider what you can produce with the knowlage you have...");
        this.populateOptions();
        dialog.setPromptText(Misc.ucFirst("Options"));
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        if (optionText != null) {
            this.text.addParagraph(optionText, Global.getSettings().getColor("buttonText"));
        }
        String optionData2 = (String)optionData;
        MarketRetrofits_Logger.logging("looking for option: "+optionData2,this, MarketRetrofits_Constants.CustomMarketFounderLogs);
        try {
            if (optionData2.equals(nextOption)){//optionData2 == AIRetrofits_RobotForgeDiologPlugin.Menu.NEXT) {
                ++this.page;
                this.populateOptions();//showPaginatedMenu();
                return;
            }
            if (optionData2.equals(exitOption)){
                this.exitDialog();
                return;
            }
            if (optionData2.equals(backOption)){
                this.back();
                return;
            }
            ArrayList<MarketRetrofits_MarketFounder> b = MarketRetrofits_MarketFounderMasterList.getFoundableMarketsForDialog(planet);
            for(int a = pageLength*page; a < b.size() && a < (pageLength*(page+1)); a++){
                if(b.get(a).wasOptionSelected(optionData)){//optionData2.equals(b.get(a).ID)){
                    //sellected = a;
                    //this.depth++;
                    this.runMarketFoundingPage(b.get(a));
                    return;
                }else{
                    MarketRetrofits_Logger.logging("option is not: "+b.get(a).ID,this,MarketRetrofits_Constants.CustomMarketFounderLogs);
                }
            }
        } catch (Exception var7) {
            this.text.addPara(var7.toString());
            this.addBackOption();
        }
        //text.addParagraph(getLast());
        this.populateOptions();
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
