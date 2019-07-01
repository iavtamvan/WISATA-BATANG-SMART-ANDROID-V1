package com.iavariav.wisbasmartwisatabatangsmart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.activity.DetailNewsServerActivity;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.model.KeluhanBeritaModel;
import com.iavariav.wisbasmartwisatabatangsmart.model.ResponseErrorModel;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiClient;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaKeluhanAdapter extends RecyclerView.Adapter<BeritaKeluhanAdapter.ViewHolder> {
    private Context context;
    private ArrayList<KeluhanBeritaModel> keluhanBeritaModels;


    public BeritaKeluhanAdapter(Context context, ArrayList<KeluhanBeritaModel> KeluhanBeritaModels) {
        this.context = context;
        this.keluhanBeritaModels = KeluhanBeritaModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_news_keluhan, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Glide.with(context).load(keluhanBeritaModels.get(i).getGambarKeluhan()).override(512, 512).error(R.drawable.ic_launcher_background).into(viewHolder.ivKeluhan);
        Glide.with(context).load(keluhanBeritaModels.get(i).getFacePelaporanKeluhan()).override(512, 512).error(R.drawable.ic_launcher_background).into(viewHolder.icUserProfile);
        viewHolder.listJudulJKeluhan.setText(keluhanBeritaModels.get(i).getNamaKeluhan() + " | " + keluhanBeritaModels.get(i).getJenisKeluhan());
        viewHolder.contenKeluhan.setText(keluhanBeritaModels.get(i).getDeskripsiKeluhan() + " ...");
        viewHolder.tvTotalLike.setText(String.valueOf(keluhanBeritaModels.get(i).getLikeKeluhan()) + " Like");
        viewHolder.tvTotalDisLike.setText(String.valueOf(keluhanBeritaModels.get(i).getDislikeKeluhan()) + " Dislike");

        viewHolder.ivLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ApiClient.getInstanceRetrofit();
                apiService.likeKeluhan(keluhanBeritaModels.get(i).getIdKeluhan())
                        .enqueue(new Callback<ResponseErrorModel>() {
                            @Override
                            public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                                if (response.isSuccessful()){
                                    Toast.makeText(context, "Liked", Toast.LENGTH_SHORT).show();
                                    viewHolder.ivLike.setVisibility(View.GONE);
                                    viewHolder.ivLikeSukses.setVisibility(View.VISIBLE);
                                } else {
                                    Toast.makeText(context, "Gagal Like", Toast.LENGTH_SHORT).show();
                                    viewHolder.ivLike.setVisibility(View.VISIBLE);
                                    viewHolder.ivLikeSukses.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                viewHolder.ivLike.setVisibility(View.VISIBLE);
                                viewHolder.ivLikeSukses.setVisibility(View.GONE);
                            }
                        });
            }
        });
        viewHolder.ivdisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService = ApiClient.getInstanceRetrofit();
                apiService.disLikeKeluhan(keluhanBeritaModels.get(i).getIdKeluhan())
                        .enqueue(new Callback<ResponseErrorModel>() {
                            @Override
                            public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(context, "Disliked" , Toast.LENGTH_SHORT).show();
                                    viewHolder.ivdisLike.setVisibility(View.GONE);
                                    viewHolder.ivdisLikeSukses.setVisibility(View.VISIBLE);

                                } else  {
                                    Toast.makeText(context, "Gagal Disliked", Toast.LENGTH_SHORT).show();
                                    viewHolder.ivdisLike.setVisibility(View.VISIBLE);
                                    viewHolder.ivdisLikeSukses.setVisibility(View.GONE);
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                                Toast.makeText(context, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                viewHolder.ivdisLike.setVisibility(View.VISIBLE);
                                viewHolder.ivdisLikeSukses.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }

    @Override
    public int getItemCount() {
        return keluhanBeritaModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView icUserProfile;
        private TextView listJudulJKeluhan;
        private TextView contenKeluhan;
        private ImageView ivKeluhan;
        private ImageView ivLike;
        private ImageView ivdisLike;
        private TextView tvTotalLike;
        private TextView tvTotalDisLike;
        private ImageView ivLikeSukses;
        private ImageView ivdisLikeSukses;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icUserProfile = itemView.findViewById(R.id.icUserProfile);
            listJudulJKeluhan = itemView.findViewById(R.id.listJudulJKeluhan);
            contenKeluhan = itemView.findViewById(R.id.contenKeluhan);
            ivKeluhan = itemView.findViewById(R.id.ivKeluhan);

            ivLike = itemView.findViewById(R.id.ivLike);
            ivdisLike = itemView.findViewById(R.id.ivdisLike);
            tvTotalLike = itemView.findViewById(R.id.tvTotalLike);
            tvTotalDisLike = itemView.findViewById(R.id.tvTotalDisLike);
            ivLikeSukses = itemView.findViewById(R.id.ivLikeSukses);
            ivdisLikeSukses = itemView.findViewById(R.id.ivdisLikeSukses);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailNewsServerActivity.class);
                    intent.putExtra(Config.BUNDLE_NAMA_KELUHAN, keluhanBeritaModels.get(getAdapterPosition()).getNamaKeluhan());
                    intent.putExtra(Config.BUNDLE_IMAGE, keluhanBeritaModels.get(getAdapterPosition()).getGambarKeluhan());
                    intent.putExtra(Config.BUNDLE_JENIS_KELUHAN, keluhanBeritaModels.get(getAdapterPosition()).getJenisKeluhan());
                    intent.putExtra(Config.BUNDLE_DESKRIPSI_KELUHAN, keluhanBeritaModels.get(getAdapterPosition()).getDeskripsiKeluhan());
                    intent.putExtra(Config.BUNDLE_TANGGAL_OLEH_STATUS, keluhanBeritaModels.get(getAdapterPosition()).getRegistered() + " | " +
                            keluhanBeritaModels.get(getAdapterPosition()).getIdAccount() + " | " + keluhanBeritaModels.get(getAdapterPosition()).getStatusKeluhan());
                    context.startActivity(intent);
                }
            });
        }
    }
}
