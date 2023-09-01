package data.scripts.customMarketFounding;

import com.fs.starfarer.api.campaign.SectorEntityToken;

import java.util.Map;

public class MarketRetrofits_customMarketFounder_BaseMarket extends MarketRetrofits_MarketFounder{
    public MarketRetrofits_customMarketFounder_BaseMarket(String ID, String name) {
        super(ID, name);
        this.skipOptionSelectionIfOnlyOption = true;
        this.MarketFounderHasDescription = false;
        this.MarketFounderDescription = "lololol i gott dat description boi";
    }

    @Override
    public Map<String, Integer> getOutpostConsumed(SectorEntityToken planet) {
        return super.getOutpostConsumed(planet);
    }
}
