package com.example.mazzam.quraan;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mazzam.quraan.Adapters.QuranRecyclerViewAdapter;
import com.example.mazzam.quraan.Base.BaseFrgment;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuranFragment extends BaseFrgment {
    RecyclerView recyclerView;
    QuranRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    public QuranFragment() {
        // Required empty public constructor
    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_quran, container, false);
        recyclerView=view.findViewById(R.id.Quran_recyclerView);
        adapter=new QuranRecyclerViewAdapter(Datasourceee.ArSuras);
        layoutManager=new GridLayoutManager(getActivity(),3,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.setOnItemClicklistner(new QuranRecyclerViewAdapter.OnItemClicklistner() {
            @Override
            public void onItemClick(int position,String sura) {
                String surapos=position+1+"";
                Intent intent=new Intent(getActivity(),SuraActivity.class);
                intent.putExtra("sura_name",sura);
                intent.putExtra("file_name",(position+1)+".txt");
                intent.putExtra("surapos",surapos);
                startActivity(intent);
            }
        });
        return view;
    }



}
