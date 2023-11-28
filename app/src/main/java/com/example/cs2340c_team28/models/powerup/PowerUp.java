package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.example.cs2340c_team28.models.enemies.TextureFactory;


public abstract class PowerUp {
    protected Texture texture;
    protected String imgRes;
    public PowerUp() {
    }
    protected abstract void activate();
    public void assignTexture() {
        texture = new Texture(imgRes);
    }

    public Texture getTexture() {
        return texture;
    }



}
