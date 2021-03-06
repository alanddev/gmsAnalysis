package com.alanddev.gmscall.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alanddev.gmscall.R;
import com.alanddev.gmscall.model.Cell;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by ANLD on 01/12/2015.
 */
public class CellAdapter extends BaseAdapter {
    private List<Cell> cells;
    private Context mContext;
    private String networkType;

    public CellAdapter(Context context, List<Cell> cells){
        this.cells = cells;
        this.mContext = context;
        //this.networkType = networkType;
    }
    @Override
    public int getCount() {
        if(cells!=null){
            return cells.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(cells!=null){
            return cells.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cell cell = (Cell)this.getItem(position);
        Viewholder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cell,null);
            viewHolder = new Viewholder();
            viewHolder.txtnoname = ((TextView) convertView
                    .findViewById(R.id.noname_value));
            viewHolder.txtband = ((TextView) convertView
                    .findViewById(R.id.bandc_value));
            viewHolder.txtearfcn = ((TextView) convertView
                    .findViewById(R.id.earfcnc_value));
            viewHolder.txtpci = ((TextView) convertView
                    .findViewById(R.id.pcic_value));
            viewHolder.prorsrp = ((ProgressBar) convertView
                    .findViewById(R.id.rsrp_value));
            viewHolder.prorsrq = ((ProgressBar) convertView
                    .findViewById(R.id.rsrq_value));
            viewHolder.txtrsrp = ((TextView) convertView
                    .findViewById(R.id.rsrp_progress_title));
            viewHolder.txtrsrq = ((TextView) convertView
                    .findViewById(R.id.rsrq_progress_title));

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Viewholder) convertView.getTag();
        }
        networkType = "HSPA";
        // 2G
        if (networkType.equals("HSPA")||networkType.equals("HSPAP")){
            viewHolder.txtnoname.setText(String.valueOf(cell.getArfcn()));
            int rssi =  Math.abs((int)cell.getRssi());
            viewHolder.prorsrq.setProgress(rssi);
            viewHolder.txtband.setVisibility(View.GONE);
            viewHolder.txtearfcn.setVisibility(View.GONE);
            viewHolder.txtpci.setVisibility(View.GONE);
            viewHolder.prorsrp.setVisibility(View.GONE);
            viewHolder.txtrsrp.setVisibility(View.GONE);
            viewHolder.txtrsrq.setVisibility(View.GONE);
        }else if (networkType.equals("LTE")){
            viewHolder.txtnoname.setText("s");
            viewHolder.txtband.setText(String.valueOf(cell.getBand()));
            viewHolder.txtearfcn.setText(String.valueOf(cell.getEarfcn()));
            viewHolder.txtpci.setText(String.valueOf(cell.getPci()));
            int rsrp = Math.abs((int)cell.getRsrp());
            viewHolder.prorsrp.setProgress(rsrp);
            int rsrq =  Math.abs((int)cell.getRsrq());
            viewHolder.prorsrq.setProgress(rsrq);
            viewHolder.txtrsrp.setText(String.valueOf(cell.getRsrp()));
            viewHolder.txtrsrq.setText(String.valueOf(cell.getRsrq()));
        }else{

        }

        return convertView;
    }

    class Viewholder {
        public TextView txtnoname;
        public TextView txtband;
        public TextView txtearfcn;
        public TextView txtpci;
        public ProgressBar prorsrp;
        public ProgressBar prorsrq;
        public TextView txtrsrp;
        public TextView txtrsrq;

        Viewholder() {
        }
    }

    /*public void setData(List<Cell> cells){
        this.cells=cells;
    }*/
}
