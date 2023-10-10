package com.example.cs2340c_team28.threads;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

public class GameThread extends com.badlogic.gdx.Game {
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

    /**
     * Loads images and handles how often the game renders
     */
    @Override
    public void create() {
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
        Gdx.graphics.setContinuousRendering(false);
        Gdx.graphics.requestRendering();
    }

    /**
     * Renders game each frame of the game
     */
    @Override
    public void render() {
        super.render();
        ScreenUtils.clear(0, 0, 0, 0); //placeholder
        batch.begin();
        batch.draw(playerImage, 0, 0); //placeholder
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
