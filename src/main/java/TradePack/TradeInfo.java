package TradePack;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

class TradeInfo{
    String tradesInfoStr;
    JSONParser parser = new JSONParser();
    ArrayList<Double> price = new  ArrayList<>();
    JSONObject jsonObj;
    JSONArray jsonArray;
    static  long getTime = 0;

    TradeInfo(String tradesInfoStr,String pair){
        tradesInfoStr = tradesInfoStr;
        Object obj = null;
        try {
            obj = parser.parse(tradesInfoStr);
        } catch (ParseException e1) {
            System.out.println("Something wrong!");
            e1.printStackTrace();
        }
        jsonObj = (JSONObject) obj;
        jsonArray = (JSONArray) jsonObj.get(pair);
    }

    Double getAvgPriceForMinute( int timeInMinute){
        price.clear();
        Iterator i = jsonArray.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Date d = new Date() ;
            Long time_passed =  (d.getTime()/1000 - (Long) innerObj.get("date") )/60;
            if (time_passed == timeInMinute){
                String priceStr= (String)innerObj.get("price");
                price.add(Double.valueOf((String)innerObj.get("price")));
            }
        }
        Double sum = 0d;
        for ( Double s : price)
            sum = sum + s;

        Double avg_price;
        avg_price = sum /price.size();
        return avg_price;

    }
    Double getAvgPriceRangeMin( int BeginMinute, int EndMinute){
        price.clear();
        Iterator i = jsonArray.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            Date d = new Date() ;
            Long time_passed =  (d.getTime()/1000 - (Long) innerObj.get("date") )/60;
            if ((time_passed >= BeginMinute) && (time_passed <= EndMinute)) {
                String priceStr= (String)innerObj.get("price");
                // System.out.println("price = " + priceStr);
                // double pric = Double.valueOf(price);
                //  System.out.println("---pric = " + pric);
                price.add(Double.valueOf((String)innerObj.get("price")));
            }
        }
        Double sum = 0d;
        for ( Double s : price)
            sum = sum + s;

        Double avg_price;
        avg_price = sum /price.size();
        return avg_price;

    }

    public boolean priceGrow(){
        Double sum = 0d;
        boolean grow = true;
        for(int i=0;i<3; i++)
        {
            if ((sum == 0) || (sum.isNaN()))
                sum = getAvgPriceForMinute(i);
            else
                System.out.println("sum = " + sum + "  tradeInfo.getAvgPriceForMinute("+i+")="+getAvgPriceForMinute(i));
            if (sum < getAvgPriceForMinute(i))
                grow = false;
            //System.out.println("price"+i+"="+tradeInfo.getAvgPriceForMinute(i));
        }
        System.out.println("high ="+grow);
        return grow;
    }

}
