package com.example.cs2340c_team28;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team28.annotation.Sprint;
import com.example.cs2340c_team28.helpers.GameViewModelTester;
import com.example.cs2340c_team28.helpers.LibGdxTester;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.attack.AttackListener;
import com.example.cs2340c_team28.models.attack.StandardAttackStrategy;
import com.example.cs2340c_team28.models.enemies.AirEnemy;
import com.example.cs2340c_team28.models.enemies.Enemy;
import com.example.cs2340c_team28.models.enemies.EnemyListFactory;
import com.example.cs2340c_team28.models.enemies.GroundEnemy;
import com.example.cs2340c_team28.models.enemies.WaterEnemy;

import org.junit.Test;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class AttackUnitTests {
    static {
        LibGdxTester.initializeForTests();
    }

    @Test @Sprint(5)
    public void canAttack() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        player.setLastAttack(System.currentTimeMillis());

        gameViewModel.cycledUpdate(1, 1);

        assertFalse(Player.getInstance().getCanAttack());

        player.setLastAttack(System.currentTimeMillis() - 4000);
        gameViewModel.cycledUpdate(1, 1);

        assertTrue(Player.getInstance().getCanAttack());
    }
    @Test @Sprint(5)
    public void enemyRemoved() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();
        StandardAttackStrategy attack = new StandardAttackStrategy();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getWater());
        gameViewModel.cycledUpdate(1, 1);
        Player player = Player.getInstance();
        Enemy enemy = game.getEnemyList().get(0);
        int ex = enemy.getX(true);
        int ey = enemy.getY(true);

        player.setX(enemy.getX(true) + 1, true);
        player.setY(enemy.getY(true), true);

        gameViewModel.cycledUpdate(1, 1);
        attack.attack();

        gameViewModel.cycledUpdate(1, 1);
        assertNotEquals(ex, enemy.getX(true));
        assertNotEquals(ey, enemy.getY(true));
        assertEquals(10000, enemy.getX(true));
        assertEquals(10000, enemy.getY(true));
    }
}
