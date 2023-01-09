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
    public MarketRetrofit_BaseIndustry CurrentIndustry;
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
                break;
            case "aiCoreId":
                this.aiCoreId = (String) data;
                break;
            case "buildCostOverride":
                this.buildCostOverride = (Float) data;
                break;
            case "building":
                this.building = (boolean) data;
                break;
            case "buildProgress":
                this.buildProgress = (float) data;
                break;
            case "buildTime":
                this.buildTime = (float) data;
                break;
            case "currTooltipMode":
                this.currTooltipMode = (Industry.IndustryTooltipMode) data;
                break;
            case "demand":
                this.demand = (Map<String, MutableCommodityQuantity>) data;
                break;
            case "demandReduction":
                this.demandReduction = (MutableStat) data;
                break;
            case "demandReductionFromOther":
                this.demandReductionFromOther = (MutableStat) data;
                break;
            case "dKey":
                this.dKey = (String) data;
                break;
            case "hasInstallableItems":
                this.hasInstallableItems = (Boolean) data;
                break;
            case "hiddenOverride":
                this.hiddenOverride = (Boolean) data;
                break;
            case "id":
                this.id = (String) data;
                break;
            case "improved":
                this.improved = (Boolean) data;
                break;
            case "income":
                this.income = (MutableStat) data;
                break;
            case "spec":
                this.spec = (IndustrySpecAPI) data;
                break;
            case "special":
                this.special = (SpecialItemData) data;
                break;
            case "supply":
                this.supply = (Map<String, MutableCommodityQuantity>) data;
                break;
            case "supplyBonus":
                this.supplyBonus = (MutableStat) data;
                break;
            case "supplyBonusFromOther":
                this.supplyBonusFromOther = (MutableStat) data;
                break;
            case "upgradeId":
                this.upgradeId = (String) data;
                break;
            case "upkeep":
                this.upkeep = (MutableStat) data;
                break;
            case "wasDisrupted":
                this.wasDisrupted = (boolean) data;
                break;
            default:
                this.exstraData.addData(dataName,data);
                break;
        }
    }

    public MarketRetorfits_ExstraData getExstraData(){
        return exstraData;
    }

    @Override
    public void apply() {

    }
}
