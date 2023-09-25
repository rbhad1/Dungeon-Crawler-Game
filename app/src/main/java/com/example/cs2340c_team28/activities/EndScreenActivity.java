package com.example.cs2340c_team28.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.cs2340c_team28.R;

import androidx.appcompat.app.AppCompatActivity;

public class EndScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);

        Button returnToMainButton = findViewById(R.id.returnToMainButton);
        returnToMainButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            // Call this to reset the activity back stack, stop user from returning to this page
            finishAffinity();
            startActivity(intent);
        });
    }

    /**
     * Overridden to stop the user from returning back into the game
     */
    @Override
    public void onBackPressed() {

    }
}