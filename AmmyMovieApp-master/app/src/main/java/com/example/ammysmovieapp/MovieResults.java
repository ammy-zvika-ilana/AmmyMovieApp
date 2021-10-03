package com.example.ammysmovieapp;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieResults {
    String id;
    String name;
    String image;
    String overview;


    public MovieResults(String id, String name, String image, String overview) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.overview = overview;
    }

    public MovieResults() {
    }


    public String getVoteAverage() {
        return id;
    }

    public void setVoteAverage(String id) {
        this.id = id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }
}
