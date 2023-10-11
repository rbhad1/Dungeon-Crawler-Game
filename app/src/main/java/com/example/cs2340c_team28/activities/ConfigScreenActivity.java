package com.example.cs2340c_team28.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cs2340c_team28.R;
import com.example.cs2340c_team28.databinding.ActivityConfigScreenBinding;
import com.example.cs2340c_team28.viewmodels.ConfigScreenViewModel;

/**
 * Activity for game configuration screen.
 */
public class ConfigScreenActivity extends AppCompatActivity {

    /**
     * Creates the view.
     * Loads view elements and adds event listeners
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *                           <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        ConfigScreenViewModel viewModel = new ConfigScreenViewModel();
        ActivityConfigScreenBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_config_screen);
        binding.setViewModel(viewModel);
    }

    public void openGameActivity() {
        Intent intent = new Intent(ConfigScreenActivity.this, LegacyGameActivity.class);
        startActivity(intent);
    }

    public void openGameGdxActivity() {
        Intent intent = new Intent(ConfigScreenActivity.this, LibGdxActivity.class);
        startActivity(intent);
    }
}