package com.emreoksuz.movieapp.Service;

import com.emreoksuz.movieapp.Model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApi {

    @GET("movies")
    Call<List<Movie>> getMovies();

}
