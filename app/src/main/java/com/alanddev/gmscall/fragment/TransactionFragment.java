package com.alanddev.gmscall.fragment;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;


import com.alanddev.gmscall.R;
import com.alanddev.gmscall.adapter.CellAdapter;
import com.alanddev.gmscall.model.Cell;
import com.alanddev.gmscall.model.Network;
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
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
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
        Cell cell = new Cell();
        cell.setBand(3);
        cell.setEarfcn(1503);
        cell.setPci(499);
        cell.setRsrp(Float.valueOf("75.7"));
        cell.setRsrq(Float.valueOf("6.8"));
        List<Cell> cells = new ArrayList<Cell>();
        cells.add(cell);
        CellAdapter adapter = new CellAdapter(getActivity().getApplicationContext(),cells);
        lstcell.setAdapter(adapter);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
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
}




