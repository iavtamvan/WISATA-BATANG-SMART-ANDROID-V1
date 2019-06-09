package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;

public class DetailNewsServerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvDetailNamaKeluhan;
    private TextView tvDetailTanggalOlehStatusKeluhan;
    private TextView tvDetailDeskripsiKeluhan;

    private String nama_keluhan;
    private String image;
    private String jenis_keluhan;
    private String deskripsi_keluhan;
    private String tanggal_oleh_status;
    private ImageView ivDetailKeluhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news_server);
        initView();
        setSupportActionBar(toolbar);

        nama_keluhan = getIntent().getStringExtra(Config.BUNDLE_NAMA_KELUHAN);
        image = getIntent().getStringExtra(Config.BUNDLE_IMAGE);
        jenis_keluhan = getIntent().getStringExtra(Config.BUNDLE_JENIS_KELUHAN);
        deskripsi_keluhan = getIntent().getStringExtra(Config.BUNDLE_DESKRIPSI_KELUHAN);
        tanggal_oleh_status = getIntent().getStringExtra(Config.BUNDLE_TANGGAL_OLEH_STATUS);

        toolbar.setTitle("Detail Keluhan");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        tvDetailNamaKeluhan.setText(nama_keluhan);
        Glide.with(this).load(image).override(512, 512).error(R.drawable.logo_h128).into(ivDetailKeluhan);
        tvDetailTanggalOlehStatusKeluhan.setText(tanggal_oleh_status);
        tvDetailDeskripsiKeluhan.setText(deskripsi_keluhan);


    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        tvDetailNamaKeluhan = findViewById(R.id.tvDetailNamaKeluhan);
        tvDetailTanggalOlehStatusKeluhan = findViewById(R.id.tvDetailTanggalOlehStatusKeluhan);
        tvDetailDeskripsiKeluhan = findViewById(R.id.tvDetailDeskripsiKeluhan);
        ivDetailKeluhan = findViewById(R.id.ivDetailKeluhan);
    }
}
