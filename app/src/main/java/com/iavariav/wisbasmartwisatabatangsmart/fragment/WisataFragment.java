package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.annotation.SuppressLint;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.an.deviceinfo.device.model.App;
import com.an.deviceinfo.device.model.Battery;
import com.an.deviceinfo.device.model.Device;
import com.an.deviceinfo.device.model.Memory;
import com.an.deviceinfo.device.model.Network;
import com.an.deviceinfo.location.DeviceLocation;
import com.an.deviceinfo.location.LocationInfo;
import com.an.deviceinfo.userapps.UserAppInfo;
import com.an.deviceinfo.userapps.UserApps;
import com.an.deviceinfo.usercontacts.UserContactInfo;
import com.an.deviceinfo.usercontacts.UserContacts;
import com.iavariav.wisbasmartwisatabatangsmart.R;

import java.util.List;

import static android.content.Context.WIFI_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class WisataFragment extends Fragment {


    private TextView tvipString;

    public WisataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_wisata, container, false);
        initView(view);
        getIpAddr();
        return view;
    }

    private void getIpAddr() {
        // TODO Device Info
//        Device device = new Device(getActivity());
        // TODO Location Info
//        LocationInfo locationInfo = new LocationInfo(getActivity());
//        DeviceLocation location = locationInfo.getLocation();
        // TODO App Info
//        App app = new App(getActivity());
        // TODO Battery Info
//        Battery battery = new Battery(getActivity());
        // TODO Memory
//        Memory memory = new Memory(getActivity());
        // TODO Network
//        Network network = new Network(getActivity());
        // TODO User Install Apps
//        UserAppInfo userAppInfo = new UserAppInfo(getActivity());
//        List<UserApps> userApps = userAppInfo.getInstalledApps((boolean includeSystemApps));
        // TODO Uer Contact Info
//        UserContactInfo userContactInfo = new UserContactInfo(getActivity());
//        List<UserContacts> userContacts = userContactInfo.getContacts();

//        tvipString.setText(network.getOperator());

    }

    private void initView(View view) {
        tvipString = view.findViewById(R.id.ipString);
    }
}
