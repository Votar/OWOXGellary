package com.example.beretta.owoxgallary.network;

import com.example.beretta.owoxgallary.network.request.GetPhotosRequest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by beretta on 12.10.2017.
 */

public class ApiUnsplash {
    private static final ApiUnsplash ourInstance = new ApiUnsplash();

    static public ApiUnsplash getInstance() {
        return ourInstance;
    }

    public GetPhotosRequest getService() {
        return service;
    }

    private GetPhotosRequest service;

    private ApiUnsplash() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();

        client.addInterceptor(loggingInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

        service = retrofit.create(GetPhotosRequest.class);
    }


}
