package com.example.cs2340c_team28;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.example.cs2340c_team28.helpers.GameViewModelTester;
import com.example.cs2340c_team28.helpers.LibGdxTester;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.enemies.AirEnemy;
import com.example.cs2340c_team28.models.enemies.Enemy;
import com.example.cs2340c_team28.models.enemies.EnemyHandler;
import com.example.cs2340c_team28.models.enemies.GroundEnemy;
import com.example.cs2340c_team28.models.enemies.WaterEnemy;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;
import com.example.cs2340c_team28.models.movement.TileMovementStrategy;
import com.example.cs2340c_team28.screens.TiledView;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class EnemyUnitTests {

    static {
        LibGdxTester.initializeForTests();
    }

    @Test
    public void correctEnemiesInForestMap() {
        Game.getInstance().setEnemiesList(null);

        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Game game = Game.getInstance();

        gameViewModel.cycledUpdate(4, 1);

        int counter = 0;

        for (Enemy enemy : game.getEnemyList()) {

            if (enemy instanceof GroundEnemy || enemy instanceof AirEnemy) {
                counter++;
            }
        }
        assertEquals(4, counter);
    }


    @Test
    public void correctEnemiesInWaterMap() {

        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Game game = Game.getInstance();

        game.setCurrentMap(gameViewModel.getWater());

        gameViewModel.cycledUpdate(1, 1);

        int counter = 0;
        for (Enemy enemy : game.getEnemyList()) {
            if (enemy instanceof WaterEnemy || enemy instanceof AirEnemy) {
                counter++;
            }
        }
        assertEquals(3, counter);
    }
    @Test
    public void waterEnemyMovesInCircle() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Game game = Game.getInstance();

        game.setCurrentMap(gameViewModel.getWater());
        Game.getInstance().setEnemiesList(new EnemyHandler().createEnemyList());

        gameViewModel.cycledUpdate(1, 1);
        Game.getInstance().setEnemiesList(new EnemyHandler().createEnemyList());
        int startX = game.getEnemyList().get(0).getX(true);
        int startY = game.getEnemyList().get(0).getY(true);
        // start is 5,10

        gameViewModel.cycledUpdate(2, 200);

        assertEquals(startX+1, game.getEnemyList().get(0).getX(true));
        assertEquals(startY, game.getEnemyList().get(0).getY(true));
        gameViewModel.cycledUpdate(2, 200);
        assertEquals(startX+1, game.getEnemyList().get(0).getX(true));
        assertEquals(startY+1, game.getEnemyList().get(0).getY(true));
        gameViewModel.cycledUpdate(2, 200);
        assertEquals(startX, game.getEnemyList().get(0).getX(true));
        assertEquals(startY+1, game.getEnemyList().get(0).getY(true));
        gameViewModel.cycledUpdate(2, 200);
        assertEquals(startX, game.getEnemyList().get(0).getX(true));
        assertEquals(startY, game.getEnemyList().get(0).getY(true));
    }
    @Test
    public void enemyMovesInBounds() {
        Game.getInstance().setEnemiesList(null);
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();
        Game game = Game.getInstance();
        gameViewModel.cycledUpdate(4, 1);
        List<Enemy> enemylist = game.getEnemyList();

        gameViewModel.cycledUpdate(2, 200);
        // it starts at index 2
        assertNotEquals(2, enemylist.get(0).getX(true));
        gameViewModel.cycledUpdate(10, 200);
        assertEquals(8, enemylist.get(0).getX(true));
        gameViewModel.cycledUpdate(2, 200);
        assertNotEquals(9, enemylist.get(0).getX(true));
    }

    @Test
    public void checkGroundEnemyInLine() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Game game = Game.getInstance();

        // Set the current map to the desired map (e.g., forest)
        game.setCurrentMap(gameViewModel.getForest());
        Game.getInstance().setEnemiesList(new EnemyHandler().createEnemyList());

        // Update the game for a certain number of cycles
        gameViewModel.cycledUpdate(1, 1);

        // Get the initial position of the ground enemy
        int initialX = game.getEnemyList().get(0).getX(true);
        int initialY = game.getEnemyList().get(0).getY(true);

        // Update the game for several cycles
        for (int i = 0; i < 5; i++) {
            gameViewModel.cycledUpdate(2, 200);
        }

        // Check if the ground enemy stays on the same line
        assertEquals(initialY, game.getEnemyList().get(0).getY(true));

        // Check if the ground enemy's X coordinate has changed
        assertNotEquals(initialX, game.getEnemyList().get(0).getX(true));
    }

    public void checkAirEnemyInLine() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Game game = Game.getInstance();

        // Set the current map to the desired map
        game.setCurrentMap(gameViewModel.getForest());
        Game.getInstance().setEnemiesList(new EnemyHandler().createEnemyList());

        // Update the game for a certain number of cycles
        gameViewModel.cycledUpdate(1, 1);

        // Get the initial position of the air enemy
        int initialX = game.getEnemyList().get(1).getX(true);
        int initialY = game.getEnemyList().get(1).getY(true);

        // Update the game for several cycles
        for (int i = 0; i < 5; i++) {
            gameViewModel.cycledUpdate(2, 200);
        }

        // Check if the ground enemy stays on the same line
        assertEquals(initialY, game.getEnemyList().get(1).getY(true));

        // Check if the ground enemy's X coordinate has changed
        assertNotEquals(initialX, game.getEnemyList().get(1).getX(true));
    }
}