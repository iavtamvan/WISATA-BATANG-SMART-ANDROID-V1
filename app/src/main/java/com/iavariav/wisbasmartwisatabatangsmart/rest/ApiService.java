package com.iavariav.wisbasmartwisatabatangsmart.rest;

import com.iavariav.wisbasmartwisatabatangsmart.model.LoginModel;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("api_login.php")
    Call<LoginModel> postLogin(@Field("username") String username,
                               @Field("password") String password);


    @FormUrlEncoded
    @POST("api_register.php")
    Call<ResponseErrorModel> postRegisterAwal
            (@Field("nama_account") String nama_account,
             @Field("email_account") String email_account,
             @Field("no_hp_account") String no_hp_account,
             @Field("foto_front_account") String foto_front_account,
             @Field("foto_back_account") String foto_back_account,
             @Field("ttd_account") String ttd_account,
             @Field("lat_account") String lat_account,
             @Field("long_account") String long_account,
             @Field("username") String username,
             @Field("password") String password);


}