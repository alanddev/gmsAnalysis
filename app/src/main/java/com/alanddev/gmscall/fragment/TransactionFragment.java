package com.alanddev.gmscall.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import com.alanddev.gmscall.R;
import com.alanddev.gmscall.adapter.CellAdapter;
import com.alanddev.gmscall.listener.NwPhoneStateListener;
import com.alanddev.gmscall.model.Cell;
import com.alanddev.gmscall.model.Network;
import com.alanddev.gmscall.util.Constant;
import com.alanddev.gmscall.util.NwConst;
import com.alanddev.gmscall.util.NwUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANLD on 18/11/2015.
 */
public class TransactionFragment extends Fragment {

    TableLayout tabNetwork;
    Activity activity;
    private double longitude;
    private double latitude;

    private int lac;
    private int cellID;
    private int RNC;
    private int rsrp;
    private int rsrq;
    private int cqi;
    private double dBm;
    private double ecIO;
    private double SNR;
    private String time;
    private double altitude;
    private double ground;
    private double height;
    private double nwAccuracy;
    private double speed;


    private String networkType;
    private String titleOperator;
    private String titleRSCP;
    private String titleMCC;
    private String titleMNC;
    private String titleLongitude;
    private String titleLatitude;
    private String titleLAC;
    private String titleCellID;
    private String titleRNC;
    private String titleSNR;
    private String titleECNO;
    private String titleSpeed;
    private String titleAltitude;
    private String titleNwAccuracy;
    private String titleHeight;
    private String titleGround;
    private String titleType;
    private String data;

    private TextView tvRSCP;
    private ProgressBar proRSCP;
    private ProgressBar proSINR;

    private TextView tvMCC;
    private TextView tvOperator;
    private TextView tvMNC;
    private TextView tvLAC;
    private TextView tvCellID;
    private TextView tvSNR;
    private TextView tvRNC;
    private TextView tvType;
    private TextView tvEcNo;
    private TextView tvLat;
    private TextView tvLong;
    private TextView tvData;
    private TextView tvNwAccuracy;
    private TextView tvGround;
    private TextView tvSpeed;
    private TextView tvAltitude;
    private TextView tvHeight;
    private TextView tvRsrp;
    private TextView tvRsrq;
    private TextView tvCqi;

    private TelephonyManager tel;
    private int nRow;
    private long currentTime;
    private NwUtils networkUtils = new NwUtils();
    //private LocationUtils locUtils;
    private Network net;
    DateFormat dateFormat;

    //private NwDataSource datasource;
    private String titleData;
    private String provider;
    private Location currentLocation;
    private List<Cell> cells;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private LocationManager locationManager;
   // private static  List<Transactions> transactionses;
    public TransactionFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TransactionFragment newInstance(int sectionNumber) {
        TransactionFragment fragment = new TransactionFragment();
        //transactionses = datas;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView lstcell = (ListView) rootView.findViewById(R.id.lstcell);
        cells = new ArrayList<Cell>();
        /*Cell cell = new Cell();
        cell.setBand("03");
        cell.setEarfcn(1503);
        cell.setPci(499);
        cell.setRsrp(Float.valueOf("75.7"));
        cell.setRsrq(Float.valueOf("6.8"));
        cells.add(cell);
        cell = new Cell();
        cell.setBand("04");
        cell.setEarfcn(1570);
        cell.setPci(209);
        cell.setRsrp(Float.valueOf("60.7"));
        cell.setRsrq(Float.valueOf("5.8"));
        cells.add(cell);

        cell = new Cell();
        cell.setBand("03");
        cell.setEarfcn(1270);
        cell.setPci(109);
        cell.setRsrp(Float.valueOf("80.7"));
        cell.setRsrq(Float.valueOf("7.8"));
        cells.add(cell);*/



        final CellAdapter adapter = new CellAdapter(getActivity().getApplicationContext(),cells);
        lstcell.setAdapter(adapter);
        activity = getActivity();
        tel = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
        //String networkOperator = tel.getNetworkOperator();
        net = networkUtils.NetworkFromTelMan(tel, activity);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // check if enabled and if not send user to the GSP settings
        // Better solution would be to display a dialog and suggesting to
        // go to the settings
        if (!enabled) {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }



        List<String> providers = locationManager.getProviders(true);
        // order is always (passive, gps, network, gps_bluetooth)
        for (int i=providers.size()-1; i>=0; i--) {
            provider = providers.get(i);
            try {
                currentLocation = locationManager.getLastKnownLocation(provider);
            }catch(SecurityException e){

            }
            if (currentLocation != null) break;
        }
        if (currentLocation == null) {
            currentLocation = new Location("");
            currentLocation.setLatitude(21.0345);
            currentLocation.setLongitude(105.827);
        }
        longitude = currentLocation.getLongitude();
        latitude = currentLocation.getLatitude();
        altitude = currentLocation.getAltitude();
        speed = currentLocation.getSpeed();
        ground = currentLocation.getBearing();

        initTable();
        initTitle(rootView);


        tel = (TelephonyManager)activity. getSystemService(Context.TELEPHONY_SERVICE);
        net = networkUtils.NetworkFromTelMan(tel, activity);

        lac = net.getLac();
        RNC = net.getRNC();
        cellID = net.getCellID();




        net.setMyLatitude(latitude);
        net.setMyLongitude(longitude);
        net.setAltitude(altitude);
        net.setGround(ground);
        net.setSpeed(speed);
        setTitleValue();


        tel.listen(new NwPhoneStateListener(getActivity()){
                       @Override
                       protected void updateActivity() {
                           //Add your activty logic here
                           dBm = getdBM();
                           ecIO = getEcIo();
                           SNR = getSNR();
                           rsrp = getLteRsrp();
                           rsrq = getLteRsrq();
                           cqi = getLteCqi();
                           net.setdBm(dBm);
                           net.setEcIO(ecIO);
                           net.setSNR(SNR);
                           net.setMyLatitude(latitude);
                           net.setMyLongitude(longitude);
                           net.setAltitude(altitude);
                           net.setGround(ground);
                           net.setSpeed(speed);
                           net.setType(getMobileNetwork());

                           Cell cell = new Cell();
                           cell.setBand(net.getMnc());
                           cell.setEarfcn(Integer.valueOf(net.getLac()));
                           cell.setPci(Integer.valueOf(net.getMcc()));
                           cell.setRsrp(rsrp);
                           cell.setRsrq(rsrq);
                           cells.add(cell);
                           adapter.notifyDataSetChanged();



                           setTitleValue();
                           long tmpTime = System.currentTimeMillis();
                           if (tmpTime - currentTime >= Constant.UPDATE_TIME){
                               currentTime = tmpTime;
                               time = (String) DateFormat.format("hh:mm:ss", currentTime);
                               net.setTime(time);
                               //NwConst.sharedListNetwork.add(net);
                               //addRow(nRow++,net);
                               setTitleValue();
                               //datasource.createNetwork(net);

                           }

                       }
                   },
                PhoneStateListener.LISTEN_SIGNAL_STRENGTHS);

        return rootView;
    }

    public String getMobileNetwork(){
        String mobileNetwork = "";
//		ConnectivityManager connMgr = (ConnectivityManager)
//			    activity.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//		if (mobile.getState() == NetworkInfo.State.CONNECTED){
//			mobileNetwork = "3G connection";
//		}
        TelephonyManager telephonyManager = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = telephonyManager.getNetworkType();
        switch(networkType){
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                mobileNetwork="1xRTT";
                break;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                mobileNetwork = "CDMA";
                break;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                mobileNetwork = "EDGE";
                break;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                mobileNetwork = "EHRPD";
                break;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                mobileNetwork = "GPRS";
                break;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                mobileNetwork = "HSDPA";
                break;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                mobileNetwork = "HSPA";
                break;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                mobileNetwork = "HSPAP";
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                mobileNetwork = "LTE";
                break;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                mobileNetwork="Unknown";
                break;
        }
        return mobileNetwork;
    }

    private final LocationListener gpsLocationListener =
            new LocationListener() {
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    System.out.println("Test status : "  + status + "\n");
                }

                @Override
                public void onProviderEnabled(String provider) {
                    System.out.println("Test provider : "  + provider + "\n");
                }

                @Override
                public void onProviderDisabled(String provider) {
                    System.out.println("provider is disable : "  + provider + "\n");
                }

                @Override
                public void onLocationChanged(final Location location) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    altitude = location.getAltitude();
                    speed = location.getSpeed();
                    ground = location.getBearing();
                    net.setMyLatitude(latitude);
                    net.setMyLongitude(longitude);
                }
            };

    @Override
    public void onResume(){
        super.onResume();
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, gpsLocationListener);
        try {
            locationManager.requestLocationUpdates(provider, 8000, 10, gpsLocationListener);
        }catch(SecurityException e){

        }
    }

    public void initTable(){
        nRow = 1;
        for (int i = 0; i < NwConst.getInstance().sharedListNetwork.size(); i++){
            Network net = NwConst.getInstance().sharedListNetwork.get(i);
            if (net.getTime() !=null){
                addRow(nRow++,net);
            }
        }
        currentTime = System.currentTimeMillis();
    }

    public void addRow(int index,Network net){
        TableRow row= new TableRow(activity);
        //TableRow.LayoutParams lp = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT);
        //row.setLayoutParams(lp);
        // follow priority: Time,LAC,Node,CellID,Level, Qual,Type,Serv_c
        addColumnInRow(row,time);
        addColumnInRow(row,Integer.toString(net.getLac()));
        addColumnInRow(row,Integer.toString(net.getRNC()));
        addColumnInRow(row,Integer.toString(net.getCellID()));
        addColumnInRow(row,Double.toString(net.getdBm()));
        addColumnInRow(row,Double.toString(net.getEcIO()));
        addColumnInRow(row,net.getType());
        addColumnInRow(row,"1" + index);
        tabNetwork.addView(row,index);
    }

    public void addColumnInRow(TableRow row,String sContent){
        TextView cell = new TextView(activity);
        cell.setText(sContent);
        cell.setTextColor(0xFFFFFFFF);
        cell.setBackgroundResource(R.drawable.cell_shape);
        cell.setLayoutParams(new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
        row.addView(cell);
    }

    @Override
    public void onDestroyView(){
//		datasource.close();
        super.onDestroyView();
    }

    public void initTitle(View v){
        // init TextView;
        tvRSCP = (TextView)v.findViewById(R.id.rsrp_progress_title);
        proRSCP = (ProgressBar)v.findViewById(R.id.rsrp_value);
        tvSNR = (TextView)v.findViewById(R.id.sinr_progress_title);
        proSINR = (ProgressBar)v.findViewById(R.id.sinr_value);
        tvMCC = (TextView)v.findViewById(R.id.earfcn_value);
        tvMNC = (TextView)v.findViewById(R.id.pci_value);
        tvLAC = (TextView)v.findViewById(R.id.plmn_value);
        tvRNC = (TextView)v.findViewById(R.id.band_value);
        tvCellID = (TextView)v.findViewById(R.id.ecellid_value);
        /*tvMCC = (TextView)v.findViewById(R.id.mcc);
        tvMNC = (TextView)v.findViewById(R.id.mnc);
        tvLAC = (TextView)v.findViewById(R.id.lac);
        tvCellID = (TextView)v.findViewById(R.id.cellID);
        tvSNR = (TextView)v.findViewById(R.id.snr);
        tvRNC = (TextView)v.findViewById(R.id.rnc);
        tvType = (TextView)v.findViewById(R.id.type);
        tvEcNo = (TextView)v.findViewById(R.id.ecno);
        tvOperator = (TextView)v.findViewById(R.id.operator);

        tvLat = (TextView)v.findViewById(R.id.latitude);
        tvLong = (TextView)v.findViewById(R.id.longitude);
        tvNwAccuracy = (TextView)v.findViewById(R.id.nw_accuracy);
        tvSpeed = (TextView)v.findViewById(R.id.speed);
        tvGround = (TextView)v.findViewById(R.id.ground);
        tvHeight = (TextView)v.findViewById(R.id.height);
        tvAltitude = (TextView)v.findViewById(R.id.altitude);

        tvData = (TextView)v.findViewById(R.id.data);
        tvRsrp = (TextView)v.findViewById(R.id.rsrp);
        tvRsrq = (TextView)v.findViewById(R.id.rsrq);
        tvCqi = (TextView)v.findViewById(R.id.cqi);*/
    }

    public void setTitleValue(){
        tvRSCP.setText(dBm + " dBm");
        int idBm = - (int)dBm;
        proRSCP.setProgress(idBm);
        proRSCP.setMax(120);
        tvSNR.setText(SNR + " dBm");
        proSINR.setProgress((int)SNR);
        tvMCC.setText(String.valueOf(net.getMcc()));
        tvMNC.setText(String.valueOf(net.getMnc()));
        tvLAC.setText(String.valueOf(lac));
        tvRNC.setText(String.valueOf(RNC));
        tvCellID.setText(String.valueOf(cellID));
        /*tvOperator.setText(titleOperator + ": " + net.getOperator());

        tvEcNo.setText(titleECNO +": " + ecIO);
        tvSNR.setText(titleSNR + ": " + SNR);
        tvLAC.setText(titleLAC + ": " + lac);
        tvMCC.setText(titleMCC + ":" + net.getMcc());
        tvMNC.setText(titleMNC + ":" + net.getMnc());
        tvCellID.setText(titleCellID + ": " + cellID);
        tvRNC.setText(titleRNC + ": " + RNC);
        tvData.setText(titleData + ": " + net.getData());
        tvNwAccuracy.setText(titleNwAccuracy + ": " + net.getNwAccuracy());
        tvHeight.setText(titleHeight + ": " + net.getHeight());
        tvGround.setText(titleGround + ": " + net.getGround());
        tvSpeed.setText(titleSpeed + ": " + net.getSpeed());
        tvAltitude.setText(titleAltitude + ": " + net.getAltitude());
        tvLat.setText(titleLatitude + ": " + latitude);
        tvLong.setText(titleLongitude + ": " + longitude);
        tvType.setText(titleType +": " + net.getType());
        tvRsrp.setText("Rsrp:" + rsrp);
        tvRsrq.setText("Rsrq:" + rsrq);
        tvCqi.setText("CQI:" + cqi);*/

    }
}




