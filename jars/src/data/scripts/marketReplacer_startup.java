package data.scripts;
import com.fs.starfarer.api.BaseModPlugin;
import data.scripts.supplyDemandLibary.MarketRetrofit_commoditiesChangeSet;
import data.scripts.supplyDemandLibary.base.MarketRetrofit_SwapperBase;

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
        MarketRetrofit_SwapperBase a = new MarketRetrofit_SwapperBase("name",0,"replace","with");
        MarketRetrofit_commoditiesChangeSet b = new MarketRetrofit_commoditiesChangeSet("name2");
        b.addChange(a);
    }
}