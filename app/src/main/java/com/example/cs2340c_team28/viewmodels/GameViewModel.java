package com.example.cs2340c_team28.viewmodels;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.example.cs2340c_team28.activities.LibGdxActivity;
import com.example.cs2340c_team28.models.Movable;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;
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
    
    protected TiledMap forest;
    protected TiledMap water;
    protected TiledMap dungeon;

    public TiledMap getForest() {
        return forest;
    }

    public TiledMap getWater() {
        return water;
    }

    public TiledMap getDungeon() {
        return dungeon;
    }

    public GameViewModel(LibGdxActivity activity) {
        this.activity = activity;
    }

    public GameViewModel() {
        this.activity = new LibGdxActivity();
    }

    protected void loadAssets() {
        this.forest = new TmxMapLoader().load("forest-map.tmx");
        this.water = new TmxMapLoader().load("water-map.tmx");
        this.dungeon = new TmxMapLoader().load("dungeon-map.tmx");
        game.setCurrentMap(forest);
    }

    /**
     * Loads images and handles how often the game renders
     */
    @Override
    public void create() {
        setScreen(new TiledView(this));

        this.loadAssets();

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
    public void updateGameLogic() {
        long currentTime = getTime();
        long timeSinceLastDecrement = currentTime - game.getScoreTime();

        if (timeSinceLastDecrement >= 1000) {
            if (game.getScore() > 0) {
                game.setScore(game.getScore() - 1);
            }
            game.setScoreTime(currentTime);
        }

        TiledMapTileLayer.Cell possibleDoorCell = game.getDoorLayer()
                .getCell(player.getX(true), player.getY(true));
        // Check if we've reached door
        if (possibleDoorCell != null && possibleDoorCell.getTile().getId() != 0) {
            if (game.getCurrentMap().equals(forest)) {
                Game.getInstance().setCurrentMap(water);
            } else if (game.getCurrentMap().equals(water)) {
                Game.getInstance().setCurrentMap(dungeon);
            } else if (game.getCurrentMap().equals(dungeon)) {
                this.endGame();
            }
        }

        handleMovement(player);
    }

    private void handleMovement(Movable movable) {

        // Null-check the Movable
        if (movable == null) {
            return;
        }

        // Eet and null-check the Movement
        Movement movement = movable.getCurrentMovement();
        if (movement == null) {
            return;
        }

        // Ensure movement still in progress
        if (movement.getStatus() != Movement.Status.IN_PROGRESS) {
            return;
        }

        // Get start position and end position, make a delta tile also
        Position startGraphical = movement.getStart(false);
        Position endGraphical = movement.getEnd(false);
        Position deltaGraphical = endGraphical.subtract(startGraphical);

        // If movement collision style is precheck,
        //  go ahead and make sure end position won't collide
        if (movement.getCollisionStyle() == Movement.CollisionStyle.PRECHECK) {
            // Figure out what cell we're going to
            TiledMapTileLayer.Cell newCell = game.getWalkableLayer()
                    .getCell(endGraphical.graphicalToTile().getX(),
                            endGraphical.graphicalToTile().getY());

            if (newCell != null && newCell.getTile().getId() != 0) {
                movement.setStatus(Movement.Status.COLLIDED);
                movable.setX(startGraphical.getX(), false);
                movable.setX(startGraphical.getY(), false);
            }
        }

        // Get the current time and start time (from the movement)
        long currentTime = getTime();
        long initialTime = movement.getStartTime();

        // Check that the start time has been set for the movement.
        // If not, set its start time to now
        if (initialTime == -1) {
            movement.setStartTime(currentTime);
            initialTime = currentTime;
        }

        // Calculate elapsed time (deltaTime) and percent complete for the movement
        long deltaTime = currentTime - initialTime;
        long duration = movement.getDuration();

        Position currentGraphical;
        Position eventualGraphical;
        double percentComplete;

        if (duration > 0) {
            percentComplete = Math.min((double) deltaTime / movement.getDuration(), 1.0);

            // Calculate what should be current position, based on movement time
            currentGraphical = startGraphical.add(deltaGraphical.scale(percentComplete));

            // Calculate what will be an eventual position
            eventualGraphical = startGraphical
                    .add(deltaGraphical.scale(Math.min(percentComplete + 0.2, 1.0)))
                    .add(new Position(16, 16));
        } else {
            percentComplete = 1.0;
            currentGraphical = endGraphical;
            eventualGraphical = endGraphical;
        }

        // Figure out what cell we're mostly on
        TiledMapTileLayer.Cell newCell = game.getWalkableLayer()
                .getCell(eventualGraphical.graphicalToTile().getX(),
                        eventualGraphical.graphicalToTile().getY());

        // Collision detection here
        // Either look at the new cell or just ignore collision detection
        // Ignoring collision detection is useful if we have a mob that can traverse non-paths
        //  or if we need to go back from a space on which we "collided"
        if (movement.getCollisionStyle() == Movement.CollisionStyle.IGNORE_COLLISIONS
                || (eventualGraphical.getX() > 0
                && eventualGraphical.getY() > 0
                && newCell != null && newCell.getTile().getId() != 0)) {
            // End tile is valid
            movable.setX(currentGraphical.getX(), false);
            movable.setY(currentGraphical.getY(), false);
            if (percentComplete >= 1.0) {
                movement.setStatus(Movement.Status.COMPLETE);
            }
        } else {
            // implicit collision here, set status to collided and return to start position
            movement.setStatus(Movement.Status.COLLIDED);
            movable.setCurrentMovement(
                    new Movement(
                            currentGraphical,
                            startGraphical,
                            false,
                            50
                    )
            );
            movable.getCurrentMovement().setCollisionStyle(
                    Movement.CollisionStyle.IGNORE_COLLISIONS);
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
        Player.getInstance().setCurrentMovement(null);
    }

}
