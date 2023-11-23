package com.example.cs2340c_team28.models.enemies.trackers;

import com.example.cs2340c_team28.models.movement.Position;

import java.util.Set;

public class GeneratedPath {
    /**
     * The path component from which to start the path.
     * It should be assumed that each path component has a previous/next link,
     *  except for the first component which will only have a next link.
     * This variable effectively represents the starting node of a doubly-linked list.
     * <p>
     * See {@link PathComponent} for more information about how path components link together.
     */
    private PathComponent startingPathComponent;

    /**
     * The set of positions that are on our path that we haven't visited yet.
     * This should correspond to the same positions that are in the path components but serves as
     *  a more efficient way to access them
     */
    private Set<Position> waypointTileSet;

    /**
     * The tile that the enemy is targeting. This should be the same as the position
     *  in the last element of the path components linked list that {@link #startingPathComponent}
     *  points to.
     */
    private Position endingTile;

    public GeneratedPath(PathComponent startingPathComponent,
                         Set<Position> waypointTileSet,
                         Position endingTile) {
        this.startingPathComponent = startingPathComponent;
        this.waypointTileSet = waypointTileSet;
        this.endingTile = endingTile;
    }

    public PathComponent getStartingPathComponent() {
        return startingPathComponent;
    }

    void setStartingPathComponent(PathComponent startingPathComponent) {
        this.startingPathComponent = startingPathComponent;
    }

    public Set<Position> getWaypointTileSet() {
        return waypointTileSet;
    }

    void setWaypointTileSet(Set<Position> waypointTileSet) {
        this.waypointTileSet = waypointTileSet;
    }

    public Position getEndingTile() {
        return endingTile;
    }

    void setEndingTile(Position endingTile) {
        this.endingTile = endingTile;
    }
}