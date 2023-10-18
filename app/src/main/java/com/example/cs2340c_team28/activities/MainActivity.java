package com.example.cs2340c_team28.activities;

import static com.example.cs2340c_team28.R.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.viewmodels.ConfigScreenViewModel;


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

        configButton.setOnLongClickListener(view -> {
            ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
            configScreenViewModel.setPlayerName("Player");
            configScreenViewModel.setDifficulty(Difficulty.MEDIUM);
            configScreenViewModel.assignGameAndPlayerDetails();

            Intent intent = new Intent(MainActivity.this, LibGdxActivity.class);
            startActivity(intent);

            return true;
        });

        leaderButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LeaderboardActivity.class);
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