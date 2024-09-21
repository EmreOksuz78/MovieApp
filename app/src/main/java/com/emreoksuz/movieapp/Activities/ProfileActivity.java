package com.emreoksuz.movieapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.emreoksuz.movieapp.R;

public class ProfileActivity extends AppCompatActivity {

    Button updateProfile;
    ImageView explore;
    ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    updateProfile=findViewById(R.id.updateProfile);
    updateProfile.setBackgroundColor(Color.parseColor("#FF8C00"));

    profileImage = findViewById(R.id.profileImage);



    explore=findViewById(R.id.explore);
    explore.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ProfileActivity.this,MoviesActivity.class);
            startActivity(intent);
        }
    });


    }
}