package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.example.cs2340c_team28.models.Movable;
import com.example.cs2340c_team28.models.movement.Movement;

public abstract class Enemy extends Movable {

    protected Texture enemyImage;
    protected String imgRes;

    protected Enemy() {

    }


    public void assignTexture() {
        enemyImage = TextureFactory.getInstance().createTexture(imgRes);
    }
    public Texture getTexture() {
        return enemyImage;
    }

    public abstract void move();

    protected boolean shouldNotMove() {
        Movement movement = getCurrentMovement();
        return movement != null
                && (movement.getStatus() == Movement.Status.IN_PROGRESS
                    || movement.getStatus() == Movement.Status.DELAYING);
    }

}
