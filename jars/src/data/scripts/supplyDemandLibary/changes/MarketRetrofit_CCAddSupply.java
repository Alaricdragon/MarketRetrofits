package data.scripts.supplyDemandLibary.changes;

import com.fs.starfarer.api.campaign.econ.Industry;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCAddDemandSupplyBase;

public class MarketRetrofit_CCAddSupply extends MarketRetrofit_CCAddDemandSupplyBase {
    public MarketRetrofit_CCAddSupply(String name, float orderT, String addedT){
        super(name,orderT,addedT);
    }
    @Override
    public void apply(Industry industry,String ID){
        float size = industry.getMarket().getSize();
        industry.getDemand(added).getQuantity().modifyFlat(ID, applyMutableStat(size));
    }
    @Override
    public void unApply(Industry industry,String ID){
        industry.getDemand(added).getQuantity().unmodify(ID);
    }
}
