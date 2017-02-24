package com.alanddev.gmscall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.alanddev.gmscall.fragment.TransactionFragment;
import com.alanddev.gmscall.model.Transactions;

import java.util.List;


/**
 * Created by ANLD on 18/11/2015.
 */
public class TransSectionPagerAdapter extends FragmentStatePagerAdapter {
    //private List<Transactions> datas;
    public TransSectionPagerAdapter(FragmentManager fm, List<Transactions> datas) {
        super(fm);
        //this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Fragment fragment = TransactionFragment.newInstance(position + 1);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "VIEW 0";
                break;
            case 1:
                title = "VIEW 1";
                break;
            case 2:
                title = "VIEW 2";
        }
        return title;
    }


}
