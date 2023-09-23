package com.example.cs2340c_team28.GameScreen;

import android.app.Activity;

import android.os.Bundle;
import android.view.Window;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // TODO: use xml (activity_game.xml) instead of view class
        setContentView(new GameView(this));
    }
}