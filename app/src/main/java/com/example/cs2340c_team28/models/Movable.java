package com.example.cs2340c_team28.models;

public abstract class Movable {
    private Movement currentMovement;

    private int x;
    private int y;

    /**
     * Get the current x position
     * @param tileRelative If true, give position as number of tiles
     * @return x position
     */
    public int getX(boolean tileRelative) {
        return this.x / (tileRelative ? 32 : 1);
    }

    /**
     * Get the current y position
     * @param tileRelative If true, give position as number of tiles
     * @return y position
     */
    public int getY(boolean tileRelative) {
        return this.y / (tileRelative ? 32 : 1);
    }

    /**
     * Get the current x position
     * @param x The new position
     * @param tileRelative If true, input is number of tiles rather than points
     */
    public void setX(int x, boolean tileRelative) {
        this.x = x * (tileRelative ? 32 : 1);
    }

    /**
     * Get the current y position
     * @param y The new position
     * @param tileRelative If true, input is number of tiles rather than points
     */
    public void setY(int y, boolean tileRelative) {
        this.y = y * (tileRelative ? 32 : 1);
    }

    public Movement getCurrentMovement() {
        return currentMovement;
    }

    public void setCurrentMovement(Movement currentMovement) {
        this.currentMovement = currentMovement;
    }
}
