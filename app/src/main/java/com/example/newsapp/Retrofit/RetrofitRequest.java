package com.example.newsapp.Retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.newsapp.Constants.AppConstants.BASE_URL;

public class RetrofitRequest {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
