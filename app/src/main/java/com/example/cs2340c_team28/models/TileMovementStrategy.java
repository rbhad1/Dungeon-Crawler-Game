package com.example.cs2340c_team28.models;

public class TileMovementStrategy implements MovementStrategy {

    @Override
    public void moveUp() {
        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);

        Player.getInstance().setCurrentMovement(new Movement(
                new Position(x, y),
                new Position(x, y + 1),
                true));
    }
    @Override
    public void moveDown() {
        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);

        Player.getInstance().setCurrentMovement(new Movement(
                new Position(x, y),
                new Position(x, y - 1),
                true));
    }
    @Override
    public void moveLeft() {
        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);

        Player.getInstance().setCurrentMovement(new Movement(
                new Position(x, y),
                new Position(x - 1, y),
                true));
    }
    @Override
    public void moveRight() {
        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);

        Player.getInstance().setCurrentMovement(new Movement(
                new Position(x, y),
                new Position(x + 1, y),
                true));
    }


}
