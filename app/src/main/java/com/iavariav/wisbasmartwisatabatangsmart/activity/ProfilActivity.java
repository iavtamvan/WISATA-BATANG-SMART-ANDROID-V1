package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private String id_account;
    private String registered;
    private String nama_account;
    private String email_account;
    private String no_hp_account;
    private String nik_account;
    private String alamat_account;
    private String agama_account;
    private String jabatan_account;
    private String kota_account;
    private String kab_account;
    private String foto_front_account;
    private String foto_back_account;
    private String ttd_account;
    private String lat_account;
    private String long_account;
    private String status_account;
    private String username;
    private CircleImageView icUserProfile;
    private TextView tvUsername;
    private TextView tvNamaAkun;
    private TextView tvEmailAkun;
    private TextView tvNoHp;
    private TextView tvAlamatAKun;
    private TextView tvStatusAKun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        getSupportActionBar().hide();
        initView();

        sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);

        id_account = sharedPreferences.getString(Config.SHARED_ID_ACCOUNT, "");
        registered = sharedPreferences.getString(Config.SHARED_REGISTERED, "");
        nama_account = sharedPreferences.getString(Config.SHARED_NAMA_ACCOUNT, "");
        email_account = sharedPreferences.getString(Config.SHARED_EMAIL_ACCOUNT, "");
        no_hp_account = sharedPreferences.getString(Config.SHARED_NO_HP_ACCOUNT, "");
        nik_account = sharedPreferences.getString(Config.SHARED_NIK_ACCOUNT, "");
        alamat_account = sharedPreferences.getString(Config.SHARED_ALAMAT_ACCOUNT, "");
        agama_account = sharedPreferences.getString(Config.SHARED_AGAMA_ACCOUNT, "");
        jabatan_account = sharedPreferences.getString(Config.SHARED_JABATAN_ACCOUNT, "");
        kota_account = sharedPreferences.getString(Config.SHARED_KOTA_ACCOUNT, "");
        kab_account = sharedPreferences.getString(Config.SHARED_KAB_ACCOUNT, "");
        foto_front_account = sharedPreferences.getString(Config.SHARED_FOTO_FRONT_ACCOUNT, "");
        foto_back_account = sharedPreferences.getString(Config.SHARED_FOTO_BACK_ACCOUNT, "");
        ttd_account = sharedPreferences.getString(Config.SHARED_TTD_ACCOUNT, "");
        lat_account = sharedPreferences.getString(Config.SHARED_LAT_ACCOUNT, "");
        long_account = sharedPreferences.getString(Config.SHARED_LONG_ACCOUNT, "");
        status_account = sharedPreferences.getString(Config.SHARED_STATUS_ACCOUNT, "");
        username = sharedPreferences.getString(Config.SHARED_USERNAME, "");

        Glide.with(ProfilActivity.this).load(foto_front_account).error(R.drawable.logo_h128).override(512, 512).into(icUserProfile);
        tvNamaAkun.setText(nama_account);
        tvUsername.setText(username);
        tvEmailAkun.setText(email_account);
        tvNoHp.setText(no_hp_account);
        tvAlamatAKun.setText(kota_account + ", " + kab_account+ ", " + alamat_account);
        tvStatusAKun.setText(status_account);


    }

    private void initView() {
        icUserProfile = findViewById(R.id.icUserProfile);
        tvUsername = findViewById(R.id.tvUsername);
        tvNamaAkun = findViewById(R.id.tvNamaAkun);
        tvEmailAkun = findViewById(R.id.tvEmailAkun);
        tvNoHp = findViewById(R.id.tvNoHp);
        tvAlamatAKun = findViewById(R.id.tvAlamatAKun);
        tvStatusAKun = findViewById(R.id.tvStatusAKun);
    }
}
