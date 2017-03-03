package com.alanddev.gmscall.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by ANLD on 18/11/2015.
 */
public class TransSectionPagerAdapter extends FragmentStatePagerAdapter {
    //private List<Transactions> datas;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public TransSectionPagerAdapter(FragmentManager fm){
        super(fm);
    }

   /* public TransSectionPagerAdapter(FragmentManager fm, List<Transactions> datas) {
        super(fm);
        //this.datas = datas;
    }*/

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //Fragment fragment = TransactionFragment.newInstance(position + 1);
        Fragment fragment = mFragmentList.get(position);
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment,String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }


}
