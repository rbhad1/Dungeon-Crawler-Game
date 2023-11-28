package com.example.cs2340c_team28.models.powerup;

import android.media.Image;

import com.badlogic.gdx.graphics.Texture;
import com.example.cs2340c_team28.models.GlobalTime;

import org.w3c.dom.Text;

public abstract class PowerUp {
    protected Texture texture;
    public PowerUp() {
    }
    protected abstract void activate();

    public Texture getTexture() {
        return texture;
    }



}
