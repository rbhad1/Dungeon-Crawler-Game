package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.graphics.Texture;

public class AirEnemy extends Enemy {

    public AirEnemy() {
        super.imgRes = "air_sprite.jpg";
        enemyImage = new Texture(imgRes);
        super.assignTexture();
    }

}


