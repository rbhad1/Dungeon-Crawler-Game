package com.example.cs2340c_team28.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.cs2340c_team28.R;

public class ErrorViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_view);

        TextView textView = findViewById(R.id.errorText);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String errorText = extras.getString("errorText");
            if (errorText != null) {
                textView.setText(errorText);
            }
        }

    }
}