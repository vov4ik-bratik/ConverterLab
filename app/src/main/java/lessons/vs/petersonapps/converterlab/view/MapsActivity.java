package lessons.vs.petersonapps.converterlab.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import lessons.vs.petersonapps.converterlab.R;
import lessons.vs.petersonapps.converterlab.utils.GetCoordinates;
import lessons.vs.petersonapps.converterlab.utils.Utils;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap myMap;
    private Marker marker;

    private String city;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();

        city = intent.getStringExtra(Utils.CITY);
        address = intent.getStringExtra(Utils.ADDRESS);
        initMap();
    }

    private void initMap() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        myMap = googleMap;

        if(Utils.getInstance(getApplicationContext()).isOnline(getApplicationContext())){
            new GetCoordinates(this){

                @Override
                protected void onPostExecute(LatLng latLng) {
                    super.onPostExecute(latLng);
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(latLng)
                            .zoom(17)
                            .build();
                    changeCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    myMap.clear();
                    marker = myMap.addMarker(new MarkerOptions().position(latLng));

                }
            }.execute(city + "+" + address.replace(", ", "+").replace(". ", "+").replace(" ", "+"));
        }
        else {
            Toast.makeText(this, "network is unavailable", Toast.LENGTH_SHORT).show();
        }

    }

    private void changeCamera(CameraUpdate cameraUpdate) {
        changeCamera(cameraUpdate, null);
    }

    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback){
        myMap.animateCamera(update, 2000, callback);
    }

}
