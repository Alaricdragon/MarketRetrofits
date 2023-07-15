package data.scripts.customMarketFounding;

import java.util.Map;

public class MarketRetrofits_customMarketFounder_BaseMarket extends MarketRetrofits_MarketFounder{
    public MarketRetrofits_customMarketFounder_BaseMarket(String ID, String name) {
        super(ID, name);
        this.skipDescriptionIfOnlyOption = false;
    }

    @Override
    public Map<String, Integer> getOutpostConsumed() {
        return super.getOutpostConsumed();
    }
}
