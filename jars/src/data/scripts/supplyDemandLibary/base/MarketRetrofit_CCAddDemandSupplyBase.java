package data.scripts.supplyDemandLibary.base;

public class MarketRetrofit_CCAddDemandSupplyBase extends MarketRetrofit_CCBase {
    public String added;
    public MarketRetrofit_CCAddDemandSupplyBase(String name, float orderT, String addedT){
        super(name, orderT);
        added = addedT;
    }
}
