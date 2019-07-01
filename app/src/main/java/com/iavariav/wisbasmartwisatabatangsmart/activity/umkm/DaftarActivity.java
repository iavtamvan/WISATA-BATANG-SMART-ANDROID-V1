package com.iavariav.wisbasmartwisatabatangsmart.activity.umkm;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.an.deviceinfo.location.DeviceLocation;
import com.an.deviceinfo.location.LocationInfo;
import com.bumptech.glide.Glide;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;
import com.iavariav.wisbasmartwisatabatangsmart.rest.uploadImage.APIServiceUploadImage;
import com.iavariav.wisbasmartwisatabatangsmart.rest.uploadImage.Result;
import com.iavariav.wisbasmartwisatabatangsmart.rest.uploadImage.RetroClientUploadImage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class DaftarActivity extends AppCompatActivity {


    private String idAccount;
    private String status;
    private String imagePath;
    private String jarak;
    private String statusEdit;

    private String registered;
    private String nama_umkm;
    private String gambar_thumbnail_umkm;
    private String alamat_umkm;
    private String jarak_umkm;
    private String lat_umkm;
    private String long_umkm;
    private String gambar_1_umkm;
    private String gambar_2_umkm;
    private String detail_deskripsi_umkm;
    private String like_umkm;
    private String dislike_umkm;
    private String kategori_umkm;
    private String status_umkm;

    private double longitude;
    private double latitude;


    private ImageView ivUmkm;
    private EditText edtNamaUMKM;
    private EditText edtUMKMAlamat;
    private EditText edtUMKMDeskripsi;
    private Spinner spnUMKMKategori;
    private ImageView ivResult;
    private FrameLayout cameraPreview;
    private Button btnSendUmkm;
    private Button btnUpdateUMKM;
    private String getNameImage;

    private ProgressDialog p;

    private ResponseErrorModel responseErrorModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        initView();
        SharedPreferences sp = getSharedPreferences(Config.SHARED_NAME, MODE_PRIVATE);
        idAccount = sp.getString(Config.SHARED_ID_ACCOUNT, "");
        status = sp.getString(Config.SHARED_STATUS_ACCOUNT, "");
        statusEdit = getIntent().getStringExtra(Config.BUNDLE_STATUS_EDIT);
        if (statusEdit != null && !statusEdit.isEmpty()){
            btnSendUmkm.setVisibility(View.GONE);
            btnUpdateUMKM.setVisibility(View.VISIBLE);

            registered = getIntent().getStringExtra(Config.BUNDLE_REGISTERED);
            nama_umkm = getIntent().getStringExtra(Config.BUNDLE_NAMA_UMKM);
            gambar_thumbnail_umkm = getIntent().getStringExtra(Config.BUNDLE_GAMBAR_THUMBNAIL_UMKM);
            alamat_umkm = getIntent().getStringExtra(Config.BUNDLE_ALAMAT_UMKM);
            jarak_umkm = getIntent().getStringExtra(Config.BUNDLE_JARAK_UMKM);
            lat_umkm = getIntent().getStringExtra(Config.BUNDLE_LAT_UMKM);
            long_umkm = getIntent().getStringExtra(Config.BUNDLE_LONG_UMKM);
            gambar_1_umkm = getIntent().getStringExtra(Config.BUNDLE_GAMBAR_1_UMKM);
            gambar_2_umkm = getIntent().getStringExtra(Config.BUNDLE_GAMBAR_2_UMKM);
            detail_deskripsi_umkm = getIntent().getStringExtra(Config.BUNDLE_DETAIL_DESKRIPSI_UMKM);
            like_umkm = getIntent().getStringExtra(Config.BUNDLE_LIKE_UMKM);
            dislike_umkm = getIntent().getStringExtra(Config.BUNDLE_DISLIKE_UMKM);
            kategori_umkm = getIntent().getStringExtra(Config.BUNDLE_KATEGORI_UMKM);
            status_umkm = getIntent().getStringExtra(Config.BUNDLE_STATUS_UMKM);

            Glide.with(DaftarActivity.this).load(gambar_thumbnail_umkm).override(512, 512).error(R.drawable.logo_h128).into(ivUmkm);
            edtNamaUMKM.setText(nama_umkm);
            edtUMKMAlamat.setText(alamat_umkm);
            edtUMKMDeskripsi.setText(detail_deskripsi_umkm);
        } else  {
            btnSendUmkm.setVisibility(View.VISIBLE);
            btnUpdateUMKM.setVisibility(View.GONE);
        }

        LocationInfo locationInfo = new LocationInfo(this);
        DeviceLocation location = locationInfo.getLocation();
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        ivUmkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarActivity.this, ImageSelectActivity.class);
                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, false);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
                startActivityForResult(intent, 1213);
            }
        });

        btnUpdateUMKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNamaUMKM.getText().toString().isEmpty() || edtUMKMAlamat.getText().toString().isEmpty() || edtUMKMDeskripsi.getText().toString().isEmpty()) {
                    Toast.makeText(DaftarActivity.this, "Lengkapi Isian Anda", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImageUpdate();
                }
            }
        });

        btnSendUmkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNamaUMKM.getText().toString().isEmpty() || edtUMKMAlamat.getText().toString().isEmpty() || edtUMKMDeskripsi.getText().toString().isEmpty()) {
                    Toast.makeText(DaftarActivity.this, "Lengkapi Isian Anda", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage();
                }
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
            File myDir = new File(root + "/wisba_umkm");
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
//            edtNamaKeluhan.setText(String.valueOf("" + file));
            ivUmkm.setImageBitmap(selectedImage);
        }
    }


    private void postUMKM() {
        ApiService apiService = ApiClient.getInstanceRetrofit();
        apiService.postUMKM(idAccount, edtNamaUMKM.getText().toString().trim(), "http://devlop.can.web.id/uploads/client_profile_images/3/" + getNameImage, edtUMKMAlamat.getText().toString().trim(),
                jarak, latitude, longitude, "", "", edtUMKMDeskripsi.getText().toString().trim(),
                "")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject object = new JSONObject(response.body().string());
                                String error_msg = object.optString("error_msg");
                                Toast.makeText(DaftarActivity.this, "" + error_msg, Toast.LENGTH_SHORT).show();

                                edtNamaUMKM.setText("");
                                edtUMKMAlamat.setText("");
                                edtUMKMDeskripsi.setText("");
                                Glide.with(DaftarActivity.this).load(R.drawable.upload_gambar_v1_256).error(R.drawable.logo_h128).into(ivUmkm);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DaftarActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void uploadImage() {
        p = new ProgressDialog(DaftarActivity.this);
        p.setMessage("Mengirim Data");
        p.show();
        APIServiceUploadImage s = RetroClientUploadImage.getService();

        File f = new File(imagePath);
        if (f == null) {
            Log.d(TAG, "uploadImage: erroor files");
        }
        getNameImage = f.getName();
        Log.d("", "uploadImage: " + f.getName());


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);

        MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postIMmage(part);
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("success")) {
                        Toast.makeText(DaftarActivity.this, "Sukses Imagenya keluhabn", Toast.LENGTH_SHORT).show();
                        postUMKM();
                        p.dismiss();
                    } else {
                    }
                } else {
                    Toast.makeText(DaftarActivity.this, "Upload Image Gagal", Toast.LENGTH_SHORT).show();
                }

                imagePath = "";
//                te.setVisibility(View.VISIBLE);
//                imageVi.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(DaftarActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                p.dismiss();


            }
        });
    }

    private void uploadImageUpdate() {
        p = new ProgressDialog(DaftarActivity.this);
        p.setMessage("Mengirim Data");
        p.show();
        APIServiceUploadImage s = RetroClientUploadImage.getService();

        File f = new File(imagePath);
        if (f == null) {
            Log.d(TAG, "uploadImage: erroor files");
        }
        getNameImage = f.getName();
        Log.d("", "uploadImage: " + f.getName());


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);

        MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postIMmage(part);
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResult().equals("success")) {
                        Toast.makeText(DaftarActivity.this, "Sukses Imagenya keluhabn", Toast.LENGTH_SHORT).show();
                        updateUMKM();
                        p.dismiss();
                    } else {
                    }
                } else {
                    Toast.makeText(DaftarActivity.this, "Upload Image Gagal", Toast.LENGTH_SHORT).show();
                }

                imagePath = "";
//                te.setVisibility(View.VISIBLE);
//                imageVi.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(DaftarActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                p.dismiss();


            }
        });
    }
    private void updateUMKM() {
        ApiService apiService = ApiClient.getInstanceRetrofit();
        apiService.updateUMKM(idAccount, edtNamaUMKM.getText().toString().trim(), getNameImage, edtUMKMAlamat.getText().toString().trim(), jarak, latitude, longitude)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject object = new JSONObject(response.body().string());
                                String error_msg = object.optString("error_msg");
                                Toast.makeText(DaftarActivity.this, "" + error_msg, Toast.LENGTH_SHORT).show();

                                edtNamaUMKM.setText("");
                                edtUMKMAlamat.setText("");
                                edtUMKMDeskripsi.setText("");
                                Glide.with(DaftarActivity.this).load(R.drawable.upload_gambar_v1_256).error(R.drawable.logo_h128).into(ivUmkm);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    private void initView() {
        ivUmkm = findViewById(R.id.ivUmkm);
        edtNamaUMKM = findViewById(R.id.edtNamaUMKM);
        edtUMKMAlamat = findViewById(R.id.edtUMKMAlamat);
        edtUMKMDeskripsi = findViewById(R.id.edtUMKMDeskripsi);
        spnUMKMKategori = findViewById(R.id.spnUMKMKategori);
        ivResult = findViewById(R.id.ivResult);
        cameraPreview = findViewById(R.id.camera_preview);
        btnSendUmkm = findViewById(R.id.btnSendUMKM);
        btnUpdateUMKM = findViewById(R.id.btnUpdateUMKM);
    }
}
