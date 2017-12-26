package TradePack;

import TradePack.Exmo;

import java.util.*;



public class Main {
    public static int n = 2;

    public static void main(String[] args) {
        String CURRENCY_1 = "BTC";
        String CURRENCY_2 = "USD";
        String CURRENT_PAIR = CURRENCY_1 + "_" + CURRENCY_2;
        double STOCK_FEE = 0.002d; // Комиссия, которую берет биржа (0.002 = 0.2%)
        double PROFIT_MARKUP = 0.001d;// # Какой навар нужен с каждой сделки? (0.001 = 0.1%)
        double CAN_SPEND = 20.0d;
        double myAmount;
        double minQuantity;
        double avgPrice;
        double avgPriceTest4;
        JsonWorker jsonWorker = new JsonWorker();
        Exmo e = new Exmo("K-ad422d7a8bbf80d49aee9adda44f8a41b6612b09","S-ffc2ebde3580d2ff2f50812b0db19d8241ddaad6");
        /*String result = e.Request("user_info", null);
        System.out.println(result);
        String result2 = e.Request("user_cancelled_orders",
                new HashMap<String, String>()
                {
                    {
                        put("limit", "2");
                        put("offset", "0");
                    }
                }
                );
        System.out.println("result2="+result2);*/

        //информация об открытых ордерах
        String openedOrders = e.Request("user_open_orders",null);
        System.out.println("openedOrders="+openedOrders);

        String buyOrderHist ;
        if (openedOrders.equals("{}"))
            System.out.println("No opened orders");
        else {
            jsonWorker.setJsonObj(openedOrders);
            jsonWorker.setArrayName("BTC_USD");
            String orderTypr = jsonWorker.getElemStr("type");
            //Проверка ордеров на продажу
            if (orderTypr.equals("sell")) {
                System.out.println("Sell oreder exists.Waiting...");
                return;
            }
            else {
                String myBuyOrderId  = jsonWorker.getElemStr("order_id");
                buyOrderHist = e.Request("order_trades",new HashMap<String, String>() {{put("order_id", myBuyOrderId);}});
                if (buyOrderHist.equals("{}")) {
                    System.out.println("Выход, продолжаем надеяться докупить валюту по тому курсу, по которому уже купили часть");
                    return;
                }
                else
                    System.out.println("Частично исполненных ордеров нет");

            }
        }
        //Есть ли CURRENCY_1 для продажи
        String userInfo = e.Request("user_info", null);System.out.println("userInfo = [" + userInfo);
        jsonWorker.setJsonObj(userInfo);
        jsonWorker.setArrayName("BTC_USD");
        String orderTypr = jsonWorker.getElemStr("type");

        openedOrders ="{\"BTC_USD\": [{\"order_id\": \"14\",\"created\": \"1435517311\",\"type\": \"buy\",\"pair\": \"BTC_USD\",\"price\": \"100\",\"quantity\": \"1\",\"amount\": \"100\"}]}";
        System.out.println("openedOrders="+openedOrders);
        jsonWorker.setJsonObj(openedOrders);
        jsonWorker.setArrayName("BTC_USD");
        System.out.println("jsonWorker.getElemStr = " + jsonWorker.getElemStr("order_id"));
        String tradesInfoStr = e.Request("trades", new HashMap<String, String>() {{put("pair", CURRENT_PAIR);}});
        TradeInfo tradeInfo = new TradeInfo(tradesInfoStr,"BTC_USD");
        //System.out.println("tradesInfo="+tradesInfo);
//------------------JsonWorkerExamle-----
       /* JsonWorker jsonWorker = new JsonWorker();
        jsonWorker.setJsonObj(tradesInfo);
        jsonWorker.setArrayName("BTC_USD");
        jsonWorker.getArrElem("price");*/
        //----------------------------


        tradeInfo.priceGrow();

        double avgRangePrice = tradeInfo.getAvgPriceRangeMin(0,5);
        System.out.println("avgRangePrice ="+avgRangePrice);
        double myNeedPrice = avgRangePrice - avgRangePrice * (STOCK_FEE+PROFIT_MARKUP);
        System.out.println("myNeedPrice ="+myNeedPrice);
        myAmount = CAN_SPEND/myNeedPrice;
        System.out.println("myAmount ="+myAmount);

        String pairInf = Internet.getHttpInfo("https://api.exmo.com/v1/pair_settings/"); // Pair Info
        jsonWorker.setJsonObj(pairInf);
        jsonWorker.setMainElem("BTC_USD");
        String minQuantStr = jsonWorker.getSubElem("min_quantity");
        minQuantity = Double.valueOf(minQuantStr);
        System.out.println("minQuantity ="+minQuantity);

        if (myAmount > minQuantity)
            System.out.println("Go!go!go!");
        //myAmount = CAN_SPEND/myNeedPrice;
        //==================Получение информации из ссылки================
        /*URL url = JsonUtils.createUrl("https://api.exmo.com/v1/pair_settings/");
        String exmoInfo = JsonUtils.parseUrl(url);
        System.out.println("exmoInfo = ");
        System.out.println(exmoInfo);*/
        //===========================================================

        //===========JSON
       /* Long time_passed;
        ArrayList<Double> price0 = new  ArrayList<>();
        Double avg_price0;
        Double sum0;
        JSONParser parser = new JSONParser();

        Object obj = null;
        try {
            obj = parser.parse(result3);
            System.out.println("OK!");
        } catch (ParseException e1) {
            System.out.println("Something wrong!");
            e1.printStackTrace();
        }
        JSONObject jsonObj = (JSONObject) obj;
        //System.out.println(jsonObj.get("BTC_USD"));
        //System.out.println(jsonObj.get("BTC_USD"));
        JSONArray jsonArray = (JSONArray) jsonObj.get("BTC_USD");
        //System.out.println(jo.get(1));
        Iterator i = jsonArray.iterator();
        while (i.hasNext()) {
            JSONObject innerObj = (JSONObject) i.next();
            //System.out.println("trade_id "+ innerObj.get("price"));
            Date d = new Date() ;
            time_passed =  (d.getTime()/1000 - (Long) innerObj.get("date") )/60;
            if (time_passed == 0){
                String price= (String)innerObj.get("price");
                System.out.println("price = " + price);
               // double pric = Double.valueOf(price);
              //  System.out.println("---pric = " + pric);
                price0.add(Double.valueOf((String)innerObj.get("price")));
            }
            //System.out.println("time_passed =" + time_passed);
            //if time_passed < AVG_PRICE_PERIOD*60:
        }
        sum0 = 0d;
        System.out.println("price0.size() = " +price0.size());
        for ( Double s : price0)
            sum0 = sum0 + s;
        avg_price0 = sum0/price0.size();
        System.out.println("avg_price0 = " + avg_price0);*/

       /*
        Date d = new Date() ;
        Date today = new Date(); System.out.println( "Дата: " + today );
        System.out.println( "today.getTime()= " +today.getTime() );
        Long l =  (d.getTime()/1000 - 1502639479L)/60;// (24 * 60 * 60 * 1000);
        System.out.println("l =" + l);
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("День dd Месяц MM Год yyyy Время hh:mm");
        System.out.println(format1.format(d)); //25.02.2013 09:03
        System.out.println(format2.format(d)); //День 25 Месяц 02 Год 2013 Время 09:03
*/

    }


}
