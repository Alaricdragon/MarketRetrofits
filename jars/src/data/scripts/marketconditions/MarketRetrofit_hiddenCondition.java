package data.scripts.marketconditions;

import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.econ.BaseHazardCondition;
import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.marketConditionReplacer.marketConditionReplacer_ConditionSet;
import data.scripts.marketConditionReplacer.marketConditonReplacer_MasterList;
import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCIndustrySet;
import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCMasterList;

import java.util.ArrayList;

public class MarketRetrofit_hiddenCondition extends BaseMarketConditionPlugin {
    @Override
    public void apply(String id) {
        super.apply(id);
        applyCC();
        //applyMarketConditionReplacer();
        //BaseHazardCondition a = new BaseHazardCondition();
        /*/if(market.hasCondition("AIRetrofit_AIPop")) {
            String id2 = "mild_climate";
            market.getHazard().modifyFlat(id2, 20, market.getCondition(id2).getName());
        }/**/
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

    public void applyMarketConditionReplacer(){
    }
}