package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

public class BFSEnemy extends Enemy {

    private PathComponent startingPathComponent = null;

    public BFSEnemy() {
        super.imgRes = "sprites_enemy/enemy5.png";
        super.assignTexture();
    }

    private void determineFastestPath() {

        startingPathComponent = determineFastestPath(0, this.getPosition())
                .pathComponent;
    }

    private FastestPathReturnValue determineFastestPath(int existingLength,
                                                        Position current) {

        if (current.equals(Player.getInstance().getPosition())) {
            return new FastestPathReturnValue(
                    existingLength,
                    new PathComponent(current, null));
        }

        Game game = Game.getInstance();
        TiledMapTileLayer.Cell newCell = game.getWalkableLayer()
                .getCell(current.getX(),
                        current.getY());

        if (newCell != null && newCell.getTile().getId() != 0) {
            return new FastestPathReturnValue(
                    Integer.MAX_VALUE,
                    new PathComponent(current, null));
        }

        // Try paths up, down, left, right
        FastestPathReturnValue up = determineFastestPath(
                existingLength + 1,
                current.add(new Position(0, 1))
        );
        FastestPathReturnValue down = determineFastestPath(
                existingLength + 1,
                current.add(new Position(0, -1))
        );
        FastestPathReturnValue left = determineFastestPath(
                existingLength + 1,
                current.add(new Position(-1, 0))
        );
        FastestPathReturnValue right = determineFastestPath(
                existingLength + 1,
                current.add(new Position(1, 0))
        );
        FastestPathReturnValue[] values = new FastestPathReturnValue[] {up, down, left, right};
        FastestPathReturnValue shortestValue = up;
        int smallestLength = Integer.MAX_VALUE;
        for (FastestPathReturnValue value : values) {
            if (value.length < smallestLength) {
                shortestValue = value;
            }
        }

        return new FastestPathReturnValue(
                existingLength,
                new PathComponent(current, shortestValue.pathComponent)
        );
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
                    this.getPosition(),
                    this.startingPathComponent.position.tileToGraphical(),
                    false,
                    300
            );
            newMovement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
            newMovement.setEndDelay(200);
            this.setCurrentMovement(newMovement);

            // Update the starting path component
            this.startingPathComponent = this.startingPathComponent.next;

        } else {
            determineFastestPath();
        }
    }

    private static class PathComponent {
        private final Position position;
        private final PathComponent next;

        public PathComponent(Position position, PathComponent next) {
            this.position = position;
            this.next = next;
        }
    }

    private static class FastestPathReturnValue {
        private final int length;
        private final PathComponent pathComponent;

        public FastestPathReturnValue(int length, PathComponent pathComponent) {
            this.length = length;
            this.pathComponent = pathComponent;
        }
    }
}
