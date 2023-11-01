package com.example.cs2340c_team28.models;

public class Movement {
    private final Position startGraphical;
    private final Position endGraphical;

    private Position currentGraphical;
    private long duration;

    private boolean complete;
    private boolean collided;

    public Position getStart(boolean tileBased) {
        return tileBased ? startGraphical.graphicalToTile() : startGraphical;
    }

    public Position getEnd(boolean tileBased) {
        return tileBased ? endGraphical.graphicalToTile() : endGraphical;
    }

    public Position getCurrent(boolean tileBased) {
        return tileBased ? currentGraphical.graphicalToTile() : currentGraphical;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isComplete() {
        return complete;
    }

    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Movement(Position start, Position end, boolean tileBased, long duration) {
        if (tileBased) {
            this.startGraphical = start.tileToGraphical();
            this.endGraphical = end.tileToGraphical();
        } else {
            this.startGraphical = start;
            this.endGraphical = end;
        }
    }

    public Movement(Position start, Position end, boolean tileBased) {
        this(start, end, tileBased, 0);
    }
}
