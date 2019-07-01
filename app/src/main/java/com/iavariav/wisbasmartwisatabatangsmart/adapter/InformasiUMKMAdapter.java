package com.iavariav.wisbasmartwisatabatangsmart.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
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
import com.iavariav.wisbasmartwisatabatangsmart.activity.umkm.DetailUMKMActivity;
import com.iavariav.wisbasmartwisatabatangsmart.helper.Config;
import com.iavariav.wisbasmartwisatabatangsmart.model.UmkmModel;

import java.util.ArrayList;

public class InformasiUMKMAdapter extends RecyclerView.Adapter<InformasiUMKMAdapter.ViewHolder> {
    private Context context;
    private ArrayList<UmkmModel> umkmModel;



    public InformasiUMKMAdapter(Context context, ArrayList<UmkmModel> umkmModel) {
        this.context = context;
        this.umkmModel = umkmModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_infromasi_umkm, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        Glide.with(context).load(umkmModel.get(i).getGambarThumbnailUmkm()).override(512,512).error(R.drawable.logo_h128).into(viewHolder.ivUMKMInformasi);
        viewHolder.tvUMKMInformasi.setText(umkmModel.get(i).getNamaUmkm());
    }

    @Override
    public int getItemCount() {
        return umkmModel.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvKlikInformasi;
        private ImageView ivUMKMInformasi;
        private TextView tvUMKMInformasi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvKlikInformasi = itemView.findViewById(R.id.cvKlikInformasi);
            ivUMKMInformasi = itemView.findViewById(R.id.ivUMKMInformasi);
            tvUMKMInformasi = itemView.findViewById(R.id.tvUMKMInformasi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailUMKMActivity.class);
                    intent.putExtra(Config.BUNDLE_ID_UMKM, umkmModel.get(getAdapterPosition()).getIdUmkm());
                    intent.putExtra(Config.BUNDLE_REGISTERED, umkmModel.get(getAdapterPosition()).getRegistered());
                    intent.putExtra(Config.BUNDLE_NAMA_UMKM, umkmModel.get(getAdapterPosition()).getNamaUmkm());
                    intent.putExtra(Config.BUNDLE_GAMBAR_THUMBNAIL_UMKM, umkmModel.get(getAdapterPosition()).getGambarThumbnailUmkm());
                    intent.putExtra(Config.BUNDLE_ALAMAT_UMKM, umkmModel.get(getAdapterPosition()).getAlamatUmkm());
                    intent.putExtra(Config.BUNDLE_JARAK_UMKM, umkmModel.get(getAdapterPosition()).getJarakUmkm());
                    intent.putExtra(Config.BUNDLE_LAT_UMKM, umkmModel.get(getAdapterPosition()).getLatUmkm());
                    intent.putExtra(Config.BUNDLE_LONG_UMKM, umkmModel.get(getAdapterPosition()).getLongUmkm());
                    intent.putExtra(Config.BUNDLE_GAMBAR_1_UMKM, umkmModel.get(getAdapterPosition()).getGambar1Umkm());
                    intent.putExtra(Config.BUNDLE_GAMBAR_2_UMKM, umkmModel.get(getAdapterPosition()).getGambar2Umkm());
                    intent.putExtra(Config.BUNDLE_DETAIL_DESKRIPSI_UMKM, umkmModel.get(getAdapterPosition()).getDetailDeskripsiUmkm());
                    intent.putExtra(Config.BUNDLE_LIKE_UMKM, umkmModel.get(getAdapterPosition()).getLikeUmkm());
                    intent.putExtra(Config.BUNDLE_DISLIKE_UMKM, umkmModel.get(getAdapterPosition()).getDislikeUmkm());
                    intent.putExtra(Config.BUNDLE_KATEGORI_UMKM, umkmModel.get(getAdapterPosition()).getKategoriUmkm());
                    intent.putExtra(Config.BUNDLE_STATUS_UMKM, umkmModel.get(getAdapterPosition()).getStatusUmkm());
                    context.startActivity(intent);
                }
            });
        }
    }
}
