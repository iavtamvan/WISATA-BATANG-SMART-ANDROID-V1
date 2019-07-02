package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.activity.umkm.InformasiUMKMActivity;
import com.iavariav.wisbasmartwisatabatangsmart.adapter.InformasiUMKMAdapter;
import com.iavariav.wisbasmartwisatabatangsmart.model.UmkmModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class InformasiUMKMFragment extends Fragment {

    private RecyclerView rvUMKMInformasi;
    private InformasiUMKMAdapter informasiUMKMAdapter;
    private ArrayList<UmkmModel> umkmModels;

    private ImageView ivMemuat;
    public InformasiUMKMFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_informasi_umkm, container, false);
        initView(view);
        umkmModels = new ArrayList<>();
        getInformasiUMKM();
        return view;
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
                            rvUMKMInformasi.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            informasiUMKMAdapter = new InformasiUMKMAdapter(getActivity(), umkmModels);
                            informasiUMKMAdapter.notifyDataSetChanged();
                            rvUMKMInformasi.setAdapter(informasiUMKMAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<UmkmModel>> call, Throwable t) {
                        ivMemuat.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView(View view) {
        rvUMKMInformasi = view.findViewById(R.id.rvUMKMInformasi);
        ivMemuat = view.findViewById(R.id.ivMemuat);
    }

}
