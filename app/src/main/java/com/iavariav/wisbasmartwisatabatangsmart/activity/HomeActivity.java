package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.fragment.AsmaFragment;
import com.iavariav.wisbasmartwisatabatangsmart.fragment.berita.HomeBeritaFragment;
import com.iavariav.wisbasmartwisatabatangsmart.fragment.TempatFragment;
import com.iavariav.wisbasmartwisatabatangsmart.fragment.UmkmFragment;
import com.iavariav.wisbasmartwisatabatangsmart.fragment.WisataFragment;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class HomeActivity extends AppCompatActivity {
    private static final int RC_CAMERA_AND_LOCATION = 1;
    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_wisata:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerFragment, new WisataFragment()).commit();
                    return true;
                case R.id.navigation_tempat:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerFragment, new TempatFragment()).commit();
                    return true;
                case R.id.navigation_berita:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerFragment, new HomeBeritaFragment()).commit();
                    return true;
                case R.id.navigation_asma:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerFragment, new AsmaFragment()).commit();
                    return true;
                case R.id.navigation_umkm:
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.containerFragment, new UmkmFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        methodRequiresTwoPermission();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.containerFragment, new WisataFragment()).commit();
    }

    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.app_name),
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

}
