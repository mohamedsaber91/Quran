package com.example.quran.APIS;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManger {


    private static Retrofit retrofitInstance;
    private static Retrofit getInstance(){
        if (retrofitInstance == null){
            retrofitInstance =  new Retrofit.Builder()
                    .baseUrl("http://api.mp3quran.net/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }


            return retrofitInstance;
    }

    public static Apis getApis(){
        return getInstance().create(Apis.class);
    }
}
