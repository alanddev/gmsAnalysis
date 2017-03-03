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
    private static final String ARG_SECTION_NUMBER = "section_number";
   // private static  List<Transactions> transactionses;
    public CallFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_call, container, false);

        ImageView imgCaller = (ImageView)rootView.findViewById(R.id.imageView);
        TranslateAnimation trans = new TranslateAnimation(0,100,0,100);
        trans.setDuration(500);
        imgCaller.startAnimation(trans);
        return rootView;
    }
}




