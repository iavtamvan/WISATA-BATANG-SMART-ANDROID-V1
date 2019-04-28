package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.rest.ApiService;
import com.iavariav.wisbasmartwisatabatangsmart.rest.googleMapsAPI.ApiClientGoogle;
import com.iavariav.wisbasmartwisatabatangsmart.rest.googleMapsAPI.ApiServiceGoogle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TempatFragment extends Fragment {


    public TempatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tempat, container, false);

        getdataWisata();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        getdataWisata();
    }

    private void getdataWisata() {
        ApiServiceGoogle apiServiceGoogle = ApiClientGoogle.getInstanceRetrofit();
        apiServiceGoogle.getDataWisata()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonObject = new JSONObject(response.body().string());
//                                Toast.makeText(getActivity(), "" + jsonObject, Toast.LENGTH_SHORT).show();
                                JSONArray jsonArray = jsonObject.optJSONArray("results");
//                                Toast.makeText(getActivity(), "" + jsonArray, Toast.LENGTH_SHORT).show();
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObjectResult = jsonArray.optJSONObject(i);
                                    String formatted_address = jsonObjectResult.optString("formatted_address"); // untuk alamat
                                    JSONObject objectGeometruy = jsonObjectResult.optJSONObject("geometry");
                                    JSONObject objectGeometryLocation = objectGeometruy.optJSONObject("location"); // untuk lat long
                                    String locLat = objectGeometryLocation.optString("lat"); // posisi lat
                                    String locLong = objectGeometryLocation.optString("long"); // posisi long

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


}
