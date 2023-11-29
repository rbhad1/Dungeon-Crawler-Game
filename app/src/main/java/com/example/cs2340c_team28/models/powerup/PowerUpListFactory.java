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
            PickupEffect pickupEffect = new PickupEffect(new JumpingPowerUp());
            pickupEffect.setPosition(new Position(2, 10), true);
            powerUpList.add(pickupEffect);

            pickupEffect = new PickupEffect(new SuperSpeedDecorator());
            pickupEffect.setPosition(new Position(8, 2), true);
            powerUpList.add(pickupEffect);

            pickupEffect = new PickupEffect(new RegenerationPowerUp());
            pickupEffect.setPosition(new Position(6, 12), true);
            powerUpList.add(pickupEffect);


        } else if (currentMap.getLayers().get("rocks") != null) {
            // water
            PickupEffect pickupEffect = new PickupEffect(new SuperSpeedDecorator());
            pickupEffect.setPosition(new Position(0, 14), true);
            powerUpList.add(pickupEffect);

            pickupEffect = new PickupEffect(new RegenerationPowerUp());
            pickupEffect.setPosition(new Position(8, 6), true);
            powerUpList.add(pickupEffect);

        } else {
            // dungeon
            PickupEffect pickupEffect1 = new PickupEffect(null);
            pickupEffect1.setPosition(new Position(2, 10), true);
            // powerUpList.add(pickupEffect1) ;

        }

        return powerUpList;
    }
}
