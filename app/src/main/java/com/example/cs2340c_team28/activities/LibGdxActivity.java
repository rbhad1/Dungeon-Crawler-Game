package com.example.cs2340c_team28.activities;


import android.os.Bundle;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class LibGdxActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();

        Game game = new Game() {
            @Override
            public void create() {
                setScreen(new TiledView());
            }
        };
        initialize(game);
    }
}