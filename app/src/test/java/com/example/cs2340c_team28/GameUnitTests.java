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

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class GameUnitTests {

    static {
        LibGdxTester.initializeForTests();
    }

    @Test
    public void playerDoesExitForestMap() {

        // Create the GameViewModel
        GameViewModelTester gameViewModel = new GameViewModelTester();

        // Mandatory function to load assets, set time to 0, setup game
        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();

        // Set the current map, if desired
        // Note that this is redundant here since the first map is forest anyway
        game.setCurrentMap(gameViewModel.getForest());

        // Set the player's position to the location of the door in the forest
        player.setX(1, true);
        player.setY(0, true);

        // Increment the time. This isn't relevant to movement but shown here in case we
        gameViewModel.incrementTime(1);

        // Get the original map that we started at (forest)
        TiledMap originalTileView = game.getCurrentMap();

        // Call updateGameLogic() which should transition room
        gameViewModel.updateGameLogic();

        // Check if we're in a different room
        assertNotEquals(originalTileView, game.getCurrentMap());

        // Check if we're in the water room now
        assertEquals(game.getCurrentMap(), gameViewModel.getWater());
    }

    @Test
    public void playerCanMoveUp() {

        // Create the GameViewModel
        GameViewModelTester gameViewModel = new GameViewModelTester();

        // Mandatory function to load assets, set time to 0, setup game
        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        // Game game = Game.getInstance();

        // Set the player's position to the location of the door in the forest
        player.setX(4, true);
        player.setY(9, true);

        new TileMovementStrategy().moveUp();

        // Call updateGameLogic() which should transition room
        gameViewModel.updateGameLogic();

        // Check that we've moved successfully
        assertEquals(player.getX(true), 4);
        assertEquals(player.getY(true), 10);
    }
    @Test
    public void playerMultipleMovements() {
        GameViewModelTester gameViewModel = new GameViewModelTester();

        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();

        player.setX(4, true);
        player.setY(9,true);

        new TileMovementStrategy().moveLeft();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveLeft();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveDown();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveDown();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveDown();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveRight();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveDown();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveDown();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveDown();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveRight();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveDown();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveDown();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveLeft();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveLeft();
        gameViewModel.updateGameLogic();
        new TileMovementStrategy().moveLeft();
        gameViewModel.updateGameLogic();

        assertEquals(player.getX(true), 1);
        assertEquals(player.getY(true), 1);
    }
    @Test
    public void directMovement() {
        int startX = 4;
        int startY = 9;
        Movement movement = new Movement(startX, startY, 16, 0);
        assertEquals(movement.getEndTileX(), 16);
        assertEquals(movement.getEndTileY(), 0);
    }
    @Test
    public void playerInBounds() {
        GameViewModelTester gameViewModel = new GameViewModelTester();

        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();

        player.setX(0, true);
        player.setY(1, true);

        new TileMovementStrategy().moveLeft();
        gameViewModel.updateGameLogic();

        assertEquals(player.getX(true), 0);
        assertNotEquals(player.getX(true), -1);

        player.setX(15, true);
        player.setY(1, true);

        new TileMovementStrategy().moveRight();
        gameViewModel.updateGameLogic();

        assertEquals(player.getX(true), 15);
        assertNotEquals(player.getX(true), 16);
    }


    @Test
    public void playerCollisionInForestMap() {
        GameViewModelTester gameViewModel = new GameViewModelTester();

        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getForest());


        player.setX(0, true);
        player.setY(3, true);

        new TileMovementStrategy().moveRight();
        gameViewModel.updateGameLogic();


        assertEquals(player.getX(true), 0);
        assertEquals(player.getY(true), 3);

        assertTrue(player.getCurrentMovement().isCollided());


    }

    @Test
    public void playerDoesNotCollideInWaterMap() {
        GameViewModelTester gameViewModel = new GameViewModelTester();

        gameViewModel.doPreinitialization();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getWater());


        Player player = Player.getInstance();

        player.setX(0, true);
        player.setY(0, true);

        new TileMovementStrategy().moveUp();
        gameViewModel.updateGameLogic();

        assertEquals(player.getX(true), 0);
        assertEquals(player.getY(true), 1);
        assertFalse(player.getCurrentMovement().isCollided());
    }

}