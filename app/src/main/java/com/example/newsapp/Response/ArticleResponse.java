package com.example.newsapp.Response;

import com.example.newsapp.Model.Articles;

import java.util.List;

public class ArticleResponse {
    private List<Articles> articles;

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
