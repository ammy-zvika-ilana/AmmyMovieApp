package com.example.ammysmovieapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.ViewHolder> {
    private static final String TAG = "TVA";
    private Context movie_Context;
    private List<MovieResults> movie_Data;

    //constructor
    public Adaptery(Context movie_Context, List<MovieResults> movie_Data) {
        this.movie_Context = movie_Context;
        this.movie_Data = movie_Data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(movie_Context);
        view = inflater.inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText("avg score: " + movie_Data.get(position).getVoteAverage());
        holder.name.setText(movie_Data.get(position).getTitle());


        holder.image.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent intent = new Intent(movie_Context, moviePage.class);
                                                intent.putExtra("image_url", "https://image.tmdb.org/t/p/w500" + movie_Data.get(position).getImage());
                                                intent.putExtra("movie_title", movie_Data.get(position).getTitle());
                                                intent.putExtra("movie_description", movie_Data.get(position).getOverview());
                                                movie_Context.startActivity(intent);
                                            }
                                        }
        );
        holder.name.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent intent = new Intent(movie_Context, moviePage.class);
                                               intent.putExtra("image_url", "https://image.tmdb.org/t/p/w500" + movie_Data.get(position).getImage());
                                               intent.putExtra("movie_title", movie_Data.get(position).getTitle());
                                               intent.putExtra("movie_description", movie_Data.get(position).getOverview());
                                               movie_Context.startActivity(intent);
                                           }
                                       }
        );
        //display the image from the url
        Glide.with(movie_Context).load("https://image.tmdb.org/t/p/w500" + movie_Data.get(position).getImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return movie_Data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        ImageView image;
        ConstraintLayout parent_layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_view);
            name = itemView.findViewById(R.id.name_view);
            image = itemView.findViewById(R.id.image_View);
        }
    }
}
