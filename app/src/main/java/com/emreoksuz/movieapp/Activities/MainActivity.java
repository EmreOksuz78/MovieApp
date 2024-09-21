package com.emreoksuz.movieapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.emreoksuz.movieapp.Model.Movie;
import com.emreoksuz.movieapp.R;
import com.emreoksuz.movieapp.Service.MovieApi;
import com.emreoksuz.movieapp.Service.MovieApiService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    Button buttonLogin;
    Button buttonSignup;
    EditText emailText;
    EditText passwordText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadApi();

        buttonLogin=findViewById(R.id.buttonLogin);
        buttonSignup=findViewById(R.id.buttonSignUp);

        buttonLogin.setBackgroundColor(Color.parseColor("#FF8C00"));
        buttonSignup.setBackgroundColor(Color.parseColor("#FF8C00"));

        emailText=findViewById(R.id.emailText);
        passwordText=findViewById(R.id.passwordText);

        mAuth=FirebaseAuth.getInstance();


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, MoviesActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Email or Password Wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                
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