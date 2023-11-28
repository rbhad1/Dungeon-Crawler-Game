package com.example.cs2340c_team28.models.enemies.trackers;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TrackerStrategyDFS implements TrackerStrategy {
    @Override
    public GeneratedPath generatePath(Position originTile) {

        // Initial eventual return values
        PathComponent startingPathComponent = null;
        Set<Position> waypointTileSet = new HashSet<>();
        Position targetTile = null;

        Game game = Game.getInstance();
        // Create visited set
        HashSet<Position> visitedPositions = new HashSet<>();
        // Create a stack of position's that we'll be visiting
        Stack<PathComponent> pathComponentsStack = new Stack<>();

        // Create a variable representing the component at which we end the path.
        PathComponent finalComponent = null;

        // Add the DFSEnemy's current position to the stack
        pathComponentsStack.push(new PathComponent(originTile));

        while (!pathComponentsStack.isEmpty()) {
            // pop from the stack
            PathComponent topOfStack = pathComponentsStack.pop();

            // if position we are currently at has not been visited, add it to the set
            if (visitedPositions.contains(topOfStack.getPosition())) {
                continue;
            } else {
                visitedPositions.add(topOfStack.getPosition());
            }

            // First see if this is a valid position. If it isn't, continue.
            TiledMapTileLayer.Cell newCell = game.getWalkableLayer()
                    .getCell(topOfStack.getPosition().getX(), topOfStack.getPosition().getY());
            if (newCell == null || newCell.getTile().getId() == 0) {
                continue;
            }

            // Then see if it's the correct position. (aka at player location) If it is, break.
            if (topOfStack.getPosition().equals(Player.getInstance().getPosition(true))) {
                finalComponent = topOfStack;
                break;
            }

            // Add new position options to the stack for each direction from the current tile
            pathComponentsStack.push(new PathComponent(
                    topOfStack.getPosition().add(new Position(1, 0)), topOfStack));
            pathComponentsStack.push(new PathComponent(
                    topOfStack.getPosition().add(new Position(-1, 0)), topOfStack));
            pathComponentsStack.push(new PathComponent(
                    topOfStack.getPosition().add(new Position(0, 1)), topOfStack));
            pathComponentsStack.push(new PathComponent(
                    topOfStack.getPosition().add(new Position(0, -1)), topOfStack));

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


