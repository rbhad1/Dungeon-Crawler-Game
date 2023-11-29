package com.example.cs2340c_team28.models;

import com.example.cs2340c_team28.models.movement.Movement;

public abstract class Movable extends Positionable {
    private Movement currentMovement;

    public Movement getCurrentMovement() {
        return currentMovement;
    }

    public void setCurrentMovement(Movement currentMovement) {
        this.currentMovement = currentMovement;
    }
}
