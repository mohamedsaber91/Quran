package com.example.quran.APIS;

import com.example.quran.APIS.Model.RadioResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apis {

     @GET("radios//radio_arabic.json")
    Call<RadioResponse> getRadioChannel();
}
