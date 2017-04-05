package com.alanddev.gmscall.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.alanddev.gmscall.model.Command;
import com.alanddev.gmscall.R;

import java.util.List;

/**
 * Created by ANLD on 31/12/2015.
 */
public class CommandAdapter extends ArrayAdapter<Command> {

    private Context mContext;
    private LayoutInflater inflate;
    public CommandAdapter(Context context, LayoutInflater inflate, List<Command> commands) {
        super(context, 0, commands);
        mContext=context;
        this.inflate = inflate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final Command command = (Command)getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_command, parent, false);
        }
        // Lookup view for data population
        TextView tvCmd = (TextView) convertView.findViewById(R.id.cmd);
        TextView tvServer = (TextView) convertView.findViewById(R.id.server);
        TextView tvElapsedTime = (TextView) convertView.findViewById(R.id.elapseTime);
        TextView tvStream = (TextView) convertView.findViewById(R.id.stream);
        TextView tvNumberRun = (TextView) convertView.findViewById(R.id.numberRun);
        TextView tvtimeNext = (TextView) convertView.findViewById(R.id.timeNext);
        TextView tvUser = (TextView) convertView.findViewById(R.id.user);
        TextView tvPass = (TextView) convertView.findViewById(R.id.pass);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.status);

        ImageView imgIcon = (ImageView)convertView.findViewById(R.id.icon);
        // Populate the data into the template view using the data object
        // ID, Server, Cmd, time, stream, numberrun,timeNext,user,pass, status
        tvCmd.setText(command.getCmd());
        tvServer.setText(command.getServer());
        tvElapsedTime.setText(command.getTimeTest());
        tvStream.setText(command.getStream());
        tvNumberRun.setText(command.getNumber());
        tvtimeNext.setText(command.getTimeToNext());
        tvUser.setText(command.getUser());
        tvPass.setText(command.getPass());
        tvStatus.setText(command.getStatus());
        //int temp = position%5+1;
        imgIcon.setImageResource(mContext.getResources().getIdentifier(command.getImg(), "mipmap", mContext.getPackageName()));

        // Return the completed view to render on screen
        return convertView;
    }



}
