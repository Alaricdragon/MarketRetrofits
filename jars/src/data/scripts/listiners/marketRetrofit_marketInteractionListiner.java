package data.scripts.listiners;

import com.fs.starfarer.api.campaign.BaseCampaignEventListener;
import com.fs.starfarer.api.campaign.econ.MarketAPI;

public class marketRetrofit_marketInteractionListiner extends BaseCampaignEventListener {
    public marketRetrofit_marketInteractionListiner(boolean permaRegister) {
        super(permaRegister);
    }
    @Override
    public void reportEconomyMonthEnd(){
        /*Global.getSector().getEconomy().getMarketsCopy();
        for(MarketAPI market: Global.getSector().getEconomy().getMarketsCopy() ){
            if(market.hasCondition("AIRetrofit_AIPop") && market.getDaysInExistence() < 34) {
                List<PersonAPI> peopletemp = market.getPeopleCopy();
                //Global.getSettings().createPerson().setId("?");
                for (PersonAPI person : peopletemp) {
                    market.removePerson(person);
                    person.setPortraitSprite(setDataLists.getRandom(2));
                    person.setName(new FullName(setDataLists.getRandom(0), setDataLists.getRandom(1), FullName.Gender.ANY));
                    market.addPerson(person);
                }
            }*/
            /*if(false) {
                market = Global.getSector().getEconomy().getMarket(market.getId());
                market.getPeopleCopy().get(0).getId();
                for(PersonAPI person: market.getPeopleCopy()){
                    //detect if the person has been swapped out with an AI core already.
                    //if so, do nothing.
                    //if not so, swap it out with an AI core...
                    //or do i really really want to? I think i should.
                    //maybe.
                    //ill keep the name then change the image? maybe...
                    //should 100% do so when i create an market however...
                }

            }*/
        //market.getCommDirectory().getEntryForPerson().
        /*}*/
        /*if(setDataLists.fleetMod(fleet)){
            for(int a = 0; a < fleet.getFleetData().getMembersInPriorityOrder().size(); a++){
                fleet.getFleetData().getMembersInPriorityOrder().get(a).getCaptain().setPortraitSprite(setDataLists.getRandom(2));
                fleet.getFleetData().getMembersInPriorityOrder().get(a).getCaptain().setName(new FullName(setDataLists.getRandom(0), setDataLists.getRandom(1), FullName.Gender.ANY));
            }
        }*/
    }
    @Override
    public void reportPlayerOpenedMarket(MarketAPI market){
        market.addCondition("marketRetrofit_hiddinCondition");
        //crash();
    }
    private void crash(){
        int[] a = {};
        a[0] = a[9999];
    }
}
