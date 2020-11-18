package com.ali.ssbdeliveryboy.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ali.ssbdeliveryboy.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.lang.reflect.Array;

public class MapsFragment extends Fragment implements OnMapReadyCallback{

    FusedLocationProviderClient fusedLocationProviderClient;
    String lati,longi,name;
    TextView namee;
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_maps, container, false);

        namee=view.findViewById(R.id.name);


        lati=getArguments().getString("lati");
        longi=getArguments().getString("longi");
        name=getArguments().getString("name");

        namee.setText("Customer: "+name);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(MapsFragment.this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng=new LatLng(Double.valueOf(lati),Double.valueOf(longi));

        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latLng);
        googleMap.addMarker(markerOptions);

        googleMap.isMyLocationEnabled();

        Toast.makeText(getContext(), lati, Toast.LENGTH_SHORT).show();
        LatLng coordinate = new LatLng(Double.parseDouble(lati),Double.parseDouble(longi)); //Store these lat lng values somewhere. These should be constant.
        CameraUpdate locations = CameraUpdateFactory.newLatLngZoom(
                coordinate, 15);
        googleMap.animateCamera(locations);




    }
}