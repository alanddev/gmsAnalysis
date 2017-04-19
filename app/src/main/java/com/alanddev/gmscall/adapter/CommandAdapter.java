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

import java.util.Collection;
import java.util.List;

/**
 * Created by ANLD on 31/12/2015.
 */
public class CommandAdapter extends ArrayAdapter<Command> {

    private Context mContext;
    private LayoutInflater inflate;
    private List<Command> datas;

    public CommandAdapter(Context context, LayoutInflater inflate, List<Command> commands) {
        super(context, 0, commands);
        mContext=context;
        this.inflate = inflate;
        this.datas = commands;
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

        //ImageView imgIcon = (ImageView)convertView.findViewById(R.id.icon);
        // Populate the data into the template view using the data object
        // ID, Server, Cmd, time, stream, numberrun,timeNext,user,pass, status
        tvCmd.setText(createFormatTableString(command.getCmd()));
        tvServer.setText(createFormatTableString(command.getServer()));
        tvElapsedTime.setText(createFormatTableString(command.getTimeTest()));
        tvStream.setText(createFormatTableString(command.getStream()));
        tvNumberRun.setText(createFormatTableString(command.getNumber()));
        tvtimeNext.setText(createFormatTableString(command.getTimeToNext()));
        tvUser.setText(createFormatTableString(command.getUser()));
        tvPass.setText(createFormatTableString(command.getPass()));
        tvStatus.setText(createFormatTableString(command.getStatus()));
        //int temp = position%5+1;
        //imgIcon.setImageResource(mContext.getResources().getIdentifier(command.getImg(), "mipmap", mContext.getPackageName()));

        // Return the completed view to render on screen
        return convertView;
    }

    private String createFormatTableString(String source){
        int length = source.length();
        int max_length = 6 ;
        //String delimiter = "|";

        if (length >= max_length){
            source = source.substring(0,max_length); //+ delimiter;
        }else{
            int different = max_length - length;
            for(int i = 0 ; i <different ; i++){
                source += " ";
            }
            //source += delimiter;
        }
        return source;
    }

    @Override
    public long getItemId(int position) {
        Command command = datas.get(position);
        if(command!=null){
            return command.getId();
        }
        return 0;
    }
}
