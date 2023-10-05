package com.example.cs2340c_team28.activities;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cs2340c_team28.databinding.ActivityScreen1Binding;

import com.example.cs2340c_team28.R;

public class Screen1 extends AppCompatActivity {
    public static final int TILE_WIDTH_PIXELS = 64;
    public static final int TILE_HEIGHT_PIXELS = 64;
    public static final int NUMBER_OF_ROW_TILES = 60;
    public static final int NUMBER_OF_COLUMN_TILES = 60;

    private int[][] layout;
    public Screen1() {
        initializeLayout();
    }
    private void initializeLayout() {
        layout = new int[][] {

        };
    }

}