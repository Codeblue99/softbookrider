package com.example.softbookrider;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.example.softbookrider.databinding.ActivityBookStoreLocationBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.softbookrider.databinding.ActivityMyLocationBinding;

import java.util.ArrayList;

public class MyLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
private ActivityMyLocationBinding binding;

    String latitude,longitude,storename;
    String latitude2,longitude2,storename2;
    private ArrayList<LatLng> locationArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityMyLocationBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
        final Bundle extras = getIntent().getExtras();
        latitude=extras.getString("latitude");
        longitude=extras.getString("longitude");




        LatLng sydney = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
        mMap.addMarker(new MarkerOptions().position(sydney).title("My Location"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,11),5000,null);
    }
}