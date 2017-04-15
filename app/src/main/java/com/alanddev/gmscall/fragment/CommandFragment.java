package com.alanddev.gmscall.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alanddev.gmscall.R;
import com.alanddev.gmscall.adapter.CommandAdapter;
import com.alanddev.gmscall.controller.CommandController;
import com.alanddev.gmscall.helper.MwSQLiteHelper;
import com.alanddev.gmscall.model.Command;
import com.alanddev.gmscall.ui.CommandAddActivity;
import com.alanddev.gmscall.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longty on 26/02/2017.
 */
public class CommandFragment extends Fragment {
    private Activity activity;
    private CommandController commandController;
    private CommandAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_command, container, false);
        commandController = new CommandController(getContext());
        ListView listCmd = (ListView)v.findViewById(R.id.list_command);

        /*//if (commandController.getAllCommands().size() == 0) {
            Command cmdTitle = new Command(0, "Cmd", "Server", "Time", "Stream", "Run", "Time2Next", "User", "Pass", "Status", "");
            //commandController.createCommand(cmdTitle);
            Command cmdFirst = new Command(1, "Loop", "N/A", "N/A", "N/A", "4", "N/A", "N/A", "N/A", "2/4", "star");
            //commandController.createCommand(cmdFirst);
            Command cmdSecond = new Command(2, "SDownload", "http://download.zing.vcdn.vn/", "20", "1", "10", "10", "-", "-", "Finish", "add1");
            //commandController.createCommand(cmdSecond);
            Command cmdThird = new Command(3, "Upload", "113.164.7.27", "20", "1", "10", "10", "dktu1", "dktu#1", "2/5", "ic_save");
            //commandController.createCommand(cmdThird);
            Command cmdFourth = new Command(4, "End_Loop", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "ic_replay");
            //commandController.createCommand(cmdFourth);
        //}*/
        //ArrayList<Command> listCommand = getData();
        //List<Command>
        adapter = new CommandAdapter(getActivity().getApplicationContext(), inflater, getData());
        listCmd.setAdapter(adapter);

        listCmd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long commanid = parent.getItemIdAtPosition(position);
                Intent intent = new Intent(getContext(), CommandAddActivity.class);
                intent.putExtra(MwSQLiteHelper.COLUMN_COMMAND_ID,commanid);
                startActivityForResult(intent,Constant.COMMAND_ADD_RESULT);
            }
        });


        activity = getActivity();

        FloatingActionButton addCommand = (FloatingActionButton)v.findViewById(R.id.add_command);
        addCommand.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), CommandAddActivity.class);
                startActivityForResult(intent, Constant.COMMAND_ADD_RESULT);
            }
        });

        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(resultCode== Constant.COMMAND_ADD_RESULT) {
            notifyDataSetChanged();
        }
    }


    private void notifyDataSetChanged(){
        adapter.clear();
        adapter.addAll(getData());
        adapter.notifyDataSetChanged();


    }

    private List<Command> getData(){
        CommandController commandController = new CommandController(getContext());
        commandController.open();
        List<Command> commands = commandController.getAllCommands();
        commandController.close();
        return commands;
    }


}
