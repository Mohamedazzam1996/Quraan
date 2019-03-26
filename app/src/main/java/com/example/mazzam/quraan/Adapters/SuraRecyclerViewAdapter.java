package com.example.mazzam.quraan.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mazzam.quraan.R;

import java.util.ArrayList;

public class SuraRecyclerViewAdapter extends RecyclerView.Adapter<SuraRecyclerViewAdapter.ViewHolder> {

    View view;
    ArrayList<String>suracontent;
    OnItemClickListner onAyaClickListner;
    public void setOnAyaClickListner(OnItemClickListner onAyaClickListner) {
        this.onAyaClickListner = onAyaClickListner;
    }

    public SuraRecyclerViewAdapter(ArrayList<String> suracontent) {
        this.suracontent = suracontent;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_sura_content, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        final String suraName=suracontent.get(position);
        viewHolder.suraNameTV.setText(suraName);
        if (onAyaClickListner!=null)
        {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAyaClickListner.onItemClick(position,suraName);
                }
            });

        }
    }


    @Override
    public int getItemCount() {

        return suracontent.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView suraNameTV;

        public ViewHolder(@NonNull View view) {
            super(view);
            suraNameTV = view.findViewById(R.id.aya_name_TV);
        }
    }
    public interface OnItemClickListner{
        public void onItemClick(int position ,String aya);
    }
}


