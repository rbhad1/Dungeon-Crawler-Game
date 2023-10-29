package com.example.cs2340c_team28.models;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

abstract class EnemyHandler {
    Enemy enemy;

    void initialize() {
        Game game = Game.getInstance();
        TiledMap currentMap = game.getCurrentMap();

        if (currentMap.getProperties().containsKey("forest")) {
            enemy = new GroundEnemy();
            enemy = new AirEnemy();
        } else if (currentMap.getProperties().containsKey("water")) {
            enemy = new WaterEnemy();
            enemy = new AirEnemy();
        } else if (currentMap.getProperties().containsKey("portal")) {
            enemy = new GroundEnemy();
            enemy = new FireEnemy();
        }
    }
    void main() {
        this.initialize();
    }


}