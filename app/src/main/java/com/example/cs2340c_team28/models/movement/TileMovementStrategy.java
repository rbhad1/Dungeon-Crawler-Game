package com.example.cs2340c_team28.models.movement;

import com.example.cs2340c_team28.models.Player;

public class TileMovementStrategy implements MovementStrategy {

    public static long MOVE_DURATION = 100;


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
                true, MOVE_DURATION));
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
                true, MOVE_DURATION));
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
                true, MOVE_DURATION));
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
                true, MOVE_DURATION));
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
