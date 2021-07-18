package com.example.newsapp.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Articles {
    private String title;
    private String description;
    private String urlToImage;
    private String url;
    private String content;
    private String publishedAt;
    private String author;
    private Source source;

    public Articles(String title, String description, String urlToImage, String url, String content,
                    String publishedAt, String author, Source source) {
        this.title = title;
        this.description = description;
        this.urlToImage = urlToImage;
        this.url = url;
        this.content = content;
        this.publishedAt = publishedAt;
        this.author = author;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishedAt() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = fmt.parse(publishedAt);
            return fmt.format(date);
        }
        catch(ParseException pe) {
            return "Date";
        }
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
