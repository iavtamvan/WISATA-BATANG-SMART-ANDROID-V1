package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;

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
        Config.methodRequiresTwoPermission(this);
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
                                    SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(Config.SHARED_ID_ACCOUNT,loginModel.getIdAccount());
                                    editor.putString(Config.SHARED_REGISTERED,loginModel.getRegistered());
                                    editor.putString(Config.SHARED_NAMA_ACCOUNT,loginModel.getNamaAccount());
                                    editor.putString(Config.SHARED_EMAIL_ACCOUNT,loginModel.getEmailAccount());
                                    editor.putString(Config.SHARED_NO_HP_ACCOUNT,loginModel.getNoHpAccount());
                                    editor.putString(Config.SHARED_NIK_ACCOUNT,loginModel.getNikAccount());
                                    editor.putString(Config.SHARED_ALAMAT_ACCOUNT,loginModel.getAlamatAccount());
                                    editor.putString(Config.SHARED_AGAMA_ACCOUNT,loginModel.getAgamaAccount());
                                    editor.putString(Config.SHARED_JABATAN_ACCOUNT,loginModel.getJabatanAccount());
                                    editor.putString(Config.SHARED_KOTA_ACCOUNT,loginModel.getKotaAccount());
                                    editor.putString(Config.SHARED_KAB_ACCOUNT,loginModel.getKabAccount());
                                    editor.putString(Config.SHARED_FOTO_FRONT_ACCOUNT,loginModel.getFotoFrontAccount());
                                    editor.putString(Config.SHARED_FOTO_BACK_ACCOUNT,loginModel.getFotoBackAccount());
                                    editor.putString(Config.SHARED_TTD_ACCOUNT,loginModel.getTtdAccount());
                                    editor.putString(Config.SHARED_LAT_ACCOUNT,loginModel.getLatAccount());
                                    editor.putString(Config.SHARED_LONG_ACCOUNT,loginModel.getLongAccount());
                                    editor.putString(Config.SHARED_STATUS_ACCOUNT,loginModel.getStatusAccount());

                                    editor.apply();

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
