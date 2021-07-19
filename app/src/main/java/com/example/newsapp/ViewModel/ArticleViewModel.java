package com.example.newsapp.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapp.Repository.ArticleRepository;
import com.example.newsapp.Response.ArticleResponse;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private LiveData<ArticleResponse> articleResponseLiveData;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        String ct = "All";

        this.articleRepository = new ArticleRepository();
        this.articleResponseLiveData = articleRepository.getDashboardNews(ct);

    }

    public LiveData<ArticleResponse> getDashboardNewsResponseLiveData(String category)
    {
        if(category.equals("All")) {
            return this.articleResponseLiveData;
        }
        else
        {
            return this.articleResponseLiveData = articleRepository.getDashboardNews(category);
        }
    }

    public LiveData<ArticleResponse> getNewsBySearch(String key)
    {
        return this.articleResponseLiveData = articleRepository.getSearchNews(key);

    }
}
