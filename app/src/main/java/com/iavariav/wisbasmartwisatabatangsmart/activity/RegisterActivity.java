package com.iavariav.wisbasmartwisatabatangsmart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtNamaLengkap;
    private EditText edtEmail;
    private EditText edtNoHp;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnRegister;

    private ResponseErrorModel responseErrorModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ApiClient.getInstanceRetrofit();
                apiService.postRegisterAwal(edtNamaLengkap.getText().toString(), edtEmail.getText().toString(), edtNoHp.getText().toString()
                ,"https://asset-a.grid.id/crop/0x0:0x0/700x465/photo/2018/08/07/2985630265.jpg",
                        "https://cdn.brilio.net/news/2018/05/30/143480/thumbnail-sophia-latjuba-pamer-foto-mesra-bareng-cowok-ganteng-kekasih-baru-180530l.jpg",
                        "https://cdn2.boombastis.com/wp-content/uploads/2018/02/Tanda-tangan-dengan-garis-bawah.jpg",
                        "-9999", "873783", edtUsername.getText().toString(), edtPassword.getText().toString())
                        .enqueue(new Callback<ResponseErrorModel>() {
                            @Override
                            public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                                responseErrorModel = response.body();
                                if (response.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this, "" + responseErrorModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                    edtNamaLengkap.setText("");
                                    edtEmail.setText("");
                                    edtUsername.setText("");
                                    edtPassword.setText("");
                                    finishAffinity();
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                }
                                else {
                                    Toast.makeText(RegisterActivity.this, "" + responseErrorModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                                Toast.makeText(RegisterActivity.this, "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }

    private void initView() {
        edtNamaLengkap = findViewById(R.id.edtNamaLengkap);
        edtEmail = findViewById(R.id.edtEmail);
        edtNoHp = findViewById(R.id.edtNoHp);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegister = findViewById(R.id.btnRegister);
    }
}
