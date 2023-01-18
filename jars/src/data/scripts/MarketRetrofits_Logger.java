package data.scripts;

import com.fs.starfarer.api.Global;
import org.apache.log4j.Logger;

public class MarketRetrofits_Logger {
    static boolean logging = true;
    public static void logging(String output,Object displayClass) {
        logging(output,displayClass,logging);
    }

    public static void logging(String output,Object displayClass,boolean override) {
        if(!override){return;}
        String add = "";
        final Logger LOG = Global.getLogger(displayClass.getClass());
        LOG.info(add + output);
    }
}
