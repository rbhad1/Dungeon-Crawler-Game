package com.example.cs2340c_team28;

import static org.junit.Assert.*;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.example.cs2340c_team28.helpers.LibGdxTester;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Movement;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.helpers.GameViewModelTester;
import com.example.cs2340c_team28.models.TileMovementStrategy;

import org.junit.Test;

public class TileMovementStrategyTests {
    static {
        LibGdxTester.initializeForTests();
    }

    @Test
    public void movementSuccess() {
        int startX = 4;
        int startY = 9;
        Movement movement = new Movement(startX, startY, 16, 0);

        assertEquals(movement.getEndTileX(), 16);
        assertEquals(movement.getEndTileY(), 0);
    }

}
