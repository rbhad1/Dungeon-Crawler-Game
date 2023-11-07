package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.example.cs2340c_team28.models.Game;

import java.util.ArrayList;
import java.util.List;

// make this a Singleton?? does not need to be abstract

public  class EnemyHandler {
    private Enemy enemy1;
    private Enemy enemy2;
    protected ArrayList<Enemy> enemyList;

    protected TiledMap forest = new TmxMapLoader().load("forest-map.tmx");
    protected TiledMap water = new TmxMapLoader().load("water-map.tmx");
    protected TiledMap dungeon = new TmxMapLoader().load("dungeon-map.tmx");



    public  List<Enemy> initialize() {
        Game game = Game.getInstance();
        TiledMap currentMap = game.getCurrentMap();

        // TODO update constructors when sprites are made
        enemyList = new ArrayList<>();
        if (currentMap.getLayers().get("forest") != null) {
            enemy1 = new GroundEnemy();
            enemy1.setX(2, true);
            enemy1.setY(6, true);
            enemy2 = new AirEnemy();
            enemy2.setX(8, true);
            enemy2.setY(4, true);
            enemyList.add(enemy1);
            enemyList.add(enemy2);
        } else if (currentMap.getLayers().get("rocks") != null) {
            enemy1 = new WaterEnemy();
            enemy1.setX(6, true);
            enemy1.setY(10, true);
            enemy2 = new AirEnemy();
            enemy2.setX(5, true);
            enemy2.setY(3, true);
            enemyList.add(enemy1);
            enemyList.add(enemy2);
        // (currentMap.getProperties().containsKey("portal"))
        } else {
            enemy1 = new GroundEnemy();
            enemy1.setX(7, true);
            enemy1.setY(13, true);
            enemy2 = new FireEnemy();
            enemy2.setX(6, true);
            enemy2.setY(13, true);
            Enemy enemy3 = new FireEnemy();
            enemy3.setX(7, true);
            enemy3.setY(12, true);
            enemyList.add(enemy1);
            enemyList.add(enemy2);
            enemyList.add(enemy3);
        }

        return enemyList;
        // put the enemy on a valid location
    }

    void main() {
        this.initialize();
    }

    // put this in Game, should not have access to Game






}