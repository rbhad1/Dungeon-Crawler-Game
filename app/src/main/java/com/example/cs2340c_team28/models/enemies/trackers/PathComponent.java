package com.example.cs2340c_team28.models.enemies.trackers;

import androidx.annotation.NonNull;

import com.example.cs2340c_team28.models.movement.Position;

/**
 * Class representing components along a path to the player.
 * This is essential for the BFS/DFS algorithm and creating "chains" of possible paths.
 * Note that this effectively creates a LinkedList with its previous and next variables.
 */
class PathComponent {

    /**
     * The position represented by this path component
     */
    private final Position position;

    /**
     * The path component that comes before this one
     */
    private PathComponent previous;

    /**
     * The path component that comes after this one
     */
    private PathComponent next;

    /**
     * Construct the path component
     * @param position The position that the path component represents
     */
    public PathComponent(Position position) {
        this(position, null);
    }

    /**
     * Construct the path component
     * @param position The position that the path component represents
     * @param previous The previous path component
     */
    public PathComponent(Position position, PathComponent previous) {
        this.position = position;
        this.previous = previous;
    }

    public Position getPosition() {
        return position;
    }

    public PathComponent getPrevious() {
        return previous;
    }

    public void setPrevious(PathComponent previous) {
        this.previous = previous;
    }

    public PathComponent getNext() {
        return next;
    }

    public void setNext(PathComponent next) {
        this.next = next;
    }

    @NonNull
    @Override
    public String toString() {
        return "PathComponent{" + "position=" + position + '}';
    }
}
