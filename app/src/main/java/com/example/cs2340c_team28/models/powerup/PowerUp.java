package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.graphics.Texture;


public abstract class PowerUp {
    protected static final long POWER_UP_DURATION = 5000;
    protected long powerUpStartTime;
    protected Texture texture;
    protected String imgRes;
    public PowerUp() {
    }
    protected abstract void activate();
    public void assignTexture() {
        texture = new Texture(imgRes);
    }
    //    protected abstract boolean isSuperSpeedComplete();

    public long getPowerUpStartTime() {
        return powerUpStartTime;
    }
    public void setPowerUpDuration(long powerUpStartTime) {
        this.powerUpStartTime = powerUpStartTime;
    }

    public Texture getTexture() {
        return texture;
    }



}
