package com.example.mobapplicationdev;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import com.google.android.gms.maps.SupportMapFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.content.*;
import android.net.Uri;
import android.widget.TextView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static android.content.Intent.ACTION_VIEW;

public class DashboardFragment extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onMapReady: Map is ready");
        mMap = googleMap;

        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }

    }

    private static final String TAG = "DashboardFragment";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;

    private static final float DEFAULT_ZOOM = 15f;
    private static final int LOCATION_REQUEST_PERMISSION_CODE = 1234;

    private Boolean mLocationPermissionGranted = false;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dashboard);
        getLocationPermission();
        TextView tv1 = findViewById(R.id.item1);
        TextView tv2 = findViewById(R.id.item2);
        TextView tv3 = findViewById(R.id.item3);
        TextView tv4 = findViewById(R.id.item4);
        TextView tv5 = findViewById(R.id.item5);
        TextView tv6 = findViewById(R.id.item6);
        TextView tv7 = findViewById(R.id.item7);
        TextView tv8 = findViewById(R.id.item8);
        TextView tv9 = findViewById(R.id.item9);

        tv1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse("https://naturopathicfamilyhealth.com"));
                            startActivity(browserIntent);

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
        tv2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse("https://www.saraleungrd.com"));
                            startActivity(browserIntent);
                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
        tv3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse("http://resultsfoodcoaching.com"));
                            startActivity(browserIntent);
                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
        tv4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse("http://www.bayareanutrition.com"));
                            startActivity(browserIntent);

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
        tv5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse("http://www.naturopathicfamilyhealth.com"));
                            startActivity(browserIntent);

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });

        tv6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse("https://www.naniglass.com"));
                            startActivity(browserIntent);

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });

        tv7.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse(""));
                            startActivity(browserIntent);

                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
        tv8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse("https://mor-nutrition4life.com"));
                            startActivity(browserIntent);
                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
        tv9.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        try {
                            Intent browserIntent = new Intent(ACTION_VIEW, Uri.parse(""));
                            startActivity(browserIntent);
                        }
                        catch (Exception e)
                        {

                        }
                    }
                });



    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_dashboard);
//
//    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Log.d(TAG, "initMap: initializing map");
        mapFragment.getMapAsync(DashboardFragment.this);


        if (mLocationPermissionGranted) {
            getDeviceLocation();
        }
    }


    private void getDeviceLocation(){
        Log.d(TAG,"getDeviceLocation: getting current device location ");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(mLocationPermissionGranted){
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"onComplete: location found!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()), DEFAULT_ZOOM);
                        }
                        else {
                            Log.d(TAG, "onComplete: location not found! ");
                            //Toast.makeText(DashboardFragment.this,"Unable to find current location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        }catch (SecurityException e){
            Log.d(TAG,"getDeviceLocation: SecurityException" +e.getMessage() );
        }

    }


    private void moveCamera(LatLng latLng,float zoom){
        Log.d(TAG,"moveCamera: moving the camera to current device location lat: " +latLng.latitude+ "lon: " +latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }


    private void getLocationPermission(){

        Log.d(TAG,"getLocationPermission: getting location permission");
        String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true;
                initMap();
            }
            else{
                ActivityCompat.requestPermissions(this,permissions, LOCATION_REQUEST_PERMISSION_CODE);
            }
        }
        else{
            ActivityCompat.requestPermissions(this,permissions, LOCATION_REQUEST_PERMISSION_CODE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        Log.d(TAG,"onRequestPermissionsResult: requesting permission results");
        mLocationPermissionGranted = false;

        switch (requestCode){
            case LOCATION_REQUEST_PERMISSION_CODE: {
                if(grantResults.length > 0 )
                {
                    for(int i = 0;i<grantResults.length;i++)
                    {
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED ) {
                            Log.d(TAG,"onRequestPermissionsResult: permission failed");
                            return;}
                    }}
                Log.d(TAG,"onRequestPermissionsResult: permission granted ");
                mLocationPermissionGranted = true;
                initMap();
            }
        }

    }


}
