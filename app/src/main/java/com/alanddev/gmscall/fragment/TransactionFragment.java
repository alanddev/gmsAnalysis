package com.alanddev.gmscall.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;


import com.alanddev.gmscall.R;
import com.alanddev.gmscall.adapter.CellAdapter;
import com.alanddev.gmscall.model.Cell;

import java.util.ArrayList;

/**
 * Created by ANLD on 18/11/2015.
 */
public class TransactionFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
   // private static  List<Transactions> transactionses;
    public TransactionFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static TransactionFragment newInstance(int sectionNumber) {
        TransactionFragment fragment = new TransactionFragment();
        //transactionses = datas;
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ListView lstcell = (ListView) rootView.findViewById(R.id.lstcell);
        CellAdapter adapter = new CellAdapter(getActivity().getApplicationContext(),new ArrayList<Cell>());
        lstcell.setAdapter(adapter);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }
}




