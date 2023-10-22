package com.example.cs2340c_team28.viewmodels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.example.cs2340c_team28.activities.LibGdxActivity;
import com.example.cs2340c_team28.models.Movable;
import com.example.cs2340c_team28.models.Movement;
import com.example.cs2340c_team28.screens.TiledView;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

import java.util.Date;

public class GameViewModel extends com.badlogic.gdx.Game {
    /**
     * Current game instance
     */
    private final Game game = Game.getInstance();
    private final Player player = Player.getInstance();

    /**
     * Texture for the player sprite
     */
    private Texture playerImage;

    /**
     * Batch of sprites to be rendered
     */
    private SpriteBatch batch;

    private LibGdxActivity activity;
    
    private TiledMap forest;
    private TiledMap water;
    private TiledMap dungeon;

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

        this.forest = new TmxMapLoader().load("forest-map.tmx");
        this.water = new TmxMapLoader().load("water-map.tmx");
        this.dungeon = new TmxMapLoader().load("dungeon-map.tmx");
        game.setCurrentMap(forest);
                
        setupGame();
        int spriteId = player.getSpriteId();
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

        if (Game.getInstance().getCurrentMap().equals(forest)) {
            if (player.getX(true) == 7 && player.getY(true) == 0) {
                Game.getInstance().setCurrentMap(water);
            }
        }
        if (Game.getInstance().getCurrentMap().equals(water)) {
            if (player.getX(true) == 0 && player.getY(false) == 15 * 32) {
                Game.getInstance().setCurrentMap(dungeon);
            }
        }
        
        if (Game.getInstance().getCurrentMap().equals(dungeon)) {
            if (1 < player.getX(true) && player.getX(true) < 7
                    && 0 < player.getY(true) && player.getY(true) < 12) {
                this.endGame();
            }
        }

        handleMovement(player, player.getCurrentMovement());
    }

    private void handleMovement(Movable movable, Movement movement) {
        if (movable == null || movement == null) {
            return;
        }

        if (movement.isComplete()) {
            return;
        }

        int newTileId = game.getWalkableLayer()
                .getCell(movement.getEndTileX(), movement.getEndTileY())
                .getTile().getId();

        // Collision detection here
        if (newTileId != 0) {
            movable.setX(movement.getEndTileX(), true);
            movable.setX(movement.getEndTileY(), true);
        }

        movement.setComplete(true);

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
                player.getName(),
                game.getScore(),
                new Date());
        activity.navigateToEndGame();
    }

    public long getTime() {
        return System.currentTimeMillis();
    }

    public void setupGame() {
        Game.getInstance().setStartTime(getTime());
        Game.getInstance().setScoreTime(getTime());
    }

}
