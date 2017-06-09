package lessons.vs.petersonapps.converterlab.view;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import lessons.vs.petersonapps.converterlab.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private GoogleMap myMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void setMapPoint(WeatherData body) {

        LatLng cityCoordinate = new LatLng(body.getCoordinate().getLat(), body.getCoordinate().getLon());

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(cityCoordinate)
                .zoom(10)
                .build();
        changeCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        myMap.clear();
        marker = myMap.addMarker(new MarkerOptions().title(body.getCityName()).position(cityCoordinate));

    }

    private void changeCamera(CameraUpdate cameraUpdate) {
        changeCamera(cameraUpdate, null);
    }

    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback){
        myMap.animateCamera(update, 2000, callback);
    }
}
