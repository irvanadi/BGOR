package com.example.xeylyne.project.Activitys;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xeylyne.project.Adapter.GORAdapter_user;
import com.example.xeylyne.project.Api.ApiPackage;
import com.example.xeylyne.project.ListData.ListGOR_user;
import com.example.xeylyne.project.R;
import com.example.xeylyne.project.ResultData.ResultGOR_user;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsBasketActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private  GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location locationLast;
    private Marker marker;
    public static final  int PERMISSION_REQUEST = 99;
    private EditText editText ;
    private Button button, button2;

    String[] mahasiswa;

    private List<ResultGOR_user> results = new ArrayList<>();
    private GORAdapter_user gorAdapter;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://irvanlyne.000webhostapp.com/")
            .addConverterFactory(GsonConverterFactory.create());

    Retrofit retrofit = builder.build();

    ApiPackage apiPackage= retrofit.create(ApiPackage.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkLocationPermission();
        }


        button2 = findViewById(R.id.B_search);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loaddata();


                button2.setVisibility(View.GONE);



            }
        });
    }

    public void loaddata(){

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading ...");
        dialog.setCancelable(false);
        dialog.show();


        Call<ListGOR_user> call = apiPackage.gor_user("2");

        call.enqueue(new Callback<ListGOR_user>() {
            @Override
            public void onResponse(Call<ListGOR_user> call, Response<ListGOR_user> response) {

                if (response.isSuccessful()) {
                    Log.d("asd","acc");
                    dialog.hide();
                    results = response.body().getResult();
                    for(int i = 0; i < results.size(); i++){

                        List<Address> addressList = null;

                        MarkerOptions markerOptions = new MarkerOptions();


                        if (true){
                            Geocoder geocoder = new Geocoder(MapsBasketActivity.this);

                            try {
                                addressList = geocoder.getFromLocationName(results.get(i).getAlamatGor(), 1);
                                Log.d("ASD",results.get(i).getAlamatGor());

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            for (int j = 0; j < addressList.size();j++){
                                Address address = addressList.get(j);
                                LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
                                markerOptions.position(latLng);
                                markerOptions.title(results.get(i).getNamaGor());
                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                mMap.addMarker(markerOptions);
                                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                            }

                        }

                    }

                } else {
                    dialog.hide();
                    Log.d("ASD","gagal respon");
                }
            }
            @Override
            public void onFailure(Call<ListGOR_user> call, Throwable t) {
                Log.d("ASD","gagal");
                dialog.hide();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_REQUEST:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                        if (googleApiClient == null){
                            buildGoolgeApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(MapsBasketActivity.this,"Permission Denait",Toast.LENGTH_SHORT).show();
                }
                return;
        }
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

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            buildGoolgeApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoolgeApiClient(){

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        locationLast = location;

        if (marker!=null){
            marker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        // LatLng latLng = new LatLng(-34, 151);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Choose Location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        // markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo));

        marker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        if (googleApiClient!=null){
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }


    }

    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST);

            }

            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();

        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
