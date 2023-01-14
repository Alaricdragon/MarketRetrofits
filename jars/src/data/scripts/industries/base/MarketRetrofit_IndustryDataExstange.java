package data.scripts.industries.base;

import com.fs.starfarer.api.campaign.SpecialItemData;
import com.fs.starfarer.api.campaign.econ.Industry;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.campaign.econ.MutableCommodityQuantity;
import com.fs.starfarer.api.combat.MutableStat;
import com.fs.starfarer.api.impl.campaign.econ.impl.BaseIndustry;
import com.fs.starfarer.api.loading.IndustrySpecAPI;
import data.scripts.MarketRetrofits_Logger;
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
    protected Object getDataPrivate(String dataName){
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


    protected void setDataPrivate(String dataName,Object data){
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

    public void getBaseDataFromIndustry(MarketRetrofit_IndustryDataExstange industryT) {
        /*this.CurrentIndustry = industryT.CurrentIndustry;
        this.exstraData = industryT.exstraData;*/
        //a = industry.getData("a");
        this.aiCoreId = getStringData(industryT,"aiCoreId");
        this.buildCostOverride = (Float) industryT.getData("buildCostOverride");
        this.building = (boolean) industryT.getData("building");
        this.buildProgress = (float) industryT.getData("buildProgress");

        this.buildTime = (float) industryT.getData("buildTime");
        this.currTooltipMode = (Industry.IndustryTooltipMode) industryT.getData("currTooltipMode");
        this.demand = (Map<String, MutableCommodityQuantity>) industryT.getData("demand");
        this.demandReduction = (MutableStat) industryT.getData("demandReduction");

        this.demandReductionFromOther = (MutableStat) industryT.getData("demandReductionFromOther");
        this.dKey = getStringData(industryT,"dKey");
        this.hasInstallableItems = (Boolean) industryT.getData("hasInstallableItems");
        this.hiddenOverride = (Boolean) industryT.getData("hiddenOverride");
        this.id = getStringData(industryT,"id");
        this.improved = (Boolean) industryT.getData("improved");

        this.income = (MutableStat) industryT.getData("income");
        this.spec = (IndustrySpecAPI) industryT.getData("spec");
        this.special = (SpecialItemData) industryT.getData("special");
        this.supply = (Map<String, MutableCommodityQuantity>) industryT.getData("supply");
        this.supplyBonus = (MutableStat) industryT.getData("supplyBonus");
        this.supplyBonusFromOther = (MutableStat) industryT.getData("supplyBonusFromOther");
        this.upgradeId = getStringData(industryT,"upgradeId");
        this.upkeep = (MutableStat) industryT.getData("upkeep");
        this.wasDisrupted = (boolean) industryT.getData("wasDisrupted");
        //get data from industry
    }
    public void setBaseDataToIndustry(MarketRetrofit_IndustryDataExstange industryT) {
        industryT.setData("aiCoreId",this.aiCoreId);
        industryT.setData("buildCostOverride",this.buildCostOverride);
        industryT.setData("building",this.building);
        industryT.setData("buildProgress",this.buildProgress);

        industryT.setData("buildTime",this.buildTime);
        industryT.setData("currTooltipMode",this.currTooltipMode);
        industryT.setData("demand",this.demand);
        industryT.setData("demandReduction",this.demandReduction);

        industryT.setData("demandReductionFromOther",this.demandReductionFromOther);
        industryT.setData("dKey",this.dKey);
        industryT.setData("hasInstallableItems",this.hasInstallableItems);
        industryT.setData("hiddenOverride",this.hiddenOverride);
        industryT.setData("id",this.id);
        industryT.setData("improved",this.improved);

        industryT.setData("income",this.income);
        industryT.setData("spec",this.spec);
        industryT.setData("special",this.special);
        industryT.setData("supply",this.supply);
        industryT.setData("supplyBonus",this.supplyBonus);
        industryT.setData("supplyBonusFromOther",this.supplyBonusFromOther);
        industryT.setData("upgradeId",this.upgradeId);
        industryT.setData("upkeep",this.upkeep);
        industryT.setData("wasDisrupted",this.wasDisrupted);
        //get data from industry
    }

    private String getStringData(MarketRetrofit_IndustryDataExstange industryT,String dataname){
        Object a = industryT.getData(dataname);
        if(a == null){
            return null;
        }
        return a.toString();
    }
    @Override
    public void apply() {

    }

    protected void readData(String exstraText){
        MarketRetrofits_Logger.logging(exstraText + "aiCoreId: " + aiCoreId,this);
        MarketRetrofits_Logger.logging(exstraText + "buildCostOverride: " + buildCostOverride,this);
        MarketRetrofits_Logger.logging(exstraText + "building: " + building,this);
        MarketRetrofits_Logger.logging(exstraText + "buildProgress: " + buildProgress,this);

        MarketRetrofits_Logger.logging(exstraText + "buildTime: " + buildTime,this);
        MarketRetrofits_Logger.logging(exstraText + "currTooltipMode: " + currTooltipMode,this);
        MarketRetrofits_Logger.logging(exstraText + "demand: " + demand,this);
        MarketRetrofits_Logger.logging(exstraText + "demandReduction: " + demandReduction,this);

        MarketRetrofits_Logger.logging(exstraText + "demandReductionFromOther: " + demandReductionFromOther,this);
        MarketRetrofits_Logger.logging(exstraText + "dKey: " + dKey,this);
        MarketRetrofits_Logger.logging(exstraText + "hasInstallableItems: " + hasInstallableItems,this);
        MarketRetrofits_Logger.logging(exstraText + "hiddenOverride: " + hiddenOverride,this);
        MarketRetrofits_Logger.logging(exstraText + "id: " + id,this);
        MarketRetrofits_Logger.logging(exstraText + "improved: " + improved,this);

        MarketRetrofits_Logger.logging(exstraText + "income: " + income,this);
        MarketRetrofits_Logger.logging(exstraText + "spec: " + spec,this);
        MarketRetrofits_Logger.logging(exstraText + "special: " + special,this);
        MarketRetrofits_Logger.logging(exstraText + "supply: " + supply,this);
        MarketRetrofits_Logger.logging(exstraText + "supplyBonus: " + supplyBonus,this);
        MarketRetrofits_Logger.logging(exstraText + "supplyBonusFromOther: " + supplyBonusFromOther,this);
        MarketRetrofits_Logger.logging(exstraText + "upgradeId: " + upgradeId,this);
        MarketRetrofits_Logger.logging(exstraText + "upkeep: " + upkeep,this);
        MarketRetrofits_Logger.logging(exstraText + "wasDisrupted: " + wasDisrupted,this);
    }
}
