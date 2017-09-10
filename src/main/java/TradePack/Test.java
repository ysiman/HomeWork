package TradePack;

import java.util.HashMap;

/**
 * Created by HOME on 08.08.2017.
 */
public class Test {
    public static void main(String[] args) {
        int testI;
        HashMap hm =  new HashMap<String, String>()
        {
            {
                put("pai", "2");
                put("offset", "0");
            }
        };
        System.out.println("hm[limit] = " + hm.get("limit"));
        testI = 2;
    }
}
