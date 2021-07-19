package com.example.newsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;
import com.example.newsapp.Adapter.NewsRVAdapter;
import com.example.newsapp.Model.Articles;
import com.example.newsapp.R;
import com.example.newsapp.ViewModel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity  {

    SearchView searchView;
    private ProgressBar progressBar;
    private RecyclerView newsRV;
    private ArticleViewModel articleViewModel;
    private NewsRVAdapter newsRVAdapter;
    private ArrayList<Articles> articlesArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
        searchNews();

    }
    
    private void init()
    {
        searchView = findViewById(R.id.searchNewsSV);
        newsRV = findViewById(R.id.idRVSearchNews);
        progressBar = findViewById(R.id.idSearchPBLoading);
        articlesArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);

        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
    }

    private void getArticles(String keyword)
    {
        progressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        articleViewModel.getNewsBySearch(keyword).observe(this, articleResponse -> {
            if(articleResponse != null && articleResponse.getArticles()!=null
                    && !articleResponse.getArticles().isEmpty())
            {
                progressBar.setVisibility(View.GONE);
                List<Articles> articlesList = articleResponse.getArticles();
                articlesArrayList.addAll(articlesList);
                newsRVAdapter.notifyDataSetChanged();
            }
        });
    }

    private void searchNews()
    {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String keyword = searchView.getQuery().toString();
                getArticles(keyword);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
    }


}