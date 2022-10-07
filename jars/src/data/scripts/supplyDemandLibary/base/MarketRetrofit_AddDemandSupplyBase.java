package data.scripts.supplyDemandLibary.base;

public class MarketRetrofit_AddDemandSupplyBase extends MarketRetrofit_commoditiesChangeBase{
    public String added;
    public MarketRetrofit_AddDemandSupplyBase(String name, float orderT,String addedT){
        super(name, orderT);
        added = addedT;
    }
}
