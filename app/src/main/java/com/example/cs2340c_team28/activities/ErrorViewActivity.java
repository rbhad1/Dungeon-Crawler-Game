package com.example.cs2340c_team28.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340c_team28.R;

public class ErrorViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_view);

        // Get view elements
        TextView messageView = findViewById(R.id.activityErrorView_message);
        TextView stackTraceView = findViewById(R.id.activityErrorView_stackTrace);
        Button returnButton = findViewById(R.id.activityErrorView_returnButton);

        // Set on click listener to return to main screen
        returnButton.setOnClickListener(v -> {
            Intent intent = new Intent(ErrorViewActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Get bundle extras, which contains the throwable inside
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Throwable e = (Throwable) extras.get("throwable");
            if (e != null) {
                // Set message and stack trace text views, as long as the throwable isn't null
                messageView.setText(e.getMessage());
                // Use a StringBuilder to put the stack trace into a single string
                StringBuilder stackTrace = new StringBuilder();
                for (StackTraceElement element : e.getStackTrace()) {
                    stackTrace.append(element.toString()).append("\n");
                }
                stackTraceView.setText(stackTrace.toString());
            }
        }
    }

    @Override
    public void onBackPressed() {

    }
}