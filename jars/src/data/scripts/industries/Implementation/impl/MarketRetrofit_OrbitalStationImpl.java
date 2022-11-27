package data.scripts.industries.Implementation.impl;

import com.fs.starfarer.api.campaign.CampaignFleetAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import data.scripts.industries.MarketRetrofit_BaseIndustry;

public class MarketRetrofit_OrbitalStationImpl extends MarketRetrofit_BaseIndustry {
    public CampaignFleetAPI stationFleet = null;
    public boolean usingExistingStation = false;
    public SectorEntityToken stationEntity = null;
    @Override
    public String MarketRetrofits_IndustryID(){
        return "station_base";
    }
}
