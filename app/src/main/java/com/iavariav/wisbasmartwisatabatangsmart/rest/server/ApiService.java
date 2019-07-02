package com.iavariav.wisbasmartwisatabatangsmart.rest.server;

import com.iavariav.wisbasmartwisatabatangsmart.fragment.berita.KeluhanFragment;
import com.iavariav.wisbasmartwisatabatangsmart.model.KeluhanBeritaModel;
import com.iavariav.wisbasmartwisatabatangsmart.model.LoginModel;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;
import com.iavariav.wisbasmartwisatabatangsmart.model.UmkmModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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


    @FormUrlEncoded
    @POST("api_keluhan.php")
    Call<ResponseErrorModel> postKeluhan
            (@Field("id_account") String id_account,
             @Field("gambar_keluhan") String gambar_keluhan,
             @Field("face_pelaporan_keluhan") String face_pelaporan_keluhan,
             @Field("nama_keluhan") String nama_keluhan,
             @Field("deskripsi_keluhan") String deskripsi_keluhan,
             @Field("jenis_keluhan") String jenis_keluhan,
             @Field("lat_keluhan") String lat_keluhan,
             @Field("long_keluhan") String long_keluhan);


    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<KeluhanBeritaModel>> getKeluhan
            (@Field("pindah") String pindah);
    @FormUrlEncoded
    @POST("api_get.php")
    Call<ArrayList<UmkmModel>> getInformasiUMKM
            (@Field("pindah") String pindah);

    @FormUrlEncoded
    @POST("api_tambah_umkm.php")
    Call<ResponseBody> postUMKM
            (
                    @Field("id_account") String id_account,
                    @Field("nama_umkm") String nama_umkm,
                    @Field("gambar_thumbnail_umkm") String gambar_thumbnail_umkm,
                    @Field("alamat_umkm") String alamat_umkm,
                    @Field("jarak_umkm") String jarak_umkm,
                    @Field("lat_umkm") double lat_umkm,
                    @Field("long_umkm") double long_umkm,
                    @Field("gambar_1_umkm") String gambar_1_umkm,
                    @Field("gambar_2_umkm") String gambar_2_umkm,
                    @Field("detail_deskripsi_umkm") String detail_deskripsi_umkm
            );
    @FormUrlEncoded
    @POST("api_update_umkm.php")
    Call<ResponseBody> updateUMKM
            (
                    @Field("id_umkm") String id_account,
                    @Field("nama_umkm") String nama_umkm,
                    @Field("gambar_thumbnail_umkm") String gambar_thumbnail_umkm,
                    @Field("alamat_umkm") String alamat_umkm,
                    @Field("lat_umkm") String jarak_umkm,
                    @Field("long_umkm") double lat_umkm,
                    @Field("detail_deskripsi_umkm") double long_umkm
            );
    @FormUrlEncoded
    @POST("api_delete_umkm.php")
    Call<ResponseBody> deleteUMKM
            (
                    @Field("id_umkm") String id_umkm
            );

    @GET("api_like.php")
    Call<ResponseErrorModel> likeKeluhan
            (@Query("id_keluhan") String id_keluhan);

    @GET("api_dislike.php")
    Call<ResponseErrorModel> disLikeKeluhan
            (@Query("id_keluhan") String id_keluhan);

}
