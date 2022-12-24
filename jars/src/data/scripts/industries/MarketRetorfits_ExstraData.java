package data.scripts.industries;

import java.util.ArrayList;

public class MarketRetorfits_ExstraData {
    /* the porpas of this class is to provide a way to hold random data in another class whenever it is required.*/

    private ArrayList<String> extraDataName = new ArrayList<String>();
    private ArrayList<Object> extraData = new ArrayList<Object>();
    MarketRetorfits_ExstraData(){}

    public void addData(String dataName,Object data){
        for(int a = 0; a < extraDataName.size(); a++){
            if(extraDataName.get(a).equals(dataName)){
                extraData.set(a,data);
                return;
            }
        }
        extraDataName.add(dataName);
        extraData.add(data);
    }
    public Object getData(String dataName){
        for(int a = 0; a < extraDataName.size(); a++){
            if(extraDataName.get(a).equals(dataName)){
                return extraData.get(a);
            }
        }
        return null;
    }

    public boolean getBoolean(String dataName){
        Object out = getData(dataName);
        if(out != null){
            return (boolean) out;
        }
        return false;
    }
    public int getInt(String dataName){
        Object out = getData(dataName);
        if(out != null){
            return (int) out;
        }
        return 0;
    }
    public float getFloat(String dataName){
        Object out = getData(dataName);
        if(out != null){
            return (float) out;
        }
        return 0f;
    }
    public double getDouble(String dataName){
        Object out = getData(dataName);
        if(out != null){
            return (double) out;
        }
        return 0;
    }
    public char getChar(String dataName){
        Object out = getData(dataName);
        if(out != null){
            return (char) out;
        }
        return ' ';
    }
}
