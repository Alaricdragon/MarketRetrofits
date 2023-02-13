package data.scripts.industries.Implementation.defaultIndustrys;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.BattleAPI;
import com.fs.starfarer.api.campaign.CampaignEventListener;
import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.econ.*;
import com.fs.starfarer.api.campaign.rules.MemoryAPI;
import com.fs.starfarer.api.impl.campaign.DebugFlags;
import com.fs.starfarer.api.impl.campaign.econ.impl.MilitaryBase.PatrolFleetData;
import com.fs.starfarer.api.impl.campaign.fleets.*;
import com.fs.starfarer.api.impl.campaign.rulecmd.salvage.MarketCMD;
import com.fs.starfarer.api.ui.TooltipMakerAPI;
import com.fs.starfarer.api.util.IntervalUtil;
import com.fs.starfarer.api.util.Misc;
import com.fs.starfarer.api.util.Pair;
import com.fs.starfarer.api.util.WeightedRandomPicker;
import data.scripts.MarketRetrofits_Logger;
import data.scripts.industries.MarketRetorfits_ExstraData;
import data.scripts.industries.MarketRetrofits_DefaltInstanceIndustry;
import org.apache.log4j.Logger;
import org.lwjgl.util.vector.Vector2f;


import java.awt.Color;
import java.util.Random;

import com.fs.starfarer.api.campaign.listeners.FleetEventListener;
import com.fs.starfarer.api.impl.campaign.fleets.FleetFactoryV3;
import com.fs.starfarer.api.impl.campaign.fleets.FleetParamsV3;
import com.fs.starfarer.api.impl.campaign.fleets.PatrolAssignmentAIV4;
import com.fs.starfarer.api.impl.campaign.fleets.RouteManager;
import com.fs.starfarer.api.impl.campaign.fleets.RouteManager.RouteData;
import com.fs.starfarer.api.impl.campaign.fleets.RouteManager.RouteFleetSpawner;
import com.fs.starfarer.api.impl.campaign.ids.Commodities;
import com.fs.starfarer.api.impl.campaign.ids.Factions;
import com.fs.starfarer.api.impl.campaign.ids.Industries;
import com.fs.starfarer.api.impl.campaign.ids.MemFlags;
import com.fs.starfarer.api.impl.campaign.ids.Ranks;
import com.fs.starfarer.api.impl.campaign.ids.Stats;
import com.fs.starfarer.api.impl.campaign.ids.Strings;

public class MarketRetrofit_MilitaryBaseInstance extends MarketRetrofits_DefaltInstanceIndustry {// implements RouteFleetSpawner, FleetEventListener{//implements RouteManager.RouteFleetSpawner, FleetEventListener{
    public MarketRetrofit_MilitaryBaseInstance(String name, float orderT) {
        super(name, orderT);
    }
    public IntervalUtil tracker = new IntervalUtil(Global.getSettings().getFloat("averagePatrolSpawnInterval") * 0.7f,
            Global.getSettings().getFloat("averagePatrolSpawnInterval") * 1.3f);
    private static String trackerName = "tracker";
    public float returningPatrolValue = 0f;
    private static String returningPatrolValueName = "returningPatrolValue";
    private final static boolean MilataryBaseLogs = Global.getSettings().getBoolean("MarketRetrofits_MiltaryBaseLogs");
    @Override
    public void getExtraDataFromIndustry(MarketRetorfits_ExstraData extraData){
        returningPatrolValue = (float) extraData.getFloat(returningPatrolValueName);
        Object temp = extraData.getData(trackerName);
        if(temp == null){
            tracker = new IntervalUtil(Global.getSettings().getFloat("averagePatrolSpawnInterval") * 0.7f,
                    Global.getSettings().getFloat("averagePatrolSpawnInterval") * 1.3f);
            return;
        }
        tracker = (IntervalUtil) temp;
    }
    @Override
    public void setExtraDataToIndustry(MarketRetorfits_ExstraData extraData){
        extraData.addData(trackerName,tracker);
        extraData.addData(returningPatrolValueName,returningPatrolValue);
    }

    public static float OFFICER_PROB_MOD_PATROL_HQ = 0.1f;
    public static float OFFICER_PROB_MOD_MILITARY_BASE = 0.2f;
    public static float OFFICER_PROB_MOD_HIGH_COMMAND = 0.3f;


    public static float DEFENSE_BONUS_PATROL = 0.1f;
    public static float DEFENSE_BONUS_MILITARY = 0.2f;
    public static float DEFENSE_BONUS_COMMAND = 0.3f;

    public static int IMPROVE_NUM_PATROLS_BONUS = 1;
    @Override
    public void apply() {

        int size = market.getSize();

        boolean patrol = CurrentIndustry.getSpec().hasTag(Industries.TAG_PATROL);
        boolean militaryBase = CurrentIndustry.getSpec().hasTag(Industries.TAG_MILITARY);
        boolean command = CurrentIndustry.getSpec().hasTag(Industries.TAG_COMMAND);

        super.apply(!patrol);
        if (patrol) {
            CurrentIndustry.applyIncomeAndUpkeep(3);
        }

        int extraDemand = 0;

        int light = 1;
        int medium = 0;
        int heavy = 0;

        if (patrol) {
            extraDemand = 0;
        } else if (militaryBase) {
            extraDemand = 2;
        } else if (command) {
            extraDemand = 3;
        }

        if (patrol) {
            light = 2;
            medium = 0;
            heavy = 0;
        } else {
            if (size <= 3) {
                light = 2;
                medium = 0;
                heavy = 0;
            } else if (size == 4) {
                light = 2;
                medium = 0;
                heavy = 0;
            } else if (size == 5) {
                light = 2;
                medium = 1;
                heavy = 0;
            } else if (size == 6) {
                light = 3;
                medium = 1;
                heavy = 0;
            } else if (size == 7) {
                light = 3;
                medium = 2;
                heavy = 0;
            } else if (size == 8) {
                light = 3;
                medium = 3;
                heavy = 0;
            } else if (size >= 9) {
                light = 4;
                medium = 3;
                heavy = 0;
            }
        }

        if (militaryBase || command) {
            //light++;
            medium = Math.max(medium + 1, size / 2 - 1);
            heavy = Math.max(heavy, medium - 1);
        }

        if (command) {
            medium++;
            heavy++;
        }

//		if (market.getId().equals("jangala")) {
//			System.out.println("wefwefwe");
//		}

//		light += 5;
//		medium += 3;
//		heavy += 2;

//		float spawnRateMultStability = getStabilitySpawnRateMult();
//		if (spawnRateMultStability != 1) {
//			market.getStats().getDynamic().getStat(Stats.COMBAT_FLEET_SPAWN_RATE_MULT).modifyMult(getModId(), spawnRateMultStability);
//		}


        market.getStats().getDynamic().getMod(Stats.PATROL_NUM_LIGHT_MOD).modifyFlat(CurrentIndustry.getModId(), light);
        market.getStats().getDynamic().getMod(Stats.PATROL_NUM_MEDIUM_MOD).modifyFlat(CurrentIndustry.getModId(), medium);
        market.getStats().getDynamic().getMod(Stats.PATROL_NUM_HEAVY_MOD).modifyFlat(CurrentIndustry.getModId(), heavy);


        CurrentIndustry.demand(Commodities.SUPPLIES, size - 1 + extraDemand);
        CurrentIndustry.demand(Commodities.FUEL, size - 1 + extraDemand);
        CurrentIndustry.demand(Commodities.SHIPS, size - 1 + extraDemand);

        CurrentIndustry.supply(Commodities.CREW, size);

        if (!patrol) {
            //demand(Commodities.HAND_WEAPONS, size);
            CurrentIndustry.supply(Commodities.MARINES, size);

//			Pair<String, Integer> deficit = getMaxDeficit(Commodities.HAND_WEAPONS);
//			applyDeficitToProduction(1, deficit, Commodities.MARINES);
        }


        CurrentIndustry.modifyStabilityWithBaseMod();

        float mult = getDeficitMult(Commodities.SUPPLIES);
        String extra = "";
        if (mult != 1) {
            String com = CurrentIndustry.getMaxDeficit(Commodities.SUPPLIES).one;
            extra = " (" + getDeficitText(com).toLowerCase() + ")";
        }
        float bonus = DEFENSE_BONUS_MILITARY;
        if (patrol) bonus = DEFENSE_BONUS_PATROL;
        else if (command) bonus = DEFENSE_BONUS_COMMAND;
        market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD)
                .modifyMult(CurrentIndustry.getModId(), 1f + bonus * mult, CurrentIndustry.getNameForModifier() + extra);


        MemoryAPI memory = market.getMemoryWithoutUpdate();
        Misc.setFlagWithReason(memory, MemFlags.MARKET_PATROL, CurrentIndustry.getModId(), true, -1);

        if (militaryBase || command) {
            Misc.setFlagWithReason(memory, MemFlags.MARKET_MILITARY, CurrentIndustry.getModId(), true, -1);
        }

        float officerProb = OFFICER_PROB_MOD_PATROL_HQ;
        if (militaryBase) officerProb = OFFICER_PROB_MOD_MILITARY_BASE;
        else if (command) officerProb = OFFICER_PROB_MOD_HIGH_COMMAND;
        market.getStats().getDynamic().getMod(Stats.OFFICER_PROB_MOD).modifyFlat(CurrentIndustry.getModId(0), officerProb);


        if (!CurrentIndustry.isFunctional()) {
            supply.clear();
            CurrentIndustry.unapply();
        }

    }

    @Override
    public void unapply() {
        super.unapply();

        MemoryAPI memory = market.getMemoryWithoutUpdate();
        Misc.setFlagWithReason(memory, MemFlags.MARKET_PATROL, CurrentIndustry.getModId(), false, -1);
        Misc.setFlagWithReason(memory, MemFlags.MARKET_MILITARY, CurrentIndustry.getModId(), false, -1);

        CurrentIndustry.unmodifyStabilityWithBaseMod();

        //market.getStats().getDynamic().getStat(Stats.COMBAT_FLEET_SPAWN_RATE_MULT).unmodifyMult(getModId());
        //market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MULT).unmodifyFlat(getModId());

        market.getStats().getDynamic().getMod(Stats.PATROL_NUM_LIGHT_MOD).unmodifyFlat(CurrentIndustry.getModId());
        market.getStats().getDynamic().getMod(Stats.PATROL_NUM_MEDIUM_MOD).unmodifyFlat(CurrentIndustry.getModId());
        market.getStats().getDynamic().getMod(Stats.PATROL_NUM_HEAVY_MOD).unmodifyFlat(CurrentIndustry.getModId());

        market.getStats().getDynamic().getMod(Stats.GROUND_DEFENSES_MOD).unmodifyMult(CurrentIndustry.getModId());

        market.getStats().getDynamic().getMod(Stats.OFFICER_PROB_MOD).unmodifyFlat(CurrentIndustry.getModId(0));
    }
    @Override
    public boolean hasPostDemandSection(boolean hasDemand, IndustryTooltipMode mode) {
        return mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional();
    }

    @Override
    public void addPostDemandSection(TooltipMakerAPI tooltip, boolean hasDemand, IndustryTooltipMode mode) {
        if (mode != IndustryTooltipMode.NORMAL || CurrentIndustry.isFunctional()) {
            CurrentIndustry.addStabilityPostDemandSection(tooltip, hasDemand, mode);

            boolean patrol = CurrentIndustry.getSpec().hasTag(Industries.TAG_PATROL);
            boolean command = CurrentIndustry.getSpec().hasTag(Industries.TAG_COMMAND);
            float bonus = DEFENSE_BONUS_MILITARY;
            if (patrol) bonus = DEFENSE_BONUS_PATROL;
            if (command) bonus = DEFENSE_BONUS_COMMAND;
            CurrentIndustry.addGroundDefensesImpactSection(tooltip, bonus, Commodities.SUPPLIES);
        }
    }

    @Override
    public int getBaseStabilityMod() {
        boolean patrol = CurrentIndustry.getSpec().hasTag(Industries.TAG_PATROL);
        boolean militaryBase = CurrentIndustry.getSpec().hasTag(Industries.TAG_MILITARY);
        boolean command = CurrentIndustry.getSpec().hasTag(Industries.TAG_COMMAND);
        int stabilityMod = 1;
        if (patrol) {
            stabilityMod = 1;
        } else if (militaryBase) {
            stabilityMod = 2;
        } else if (command) {
            stabilityMod = 2;
        }
        return stabilityMod;
    }
    @Override
    public String getNameForModifier() {
//		boolean patrol = Industries.PATROLHQ.equals(getId());
//		if (patrol) return getSpec().getName();
        if (CurrentIndustry.getSpec().getName().contains("HQ")) {
            return CurrentIndustry.getSpec().getName();
        }

        return Misc.ucFirst(CurrentIndustry.getSpec().getName().toLowerCase());
    }


//	public float getStabilitySpawnRateMult() {
//		return Math.max(0.2f, market.getStabilityValue() / 10f);
//	}

    @Override
    public Pair<String, Integer> getStabilityAffectingDeficit() {
        boolean patrol = CurrentIndustry.getSpec().hasTag(Industries.TAG_PATROL);
        if (patrol) {
            return CurrentIndustry.getMaxDeficit(Commodities.SUPPLIES, Commodities.FUEL, Commodities.SHIPS);
        }
        //return getMaxDeficit(Commodities.SUPPLIES, Commodities.FUEL, Commodities.SHIPS, Commodities.HAND_WEAPONS);
        return CurrentIndustry.getMaxDeficit(Commodities.SUPPLIES, Commodities.FUEL, Commodities.SHIPS);
    }


    @Override
    public String getCurrentImage() {
        boolean military = Industries.MILITARYBASE.equals(CurrentIndustry.getId());
        if (military) {
            PlanetAPI planet = market.getPlanetEntity();
            if (planet == null || planet.isGasGiant()) {
                return Global.getSettings().getSpriteName("industry", "military_base_orbital");
            }
        }
        return super.getCurrentImage();
    }

    @Override
    public boolean isDemandLegal(CommodityOnMarketAPI com) {
        return true;
    }
    @Override
    public boolean isSupplyLegal(CommodityOnMarketAPI com) {
        return true;
    }
    @Override
    public boolean isAvailableToBuild() {
        boolean canBuild = false;
        for (Industry ind : market.getIndustries()) {
            if (ind == getIndustry()) continue;//HERE
            if (!ind.isFunctional()) continue;
            if (ind.getSpec().hasTag(Industries.TAG_SPACEPORT)) {
                canBuild = true;
                break;
            }
        }
        return canBuild;
    }
    @Override
    public String getUnavailableReason() {
        return "Requires a functional spaceport";
    }


    //public IntervalUtil tracker = new IntervalUtil(5f, 9f);

    @Override
    public void buildingFinished() {
        super.buildingFinished();
        tracker.forceIntervalElapsed();
    }

    @Override
    public void upgradeFinished(Industry previous) {
        super.upgradeFinished(previous);
        tracker.forceIntervalElapsed();
    }

    @Override
    public void advance(float amount) {
        super.advance(amount);

        if (Global.getSector().getEconomy().isSimMode()) return;

        if (!CurrentIndustry.isFunctional()) return;

        float days = Global.getSector().getClock().convertToDays(amount);

//		float stability = market.getPrevStability();
//		float spawnRate = 1f + (stability - 5) * 0.2f;
//		if (spawnRate < 0.5f) spawnRate = 0.5f;

        float spawnRate = 1f;
        float rateMult = market.getStats().getDynamic().getStat(Stats.COMBAT_FLEET_SPAWN_RATE_MULT).getModifiedValue();
        spawnRate *= rateMult;

        if (Global.getSector().isInNewGameAdvance()) {
            spawnRate *= 3f;
        }
        float extraTime = 0f;
        if (returningPatrolValue > 0) {
            // apply "returned patrols" to spawn rate, at a maximum rate of 1 interval per day
            float interval = tracker.getIntervalDuration();
            extraTime = interval * days;
            returningPatrolValue -= days;
            if (returningPatrolValue < 0) returningPatrolValue = 0;
        }
        tracker.advance(days * spawnRate + extraTime);

        //DebugFlags.FAST_PATROL_SPAWN = true;
        if (DebugFlags.FAST_PATROL_SPAWN) {
            tracker.advance(days * spawnRate * 100f);
        }

        if (tracker.intervalElapsed()) {
//			if (market.isPlayerOwned()) {
//				System.out.println("ewfwefew");
//			}
//			if (market.getName().equals("Jangala")) {
//				System.out.println("wefwefe");
//			}
            String sid = getRouteSourceId();

            int light = getCount(FleetFactory.PatrolType.FAST);
            int medium = getCount(FleetFactory.PatrolType.COMBAT);
            int heavy = getCount(FleetFactory.PatrolType.HEAVY);

            int maxLight = getMaxPatrols(FleetFactory.PatrolType.FAST);
            int maxMedium = getMaxPatrols(FleetFactory.PatrolType.COMBAT);
            int maxHeavy = getMaxPatrols(FleetFactory.PatrolType.HEAVY);

            WeightedRandomPicker<FleetFactory.PatrolType> picker = new WeightedRandomPicker<FleetFactory.PatrolType>();
            picker.add(FleetFactory.PatrolType.HEAVY, maxHeavy - heavy);
            picker.add(FleetFactory.PatrolType.COMBAT, maxMedium - medium);
            picker.add(FleetFactory.PatrolType.FAST, maxLight - light);


            if (picker.isEmpty()) return;

            FleetFactory.PatrolType type = picker.pick();
            PatrolFleetData custom = new PatrolFleetData(type);

            RouteManager.OptionalFleetData extra = new RouteManager.OptionalFleetData(market);
            extra.fleetType = type.getFleetType();

            RouteData route = RouteManager.getInstance().addRoute(sid, market, Misc.genRandomSeed(), extra, (RouteFleetSpawner) getIndustry(), custom);////HERE. (casting) this is added by me  //HERE. this not being set right may be the issue... very frustrating
            loging("MarketRetrofits_buildingRoute: " + route.getSource());
            extra.strength = (float) getPatrolCombatFP(type, route.getRandom());
            extra.strength = Misc.getAdjustedStrength(extra.strength, market);


            float patrolDays = 35f + (float) Math.random() * 10f;
            route.addSegment(new RouteManager.RouteSegment(patrolDays, market.getPrimaryEntity()));
        }
    }

    /*public static class PatrolFleetData {
        public FleetFactory.PatrolType type;
        public int spawnFP;
        //public int despawnFP;
        public PatrolFleetData(FleetFactory.PatrolType type) {
            this.type = type;
        }
    }*/
    @Override
    public void reportAboutToBeDespawnedByRouteManager(RouteData route) {
//		if (route.getActiveFleet() == null) return;
//		PatrolFleetData custom = (PatrolFleetData) route.getCustom();
//		custom.despawnFP = route.getActiveFleet().getFleetPoints();
    }
    @Override
    public boolean shouldRepeat(RouteData route) {
//		PatrolFleetData custom = (PatrolFleetData) route.getCustom();
////		return custom.spawnFP == custom.despawnFP ||
////				(route.getActiveFleet() != null && route.getActiveFleet().getFleetPoints() >= custom.spawnFP * 0.6f);
//		return route.getActiveFleet() != null && route.getActiveFleet().getFleetPoints() >= custom.spawnFP * 0.6f;
        return false;
    }

    public int getCount(FleetFactory.PatrolType... types) {
        int count = 0;
        for (RouteData data : RouteManager.getInstance().getRoutesForSource(getRouteSourceId())) {
            if (data.getCustom() instanceof PatrolFleetData) {
                PatrolFleetData custom = (PatrolFleetData) data.getCustom();
                for (FleetFactory.PatrolType type : types) {
                    if (type == custom.type) {
                        count++;
                        break;
                    }
                }
            }
        }
        return count;
    }

    public int getMaxPatrols(FleetFactory.PatrolType type) {
        if (type == FleetFactory.PatrolType.FAST) {
            return (int) market.getStats().getDynamic().getMod(Stats.PATROL_NUM_LIGHT_MOD).computeEffective(0);
        }
        if (type == FleetFactory.PatrolType.COMBAT) {
            return (int) market.getStats().getDynamic().getMod(Stats.PATROL_NUM_MEDIUM_MOD).computeEffective(0);
        }
        if (type == FleetFactory.PatrolType.HEAVY) {
            return (int) market.getStats().getDynamic().getMod(Stats.PATROL_NUM_HEAVY_MOD).computeEffective(0);
        }
        return 0;
    }

    public String getRouteSourceId() {
        return CurrentIndustry.getMarket().getId() + "_" + "military";
    }
    @Override
    public boolean shouldCancelRouteAfterDelayCheck(RouteData route) {
        return false;
    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI fleet, CampaignFleetAPI primaryWinner, BattleAPI battle) {

    }
    @Override
    public void reportFleetDespawnedToListener(CampaignFleetAPI fleet, CampaignEventListener.FleetDespawnReason reason, Object param) {
        if (!CurrentIndustry.isFunctional()) return;

        if (reason == CampaignEventListener.FleetDespawnReason.REACHED_DESTINATION) {
            RouteData route = RouteManager.getInstance().getRoute(getRouteSourceId(), fleet);
            //if(route == null){return;}//HERE
            loging("MarketRetrofits_reportFleetDespawnedToListener: " + CurrentIndustry.getMarket().getId() + "_" + "military");
            loging("MarketRetrofits_reportFleetDespawnedToListenerALT: " + getIndustry().getMarket().getId() + "_" + "military");

            if (route.getCustom() instanceof PatrolFleetData) {
                PatrolFleetData custom = (PatrolFleetData) route.getCustom();
                if (custom.spawnFP > 0) {
                    float fraction  = fleet.getFleetPoints() / custom.spawnFP;
                    returningPatrolValue += fraction;
                }
            }
        }
    }

    public static int getPatrolCombatFP(FleetFactory.PatrolType type, Random random) {
        float combat = 0;
        switch (type) {
            case FAST:
                combat = Math.round(3f + (float) random.nextFloat() * 2f) * 5f;
                break;
            case COMBAT:
                combat = Math.round(6f + (float) random.nextFloat() * 3f) * 5f;
                break;
            case HEAVY:
                combat = Math.round(10f + (float) random.nextFloat() * 5f) * 5f;
                break;
        }
        return (int) Math.round(combat);
    }
    @Override
    public CampaignFleetAPI spawnFleet(RouteData route) {

        PatrolFleetData custom = (PatrolFleetData) route.getCustom();
        FleetFactory.PatrolType type = custom.type;

        Random random = route.getRandom();

        CampaignFleetAPI fleet = createPatrol(type, market.getFactionId(), route, market, null, random);

        if (fleet == null || fleet.isEmpty()) return null;

        fleet.addEventListener((FleetEventListener) getIndustry());//HERE. (casting) this is added by me

        market.getContainingLocation().addEntity(fleet);
        fleet.setFacing((float) Math.random() * 360f);
        // this will get overridden by the patrol assignment AI, depending on route-time elapsed etc
        fleet.setLocation(market.getPrimaryEntity().getLocation().x, market.getPrimaryEntity().getLocation().y);

        fleet.addScript(new PatrolAssignmentAIV4(fleet, route));

        fleet.getMemoryWithoutUpdate().set(MemFlags.FLEET_IGNORES_OTHER_FLEETS, true, 0.3f);

        //market.getContainingLocation().addEntity(fleet);
        //fleet.setLocation(market.getPrimaryEntity().getLocation().x, market.getPrimaryEntity().getLocation().y);

        if (custom.spawnFP <= 0) {
            custom.spawnFP = fleet.getFleetPoints();
        }

        return fleet;
    }

    public static CampaignFleetAPI createPatrol(FleetFactory.PatrolType type, String factionId, RouteData route, MarketAPI market, Vector2f locInHyper, Random random) {
        if (random == null) random = new Random();


        float combat = getPatrolCombatFP(type, random);
        float tanker = 0f;
        float freighter = 0f;
        String fleetType = type.getFleetType();
        switch (type) {
            case FAST:
                break;
            case COMBAT:
                tanker = Math.round((float) random.nextFloat() * 5f);
                break;
            case HEAVY:
                tanker = Math.round((float) random.nextFloat() * 10f);
                freighter = Math.round((float) random.nextFloat() * 10f);
                break;
        }

        FleetParamsV3 params = new FleetParamsV3(
                market,
                locInHyper,
                factionId,
                route == null ? null : route.getQualityOverride(),
                fleetType,
                combat, // combatPts
                freighter, // freighterPts
                tanker, // tankerPts
                0f, // transportPts
                0f, // linerPts
                0f, // utilityPts
                0f // qualityMod
        );
        if (route != null) {
            params.timestamp = route.getTimestamp();
        }
        params.random = random;
        CampaignFleetAPI fleet = FleetFactoryV3.createFleet(params);

        if (fleet == null || fleet.isEmpty()) return null;

        if (!fleet.getFaction().getCustomBoolean(Factions.CUSTOM_PATROLS_HAVE_NO_PATROL_MEMORY_KEY)) {
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_PATROL_FLEET, true);
            if (type == FleetFactory.PatrolType.FAST || type == FleetFactory.PatrolType.COMBAT) {
                fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_CUSTOMS_INSPECTOR, true);
            }
        } else if (fleet.getFaction().getCustomBoolean(Factions.CUSTOM_PIRATE_BEHAVIOR)) {
            fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_PIRATE, true);

            // hidden pather and pirate bases
            // make them raid so there's some consequence to just having a colony in a system with one of those
            if (market != null && market.isHidden()) {
                fleet.getMemoryWithoutUpdate().set(MemFlags.MEMORY_KEY_RAIDER, true);
            }
        }

        String postId = Ranks.POST_PATROL_COMMANDER;
        String rankId = Ranks.SPACE_COMMANDER;
        switch (type) {
            case FAST:
                rankId = Ranks.SPACE_LIEUTENANT;
                break;
            case COMBAT:
                rankId = Ranks.SPACE_COMMANDER;
                break;
            case HEAVY:
                rankId = Ranks.SPACE_CAPTAIN;
                break;
        }

        fleet.getCommander().setPostId(postId);
        fleet.getCommander().setRankId(rankId);

        return fleet;
    }



    public static float ALPHA_CORE_BONUS = 0.25f;
    @Override
    public void applyAlphaCoreModifiers() {
        market.getStats().getDynamic().getMod(Stats.COMBAT_FLEET_SIZE_MULT).modifyMult(
                CurrentIndustry.getModId(), 1f + ALPHA_CORE_BONUS, "Alpha core (" + CurrentIndustry.getNameForModifier() + ")");
    }

    @Override
    public void applyNoAICoreModifiers() {
        market.getStats().getDynamic().getMod(Stats.COMBAT_FLEET_SIZE_MULT).unmodifyMult(CurrentIndustry.getModId());
    }

    @Override
    public void applyAlphaCoreSupplyAndDemandModifiers() {
        demandReduction.modifyFlat(CurrentIndustry.getModId(0), DEMAND_REDUCTION, "Alpha core");
    }
    @Override
    public void addAlphaCoreDescription(TooltipMakerAPI tooltip, AICoreDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        String pre = "Alpha-level AI core currently assigned. ";
        if (mode == AICoreDescriptionMode.MANAGE_CORE_DIALOG_LIST || mode == AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            pre = "Alpha-level AI core. ";
        }
        float a = ALPHA_CORE_BONUS;
        //String str = "" + (int)Math.round(a * 100f) + "%";
        String str = Strings.X + (1f + a);

        if (mode == AICoreDescriptionMode.INDUSTRY_TOOLTIP) {
            CommoditySpecAPI coreSpec = Global.getSettings().getCommoditySpec(aiCoreId);
            TooltipMakerAPI text = tooltip.beginImageWithText(coreSpec.getIconName(), 48);
            text.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                            "Increases fleet size by %s.", 0f, highlight,
                    "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                    str);
            tooltip.addImageWithText(opad);
            return;
        }

        tooltip.addPara(pre + "Reduces upkeep cost by %s. Reduces demand by %s unit. " +
                        "Increases fleet size by %s.", opad, highlight,
                "" + (int)((1f - UPKEEP_MULT) * 100f) + "%", "" + DEMAND_REDUCTION,
                str);

    }


    @Override
    public boolean canImprove() {
        return true;
    }
    @Override
    public void applyImproveModifiers() {

        String key = "mil_base_improve";
        if (CurrentIndustry.isImproved()) {
            boolean patrol = CurrentIndustry.getSpec().hasTag(Industries.TAG_PATROL);
//			boolean militaryBase = getSpec().hasTag(Industries.TAG_MILITARY);
//			boolean command = getSpec().hasTag(Industries.TAG_COMMAND);

            if (patrol) {
                market.getStats().getDynamic().getMod(Stats.PATROL_NUM_MEDIUM_MOD).modifyFlat(key, IMPROVE_NUM_PATROLS_BONUS);
            } else {
                market.getStats().getDynamic().getMod(Stats.PATROL_NUM_HEAVY_MOD).modifyFlat(key, IMPROVE_NUM_PATROLS_BONUS);
            }
        } else {
            market.getStats().getDynamic().getMod(Stats.PATROL_NUM_MEDIUM_MOD).unmodifyFlat(key);
            market.getStats().getDynamic().getMod(Stats.PATROL_NUM_HEAVY_MOD).unmodifyFlat(key);
        }
    }
    @Override
    public void addImproveDesc(TooltipMakerAPI info, ImprovementDescriptionMode mode) {
        float opad = 10f;
        Color highlight = Misc.getHighlightColor();

        String str = "" + (int) IMPROVE_NUM_PATROLS_BONUS;

        boolean patrol = CurrentIndustry.getSpec().hasTag(Industries.TAG_PATROL);
        String type = "medium patrols";
        if (!patrol) type = "heavy patrols";

        if (mode == ImprovementDescriptionMode.INDUSTRY_TOOLTIP) {
            info.addPara("Number of " + type + " launched increased by %s.", 0f, highlight, str);
        } else {
            info.addPara("Increases the number of " + type + " launched by %s.", 0f, highlight, str);
        }

        info.addSpacer(opad);
        super.addImproveDesc(info, mode);
    }


    @Override
    public MarketCMD.RaidDangerLevel adjustCommodityDangerLevel(String commodityId, MarketCMD.RaidDangerLevel level) {
        return level.next();
    }

    @Override
    public MarketCMD.RaidDangerLevel adjustItemDangerLevel(String itemId, String data, MarketCMD.RaidDangerLevel level) {
        return level.next();
    }


    private void loging(String output){
        MarketRetrofits_Logger.logging(output,this,MilataryBaseLogs);
        //final Logger LOG = Global.getLogger(this.getClass());
        //LOG.info(output);
    }
}
