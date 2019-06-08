package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.an.deviceinfo.location.DeviceLocation;
import com.an.deviceinfo.location.LocationInfo;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;
import com.iavariav.wisbasmartwisatabatangsmart.rest.uploadImage.APIServiceUploadImage;
import com.iavariav.wisbasmartwisatabatangsmart.rest.uploadImage.Result;
import com.iavariav.wisbasmartwisatabatangsmart.rest.uploadImage.RetroClientUploadImage;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AsmaFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView ivKeluhan;
    private EditText edtNamaKeluhan;
    private Spinner spnJenisKeluhan;
    private EditText edtDeskripsiKeluhan;
    private Button btnSendKeluhan;

    private String idAccount;
    private String imagePath;
    private String getNameImage;

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

//        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
//        @SuppressLint("MissingPermission") Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        longitude = location.getLongitude();
//        latitude = location.getLatitude();
        LocationInfo locationInfo = new LocationInfo(getActivity());
        DeviceLocation location = locationInfo.getLocation();
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        ivKeluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                startActivityForResult(intent, 1213);
            }
        });


        btnSendKeluhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtDeskripsiKeluhan.getText().toString().isEmpty() || edtDeskripsiKeluhan.getText().toString().isEmpty()){
                    imagePath = "";
                    new FancyAlertDialog.Builder(getActivity())
                            .setTitle("Lengkapi keluhan kamu dulu, baru dikirim ya.")
                            .setBackgroundColor(Color.parseColor("#FFFFFFFF"))  //Don't pass R.color.colorvalue
//                            .setMessage("Mau melaporkan lagi ?")
//                                    .setNegativeBtnText("Tidak")
//                                    .setPositiveBtnBackground(Color.parseColor("#E52028"))  //Don't pass R.color.colorvalue
//                                    .setPositiveBtnText("Ya")
//                                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                            .setAnimation(Animation.SLIDE)
                            .isCancellable(true)
                            .setIcon(R.drawable.logo_h64, Icon.Visible)
//                                    .OnPositiveClicked(new FancyAlertDialogListener() {
//                                        @Override
//                                        public void OnClick() {
//                                            edtNamaKeluhan.setText("");
//                                            edtDeskripsiKeluhan.setText("");
//                                        }
//                                    })
//                                    .OnNegativeClicked(new FancyAlertDialogListener() {
//                                        @Override
//                                        public void OnClick() {
//                                            edtNamaKeluhan.setText("");
//                                            edtDeskripsiKeluhan.setText("");
//                                        }
//                                    })
                            .build();
                } else  {
                    uploadImage();
                }
            }
        });


        return view;
    }

    private void sendData() {
        ApiService apiService = ApiClient.getInstanceRetrofit();
        apiService.postKeluhan(idAccount, "http://devlop.can.web.id/uploads/client_profile_images/3/" + getNameImage
                , "https://assets-a1.kompasiana.com/items/album/2018/11/21/foto-pria-ganteng-indonesia-21-5bf5756faeebe169634adeb4.jpg?t=o&v=800"
                , edtNamaKeluhan.getText().toString().trim(), edtDeskripsiKeluhan.getText().toString().trim(), "Wisata"
                , String.valueOf(latitude), String.valueOf(longitude))
                .enqueue(new Callback<ResponseErrorModel>() {
                    @Override
                    public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                        responseErrorModel = response.body();
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "" + responseErrorModel.getErrorMsg(), Toast.LENGTH_SHORT).show();
                            edtNamaKeluhan.setText("");
                            edtDeskripsiKeluhan.setText("");
                            new FancyAlertDialog.Builder(getActivity())
                                    .setTitle("Terima kasih telah melaporakan keadaan")
                                    .setBackgroundColor(Color.parseColor("#FFFFFFFF"))  //Don't pass R.color.colorvalue
                                    .setMessage("Mau melaporkan lagi ?")
//                                    .setNegativeBtnText("Tidak")
//                                    .setPositiveBtnBackground(Color.parseColor("#E52028"))  //Don't pass R.color.colorvalue
//                                    .setPositiveBtnText("Ya")
//                                    .setNegativeBtnBackground(Color.parseColor("#FFA9A7A8"))  //Don't pass R.color.colorvalue
                                    .setAnimation(Animation.SLIDE)
                                    .isCancellable(true)
                                    .setIcon(R.drawable.logo_h64, Icon.Visible)
//                                    .OnPositiveClicked(new FancyAlertDialogListener() {
//                                        @Override
//                                        public void OnClick() {
//                                            edtNamaKeluhan.setText("");
//                                            edtDeskripsiKeluhan.setText("");
//                                        }
//                                    })
//                                    .OnNegativeClicked(new FancyAlertDialogListener() {
//                                        @Override
//                                        public void OnClick() {
//                                            edtNamaKeluhan.setText("");
//                                            edtDeskripsiKeluhan.setText("");
//                                        }
//                                    })
                                    .build();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            String filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);

            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/wisba_keluhan");
            myDir.mkdirs();

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fname = "wisba_" + timeStamp + ".jpg";

            File file = new File(myDir, fname);
            imagePath = String.valueOf(file);
            if (file.exists()) file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            edtNamaKeluhan.setText(String.valueOf("" + file));
            ivKeluhan.setImageBitmap(selectedImage);
        }
    }

    private void uploadImage() {

        final ProgressDialog p;
        p = new ProgressDialog(getActivity());
        p.setMessage("Upload foto");
        p.show();

        APIServiceUploadImage s = RetroClientUploadImage.getService();

        File f = new File(imagePath);
        if (f==null) {Log.d(TAG, "uploadImage: erroor files");}
        getNameImage = f.getName();
        Log.d("", "uploadImage: " + f.getName());


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);

        MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postIMmage(part);
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                p.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("success")) {
                        Toast.makeText(getActivity(), "Sukses Imagenya", Toast.LENGTH_SHORT).show();
                        sendData();
                    } else {

                    }
                } else {
                    Toast.makeText(getActivity(), "Upload Image Gagal", Toast.LENGTH_SHORT).show();
                }

                imagePath = "";
//                te.setVisibility(View.VISIBLE);
//                imageVi.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                p.dismiss();


            }
        });
    }

    private void initView(View view) {
        ivKeluhan = view.findViewById(R.id.ivKeluhan);
        edtNamaKeluhan = view.findViewById(R.id.edtNamaKeluhan);
        spnJenisKeluhan = view.findViewById(R.id.spnJenisKeluhan);
        edtDeskripsiKeluhan = view.findViewById(R.id.edtDeskripsiKeluhan);
        btnSendKeluhan = view.findViewById(R.id.btnSendKeluhan);
    }
}
