package com.example.cs2340c_team28.models.enemies;

import androidx.annotation.NonNull;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.GlobalTime;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.Map;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;
import java.util.Stack;

public class DFSEnemy extends Enemy {

    private static final long MOVEMENT_DURATION = 150;

    private static final long MOVEMENT_END_DELAY = 0;

    private static final long DESIRED_PATH_BREAK_TIME = 3000;

    private BFSEnemy.PathComponent startingPathComponent = null;

    private Set<Position> waypointTileSet = null;

    private long lastPathFinish = GlobalTime.getInstance().getTime();

    private Position targetTile = null;

    /**
     * The texture representing the target (where the BFSEnemy is trying to get to)
     */
    private final Texture targetTexture =
            TextureFactory.getInstance().createTexture("sprites_enemy/DFS-target.png");

    /**
     * The texture representing a waypoint along the BFSEnemy's path
     */
    private final Texture waypointTexture =
            TextureFactory.getInstance().createTexture("sprites_enemy/DFS-waypoint.png");


    public DFSEnemy() {
        super.imgRes = "sprites_enemy/DFS-sprite.png";
        super.assignTexture();
    }

    /**
     * Determine the fastest path for the BFSEnemy. This contains the BFS algorithm.
     */
    public void determineFastestPath() {

        Game game = Game.getInstance();

        // Create a set of positions that we've already visited so we don't re-visit.
        HashSet<Position> visitedPositions = new HashSet<>();

        // Create a stack of position's that we'll be visiting
        Stack<BFSEnemy.PathComponent> pathComponentsStack = new Stack<>();

        // Create a variable representing the component at which we end the path.
        BFSEnemy.PathComponent finalComponent = null;

        // Add the DFSEnemy's current position to the stack
        pathComponentsStack.push(new BFSEnemy.PathComponent(this.getPosition(true)));

        while (!pathComponentsStack.isEmpty()) {
            BFSEnemy.PathComponent topOfStack = pathComponentsStack.pop();

            if (!visitedPositions.contains(topOfStack.position)) {
                visitedPositions.add(topOfStack.position);
            }

            // First see if this is a valid position. If it isn't, continue.
            TiledMapTileLayer.Cell newCell = game.getWalkableLayer()
                    .getCell(topOfStack.position.getX(), topOfStack.position.getY());
            if (newCell == null || newCell.getTile().getId() == 0) {
                continue;
            }

            // Then see if it's the correct position. (aka at player location) If it is, break.
            if (topOfStack.position.equals(Player.getInstance().getPosition(true))) {
                finalComponent = topOfStack;
                break;
            }

            // Add new position options to the stack for each direction from the current tile
            pathComponentsStack.push(new BFSEnemy.PathComponent(
                    topOfStack.position.add(new Position(1, 0)), topOfStack));
            pathComponentsStack.push(new BFSEnemy.PathComponent(
                    topOfStack.position.add(new Position(-1, 0)), topOfStack));
            pathComponentsStack.push(new BFSEnemy.PathComponent(
                    topOfStack.position.add(new Position(0, 1)), topOfStack));
            pathComponentsStack.push(new BFSEnemy.PathComponent(
                    topOfStack.position.add(new Position(0, -1)), topOfStack));

        }

        // We are now finished with the loop and *might* have a valid ending location.
        // We've chained all the path components so that they point to their previous element
        //  so now we'll create forward links so that we can move along the path efficiently
        waypointTileSet = new HashSet<>();
        for (BFSEnemy.PathComponent current = finalComponent; current != null; current = current.previous) {
            // Add positions to the waypoint tile set
            waypointTileSet.add(current.position);

            // Get the previous path component, and if not null, set its next value to current
            BFSEnemy.PathComponent previous = current.previous;
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

    public BFSEnemy.ChaseStatus getChaseStatus() {
        if (this.getPosition(true)
                .equals(Player.getInstance().getPosition(true))) {
            return BFSEnemy.ChaseStatus.AT_PLAYER;
        } else if (this.startingPathComponent == null) {
            return BFSEnemy.ChaseStatus.WAITING;
        } else {
            return BFSEnemy.ChaseStatus.CHASING;
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
