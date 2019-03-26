package com.example.mazzam.quraan;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mazzam.quraan.Adapters.RadioRecyclerViewAdapter;
import com.example.mazzam.quraan.Api.ApiManager;
import com.example.mazzam.quraan.Api.Model.RadioResponse;
import com.example.mazzam.quraan.Api.Model.RadiosItem;
import com.example.mazzam.quraan.Base.BaseFrgment;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RadioFragment extends BaseFrgment {
    RecyclerView recyclerView;
    RadioRecyclerViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public RadioFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view= inflater.inflate(R.layout.fragment_radio, container, false);
       recyclerView=view.findViewById(R.id.radioRecyclerView);
       adapter=new RadioRecyclerViewAdapter(null);
       recyclerView.setAdapter(adapter);
       layoutManager=new LinearLayoutManager(activity);
       recyclerView.setLayoutManager(layoutManager);
       getRadioChanel();
        SnapHelper snapHelper=new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.setOnplayclicklitsner(new RadioRecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position, RadiosItem radiosItem) {
                PlayChannel(radiosItem.getURL());
            }
        });
        adapter.setOnstopclicklitsner(new RadioRecyclerViewAdapter.OnItemClickListner() {
            @Override
            public void onItemClick(int position, RadiosItem radiosItem) {
                  stopPlaying();
            }
        });
        return view;
    }

    public void getRadioChanel(){
        showprogressBar();
         ApiManager.getApis().getAllRadioChanels()
                 .enqueue(new Callback<RadioResponse>() {
                     @Override
                     public void onResponse(Call<RadioResponse> call, Response<RadioResponse> response) {
                         hidePrgressBar();
                         if (response.isSuccessful())
                         {
                             adapter.changeData(response.body().getRadios());
                         }
                     }

                     @Override
                     public void onFailure(Call<RadioResponse> call, Throwable t) {
                         hidePrgressBar();
                         showMessage("Error",t.getLocalizedMessage(),"ok");

                     }
                 });
    }
    MediaPlayer mediaPlayer;
    public void PlayChannel(String url)
    {
        stopPlaying();
        mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            showMessage("Erroe","can't reached ","ok");
        }

    }

    public void stopPlaying(){
        if(mediaPlayer!=null)
            mediaPlayer.stop();
    }
}
