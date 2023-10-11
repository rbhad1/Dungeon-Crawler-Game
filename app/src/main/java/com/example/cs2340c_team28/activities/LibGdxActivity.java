package com.example.cs2340c_team28.activities;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.cs2340c_team28.threads.GameThread;

public class LibGdxActivity extends AndroidApplication {

    private static final String TAG = "LibGdxActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
                Log.e("TAG", "Uncaught exception in " + TAG);

                Intent intent = new Intent(LibGdxActivity.this, ErrorViewActivity.class);
                intent.putExtra("throwable", e);
                startActivity(intent);
            }
        });

        GameThread gameThread = new GameThread(this);
        initialize(gameThread);


    }

    public void navigateToEndGame() {
        Intent intent = new Intent(this, LeaderboardV.class);
        startActivity(intent);
    }


}