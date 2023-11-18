package com.example.cs2340c_team28.models.enemies;

import androidx.annotation.NonNull;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.GlobalTime;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BFSEnemy extends Enemy {

    /**
     * The duration of the movement.
     * <p>
     * Note that while this is constant now we might want to implement this differently so that
     *  movement duration can be different based on difficulty.
     */
    private static final long MOVEMENT_DURATION = 150;

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
    private static final long DESIRED_PATH_BREAK_TIME = 3000;

    /**
     * The next path component to process to start or continue movement along a path.
     * If this is null, there is no currently-generated path.
     * <p>
     * This variable effectively represents the starting node of a doubly-linked list.
     * See {@link PathComponent} for more information about how path components link together.
     */
    private PathComponent startingPathComponent = null;

    /**
     * The set of positions that are on our path that we haven't visited yet.
     * This should correspond to the same positions that are in the path components but serves as
     *  a more efficient way to access them
     */
    private Set<Position> waypointTileSet = null;

    /**
     * The time that the most recent path finished.
     * This is used so that we can figure out when to calculate the next path.
     */
    private long lastPathFinish = GlobalTime.getInstance().getTime();

    /**
     * The tile that the BFSEnemy is targeting. This should be the same as the position
     *  in the last element of the path components linked list that {@link #startingPathComponent}
     *  points to.
     */
    private Position targetTile = null;

    /**
     * The texture representing the target (where the BFSEnemy is trying to get to)
     */
    private final Texture targetTexture =
            TextureFactory.getInstance().createTexture("sprites_enemy/BFS-target.png");

    /**
     * The texture representing a waypoint along the BFSEnemy's path
     */
    private final Texture waypointTexture =
            TextureFactory.getInstance().createTexture("sprites_enemy/BFS-waypoint.png");

    public BFSEnemy() {
        super.imgRes = "sprites_enemy/BFS-sprite.png";
        super.assignTexture();
    }

    /**
     * Determine the fastest path for the BFSEnemy. This contains the BFS algorithm.
     */
    private void determineFastestPath() {

        Game game = Game.getInstance();
        // Create a queue of positions that we'll be visiting
        LinkedList<PathComponent> pathComponentsQueue = new LinkedList<>();
        // Create a set of positions that we've already visited so we don't re-visit.
        HashSet<Position> visitedPositions = new HashSet<>();
        // Add the BFSEnemy's current position to the queue since this is where we'll start from
        pathComponentsQueue.add(new PathComponent(this.getPosition(true)));

        // Create a variable representing the component at which we end the path.
        PathComponent finalComponent = null;

        // Loop while there are still path components to visit
        while (!pathComponentsQueue.isEmpty()) {
            // Get the pathc component off the top of the queue
            PathComponent topOfQueue = pathComponentsQueue.pop();
            // Check if we've already visited the position
            if (visitedPositions.contains(topOfQueue.position)) {
                // Don't check this position again
                continue;
            } else {
                visitedPositions.add(topOfQueue.position);
            }

            // First see if this is a valid position. If it isn't, continue.
            TiledMapTileLayer.Cell newCell = game.getWalkableLayer()
                    .getCell(topOfQueue.position.getX(), topOfQueue.position.getY());
            if (newCell == null || newCell.getTile().getId() == 0) {
                continue;
            }

            // Then see if it's the correct position. (aka at player location) If it is, break.
            if (topOfQueue.position.equals(Player.getInstance().getPosition(true))) {
                finalComponent = topOfQueue;
                break;
            }

            // Add new position options to the queue for each direction from the current tile
            pathComponentsQueue.add(new PathComponent(
                    topOfQueue.position.add(new Position(1, 0)), topOfQueue));
            pathComponentsQueue.add(new PathComponent(
                    topOfQueue.position.add(new Position(-1, 0)), topOfQueue));
            pathComponentsQueue.add(new PathComponent(
                    topOfQueue.position.add(new Position(0, 1)), topOfQueue));
            pathComponentsQueue.add(new PathComponent(
                    topOfQueue.position.add(new Position(0, -1)), topOfQueue));
        }

        // We are now finished with the loop and *might* have a valid ending location.
        // We've chained all the path components so that they point to their previous element
        //  so now we'll create forward links so that we can move along the path efficiently
        waypointTileSet = new HashSet<>();
        for (PathComponent current = finalComponent; current != null; current = current.previous) {
            // Add positions to the waypoint tile set
            waypointTileSet.add(current.position);

            // Get the previous path component, and if not null, set its next value to current
            PathComponent previous = current.previous;
            if (previous != null) {
                // There's still a prior element that we can access
                previous.next = current;
            } else {
                // No prior element, aka we're at the first element
                this.startingPathComponent = current;
            }
        }

        // Make sure that the final component isn't null (aka we actually have a valid path)
        if (finalComponent != null) {
            targetTile = finalComponent.position;
        }

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
        if (startingPathComponent != null) {
            // Create a new movement to the next space along the path. Note that we go tile by tile.
            Movement newMovement = new Movement(
                    this.getPosition(true),
                    this.startingPathComponent.position,
                    true,
                    MOVEMENT_DURATION
            );
            // Ignore collisions even though we should be going on the valid path anyway
            newMovement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
            // Set the end delay and then set the movement
            newMovement.setEndDelay(MOVEMENT_END_DELAY);
            this.setCurrentMovement(newMovement);

            // Remove the position at the end of this movement from the waypointTileSet
            this.waypointTileSet.remove(this.startingPathComponent.position);

            // Update the starting path component to be the next path component
            this.startingPathComponent = this.startingPathComponent.next;

            if (this.startingPathComponent != null) {
                // The startingPathComponent is not null, we still have more path
                // Set the previous component to null so that it can be garbage-collected
                this.startingPathComponent.previous = null;
            } else {
                // The startingPathComponent is null, means we finished all of our path components.
                lastPathFinish = GlobalTime.getInstance().getTime();
            }
        } else if (lastPathFinish + DESIRED_PATH_BREAK_TIME < GlobalTime.getInstance().getTime()) {
            // We don't have a path currently but we're at the time where we can make another one
            determineFastestPath();
        }
    }

    public Position getTargetTile() {
        return targetTile;
    }

    public Texture getTargetTexture() {
        return targetTexture;
    }

    public Texture getWaypointTexture() {
        return waypointTexture;
    }

    public Set<Position> getWaypointTileSet() {
        return waypointTileSet;
    }

    public ChaseStatus getChaseStatus() {
        if (this.getPosition(true)
                .equals(Player.getInstance().getPosition(true))) {
            return ChaseStatus.AT_PLAYER;
        } else if (this.startingPathComponent == null) {
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

    /**
     * Class representing components along a path to the player.
     * This is essential for the BFS algorithm and creating "chains" of possible paths.
     * Note that this effectively creates a LinkedList with its previous and next variables.
     */
    private static class PathComponent {
        /**
         * The position represented by this path component
         */
        private final Position position;

        /**
         * The path component that comes before this one
         */
        private PathComponent previous;

        /**
         * The path component that comes after this one
         */
        private PathComponent next;

        /**
         * Construct the path component
         * @param position The position that the path component represents
         */
        public PathComponent(Position position) {
            this(position, null);
        }

        /**
         * Construct the path component
         * @param position The position that the path component represents
         * @param previous The previous path component
         */
        public PathComponent(Position position, PathComponent previous) {
            this.position = position;
            this.previous = previous;
        }

        @NonNull
        @Override
        public String toString() {
            return "PathComponent{" + "position=" + position + '}';
        }
    }
}
