package data.scripts.supplyDemandLibary.base;

import com.fs.starfarer.api.campaign.econ.Industry;

public class MarketRetrofit_SwapperBase extends MarketRetrofit_commoditiesChangeBase {
    public String removedCommodity;
    public String addedCommodity;
    public MarketRetrofit_SwapperBase(String name, float orderT, String removeCommodity, String addCommodity){
        super(name, orderT);
        removedCommodity = removeCommodity;
        addedCommodity = addCommodity;
    }
}
