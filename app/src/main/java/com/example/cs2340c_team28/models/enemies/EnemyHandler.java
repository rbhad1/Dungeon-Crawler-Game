package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.ArrayList;
import java.util.List;

public class EnemyHandler {

    public List<Enemy> createEnemyList() {
        Game game = Game.getInstance();
        TiledMap currentMap = game.getCurrentMap();

        ArrayList<Enemy> enemyList = new ArrayList<>();
        if (currentMap.getLayers().get("forest") != null) {
            Enemy enemy1 = new GroundEnemy();
            enemy1.setX(2, true);
            enemy1.setY(6, true);
            Enemy enemy2 = new AirEnemy();
            enemy2.setX(8, true);
            enemy2.setY(4, true);
            Enemy enemy3 = new AirEnemy();
            enemy3.setX(3, true);
            enemy3.setY(0, true);
            Enemy enemy4 = new GroundEnemy();
            enemy4.setX(0, true);
            enemy4.setY(1, true);
            enemyList.add(enemy1);
            enemyList.add(enemy2);
            enemyList.add(enemy3);
            enemyList.add(enemy4);

            Enemy enemy5 = new BFSEnemy();
            enemy5.setPosition(new Position(1, 9), true);
            enemyList.add(enemy5);

        } else if (currentMap.getLayers().get("rocks") != null) {
            Enemy enemy1 = new WaterEnemy();
            enemy1.setX(5, true);
            enemy1.setY(10, true);
            Enemy enemy2 = new AirEnemy();
            enemy2.setX(5, true);
            enemy2.setY(3, true);
            Enemy enemy3 = new WaterEnemy();
            enemy3.setX(4, true);
            enemy3.setY(5, true);
            enemyList.add(enemy1);
            enemyList.add(enemy2);
            enemyList.add(enemy3);
            // (currentMap.getProperties().containsKey("portal"))
        } else {
            Enemy enemy1 = new GroundEnemy();
            enemy1.setX(4, true);
            enemy1.setY(7, true);
            Enemy enemy2 = new FireEnemy();
            enemy2.setX(6, true);
            enemy2.setY(13, true);
            Enemy enemy3 = new FireEnemy();
            enemy3.setX(4, true);
            enemy3.setY(7, true);
            Enemy enemy4 = new FireEnemy();
            enemy4.setX(4, true);
            enemy4.setY(7, true);
            Enemy enemy5 = new FireEnemy();
            enemy5.setX(4, true);
            enemy5.setY(7, true);
            Enemy enemy6 = new FireEnemy();
            enemy6.setX(4, true);
            enemy6.setY(7, true);
            Enemy enemy7 = new FireEnemy();
            enemy7.setX(4, true);
            enemy7.setY(7, true);
            enemyList.add(enemy1);
            enemyList.add(enemy2);
            enemyList.add(enemy3);
            enemyList.add(enemy4);
            enemyList.add(enemy5);
            enemyList.add(enemy6);
            enemyList.add(enemy7);
        }

        return enemyList;
        // put the enemy on a valid location
    }
}


