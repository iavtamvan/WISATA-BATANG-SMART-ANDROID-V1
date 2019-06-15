package com.iavariav.wisbasmartwisatabatangsmart.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import com.iavariav.wisbasmartwisatabatangsmart.service.CameraPreview;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialog;
import com.shashank.sony.fancydialoglib.Icon;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private final static int CAMERA_PIC_REQUEST1 = 0;

    private ImageView ivKeluhan;
    private EditText edtNamaKeluhan;
    private Spinner spnJenisKeluhan;
    private EditText edtDeskripsiKeluhan;
    private Button btnSendKeluhan;

    private String idAccount;
    private String imagePath;
    private String imagePathFront;
    private String getNameImage;
    private String getNameImageFront;

    private double longitude;
    private double latitude;

    private ResponseErrorModel responseErrorModel;


    private SharedPreferences sharedPreferences;
    private ImageView ivResult;
    private Camera mCamera;
    private CameraPreview mPreview;
    private FrameLayout camera_preview;
    static Context con;
    private String imageFotoFrontPathLaporan;
    private String getPathFotoDront;

    private ProgressDialog p;

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
                if (edtDeskripsiKeluhan.getText().toString().isEmpty() || edtDeskripsiKeluhan.getText().toString().isEmpty()) {
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
                } else {
                    p = new ProgressDialog(getActivity());
                    p.setMessage("Upload foto");
                    p.show();
                    uploadImage();
                    mCamera.takePicture(null, null, mPicture);
                }
            }
        });


        return view;
    }

    private void sendData() {
        ApiService apiService = ApiClient.getInstanceRetrofit();
        apiService.postKeluhan(idAccount, "http://devlop.can.web.id/uploads/client_profile_images/3/" + getNameImage
                , "http://devlop.can.web.id/uploads/client_profile_images/3/" + getNameImageFront
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
                            p.dismiss();
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
                        Toast.makeText(getActivity(), "Sukses Imagenya keluhabn", Toast.LENGTH_SHORT).show();


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
        ivResult = view.findViewById(R.id.ivResult);
        camera_preview = view.findViewById(R.id.camera_preview);
    }



//    Mulai Camera

    Bitmap bitmap;
    private Camera.PictureCallback mPicture = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {

            System.gc();
            bitmap = null;
            BitmapWorkerTask task = new BitmapWorkerTask(data);
            task.execute(0);
        }
    };

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private final WeakReference<byte[]> dataf;
        private int data = 0;

        public BitmapWorkerTask(byte[] imgdata) {
            // Use a WeakReference to ensure the ImageView can be garbage
            // collected
            dataf = new WeakReference<byte[]>(imgdata);
        }

        // Decode image in background.
        @Override
        protected Bitmap doInBackground(Integer... params) {
            data = params[0];
            ResultActivity(dataf.get());
            return mainbitmap;
        }

        // Once complete, see if ImageView is still around and set bitmap.
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            saving();
//            if (mainbitmap != null) {
//
//                Intent i = new Intent();
//                i.putExtra("BitmapImage", mainbitmap);
//                setResult(-1, i);
//                // Here I am Setting the Requestcode 1, you can put according to
//                // your requirement
////                finish(); // ini buat ngelempar ke activity sebelumnya
//
//            }
        }
    }
    int requestCode;
    private void saving() {
        if (requestCode == CAMERA_PIC_REQUEST1){
            ivResult.setImageBitmap(mainbitmap);
            ivResult.setDrawingCacheEnabled(true);
            Bitmap bitmap = ivResult.getDrawingCache();
            Uri tempUriFotoFront = getImageUriFotoFront(getActivity(), bitmap);
            File finalFileFotoFront = new File(getRealPathFromURILaporan(tempUriFotoFront));
        }
        else {
            Toast.makeText(getActivity(), "Foto Galat", Toast.LENGTH_SHORT).show();
        }
    }

    public Uri getImageUriFotoFront(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURILaporan(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx1 = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        imageFotoFrontPathLaporan = cursor.getString(idx1);
        getPathFotoDront = new File(imageFotoFrontPathLaporan).getName();

//        edtDeskripsiKeluhan.setText(imageFotoFrontPathLaporan);

        uploadFotoLaporan();
        return cursor.getString(idx1);
    }

    private void uploadFotoLaporan() {
        APIServiceUploadImage s = RetroClientUploadImage.getService();

        File f = new File(imageFotoFrontPathLaporan);
        if (f == null) {
            Log.d(TAG, "uploadImage: erroor files");
        }
        getNameImageFront = f.getName();
        Log.d("", "uploadImage: " + f.getName());


        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);

        MultipartBody.Part part = MultipartBody.Part.createFormData("uploaded_file", f.getName(), requestFile);
        Call<Result> resultCAll = s.postIMmage(part);
        resultCAll.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
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


    Bitmap mainbitmap;

    public void ResultActivity(byte[] data) {
        mainbitmap = null;
        mainbitmap = decodeSampledBitmapFromResource(data, 1000, 1000);
        mainbitmap=RotateBitmap(mainbitmap,270);
        mainbitmap=flip(mainbitmap);
    }

    public static Bitmap decodeSampledBitmapFromResource(byte[] data,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // BitmapFactory.decodeResource(res, resId, options);
        BitmapFactory.decodeByteArray(data, 0, data.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }
        }
        return inSampleSize;
    }

    /** A safe way to get an instance of the Camera object. */
    public static Camera getCameraInstance() {
        Camera c = null;
        Log.d("No of cameras", Camera.getNumberOfCameras() + "");
        for (int camNo = 0; camNo < Camera.getNumberOfCameras(); camNo++) {
            Camera.CameraInfo camInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(camNo, camInfo);

            if (camInfo.facing == (Camera.CameraInfo.CAMERA_FACING_FRONT)) {
                c = Camera.open(camNo);
                c.setDisplayOrientation(90);
            }
        }
        return c; // returns null if camera is unavailable
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release(); // release the camera for other applications
            mCamera = null;
        }
    }

    // rotate the bitmap to portrait
    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
                source.getHeight(), matrix, true);
    }

    //the front camera displays the mirror image, we should flip it to its original
    Bitmap flip(Bitmap d)
    {
        Matrix m = new Matrix();
        m.preScale(-1, 1);
        Bitmap src = d;
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), m, false);
        dst.setDensity(DisplayMetrics.DENSITY_DEFAULT);
        return dst;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCamera == null){
            con = getActivity();
            try {
                mCamera = getCameraInstance();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // Create our Preview view and set it as the content of our activity.
            mPreview = new CameraPreview(getActivity(), mCamera);
            camera_preview.addView(mPreview);
        }



    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }


//    selesai camera


}
