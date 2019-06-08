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
import com.iavariav.wisbasmartwisatabatangsmart.model.newsModel.ArticlesItem;

import java.util.ArrayList;

public class BeritaNewsAdapter extends RecyclerView.Adapter<BeritaNewsAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ArticlesItem> articlesItems;

    public BeritaNewsAdapter(Context context, ArrayList<ArticlesItem> articlesItems) {
        this.context = context;
        this.articlesItems = articlesItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_news_api, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(articlesItems.get(i).getUrlToImage()).override(512, 512).error(R.drawable.ic_launcher_background).into(viewHolder.ivNews);
        viewHolder.listJudulNews.setText(articlesItems.get(i).getTitle());
        viewHolder.contenNews.setText(articlesItems.get(i).getContent() + " ...");
    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView listJudulNews;
        private TextView contenNews;
        private ImageView ivNews;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listJudulNews = itemView.findViewById(R.id.listJudulNews);
            contenNews = itemView.findViewById(R.id.contenNews);
            ivNews = itemView.findViewById(R.id.ivNews);
        }
    }
}