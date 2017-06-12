package lessons.vs.petersonapps.converterlab.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vs on 12.06.2017.
 */

public class GetCoordinates extends AsyncTask<String, Void, LatLng> {

    ProgressDialog dialog;

    public GetCoordinates(Context context) {
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.setMessage("Please wait....");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected LatLng doInBackground(String... strings) {
        String response;
        try {
            String address = strings[0];
            HttpDataHandler http = new HttpDataHandler();
            String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s", address);
            response = http.getHTTPData(url);
            if (!response.contains("ZERO_RESULTS")){
                return parseJson(response);
            }
            else{
                url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s", strings[1]);
                response = http.getHTTPData(url);
                return parseJson(response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new LatLng(0, 0);
    }

    private LatLng parseJson(String s) {
        double lat;
        double lon;
        try {
            JSONObject jsonObject = new JSONObject(s);

            String latitude = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location").get("lat").toString();
            String longitude = ((JSONArray) jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                    .getJSONObject("location").get("lng").toString();

            lat = Double.parseDouble(latitude);
            lon = Double.parseDouble(longitude);

            if (dialog.isShowing())
                dialog.dismiss();

            return new LatLng(lat, lon);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new LatLng(0, 0);
    }

}
