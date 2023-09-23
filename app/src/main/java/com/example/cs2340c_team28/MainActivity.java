package com.example.cs2340c_team28;

import static com.example.cs2340c_team28.R.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cs2340c_team28.databinding.ActivityMainBinding;
import android.app.Activity;

public class MainActivity extends AppCompatActivity {

    private MainActivity mainActRef = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        Button configButton = findViewById(id.configButton);
        Button endButton = findViewById(id.endButton);

        // clicked on end button
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, EndScreen.class);
                startActivity(intent);

            }
        });






    }


}