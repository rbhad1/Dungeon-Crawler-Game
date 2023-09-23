package com.example.cs2340c_team28.GameScreen;

import android.app.Activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.example.cs2340c_team28.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(new GameView(this));
    }
}