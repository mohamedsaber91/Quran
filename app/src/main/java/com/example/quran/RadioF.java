package com.example.quran;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quran.APIS.APIManger;
import com.example.quran.APIS.Model.RadioResponse;
import com.example.quran.APIS.Model.RadiosItem;
import com.example.quran.Adapters.RadioChannelsAdapter;
import com.example.quran.Base.BaseFragment;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RadioF extends BaseFragment {
      RecyclerView recyclerView;
      RecyclerView.LayoutManager layoutManager;
      RadioChannelsAdapter adapter;
      MediaPlayer mediaPlayer;


    public RadioF() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_radio, container, false);
        init();
        getRdaioChannels();
        return view;
    }

    private void init() {
        recyclerView = view.findViewById(R.id.radio_recyclerview);
        layoutManager = new LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false);
        adapter = new RadioChannelsAdapter(null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.setPlayClickListener(new RadioChannelsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(RadiosItem radiosItem) {
                String url = radiosItem.getRadioUrl();
                mediaPlayer = new MediaPlayer();
                playRadio(url);
            }
        });
        adapter.setStopClickListener(new RadioChannelsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(RadiosItem radiosItem) {
                stopRadio();
            }
        });
    }

    private void getRdaioChannels() {
        showProgressBar(R.string.loading,R.string.please_wait);
        APIManger.getApis()
                .getRadioChannel()
                .enqueue(new Callback<RadioResponse>() {
                    @Override
                    public void onResponse(Call<RadioResponse> call,
                                           Response<RadioResponse> response) {
                        hideProgressBar();
                        if (response.isSuccessful()){
                            List<RadiosItem> items = response.body().getRadios();
                            adapter.setOnDataChanged(items);
                        }else {
                            showMessage(R.string.error,R.string.cannotconnecttoserver);
                        }

                    }

                    @Override
                    public void onFailure(Call<RadioResponse> call, Throwable t) {
                        hideProgressBar();
                             showMessage(getString(R.string.error),t.getLocalizedMessage());
                    }
                });
    }

    private void playRadio(String url){
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }
    private void stopRadio(){
        if (mediaPlayer != null){
            mediaPlayer.stop();
        }
    }

}
