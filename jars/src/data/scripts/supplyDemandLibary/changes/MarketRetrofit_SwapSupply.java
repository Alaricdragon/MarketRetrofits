package data.scripts.supplyDemandLibary.changes;

import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MutableCommodityQuantity;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_SwapperBase;

public class MarketRetrofit_SwapSupply extends MarketRetrofit_SwapperBase {
    MarketRetrofit_SwapSupply(String name, float orderT, String removeCommodity, String addCommodity){
        super(name,orderT,removeCommodity,addCommodity);
    }
    @Override
    public void apply(Industry industry,String ID){
        MutableCommodityQuantity supply = industry.getSupply(removedCommodity);
        industry.getSupply(addedCommodity).getQuantity().modifyFlat(ID, applyMutableStat(supply.getQuantity().base));
        supply.getQuantity().modifyMult(ID,0);
    }
    @Override
    public void unApply(Industry industry,String ID){
        industry.getSupply(removedCommodity).getQuantity().unmodify(ID);
        industry.getSupply(addedCommodity).getQuantity().unmodify(ID);
    }
}
