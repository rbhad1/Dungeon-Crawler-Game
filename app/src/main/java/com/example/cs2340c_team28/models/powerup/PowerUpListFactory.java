package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.ArrayList;
import java.util.List;

public class PowerUpListFactory {
    public List<PickupEffect> createPowerUpList() {
        Game game = Game.getInstance();
        TiledMap currentMap = game.getCurrentMap();

        ArrayList<PickupEffect> powerUpList = new ArrayList<>();
        if (currentMap.getLayers().get("forest") != null) {
            PickupEffect pickupEffect1 = new PickupEffect(null);
            pickupEffect1.setPosition(new Position(2, 10), true);
            // powerUpList.add(pickupEffect1) ;

        } else if (currentMap.getLayers().get("rocks") != null) {
            // water
            PickupEffect pickupEffect1 = new PickupEffect(null);
            pickupEffect1.setPosition(new Position(2, 10), true);
            // powerUpList.add(pickupEffect1) ;

        } else {
            // dungeon
            PickupEffect pickupEffect1 = new PickupEffect(null);
            pickupEffect1.setPosition(new Position(2, 10), true);
            // powerUpList.add(pickupEffect1) ;

        }

        return powerUpList;
    }
}
