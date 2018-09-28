package com.example.beretta.owoxgallary.network

import com.example.beretta.owoxgallary.network.request.GetPhotosRequest

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUnsplash {

    val service: GetPhotosRequest

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()

        client.addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build()

        service = retrofit.create(GetPhotosRequest::class.java)
    }


}
