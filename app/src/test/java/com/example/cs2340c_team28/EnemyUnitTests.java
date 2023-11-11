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
import com.example.cs2340c_team28.models.enemies.GroundEnemy;
import com.example.cs2340c_team28.models.enemies.WaterEnemy;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;
import com.example.cs2340c_team28.models.movement.TileMovementStrategy;
import com.example.cs2340c_team28.screens.TiledView;

import org.junit.Test;

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

        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Game game = Game.getInstance();
        //game.setCurrentMap(gameViewModel.getForest());
        gameViewModel.cycledUpdate(4, 1);

        TiledView tiledView = new TiledView(gameViewModel);
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
        TiledView tiledView = new TiledView(gameViewModel);
        gameViewModel.cycledUpdate(1, 1);

        int counter = 0;
        for (Enemy enemy : game.getEnemyList()) {
            if (enemy instanceof WaterEnemy || enemy instanceof AirEnemy) {
                counter++;
            }
        }
        assertEquals(3, counter);
    }


}