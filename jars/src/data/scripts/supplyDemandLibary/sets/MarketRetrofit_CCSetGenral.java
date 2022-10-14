package data.scripts.supplyDemandLibary.sets;

import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCMasterList;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSetBase;

public class MarketRetrofit_CCSetGenral extends MarketRetrofit_CCSetBase {
    public MarketRetrofit_CCSetGenral(String name) {
        super(name);
        applyToGenralChanges();
    }
    private void applyToGenralChanges(){
        MarketRetrofit_CCMasterList.addSetToGenral(this);
    }
}