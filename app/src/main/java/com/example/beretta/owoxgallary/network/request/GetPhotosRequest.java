package com.example.beretta.owoxgallary.network.request;

import com.example.beretta.owoxgallary.models.network.response.PhotoRest;
import com.example.beretta.owoxgallary.models.network.response.SearchResponse;
import com.example.beretta.owoxgallary.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by beretta on 12.10.2017.
 */

public interface GetPhotosRequest {
    @GET("photos")
    Call<List<PhotoRest>> listPhotos(@Query("client_id") String clientId);


    @GET("search/photos")
    Call<SearchResponse> searchPhotos(@Query("client_id") String clientId, @Query("query") String query);
}
