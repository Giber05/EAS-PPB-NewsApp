package com.example.newsapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Model.Articles;
import com.example.newsapp.Activity.NewsDetailActivity;
import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.ViewHolder>{
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    //Constructor
    public NewsRVAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
        return new NewsRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NewsRVAdapter.ViewHolder holder, int position) {

        Articles articles = articlesArrayList.get(position);
        holder.publishAtTV.setText(articles.getPublishedAt());
        holder.titleTV.setText(articles.getTitle());
        holder.publisherTV.setText(articles.getSource().getName());
        Picasso.get().load(articles.getUrlToImage()).into(holder.newsIV);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, NewsDetailActivity.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("content",articles.getContent());
                i.putExtra("desc",articles.getDescription());
                i.putExtra("image",articles.getUrlToImage());
                i.putExtra("url",articles.getUrl());
                i.putExtra("publishAt",articles.getPublishedAt());
                i.putExtra("publisher",articles.getSource().getName());
                i.putExtra("author",articles.getAuthor());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV, publishAtTV, publisherTV;
        private ImageView newsIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.idTVNewsHeading);
            publishAtTV = itemView.findViewById(R.id.idPublishAt);
            publisherTV = itemView.findViewById(R.id.idPublisher);
            newsIV = itemView.findViewById(R.id.idIVNews);
        }
    }
}
