package com.example.cs2340c_team28.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GroundEnemy extends Movable implements Enemy {
    private int spriteId;
    private Texture enemyImage;

    private Sprite sprite;

    private SpriteBatch batch;
    String imageResource;
    public GroundEnemy() {

    }



    // TODO: update name of file when sprites are ready
    @Override
    public void render() {
        imageResource = null;
        enemyImage = new Texture(imageResource);
        batch = new SpriteBatch();
        batch.begin();
        batch.draw(enemyImage,32, 32);
        batch.end();

    }
    public int getSpriteId() {
        return spriteId;
    }
}
