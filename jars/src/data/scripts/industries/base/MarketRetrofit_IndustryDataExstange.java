package data.scripts.industries.base;

import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MutableCommodityQuantity;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import data.scripts.industries.MarketRetorfits_ExstraData;
import data.scripts.industries.MarketRetrofit_BaseIndustry;

import java.util.Map;

public class MarketRetrofit_IndustryDataExstange extends BaseIndustry{
    public MarketRetorfits_ExstraData exstraData = new MarketRetorfits_ExstraData();
    public Object getData(String dataName){
        //pastIndustrys.add(CurrentInstance);
        //if(pastIndustrys.size() <= 1){
        return getDataPrivate(dataName);
        //}
        //return pastIndustrys.get(pastIndustrys.size() - 1).getData(dataName);
    }
    public void setData(String dataName,Object data) {
        //pastIndustrys.remove(pastIndustrys.size() - 1);
        //if(pastIndustrys.size() == 0) {
        setDataPrivate(dataName, data);
        //}
        //pastIndustrys.get(pastIndustrys.size() - 1).setData(dataName,data);
    }
    private Object getDataPrivate(String dataName){
        //building a list of data here
        /*
        this.aiCoreId;
        this.buildCostOverride;
        this.building;
        this.buildProgress;
        this.buildProgress;

        this.buildTime;
        this.currTooltipMode;
        this.demand;
        this.demandReduction;

        this.demandReductionFromOther;
        this.dKey;
        this.hasInstallableItems;
        this.hiddenOverride;
        this.id;
        this.improved;

        this.income;
        this.spec;
        this.special;
        this.supply;
        this.supplyBonus;
        this.supplyBonusFromOther;
        this.upgradeId;
        this.upkeep;
        this.wasDisrupted;*/
        switch (dataName){
            case "market":
                return this.market;
            case "aiCoreId":
                return this.aiCoreId;
            case "buildCostOverride":
                return this.buildCostOverride;
            case "building":
                return this.building;
            case "buildProgress":
                return this.buildProgress;
            case "buildTime":
                return this.buildTime;
            case "currTooltipMode":
                return this.currTooltipMode;
            case "demand":
                return this.demand;
            case "demandReduction":
                return this.demandReduction;
            case "demandReductionFromOther":
                return this.demandReductionFromOther;
            case "dKey":
                return this.dKey;
            case "hasInstallableItems":
                return this.hasInstallableItems;
            case "hiddenOverride":
                return this.hiddenOverride;
            case "id":
                return this.id;
            case "improved":
                return this.improved;
            case "income":
                return this.income;
            case "spec":
                return this.spec;
            case "special":
                return this.special;
            case "supply":
                return this.supply;
            case "supplyBonus":
                return this.supplyBonus;
            case "supplyBonusFromOther":
                return this.supplyBonusFromOther;
            case "upgradeId":
                return this.upgradeId;
            case "upkeep":
                return this.upkeep;
            case "wasDisrupted":
                return this.wasDisrupted;
            default:
                return this.exstraData.getData(dataName);
        }
    }
    private void setDataPrivate(String dataName,Object data){
        switch (dataName){
            case "market":
                this.market = (MarketAPI) data;
            case "aiCoreId":
                this.aiCoreId = (String) data;
            case "buildCostOverride":
                this.buildCostOverride = (Float) data;
            case "building":
                this.building = (boolean) data;
            case "buildProgress":
                this.buildProgress = (float) data;
            case "buildTime":
                this.buildTime = (float) data;
            case "currTooltipMode":
                this.currTooltipMode = (Industry.IndustryTooltipMode) data;
            case "demand":
                this.demand = (Map<String, MutableCommodityQuantity>) data;
            case "demandReduction":
                this.demandReduction = (MutableStat) data;
            case "demandReductionFromOther":
                this.demandReductionFromOther = (MutableStat) data;
            case "dKey":
                this.dKey = (String) data;
            case "hasInstallableItems":
                this.hasInstallableItems = (Boolean) data;
            case "hiddenOverride":
                this.hiddenOverride = (Boolean) data;
            case "id":
                this.id = (String) data;
            case "improved":
                this.improved = (Boolean) data;
            case "income":
                this.income = (MutableStat) data;
            case "spec":
                this.spec = (IndustrySpecAPI) data;
            case "special":
                this.special = (SpecialItemData) data;
            case "supply":
                this.supply = (Map<String, MutableCommodityQuantity>) data;
            case "supplyBonus":
                this.supplyBonus = (MutableStat) data;
            case "supplyBonusFromOther":
                this.supplyBonusFromOther = (MutableStat) data;
            case "upgradeId":
                this.upgradeId = (String) data;
            case "upkeep":
                this.upkeep = (MutableStat) data;
            case "wasDisrupted":
                this.wasDisrupted = (boolean) data;
            default:
                this.exstraData.addData(dataName,data);
        }
    }

    public MarketRetorfits_ExstraData getExstraData(){
        return exstraData;
    }

    @Override
    public void apply() {

    }
}
