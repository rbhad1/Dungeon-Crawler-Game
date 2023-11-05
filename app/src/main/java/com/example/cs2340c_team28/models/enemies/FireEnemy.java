package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.graphics.Texture;

public class FireEnemy extends Enemy {



    public FireEnemy() {
        super.imgRes = "fire_sprite.jpg";
        enemyImage = new Texture(imgRes);
        super.assignTexture();

    }

}
