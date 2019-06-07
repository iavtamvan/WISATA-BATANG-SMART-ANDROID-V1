package com.iavariav.wisbasmartwisatabatangsmart.fragment.berita;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iavariav.wisbasmartwisatabatangsmart.R;
import com.iavariav.wisbasmartwisatabatangsmart.adapter.BeritaNewsAdapter;
import com.iavariav.wisbasmartwisatabatangsmart.model.newsModel.ArticlesItem;
import com.iavariav.wisbasmartwisatabatangsmart.model.newsModel.RootNews;
import com.iavariav.wisbasmartwisatabatangsmart.rest.newsAPI.ApiClientNews;
import com.iavariav.wisbasmartwisatabatangsmart.rest.newsAPI.ApiServiceNews;
import com.iavariav.wisbasmartwisatabatangsmart.rest.server.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaFragment extends Fragment {


    private RecyclerView rv;
    private ArrayList<ArticlesItem> rootNews;
    private BeritaNewsAdapter beritaNewsAdapter;

    public BeritaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_berita, container, false);
        initView(view);
        getBerita();
        return view;
    }

    private void getBerita() {
        ApiServiceNews apiServiceNews = ApiClientNews.getInstanceRetrofit();
        apiServiceNews.getNews().enqueue(new Callback<RootNews>() {
            @Override
            public void onResponse(Call<RootNews> call, Response<RootNews> response) {
                if (response.isSuccessful()){
                    rootNews = response.body().getArticles();
                    beritaNewsAdapter = new BeritaNewsAdapter(getActivity(), rootNews);
                    rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));
                    rv.setAdapter(beritaNewsAdapter);
                    beritaNewsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RootNews> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view) {
        rv = view.findViewById(R.id.rv);
    }
}
