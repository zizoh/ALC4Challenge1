package com.example.alcphase1challenge;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileActivity extends AppCompatActivity {

    CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        imageView = findViewById(R.id.photo);

        Glide.with(this)
                .load(R.drawable.photo)
                .centerCrop()
                .into(imageView);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MyProfileActivity.class);
    }
}
