package com.example.beretta.owoxgallary.network.request;

import com.example.beretta.owoxgallary.models.network.response.DownloadLinkResult;
import com.example.beretta.owoxgallary.models.network.response.PhotoRest;
import com.example.beretta.owoxgallary.models.network.response.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by beretta on 12.10.2017.
 */

public interface GetPhotosRequest {
    @GET("photos")
    Call<List<PhotoRest>> listPhotos(@Query("client_id") String clientId, @Query("page") int page);


    @GET("search/photos")
    Call<SearchResponse> searchPhotos(@Query("client_id") String clientId, @Query("query") String query, @Query("page") int page);

    @GET("photos/{id}/download")
    Call<DownloadLinkResult> getPhotosLink(@Path("id") String photoId, @Query("client_id") String clientId);
}
