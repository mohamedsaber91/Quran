package com.example.quran;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.quran.Base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AzkarF extends BaseFragment{
    protected ImageView tasbeh;
    protected ImageView refresh;
    protected TextView view_total_count;
    protected TextView view_single_count;
    protected Spinner spinner;
    protected static int single_counter = 0;
    protected static int total_counter = 0;
    View.OnClickListener tasbeh_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            single_counter++;
            total_counter++;
              view_single_count.setText(single_counter+"");
              view_total_count.setText(total_counter+"");
        }
    };
    View.OnClickListener refresh_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            single_counter=0;
            view_single_count.setText(0+"");
        }
    };

    AdapterView.OnItemSelectedListener spinner_selected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            single_counter = 0;
            view_single_count.setText(0+"");
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };


    public AzkarF() {
        // Required empty public constructor
    }


    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_azkar, container, false);
        init();
        showSingleCount();
        showTotalCount();

        return view;
    }

    private void showTotalCount() {
        view_total_count.setText(total_counter+"");
    }

    private void showSingleCount() {
        view_single_count.setText(single_counter+"");
    }

    private void init() {
        tasbeh = view.findViewById(R.id.messbaha_c);
        tasbeh.setOnClickListener(tasbeh_click);

        refresh = view.findViewById(R.id.m_refresh);
        refresh.setOnClickListener(refresh_click);

        view_total_count = view.findViewById(R.id.total_count);
        view_single_count = view.findViewById(R.id.single_count);
        spinner = view.findViewById(R.id.spinner_);
        spinner.setOnItemSelectedListener(spinner_selected);
    }


}
