package data.scripts.customMarketFounding;

import com.fs.starfarer.api.impl.campaign.ids.Commodities;

import java.util.LinkedHashMap;
import java.util.Map;

public class MarketRetrofits_MarketFounder {
    String name = "";
    int order = 0;
    public MarketRetrofits_MarketFounder(String name){
        this.name = name;
    }
    public MarketRetrofits_MarketFounder(String name,int order){
        this.name = name;
        this.order = order;
    }
    public boolean canEstablishOutpost(){
        return true;
    }
    public String getOutpostFoundOptionText(){
        return "";
    }
    public boolean showOutpostFoundingDescription(){
        return false;
    }
    public void getOutpostFoundingDescription(){

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
}
