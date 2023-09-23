package com.example.cs2340c_team28.views;

import static com.example.cs2340c_team28.R.*;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // private MainActivity mainActRef = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Button configButton = findViewById(id.configButton);
        Button exitButton = findViewById(id.exitButton);

        // clicked on end button
        exitButton.setOnClickListener(view -> {
//            Intent intent = new Intent(MainActivity.this, EndScreen.class);
//            startActivity(intent);

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        });






    }


}