package com.example.newsapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsapp.Adapter.CategoryRVAdapter;
import com.example.newsapp.Adapter.NewsRVAdapter;
import com.example.newsapp.Model.Articles;
import com.example.newsapp.Model.CategoryRVModel;
import com.example.newsapp.R;
import com.example.newsapp.Response.ArticleResponse;
import com.example.newsapp.Retrofit.RetrofitAPI;
import com.example.newsapp.ViewModel.ArticleViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface{
//44a6f0164757476d9f4e656d94362324
    public static final String API_KEY ="44a6f0164757476d9f4e656d94362324";

    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModel> categoryRVModelArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    ArticleViewModel articleViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        getCategories();

        getArticles("All");
//        getNews("All");
        newsRVAdapter.notifyDataSetChanged();
    }

    private void getCategories()
    {
        categoryRVModelArrayList.add(new CategoryRVModel("All","https://images.unsplash.com/photo-1504711434969-e33886168f5c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Technology","https://images.unsplash.com/photo-1485827404703-89b55fcc595e?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Science","https://images.unsplash.com/photo-1581093450021-4a7360e9a6b5?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Sports","https://images.unsplash.com/photo-1611119535617-ee346f83bb2c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("General","https://images.unsplash.com/photo-1618289733606-9ae55927a287?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Business","https://images.unsplash.com/photo-1579532536935-619928decd08?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Entertainment","https://images.unsplash.com/photo-1603190287605-e6ade32fa852?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryRVModelArrayList.add(new CategoryRVModel("Health","https://images.unsplash.com/photo-1576765974102-b756026ecee3?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=757&q=80"));

        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String category)
    {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=id&category=" + category + "&apiKey="+API_KEY;
        String url = "https://newsapi.org/v2/top-headlines?country=id&excludeDomains=stackoverflow.com&sortBy=publishedAt&apiKey="+API_KEY;
        String BASE_URL = "https://newsapi.org";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        Call<ArticleResponse> call;
        if(category.equals("All"))
        {
            call = retrofitAPI.getAllNews();
        }
        else
        {
            call = retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<ArticleResponse>() {
            @Override
            public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                ArticleResponse newsModel = response.body();
                loadingPB.setVisibility(View.GONE);
                List<Articles> articles = newsModel.getArticles();
                for (int i = 0; i<articles.size();i++)
                {
                    articlesArrayList.add(new Articles(
                            articles.get(i).getTitle(),
                            articles.get(i).getDescription(),
                            articles.get(i).getUrlToImage(),
                            articles.get(i).getUrl(),
                            articles.get(i).getContent(),
                            articles.get(i).getPublishedAt(),
                            articles.get(i).getAuthor(),
                            articles.get(i).getSource()));
                }
                newsRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArticleResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Fail to get news",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCategoryClick(int position) {
        String category = categoryRVModelArrayList.get(position).getCategory();
//        getNews(category);
        getArticles(category);
    }


    private void init()
    {
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList = new ArrayList<>();
        categoryRVModelArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModelArrayList,this,this::onCategoryClick);

        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);

        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
    }

    private void getArticles(String category)
    {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        articleViewModel.getDashboardNewsResponseLiveData(category).observe(this, articleResponse -> {
            if(articleResponse != null && articleResponse.getArticles()!=null
            && !articleResponse.getArticles().isEmpty())
            {
                loadingPB.setVisibility(View.GONE);
                List<Articles> articlesList = articleResponse.getArticles();
                articlesArrayList.addAll(articlesList);
                newsRVAdapter.notifyDataSetChanged();
            }
        });
    }

}