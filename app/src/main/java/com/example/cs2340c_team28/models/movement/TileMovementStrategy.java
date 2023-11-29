package com.example.cs2340c_team28.models.movement;

import com.example.cs2340c_team28.models.Player;

public class TileMovementStrategy implements MovementStrategy {

    private static long moveDuration = 100;

    public static long getMoveDuration() {
        return moveDuration;
    }

    @Override
    public void moveUp() {
        if (cannotStartMovement()) {
            return;
        }

        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);

        Player.getInstance().setCurrentMovement(new Movement(
                new Position(x, y),
                new Position(x, y + 1),
                true, moveDuration));
    }
    @Override
    public void moveDown() {
        if (cannotStartMovement()) {
            return;
        }

        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);

        Player.getInstance().setCurrentMovement(new Movement(
                new Position(x, y),
                new Position(x, y - 1),
                true, moveDuration));
    }
    @Override
    public void moveLeft() {
        if (cannotStartMovement()) {
            return;
        }

        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);

        Player.getInstance().setCurrentMovement(new Movement(
                new Position(x, y),
                new Position(x - 1, y),
                true, moveDuration));
    }
    @Override
    public void moveRight() {
        if (cannotStartMovement()) {
            return;
        }

        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);

        Player.getInstance().setCurrentMovement(new Movement(
                new Position(x, y),
                new Position(x + 1, y),
                true, moveDuration));
    }

    private boolean cannotStartMovement() {
        return Player
                .getInstance()
                .getCurrentMovement() != null
                && Player
                .getInstance()
                .getCurrentMovement()
                .getStatus() == Movement.Status.IN_PROGRESS;
    }

}
