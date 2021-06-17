package com.firestudio.projectwordfinding.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private RetrofitClient() {
    }

    public static Retrofit INSTANCE = null;

    public static Retrofit getRetrofitInstance(String BaseUrl) {
        if (INSTANCE == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

            INSTANCE = new Retrofit.Builder().baseUrl(BaseUrl).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return INSTANCE;
    }
}

