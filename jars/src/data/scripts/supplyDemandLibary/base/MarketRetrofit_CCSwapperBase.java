package data.scripts.supplyDemandLibary.base;

public class MarketRetrofit_CCSwapperBase extends MarketRetrofit_CCBase {
    public String removedCommodity;
    public String addedCommodity;
    public MarketRetrofit_CCSwapperBase(String name, float orderT, String removeCommodity, String addCommodity){
        super(name, orderT);
        removedCommodity = removeCommodity;
        addedCommodity = addCommodity;
    }
}
