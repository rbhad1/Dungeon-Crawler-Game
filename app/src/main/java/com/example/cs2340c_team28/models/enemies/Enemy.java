package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.cs2340c_team28.models.Movable;

public abstract class Enemy extends Movable {

//    protected int spriteId;
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


}
