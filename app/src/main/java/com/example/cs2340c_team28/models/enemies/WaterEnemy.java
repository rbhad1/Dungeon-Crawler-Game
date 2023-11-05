package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.graphics.Texture;

public class WaterEnemy  extends Enemy {

    public WaterEnemy(int spriteId, String imgRes) {
        super(spriteId, imgRes);
        enemyImage = new Texture(imgRes);
        super.assignTexture();
    }




}
