package com.example.cs2340c_team28.models.powerup;

import com.example.cs2340c_team28.models.Player;

/**
 * FOR TESTING PURPOSES -- Only different is doesn't assign texture to
 * avoid loading assets for JUnit testing
 * DON'T EDIT
 */
public class SuperSpeedTester extends PowerUp {
    private boolean superSpeedActivated = false;


    public SuperSpeedTester() {
        super();
    }
    @Override
    public void activate() {
        Player player = Player.getInstance();

        if (player.getCurrentMovement() == null) {
            return;
        }
        player.getCurrentMovement().setDuration(player.getCurrentMovement().getDuration() / 2);
        superSpeedActivated = true;
    }

    public void deactivate() {
        Player player = Player.getInstance();

        if (player.getCurrentMovement() == null) {
            return;
        }
        if (superSpeedActivated) {
            player.getCurrentMovement().setDuration(player.getCurrentMovement().getDuration() * 2);
        }
    }

}
