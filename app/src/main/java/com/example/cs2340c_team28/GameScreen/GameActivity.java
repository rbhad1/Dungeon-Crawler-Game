package com.example.cs2340c_team28.GameScreen;

import static com.example.cs2340c_team28.R.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340c_team28.views.EndScreen;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_game);
        TextView playerName = findViewById(id.playerName);
        TextView playerHealth = findViewById(id.playerHealth);
        TextView difficulty = findViewById(id.difficulty);
        playerName.setText("Jerry");
        playerHealth.setText("50/50");
        difficulty.setText("HARD");
        Button endScreenButton = findViewById(id.endScreenButton);

        endScreenButton.setOnClickListener(view -> {
            Intent intent = new Intent(GameActivity.this, EndScreen.class);
            startActivity(intent);
        });
    }
}