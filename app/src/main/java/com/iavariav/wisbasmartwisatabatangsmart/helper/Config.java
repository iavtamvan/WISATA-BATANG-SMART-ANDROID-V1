package com.iavariav.wisbasmartwisatabatangsmart.helper;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.activity.LoginActivity;

import pub.devrel.easypermissions.EasyPermissions;

import static android.content.Context.MODE_PRIVATE;

public final class Config {

    public static final String ERROR_FORM = "Lengkapi akun anda";
    public static final String ERROR_LOGIN_GAGAL = "Login gagal";
    public static final String ERROR_LOAD = "Cek koneksi anda";
    public static final String ERROR_PERMISSION_SUCCES = "Permission Sukses";
    public static final String ERROR_PERMISSION_FAIL = "Permission Gagal";
    public static final String ERROR_PENUKARAN_BARANG = "Penukaran_barang";

    public static final String SHARED_NAME = "WISBA";
    public static final String SHARED_ID_ACCOUNT = "id_account";
    public static final String SHARED_REGISTERED = "registered";
    public static final String SHARED_NAMA_ACCOUNT = "nama_account";
    public static final String SHARED_EMAIL_ACCOUNT = "email_account";
    public static final String SHARED_NO_HP_ACCOUNT = "no_hp_account";
    public static final String SHARED_NIK_ACCOUNT = "nik_account";
    public static final String SHARED_ALAMAT_ACCOUNT = "alamat_account";
    public static final String SHARED_AGAMA_ACCOUNT = "agama_account";
    public static final String SHARED_JABATAN_ACCOUNT = "jabatan_account";
    public static final String SHARED_KOTA_ACCOUNT = "kota_account";
    public static final String SHARED_KAB_ACCOUNT = "kab_account";
    public static final String SHARED_FOTO_FRONT_ACCOUNT = "foto_front_account";
    public static final String SHARED_FOTO_BACK_ACCOUNT = "foto_back_account";
    public static final String SHARED_TTD_ACCOUNT = "ttd_account";
    public static final String SHARED_LAT_ACCOUNT = "lat_account";
    public static final String SHARED_LONG_ACCOUNT = "long_account";
    public static final String SHARED_STATUS_ACCOUNT = "status_account";
    public static final String SHARED_USERNAME = "username_account";


    public static final String BUNDLE_URL_NEWS = "bundle_url_news";
    public static final String BUNDLE_NAMA_KELUHAN = "bundle_nama_keluhan";
    public static final String BUNDLE_IMAGE = "bundle_image";
    public static final String BUNDLE_JENIS_KELUHAN = "bundle_jenis_keluhan";
    public static final String BUNDLE_DESKRIPSI_KELUHAN = "bundle_deskripsi_keluhan";
    public static final String BUNDLE_TANGGAL_OLEH_STATUS = "bundle_tanggal_oleh_status";

    public static final String BUNDLE_STATUS_EDIT = "bundle_status_edit";
    public static final String BUNDLE_ID_UMKM = "bundle_id_umkm";
    public static final String BUNDLE_REGISTERED = "bundle_registered";
    public static final String BUNDLE_NAMA_UMKM = "bundle_nama_umkm";
    public static final String BUNDLE_GAMBAR_THUMBNAIL_UMKM = "bundle_gambar_thumbnail_umkm";
    public static final String BUNDLE_ALAMAT_UMKM = "bundle_alamat_umkm";
    public static final String BUNDLE_JARAK_UMKM = "bundle_jarak_umkm";
    public static final String BUNDLE_LAT_UMKM = "bundle_lat_umkm";
    public static final String BUNDLE_LONG_UMKM = "bundle_long_umkm";
    public static final String BUNDLE_GAMBAR_1_UMKM = "bundle_gambar_1_umkm";
    public static final String BUNDLE_GAMBAR_2_UMKM = "bundle_gambar_2_umkm";
    public static final String BUNDLE_DETAIL_DESKRIPSI_UMKM = "bundle_detail_deskripsi_umkm";
    public static final String BUNDLE_LIKE_UMKM = "bundle_like_umkm";
    public static final String BUNDLE_DISLIKE_UMKM = "bundle_dislike_umkm";
    public static final String BUNDLE_KATEGORI_UMKM = "bundle_kategori_umkm";
    public static final String BUNDLE_STATUS_UMKM = "bundle_status_umkm";



    private static final int RC_CAMERA_AND_LOCATION = 100;
    public static final void methodRequiresTwoPermission(Context context) {
        String[] perms = {
                Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
        };
        if (EasyPermissions.hasPermissions(context, perms)) {
            Log.d("Perms", "methodRequiresTwoPermission: Succes Perms");
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions((Activity) context, context.getString(R.string.app_name),
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    public static double longitude;
    public static double latitude;
    public static final void location(Context context){
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();

        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_LAT_ACCOUNT, String.valueOf(latitude));
        editor.putString(SHARED_LONG_ACCOUNT, String.valueOf(longitude));
        editor.apply();
    }

    public static String longitudeRead;
    public static String latitudeRead;
    public static final void readLocation(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
        latitudeRead = sharedPreferences.getString(Config.SHARED_LAT_ACCOUNT, "");
        longitudeRead = sharedPreferences.getString(Config.SHARED_LONG_ACCOUNT, "");
    }

    public static void logout(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_NAME, "");
        editor.putString(SHARED_ID_ACCOUNT, "");
        editor.putString(SHARED_REGISTERED, "");
        editor.putString(SHARED_NAMA_ACCOUNT, "");
        editor.putString(SHARED_EMAIL_ACCOUNT, "");
        editor.putString(SHARED_NO_HP_ACCOUNT, "");
        editor.putString(SHARED_NIK_ACCOUNT, "");
        editor.putString(SHARED_ALAMAT_ACCOUNT, "");
        editor.putString(SHARED_AGAMA_ACCOUNT, "");
        editor.putString(SHARED_JABATAN_ACCOUNT, "");
        editor.putString(SHARED_KOTA_ACCOUNT, "");
        editor.putString(SHARED_KAB_ACCOUNT, "");
        editor.putString(SHARED_FOTO_FRONT_ACCOUNT, "");
        editor.putString(SHARED_FOTO_BACK_ACCOUNT, "");
        editor.putString(SHARED_TTD_ACCOUNT, "");
        editor.putString(SHARED_LAT_ACCOUNT, "");
        editor.putString(SHARED_LONG_ACCOUNT, "");
        editor.putString(SHARED_STATUS_ACCOUNT, "");
        editor.apply();

        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
