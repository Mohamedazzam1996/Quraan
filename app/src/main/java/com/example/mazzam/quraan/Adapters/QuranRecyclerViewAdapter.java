package com.example.mazzam.quraan.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mazzam.quraan.Model.Sura;
import com.example.mazzam.quraan.R;

import java.util.ArrayList;

import static com.example.mazzam.quraan.R.id.sura_bg_image;

public class QuranRecyclerViewAdapter extends RecyclerView.Adapter<QuranRecyclerViewAdapter.ViewHolder> {
 OnItemClicklistner onItemClicklistner;
 String suraNmaes[];
 View view;

    public void setOnItemClicklistner(OnItemClicklistner onItemClicklistner) {
        this.onItemClicklistner = onItemClicklistner;
    }

    public QuranRecyclerViewAdapter(String [] suraNmaes)

 {
     this.suraNmaes=suraNmaes;
 }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

       view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.single_sura,viewGroup,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final String sura=suraNmaes[position];
        viewHolder.suraNameTV.setText(suraNmaes[position]);
        if(onItemClicklistner!=null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClicklistner.onItemClick(position,sura);
                }
            });
        }

    }

    @Override
    public int getItemCount() {


        return suraNmaes.length;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
    TextView suraNameTV;

    public ViewHolder(@NonNull View view) {
        super(view);
       suraNameTV=view.findViewById(R.id.sura_name_Tv);
    }
}
public interface OnItemClicklistner{
        public void onItemClick(int position,String sura);

}

}
