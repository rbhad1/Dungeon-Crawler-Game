package com.example.cs2340c_team28.activities;

import com.example.cs2340c_team28.R;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView playerName = findViewById(R.id.playerNameField);
        TextView playerHealth = findViewById(R.id.playerHealth);
        TextView difficulty = findViewById(R.id.difficultyField);
        TextView spriteName = findViewById(R.id.spriteName);
        playerName.setText("Jerry");
        playerHealth.setText("50/50");
        difficulty.setText("HARD");
        Button endScreenButton = findViewById(R.id.endScreenButton);

        endScreenButton.setOnClickListener(view -> {
            Intent intent = new Intent(GameActivity.this, EndScreenActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Overridden to stop the user from returning back into the config screen
     */
    @Override
    public void onBackPressed() {

    }
}