package com.example.cs2340c_team28.models;

public class TileBasedMovement implements MovementStrategy {
    public void moveUp() {
        int y = Player.getInstance().getY();
        if (y + 32 < 32 * 16) {
            Player.getInstance().setY(y + 32);
        }
    }
    @Override
    public void moveDown() {
        int y = Player.getInstance().getY();
        if (y - 32 >= 0) {

            Player.getInstance().setY(y - 32);
        }
    }
    @Override
    public void moveLeft() {
        int x = Player.getInstance().getX();
        if (x - 32 >= 0) {
            // check collision
            if (Player.getInstance().checkCollisionsX()) {
                Player.getInstance().setX(Player.getInstance().getOldX());
            } else {
                Player.getInstance().setX(x - 32);
            }
        }
    }
    @Override
    public void moveRight() {
        int x = Player.getInstance().getX();
        if (x + 32 < 32 * 9) {
            Player.getInstance().setX(x + 32);
        }
    }


}
