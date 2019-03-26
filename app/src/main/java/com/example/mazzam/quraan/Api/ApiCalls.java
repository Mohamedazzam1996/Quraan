package com.example.mazzam.quraan.Api;

import com.example.mazzam.quraan.Api.Model.RadioResponse;
import com.example.mazzam.quraan.Api.Model.RadiosItem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCalls {

    @GET("radio/radio_ar.json")
    Call<RadioResponse> getAllRadioChanels();
}
