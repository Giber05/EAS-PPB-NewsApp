package com.example.newsapp.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.Response.ArticleResponse;
import com.example.newsapp.Retrofit.RetrofitAPI;
import com.example.newsapp.Retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.newsapp.Constants.AppConstants.API_KEY;

public class ArticleRepository {
    private static final String TAG = ArticleRepository.class.getSimpleName();
    private final RetrofitAPI retrofitAPI;

    public ArticleRepository() {
        retrofitAPI = RetrofitRequest.getRetrofitInstance().create(RetrofitAPI.class);
    }

    public LiveData<ArticleResponse> getDashboardNews(String category) {
    final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();

        if(category.equals("All"))
        {
            retrofitAPI.getTopHeadlines()
                    .enqueue(new Callback<ArticleResponse>() {
                        @Override
                        public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                            if (response.body() != null) {
                                data.setValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<ArticleResponse> call, Throwable t) {
                            data.setValue(null);
                        }
                    });
            return data;
        }
        else
        {
            retrofitAPI.getNewsByCategory("https://newsapi.org/v2/top-headlines?country=id&category=" + category + "&apiKey="+API_KEY)
                    .enqueue(new Callback<ArticleResponse>() {
                        @Override
                        public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                            if (response.body() != null) {
                                data.setValue(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<ArticleResponse> call, Throwable t) {
                            data.setValue(null);
                        }
                    });
            return data;
        }
    }

    public LiveData<ArticleResponse> getSearchNews(String key) {
        final MutableLiveData<ArticleResponse> data = new MutableLiveData<>();
        retrofitAPI.getNewsBySearch("https://newsapi.org/v2/top-headlines?country=id&q="+key+"&sortBy=popularity&apiKey="+API_KEY)
                .enqueue(new Callback<ArticleResponse>() {
                    @Override
                    public void onResponse(Call<ArticleResponse> call, Response<ArticleResponse> response) {
                        if (response.body() != null) {
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<ArticleResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
