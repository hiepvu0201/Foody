package hcmute.edu.vn.group11.foody.activities;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import hcmute.edu.vn.group11.foody.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng dhSPKT = new LatLng(10.8508, 106.7719);
        googleMap.addMarker(new MarkerOptions()
                .position(dhSPKT)
                .title("Marker in DHSPKT"));
    }
}