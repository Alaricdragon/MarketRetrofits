package data.scripts.supplyDemandLibary.sets;

import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCMasterList;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSetBase;

public class MarketRetrofit_CCSetIndustry extends MarketRetrofit_CCSetBase {
    public MarketRetrofit_CCSetIndustry(String name) {
        super(name);
    }
    public void applyToIndustry(String Industry){
        MarketRetrofit_CCMasterList.addSetToIndustrySet(Industry,this);
    }
}
