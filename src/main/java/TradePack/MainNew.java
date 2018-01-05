package TradePack;

import java.util.HashMap;

/**
 * Created by Yanis on 26.12.2017.
 */
public class MainNew {
    double minQuantity;
    String CURRENCY_1 = "BTC";
    String CURRENCY_2 = "USD";
    String CURRENT_PAIR = CURRENCY_1 + "_" + CURRENCY_2;
    double STOCK_FEE = 0.002d; // Комиссия, которую берет биржа (0.002 = 0.2%)
    double PROFIT_MARKUP = 0.001d;// # Какой навар нужен с каждой сделки? (0.001 = 0.1%)
    double CAN_SPEND = 14.0d;
    double myAmount;
    double avgPrice;
    double avgPriceTest4;
    String userInfoStr;
    JsonWorker jsonWorker;
    Exmo e;
    double myNeedPrice;

    public MainNew() {
        jsonWorker = new JsonWorker();
        e = new Exmo("K-ad422d7a8bbf80d49aee9adda44f8a41b6612b09","S-ffc2ebde3580d2ff2f50812b0db19d8241ddaad6");
        userInfoStr = e.Request("user_info", null);
        String pairInf = Internet.getHttpInfo("https://api.exmo.com/v1/pair_settings/"); // Pair Info
        jsonWorker.setJsonObj(pairInf);
        jsonWorker.setMainElem("BTC_USD");
        String minQuantStr = jsonWorker.getSubElem("min_quantity");
        minQuantity = Double.valueOf(minQuantStr);
    }

    public  void sell() {

        UserInfo userInfo = new UserInfo(userInfoStr);
        if (userInfo.getBalances("BTC") > minQuantity ) {
            myNeedPrice = myNeedPrice + myNeedPrice * (STOCK_FEE+PROFIT_MARKUP);
            e.Request("order_create", new HashMap<String, String>() {
                        {
                            put("pair", CURRENT_PAIR);
                            put("quantity", String.valueOf(minQuantity));
                            put("price", String.valueOf(myNeedPrice));
                            put("type", "sell");
                        }
                    }
            );

        }
    }

    public  void buy()  {

        String tradesInfoStr = e.Request("trades",
                new HashMap<String, String>()
                {
                    {
                        put("pair", CURRENT_PAIR);
                    }
                }
        );
        TradeInfo tradeInfo = new TradeInfo(tradesInfoStr,"BTC_USD");
        System.out.println("waiting for price growing...");
        while (tradeInfo.priceGrow()== false)
        {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e1) {
                System.out.println("Sleep exception");
                e1.printStackTrace();
            }
        }
        System.out.println("Time yo buy");
        double avgRangePrice = tradeInfo.getAvgPriceRangeMin(0,5);
        System.out.println("avgRangePrice ="+avgRangePrice);
        myNeedPrice = avgRangePrice - avgRangePrice * (STOCK_FEE+PROFIT_MARKUP);
        System.out.println("myNeedPrice ="+myNeedPrice);

        System.out.println("minQuantity ="+minQuantity);

        double spendSum = myNeedPrice * minQuantity;
        System.out.println("spendSum = " + spendSum);

        String userInfoStr = e.Request("user_info", null);
        UserInfo userInfo = new UserInfo(userInfoStr);
        if (userInfo.getBalances("USD") >= spendSum ) {
            e.Request("order_create", new HashMap<String, String>() {
                        {
                            put("pair", CURRENT_PAIR);
                            put("quantity", String.valueOf(minQuantity));
                            put("price", String.valueOf(myNeedPrice));
                            put("type", "buy");
                        }
                    }
            );
        }

    }
    public void checkOpenOrders(){
        //информация об открытых ордерах
        String orderType;
        String openedOrders = e.Request("user_open_orders",null);
        if (openedOrders.equals("{}")) {
            System.out.println("No opened orders");
            return;
        }
        else
        {
            int cnt = 0;
            jsonWorker.setJsonObj(openedOrders);
            jsonWorker.setArrayName("BTC_USD");
            orderType = jsonWorker.getElemStr("type");
            if (orderType.equals("sell")) {
                    while (!openedOrders.equals("{}")){
                        try {
                            Thread.sleep(60000);
                        } catch (InterruptedException e1) {
                            System.out.println("Sleep exception");
                            e1.printStackTrace();
                        }
                        openedOrders = e.Request("user_open_orders",null);
                        System.out.println(cnt+": openedOrders="+openedOrders);
                        //Проверка ордеров на продажу
                        if (openedOrders.equals("{}")) {
                            System.out.println("No opened orders");
                            return;
                        }
                        cnt++;
                    }
                String mySellOrderId  = jsonWorker.getElemStr("order_id");
                String closeDeal =  e.Request("order_cancel", new HashMap<String, String>() {{put("order_id", mySellOrderId);}});
            }

        }
    }
    public  void Old() {



        //информация об открытых ордерах
        String openedOrders = e.Request("user_open_orders",null);
        //System.out.println("openedOrders="+openedOrders);

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



        openedOrders ="{\"BTC_USD\": [{\"order_id\": \"14\",\"created\": \"1435517311\",\"type\": \"buy\",\"pair\": \"BTC_USD\",\"price\": \"100\",\"quantity\": \"1\",\"amount\": \"100\"}]}";
        System.out.println("openedOrders="+openedOrders);
        jsonWorker.setJsonObj(openedOrders);
        jsonWorker.setArrayName("BTC_USD");
        System.out.println("jsonWorker.getElemStr = " + jsonWorker.getElemStr("order_id"));


       // System.out.println("tradesInfoStr="+tradesInfoStr);
//------------------JsonWorkerExamle-----
       /* JsonWorker jsonWorker = new JsonWorker();
        jsonWorker.setJsonObj(tradesInfo);
        jsonWorker.setArrayName("BTC_USD");
        jsonWorker.getArrElem("price");*/
        //----------------------------

        //System.out.println('Создан ордер на покупку', new_order['order_id']);
        //myAmount = CAN_SPEND/myNeedPrice;
        //==================Получение информации из ссылки================
        /*URL url = JsonUtils.createUrl("https://api.exmo.com/v1/pair_settings/");
        String exmoInfo = JsonUtils.parseUrl(url);
        System.out.println("exmoInfo = ");
        System.out.println(exmoInfo);*/
        //===========================================================

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
    public static void main(String[] args) {
        MainNew m = new MainNew();
        m.buy();
        m.sell();
    }

}
