package com.example.mazzam.quraan;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.mazzam.quraan.Adapters.SuraRecyclerViewAdapter;
import com.example.mazzam.quraan.Base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SuraActivity extends BaseActivity {
    TextView suraTv;
    RecyclerView recyclerView;
    SuraRecyclerViewAdapter adapter;
    ArrayList<String>suracontent;
    RecyclerView.LayoutManager layoutManager;
    String suraName,fileName,surapos;
    int counter=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sura);
        suraTv=findViewById(R.id.sura_name_tv);
        recyclerView=findViewById(R.id.sura_recyclerView);
       suraName= getIntent().getStringExtra("sura_name");
       fileName=getIntent().getStringExtra("file_name");
       surapos=getIntent().getStringExtra("surapos");
       suraTv.setText(suraName);
       ReadFile(fileName);
      adapter=new SuraRecyclerViewAdapter(suracontent);
      adapter.setOnAyaClickListner(new SuraRecyclerViewAdapter.OnItemClickListner() {
          @Override
          public void onItemClick(int position, String aya) {
              String url;
              String ayapos=position +1+"";
              if (surapos.length()==1) {
                  surapos = "00" + surapos;
              }
              else if(surapos.length()==2)
              {
                  surapos="0"+surapos;
              }
              else if(surapos.length()==3)
              {
                  surapos=""+surapos;
              }

              if (ayapos.length()==1) {
                  ayapos = "00" + ayapos;
              }
              else if(ayapos.length()==2)
              {
                  ayapos="0"+ayapos;
              }
              else if(ayapos.length()==3)
              {
                  ayapos=""+ayapos;
              }
       url="https://verse.mp3quran.net/data/Abu_Bakr_Ash-Shaatree_64kbps/"+surapos+ayapos+".mp3";
              stopPlaying();
              PlayChannel(url);
          }
      });
      layoutManager=new LinearLayoutManager(this);
      recyclerView.setAdapter(adapter);
      recyclerView.setLayoutManager(layoutManager);
    }

   ArrayList<String>  ReadFile(String fileName)
   {
       suracontent=new ArrayList<>();
       BufferedReader reader;
       try{
           final InputStream file = getAssets().open(fileName);
           reader = new BufferedReader(new InputStreamReader(file));
           String line = reader.readLine();
           while(line != null){
               if (line.trim().isEmpty())
               {
                   break;
               }
               //Log.d("StackOverflow", line);
               suracontent.add(line+" "+"("+counter+")");
               line = reader.readLine();
               counter++;
           }
       } catch(IOException ioe){
           ioe.printStackTrace();
       }
    return suracontent;
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
