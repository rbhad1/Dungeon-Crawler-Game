package com.example.cs2340c_team28.threads;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.cs2340c_team28.R;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

public class GameThread extends ApplicationAdapter {
    /**
     * Current game instance
     */
    private Game game = Game.getUniqueGameInstance();

    /**
     * Texture for the player sprite
     */
    private Texture playerImage;

    /**
     * Batch of sprites to be rendered
     */
    private SpriteBatch batch;

    /**
     * Loads images and handles how often the game renders
     */
    @Override
    public void create() {
        int spriteId = Player.getUniquePlayerInstance().getSpriteId();
        int imageResource;
        switch (spriteId) {
            case 1: imageResource = R.drawable.person1;
            break;
            case 2: imageResource = R.drawable.person2;
            break;
            default: imageResource = R.drawable.person3;
            break;
        }
        playerImage = new Texture(imageResource);
        batch = new SpriteBatch();
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();
    }

    /**
     * Renders game each frame of the game
     */
    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0);
        batch.begin();
        batch.draw(playerImage);
        batch.end();
        updateGameLogic();
    }

    /**
     * Updates score and time of the game
     */
    private void updateGameLogic() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - game.getStartTime();
        game.setTime((double) elapsedTime / 1000.0);
        long timeSinceLastDecrement = currentTime - game.getScoreTime();

        if (timeSinceLastDecrement >= 1000) {
            game.setScore(game.getScore() - 1);
            game.setScoreTime(currentTime);
        }
    }

    /**
     * Disposes of the native assets in GameThread
     */
    @Override
    public void dispose() {
        playerImage.dispose();
        batch.dispose();
    }
}
