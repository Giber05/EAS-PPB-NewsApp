package com.example.newsapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.R;
import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    String title,desc,content,imageURL,url,publishAt,publisher,author;

    private TextView titleTV, subDescTv,contentTV, publishAtTV,publisherTV,authorTV, urlTV;
    private ImageView newsIV;
    Button readNewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        getDataFromIntent();
        init();
        setDetailNewsData();

    }

    private void init()
    {
        titleTV = findViewById(R.id.idTVDetailTitle);
        subDescTv = findViewById(R.id.idTVDetailSubDesc);
        contentTV = findViewById(R.id.idTVDetailContent);
        newsIV = findViewById(R.id.idIVDetailNews);
        publishAtTV=findViewById(R.id.idTVDetailPublishAt);
        authorTV=findViewById(R.id.idTVDetailAuthor);
        publisherTV=findViewById(R.id.idTVDetailPublisher);
        readNewsBtn=findViewById(R.id.idBtnReadNews);
        urlTV = findViewById(R.id.idTVDetailUrl);
    }

    private void getDataFromIntent()
    {
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        desc = getIntent().getStringExtra("desc");
        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
        publishAt = getIntent().getStringExtra("publishAt");
        author = getIntent().getStringExtra("author");
        publisher = getIntent().getStringExtra("publisher");
    }

    private void setDetailNewsData()
    {
        titleTV.setText(title);
        contentTV.setText(content);
        subDescTv.setText(desc);
        publisherTV.setText(publisher);
        authorTV.setText(author);
        publishAtTV.setText(publishAt);
        urlTV.setText(url);
        Picasso.get().load(imageURL).into(newsIV);

        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("url",url);
                openWebPage(url);
            }
        });
    }

    public void openWebPage(String url) {

        Uri webpage = Uri.parse(url);

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            webpage = Uri.parse("http://" + url);
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        else{
            Log.d("intent","resolveActivity = Null");
        }
    }
}