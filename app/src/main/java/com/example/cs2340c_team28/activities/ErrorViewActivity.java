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

        TextView messageView = findViewById(R.id.activityErrorView_message);
        TextView stackTraceView = findViewById(R.id.activityErrorView_stackTrace);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Throwable e = (Throwable) extras.get("throwable");
            if (e != null) {
                messageView.setText(e.getMessage());
                StringBuilder stackTrace = new StringBuilder();
                for (StackTraceElement element : e.getStackTrace()) {
                    stackTrace.append(element.toString()).append("\n");
                }
                stackTraceView.setText(stackTrace.toString());
            }
        }

    }
}