package com.example.cs2340c_team28.models.movement;

import androidx.annotation.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Position) {
            return x == ((Position) o).x && y == ((Position) o).y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @NonNull
    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }
}
