package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.activity.umkm.DaftarActivity;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.model.LoginModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;

import java.util.HashMap;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_CAMERA_AND_LOCATION = 1;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    private LoginModel loginModel;
    private TextView tvDaftar;
    private SliderLayout slider;
    private PagerIndicator customIndicator;

    private ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        getSupportActionBar().hide();
        Config.methodRequiresTwoPermission(this);
//        methodRequiresTwoPermission();
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Taxi", R.drawable.sidney);
        file_maps.put("Laut", R.drawable.jean);
        file_maps.put("Jembatan Buntu", R.drawable.jembatanbuntu);
        file_maps.put("Curug", R.drawable.natapmasdep);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            slider.addSlider(textSliderView);
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(3000);

        tvDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsername.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Lengkapi Akun Anda", Toast.LENGTH_SHORT).show();
                } else {
                    login();
                }

            }
        });
    }

    private void login() {
        p = new ProgressDialog(LoginActivity.this);
        p.setMessage("Mencari Data");
        p.show();
        ApiService apiService = ApiClient.getInstanceRetrofit();
        apiService.postLogin(edtUsername.getText().toString(), edtPassword.getText().toString())
                .enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        loginModel = response.body();
                        if (response.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "" + loginModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                            if (loginModel.getErrorMsg().contains("Login Sukses")) {
                                p.dismiss();
                                SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(Config.SHARED_ID_ACCOUNT, loginModel.getIdAccount());
                                editor.putString(Config.SHARED_REGISTERED, loginModel.getRegistered());
                                editor.putString(Config.SHARED_NAMA_ACCOUNT, loginModel.getNamaAccount());
                                editor.putString(Config.SHARED_EMAIL_ACCOUNT, loginModel.getEmailAccount());
                                editor.putString(Config.SHARED_NO_HP_ACCOUNT, loginModel.getNoHpAccount());
                                editor.putString(Config.SHARED_NIK_ACCOUNT, loginModel.getNikAccount());
                                editor.putString(Config.SHARED_ALAMAT_ACCOUNT, loginModel.getAlamatAccount());
                                editor.putString(Config.SHARED_AGAMA_ACCOUNT, loginModel.getAgamaAccount());
                                editor.putString(Config.SHARED_JABATAN_ACCOUNT, loginModel.getJabatanAccount());
                                editor.putString(Config.SHARED_KOTA_ACCOUNT, loginModel.getKotaAccount());
                                editor.putString(Config.SHARED_KAB_ACCOUNT, loginModel.getKabAccount());
                                editor.putString(Config.SHARED_FOTO_FRONT_ACCOUNT, loginModel.getFotoFrontAccount());
                                editor.putString(Config.SHARED_FOTO_BACK_ACCOUNT, loginModel.getFotoBackAccount());
                                editor.putString(Config.SHARED_TTD_ACCOUNT, loginModel.getTtdAccount());
                                editor.putString(Config.SHARED_LAT_ACCOUNT, loginModel.getLatAccount());
                                editor.putString(Config.SHARED_LONG_ACCOUNT, loginModel.getLongAccount());
                                editor.putString(Config.SHARED_STATUS_ACCOUNT, loginModel.getStatusAccount());
                                editor.putString(Config.SHARED_USERNAME, loginModel.getUsername());

                                editor.apply();

                                finishAffinity();
                                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                            } else  {
                                p.dismiss();
                                Toast.makeText(LoginActivity.this, "" + loginModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            p.dismiss();
                            Toast.makeText(LoginActivity.this, "" + Config.ERROR_LOGIN_GAGAL, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        p.dismiss();
                        Toast.makeText(LoginActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvDaftar = findViewById(R.id.tvDaftar);
        slider = findViewById(R.id.slider);
        customIndicator = findViewById(R.id.custom_indicator);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
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
