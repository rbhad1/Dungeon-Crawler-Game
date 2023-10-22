package com.example.cs2340c_team28.models;

import com.badlogic.gdx.Gdx;

public class ConcreteMovement implements MovementStrategy {
    public void moveUp() {
        int y = Player.getInstance().getY();
        if (y + Gdx.graphics.getHeight() / 16 < Gdx.graphics.getHeight()) {
            Player.getInstance().setY(y + Gdx.graphics.getHeight() / 16);
        }
    }
    @Override
    public void moveDown() {
        int y = Player.getInstance().getY();
        if (y - Gdx.graphics.getHeight() / 16 > 0) {
            Player.getInstance().setY(y - Gdx.graphics.getHeight() / 16);
        }
    }
    @Override
    public void moveLeft() {
        int x = Player.getInstance().getX();
        if (x - Gdx.graphics.getWidth() / 9 > 0) {
            Player.getInstance().setX(x - Gdx.graphics.getWidth() / 9);
        }
    }
    @Override
    public void moveRight() {
        int x = Player.getInstance().getX();
        if (x + Gdx.graphics.getWidth() / 9 < Gdx.graphics.getWidth()) {
            Player.getInstance().setX(x + Gdx.graphics.getWidth() / 9);
        }
    }
}
