package com.example.newsapp.Retrofit;


import com.example.newsapp.Model.NewsModel;
import com.example.newsapp.Response.ArticleResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

import static com.example.newsapp.Constants.AppConstants.API_KEY;

public interface RetrofitAPI {
    @GET("top-headlines?country=id&excludeDomains=stackoverflow.com&sortBy=publishedAt&apiKey="+API_KEY)
    Call<ArticleResponse> getAllNews();

    @GET
    Call<ArticleResponse>getNewsByCategory(@Url String url);

    @GET
    Call<NewsModel>getNewsBySearch(@Url String url);

    @GET("top-headlines?country=id&excludeDomains=stackoverflow.com&sortBy=publishedAt&apiKey="+API_KEY)
    Call<ArticleResponse> getTopHeadlines();

}
