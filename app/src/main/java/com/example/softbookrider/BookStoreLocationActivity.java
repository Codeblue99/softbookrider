package com.example.softbookrider;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.softbookrider.databinding.ActivityBookStoreLocationBinding;

import java.util.ArrayList;

public class BookStoreLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityBookStoreLocationBinding binding;
    String latitude,longitude,storename;
    String latitude2,longitude2,storename2;
    private ArrayList<LatLng> locationArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBookStoreLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final Bundle extras = getIntent().getExtras();
        latitude=extras.getString("latitude");
        longitude=extras.getString("longitude");
        storename=extras.getString("store");

        latitude2=extras.getString("latitude2");
        longitude2=extras.getString("longitude2");
        storename2=extras.getString("store2");

        LatLng sydney = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
        LatLng TamWorth = new LatLng(Double.valueOf(latitude2), Double.valueOf(longitude2));

        //Toast.makeText(this, ""+latitude, Toast.LENGTH_SHORT).show();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationArrayList = new ArrayList<>();

        // on below line we are adding our
        // locations in our array list.
        locationArrayList.add(sydney);
        locationArrayList.add(TamWorth);
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
        LatLng TamWorth = new LatLng(Double.valueOf(latitude2), Double.valueOf(longitude2));
        mMap.addMarker(new MarkerOptions().position(sydney).title(storename));
        mMap.addMarker(new MarkerOptions().position(TamWorth).title(storename2));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,11),5000,null);

        // below line is use to move our camera to the specific location.
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));


//        for (int i = 0; i < locationArrayList.size(); i++) {
//
//            // below line is use to add marker to each location of our array list.
//            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));
//
//            // below lin is use to zoom our camera on map.
//            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,18),5000,null);
//
//            // below line is use to move our camera to the specific location.
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
//        }
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(Double.valueOf(latitude),Double.valueOf(longitude));
//        mMap.addMarker(new MarkerOptions().position(sydney).title(storename));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18), 5000, null);
//
//        LatLng sydney2 = new LatLng(Double.valueOf(latitude2),Double.valueOf(longitude2));
//        mMap.addMarker(new MarkerOptions().position(sydney).title(storename2));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney2, 18), 5000, null);

//        mMap.animateCamera( CameraUpdateFactory.zoomTo( 12.0f ) );
    }
}