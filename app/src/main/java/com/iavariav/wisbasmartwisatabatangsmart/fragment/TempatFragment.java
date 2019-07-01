package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.metode.Haversine;
import com.iavariav.wisbasmartwisatabatangsmart.rest.googleMapsAPI.ApiClientGoogle;
import com.iavariav.wisbasmartwisatabatangsmart.rest.googleMapsAPI.ApiServiceGoogle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * A simple {@link Fragment} subclass.
 */
public class TempatFragment extends Fragment {


    private LinearLayout div;
    static double PI_RAD = Math.PI / 180.0;
    private double distance;
    private double longitude;
    private double latitude;

    private ImageView ivMemuat;

    public TempatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tempat, container, false);
        initView(view);
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        longitude = location.getLongitude();
        latitude = location.getLatitude();

//        getdataWisata();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        getdataWisata();
    }

    @Override
    public void onResume() {
        super.onResume();
        getdataWisata();
    }

    private void getdataWisata() {
        ApiServiceGoogle apiServiceGoogle = ApiClientGoogle.getInstanceRetrofit();
        apiServiceGoogle.getDataWisata()
                .enqueue(new Callback<ResponseBody>() {
                    @SuppressLint("SetTextI18n")
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
//                                Toast.makeText(getActivity(), "" + jsonObject, Toast.LENGTH_SHORT).show();
                                JSONArray jsonArray = jsonObject.optJSONArray("results");
//                                Toast.makeText(getActivity(), "" + jsonArray, Toast.LENGTH_SHORT).show();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObjectResult = jsonArray.optJSONObject(i);
                                    final String formatted_address = jsonObjectResult.optString("formatted_address"); // untuk alamat
                                    JSONObject objectGeometruy = jsonObjectResult.optJSONObject("geometry");
                                    JSONObject objectGeometryLocation = objectGeometruy.optJSONObject("location"); // untuk lat long
                                    double locLat = objectGeometryLocation.optDouble("lat"); // posisi lat
                                    double locLong = objectGeometryLocation.optDouble("lng"); // posisi long
                                    getDistance(latitude, longitude, locLat, locLong);

                                    String namaTempat = jsonObjectResult.optString("name"); // nama tempat

                                    JSONObject objectOpening = jsonObjectResult.optJSONObject("opening_hours");
//                                    JSONObject open_now = objectOpening.optJSONObject("open_now");

//                                    Toast.makeText(getActivity(), "" + open_now, Toast.LENGTH_SHORT).show();

                                    JSONArray arrayPhotos = jsonObjectResult.optJSONArray("photos");
                                    int height = 0;
                                    int width = 0;
                                    String photo_reference = null;
                                    for (int j = 0; j < arrayPhotos.length(); j++) {
                                        JSONObject masukPhotos = arrayPhotos.optJSONObject(j);
                                        height = masukPhotos.optInt("height");
                                        width = masukPhotos.optInt("width");
                                        photo_reference = masukPhotos.optString("photo_reference");
                                    }
                                    int rating = jsonObjectResult.optInt("rating");
                                    int user_ratings_total = jsonObjectResult.optInt("user_ratings_total");

//                                    Toast.makeText(getActivity(), "" + photo_reference, Toast.LENGTH_SHORT).show();

                                    LayoutInflater layoutInflater = (LayoutInflater) Objects.requireNonNull(getActivity()).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                    if (layoutInflater== null) {
                                        Log.d(TAG, "onResponse: Eror");
                                    } else {
                                        ivMemuat.setVisibility(View.GONE);
                                        View view = layoutInflater.inflate(R.layout.list_tempat, null);

                                        final LinearLayout divDirectMap;
                                        final TextView tvJarak;
                                        final TextView tvAlamat;
                                        final TextView tvNamaTempat;
                                        final ImageView ivIcon;

                                        ivIcon = view.findViewById(R.id.ivIcon);
                                        Glide.with(getActivity()).load("https://maps.googleapis.com/maps/api/place/photo?photoreference=" + photo_reference + "&maxheight=3120&maxwidth=4160&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM").override(512, 512).error(R.drawable.logo_h128).into(ivIcon);
                                        tvNamaTempat = view.findViewById(R.id.tvNamaTempat);
                                        tvNamaTempat.setText(namaTempat);
                                        tvAlamat = view.findViewById(R.id.tvAlamat);
                                        tvAlamat.setText(formatted_address);

                                        tvJarak = view.findViewById(R.id.tvJarak);
//                                    tvJarak.setText(String.valueOf(distance));
                                        double hitungJarak = Haversine.hitungJarak(latitude, longitude, locLat, locLong);
                                        tvJarak.setText(String.format("%.2f", hitungJarak) + " km");


                                        divDirectMap = view.findViewById(R.id.divDirectMap);
                                        divDirectMap.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=+"+formatted_address));
                                                startActivity(intent);
                                            }
                                        });


                                        div.addView(view);
                                    }


                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getActivity(), "" + Config.ERROR_LOAD, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public Double getDistance(Double firstLat, Double firstLong, Double secondLat, Double secondLong){

        double phi1 = firstLat * PI_RAD;

        double phi2 = secondLat * PI_RAD;

        double lam1 = firstLong * PI_RAD;

        double lam2 = secondLong * PI_RAD;


        distance = 6371.01 * acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(lam2 - lam1));


        return distance;

    }


    private void initView(View view) {
        div = view.findViewById(R.id.div);
        ivMemuat = view.findViewById(R.id.ivMemuat);
    }
}
