package com.example.cs2340c_team28.models.powerup;

import com.example.cs2340c_team28.models.Positionable;

/**
 * Class representing a status effect (for now, just power-ups) that can be picked up.
 * Stores a location by extending positionable, and holds a power-up.
 */
public class PickupEffect extends Positionable {
    private final PowerUp powerUp;

    public PickupEffect(PowerUp powerUp) {
        this.powerUp = powerUp;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}
