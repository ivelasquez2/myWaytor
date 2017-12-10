package com.example.lap.mywaytor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationActivity extends FragmentActivity implements OnMyLocationButtonClickListener,OnMapReadyCallback {
    public Button bRetry, bYes, bCycle;
    public int locationCycle = 0;
    private Marker marker;
    private LocationManager locationManager;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    public FusedLocationProviderClient mFusedLocationClient;
    private Location location;
    public LatLng spot, LatLng;
    public boolean clicked;
    private Criteria criteria;
    public double lat, lng;
    public String provider;
    boolean check = false;
    public LocationCallback mLocationCallback;
    public LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);//Creates map support wrapper
        bRetry = findViewById(R.id.bRetry);//button assignments
        bYes = findViewById(R.id.bYes);
        bCycle = findViewById(R.id.bCycle);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();//Map setting to strict to accept all inputs
        StrictMode.setThreadPolicy(policy);
    }

    public void buttons() {
        bCycle.setOnClickListener(new View.OnClickListener() {//Changes location through our 3 participating locations and moves camera position to that spot
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                CameraPosition CameraPosition;
                locationCycle +=1;
                if(locationCycle == 4){
                    locationCycle = 1;
                }
                if (locationCycle == 1) {//Thai Star
                    lat = 37.315616;
                    lng = -120.4706;
                    location.setLatitude(lat);
                    location.setLongitude(lng);
                    LatLng = new LatLng(lat, lng);
                    spot = new LatLng(lat, lng);
                    Toast.makeText(getApplicationContext(), "1Cycle Clicked!", Toast.LENGTH_SHORT).show();
                    CameraPosition = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(spot, 0);
                    CameraUpdateFactory.newCameraPosition(CameraPosition);
                    clicked = true;
                }
                if (locationCycle == 2) {//Snow-Crave
                    lat = 37.318783;
                    lng = -120.4986;
                    location.setLatitude(lat);
                    location.setLongitude(lng);
                    LatLng = new LatLng(lat, lng);
                    spot = new LatLng(lat, lng);
                    Toast.makeText(getApplicationContext(), "2Cycle Clicked!", Toast.LENGTH_SHORT).show();
                    CameraPosition = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(spot, 0);
                    CameraUpdateFactory.newCameraPosition(CameraPosition);
                    clicked = true;
                }
                if (locationCycle == 3) {//Em-Tea
                    lat = 37.331419;
                    lng = -120.4667;
                    location.setLatitude(lat);
                    location.setLongitude(lng);
                    LatLng = new LatLng(lat, lng);
                    spot = new LatLng(lat, lng);
                    Toast.makeText(getApplicationContext(), "3Cycle Clicked!", Toast.LENGTH_SHORT).show();
                    CameraPosition = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(spot, 0);
                    CameraUpdateFactory.newCameraPosition(CameraPosition);
                    clicked = true;
                }

            }
        });
        bRetry.setOnClickListener(new View.OnClickListener() {//Rechecks the useer location, can be used if location gets stuck, helps user regain the check location in case of any UI bug
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                locationCycle = 0;
                check = false;
                Toast.makeText(getApplicationContext(), "Location Moved Click Target!", Toast.LENGTH_SHORT).show();
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (locationManager == null) {
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                }
                provider = locationManager.getBestProvider(criteria, false);
                location = locationManager.getLastKnownLocation(provider);
                if (provider == null || location == null) {
                    provider = locationManager.getBestProvider(criteria, false);
                    location = locationManager.getLastKnownLocation(provider);
                }
                if (location != null) {
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                }
                spot = new LatLng(lat, lng);
                locationCycle = 0;
        }
        });
        bYes.setOnClickListener(new View.OnClickListener() {//Verifies location across our 3 participating places, if at none page navigation is denied
            @Override
            public void onClick(View v) {
                // insert checks
                if ((lat > 37.31531) && (lat < 37.31566) && (lng < -120.4705) && (lng > -120.4708)) {
                    //at ThaiStar
                    Toast.makeText(getApplicationContext(), "Chose Thai Star!", Toast.LENGTH_SHORT).show();
                    check = true;
                    //add toast and StartActivity variable
                }
                if ((lat > 37.3308) && (lat < 37.3316) && (lng > -120.4986) && (lng < -120.4662)) {
                    //at Em-tea
                    //add toast and StartActivity variable
                    Toast.makeText(getApplicationContext(), "Chose Em-tea!", Toast.LENGTH_SHORT).show();
                    check = true;
                }
                if ((lat > 37.3185) && (lat < 37.3189) && (lng < -120.4982) && (lng > -120.4989)) {
                    //at Sno-Crave Tea House
                    //add toast and StartActivity variable
                    Toast.makeText(getApplicationContext(), "Chose Sno-Crave!", Toast.LENGTH_SHORT).show();
                    check = true;
                }
                if (check) {
                    startActivity(new Intent(LocationActivity.this, DetailedMenuActivity.class));
                } else if (!check) {
                    Toast.makeText(getApplicationContext(), "Not at a participating location!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onMapReady(GoogleMap googleMap) {//Updates map based on our locationCycle(Only needed for presenting, full app would just check and send button is used only to show utility) all map update calls must be done here to avoid null reception and crashes.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Location Access Required!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LocationActivity.this, LocationCheckActivity.class));
        } else {
            CameraPosition CameraPosition;
            if (locationCycle == 1 && clicked) {
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                mLocationCallback = new LocationCallback();
                mLocationRequest = new LocationRequest();
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMyLocationButtonClickListener(this);
                criteria = new Criteria();
                CameraPosition = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(spot, 0);
                CameraUpdateFactory.newCameraPosition(CameraPosition);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spot, 18));
                marker.setPosition(spot);
            }
            if (locationCycle == 2 && clicked) {
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                mLocationCallback = new LocationCallback();
                mLocationRequest = new LocationRequest();
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMyLocationButtonClickListener(this);
                criteria = new Criteria();
                CameraPosition = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(spot, 0);
                CameraUpdateFactory.newCameraPosition(CameraPosition);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spot, 18));
                marker.setPosition(spot);
            }
            if (locationCycle == 3 && clicked) {
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                mLocationCallback = new LocationCallback();
                mLocationRequest = new LocationRequest();
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMyLocationButtonClickListener(this);
                criteria = new Criteria();
                CameraPosition = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(spot, 0);
                CameraUpdateFactory.newCameraPosition(CameraPosition);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spot, 18));
                marker.setPosition(spot);
            }if(locationCycle == 0){
                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                mapFragment.getMapAsync(this);
                mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                mLocationCallback = new LocationCallback();
                mLocationRequest = new LocationRequest();
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMyLocationButtonClickListener(this);
                criteria = new Criteria();
                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
                if (locationManager == null) {
                    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                }
                provider = locationManager.getBestProvider(criteria, true);//was false
                location = locationManager.getLastKnownLocation(provider);
                if (provider == null || location == null) {
                    provider = locationManager.getBestProvider(criteria, true);
                    location = locationManager.getLastKnownLocation(provider);
                }
                if (location != null) {
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                }
                spot = new LatLng(lat, lng);
                if ( marker == null) {
                    marker = googleMap.addMarker(new MarkerOptions().position(spot).title("Are you here?"));
                }
                if (marker != null){
                    marker.setPosition(spot);
                }
                CameraPosition = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(spot, 0);
                CameraUpdateFactory.newCameraPosition(CameraPosition);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spot, 18));
                buttons();
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {//If map action is clicked map is also updated so a toast is here to show this
        Toast.makeText(this, "Camera Updated", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }
}
