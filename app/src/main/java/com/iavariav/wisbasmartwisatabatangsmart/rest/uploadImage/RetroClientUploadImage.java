package com.iavariav.wisbasmartwisatabatangsmart.rest.uploadImage;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LENOVO on 26/10/2017.
 */

public class RetroClientUploadImage {


//    private static final String ROOT_URL = "http://inssang.can.web.id/images/";
    private static final String ROOT_URL = "http://devlop.can.web.id/uploads/client_profile_images/3/";

    public RetroClientUploadImage(){
    }

    private static Retrofit getRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static APIServiceUploadImage getService(){
        return getRetrofitClient().create(APIServiceUploadImage.class);
    }
}
