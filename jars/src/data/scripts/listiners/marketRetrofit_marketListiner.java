package data.scripts.listiners;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.EconomyAPI;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCIndustrySet;
import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCMasterList;

public class marketRetrofit_marketListiner  implements EconomyAPI.EconomyUpdateListener {
    private static String condition = "marketRetrofit_hiddinCondition";
    private void applyMarketData() {
        //HERE
        for (MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()) {
            MarketRetrofit_CCIndustrySet c = MarketRetrofit_CCMasterList.getGeneralSets();
            market = Global.getSector().getEconomy().getMarket(market.getId());
            for(Industry a: market.getIndustries()){//market.getIndustry()
                MarketRetrofit_CCIndustrySet b = MarketRetrofit_CCMasterList.getIndustrySet(a.getId());
                if (b != null && !b.connected) {
                    b.apply(a);
                    if(c != null){
                        c.apply(a);
                    }
                }
                if(b == null && c != null){
                    c.apply(a);
                }
            }
        }
    }
    private void applyReqCondition() {
        for (MarketAPI market : Global.getSector().getEconomy().getMarketsCopy()) {
            market = Global.getSector().getEconomy().getMarket(market.getId());
            if (!market.hasCondition(condition)) {
                market.addCondition(condition);
            }
        }
    }
    private void crash(){
        int[] a = {};
        a[0] = a[9999];
    }
    @Override
    public void commodityUpdated(String commodityId) {

    }

    @Override
    public void economyUpdated() {
        //applyMarketData();
        applyReqCondition();
        //crash();
    }
    @Override
    public boolean isEconomyListenerExpired() {
        return false;
    }
}