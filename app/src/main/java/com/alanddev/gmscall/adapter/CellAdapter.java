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

    public CellAdapter(Context context, List<Cell> cells){
        this.cells = cells;
        this.mContext = context;
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

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Viewholder) convertView.getTag();
        }

        if(viewHolder.txtband==null){
            Log.d("TAG", "getView: ");
        }
        viewHolder.txtnoname.setText("s");
        viewHolder.txtband.setText(String.valueOf(cell.getBand()));
        viewHolder.txtearfcn.setText(String.valueOf(cell.getEarfcn()));
        viewHolder.txtpci.setText(String.valueOf(cell.getPci()));
        viewHolder.prorsrp.setProgress(Math.round(cell.getRsrp()));
        viewHolder.prorsrq.setProgress(Math.round(cell.getRsrq()));
        /*if(progress<25){
            viewHolder.progressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progess_drawable_25));
        }else if(progress<50){
            viewHolder.progressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progess_drawable_50));
        }else if(progress<70){
            viewHolder.progressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progess_drawable_70));
        }else if(progress<90){
            viewHolder.progressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progess_drawable_90));
        }else{
            viewHolder.progressBar.setProgressDrawable(mContext.getResources().getDrawable(R.drawable.progess_drawable_100));
        }
        viewHolder.progressBar.setProgress(progress);*/
        //viewHolder.progressBar.setProgress((int)(budget.getRealamt()/budget.getAmount())*100);
        return convertView;
    }

    class Viewholder {
        public TextView txtnoname;
        public TextView txtband;
        public TextView txtearfcn;
        public TextView txtpci;
        public ProgressBar prorsrp;
        public ProgressBar prorsrq;

        Viewholder() {
        }
    }
}
