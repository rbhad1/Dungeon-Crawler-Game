package com.example.cs2340c_team28.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FireEnemy extends Enemy {


    public FireEnemy(int spriteId, String imgRes) {
        super(spriteId, imgRes);
        enemyImage = new Texture(imgRes);
        super.assignTexture();

    }

}
