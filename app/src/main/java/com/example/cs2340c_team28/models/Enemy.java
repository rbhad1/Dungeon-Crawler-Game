package com.example.cs2340c_team28.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Enemy extends Movable {

    protected int spriteId;
    protected String imageResource;
    protected Texture enemyImage;

    protected Enemy(int spriteId) {
        this.spriteId = spriteId;
//        this.imageResource = imageResource;
//        this.enemyImage = enemyImage;
    }

    public int getSpriteId() {
        return spriteId;
    }
//
//    public String getImageResource() {
//        return imageResource;
//    }
//

}
