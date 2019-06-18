package com.example.quran;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.quran.Adapters.SuraContentAdapter;
import com.example.quran.Base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SuraContent extends BaseActivity {

    protected TextView contentSuraName;
    protected RecyclerView versesRecyclerView;
    SuraContentAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> verses;
    InputStream in;
    BufferedReader reader;
    String line;
    int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_sura_content);
        initView();
        setVersesRecyclerView();
    }



    private void setVersesRecyclerView() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String content = intent.getStringExtra("content");
        verses = new ArrayList<>();

            try {
                in = activity.getAssets().open(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            reader = new BufferedReader(new InputStreamReader(in));
            try {

                while ((line = reader.readLine()) != null) {
                    verses.add(line+" ("+counter+") ");
                    counter++;
                }


                contentSuraName.setText(" سورة " + name);
            } catch (IOException e) {
                e.printStackTrace();

        }
        adapter = new SuraContentAdapter(verses);
        layoutManager = new LinearLayoutManager(activity);
        versesRecyclerView.setAdapter(adapter);
        versesRecyclerView.setLayoutManager(layoutManager);
    }

    private void initView() {
        contentSuraName = (TextView) findViewById(R.id.content_sura_name);
        versesRecyclerView = (RecyclerView) findViewById(R.id.versesRecyclerView);
    }
}
