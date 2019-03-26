package com.example.mazzam.quraan;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mazzam.quraan.Base.BaseFrgment;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasbehFragment extends BaseFrgment {
    int allahAkbr_counter=0;
    int sobhanAllah_counter=0;
    int total_tasbehat=0;
    ImageView sebhaImage;
    ImageView resetImage;
    TextView tasebha_tv_counter;
    TextView kol_eltasebhat_tv_counter;
    Spinner mySpinner;
    View view;
    Button save_btn;
    Button load_btn;
    SharedPreferences myshared;

    public TasbehFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       view=inflater.inflate(R.layout.fragment_tasbeh, container, false);
       sebhaImage=view.findViewById(R.id.sebeha_image);
       resetImage=view.findViewById(R.id.reset_image);
       tasebha_tv_counter=view.findViewById(R.id.tasebha_tv_counter);
       kol_eltasebhat_tv_counter=view.findViewById(R.id.kol_eltasebhat_tv_counter);
       save_btn=view.findViewById(R.id.save_btn);
       load_btn=view.findViewById(R.id.load_btn);
        myshared= getContext().getSharedPreferences("myFile",Context.MODE_PRIVATE);
        mySpinner=view.findViewById(R.id.myspinner);
        tasebha_tv_counter.setText(""+0);
        kol_eltasebhat_tv_counter.setText(""+0);
       mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (position==0)
               {

                   sebhaImage.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           allahAkbr_counter++;
                         tasebha_tv_counter.setText(""+allahAkbr_counter);
                         total_tasbehat=allahAkbr_counter+sobhanAllah_counter;
                         kol_eltasebhat_tv_counter.setText(""+total_tasbehat);
                       }
                   });


               }
               else
               sebhaImage.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           sobhanAllah_counter++;
                           tasebha_tv_counter.setText(""+sobhanAllah_counter);
                           total_tasbehat=allahAkbr_counter+sobhanAllah_counter;
                           kol_eltasebhat_tv_counter.setText(""+total_tasbehat);
                       }
                   });
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
       save_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               SharedPreferences.Editor myedit=myshared.edit();
               mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                   @Override
                   public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                   }

                   @Override
                   public void onNothingSelected(AdapterView<?> parent) {

                   }
               });
           }
       });


      resetImage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              allahAkbr_counter=0;
              sobhanAllah_counter=0;
              tasebha_tv_counter.setText(""+allahAkbr_counter);
              kol_eltasebhat_tv_counter.setText(""+allahAkbr_counter);
          }
      });


        return view;
    }

}
