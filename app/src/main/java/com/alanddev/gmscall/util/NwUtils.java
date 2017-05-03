package com.alanddev.gmscall.util;

import java.util.List;



import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import com.alanddev.gmscall.model.Network;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

public class NwUtils {
	
	//public NetworkUtils m_Context;
//	public static void getInstance(){
//		if (m_Context == null){
//			m_Context = new NetworkUtils();
//		}
//	}
	
	public Network NetworkFromTelMan(TelephonyManager tel,Activity activity){
		Network networkReturn = new Network();
		String networkOperator = tel.getNetworkOperator();
		int cid;
		CellLocation cellLocation = tel.getCellLocation();

		//GsmCellLocation cellLocation = (GsmCellLocation)tel.getCellLocation();


		try {
			String operatorName = tel.getNetworkOperatorName();
			networkReturn.setOperator(operatorName);
			if (networkOperator != null && networkOperator.length() >= 3) {
				networkReturn.setMcc(networkOperator.substring(0, 3));
				networkReturn.setMnc(networkOperator.substring(3));
			}
			GsmCellLocation gsmcellLocation;
			CdmaCellLocation cdmaCellLocation;
			String clsCell = cellLocation.getClass().toString();

			if (clsCell.contains("GsmCellLocation")){
				gsmcellLocation = (GsmCellLocation)tel.getCellLocation();
				networkReturn.setLac(gsmcellLocation.getLac());
				networkReturn.setCid(gsmcellLocation.getCid());
				cid = networkReturn.getCid();
				networkReturn.setCellID(cid & 0xffff);
				networkReturn.setRNC((cid >> 16) & 0xffff);

			}else if (clsCell.contains("CdmaCellLocation")){
				cdmaCellLocation = (CdmaCellLocation)tel.getCellLocation();

				//networkReturn.setLac(cdmaCellLocation.getBaseStationLatitude());
				//networkReturn.setCid(cdmaCellLocation.getCid());
			}




			networkReturn.setType(getNetworkTypeName(tel.getNetworkType()));
			networkReturn.setData(getWifi(networkReturn, activity));
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return networkReturn;
	}
	
	public static String getNetworkTypeName(int networkType){
		switch (networkType){
		// it is GSM Network
		case TelephonyManager.NETWORK_TYPE_GPRS:
			return "GPRS";
		case TelephonyManager.NETWORK_TYPE_EDGE:
			return "EDGE";
		// it is UMTS network
		case TelephonyManager.NETWORK_TYPE_HSDPA:
			return "HSDPA";
		case TelephonyManager.NETWORK_TYPE_UMTS:
			return "UMTS";
		case TelephonyManager.NETWORK_TYPE_HSPA:
			return "HSPA";
		case TelephonyManager.NETWORK_TYPE_HSUPA:
			return "HSUPA";
		default:
			return "UNKNOWN"; 
		}
	}
		
	public String checkType (Activity activity){
		ConnectivityManager manager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

		//For 3G check
		boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
		            .isConnectedOrConnecting();
		//For WiFi Check
		//boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
		//            .isConnectedOrConnecting();
		
		return is3g ? "3G connection" : "Wifi Connection";
	}
		
	public String getWifi(Network network, Activity activity){
		WifiManager wifiManager = (WifiManager)activity.getSystemService(Context.WIFI_SERVICE);
		String returnWifi = "";
		if (wifiManager.isWifiEnabled()){
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			// speed
			//wifiInfo.getLinkSpeed()
			returnWifi =  "WIFI - " + wifiInfo.getSSID();
		}else{
			returnWifi =  network.getOperator() + "-" + network.getType();
		}
		return returnWifi;
		
	}
	
	public String getNeighboringInfo(TelephonyManager tel){
		
		   //List<CellInfo> cellInfo = tel.getAllCellInfo();
		   List<NeighboringCellInfo> neighboringList = tel.getNeighboringCellInfo();
		   String stringNeighboring = "Neighboring List - Lac: CID : RSSI\n";
		   for (int i = 0; i < neighboringList.size();i++){
			   String dBm;
			   int rssi = neighboringList.get(i).getRssi();
			   if (rssi == NeighboringCellInfo.UNKNOWN_RSSI){
				   dBm = "Unknown RSSI";
			   } else {
				   dBm = String.valueOf(-113 + 2*rssi) + "dBm";
			   }
			   
			   stringNeighboring += String.valueOf(neighboringList.get(i).getLac()) +" : "
					     + String.valueOf(neighboringList.get(i).getCid()) +" : "
					     + String.valueOf(neighboringList.get(i).getPsc()) +" : "
					     + String.valueOf(neighboringList.get(i).getNetworkType()) +" : "
					     + dBm +"\n";

		   }
		   
		   return stringNeighboring;
		   
	}
	
/*
 * Get Download, upload rate per second (1000 milisecond)	
 */
	
//	private Handler mHandler = new Handler();
//	private long mStartRX = 0;
//	private long mStartTX = 0;
//	private final Runnable mRunnable = new Runnable() {
//		public void run() {
//		long rxBytes = TrafficStats.getTotalRxBytes()- mStartRX;
//		//RX.setText(Long.toString(rxBytes));
//		long txBytes = TrafficStats.getTotalTxBytes()- mStartTX;
//		//TX.setText(Long.toString(txBytes));
//		mHandler.postDelayed(mRunnable, 1000);
//		}
//		};
}
