package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailActivity extends AppCompatActivity {

    String title,desc,content,imageURL,url;

    private TextView titleTV, subDescTv,contentTV;
    private ImageView newsIV;
    Button readNewsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        desc = getIntent().getStringExtra("desc");
        imageURL = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");

        titleTV = findViewById(R.id.idTVDetailTitle);
        subDescTv = findViewById(R.id.idTVDetailSubDesc);
        contentTV = findViewById(R.id.idTVDetailContent);
        newsIV = findViewById(R.id.idIVDetailNews);
        readNewsBtn=findViewById(R.id.idBtnReadNews);

        titleTV.setText(title);
        contentTV.setText(content);
        subDescTv.setText(desc);
        Picasso.get().load(imageURL).into(newsIV);

        readNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse(url));
//                startActivity(i);
            }
        });
    }
}