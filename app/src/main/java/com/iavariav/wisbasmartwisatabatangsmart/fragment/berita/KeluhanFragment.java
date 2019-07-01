package com.iavariav.wisbasmartwisatabatangsmart.fragment.berita;


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
import com.iavariav.wisbasmartwisatabatangsmart.adapter.BeritaKeluhanAdapter;
import com.iavariav.wisbasmartwisatabatangsmart.model.KeluhanBeritaModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeluhanFragment extends Fragment {
    private ArrayList<KeluhanBeritaModel> keluhanBeritaModels;
    private RecyclerView rv;
    private BeritaKeluhanAdapter beritaKeluhanAdapter;

    private ImageView ivMemuat;

    public KeluhanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_keluhan, container, false);
        initView(view);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataKeluhan();
    }

    @Override
    public void onPause() {
        super.onPause();
        getDataKeluhan();
    }

    private void getDataKeluhan() {
        ApiService apiService = ApiClient.getInstanceRetrofit();
        apiService.getKeluhan("getDataKeluhan")
                .enqueue(new Callback<ArrayList<KeluhanBeritaModel>>() {
                    @Override
                    public void onResponse(Call<ArrayList<KeluhanBeritaModel>> call, Response<ArrayList<KeluhanBeritaModel>> response) {
                        if (response.isSuccessful()){
                            ivMemuat.setVisibility(View.GONE);
                            keluhanBeritaModels = response.body();
                            beritaKeluhanAdapter = new BeritaKeluhanAdapter(getActivity(), keluhanBeritaModels);
                            rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                            rv.setAdapter(beritaKeluhanAdapter);
                            beritaKeluhanAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<KeluhanBeritaModel>> call, Throwable t) {
                        ivMemuat.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rv);
        ivMemuat= view.findViewById(R.id.ivMemuat);
    }
}
