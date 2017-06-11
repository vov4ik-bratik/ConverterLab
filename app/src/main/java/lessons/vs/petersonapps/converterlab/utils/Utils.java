package lessons.vs.petersonapps.converterlab.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import lessons.vs.petersonapps.converterlab.view.MapsActivity;

/**
 * Created by vs on 09.06.2017.
 */

public class Utils {

    public static final String CITY = "city";
    public static final String ADDRESS = "address";

    private double lat = 0d;
    private double lon = 0d;

    private static Utils instance;
    private static Context context;

    private Utils(Context context) {
        this.context = context;
    }

    public static Utils getInstance(Context context) {
        if (instance == null) {
            instance = new Utils(context);
        }
        return instance;
    }

    public void openMap(String city, String address) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(CITY, city);
        intent.putExtra(ADDRESS, address);
        context.startActivity(intent);

    }

    public void makeCall(String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void openBrowser(String _link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(_link));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
