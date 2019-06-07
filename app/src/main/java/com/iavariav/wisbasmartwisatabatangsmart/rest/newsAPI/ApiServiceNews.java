package com.iavariav.wisbasmartwisatabatangsmart.rest.newsAPI;

import com.iavariav.wisbasmartwisatabatangsmart.model.LoginModel;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;
import com.iavariav.wisbasmartwisatabatangsmart.model.newsModel.RootNews;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceNews {
    @GET("everything?q=wisata&language=id&apiKey=12850cd010b54441aaeff6749dc99cd0")
    Call<RootNews> getNews();


}