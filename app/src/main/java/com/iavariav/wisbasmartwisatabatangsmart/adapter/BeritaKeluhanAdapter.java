package com.iavariav.wisbasmartwisatabatangsmart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.model.KeluhanBeritaModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(keluhanBeritaModels.get(i).getGambarKeluhan()).override(512, 512).error(R.drawable.ic_launcher_background).into(viewHolder.ivKeluhan);
        Glide.with(context).load(keluhanBeritaModels.get(i).getFacePelaporanKeluhan()).override(512, 512).error(R.drawable.ic_launcher_background).into(viewHolder.icUserProfile);
        viewHolder.listJudulJKeluhan.setText(keluhanBeritaModels.get(i).getNamaKeluhan() + " | " + keluhanBeritaModels.get(i).getJenisKeluhan());
        viewHolder.contenKeluhan.setText(keluhanBeritaModels.get(i).getDeskripsiKeluhan() + " ...");
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icUserProfile = itemView.findViewById(R.id.icUserProfile);
            listJudulJKeluhan = itemView.findViewById(R.id.listJudulJKeluhan);
            contenKeluhan = itemView.findViewById(R.id.contenKeluhan);
            ivKeluhan = itemView.findViewById(R.id.ivKeluhan);
        }
    }
}
