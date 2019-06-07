package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UmkmFragment extends Fragment {

    private String status;
    private LinearLayout divcontainerKontenKosong;
    private LinearLayout divcontainerKonten;

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
        Toast.makeText(getActivity(), "" + status, Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(), "" + status, Toast.LENGTH_SHORT).show();
        if (status.contains("umum")) {
            divcontainerKontenKosong.setVisibility(View.VISIBLE);

        } else {
            //TODO Tampilkan seluruh data
            divcontainerKontenKosong.setVisibility(View.GONE);
        }
        return view;
    }

    private void initView(View view) {
        divcontainerKontenKosong = view.findViewById(R.id.divcontainerKontenKosong);
        divcontainerKonten = view.findViewById(R.id.divcontainerKonten);
    }
}
