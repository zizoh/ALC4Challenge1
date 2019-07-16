package com.example.alcphase1challenge;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAboutALC;
    Button btnMyProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context context = this;

        btnAboutALC = findViewById(R.id.btn_about_alc);
        btnAboutALC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AboutALCActivity.getIntent(context);
                startActivity(intent);
            }
        });

        btnMyProfile = findViewById(R.id.btn_my_profile);
        btnMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MyProfileActivity.getIntent(context);
                startActivity(intent);
            }
        });
    }
}
