package lessons.vs.petersonapps.converterlab.utils;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.IOException;
import java.util.List;

import lessons.vs.petersonapps.converterlab.view.MapsActivity;

/**
 * Created by vs on 09.06.2017.
 */

public class Utils {

    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

    private double lat = 0d;
    private double lon = 0d;

    private static Utils instance;
    private static Context context;

    private Utils(Context context) {
        this.context = context;
    }

    public static Utils getInstance(Context context){
        if(instance == null){
            instance = new Utils(context);
        }
        return instance;
    }

    public void openMap(String city, String address){

        setCoordinates(city, address);

//        Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + lon);
//        Intent intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        context.startActivity(intent);

        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra(LATITUDE, lat);
        intent.putExtra(LONGITUDE, lon);
        context.startActivity(intent);

    }

    private void setCoordinates(String city, String address) {
        Geocoder geocoder = new Geocoder(context);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(city + ", " + address, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(addresses.size() > 0) {
            lat= addresses.get(0).getLatitude();
            lon= addresses.get(0).getLongitude();
        }
    }


    public boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}