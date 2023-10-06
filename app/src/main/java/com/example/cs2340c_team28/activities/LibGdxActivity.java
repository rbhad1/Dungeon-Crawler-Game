package com.example.cs2340c_team28.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.example.cs2340c_team28.R;

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
                Bundle extras = new Bundle();
                extras.putString("errorText", e.getMessage());
                intent.putExtras(extras);
                startActivity(intent);
//
//                Context context = LibGdxActivity.super.getContext();
//                TextView errorView = new TextView(context);
//                errorView.setText(e.getMessage());
//                errorView.setLayoutParams(
//                        new ViewGroup.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.MATCH_PARENT));
//                errorView.setTextColor(Color.RED);
//
//                setContentView(R.layout.activity_main);
            }
        });

        Game game = new Game() {
            @Override
            public void create() {
                setScreen(new TiledView());
            }
        };
        initialize(game);
    }
}