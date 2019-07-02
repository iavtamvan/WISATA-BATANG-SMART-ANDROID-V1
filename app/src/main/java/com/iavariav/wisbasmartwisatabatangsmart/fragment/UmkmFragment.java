package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.activity.BeritaActivity;
import com.iavariav.wisbasmartwisatabatangsmart.activity.ProfilActivity;
import com.iavariav.wisbasmartwisatabatangsmart.activity.umkm.DaftarActivity;
import com.iavariav.wisbasmartwisatabatangsmart.activity.umkm.InformasiUMKMActivity;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UmkmFragment extends Fragment {

    private String status;
    private LinearLayout divcontainerKontenKosong;
    private LinearLayout divcontainerKonten;
    private CardView cvKlikInformasi;
    private CardView cvKlikLogout;
    private ImageView ivUMKMInformasi;
    private TextView tvUMKMInformasi;
    private CardView cvKlikdaftar;
    private CardView cvKlikProfil;
    private ImageView ivUMKMDaftar;
    private TextView tvUMKMDaftar;

    public UmkmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_umkm, container, false);
        initView(view);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
        status = sharedPreferences.getString(Config.SHARED_STATUS_ACCOUNT, "");
//        Toast.makeText(getActivity(), "" + status, Toast.LENGTH_SHORT).show();
        cvKlikLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finishAffinity();
                Config.logout(getActivity());
            }
        });

//        if (status.contains("umum")) {
//            divcontainerKontenKosong.setVisibility(View.VISIBLE);
//
//        } else {
            //TODO Tampilkan seluruh data
            divcontainerKontenKosong.setVisibility(View.GONE);
            divcontainerKonten.setVisibility(View.VISIBLE);

            cvKlikdaftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (status.contains("Umum")) {
                        Toast.makeText(getActivity(), "Maaf Hanya Untuk Pedagang", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(getActivity(), DaftarActivity.class));
                    }

                }
            });

            cvKlikInformasi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), BeritaActivity.class));
                }
            });

            cvKlikProfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), ProfilActivity.class));
                }
            });



//        }
        return view;
    }

    private void initView(View view) {
        divcontainerKontenKosong = view.findViewById(R.id.divcontainerKontenKosong);
        divcontainerKonten = view.findViewById(R.id.divcontainerKonten);
        cvKlikInformasi = view.findViewById(R.id.cvKlikInformasi);
        cvKlikLogout = view.findViewById(R.id.cvKlikLogout);
        ivUMKMInformasi = view.findViewById(R.id.ivUMKMInformasi);
        tvUMKMInformasi = view.findViewById(R.id.tvUMKMInformasi);
        cvKlikdaftar = view.findViewById(R.id.cvKlikdaftar);
        cvKlikProfil = view.findViewById(R.id.cvKlikProfil);
        ivUMKMDaftar = view.findViewById(R.id.ivUMKMDaftar);
        tvUMKMDaftar = view.findViewById(R.id.tvUMKMDaftar);
    }
}
