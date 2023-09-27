package com.example.cs2340c_team28.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.cs2340c_team28.R;
import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

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

    /**
     * The difficulty with which to start the game
     */
    private Difficulty difficulty;

    /**
     * The name entered by the player. This may or may not be valid.
     */
    private String playerName;

    /**
     * The index of the sprite the player wants to use
     */
    private int spriteIndex = 1;

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
        this.startHint = findViewById(R.id.startHint);

        // Create arrays representing groups of elements
        spriteButtons = new Button[]{spriteButton1, spriteButton2, spriteButton3};
        spriteViews = new ImageView[]{spriteView1, spriteView2, spriteView3};

        // Update view state
        validateConfigState();

        // Listener for player name input
        playerNameEditText.addTextChangedListener(onPlayerNameTextChange);

        // Listener for difficulty select
        radioGroup.setOnCheckedChangeListener(onDifficultySelect);

        // Listener for sprite select
        for (Button button: spriteButtons) {
            button.setOnClickListener(onSpriteButtonClick);
        }

        // Listener for start game select
        startGameButton.setOnClickListener(onStartGameButtonClick);
    }

    /**
     * TextWatcher for the player name text field.
     * Updates the {@link #playerName} variable with the contents entered into text field
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
            playerName = s.toString();
            validateConfigState();
        }
    };

    /**
     * Listener for the sprite selector buttons.
     * Updates the {@link #spriteIndex} variable based on the selected button.
     * Sets sprite ImageView opacity and button enable/disable flags.
     */
    private final View.OnClickListener onSpriteButtonClick = view -> {
        for (int i = 0; i < spriteButtons.length; i++) {
            boolean isSelectedSprite = spriteButtons[i].getId() == view.getId();

            if (isSelectedSprite) {
                spriteIndex = i + 1;
            }

            spriteButtons[i].setEnabled(!isSelectedSprite);
            spriteViews[i].setAlpha(isSelectedSprite ? 1.0f : 0.2f);
        }
    };

    /**
     * Listener for the difficulty selector buttons.
     * Updates the {@link #difficulty} variable based on the selected difficulty
     */
    private final RadioGroup.OnCheckedChangeListener onDifficultySelect = (group, checkedId) -> {
        View radioButton = group.findViewById(checkedId);
        int index = group.indexOfChild(radioButton);
        difficulty = Difficulty.values()[index];
        validateConfigState();
    };

    /**
     * Listener for the start game button.
     * It is assumed that if this button was pressed, the config params must have been valid
     */
    private final View.OnClickListener onStartGameButtonClick = view -> {
        Game.createNewGame(difficulty);
        Player.createNewPlayer(playerName, difficulty, spriteIndex);

        Intent intent = new Intent(ConfigScreenActivity.this, GameActivity.class);
        startActivity(intent);
    };

    /**
     * Function to validate whether what the user has selected can be used to start the game
     */
    private void validateConfigState() {

        boolean playerNameValid = playerName != null && playerName.matches("[:alnum:].*");
        playerNameEditText.setError(
                playerNameValid ? null : "Player name must start with a letter or number.");

        boolean difficultyValid = difficulty != null;

        boolean canStart = playerNameValid && difficultyValid;

        startHint.setVisibility(canStart ? View.INVISIBLE : View.VISIBLE);
        startGameButton.setEnabled(canStart);
    }



}