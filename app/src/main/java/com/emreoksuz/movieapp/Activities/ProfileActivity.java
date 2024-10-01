package com.emreoksuz.movieapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.emreoksuz.movieapp.R;

public class ProfileActivity extends AppCompatActivity {

    Button updateProfile;
    ImageView explore;
    ImageView profileImage;
    private static final int GALLERY_REQUEST_CODE = 100;

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

    profileImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //ASK FOR PERMISSION
            openGallery();
            Toast.makeText(ProfileActivity.this, "ProfilePhoto", Toast.LENGTH_SHORT).show();
        }
    });


    }

    public void openGallery(){
        Intent intentToGallery= new Intent(Intent.ACTION_PICK);
        intentToGallery.setType("image/*");
        startActivityForResult(intentToGallery,GALLERY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null){
            Uri uriImage = data.getData();
            ImageView imageView = findViewById(R.id.profileImage);
            imageView.setImageURI(uriImage);
        }

    }
}