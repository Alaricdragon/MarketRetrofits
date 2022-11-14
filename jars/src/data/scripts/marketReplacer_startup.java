package data.scripts;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import data.scripts.industries.Implementation.defaultIndustrys.MarketRetrofit_MiningInstance;
import data.scripts.listiners.marketRetrofit_marketInteractionListiner;
import data.scripts.listiners.marketRetrofit_marketListiner;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSetBase;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSwapperBase;
import data.scripts.supplyDemandLibary.changes.MarketRetrofit_CCAddDemand;
import data.scripts.supplyDemandLibary.changes.MarketRetrofit_CCSwapSupply;
import data.scripts.supplyDemandLibary.lists.MarketRetrofit_CCMasterList;
import data.scripts.supplyDemandLibary.sets.MarketRetrofit_CCSetGenral;
import data.scripts.supplyDemandLibary.sets.MarketRetrofit_CCSetIndustry;

/*
 */
public class marketReplacer_startup extends BaseModPlugin {
    @Override
    public void onApplicationLoad() {
        applyDefaultIndustryInstances();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        super.onGameLoad(newGame);
        applyListeners();
        MarketRetrofit_CCMasterList.startup();
    }
    private static void applyListeners(){
        marketRetrofit_marketListiner a = new marketRetrofit_marketListiner();
        Global.getSector().getEconomy().addUpdateListener(a);
        //Global.getSector().addTransientListener(new marketRetrofit_marketInteractionListiner(false));
    }
    private static void applyDefaultIndustryInstances(){
        MarketRetrofit_MiningInstance miningInstance = new MarketRetrofit_MiningInstance("mining",0);
        miningInstance.applyToIndustry("mining");
    }
    private static void test(){
        /**/
        MarketRetrofit_CCSwapSupply a = new MarketRetrofit_CCSwapSupply("name",0,"crew","fuel");
        MarketRetrofit_CCSetGenral b = new MarketRetrofit_CCSetGenral("MainThing");
        b.addChange(a);

        MarketRetrofit_CCAddDemand c = new MarketRetrofit_CCAddDemand("name",0,"lobster");
        c.modifyMult("aa",2);
        MarketRetrofit_CCSetIndustry d = new MarketRetrofit_CCSetIndustry("");
        d.addChange(c);
        d.applyToIndustry("population");/**/
        //b.addChange(a);
        //b.applyToIndustry("IndustryName0");
        //b.applyToIndustry("IndustryName1");
    }
}