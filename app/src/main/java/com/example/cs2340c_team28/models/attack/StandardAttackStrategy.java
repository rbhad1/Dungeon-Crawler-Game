package com.example.cs2340c_team28.models.attack;

import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.enemies.Enemy;

import java.util.List;

public class StandardAttackStrategy implements AttackStrategy {

    public void attack() {
        int x = Player.getInstance().getX(true);
        int y = Player.getInstance().getY(true);
        //Game.getInstance().setScore(Game.getInstance().getScore() + 100);
        for (Enemy enemy : Game.getInstance().getEnemyList()) {
            int ex = enemy.getX(true);
            int ey = enemy.getY(true);

            if ((ex == x + 1) || (ex == x - 1) || (ey == y + 1) || (ey == y - 1)) {
                enemy.setX(10000, true);
                enemy.setY(10000, true);
            }

        }
    }
}
