package com.example.quran.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quran.R;

import java.util.ArrayList;

public class SuraContentAdapter extends RecyclerView.Adapter<SuraContentAdapter.ViewHolder> {

    ArrayList<String> verses;

    public SuraContentAdapter(ArrayList<String> verses) {
        this.verses = verses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.verse_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
        viewHolder.verse.setText(verses.get(pos));

    }

    @Override
    public int getItemCount() {
        if (verses==null) return 0;
        return verses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView verse;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            verse = itemView.findViewById(R.id.sura_verse);
        }
    }
}
