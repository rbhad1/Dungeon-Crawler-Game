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

    private EditText playerNameEditText;

    private RadioGroup radioGroup;

    private Button spriteButton1;
    private Button spriteButton2;
    private Button spriteButton3;
    private Button[] spriteButtons;

    private ImageView spriteView1;
    private ImageView spriteView2;
    private ImageView spriteView3;
    private ImageView[] spriteViews;

    private TextView startHint;

    private Button startGameButton;
    private Button startGameButtonGdx;

    private ConfigScreenViewModel viewModel;

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

        // Get view elements
        this.playerNameEditText = findViewById(R.id.playerNameEditText);
        this.radioGroup = findViewById(R.id.radioGroup);
        this.spriteButton1 = findViewById(R.id.spriteButton1);
        this.spriteButton2 = findViewById(R.id.spriteButton2);
        this.spriteButton3 = findViewById(R.id.spriteButton3);
        this.spriteView1 = findViewById(R.id.spriteView1);
        this.spriteView2 = findViewById(R.id.spriteView2);
        this.spriteView3 = findViewById(R.id.spriteView3);
        this.startGameButton = findViewById(R.id.startGameButton);
        this.startGameButtonGdx = findViewById(R.id.startGameButtonGDX);
        this.startHint = findViewById(R.id.startHint);

        // Create arrays representing groups of elements
        spriteButtons = new Button[]{spriteButton1, spriteButton2, spriteButton3};
        spriteViews = new ImageView[]{spriteView1, spriteView2, spriteView3};

        this.viewModel = new ConfigScreenViewModel();

        // Listener for player name input
        playerNameEditText.addTextChangedListener(onPlayerNameTextChange);

        // Listener for difficulty select
//        radioGroup.setOnCheckedChangeListener(viewModel::onDifficultyButtonClicked);

        // Listener for sprite select
        for (Button button: spriteButtons) {
//            button.setOnClickListener(viewModel::onSpriteButtonClicked);
        }

        // Listener for start game select
//        startGameButton.setOnClickListener(v -> this.viewModel.onStartGameButtonClicked());
//        startGameButtonGdx.setOnClickListener(v -> this.viewModel.onStartGameButtonGdxClicked());
    }

    /**
     * TextWatcher for the player name text field.
     * Forwards text field value to {@link ConfigScreenViewModel}
     */
    private final TextWatcher onPlayerNameTextChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
//            viewModel.playerNameTextChanged(s.toString());
        }
    };

    public void openGameActivity() {
        Intent intent = new Intent(ConfigScreenActivity.this, LegacyGameActivity.class);
        startActivity(intent);
    }

    public void openGameGdxActivity() {
        Intent intent = new Intent(ConfigScreenActivity.this, LibGdxActivity.class);
        startActivity(intent);
    }

    public EditText getPlayerNameEditText() {
        return playerNameEditText;
    }

    public Button[] getSpriteButtons() {
        return spriteButtons;
    }

    public ImageView[] getSpriteViews() {
        return spriteViews;
    }

    public TextView getStartHint() {
        return startHint;
    }

    public Button getStartGameButton() {
        return startGameButton;
    }

    public Button getStartGameGdxButton() {
        return startGameButtonGdx;
    }
}