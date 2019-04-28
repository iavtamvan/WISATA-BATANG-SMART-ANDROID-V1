package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.model.LoginModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    private LoginModel loginModel;
    private TextView tvDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ApiClient.getInstanceRetrofit();
                apiService.postLogin(edtUsername.getText().toString(), edtPassword.getText().toString())
                        .enqueue(new Callback<LoginModel>() {
                            @Override
                            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                                loginModel = response.body();
                                if (response.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "" + loginModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                    finishAffinity();
                                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                } else {
                                    Toast.makeText(LoginActivity.this, "" + Config.ERROR_LOGIN_GAGAL, Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<LoginModel> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void initView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvDaftar = findViewById(R.id.tvDaftar);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
