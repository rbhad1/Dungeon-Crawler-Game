package com.example.cs2340c_team28.viewmodels;

import android.view.View;
import android.widget.RadioGroup;

import com.example.cs2340c_team28.activities.ConfigScreenActivity;
import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

/**
 * View model for the game configuration screen.
 */
public class ConfigScreenViewModel {

    private final ConfigScreenActivity activity;

    /**
     * The name entered by the player. This may or may not be valid.
     */
    private String playerName = "";

    /**
     * The difficulty with which to start the game
     */
    private Difficulty difficulty;

    /**
     * The index of the sprite the player wants to use
     */
    private int spriteIndex = 1;

    /**
     * Constructor for the view model.
     * @param activity An active {@link ConfigScreenActivity}
     */
    public ConfigScreenViewModel(ConfigScreenActivity activity) {
        this.activity = activity;
    }

    /**
     * Function to validate whether what the user has selected can be used to start the game
     */
    public void refreshViewsWithConfigState() {

        boolean playerNameValid = playerNameValid(playerName);
        activity.getPlayerNameEditText().setError(
                playerNameValid ? null : "Player name must start with a letter or number.");

        boolean difficultyValid = difficulty != null;

        boolean canStart = playerNameValid && difficultyValid;

        activity.getStartHint().setVisibility(canStart ? View.INVISIBLE : View.VISIBLE);
        activity.getStartGameButton().setEnabled(canStart);
    }

    /**
     * Determine whether a given player name is valid
     * @param playerName The inputted name by the player
     * @return Whether or not the inputted player name is valid
     */
    public boolean playerNameValid(String playerName) {
        return playerName != null && playerName.matches("[:alnum:].*");
    }

    public void playerNameTextChanged(String newText) {
        playerName = newText;
        refreshViewsWithConfigState();
    }

    /**
     * Handler for the sprite selector button click.
     * Updates the {@link #spriteIndex} variable based on the selected button.
     * Sets sprite ImageView opacity and button enable/disable flags.
     *
     * @param v The view object representing the sprite button that was clicked
     */
    public void onSpriteButtonClicked(View v) {
        for (int i = 0; i < activity.getSpriteButtons().length; i++) {
            boolean isSelectedSprite = activity.getSpriteButtons()[i].getId() == v.getId();

            if (isSelectedSprite) {
                spriteIndex = i + 1;
            }

            activity.getSpriteButtons()[i].setEnabled(!isSelectedSprite);
            activity.getSpriteViews()[i].setAlpha(isSelectedSprite ? 1.0f : 0.2f);
        }
    }

    /**
     * Listener for the difficulty selector buttons.
     * Updates the {@link #difficulty} variable based on the selected difficulty
     *
     * @param group The radio group that was toggled
     * @param checkedId The id of the specific element that was changed
     */
    public void onDifficultyButtonClicked(RadioGroup group, int checkedId) {
        View radioButton = group.findViewById(checkedId);
        int index = group.indexOfChild(radioButton);
        difficulty = Difficulty.values()[index];
        refreshViewsWithConfigState();
    }

    /**
     * Listener for the start game button.
     * It is assumed that if this button was pressed, the config params must have been valid
     */
    public void onStartGameButtonClicked() {
        Game.createNewGame(difficulty);
        Player.createNewPlayer(playerName, difficulty, spriteIndex);

        activity.openGameActivity();
    }

}
