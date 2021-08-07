package com.halitsever.MoldDetector.api.service;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface MoldDetectorApiService {
    @Headers("Content-Type: application/json")
    @POST("/api/imageprocess")
    Call<ImageData> createTask(@Body String task);

    @GET("/api/statistics")
    Call<Statistics> getStatistics();
}