package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.example.cs2340c_team28.models.Positionable;

public abstract class PowerUp extends Positionable {

    /**
     * A wrapped powerup to comply with the decorator pattern
     */
    private PowerUp wrapped;

    /**
     * The time at which the powerup started
     */
    private long startTime;

    /**
     * The duration of the powerup
     */
    private final long duration;

    /**
     * The texture corresponding to the powerup
     */
    private final Texture texture;

    /**
     * Construct the powerup
     * @param wrapped The wrapped powerup
     * @param startTime The start time
     * @param duration The duration
     */
    public PowerUp(PowerUp wrapped, long startTime, long duration, Texture texture) {
        this.wrapped = wrapped;
        this.startTime = startTime;
        this.duration = duration;
        this.texture = texture;
    }

    /**
     * Apply this powerup and wrapped powerups
     */
    public void applyAll() {
        if (wrapped != null) {
            wrapped.apply();
        }
        this.apply();
    }

    /**
     * Apply this specific powerup
     */
    protected abstract void apply();

    public void setWrapped(PowerUp wrapped) {
        this.wrapped = wrapped;
    }

    public PowerUp getWrapped() {
        return wrapped;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getDuration() {
        return duration;
    }

    public Texture getTexture() {
        return texture;
    }
}
