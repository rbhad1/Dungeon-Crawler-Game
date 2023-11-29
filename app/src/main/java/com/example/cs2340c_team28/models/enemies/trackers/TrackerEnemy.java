package com.example.cs2340c_team28.models.enemies.trackers;

import com.badlogic.gdx.graphics.Texture;
import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.GlobalTime;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.enemies.Enemy;
import com.example.cs2340c_team28.models.enemies.TextureFactory;
import com.example.cs2340c_team28.models.movement.Movement;

import java.util.HashSet;

public class TrackerEnemy extends Enemy {

    /**
     * The duration of the movement.
     */
    private long movementDuration = setMovementDuration();

    /**
     * Get the movement duration
     * @return the movement duration
     */
    public long getMovementDuration() {
        return movementDuration;
    }

    /**
     * Sets the speed of the enemy based on difficulty.
     *
     * @return The new movement duration that was set
     */
    public long setMovementDuration() {
        Game game = Game.getInstance();
        Difficulty difficulty = game.getDifficulty();

        switch (difficulty) {
        case EASY:
            movementDuration = 150;
            break;
        case MEDIUM:
            movementDuration = 110;
            break;
        default:
            movementDuration = 90;
            break;
        }

        return movementDuration;
    }

    /**
     * The time duration to delay after each individual movement.
     * If 0, the BFSEnemy will appear to follow a smooth path
     */
    private static final long MOVEMENT_END_DELAY = 0;

    /**
     * The time duration to wait to follow the player again after a path is finished.
     * <p>
     * Note that while this is constant now we might want to implement this differently so that
     *  it can be different based on difficulty.
     */
    private static final long DESIRED_PATH_BREAK_TIME = 1500;

    /**
     * The next path component to process to start or continue movement along a path.
     * If {@link GeneratedPath#getStartingPathComponent()} returns null,
     *  there is no currently-generated path.
     */
    private GeneratedPath generatedPath = new GeneratedPath(
            null, new HashSet<>(), null);

    /**
     * The time that the most recent path finished.
     * This is used so that we can figure out when to calculate the next path.
     */
    private long lastPathFinish = GlobalTime.getInstance().getTime();

    /**
     * The texture representing the target (where the enemy is trying to get to)
     */
    private final Texture targetTexture =
            TextureFactory.getInstance().createTexture("sprites_enemy/BFS-target.png");

    /**
     * The texture representing a waypoint along the enemy's path
     */
    private final Texture waypointTexture =
            TextureFactory.getInstance().createTexture("sprites_enemy/BFS-waypoint.png");

    /**
     * The strategy to use for tracking the player
     */
    private final TrackerStrategy trackerStrategy;

    public TrackerEnemy(TrackerStrategy trackerStrategy) {
        super.imgRes = "sprites_enemy/BFS-sprite.png";
        super.assignTexture();
        this.trackerStrategy = trackerStrategy;
    }

    @Override
    public void move() {
        // Get the current movement
        Movement currentMovement = this.getCurrentMovement();
        // Check that the current movement isn't still in progress or delaying
        if (currentMovement != null && (currentMovement.getStatus() == Movement.Status.IN_PROGRESS
                || currentMovement.getStatus() == Movement.Status.DELAYING)) {
            return;
        }

        // See if we have a valid path to follow (aka startingPathComponent != null)
        if (generatedPath.getStartingPathComponent() != null) {
            // Create a new movement to the next space along the path. Note that we go tile by tile.
            Movement newMovement = new Movement(
                    this.getPosition(true),
                    this.generatedPath.getStartingPathComponent().getPosition(),
                    true,
                    movementDuration
            );
            // Ignore collisions even though we should be going on the valid path anyway
            newMovement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
            // Set the end delay and then set the movement
            newMovement.setEndDelay(MOVEMENT_END_DELAY);
            this.setCurrentMovement(newMovement);

            // Remove the position at the end of this movement from the waypointTileSet
            generatedPath.getWaypointTileSet().remove(
                    generatedPath.getStartingPathComponent().getPosition());

            // Update the starting path component to be the next path component
            generatedPath.setStartingPathComponent(
                    generatedPath.getStartingPathComponent().getNext());

            if (generatedPath.getStartingPathComponent() != null) {
                // The startingPathComponent is not null, we still have more path
                // Set the previous component to null so that it can be garbage-collected
                generatedPath.getStartingPathComponent().setPrevious(null);
            } else {
                // The startingPathComponent is null, means we finished all of our path components.
                lastPathFinish = GlobalTime.getInstance().getTime();
            }
        } else if (lastPathFinish + DESIRED_PATH_BREAK_TIME < GlobalTime.getInstance().getTime()) {
            // We don't have a path currently but we're at the time where we can make another one
            generatedPath = trackerStrategy.generatePath(this.getPosition(true));
        }
    }

    public Texture getTargetTexture() {
        return targetTexture;
    }

    public Texture getWaypointTexture() {
        return waypointTexture;
    }

    public GeneratedPath getGeneratedPath() {
        return generatedPath;
    }

    public ChaseStatus getChaseStatus() {
        if (this.getPosition(true)
                .equals(Player.getInstance().getPosition(true))) {
            return ChaseStatus.AT_PLAYER;
        } else if (this.generatedPath.getStartingPathComponent() == null) {
            return ChaseStatus.WAITING;
        } else {
            return ChaseStatus.CHASING;
        }
    }

    /**
     * Enum representing the status of how the BFSEnemy is chasing the player
     */
    public enum ChaseStatus {
        /**
         * Currently chasing the player
         */
        CHASING,

        /**
         * Currently at the player's position
         */
        AT_PLAYER,

        /**
         * Not currently chasing and waiting for the time where it can chase again
         */
        WAITING
    }
}
