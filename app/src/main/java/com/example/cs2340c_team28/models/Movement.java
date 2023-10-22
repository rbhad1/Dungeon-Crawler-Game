package com.example.cs2340c_team28.models;

public class Movement {
    private final int startTileX;
    private final int startTileY;

    private final int endTileX;
    private final int endTileY;

    private boolean complete;

    public int getStartTileX() {
        return startTileX;
    }

    public int getStartTileY() {
        return startTileY;
    }

    public int getEndTileX() {
        return endTileX;
    }

    public int getEndTileY() {
        return endTileY;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public Movement(int startTileX, int startTileY, int endTileX, int endTileY) {
        this.startTileX = startTileX;
        this.startTileY = startTileY;
        this.endTileX = endTileX;
        this.endTileY = endTileY;
    }
}
