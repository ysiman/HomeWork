package TradePack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Yanis on 21.09.2017.
 */
public class JsonWorker {
    String userInfo;
    JSONParser parser = new JSONParser();
    ArrayList<Double> price = new  ArrayList<>();
    JSONObject jsonObj;
    JSONArray jsonArray;
    static  long getTime = 0;


    void setJsonObj(String userInf){
        userInfo = userInf;
        Object obj = null;
        try {
            obj = parser.parse(userInfo);
        } catch (ParseException e1) {
            System.out.println("Something wrong!");
            e1.printStackTrace();
        }
        jsonObj = (JSONObject) obj;
        //
    }

    void setArrayName(String arrName){
        System.out.println("arrName = " + arrName );
        jsonArray = (JSONArray) jsonObj.get(arrName);
    }
    void getArrElem(String key) {
        String elemStr;
        Iterator i = jsonArray.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            elemStr = (String) innerObj.get(key);
            System.out.println(key + "= " + elemStr);
        }
    }

    String getElemStr(String key) {
        String elemStr;
        if (jsonArray == null )
        {
            return "null";
        }
        Iterator i = jsonArray.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            elemStr = (String) innerObj.get(key);
           return elemStr;
        }
        return "null";
    }

    String setMainElem(String arrName) {
        jsonObj = (JSONObject) jsonObj.get(arrName);
        //String elem = (String) jsonObj.get(arrName);
       // System.out.println("elem = [" + elem);
       return arrName;
    }
    //NEW
    String getSubElem(String key) {
        //jsonObj = (JSONObject) jsonObj.get(key);
        String elem = (String) jsonObj.get(key);
        System.out.println("elem = " + elem);
        return elem;
    }


}
