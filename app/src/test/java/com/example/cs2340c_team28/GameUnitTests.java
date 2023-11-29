package com.example.cs2340c_team28;

import static org.junit.Assert.*;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.example.cs2340c_team28.annotation.Sprint;
import com.example.cs2340c_team28.helpers.LibGdxTester;
import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.helpers.GameViewModelTester;
import com.example.cs2340c_team28.models.movement.Position;
import com.example.cs2340c_team28.models.movement.TileMovementStrategy;

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
        gameViewModel.cycledUpdate(4, TileMovementStrategy.getMoveDuration() * 2);

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

        TileMovementStrategy strategy = new TileMovementStrategy();
        Runnable[] movements = new Runnable[] {
                strategy::moveLeft,
                strategy::moveLeft,
                strategy::moveDown,
                strategy::moveDown,
                strategy::moveDown,
                strategy::moveRight,
                strategy::moveDown,
                strategy::moveDown,
                strategy::moveDown,
                strategy::moveRight,
                strategy::moveDown,
                strategy::moveDown,
                strategy::moveLeft,
                strategy::moveLeft,
                strategy::moveLeft
        };

        for (Runnable r: movements) {
            r.run();
            gameViewModel.cycledUpdate(4, TileMovementStrategy.getMoveDuration() * 2);
        }

        assertEquals(player.getX(true), 1);
        assertEquals(player.getY(true), 1);
    }
    @Test
    public void directMovement() {
        int startX = 4;
        int startY = 9;
        Movement movement = new Movement(
                new Position(startX, startY), new Position(16, 0), true
        );
        assertEquals(movement.getEnd(true).getX(), 16);
        assertEquals(movement.getEnd(true).getY(), 0);
    }

    @Test
    public void testTileMovementStrategy() {
        Player player = Player.getInstance();

        int startX = 4;
        int startY = 9;

        // Set starting position for the player
        player.setX(startX, true);
        player.setY(startY, true);

        // Generate a left movement
        new TileMovementStrategy().moveLeft();
        assertEquals(player.getCurrentMovement().getEnd(true).getX(), startX - 1);
        assertEquals(player.getCurrentMovement().getEnd(true).getY(), startY);
        Player.getInstance().setCurrentMovement(null);

        new TileMovementStrategy().moveRight();
        Player.getInstance().getCurrentMovement().setStatus(Movement.Status.COMPLETE);
        assertEquals(player.getCurrentMovement().getEnd(true).getX(), startX + 1);
        assertEquals(player.getCurrentMovement().getEnd(true).getY(), startY);
        Player.getInstance().setCurrentMovement(null);

        new TileMovementStrategy().moveUp();
        Player.getInstance().getCurrentMovement().setStatus(Movement.Status.COMPLETE);
        assertEquals(player.getCurrentMovement().getEnd(true).getX(), startX);
        assertEquals(player.getCurrentMovement().getEnd(true).getY(), startY + 1);
        Player.getInstance().setCurrentMovement(null);

        new TileMovementStrategy().moveDown();
        Player.getInstance().getCurrentMovement().setStatus(Movement.Status.COMPLETE);
        assertEquals(player.getCurrentMovement().getEnd(true).getX(), startX);
        assertEquals(player.getCurrentMovement().getEnd(true).getY(), startY - 1);
        Player.getInstance().setCurrentMovement(null);
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
        gameViewModel.cycledUpdate(4, TileMovementStrategy.getMoveDuration() * 2);

        assertEquals(player.getX(true), 0);
        assertNotEquals(player.getX(true), -1);

        player.setX(15, true);
        player.setY(1, true);

        new TileMovementStrategy().moveRight();
        gameViewModel.updateGameLogic();
        gameViewModel.incrementTime(TileMovementStrategy.getMoveDuration() * 2);
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
        Movement originalMovement = player.getCurrentMovement();
        gameViewModel.cycledUpdate(6, TileMovementStrategy.getMoveDuration() * 2);

        assertEquals(0, player.getX(true));
        assertEquals(3, player.getY(true));

        assertEquals(Movement.Status.COLLIDED, originalMovement.getStatus());

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
        Movement originalMovement = player.getCurrentMovement();
        gameViewModel.cycledUpdate(4, TileMovementStrategy.getMoveDuration() * 2);

        assertEquals(0, player.getX(true));
        assertEquals(1, player.getY(true));
        assertNotEquals(Movement.Status.COLLIDED, originalMovement.getStatus());
    }

    @Test
    public void scoreAndTimeUpdateCorrectlyAfterOneMinute() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();
        Game game = Game.getInstance();

        int initialScore = Game.MAX_SCORE;
        game.setScore(1000);

        while (gameViewModel.getTime() <= 60000) {
            gameViewModel.updateGameLogic();
            gameViewModel.incrementTime(1);
        }

        int finalScore = game.getScore();

        assertEquals(initialScore - 60, finalScore);
    }

    @Test
    public void scoreAndTimeUpdateCorrectlyAfterOneHour() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();
        Game game = Game.getInstance();

        game.setScore(1000);

        while (gameViewModel.getTime() <= 3600000) {
            gameViewModel.updateGameLogic();
            gameViewModel.incrementTime(1);
        }

        int finalScore = game.getScore();

        assertEquals(0, finalScore);
    }

    @Test
    public void testGetX() {
        GameViewModelTester gameViewModel = new GameViewModelTester();

        gameViewModel.doPreinitialization();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getForest());


        Player player = Player.getInstance();
        player.setX(4, false);


        assertEquals(4, player.getX(false));
        assertNotEquals(4, player.getX(true));
    }

    @Test
    public void testGetY() {
        GameViewModelTester gameViewModel = new GameViewModelTester();

        gameViewModel.doPreinitialization();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getForest());


        Player player = Player.getInstance();
        player.setY(9, true);

        // Initial y position should be 9
        assertNotEquals(9, player.getY(false));
        assertEquals(9, player.getY(true));
    }

    @Test @Sprint(4)
    public void testPlayerHealthOnCollisionEasyDifficulty() {
        Game game = Game.getInstance();
        GameViewModelTester gmv = new GameViewModelTester();
        gmv.doPreinitialization();
        game.setDifficulty(Difficulty.EASY);
        game.setCurrentMap(gmv.getForest());
        Player player = Player.getInstance();
        player.setHp(Player.initialHp(game.getDifficulty()));
        int initialHealth = player.getHp();

        gmv.collisionOccurred();

        assertEquals(initialHealth - 5, player.getHp());
    }

    @Test @Sprint(4)
    public void testPlayerHealthOnCollisionMediumDifficulty() {
        Game game = Game.getInstance();
        GameViewModelTester gmv = new GameViewModelTester();
        gmv.doPreinitialization();
        game.setDifficulty(Difficulty.MEDIUM);
        game.setCurrentMap(gmv.getForest());
        Player player = Player.getInstance();
        player.setHp(Player.initialHp(game.getDifficulty()));
        int initialHealth = player.getHp();

        gmv.collisionOccurred();

        assertEquals(initialHealth - 10, player.getHp());
    }

    @Test @Sprint(4)
    public void testPlayerHealthOnCollisionHardDifficulty() {
        Game game = Game.getInstance();
        GameViewModelTester gmv = new GameViewModelTester();
        gmv.doPreinitialization();
        game.setDifficulty(Difficulty.HARD);
        game.setCurrentMap(gmv.getForest());
        Player player = Player.getInstance();
        player.setHp(Player.initialHp(game.getDifficulty()));
        int initialHealth = player.getHp();

        gmv.collisionOccurred();

        assertEquals(initialHealth - 15, player.getHp());
    }

    @Test @Sprint(4)
    public void gameOverAtZeroHP() {
        Game game = Game.getInstance();
        GameViewModelTester gmv = new GameViewModelTester();

        gmv.doPreinitialization();
        game.setDifficulty(Difficulty.HARD);
        game.setCurrentMap(gmv.getForest());

        Player player = Player.getInstance();
        player.setHp(Player.initialHp(game.getDifficulty()));
        int initialHealth = player.getHp();

        gmv.collisionOccurred();
        gmv.collisionOccurred();
        gmv.collisionOccurred();
        gmv.collisionOccurred();
        gmv.endGame();

        assertEquals(initialHealth - 50, player.getHp());
        assertTrue(gmv.getGameOver());
    }
}