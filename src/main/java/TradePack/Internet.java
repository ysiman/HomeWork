package TradePack;

import java.net.URL;

/**
 * Created by Yanis on 26.12.2017.
 */
public class Internet {
    public static String getHttpInfo(String linkStr){
        URL url = JsonUtils.createUrl(linkStr);
        String httpInf = JsonUtils.parseUrl(url);
        return httpInf;
    }
}
