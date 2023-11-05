package com.example.cs2340c_team28.models.movement;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position add(Position other) {
        return new Position(this.x + other.x, this.y + other.y);
    }

    public Position subtract(Position other) {
        return new Position(this.x - other.x, this.y - other.y);
    }

    public Position scale(double scaleFactor) {
        return new Position((int) (this.x * scaleFactor), (int) (this.y * scaleFactor));
    }

    public Position negate() {
        return new Position(-this.x, -this.y);
    }

    public Position tileToGraphical() {
        return new Position(this.x * 32, this.y * 32);
    }

    public Position graphicalToTile() {
        return new Position(this.x / 32, this.y / 32);
    }

    public static final Position ZERO = new Position(0, 0);
}
