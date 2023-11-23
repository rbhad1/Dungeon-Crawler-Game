package com.example.cs2340c_team28.models.enemies.trackers;

import com.example.cs2340c_team28.models.movement.Position;

public interface TrackerStrategy {
    GeneratedPath generatePath(Position originTile);
}
