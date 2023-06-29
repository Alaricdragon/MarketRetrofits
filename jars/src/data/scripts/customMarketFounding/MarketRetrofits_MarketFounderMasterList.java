package data.scripts.customMarketFounding;

import com.fs.starfarer.api.campaign.InteractionDialogAPI;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.util.Misc;
import data.scripts.supportCode.MarketRetrofit_dialogShell;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarketRetrofits_MarketFounderMasterList {
    public static ArrayList<MarketRetrofits_MarketFounder> list = new ArrayList<>();
    public static boolean noJumpTemp = false;
    public static boolean hostilesTemp = false;
    protected static MarketRetrofit_dialogShell dialog = new MarketRetrofit_dialogShell();
    public static boolean addOrReplaceMarketFounder(MarketRetrofits_MarketFounder MarketFounder){
        for(int a = 0; a < list.size(); a++){
            if(list.get(a).ID.equals(MarketFounder.ID)){
                list.set(a,MarketFounder);
                return true;
            }
        }
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

    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarkets(){
        return getFoundableMarkets(hostilesTemp,noJumpTemp);
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarkets(boolean hostiles,boolean noJump){
        ArrayList<MarketRetrofits_MarketFounder> output = new ArrayList<>();
        for(int a = 0; a < list.size(); a++){
            MarketRetrofits_MarketFounder b = list.get(a);
            if(b.canEstablishOutpost() && (!hostiles || b.canFoundWithHostileActivity()) && (!noJump && b.canFoundWithoutJumpPonits())){
                output.add(b);
            }
        }
        return output;
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarketsInOrder(){
        return getFoundableMarketsInOrder(hostilesTemp,noJumpTemp);
    }
    public static ArrayList<MarketRetrofits_MarketFounder> getFoundableMarketsInOrder(boolean hostiles,boolean noJump){
        ArrayList<MarketRetrofits_MarketFounder> active = getFoundableMarkets(hostiles,noJump);
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
        return output;
    }

    /*public static void runMarketListPlugin(String ruleId, InteractionDialogAPI dialog, List<Misc.Token> params, Map<String, MemoryAPI> memoryMap){
        for(int b = 0; b < list.size(); b++){
            list.get(b).getOutpostFoundOption(ruleId, dialog, params, memoryMap);
        }
    }*/

    public static MarketRetrofit_dialogShell activateMarketFoundingListDialog(InteractionDialogAPI dialogAPI){
        dialog.Dialog = new MarketRetrofits_customMarketFounder_dialog("");
        dialog.init(dialogAPI);
        return dialog;
    }

}
