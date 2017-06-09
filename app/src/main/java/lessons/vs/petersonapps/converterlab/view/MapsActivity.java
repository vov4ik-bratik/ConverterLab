package lessons.vs.petersonapps.converterlab.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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
import lessons.vs.petersonapps.converterlab.utils.Utils;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap myMap;
    private Marker marker;

    private double lat = 0d;
    private double lon = 0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();

        lat = intent.getDoubleExtra(Utils.LATITUDE, 0);
        lon = intent.getDoubleExtra(Utils.LONGITUDE, 0);
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
        LatLng bankPosition = new LatLng(lat, lon);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(bankPosition)
                .zoom(10)
                .build();
        changeCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        myMap.clear();
        marker = myMap.addMarker(new MarkerOptions().position(bankPosition));

    }

    private void changeCamera(CameraUpdate cameraUpdate) {
        changeCamera(cameraUpdate, null);
    }

    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback){
        myMap.animateCamera(update, 2000, callback);
    }

}
