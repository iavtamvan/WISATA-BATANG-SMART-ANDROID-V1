package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsmaFragment extends Fragment {


    private ImageView ivKeluhan;
    private EditText edtNamaKeluhan;
    private Spinner spnJenisKeluhan;
    private EditText edtDeskripsiKeluhan;
    private Button btnSendKeluhan;

    private String idAccount;

    private double longitude;
    private double latitude;

    private ResponseErrorModel responseErrorModel;


    private SharedPreferences sharedPreferences;

    public AsmaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_asma, container, false);
        initView(view);
        idAccount = "4";

        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();


        btnSendKeluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ApiClient.getInstanceRetrofit();
                apiService.postKeluhan(idAccount, "https://asset.kompas.com/crop/15x54:770x557/750x500/data/photo/2019/02/24/1104711726.jpeg"
                        , "https://assets-a1.kompasiana.com/items/album/2018/11/21/foto-pria-ganteng-indonesia-21-5bf5756faeebe169634adeb4.jpg?t=o&v=800"
                        , edtNamaKeluhan.getText().toString().trim(), edtDeskripsiKeluhan.getText().toString().trim(), "Wisata"
                        , String.valueOf(latitude), String.valueOf(longitude))
                        .enqueue(new Callback<ResponseErrorModel>() {
                            @Override
                            public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                                responseErrorModel = response.body();
                                if (response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "" + responseErrorModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Error load", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                                Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        return view;
    }

    private void initView(View view) {
        ivKeluhan = view.findViewById(R.id.ivKeluhan);
        edtNamaKeluhan = view.findViewById(R.id.edtNamaKeluhan);
        spnJenisKeluhan = view.findViewById(R.id.spnJenisKeluhan);
        edtDeskripsiKeluhan = view.findViewById(R.id.edtDeskripsiKeluhan);
        btnSendKeluhan = view.findViewById(R.id.btnSendKeluhan);
    }
}
