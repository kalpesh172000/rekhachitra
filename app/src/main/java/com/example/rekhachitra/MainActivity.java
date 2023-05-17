package com.example.rekhachitra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imageButtonSearchRoute = findViewById(R.id.imageButtonSearchRoute);
        imageButtonSearchRoute.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, SearchRoute.class);
            startActivity(i);
        });

    }
}