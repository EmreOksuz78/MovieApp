package com.emreoksuz.movieapp.Model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("title")
    String title;
    @SerializedName("director")
    String director;
    @SerializedName("country")
    String country;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
