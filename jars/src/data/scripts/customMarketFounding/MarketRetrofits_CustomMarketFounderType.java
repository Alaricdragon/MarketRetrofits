package data.scripts.customMarketFounding;

import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.impl.campaign.ids.Tags;
import com.fs.starfarer.api.impl.campaign.rulecmd.OpenCoreTab;
import data.scripts.MarketRetrofits_Constants;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.customMarketFounding.surveyPlugin.MarketRetrofits_SurveyPlugin;

public class MarketRetrofits_CustomMarketFounderType {
    String ID;
    public MarketRetrofits_CustomMarketFounderType(String ID){
        this.ID = ID;
    }
    public void foundAMarket(MarketRetrofits_MarketFounder marketFounder, boolean noJumpTemp, boolean hostilesTemp, SectorEntityToken Planet){
        //CommandPluginTemp.
        MarketRetrofits_Logger.logging("runing a market founder type of ID "+this.ID,this, MarketRetrofits_Constants.CustomMarketFounderLogs);
        MarketRetrofits_customMarketFounder_MarketFoundingListiner.marketFounder = marketFounder;
        boolean tempRemoveHyperTag = false;
        if (noJumpTemp && Planet != null && Planet.getStarSystem().hasTag(Tags.SYSTEM_CUT_OFF_FROM_HYPER)){
            tempRemoveHyperTag = true;
        }
        MarketRetrofits_MarketFounderMasterList.dialogTemp1.setPlugin(MarketRetrofits_MarketFounderMasterList.dialogTemp2);
        OpenCoreTab a = new OpenCoreTab();
        //MarketRetrofits_SurveyPlugin.costArray = marketFounder.getOutpostConsumed(Planet);
        MarketRetrofits_SurveyPlugin.activeFounder = marketFounder;
        MarketRetrofits_SurveyPlugin.planet = Planet;
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
        if (tempRemoveHyperTag) Planet.getStarSystem().removeTag(Tags.SYSTEM_CUT_OFF_FROM_HYPER);
        a.execute(MarketRetrofits_MarketFounderMasterList.ruleIdTemp1,MarketRetrofits_MarketFounderMasterList.dialogTemp1,MarketRetrofits_MarketFounderMasterList.paramsTemp1,MarketRetrofits_MarketFounderMasterList.memoryMapTemp1);
        if (tempRemoveHyperTag) Planet.getStarSystem().addTag(Tags.SYSTEM_CUT_OFF_FROM_HYPER);
        //a.execute();
    }
}
