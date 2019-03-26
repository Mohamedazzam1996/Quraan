package com.example.mazzam.quraan.Api;

import com.example.mazzam.quraan.Api.ApiCalls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {

    private static  Retrofit retrofitInstance;

   private static Retrofit getInstance() {
        if(retrofitInstance==null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl("http://mp3quran.net/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

   public static ApiCalls getApis(){

      ApiCalls apiCalls=getInstance().create(ApiCalls.class);
     return apiCalls;
   }

}
