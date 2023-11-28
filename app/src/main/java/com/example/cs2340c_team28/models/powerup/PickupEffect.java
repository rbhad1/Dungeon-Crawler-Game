package com.example.cs2340c_team28.models.powerup;

import com.example.cs2340c_team28.models.Positionable;

/**
 * Class representing a status effect (for now, just power-ups) that can be picked up.
 * Stores a location by extending positionable, and holds a power-up.
 */
public class PickupEffect extends Positionable {
    private final PowerUp powerUp;

    private boolean collected = false;

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public PickupEffect(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}
