package TradePack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.URL;
import java.util.Date;
import java.util.Iterator;

public class PairSettings {
    double minQuantity;


    public PairSettings() {

    }
   public static double getMinQuantity(String pairStr){

       JSONObject jsonObj;
       JSONArray jsonArray;
       JSONParser parser = new JSONParser();
       Object obj = null;
       String linkStr = "https://api.exmo.com/v1/pair_settings/";
       URL url = JsonUtils.createUrl(linkStr);
       String pairInfo = JsonUtils.parseUrl(url);
       double minQuantity;
       try {
           obj = parser.parse(pairInfo);
       } catch (ParseException e1) {
           System.out.println("Something wrong!");
           e1.printStackTrace();
       }
       jsonObj = (JSONObject) obj;
       System.out.println("pairStr_Work = " + pairStr );
       jsonObj = (JSONObject) jsonObj.get(pairStr);
       String mq = (String) jsonObj.get("min_quantity");
      // System.out.println("mq="+mq);
       minQuantity = Double.valueOf(mq); //1.0d; //(double)jsonObj.get("min_quantity");
       return minQuantity ;
   }
    public static void getMinQuantity2(String pairStr){

        JSONObject jsonObj;
        JSONArray jsonArray;
        JSONParser parser = new JSONParser();
        Object obj = null;
        String linkStr = "https://api.exmo.com/v1/pair_settings/";
        URL url = JsonUtils.createUrl(linkStr);
        String pairInfo = JsonUtils.parseUrl(url);

        JsonWorker jsonWorker = new JsonWorker();
       // System.out.println("pairInfo = " + pairInfo);
        jsonWorker.setJsonObj(pairInfo);
        System.out.println("pairStr = " + pairStr);
         jsonWorker.setArrElem(pairStr);
        String m = jsonWorker.getElem("min_quantity");
        System.out.println("m = " + m);
        /*double minQuantity;
        try {
            obj = parser.parse(pairInfo);
        } catch (ParseException e1) {
            System.out.println("Something wrong!");
            e1.printStackTrace();
        }
        jsonObj = (JSONObject) obj;
        jsonObj = (JSONObject) jsonObj.get(pairStr);
        String mq = (String) jsonObj.get("min_quantity");
        // System.out.println("mq="+mq);
        minQuantity = Double.valueOf(mq); //1.0d; //(double)jsonObj.get("min_quantity");
        return minQuantity ;*/
    }

}
