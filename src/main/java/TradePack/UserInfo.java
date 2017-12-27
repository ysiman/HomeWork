package TradePack;

/**
 * Created by Yanis on 27.12.2017.
 */
public class UserInfo {
    String userInfo;
    JsonWorker jsonWorker = new JsonWorker();
    public UserInfo(String userInfo) {
        this.userInfo = userInfo;
        jsonWorker.setJsonObj(userInfo);
    }

    double getBalances(String val){
        jsonWorker.setMainElem("balances");
        String balancStr = jsonWorker.getSubElem(val);
        Double balance = Double.valueOf(balancStr);
        return balance;
    }
}
