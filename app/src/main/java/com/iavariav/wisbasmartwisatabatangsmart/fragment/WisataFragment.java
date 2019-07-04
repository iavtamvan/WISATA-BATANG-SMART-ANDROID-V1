package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.annotation.SuppressLint;
import android.media.Image;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.androidstudy.networkmanager.Monitor;
import com.androidstudy.networkmanager.Tovuti;
import com.iavariav.wisbasmartwisatabatangsmart.R;

import java.util.List;

import static android.content.Context.WIFI_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class WisataFragment extends Fragment {


    private TextView tvipString;

    private WebView activityMainWebview;
    private String url;

    private ImageView ivMemuat;
    private ImageView ivKoneksi;

    public WisataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_wisata, container, false);
        initView(view);
        ivMemuat.setVisibility(View.VISIBLE);
//        flWisata.setVisibility(View.GONE);
//        ivKoneksi.setVisibility(View.GONE);

        Tovuti.from(getActivity()).monitor(new Monitor.ConnectivityListener(){
            @Override
            public void onConnectivityChanged(int connectionType, boolean isConnected, boolean isFast){
                // TODO: Handle the connection...
                if (!isConnected) {
                    Toast.makeText(getActivity(), "Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();

                    ivKoneksi.setVisibility(View.VISIBLE);
                    ivMemuat.setVisibility(View.GONE);
                    activityMainWebview.setVisibility(View.GONE);
                } else {
                    ivMemuat.setVisibility(View.GONE);
                    ivKoneksi.setVisibility(View.GONE);
                    activityMainWebview.setVisibility(View.VISIBLE);

//                    Toast.makeText(getActivity(), "Conected", Toast.LENGTH_SHORT).show();
                    url = "https://www.google.com/destination/map/topsights?q=wisata+batang&oq=wisat&aqs=chrome.2.69i57j0j35i39j69i60l3.5306j0j7&sourceid=chrome&ie=UTF-8&tcfs=&dest_src=ts&dest_mid=/m/02pyl6b&sa=X&ved=2ahUKEwjai4-SnprjAhVKtI8KHfKtD30Q6tEBKAQwAHoECAoQBw#dest_mid=/m/02pyl6b&dest_src=ts&tcfs=Eh4KCi9tLzAycHlsNmISEEthYnVwYXRlbiBCYXRhbmc";
                    // Enable Javascript
                    WebSettings webSettings = activityMainWebview.getSettings();
                    webSettings.setJavaScriptEnabled(true);
                    activityMainWebview.loadUrl(url);
                    // TODO handle jumping to chrome
                    activityMainWebview.setWebViewClient(new WebViewClient() {
                        public boolean shouldOverrideUrlLoading(WebView view, String url){
                            // do your handling codes here, which url is the requested url
                            // probably you need to open that url rather than redirect:
                            view.loadUrl(url);
                            return false; // then it is not handled by default action
                        }
                    });
                }
            }
        });

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

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
        activityMainWebview = view.findViewById(R.id.activity_main_webview);
        ivMemuat = view.findViewById(R.id.ivMemuat);
        ivKoneksi = view.findViewById(R.id.ivKoneksi);
    }
}
