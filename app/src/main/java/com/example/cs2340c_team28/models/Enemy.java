package com.example.cs2340c_team28.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Enemy extends Movable {

    protected int spriteId;
    protected Texture enemyImage;
    protected String imgRes;

    protected Enemy(int spriteId,String  imgRes) {
        this.spriteId = spriteId;
        this.imgRes = imgRes;

    }

    public void assignTexture() {
        enemyImage = new Texture(imgRes);
    }
    public Texture getTexture() {
        return enemyImage;
    }
    public int getSpriteId() {
        return spriteId;
    }

}
