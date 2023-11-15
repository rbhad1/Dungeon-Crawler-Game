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

public class BFSEnemy extends Enemy {

    private PathComponent startingPathComponent = null;

    private long lastPathFinish = Long.MIN_VALUE;
    private static final long DESIRED_PATH_BREAK_TIME = 3000;

    private Position targetTile = null;
    private final Texture targetTexture =
            TextureFactory.getInstance().createTexture("sprites_enemy/target");

    public BFSEnemy() {
        super.imgRes = "sprites_enemy/enemy5.png";
        super.assignTexture();
    }

    private void determineFastestPath() {

        Game game = Game.getInstance();
        LinkedList<PathComponent> positionsQueue = new LinkedList<>();
        HashSet<Position> visitedPositions = new HashSet<>();
        positionsQueue.add(new PathComponent(this.getPosition(true)));

        PathComponent finalComponent = null;
        while (!positionsQueue.isEmpty()) {
            PathComponent topOfQueue = positionsQueue.pop();
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

            // Then see if it's the correct position. If it is, break.
            if (topOfQueue.position.equals(Player.getInstance().getPosition(true))) {
                finalComponent = topOfQueue;
                break;
            }

            // Add new position options to the queue
            positionsQueue.add(new PathComponent(
                    topOfQueue.position.add(new Position(1, 0)), topOfQueue));
            positionsQueue.add(new PathComponent(
                    topOfQueue.position.add(new Position(-1, 0)), topOfQueue));
            positionsQueue.add(new PathComponent(
                    topOfQueue.position.add(new Position(0, 1)), topOfQueue));
            positionsQueue.add(new PathComponent(
                    topOfQueue.position.add(new Position(0, -1)), topOfQueue));
        }

        // Set up the link going the other way
        for (PathComponent current = finalComponent; current != null; current = current.previous) {
            PathComponent previous = current.previous;
            if (previous != null) {
                // There's still a prior element that we can access
                previous.next = current;
            } else {
                // No prior element, aka we're at the first element
                this.startingPathComponent = current;
            }
        }
        targetTile = finalComponent.position;
    }

    @Override
    public void move() {
        Movement currentMovement = this.getCurrentMovement();
        if (currentMovement != null && (currentMovement.getStatus() == Movement.Status.IN_PROGRESS
                || currentMovement.getStatus() == Movement.Status.DELAYING)) {
            return;
        }

        if (startingPathComponent != null) {
            Movement newMovement = new Movement(
                    this.getPosition(true),
                    this.startingPathComponent.position,
                    true,
                    250
            );
            newMovement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
            // newMovement.setEndDelay(200);
            this.setCurrentMovement(newMovement);

            // Update the starting path component
            this.startingPathComponent = this.startingPathComponent.next;
            if (this.startingPathComponent != null) {
                this.startingPathComponent.previous = null;
            } else {
                // We just exhausted all of our path components
                lastPathFinish = GlobalTime.getInstance().getTime();
            }
        } else if (lastPathFinish + DESIRED_PATH_BREAK_TIME < GlobalTime.getInstance().getTime()) {
            determineFastestPath();
        }
    }

    public Position getTargetTile() {
        return targetTile;
    }

    public Texture getTargetTexture() {
        return targetTexture;
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

    private enum ChaseStatus {
        CHASING, AT_PLAYER, WAITING
    }

    private static class PathComponent {
        private final Position position;
        private PathComponent previous;
        private PathComponent next;

        public PathComponent(Position position) {
            this(position, null);
        }

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
