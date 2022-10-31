package data.scripts.supplyDemandLibary.changes;

import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MutableCommodityQuantity;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSwapperBase;

public class MarketRetrofit_CCSwapDemand extends MarketRetrofit_CCSwapperBase {
    public MarketRetrofit_CCSwapDemand(String name, float orderT, String removeCommodity, String addCommodity){
        super(name,orderT,removeCommodity,addCommodity);
    }
    @Override
    public void apply(Industry industry,String ID){
        MutableCommodityQuantity supply = industry.getDemand(removedCommodity);
        float a = supply.getQuantity().getModifiedValue();
        if(a == 0){
            return;
        }
        industry.getDemand(addedCommodity).getQuantity().modifyFlat(ID, applyMutableStat(a));
        supply.getQuantity().modifyMult(ID,0);
    }
    @Override
    public void unApply(Industry industry,String ID){
        industry.getDemand(removedCommodity).getQuantity().unmodify(ID);
        industry.getDemand(addedCommodity).getQuantity().unmodify(ID);
    }
}
