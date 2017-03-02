package com.alanddev.gmscall.util;



import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import com.alanddev.gmscall.model.Network;
import android.os.Bundle;

public class LocationUtils {
	private double latitude;
	private double longitude;
	public Activity activity;
	private Network net;
	private boolean isChange;

	public LocationUtils(double latitude, double longitude, Activity activity,Network net){
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.activity = activity;
		this.net = net;
		setChange(false);
	}
	
	public void changeLocation(long minTime, float minDistance){
		try {
			LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, new LocationListener() {
				@Override
				public void onStatusChanged(String provider, int status, Bundle extras) {
				}

				@Override
				public void onProviderEnabled(String provider) {
				}

				@Override
				public void onProviderDisabled(String provider) {
				}

				@Override
				public void onLocationChanged(final Location location) {
					setLatitude(location.getLatitude());
					setLongitude(location.getLongitude());
					//setCurrentMarker(map);
					net.setMyLatitude(latitude);
					net.setMyLongitude(longitude);
					setChange(true);
				}
			});
		}catch(SecurityException e){

		}
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public Network getNetwork() {
		return net;
	}

	public void setNetwork(Network net) {
		this.net = net;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}
}
//private Handler handler = new Handler();
//
//private Runnable runnable = new Runnable() 
//{
//
//	public void run() 
//	{
//		//	        	LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//		//	            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, new LocationListener() {
//		//	                @Override
//		//	                public void onStatusChanged(String provider, int status, Bundle extras) {
//		//	                }
//		//
//		//	                @Override
//		//	                public void onProviderEnabled(String provider) {
//		//	                }
//		//
//		//	                @Override
//		//	                public void onProviderDisabled(String provider) {
//		//	                }
//		//
//		//	                @Override
//		//	                public void onLocationChanged(final Location location) {
//		//	                	latitude = location.getLatitude();
//		//	                	longitude = location.getLongitude();
//		//	                    net.setMyLatitude(latitude);
//		//	                    net.setMyLongitude(longitude);
//		//	                }
//		//	            });     
//		locUtils = new LocationUtils(latitude, longitude, activity, net);
//		locUtils.changeLocation(1000,100);
//		latitude = locUtils.getLatitude();
//		longitude = locUtils.getLongitude();
//		handler.postDelayed(this, 1000);
//		//net = locUtils.getNetwork();
//		tvLat.setText(titleLatitude + ": " + latitude);
//		tvLong.setText(titleLongitude + ": " + longitude);
//	}
//};