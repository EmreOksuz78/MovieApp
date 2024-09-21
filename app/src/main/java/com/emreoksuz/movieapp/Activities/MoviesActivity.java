package com.emreoksuz.movieapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emreoksuz.movieapp.Adapter.RecyclerViewAdapter;
import com.emreoksuz.movieapp.Model.Movie;
import com.emreoksuz.movieapp.R;
import com.emreoksuz.movieapp.Service.MovieApi;
import com.emreoksuz.movieapp.Service.MovieApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    private ArrayList<Movie> listMovie;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movies);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadApi();

        recyclerView=findViewById(R.id.recyclerViewMovies);
        profile=findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MoviesActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

    }


    public void loadApi(){

        MovieApi movieApi = MovieApiService.getClient().create(MovieApi.class);
        Call<List<Movie>> call = movieApi.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (response.isSuccessful()){
                    List<Movie> movieList= response.body();
                    listMovie= new ArrayList<>(movieList);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MoviesActivity.this));
                    recyclerViewAdapter= new RecyclerViewAdapter(listMovie);
                    recyclerView.setAdapter(recyclerViewAdapter);




                    for (Movie movie:movieList){
                        System.out.println(movie.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });


    }
}