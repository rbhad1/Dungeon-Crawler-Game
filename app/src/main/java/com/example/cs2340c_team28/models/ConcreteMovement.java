package com.example.cs2340c_team28.models;

import com.badlogic.gdx.Gdx;

public class ConcreteMovement implements MovementStrategy {

    // setting collision layer


    Player player = Player.getInstance();

    boolean collision = false;
    public void moveUp() {
        int y = player.getY();
        if (y + Gdx.graphics.getHeight() / 16 < Gdx.graphics.getHeight()) {
                // if true: then update accordingly
            // else
            player.setY(y + Gdx.graphics.getHeight() / 16);
        }
    }
    @Override
    public void moveDown() {
        int y = player.getY();
        if (y - Gdx.graphics.getHeight() / 16 > 0) {
            player.setY(y - Gdx.graphics.getHeight() / 16);
        }
    }
    @Override
    public void moveLeft() {
        int x = player.getX();
        if (x - Gdx.graphics.getWidth() / 9 > 0) {
            if (player.checkCollisionsX()) {
                player.setX(player.getOldX());
            } else {
                player.setX(x - Gdx.graphics.getWidth() / 9);
            }
        }
    }
    @Override
    public void moveRight() {
        int x = player.getX();
        if (x + Gdx.graphics.getWidth() / 9 < Gdx.graphics.getWidth()) {
            player.setX(x + Gdx.graphics.getWidth() / 9);
        }
    }
}
