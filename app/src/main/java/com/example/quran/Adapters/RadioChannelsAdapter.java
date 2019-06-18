package com.example.quran.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quran.APIS.Model.RadiosItem;
import com.example.quran.R;

import java.util.List;

public class RadioChannelsAdapter extends RecyclerView.Adapter<RadioChannelsAdapter.ViewHolder> {
    List<RadiosItem> list;
    onItemClickListener playClickListener;
    onItemClickListener stopClickListener;

    public RadioChannelsAdapter(List<RadiosItem> list) {
        this.list = list;
    }

    public void setPlayClickListener(onItemClickListener clickListener) {
        this.playClickListener = clickListener;
    }

    public void setStopClickListener(onItemClickListener stopClickListener) {
        this.stopClickListener = stopClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.radio_channel_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int pos) {
           final RadiosItem current = list.get(pos);
           viewHolder.name.setText(current.getName());
           if (playClickListener != null){
               viewHolder.play.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       playClickListener.onItemClick(current);
                   }
               });
           }
           if (stopClickListener != null){
               viewHolder.stop.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       stopClickListener.onItemClick(current);
                   }
               });
           }
    }

    public void setOnDataChanged(List<RadiosItem> radiosItems){
        this.list = radiosItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list==null) return 0;
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView play, stop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.channel_title);
            play = itemView.findViewById(R.id.play_);
            stop = itemView.findViewById(R.id.stop_);
        }
    }

    public interface onItemClickListener{
        public void onItemClick(RadiosItem radiosItem);
    }
}
