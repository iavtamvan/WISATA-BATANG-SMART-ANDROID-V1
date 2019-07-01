package com.iavariav.wisbasmartwisatabatangsmart.activity.umkm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;

public class DetailUMKMActivity extends AppCompatActivity {
    private String registered;
    private String nama_umkm;
    private String gambar_thumbnail_umkm;
    private String alamat_umkm;
    private String jarak_umkm;
    private String lat_umkm;
    private String long_umkm;
    private String gambar_1_umkm;
    private String gambar_2_umkm;
    private String detail_deskripsi_umkm;
    private String like_umkm;
    private String dislike_umkm;
    private String kategori_umkm;
    private String status_umkm;
    private AppBarLayout appBar;
    private CollapsingToolbarLayout toolbarLayout;
    private Toolbar toolbar;
    private ImageView ivUMKMDisplayTop;
    private TextView tvUMKMAlamat;
    private TextView tvUMKMJarak;
    private TextView tvUMKMDeskripsi;
    private FloatingActionButton fab;
    private ImageView ivEditUMKM;
    private ImageView ivHapusUMKM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_umkm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        registered = getIntent().getStringExtra(Config.BUNDLE_REGISTERED);
        nama_umkm = getIntent().getStringExtra(Config.BUNDLE_NAMA_UMKM);
        gambar_thumbnail_umkm = getIntent().getStringExtra(Config.BUNDLE_GAMBAR_THUMBNAIL_UMKM);
        alamat_umkm = getIntent().getStringExtra(Config.BUNDLE_ALAMAT_UMKM);
        jarak_umkm = getIntent().getStringExtra(Config.BUNDLE_JARAK_UMKM);
        lat_umkm = getIntent().getStringExtra(Config.BUNDLE_LAT_UMKM);
        long_umkm = getIntent().getStringExtra(Config.BUNDLE_LONG_UMKM);
        gambar_1_umkm = getIntent().getStringExtra(Config.BUNDLE_GAMBAR_1_UMKM);
        gambar_2_umkm = getIntent().getStringExtra(Config.BUNDLE_GAMBAR_2_UMKM);
        detail_deskripsi_umkm = getIntent().getStringExtra(Config.BUNDLE_DETAIL_DESKRIPSI_UMKM);
        like_umkm = getIntent().getStringExtra(Config.BUNDLE_LIKE_UMKM);
        dislike_umkm = getIntent().getStringExtra(Config.BUNDLE_DISLIKE_UMKM);
        kategori_umkm = getIntent().getStringExtra(Config.BUNDLE_KATEGORI_UMKM);
        status_umkm = getIntent().getStringExtra(Config.BUNDLE_STATUS_UMKM);

        toolbar.setTitle(nama_umkm);
        Glide.with(this).load(gambar_thumbnail_umkm).error(R.drawable.logo_h128).override(512, 512).into(ivUMKMDisplayTop);
        tvUMKMAlamat.setText(alamat_umkm);
        tvUMKMJarak.setText(jarak_umkm);
        tvUMKMDeskripsi.setText(detail_deskripsi_umkm);

        ivEditUMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailUMKMActivity.this, DaftarActivity.class);
                intent.putExtra(Config.BUNDLE_STATUS_EDIT, "edit");
                intent.putExtra(Config.BUNDLE_REGISTERED, registered);
                intent.putExtra(Config.BUNDLE_NAMA_UMKM, nama_umkm);
                intent.putExtra(Config.BUNDLE_GAMBAR_THUMBNAIL_UMKM, gambar_thumbnail_umkm);
                intent.putExtra(Config.BUNDLE_ALAMAT_UMKM, alamat_umkm);
                intent.putExtra(Config.BUNDLE_JARAK_UMKM, jarak_umkm);
                intent.putExtra(Config.BUNDLE_LAT_UMKM, lat_umkm);
                intent.putExtra(Config.BUNDLE_LONG_UMKM, long_umkm);
                intent.putExtra(Config.BUNDLE_GAMBAR_1_UMKM, gambar_1_umkm);
                intent.putExtra(Config.BUNDLE_GAMBAR_2_UMKM, gambar_2_umkm);
                intent.putExtra(Config.BUNDLE_DETAIL_DESKRIPSI_UMKM, detail_deskripsi_umkm);
                intent.putExtra(Config.BUNDLE_LIKE_UMKM, like_umkm);
                intent.putExtra(Config.BUNDLE_DISLIKE_UMKM, dislike_umkm);
                intent.putExtra(Config.BUNDLE_KATEGORI_UMKM, kategori_umkm);
                intent.putExtra(Config.BUNDLE_STATUS_UMKM, status_umkm);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        appBar = findViewById(R.id.app_bar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        toolbar = findViewById(R.id.toolbar);
        ivUMKMDisplayTop = findViewById(R.id.ivUMKMDisplayTop);
        tvUMKMAlamat = findViewById(R.id.tvUMKMAlamat);
        tvUMKMJarak = findViewById(R.id.tvUMKMJarak);
        tvUMKMDeskripsi = findViewById(R.id.tvUMKMDeskripsi);
        fab = findViewById(R.id.fab);
        ivEditUMKM = findViewById(R.id.ivEditUMKM);
        ivHapusUMKM = findViewById(R.id.ivHapusUMKM);
    }
}
