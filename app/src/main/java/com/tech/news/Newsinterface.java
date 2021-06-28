package com.tech.news;

import com.tech.news.ModelClasses.MainResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Newsinterface {

    @GET("/v2/top-headlines?country=in&apiKey=9150f6e80f654775afa1d7b80a99942e")

    Call<MainResponse> getNewsData(@Query("category") String category);




}

