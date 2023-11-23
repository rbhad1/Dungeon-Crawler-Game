package com.example.cs2340c_team28.models.enemies.trackers;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class TrackerStrategyBFS implements TrackerStrategy {
    @Override
    public GeneratedPath generatePath(Position originTile) {
        // Init eventual return values
        PathComponent startingPathComponent = null;
        Set<Position> waypointTileSet = new HashSet<>();
        Position targetTile = null;

        Game game = Game.getInstance();
        // Create a queue of positions that we'll be visiting
        LinkedList<PathComponent> pathComponentsQueue = new LinkedList<>();
        // Create a set of positions that we've already visited so we don't re-visit.
        HashSet<Position> visitedPositions = new HashSet<>();
        // Add the BFSEnemy's current position to the queue since this is where we'll start from
        pathComponentsQueue.add(new PathComponent(originTile));

        // Create a variable representing the component at which we end the path.
        PathComponent finalComponent = null;

        // Loop while there are still path components to visit
        while (!pathComponentsQueue.isEmpty()) {
            // Get the pathc component off the top of the queue
            PathComponent topOfQueue = pathComponentsQueue.pop();
            // Check if we've already visited the position
            if (visitedPositions.contains(topOfQueue.getPosition())) {
                // Don't check this position again
                continue;
            } else {
                visitedPositions.add(topOfQueue.getPosition());
            }

            // First see if this is a valid position. If it isn't, continue.
            TiledMapTileLayer.Cell newCell = game.getWalkableLayer()
                    .getCell(topOfQueue.getPosition().getX(), topOfQueue.getPosition().getY());
            if (newCell == null || newCell.getTile().getId() == 0) {
                continue;
            }

            // Then see if it's the correct position. (aka at player location) If it is, break.
            if (topOfQueue.getPosition().equals(Player.getInstance().getPosition(true))) {
                finalComponent = topOfQueue;
                break;
            }

            // Add new position options to the queue for each direction from the current tile
            pathComponentsQueue.add(new PathComponent(
                    topOfQueue.getPosition().add(new Position(1, 0)), topOfQueue));
            pathComponentsQueue.add(new PathComponent(
                    topOfQueue.getPosition().add(new Position(-1, 0)), topOfQueue));
            pathComponentsQueue.add(new PathComponent(
                    topOfQueue.getPosition().add(new Position(0, 1)), topOfQueue));
            pathComponentsQueue.add(new PathComponent(
                    topOfQueue.getPosition().add(new Position(0, -1)), topOfQueue));
        }

        // We are now finished with the loop and *might* have a valid ending location.
        // We've chained all the path components so that they point to their previous element
        //  so now we'll create forward links so that we can move along the path efficiently
        PathComponent current = finalComponent;
        for (; current != null; current = current.getPrevious()) {
            // Add positions to the waypoint tile set
            waypointTileSet.add(current.getPosition());

            // Get the previous path component, and if not null, set its next value to current
            PathComponent previous = current.getPrevious();
            if (previous != null) {
                // There's still a prior element that we can access
                previous.setNext(current);
            } else {
                // No prior element, aka we're at the first element
                startingPathComponent = current;
            }
        }

        // Make sure that the final component isn't null (aka we actually have a valid path)
        if (finalComponent != null) {
            targetTile = finalComponent.getPosition();
        }

        return new GeneratedPath(startingPathComponent, waypointTileSet, targetTile);

    }
}
