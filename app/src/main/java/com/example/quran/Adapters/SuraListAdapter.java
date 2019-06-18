package com.example.quran.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.quran.R;

public class SuraListAdapter extends RecyclerView.Adapter<SuraListAdapter.ViewHolder> {
    String [] suraList;
    OnItemClickListener onSuraClick;

    public void setOnSuraClick(OnItemClickListener onSuraClick) {
        this.onSuraClick = onSuraClick;
    }

    public SuraListAdapter(String[] suraList) {
        this.suraList = suraList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int ViewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_sura_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {
        viewHolder.name.setText(suraList[pos]);
        if (onSuraClick != null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSuraClick.onItemClick(pos);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        if (suraList==null) return 0;
        return suraList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.list_sura_name);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(int pos);
    }
}
