package com.example.cs2340c_team28.viewmodels;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.cs2340c_team28.activities.ConfigScreenActivity;
import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

/**
 * View model for the game configuration screen.
 */
public class ConfigScreenViewModel extends BaseObservable {

    private static final String TAG = ConfigScreenViewModel.class.getName();

    /**
     * Represents whether or not the game can start
     */
    private boolean gameCanStart = false;

    @Bindable public boolean isGameCanStart() {
        return gameCanStart;
    }

    private void setGameCanStart(boolean gameCanStart) {
        this.gameCanStart = gameCanStart;
        notifyPropertyChanged(BR.gameCanStart);
    }

    /**
     * The name entered by the player. This may or may not be valid.
     */
    private String playerName = "";

    @Bindable public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        if (!this.playerName.equals(playerName)) {
            this.playerName = playerName;
            refreshErrorRelatedFields();
        }
    }

    /**
     * Error text to show if player name isn't valid
     */
    private String playerNameError = null;

    /**
     * The difficulty with which to start the game
     */
    private Difficulty difficulty;

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        refreshErrorRelatedFields();
    }

    /**
     * The index of the sprite the player wants to use
     */
    private int spriteIndex = 1;

    @Bindable
    public int getSpriteIndex() {
        return spriteIndex;
    }

    private void setSpriteIndex(int spriteIndex) {
        this.spriteIndex = spriteIndex;
        notifyPropertyChanged(BR.spriteIndex);
    }

    /**
     * Function to validate whether what the user has selected can be used to start the game
     */
    public void refreshErrorRelatedFields() {

        boolean playerNameValid = playerNameValid(playerName);
        boolean playerNameLength = playerNameLength(playerName);
        if (!playerNameValid) {
            playerNameError = "Player name must start with a letter or number.";
        } else if (!playerNameLength) {
            playerNameError = "Player name must be 10 or fewer characters.";
        } else {
            playerNameError = null;
        }

        boolean difficultyValid = difficulty != null;

        setGameCanStart(playerNameValid && playerNameLength && difficultyValid);

    }

    /**
     * Called after the player name is entered to update the error message (if any)
     *
     * @param e EditText object to potentially set error on
     */
    public void updatePlayerNameEditText(EditText e) {
        Log.v(TAG, "updatePlayerNameEditText(...) called");
        e.setError(playerNameError);
    }

    /**
     * Determine whether a given player name is valid
     * @param playerName The inputted name by the player
     * @return Whether or not the inputted player name is valid
     */
    public boolean playerNameValid(String playerName) {
        return playerName != null && playerName.matches("[a-zA-Z0-9].*");
    }

    /**
     * Determine if the player name is within the required length
     * @param playerName The inputted name by the player
     * @return Whether or not the name is 10 or fewer characters
     */
    public boolean playerNameLength(String playerName) {
        return playerName.length() <= 10;
    }

    /**
     * Handler for the sprite selector button click.
     * Updates the {@link #spriteIndex} variable based on the selected button.
     * Sets sprite ImageView opacity and button enable/disable flags.
     *
     * @param i The index of the sprite button that was clicked
     */
    public void onSpriteButtonClicked(int i) {
        this.setSpriteIndex(i);
    }

    /**
     * Listener for the difficulty selector buttons.
     * Updates the {@link #difficulty} variable based on the selected difficulty
     *
     * @param difficultyIndex An "index" starting from 1 representing difficulty selected
     */
    public void onDifficultyButtonClicked(int difficultyIndex) {
        setDifficulty(Difficulty.values()[difficultyIndex - 1]);
    }

    /**
     * Listener for the legacy start game button.
     * It is assumed that if this button was pressed, the config params must have been valid
     *
     * @param v The button that was pressed, as a View element
     */
    public void onStartGameButtonClicked(View v) {
        assignGameProperties();

        Context context = v.getContext();
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof ConfigScreenActivity) {
                ((ConfigScreenActivity) baseContext).openGameActivity();
            }
        }
    }

    /**
     * Listener for the LibGdx start game button.
     * It is assumed that if this button was pressed, the config params must have been valid
     *
     * @param v The button that was pressed, as a View element
     */
    public void onStartGameButtonGdxClicked(View v) {
        assignGameProperties();
        Context context = v.getContext();
        if (context instanceof ContextWrapper) {
            Context baseContext = ((ContextWrapper) context).getBaseContext();
            if (baseContext instanceof ConfigScreenActivity) {
                ((ConfigScreenActivity) baseContext).openGameGdxActivity();
            }
        }

    }

    /**
     * Set properties for the game and player
     */
    public void assignGameProperties() {
        Game.getInstance().setDifficulty(difficulty);
        Player.getInstance().setName(playerName);
        Player.getInstance().setSpriteId(spriteIndex);
        Player.getInstance().setOriginalHp(
                Player.initialHp(Game.getInstance().getDifficulty())
        );
        Player.getInstance().setHp(
                Player.getInstance().getOriginalHp()
        );
        Player.getInstance().setX(440);
        Player.getInstance().setY(600);
    }

}
