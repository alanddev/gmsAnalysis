package com.alanddev.gmscall.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.alanddev.gmscall.R;

/**
 * Created by ANLD on 18/11/2015.
 */
public class CallFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    ImageView imgCaller;
    int yOff = 100;
    int imgSource = 1;
    ImageView imgCallee;

    private static final String ARG_SECTION_NUMBER = "section_number";
   // private static  List<Transactions> transactionses;
    public CallFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_call, container, false);
        imgCaller = (ImageView)rootView.findViewById(R.id.imageView);

        //TranslateAnimation trans = new TranslateAnimation(0,100,0,100);
        //trans.setDuration(500);
        //imgCaller.startAnimation(trans);




        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, gpsLocationListener);
        new Runnable() {
            int updateInterval = 1000; //=one second

            @Override
            public void run() {
                if (imgSource > 0) {
                    imgCaller.setImageResource(R.mipmap.ic_arrow_right);
                }else{
                    imgCaller.setImageResource(R.mipmap.star);
                }
//                TranslateAnimation trans = new TranslateAnimation(0,yOff,0,yOff+10);
//                trans.setDuration(500);
//                imgCaller.startAnimation(trans);
//                yOff +=10;
                imgSource =-imgSource;
            }
        }.run();
    }


}




