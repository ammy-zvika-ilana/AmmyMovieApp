package com.example.ammysmovieapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class moviePage extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_page);
        getIncomIntent();
    }
    private void getIncomIntent(){
        if(getIntent().hasExtra("image_url")&& getIntent().hasExtra("movie_title") &&
                getIntent().hasExtra("movie_description")){
            String imageUrl = getIntent().getStringExtra("image_url");
            String movieTitle = getIntent().getStringExtra("movie_title");
            String movieDescription = getIntent().getStringExtra("movie_description");
            setImage(imageUrl, movieTitle, movieDescription);
        }
    }

    private void setImage(String imageUrl, String movieTitle, String movieDescription){
        TextView name = findViewById(R.id.mTitle);
        name.setText(movieTitle);
        TextView description = findViewById(R.id.description);
        description.setText(movieDescription);

        ImageView imageView = findViewById(R.id.mImage);
        Glide.with(this).load(imageUrl).into(imageView);


    }

}
