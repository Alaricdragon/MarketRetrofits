package data.scripts.customMarketFounding;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.MarketRetrofits_Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarketRetrofits_customMarketFounder_dialog implements InteractionDialogPlugin{
    protected InteractionDialogAPI dialog;
    protected TextPanelAPI text;
    protected OptionPanelAPI options;
    public int page = 0;
    public int pageLength = 10;
    public int depth = 0;
    public int sellected = 0;
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
        this.options.clearOptions();
        ArrayList<MarketRetrofits_MarketFounder> b = MarketRetrofits_MarketFounderMasterList.getFoundableMarketsInOrder(planet);
        for(int a = pageLength*page; a < b.size() && a < (pageLength*page)+page; a++){
            this.options.addOption(b.get(a).getOptionText(dialog,planet),b.get(a).ID);
        }
        this.addBackOption();
    }
    protected void exitDialog(){
        this.dialog.dismiss();
    }
    protected void back(){
        if (depth == 0) {
            this.exitDialog();
        } else {
            depth--;
            this.populateOptions();
        }
    }
    public static void openMarketScreen(SectorEntityToken planet,MarketRetrofits_MarketFounder founder){

    }
    protected void runMarketFoundingPage(MarketRetrofits_MarketFounder option,int options){
        if((options == 1 && option.skipDescriptionIfOnlyOption) || !option.showOutpostFoundingDescription()){
            openMarketScreen(planet,option);
            return;
        }
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
    @Override
    public void init(InteractionDialogAPI dialog) {
        this.dialog = dialog;
        this.options = dialog.getOptionPanel();
        this.text = dialog.getTextPanel();
        dialog.getVisualPanel().setVisualFade(0.25F, 0.25F);
        //Iterator var3 = Global.getSector().getScripts().iterator();

        /*while (var3.hasNext()) {
            EveryFrameScript script = (EveryFrameScript) var3.next();
            //if (script.getClass() == LootAddScript.class) {
            //    if (((LootAddScript)script).captiveOfficers == null) {
            //        ((LootAddScript)script).captiveOfficers = new ArrayList();
            //    }

            //    this.captiveOfficers = ((LootAddScript)script).captiveOfficers;
            //}
        }*/

        //this.text.addParagraph("You currently have " + (int) this.fleet.getCargo().getCommodityQuantity("capturedcrew") + " captive crew and " + this.captiveOfficers.size() + " captive officers.");
        this.text.addParagraph("You cafullys consider what you can produce with the knowlage you have...");
        //this.lastSelectedMenu = null;
        //this.lastSelectedItems = null;
        this.populateOptions();
        dialog.setPromptText(Misc.ucFirst("Options"));
    }

    @Override
    public void optionSelected(String optionText, Object optionData) {
        if (optionText != null) {
            this.text.addParagraph(optionText, Global.getSettings().getColor("buttonText"));
        }
        String optionData2 = (String)optionData;
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
            ArrayList<MarketRetrofits_MarketFounder> b = MarketRetrofits_MarketFounderMasterList.getFoundableMarketsInOrder(planet);
            for(int a = pageLength*page; a < b.size() && a < (pageLength*page)+page; a++){
                if(optionData.equals(b.get(a).ID)){
                        sellected = a;
                        this.depth++;
                        this.runMarketFoundingPage(b.get(a),b.size());
                        return;
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
