package data.scripts.customMarketFounding;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.InteractionDialogPlugin;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.rulecmd.BaseCommandPlugin;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.HostileFleetNearbyAndAware;
import com.fs.starfarer.api.util.Misc;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.supportCode.MarketRetrofit_dialogShell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.fs.starfarer.api.impl.campaign.ids.Tags.SYSTEM_CUT_OFF_FROM_HYPER;

public class MarketRetrofits_MarketFounderMasterList {
    private static ArrayList<MarketRetrofits_MarketFounder> list = new ArrayList<>();
    private static ArrayList<MarketRetrofits_CustomMarketFounderType> FoundTypeList = new ArrayList<>();
    public static ArrayList<String> CantFoundMarketTags_Planet = new ArrayList<>();
    public static ArrayList<String> CantFoundMarketTags_System = new ArrayList<>();
    public static MarketRetrofits_CustomMarketFounderType currentMarketFoundType = null;
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
    public static boolean addOrReplaceMarketFounderType(MarketRetrofits_CustomMarketFounderType MarketFounderType){
        for(int a = 0; a < FoundTypeList.size(); a++){
            if(FoundTypeList.get(a).ID.equals(MarketFounderType.ID)){
                FoundTypeList.set(a,MarketFounderType);
                return true;
            }
        }
        FoundTypeList.add(MarketFounderType);
        return false;
    }
    public static MarketRetrofits_CustomMarketFounderType getMarketFounderType(String ID){
        for(int a = 0; a < FoundTypeList.size(); a++){
            if(FoundTypeList.get(a).ID.equals(ID)){
                return FoundTypeList.get(a);
            }
        }
        return null;
    }
    public static void actavateMarketFounder(String ID){
        for(int a = 0; a < FoundTypeList.size(); a++){
            if(FoundTypeList.get(a).ID.equals(ID)){
                 currentMarketFoundType = FoundTypeList.get(a);
            }
        }

    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarkets(SectorEntityToken planet){
        noJumpTemp = false;
        try {
            noJumpTemp = planet.getStarSystem().hasTag(SYSTEM_CUT_OFF_FROM_HYPER);
        }catch (Exception e){
            MarketRetrofits_Logger.logging("failled to get tag 'SYSTEM_CUT_OFF_FROM_HYPER'. error: "+e,new MarketRetrofits_MarketFounderMasterList(),true);
        }
        hostilesTemp = false;
        try {
            hostilesTemp = new HostileFleetNearbyAndAware().execute(null, null, null, null);
        }catch (Exception e){
            MarketRetrofits_Logger.logging("failed to get data: 'HostileFleetNearbyAndAware()'. error: "+e,new MarketRetrofits_MarketFounderMasterList(),true);
        }
        return getFoundableMarkets(planet,hostilesTemp,noJumpTemp);
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarkets(SectorEntityToken planet, boolean hostiles, boolean noJump){
        ArrayList<MarketRetrofits_MarketFounder> output = new ArrayList<>();
        if (hasCantFoundMarketTag(planet)) return output;
        for(int a = 0; a < list.size(); a++){
            MarketRetrofits_MarketFounder b = list.get(a);
            if(/*b.canEstablishOutpost(planet) && */(b.canEstablishAMarketHere(planet,hostiles,noJump))){
                output.add(b);
            }
        }
        MarketRetrofits_Logger.logging("the number of foundable markets is: "+output.size()+". the number of possable market is: "+list.size(),new MarketRetrofits_MarketFounderMasterList(),true);
        return output;
    }
    public static boolean hasCantFoundMarketTag(SectorEntityToken planet){
        for(String a : CantFoundMarketTags_Planet){
            try {
                if (planet.hasTag(a)) {
                    return true;
                }
            }catch (Exception e){

            }
        }
        for(String a : CantFoundMarketTags_System){
            try {
                if (planet.getStarSystem().hasTag(a)) {
                    return true;
                }
            }catch (Exception e){

            }
        }
        return false;
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarketsForDialog(SectorEntityToken planet){
        noJumpTemp = planet.getStarSystem().hasTag(SYSTEM_CUT_OFF_FROM_HYPER);
        hostilesTemp = new HostileFleetNearbyAndAware().execute(null,null,null,null);
        return getFoundableMarketsForDialog(planet,hostilesTemp,noJumpTemp);
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarketsForDialog(SectorEntityToken planet, boolean hostiles, boolean noJump){
        //ArrayList<MarketRetrofits_MarketFounder> active = getFoundableMarkets(planet,hostiles,noJump);
        ArrayList<MarketRetrofits_MarketFounder> output = new ArrayList<>();
        if (hasCantFoundMarketTag(planet)) return output;
        ArrayList<MarketRetrofits_MarketFounder> active = new ArrayList<>();
        for(int a = 0; a < list.size(); a++){
            MarketRetrofits_MarketFounder b = list.get(a);
            if(/*b.canEstablishOutpost(planet) && */(b.showOption(planet,hostiles,noJump))){
                output.add(b);
            }
        }
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

        ArrayList<MarketRetrofits_MarketFounder> b = MarketRetrofits_MarketFounderMasterList.getFoundableMarketsForDialog(Planet);
        if(b.size() == 1 && b.get(0).skipOptionSelectionIfOnlyOption(Planet)){
            a.setData(dialogTemp);
            a.runMarketFoundingPage(b.get(0));
            return dialog;
        }
        dialog.init(dialogTemp);
        return dialog;
    }

    public static void foundMarket(MarketRetrofits_MarketFounder marketFounder){
        currentMarketFoundType.foundAMarket(marketFounder,noJumpTemp,hostilesTemp,Planet);
    }

}
