package data.scripts.marketconditions;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketImmigrationModifier;
import com.fs.starfarer.api.impl.campaign.econ.BaseHazardCondition;
import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCIndustrySet;
import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCMasterList;

public class MarketRetrofit_hiddenCondition extends BaseMarketConditionPlugin {
    @Override
    public void apply(String id) {
        super.apply(id);
        applyCC();
        BaseHazardCondition a = new BaseHazardCondition();
    }
    @Override
    public void unapply(String id) {
        super.unapply(id);
    }
    @Override
    public boolean showIcon(){
        return false;
    }
    private void applyCC(){
        MarketRetrofit_CCIndustrySet c = MarketRetrofit_CCMasterList.getGeneralSets();
        for(Industry a: market.getIndustries()){
            //a.getSupply("food").getQuantity().modifyFlat("hhh",1,"nummy");
            MarketRetrofit_CCIndustrySet b = MarketRetrofit_CCMasterList.getIndustrySet(a.getId());
            if (b != null && !b.connected) {
                b.apply(a);
                if(c != null){
                    c.apply(a);
                }
            }
            if(b == null && c != null){
                c.apply(a);
            }
        }
    }
}