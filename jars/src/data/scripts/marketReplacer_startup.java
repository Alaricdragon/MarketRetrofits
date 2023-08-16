package data.scripts;
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.campaign.econ.Market;
import data.scripts.combatabilityPatches.MarketRetrofits_InitCombatabilityPatches;
import data.scripts.customMarketFounding.MarketRetrofits_MarketFounderMasterList;
import data.scripts.customMarketFounding.MarketRetrofits_customMarketFounder_BaseMarket;
import data.scripts.customMarketFounding.MarketRetrofits_customMarketFounder_MarketFoundingListiner;
import data.scripts.industries.Implementation.defaultIndustrys.*;
import data.scripts.industries.MarketRetorfits_ExstraData;
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
        /*MarketRetrofits_Logger.logging("trying to see if object returns null",this);
        MarketRetorfits_ExstraData a = new MarketRetorfits_ExstraData();
        String b = null;
        a.addData("a",b);
        b = (String) a.getData("a");
        if(b != null) {
            MarketRetrofits_Logger.logging("not null",this);
        }else if(b.equals(null)){
            MarketRetrofits_Logger.logging("is null",this);
        }*/
        //applyDefaultIndustryInstances();
        MarketRetrofits_InitCombatabilityPatches.onApplicationLoad();
        applyDefaultCustomMarketFounder();
    }

    @Override
    public void onGameLoad(boolean newGame) {
        super.onGameLoad(newGame);
        applyListeners();
        MarketRetrofit_CCMasterList.startup();
        MarketRetrofits_InitCombatabilityPatches.onGameLoad(newGame);
    }
    private static void applyListeners(){
        marketRetrofit_marketListiner a = new marketRetrofit_marketListiner();
        Global.getSector().getEconomy().addUpdateListener(a);
        MarketRetrofits_customMarketFounder_MarketFoundingListiner.addListiner();
        Global.getSector().addTransientListener(new marketRetrofit_marketInteractionListiner(false));
    }
    private static void applyDefaultCustomMarketFounder(){
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket","Found a ordinary market")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket1","Found a ordinary market1")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket2","Found a ordinary market2")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket3","Found a ordinary market3")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket4","Found a ordinary market4")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket5","Found a ordinary market5")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket6","Found a ordinary market6")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket7","Found a ordinary market7")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket8","Found a ordinary market8")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket9","Found a ordinary market9")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket10","Found a ordinary market10")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket11","Found a ordinary market11")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket12","Found a ordinary market12")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket13","Found a ordinary market13")
        );
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(
                new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket14","Found a ordinary market14")
        );
        /*/
        MarketRetrofits_customMarketFounder_BaseMarket temp = new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket15","Found a ordinary market15");
        temp.canFoundWithoutJumpPonits=true;
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(temp
        );
        temp = new MarketRetrofits_customMarketFounder_BaseMarket("MarketRetrofits_DefaultMarket16","Found a ordinary market16");
        temp.canFoundWithoutJumpPonits=true;
        MarketRetrofits_MarketFounderMasterList.addOrReplaceMarketFounder(temp
        );/**/
    }
    private static void applyDefaultIndustryInstances(){
        MarketRetrofit_MiningInstance miningInstance = new MarketRetrofit_MiningInstance("mining",0);
        miningInstance.applyToIndustry("mining");

        MarketRetrofit_PopulationInstance a= new MarketRetrofit_PopulationInstance("population",0);
        a.applyToIndustry("population");

        MarketRetrofit_FarmingInstance b = new MarketRetrofit_FarmingInstance("farming",0);
        b.applyToIndustry("farming");
        b = new MarketRetrofit_FarmingInstance("aquaculture",0);
        b.applyToIndustry("aquaculture");

        MarketRetrofit_TechminingInstance c = new MarketRetrofit_TechminingInstance("techmining",0);
        c.applyToIndustry("techmining");

        MarketRetrofit_RefiningInstance d = new MarketRetrofit_RefiningInstance("refining",0);
        d.applyToIndustry("refining");

        MarketRetrofit_SpaceportInstance e = new MarketRetrofit_SpaceportInstance("spaceport",0);
        e.applyToIndustry("spaceport");
        e = new MarketRetrofit_SpaceportInstance("megaport",0);
        e.applyToIndustry("megaport");

        MarketRetrofit_LightindustryInstance f = new MarketRetrofit_LightindustryInstance("lightindustry",0);
        f.applyToIndustry("lightindustry");

        MarketRetrofit_HeavyIndustryInstance g = new MarketRetrofit_HeavyIndustryInstance("heavyindustry",0);
        g.applyToIndustry("heavyindustry");
        g = new MarketRetrofit_HeavyIndustryInstance("orbitalworks",0);
        g.applyToIndustry("orbitalworks");

        MarketRetrofit_fuelprodInstance h = new MarketRetrofit_fuelprodInstance("fuelprod",0);
        h.applyToIndustry("fuelprod");

        MarketRetrofit_CommerceInstance i = new MarketRetrofit_CommerceInstance("commerce",0);
        i.applyToIndustry("commerce");

        MarketRetrofit_OrbitalStationInstance j = new MarketRetrofit_OrbitalStationInstance("station_base",0);
        j.applyToIndustry("station_base");
        j = new MarketRetrofit_OrbitalStationInstance("orbitalstation",0);
        j.applyToIndustry("orbitalstation");
        j = new MarketRetrofit_OrbitalStationInstance("battlestation",0);
        j.applyToIndustry("battlestation");
        j = new MarketRetrofit_OrbitalStationInstance("starfortress",0);
        j.applyToIndustry("starfortress");
        j = new MarketRetrofit_OrbitalStationInstance("orbitalstation_mid",0);
        j.applyToIndustry("orbitalstation_mid");
        j = new MarketRetrofit_OrbitalStationInstance("battlestation_mid",0);
        j.applyToIndustry("battlestation_mid");
        j = new MarketRetrofit_OrbitalStationInstance("starfortress_mid",0);
        j.applyToIndustry("starfortress_mid");
        j = new MarketRetrofit_OrbitalStationInstance("orbitalstation_high",0);
        j.applyToIndustry("orbitalstation_high");
        j = new MarketRetrofit_OrbitalStationInstance("battlestation_high",0);
        j.applyToIndustry("battlestation_high");
        j = new MarketRetrofit_OrbitalStationInstance("starfortress_high",0);
        j.applyToIndustry("starfortress_high");

        MarketRetrofit_GroundDefensesInstance k = new MarketRetrofit_GroundDefensesInstance("grounddefenses",0);
        k.applyToIndustry("grounddefenses");
        k = new MarketRetrofit_GroundDefensesInstance("heavybatteries",0);
        k.applyToIndustry("heavybatteries");

        MarketRetrofit_MilitaryBaseInstance l = new MarketRetrofit_MilitaryBaseInstance("patrolhq",0);
        l.applyToIndustry("patrolhq");
        l = new MarketRetrofit_MilitaryBaseInstance("militarybase",0);
        l.applyToIndustry("militarybase");
        l = new MarketRetrofit_MilitaryBaseInstance("highcommand",0);
        l.applyToIndustry("highcommand");

        MarketRetrofit_LionsGuardHQInstance m = new MarketRetrofit_LionsGuardHQInstance("lionsguard",0);
        m.applyToIndustry("lionsguard");

        MarketRetrofit_PlanetaryShieldInstance n = new MarketRetrofit_PlanetaryShieldInstance("planetaryshield",0);
        n.applyToIndustry("planetaryshield");

        MarketRetrofit_WaystationInstance o = new MarketRetrofit_WaystationInstance("waystation",0);
        o.applyToIndustry("waystation");

        MarketRetrofit_CryosanctumInstance p = new MarketRetrofit_CryosanctumInstance("cryosanctum",0);
        p.applyToIndustry("cryosanctum");

        MarketRetrofit_CryorevivalFacilityInstance q = new MarketRetrofit_CryorevivalFacilityInstance("cryorevival",0);
        q.applyToIndustry("cryorevival");




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