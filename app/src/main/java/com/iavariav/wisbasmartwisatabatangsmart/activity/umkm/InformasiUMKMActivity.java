package com.iavariav.wisbasmartwisatabatangsmart.activity.umkm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.adapter.InformasiUMKMAdapter;
import com.iavariav.wisbasmartwisatabatangsmart.model.UmkmModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiUMKMActivity extends AppCompatActivity {

    private RecyclerView rvUMKMInformasi;
    private InformasiUMKMAdapter informasiUMKMAdapter;
    private ArrayList<UmkmModel> umkmModels;

    private ImageView ivMemuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_umkm);
        initView();
        umkmModels = new ArrayList<>();
        getInformasiUMKM();
    }

    private void getInformasiUMKM() {
        ApiService apiService = ApiClient.getInstanceRetrofit();
        apiService.getInformasiUMKM("getDataUmkm")
                .enqueue(new Callback<ArrayList<UmkmModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<UmkmModel>> call, Response<ArrayList<UmkmModel>> response) {
                        if (response.isSuccessful()){
                            ivMemuat.setVisibility(View.GONE);
                            umkmModels = response.body();
                            rvUMKMInformasi.setLayoutManager(new LinearLayoutManager(InformasiUMKMActivity.this, LinearLayoutManager.VERTICAL, false));
                            informasiUMKMAdapter = new InformasiUMKMAdapter(InformasiUMKMActivity.this, umkmModels);
                            informasiUMKMAdapter.notifyDataSetChanged();
                            rvUMKMInformasi.setAdapter(informasiUMKMAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<UmkmModel>> call, Throwable t) {
                        ivMemuat.setVisibility(View.GONE);
                        Toast.makeText(InformasiUMKMActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView() {
        rvUMKMInformasi = findViewById(R.id.rvUMKMInformasi);
        ivMemuat = findViewById(R.id.ivMemuat);
    }
}
