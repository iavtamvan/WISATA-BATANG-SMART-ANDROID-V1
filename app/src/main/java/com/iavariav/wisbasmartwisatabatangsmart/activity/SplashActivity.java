package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                String username = sp.getString(Config.SHARED_EMAIL_ACCOUNT, "");

                // TODO jika belum masuk ke LoginActivity
                if (username.equalsIgnoreCase("") || TextUtils.isEmpty(username)){
                    finishAffinity();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                // TODO jika sudah nantinya akan masuk ke Home
                else {
                    finishAffinity();
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                }
            }
        }, 2000);
    }
}
