package com.example.cs2340c_team28.viewmodels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.cs2340c_team28.activities.LibGdxActivity;
import com.example.cs2340c_team28.screens.TiledView;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

import java.util.Date;

public class GameViewModel extends com.badlogic.gdx.Game {
    /**
     * Current game instance
     */
    private final Game game = Game.getUniqueGameInstance();

    /**
     * Texture for the player sprite
     */
    private Texture playerImage;

    /**
     * Batch of sprites to be rendered
     */
    private SpriteBatch batch;

    private LibGdxActivity activity;

    public GameViewModel(LibGdxActivity activity) {
        this.activity = activity;
    }

    public GameViewModel() {
        this.activity = new LibGdxActivity();
    }

    /**
     * Loads images and handles how often the game renders
     */
    @Override
    public void create() {
        setScreen(new TiledView(this));
        setupGame();
        int spriteId = Player.getUniquePlayerInstance().getSpriteId();
        String imageResource;
        switch (spriteId) {
        case 1:
            imageResource = "person1.png";
            break;
        case 2:
            imageResource = "person2.png";
            break;
        default:
            imageResource = "person3.png";
            break;
        }
        playerImage = new Texture(imageResource);
        batch = new SpriteBatch();
        Gdx.graphics.setContinuousRendering(true);
        Gdx.graphics.requestRendering();

        game.setScore(Game.MAX_SCORE);
        game.setScoreTime(getTime());
    }

    /**
     * Renders game each frame of the game
     */
    @Override
    public void render() {
        super.render();
        updateGameLogic();
    }

    /**
     * Updates score and time of the game
     */
    private void updateGameLogic() {
        long currentTime = getTime();
        long timeSinceLastDecrement = currentTime - game.getScoreTime();

        if (timeSinceLastDecrement >= 1000) {
            game.setScore(game.getScore() - 1);
            game.setScoreTime(currentTime);
        }
    }

    /**
     * Disposes of the native assets in GameViewModel
     */
    @Override
    public void dispose() {
        playerImage.dispose();
        batch.dispose();
    }

    /**
     * Getter for activity
     * @return this activity object
     */

    public LibGdxActivity getActivity() {
        return this.activity;
    }


    public void endGame() {
        new LeaderboardViewModel().addNewEntry(
                Player.getUniquePlayerInstance().getName(),
                game.getScore(),
                new Date());
        activity.navigateToEndGame();
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public void setupGame() {
        Game.getUniqueGameInstance().setStartTime(getTime());
        Game.getUniqueGameInstance().setScoreTime(getTime());
    }

}
