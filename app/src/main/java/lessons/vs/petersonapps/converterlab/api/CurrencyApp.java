package lessons.vs.petersonapps.converterlab.api;

import android.app.Application;

/**
 * Created by vs on 25.05.2017.
 */

public class CurrencyApp extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
        restClient = new ServiceGenerator();
    }

    public static RestClient getRestClient(){
        return restClient;
    }
}
