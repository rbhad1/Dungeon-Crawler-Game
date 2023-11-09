package com.example.cs2340c_team28.models.movement;

public class Movement {
    private final Position startGraphical;
    private final Position endGraphical;

    private long startTime;
    private final long duration;

    private long endDelay;

    private Status status = Status.IN_PROGRESS;

    private CollisionStyle collisionStyle = CollisionStyle.ANIMATED;

    public Position getStart(boolean tileBased) {
        return tileBased ? startGraphical.graphicalToTile() : startGraphical;
    }

    public Position getEnd(boolean tileBased) {
        return tileBased ? endGraphical.graphicalToTile() : endGraphical;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndDelay() {
        return endDelay;
    }

    public void setEndDelay(long endDelay) {
        this.endDelay = endDelay;
    }

    public CollisionStyle getCollisionStyle() {
        return collisionStyle;
    }

    public void setCollisionStyle(CollisionStyle collisionStyle) {
        this.collisionStyle = collisionStyle;
    }

    public long getDuration() {
        return duration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

    public Movement(Position start, Position end,
                    boolean tileBased, long duration, long startTime) {
        if (tileBased) {
            this.startGraphical = start.tileToGraphical();
            this.endGraphical = end.tileToGraphical();
        } else {
            this.startGraphical = start;
            this.endGraphical = end;
        }

        this.duration = duration;
        this.startTime = startTime;
    }

    public Movement(Position start, Position end, boolean tileBased, long duration) {
        this(start, end, tileBased, duration, -1);
    }

    public Movement(Position start, Position end, boolean tileBased) {
        this(start, end, tileBased, 0);
    }

    public enum Status {
        IN_PROGRESS, DELAYING, COMPLETE, COLLIDED
    }

    public enum CollisionStyle {
        IGNORE_COLLISIONS,
        PRECHECK,
        ANIMATED
    }
}
