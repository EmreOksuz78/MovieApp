package com.emreoksuz.movieapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.emreoksuz.movieapp.Model.Movie;
import com.emreoksuz.movieapp.R;

public class DetailsActivity extends AppCompatActivity {

    TextView detailTitle;
    TextView detailCountry;
    ImageView favoriteMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        detailTitle=findViewById(R.id.detailTitle);
        detailCountry=findViewById(R.id.detailCountry);
        favoriteMovies=findViewById(R.id.favoriteMovies);

        Intent intent = getIntent();

        Bundle bundle =intent.getExtras();
        String title = detailTitle.getText().toString();
        String country =detailCountry.getText().toString();
        title=bundle.getString("title");
        country=bundle.getString("country");

        detailTitle.setText("Movie Name : "+title);
        detailCountry.setText("Movie Country : "+country);


        favoriteMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteMovies.setColorFilter(Color.RED);

            }
        });


    }
}