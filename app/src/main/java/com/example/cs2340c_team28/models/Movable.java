package com.example.cs2340c_team28.models;

public abstract class Movable {
    private Movement currentMovement;

    public Movement getCurrentMovement() {
        return currentMovement;
    }

    public void setCurrentMovement(Movement currentMovement) {
        this.currentMovement = currentMovement;
    }
}
