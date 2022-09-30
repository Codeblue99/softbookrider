package com.example.softbookrider;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView mname,memail,mphone;
    LinearLayout orderrequest,pendingorder,updateprofile,logout,id6000;
    LocationManager locationManager;
    String latitude,longitude;
    private static  final int REQUEST_LOCATION=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mname=findViewById(R.id.mname);
        memail=findViewById(R.id.memail);
        mphone=findViewById(R.id.mdesignation);
        orderrequest=findViewById(R.id.id3000);
        pendingorder=findViewById(R.id.id3002);
        updateprofile=findViewById(R.id.id4000);
        logout=findViewById(R.id.id5000);
        id6000=findViewById(R.id.id6000);
        final SharedPreferences shared=getSharedPreferences("credientials",MODE_PRIVATE);
        final SharedPreferences.Editor edit=shared.edit();
        ActivityCompat.requestPermissions(this,new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);


        mname.setText(shared.getString("rider_name",""));
        memail.setText(shared.getString("rider_email",""));
        mphone.setText(shared.getString("rider_phone",""));

        locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Check gps is enable or not

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            //Write Function To enable gps

            OnGPS();
        }
        else
        {
            //GPS is already On then

            getLocation();
        }

        updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,UpdateProfileActivity.class);
                startActivity(intent);
            }
        });

        orderrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,OrderRequestActivity.class);
                startActivity(intent);
            }
        });

        pendingorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,PendingOrderActivity.class);
                startActivity(intent);
            }
        });
        id6000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MyLocationActivity.class);
                intent.putExtra("latitude",latitude);
                intent.putExtra("longitude",longitude);

                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.putString("rider_name","");
                edit.putString("rider_cnic","");
                edit.putString("rider_email","");
                edit.putString("rider_pass","");
                edit.putString("rider_phone","");


                edit.commit();
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
    private void getLocation() {

        //Check Permissions again

        if (ActivityCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this,

                Manifest.permission.ACCESS_COARSE_LOCATION) !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
        else
        {
            Location LocationGps= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (LocationGps !=null)
            {
                double lat=LocationGps.getLatitude();
                double longi=LocationGps.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);

               // Toast.makeText(this, "Latitude :" + latitude + "Longitude : " + longitude, Toast.LENGTH_SHORT).show();

            }
            else if (LocationNetwork !=null)
            {
                double lat=LocationNetwork.getLatitude();
                double longi=LocationNetwork.getLongitude();

                latitude=String.valueOf(lat);
                longitude=String.valueOf(longi);
               // Toast.makeText(this, "Latitude :" + latitude + "Longitude : " + longitude, Toast.LENGTH_SHORT).show();


            }
//            else if (LocationPassive !=null)
//            {
//                double lat=LocationPassive.getLatitude();
//                double longi=LocationPassive.getLongitude();
//
//                latitude=String.valueOf(lat);
//                longitude=String.valueOf(longi);
//
//                s_latitude.setText(latitude);
//                s_longitude.setText(longitude);
//
//            }
            else
            {
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }

            //Thats All Run Your App
        }

    }

    private void OnGPS() {

        final AlertDialog.Builder builder= new AlertDialog.Builder(this);

        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}