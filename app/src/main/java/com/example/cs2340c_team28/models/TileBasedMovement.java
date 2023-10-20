package com.example.cs2340c_team28.models;

import com.badlogic.gdx.Gdx;

public class TileBasedMovement implements MovementStrategy {
    public void moveUp() {
        int y = Player.getUniquePlayerInstance().getY();
        if (y + 32 <= 32 * 16) {
            Player.getUniquePlayerInstance().setY(y + 32);
        }
    }
    @Override
    public void moveDown() {
        int y = Player.getUniquePlayerInstance().getY();
        if (y - 32 >= 0) {
            Player.getUniquePlayerInstance().setY(y - 32);
        }
    }
    @Override
    public void moveLeft() {
        int x = Player.getUniquePlayerInstance().getX();
        if (x - 32 >= 0) {
            Player.getUniquePlayerInstance().setX(x - 32);
        }
    }
    @Override
    public void moveRight() {
        int x = Player.getUniquePlayerInstance().getX();
        if (x + 32 < 32 * 9) {
            Player.getUniquePlayerInstance().setX(x + 32);
        }
    }
}
