package data.scripts;
import com.fs.starfarer.api.BaseModPlugin;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSetBase;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_CCSwapperBase;
import data.scripts.supplyDemandLibary.changes.MarketRetrofit_CCSwapSupply;
import data.scripts.supplyDemandLibary.sets.MarketRetrofit_CCSetIndustry;

/*
 */
public class marketReplacer_startup extends BaseModPlugin {
    @Override
    public void onApplicationLoad() {
        startup2();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        super.onGameLoad(newGame);
    }
    private static void startup2(){
        MarketRetrofit_CCSwapSupply a = new MarketRetrofit_CCSwapSupply("name",0,"replace","with");
        MarketRetrofit_CCSetIndustry b = new MarketRetrofit_CCSetIndustry("name2");
        b.addChange(a);
        a = new MarketRetrofit_CCSwapSupply("name3",0,"replace2","with2");
        b.addChange(a);
        b.applyToIndustry("IndustryName0");
        b.applyToIndustry("IndustryName1");
    }
}