package com.example.cs2340c_team28.models.powerup;

import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.Movement;

public class SuperSpeed extends PowerUp {

    public SuperSpeed() {
        super();
    }
    @Override
    public void activate() {
        Player player = Player.getInstance();

        if (player.getCurrentMovement() == null) {
            return;
        }
        player.getCurrentMovement().setDuration(player.getCurrentMovement().getDuration() / 2);

        // TODO add time component
        // Get the current time and start time (from the movement)

    }


}
