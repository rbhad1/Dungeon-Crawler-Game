package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.example.cs2340c_team28.models.GlobalTime;


public abstract class PowerUp {
    /**
     * Time that the power-up starts.
     */
    private long startTime = GlobalTime.getInstance().getTime();

    /**
     * Duration of the power-up
     */
    private long duration = 5000;

    /**
     * Texture graphic to show on screen
     */
    private Texture texture;

    /**
     * Current state of the power-up
     */
    private State state = State.NOT_ACTIVATED;

    public PowerUp(Texture texture) {
        this.texture = texture;
    }

    /**
     * External method to apply power-up if it is active
     */
    public void apply() {

        if (state == State.NOT_ACTIVATED) {
            this.state = State.ACTIVE;
            this.startTime = GlobalTime.getInstance().getTime();
        }

        if (state == State.ACTIVE) {
            this.doPowerUpEffect();
            if (GlobalTime.getInstance().getTime() > this.startTime + this.duration
                && this.canSafelyDeactivate()) {
                this.state = State.FINISHED;
            }
        }
    }

    /**
     * Internal method to actually do the effect of the powerup
     */
    protected abstract void doPowerUpEffect();

    /**
     * See if the power-up can safely deactivate. By default this is true
     * @return If the game is in a state where the power-up can safely deactivate
     */
    protected boolean canSafelyDeactivate() {
        return true;
    }

    public Texture getTexture() {
        return texture;
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

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public enum State {
        NOT_ACTIVATED,
        ACTIVE,
        FINISHED
    }

}
