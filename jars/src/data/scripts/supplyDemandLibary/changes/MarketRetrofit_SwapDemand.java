package data.scripts.supplyDemandLibary.changes;

import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MutableCommodityQuantity;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_SwapperBase;

public class MarketRetrofit_SwapDemand extends MarketRetrofit_SwapperBase {
    MarketRetrofit_SwapDemand(String name, float orderT, String removeCommodity, String addCommodity){
        super(name,orderT,removeCommodity,addCommodity);
    }
    @Override
    public void apply(Industry industry,String ID){
        MutableCommodityQuantity supply = industry.getDemand(removedCommodity);
        industry.getDemand(addedCommodity).getQuantity().modifyFlat(ID, applyMutableStat(supply.getQuantity().base));
        supply.getQuantity().modifyMult(ID,0);
    }
    @Override
    public void unApply(Industry industry,String ID){
        industry.getDemand(removedCommodity).getQuantity().unmodify(ID);
        industry.getDemand(addedCommodity).getQuantity().unmodify(ID);
    }
}
