package com.iavariav.wisbasmartwisatabatangsmart.rest.googleMapsAPI;

import com.iavariav.wisbasmartwisatabatangsmart.model.LoginModel;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServiceGoogle {

    @GET("textsearch/json?query=wisata%20batang&language=id&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM")
    Call<ResponseBody> getDataWisata(
//            @Query("next_page_token") String next_page_token
    );

    @GET("details/json?placeid=ChIJV4NYbtgTcC4RL_f2KosHLZQ&language=id&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM")
    Call<ResponseBody> getDataDetailWisata();

    @GET("photo")
    Call<ResponseBody> getPhotoReference(
            @Query("photoreference") String photoreference,
            @Query("maxheight") String maxheight,
            @Query("maxwidth") String maxwidth,
            @Query("key") String key);
}