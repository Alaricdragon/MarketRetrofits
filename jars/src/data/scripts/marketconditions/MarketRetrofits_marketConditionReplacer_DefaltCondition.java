package data.scripts.marketconditions;

import com.fs.starfarer.api.impl.campaign.econ.BaseMarketConditionPlugin;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.marketConditionReplacer.marketConditonReplacer_MasterList;

public class MarketRetrofits_marketConditionReplacer_DefaltCondition extends BaseMarketConditionPlugin {
    public String activeConditionID = null;
    @Override
    public void apply(String id) {
        super.apply(id);
        if (activeConditionID == null) {
            marketConditonReplacer_MasterList.getOrCreateSet(this.condition.getSpec().getId()).activateMarketCondition(market);
        }
    }

    @Override
    public void unapply(String id) {
        super.unapply(id);
    }
    @Override
    public boolean showIcon(){
        return false;
    }
}
