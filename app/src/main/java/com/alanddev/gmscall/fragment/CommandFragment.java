package com.alanddev.gmscall.fragment;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alanddev.gmscall.R;
import com.alanddev.gmscall.adapter.CommandAdapter;
import com.alanddev.gmscall.model.Command;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by longty on 26/02/2017.
 */
public class CommandFragment extends Fragment {
    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_command, container, false);
        ListView listCmd = (ListView)v.findViewById(R.id.list_command);
        Command cmdFirst = new Command(0,"Loop","N/A","N/A","N/A","4","N/A","N/A","N/A","2/4","star");
        Command cmdSecond = new Command(1,"SDownload","http://download.zing.vcdn.vn/","20","1","10","10","-","-","Finish","add1");
        Command cmdThird = new Command(2,"Upload","113.164.7.27","20","1","10","10","dktu1","dktu#1","2/5","ic_save");
        Command cmdFourth = new Command(3,"End_Loop","N/A","N/A","N/A","N/A","N/A","N/A","N/A","N/A","ic_replay");
        ArrayList<Command> listCommand = new ArrayList<Command>();
        listCommand.add(cmdFirst);
        listCommand.add(cmdSecond);
        listCommand.add(cmdThird);
        listCommand.add(cmdFourth);
        final CommandAdapter adapter = new CommandAdapter(getActivity().getApplicationContext(), inflater, listCommand);
        listCmd.setAdapter(adapter);
        activity = getActivity();
        return v;
    }





}
