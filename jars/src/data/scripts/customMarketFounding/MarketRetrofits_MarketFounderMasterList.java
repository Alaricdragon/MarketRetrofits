package data.scripts.customMarketFounding;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.impl.campaign.rulecmd.OpenCoreTab;
import com.fs.starfarer.api.util.Misc;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.customMarketFounding.surveyPlugin.MarketRetrofits_SurveyPlugin;
import data.scripts.supportCode.MarketRetrofit_dialogShell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fs.starfarer.api.impl.campaign.ids.Tags.SYSTEM_CUT_OFF_FROM_HYPER;

public class MarketRetrofits_MarketFounderMasterList {
    private static boolean log = true;
    public static ArrayList<MarketRetrofits_MarketFounder> list = new ArrayList<>();
    public static boolean noJumpTemp = false;
    public static boolean hostilesTemp = false;
    public static BaseCommandPlugin CommandPluginTemp;
    protected static MarketRetrofit_dialogShell dialog = new MarketRetrofit_dialogShell();
    public static boolean addOrReplaceMarketFounder(MarketRetrofits_MarketFounder MarketFounder){
        for(int a = 0; a < list.size(); a++){
            if(list.get(a).ID.equals(MarketFounder.ID)){
                list.set(a,MarketFounder);
                return true;
            }
        }
        list.add(MarketFounder);
        return false;
    }
    public static boolean removeMarketFounder(String ID){
        for(int a = 0; a < list.size(); a++){
            if(list.get(a).ID.equals(ID)){
                list.remove(a);
                return true;
            }
        }
        return false;
    }
    public static MarketRetrofits_MarketFounder getMarketFounder(String ID){
        for(int a = 0; a < list.size(); a++){
            if(list.get(a).ID.equals(ID)){
                return list.get(a);
            }
        }
        return null;
    }

    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarkets(SectorEntityToken planet){
        noJumpTemp = planet.getStarSystem().hasTag(SYSTEM_CUT_OFF_FROM_HYPER);
        hostilesTemp = false;
        return getFoundableMarkets(planet,hostilesTemp,noJumpTemp);
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarkets(SectorEntityToken planet, boolean hostiles, boolean noJump){
        ArrayList<MarketRetrofits_MarketFounder> output = new ArrayList<>();
        for(int a = 0; a < list.size(); a++){
            MarketRetrofits_MarketFounder b = list.get(a);
            if(b.canEstablishOutpost(planet) && (b.canEstablishAMarketHere(planet,hostiles,noJump))){
                output.add(b);
            }
        }
        MarketRetrofits_Logger.logging("the number of foundable markets is: "+output.size()+". the number of possable market is: "+list.size(),new MarketRetrofits_MarketFounderMasterList(),true);
        return output;
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarketsInOrder(SectorEntityToken planet){
        noJumpTemp = planet.getStarSystem().hasTag(SYSTEM_CUT_OFF_FROM_HYPER);
        hostilesTemp = false;
        return getFoundableMarketsInOrder(planet,hostilesTemp,noJumpTemp);
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarketsInOrder(SectorEntityToken planet,boolean hostiles,boolean noJump){
        ArrayList<MarketRetrofits_MarketFounder> active = getFoundableMarkets(planet,hostiles,noJump);
        ArrayList<MarketRetrofits_MarketFounder> output = new ArrayList<>();

        /*todo:
        *  this while loop is the most inefficient thing i could possibly make. plz improve with a better system that does not take arraylength^2 runs to commpleat.*/
        while(active.size() != 0){
            double min = active.get(0).order;
            int index = 0;
            for(int b = 1; b < active.size(); b++){
                MarketRetrofits_MarketFounder c = active.get(b);
                if(c.order < min){
                    min = c.order;
                    index = b;
                }
            }
            output.add(active.get(index));
            active.remove(index);
        }
        MarketRetrofits_Logger.logging("the number of foundable markets is: "+output.size()+". the number of possable market is: "+list.size(),new MarketRetrofits_MarketFounderMasterList(),true);
        return output;
    }

    /*public static void runMarketListPlugin(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap){
        for(int b = 0; b < list.size(); b++){
            list.get(b).getOutpostFoundOption(ruleId, dialog, params, memoryMap);
        }
    }*/

    public static String ruleIdTemp1;
    public static InteractionDialogAPI dialogTemp1;
    public static List<Misc.Token> paramsTemp1;
    public static Map<String, MemoryAPI> memoryMapTemp1;
    public static InteractionDialogPlugin dialogTemp2;

    public static SectorEntityToken Planet = null;
    public static MarketRetrofit_dialogShell activateMarketFoundingListDialog(BaseCommandPlugin CommandPlugin,String ruleIdTemp, InteractionDialogAPI dialogTemp, List<Misc.Token> paramsTemp, Map<String, MemoryAPI> memoryMapTemp){
        dialogTemp2 = dialogTemp.getPlugin();
        CommandPluginTemp = CommandPlugin;
        MarketRetrofits_customMarketFounder_dialog a = new MarketRetrofits_customMarketFounder_dialog(dialogTemp.getInteractionTarget());
        dialog.Dialog = a;
        Planet = dialogTemp.getInteractionTarget();

        dialogTemp.setPlugin(dialog);

        ruleIdTemp1=ruleIdTemp;
        dialogTemp1=dialogTemp;
        paramsTemp1=paramsTemp;
        memoryMapTemp1=memoryMapTemp;

        ArrayList<MarketRetrofits_MarketFounder> b = MarketRetrofits_MarketFounderMasterList.getFoundableMarketsInOrder(Planet);
        if(b.size() == 1 && b.get(0).skipOptionSelectionIfOnlyOption(Planet)){
            a.setData(dialogTemp);
            a.runMarketFoundingPage(b.get(0));
            return dialog;
        }
        dialog.init(dialogTemp);
        return dialog;
    }

    public static void foundMarket(MarketRetrofits_MarketFounder marketFounder){
        //CommandPluginTemp.
        MarketRetrofits_Logger.logging("got the dialogTemp1: "+dialogTemp1,new MarketRetrofits_MarketFounderMasterList(),true);
        MarketRetrofits_Logger.logging(", and the dialogTemp2: "+dialogTemp2,new MarketRetrofits_MarketFounderMasterList(),true);
        MarketRetrofits_customMarketFounder_MarketFoundingListiner.marketFounder = marketFounder;
        dialogTemp1.setPlugin(dialogTemp2);
        OpenCoreTab a = new OpenCoreTab();
        MarketRetrofits_SurveyPlugin.costArray = marketFounder.getOutpostConsumed(Planet);
        //Misc.Token b = new Misc.Token("CARGO OPEN", Misc.TokenType.VARIABLE);
        //OpenCoreTab CARGO OPEN
        //paramsTemp1.add(b);//.put("CARGO","OPEN");
        /*MarketRetrofits_Logger.logging("getting size of data."+paramsTemp1.size()+", "+memoryMapTemp1.size(),new MarketRetrofits_MarketFounderMasterList(),true);
        MarketRetrofits_Logger.logging("",new MarketRetrofits_MarketFounderMasterList(),true);
        for(Misc.Token b : paramsTemp1){
            MarketRetrofits_Logger.logging(""+b.string+", "+b.type+", "+b.varMemoryKey+", "+b.varNameWithoutMemoryKeyIfKeyIsValid,new MarketRetrofits_MarketFounderMasterList(),true);
        }
        MarketRetrofits_Logger.logging("",new MarketRetrofits_MarketFounderMasterList(),true);
        for(MemoryAPI b : memoryMapTemp1.values()){
        }*/
        a.execute(ruleIdTemp1,dialogTemp1,paramsTemp1,memoryMapTemp1);
        //a.execute();
    }

}
