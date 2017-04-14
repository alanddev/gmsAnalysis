package com.alanddev.gmscall.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.Settings;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alanddev.gmscall.R;
import com.alanddev.gmscall.ui.MainActivity;
import com.alanddev.gmscall.ui.WiFiDirectBroadcastReceiver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class WifiDirectFragment extends Fragment implements WifiP2pManager.ChannelListener, DeviceListFragment.DeviceActionListener{
    public static String file_log_ok ="/sdcard/ok.txt", file_log_dis="/sdcard/fail.txt";
    public static final String TAG = "wifidirectdemo";
    private WifiP2pManager manager;
    private boolean isWifiP2pEnabled = false;
    private boolean retryChannel = false;
    private final IntentFilter intentFilter = new IntentFilter();
    private WifiP2pManager.Channel channel;
    private BroadcastReceiver receiver = null;
    private Button btnScan;
    private Button btnReceiver;
    private Thread Scan;
    private Thread threadReceive;
    public static WifiP2pConfig config;
    public String name = "", address = "", status = "";
    public static int number_disconnect = 0, number_connect = 0, number_scan = 0, number_scan_fails = 0;
    public static long time_disconnect, time_connect, time_scan, time_scan_fails;

    public WifiDirectFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WifiDirectFragment newInstance() {
        WifiDirectFragment fragment = new WifiDirectFragment();
        return fragment;
    }

    public void setIsWifiP2pEnabled(boolean isWifiP2pEnabled) {
        this.isWifiP2pEnabled = isWifiP2pEnabled;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wifi, container, false);

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_DISCOVERY_CHANGED_ACTION);

        manager = (WifiP2pManager) getActivity().getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(getActivity(), getActivity().getMainLooper(), null);
        receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);
        btnScan = (Button)rootView.findViewById(R.id.buttonScan);
        btnReceiver = (Button)rootView.findViewById(R.id.buttonReceive);
        btnScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //scanDevice();
                Scan = new Thread()
                {
                    @Override
                    public void run()
                    {
                        while(true)
                        {
                            try
                            {
                                scanDeviceA();
                            }
                            catch (Exception e ){
                                e.printStackTrace();
                            }
                            try{
                                Thread.sleep(30000);
                            }catch (Exception e){
                            }

                        }
                    }
                };
                Scan.start();
            }
        });

        btnReceiver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //scanDevice();
                threadReceive = new Thread()
                {
                    @Override
                    public void run()
                    {
                        while(true)
                        {
                            try
                            {
                                scanDeviceReceive();
                            }
                            catch (Exception e ){
                                e.printStackTrace();
                            }
                            try{
                                Thread.sleep(30000);
                            }catch (Exception e){
                            }

                        }
                    }
                };
                threadReceive.start();
            }
        });


        return rootView;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

    public void resetData() {
        DeviceListFragment fragmentList = (DeviceListFragment) getChildFragmentManager()
                .findFragmentById(R.id.frag_list);
        DeviceDetailFragment fragmentDetails = (DeviceDetailFragment) getChildFragmentManager()
                .findFragmentById(R.id.frag_detail);
        if (fragmentList != null) {
            fragmentList.clearPeers();
        }
        if (fragmentDetails != null) {
            fragmentDetails.resetViews();
        }
    }
    //master
    public void scanDeviceA()
    {
        //ghilog("Bat Dau Scan", file_log_ok); // Bat dau Scan -> Ghi log

        final DeviceListFragment fragment = (DeviceListFragment) getChildFragmentManager()
                .findFragmentById(R.id.frag_list);


        if (fragment.getDevice().status != WifiP2pDevice.CONNECTED)
        {

            //fragment.onInitiateDiscovery();
            Thread check =new Thread(new Runnable() {
                public void run()
                {
                    long t1 = System.currentTimeMillis();
                    boolean scan = true, timkiem = false;
                    while (t1 <System.currentTimeMillis()+10000  &&  scan) {
                        try{
                            String xxx = Arrays.toString(DeviceListFragment.peers.toArray());
                            timkiem = true;
                            Log.d("DEVICE",t1+" "+xxx);
                            //if (xxx.contains("Phone1"))
                            //if (xxx.contains("MayJ5A"))
                            //{
                                //System.out.println(xxx);
                                String []results_array = xxx.split("\n");
                                int check_result = 0;
                                for (int i=0;i< results_array.length; i++)
                                {
                                    if(results_array[i].indexOf("Device") != -1 && check_result==0)
                                    //if(results_array[i].indexOf("MayJ5A") != -1 && check_result==0)
                                    {
                                        name = results_array[i];
                                        check_result = 1;
                                        scan = false;
                                    }
                                    if(results_array[i].indexOf("deviceAddress") != -1 && check_result==1)
                                    {
                                        address = results_array[i];
                                        check_result = 2;
                                    }
                                    if(results_array[i].indexOf("status") != -1 && check_result==2)
                                    {
                                        status = results_array[i];
                                        check_result = 3;
                                    }
                                }
                                //Thread.sleep(1000);

                           // }
                            //t1=t1+100;
                            Thread.sleep(5000);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                    if(timkiem == true)
                    {
                        String []results_address = address.split(" ");
                        String []results_status = status.split(" ");
                        Log.d("Tim kiem",name + "---" + results_address[2] + "---" + results_status[2]);
                        if(results_status[2].equals("3"))
                        {
                            connectpeer(results_address[2]);
                        }
                    }
                }
            });
            check.start();
            manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {

                @Override
                public void onSuccess() {
                    Toast.makeText(getActivity(), "Discovery Initiated ",
                            Toast.LENGTH_SHORT).show();
                    time_scan = System.currentTimeMillis();
                    number_scan++;
                    Log.d("Lan scan thanh cong : "+ number_scan, " tai thoi diem : "+  time_scan +" ");
                    //ghilog("Discovery Initiated", file_log_ok); // Bat dau Scan -> Ghi log
                }

                @Override
                public void onFailure(int reasonCode) {
                    Toast.makeText(getActivity(), "Discovery Failed : " + reasonCode,
                            Toast.LENGTH_SHORT).show();
                    time_scan_fails = System.currentTimeMillis();
                    number_scan_fails++;
                    //log_wifi("Lan scan fails : ", " tai thoi diem : ", number_scan_fails, time_scan_fails);
                    //ghilog("Discovery Failed", file_log_ok); // Bat dau Scan -> Ghi log
                }
            });
        }

        else{
            //SystemClock.sleep(10000);
            //Log.d("Ket noi","Thiet bi dang trong trang thai ket noi");
            //ghilog("Thiet Bi Dang trong trang thai ket noi->Do Nothing", file_log_ok); // Bat dau Scan -> Ghi log
        }

    }


    //slave
    public void scanDeviceReceive()
    {
        final DeviceListFragment fragment = (DeviceListFragment) getChildFragmentManager()
                .findFragmentById(R.id.frag_list);
        if (fragment.getDevice().status != WifiP2pDevice.CONNECTED)
        {
            manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {

                @Override
                public void onSuccess() {
                    re_connect_slave();
                    Toast.makeText(getActivity(), "Discovery Initiated",
                            Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(int reasonCode) {
                    Toast.makeText(getActivity(), "Discovery Failed : " + reasonCode,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            //SystemClock.sleep(10000);
            System.out.println("Thiet bi dang trong trang thai ket noi");
        }

    }


    public void re_connect_master(String ip_slave)
    {
        String[] command2 = {"su","-c","adb connect "+ip_slave};
        String [] clear_key  = {"su","-c","HOME=/data;rm /data/.android/*; cp /sdcard/.android/* /data/.android/;"};
        String [] cmd1  = {"su","-c","adb kill-server;HOME=/data/; adb start-server"};
        try{
            ProcessBuilder clear_k = new ProcessBuilder( clear_key);
            Process clear_p = clear_k.start();
            clear_p.waitFor();
            ProcessBuilder probuilder1 = new ProcessBuilder(cmd1);
            Process p1 = probuilder1.start();p1.waitFor();
            ProcessBuilder probuilder = new ProcessBuilder(command2);
            Process p = probuilder.start();
            InputStream in = p.getInputStream();
            OutputStream out = p.getOutputStream();
            InputStream err = p.getErrorStream();
            BufferedReader br= new BufferedReader(new InputStreamReader(in));
            String line;
            String kq="";
            while((line = br.readLine()) != null){
                System.out.println(line);
                kq=kq+line+"\n";
            }
            Toast.makeText(getActivity(),kq,Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.printStackTrace();
        }
    }


    public void re_connect_slave()
    {
        String [] cmd1  = {"su","-c","HOME=/data/;setprop service.adb.tcp.port 5555;stop adbd;start adbd"};
        try{
            ProcessBuilder probuilder1 = new ProcessBuilder(cmd1);
            Process p1 = probuilder1.start();
            p1.waitFor();
            Toast.makeText(getActivity(), "Slave Initiated",
                    Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getActivity(), "Slave Failed",
                    Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.atn_direct_enable:
                if (manager != null && channel != null) {

                    // Since this is the system wireless settings activity, it's
                    // not going to send us a result. We will be notified by
                    // WiFiDeviceBroadcastReceiver instead.

                    startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                } else {
                    Log.e(TAG, "channel or manager is null");
                    ghilog("channel or manager is null", file_log_ok); // Bat dau Scan -> Ghi log
                }
                return true;

            case R.id.atn_direct_discover:
                if (!isWifiP2pEnabled) {
                    Toast.makeText(getActivity(), R.string.p2p_off_warning,
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
                final DeviceListFragment fragment = (DeviceListFragment) getChildFragmentManager()
                        .findFragmentById(R.id.frag_list);

                fragment.onInitiateDiscovery();
                manager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getActivity(), "Discovery Initiated",
                                Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onFailure(int reasonCode) {
                        Toast.makeText(getActivity(), "Discovery Failed : " + reasonCode,
                                Toast.LENGTH_SHORT).show();
                    }

                });
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void showDetails(WifiP2pDevice device) {
        DeviceDetailFragment fragment = (DeviceDetailFragment) getChildFragmentManager()
                .findFragmentById(R.id.frag_detail);
        fragment.showDetails(device);
    }

    @Override
    public void connect(WifiP2pConfig config) {
        config.deviceAddress = "0a:21:ef:09:a5:b4";
        manager.connect(channel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                // WiFiDirectBroadcastReceiver will notify us. Ignore for now.
                Log.d("Connect","Gui lenh Connect thanh cong");
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(getActivity(), "Connect failed. Retry.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void connectpeer(final String address) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = address;
        config.wps.setup = WpsInfo.PBC;
        manager.connect(channel, config, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                // WiFiDirectBroadcastReceiver will notify us. Ignore for now.
                time_connect = System.currentTimeMillis();
                number_connect++;
                Log.d("connectpeer","Connect thanh cong");
                re_connect_master(address);
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(getActivity(), "Connect failed. Retry.",
                        Toast.LENGTH_SHORT).show();
                ghilog("Send Invite Fail", file_log_ok); // Bat dau Scan -> Ghi log
            }
        });
    }

    @Override
    public void disconnect() {
        final DeviceDetailFragment fragment = (DeviceDetailFragment) getChildFragmentManager()
                .findFragmentById(R.id.frag_detail);
        fragment.resetViews();
        manager.removeGroup(channel, new WifiP2pManager.ActionListener() {

            @Override
            public void onFailure(int reasonCode) {
                Log.d(TAG, "Disconnect failed. Reason :" + reasonCode);
            }

            @Override
            public void onSuccess() {
                fragment.getView().setVisibility(View.GONE);
                time_disconnect = System.currentTimeMillis();
                number_disconnect++;
                //disconnect_fail(number_disconnect, time_disconnect);
                ghilog("Disconnect "+number_disconnect, file_log_dis); // Bat dau Scan -> Ghi log
                ghilog("Disconnect "+number_disconnect, file_log_ok); // Bat dau Scan -> Ghi log
                //System.out.println("So lan disconnect : " + number_disconnect);
            }

        });
    }
    public void disconnect_fail(int number, long time)
    {
        try
        {
            //System.out.println("So lan disconnect : " + number_disconnect);
            String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath()+"/disconnect.txt";
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard,true));
            //OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(sdcard, MODE_APPEND));
            writer.append("Lan disconnect : " + number + " tai thoi diem : " + time + "\n");
            writer.flush();
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void log_wifi(String str1,String str2,int number,long time)
    {
        try
        {
            String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath()+"/log_wifi.txt";
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard,true));
            writer.append(str1 + number + str2 + time + "\n");
            writer.flush();
            writer.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void ghilog(String noidung,String file_log)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        //String file_log = "e://x//GSM_SMS_Witalk_oneside.txt";
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file_log, true)));
            String thoigian = dateFormat.format(new Date()) +" : " + noidung;
            out.println(thoigian);
            //out.println(noidung);
            out.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void onChannelDisconnected() {
        // we will try once more
        if (manager != null && !retryChannel) {
            Toast.makeText(getActivity(), "Channel lost. Trying again", Toast.LENGTH_LONG).show();
            resetData();
            retryChannel = true;
            manager.initialize(getActivity(), getActivity().getMainLooper(), this);
        } else {
            Toast.makeText(getActivity(),
                    "Severe! Channel is probably lost premanently. Try Disable/Re-Enable P2P.",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void cancelDisconnect() {

        /*
         * A cancel abort request by user. Disconnect i.e. removeGroup if
         * already connected. Else, request WifiP2pManager to abort the ongoing
         * request
         */
        if (manager != null) {
            final DeviceListFragment fragment = (DeviceListFragment) getChildFragmentManager()
                    .findFragmentById(R.id.frag_list);
            if (fragment.getDevice() == null
                    || fragment.getDevice().status == WifiP2pDevice.CONNECTED) {
                disconnect();
            } else if (fragment.getDevice().status == WifiP2pDevice.AVAILABLE
                    || fragment.getDevice().status == WifiP2pDevice.INVITED) {

                manager.cancelConnect(channel, new WifiP2pManager.ActionListener() {

                    @Override
                    public void onSuccess() {
                        Toast.makeText(getActivity(), "Aborting connection",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int reasonCode) {
                        Toast.makeText(getActivity(),
                                "Connect abort request failed. Reason Code: " + reasonCode,
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, intentFilter);

    }


}
