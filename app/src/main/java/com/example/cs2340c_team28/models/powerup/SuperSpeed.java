package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.graphics.Texture;
import com.example.cs2340c_team28.models.Player;

public class SuperSpeed extends PowerUp {


    public SuperSpeed() {
        super();
        super.imgRes = "speed.png";
        super.assignTexture();
    }
    @Override
    public void activate() {
        Player player = Player.getInstance();

        if (player.getCurrentMovement() == null) {
            return;
        }
        player.getCurrentMovement().setDuration(player.getCurrentMovement().getDuration() / 2);

    }


}
