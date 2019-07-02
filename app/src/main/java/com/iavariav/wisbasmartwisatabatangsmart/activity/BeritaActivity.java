package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.fragment.berita.BeritaFragment;
import com.iavariav.wisbasmartwisatabatangsmart.fragment.berita.KeluhanFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class BeritaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                BeritaActivity.this.getSupportFragmentManager(), FragmentPagerItems.with(BeritaActivity.this)
                .add("Berita Keluhan", KeluhanFragment.class)
                .add("Berita Wisata", BeritaFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);

    }
}
