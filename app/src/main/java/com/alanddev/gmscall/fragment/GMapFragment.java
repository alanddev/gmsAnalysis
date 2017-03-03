package com.alanddev.gmscall.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alanddev.gmscall.R;
import com.alanddev.gmscall.ui.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by longty on 26/02/2017.
 */
public class GMapFragment extends Fragment {
    private Activity activity;
    private GoogleMap map;
    double longitude, latitude;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trans_map_view, container, false);
        activity = getActivity();
        map = ((MapFragment)activity.getFragmentManager().findFragmentById(R.id.map)).getMap();
        getLocation();
        //LatLng sydney = new LatLng(-34, 151);
        LatLng current;
        if (latitude >0  && longitude >0){
             current = new LatLng (latitude,longitude);
        }else{
            current = new LatLng(-34, 151);
        }
        map.addMarker(new MarkerOptions().position(current).title("Xin chào, bạn đang ở đây :)"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                current, 16));
        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MapFragment f = (MapFragment)getActivity().getFragmentManager()
                .findFragmentById(R.id.map);
        if (f != null)
            getActivity().getFragmentManager().beginTransaction().remove(f).commit();
    }


    public void getLocation(){
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // new version must check permission :)
        try {
            Location currentLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (currentLocation == null){
                currentLocation = new Location("");
                currentLocation.setLatitude(21.0345);
                currentLocation.setLongitude(105.827);
            }
            longitude = currentLocation.getLongitude();
            latitude = currentLocation.getLatitude();

        }catch(SecurityException e){

        }

    }

}
