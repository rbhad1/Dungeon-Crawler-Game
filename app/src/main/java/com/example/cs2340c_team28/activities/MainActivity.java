package com.example.cs2340c_team28.activities;

import static com.example.cs2340c_team28.R.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Button configButton = findViewById(id.configButton);
        Button exitButton = findViewById(id.exitButton);
        Button leaderButton = findViewById(id.leaderboard);

        // clicked on config button
        configButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ConfigScreenActivity.class);
            startActivity(intent);
        });

        leaderButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LeaderboardV.class);
            startActivity(intent);
        });


        // clicked on end button
        exitButton.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        });






    }


}