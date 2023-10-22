package com.example.cs2340c_team28.models;

public abstract class Movable {
    private Movement currentMovement;

    private int x;
    private int y;

    public int getX() {
        return this.x;
    }

    public int getXByTile() {
        return this.x / 32;
    }

    public int getY() {
        return this.y;
    }

    public int getYByTile() {
        return this.y / 32;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Movement getCurrentMovement() {
        return currentMovement;
    }

    public void setCurrentMovement(Movement currentMovement) {
        this.currentMovement = currentMovement;
    }
}
