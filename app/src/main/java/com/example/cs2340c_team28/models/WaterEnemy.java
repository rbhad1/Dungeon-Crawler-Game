package com.example.cs2340c_team28.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WaterEnemy  extends Enemy {

    Texture enemyImage = new Texture(imgRes); // put this in enemy

    public WaterEnemy(int spriteId, String imgRes) {
        super(spriteId, imgRes);
        enemyImage = new Texture(imgRes);
        super.assignTexture();
    }




}
