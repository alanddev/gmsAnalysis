package com.alanddev.gmscall.util;

import java.util.ArrayList;

import android.app.Activity;


import com.alanddev.gmscall.adapter.NwInfoWindowAdapter;
import com.alanddev.gmscall.model.Network;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapUtils {
	public void setMap(Activity activity, GoogleMap map){	
		//map = ((MapFragment)activity.getFragmentManager().findFragmentById(R.id.map)).getMap();
		map.setInfoWindowAdapter(new NwInfoWindowAdapter(activity.getBaseContext()));
		map.setMyLocationEnabled(true);
		//setLocationFirstMarker(map);
	}
	
	public void moveMap(GoogleMap map,double latitude, double longitude){
		LatLng current = new LatLng (latitude,longitude);
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(
				current, 16));
	}
	
	//Get Current Position
	public void setCurrentMarker(GoogleMap map,Network net, double latitude, double longitude){
		LatLng current = new LatLng (latitude,longitude);
		map.addMarker(new MarkerOptions()
		.title(net.info())
		.snippet(net.getData())
		.icon(BitmapDescriptorFactory.defaultMarker(setMarkColor(net.getdBm())))
		.flat(true)
		.position(current));

	}
	
	public void setLocationFirstMarker(GoogleMap map,double latitude, double longitude){
		LatLng current = new LatLng (latitude,longitude);
		map.addMarker(new MarkerOptions()
		.title("You are here")
		.snippet("Current")
		.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
		.flat(true)
		.position(current));

	}	
	
	
	// add network info in database
	public void setNetworkMarkers(GoogleMap map){
		NwConst.getInstance();
		for (int i = 0 ; i <NwConst.sharedListNetwork.size(); i++){
			NwConst.getInstance();
			Network net = NwConst.sharedListNetwork.get(i);	
			map.addMarker(new MarkerOptions()
					.title(net.info())
					.snippet(net.getData())
					.icon(BitmapDescriptorFactory.defaultMarker(setMarkColor(net.getdBm())))
					.flat(true)
					.position(new LatLng(net.getMyLatitude(),net.getMyLongitude())));
				
		}
	}
	
    
	
/*	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);

	}*/

	public float setMarkColor(double dBm){
		float colorMark = BitmapDescriptorFactory.HUE_RED;
		if (dBm >=-60){
			colorMark = BitmapDescriptorFactory.HUE_RED;
		}else if (dBm >=-70 && dBm <-60 ){
			colorMark = BitmapDescriptorFactory.HUE_AZURE;
		}else if (dBm >=-80 && dBm <-70 ){
			colorMark = BitmapDescriptorFactory.HUE_YELLOW;
		}else if (dBm >=-90 && dBm <-80 ){
			colorMark = BitmapDescriptorFactory.HUE_GREEN;
		}else if (dBm >=-100 && dBm <-90 ){
			colorMark = BitmapDescriptorFactory.HUE_BLUE;
		}else if (dBm >=-110 && dBm <-100 ){
			colorMark = BitmapDescriptorFactory.HUE_MAGENTA;
		}else if (dBm >=-120 && dBm <-110 ){
			colorMark = BitmapDescriptorFactory.HUE_CYAN;
		}else if (dBm <-120){
			colorMark = BitmapDescriptorFactory.HUE_VIOLET;
		}
		return colorMark;
	}
	
	
	public void setDataNetwork(ArrayList<Network> lNetwork){
		
		Network net1 = new Network("Lotus1",105.82746,21.013813,-60);
		lNetwork.add(net1);
		// Dien Bien 
		Network net2 = new Network("Lotus2",105.839313,21.032296,-70);
		lNetwork.add(net2);
		//Trang Thi
		Network net3= new Network("Lotus3",105.846866,21.027289,-80);
		lNetwork.add(net3);
		// Hang Trong
		Network net4 = new Network("Lotus4",105.850943,21.027970,-90);
		lNetwork.add(net4);
		// Tho Nhuom
		Network net5 = new Network("Lotus5",105.846094,21.025045,-100);
		lNetwork.add(net5);
		// O Cho Dua 
		Network net6= new Network("Lotus6",105.826310,21.020358,-110);
		lNetwork.add(net6);
		// Cat Linh
		Network net7 = new Network("Lotus7",105.831502,21.028610,-120);
		lNetwork.add(net7);
		// Xa Dan
		Network net8= new Network("Lotus8",105.830069,21.017989,-126);
		lNetwork.add(net8);	
		
	}	
}
