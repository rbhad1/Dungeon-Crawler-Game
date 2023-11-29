package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.enemies.TextureFactory;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

public class RegenerationPowerUp extends PowerUpDecorator {

    public RegenerationPowerUp() {
        this(null);
    }

    public RegenerationPowerUp(PowerUp wrapped) {
        super(wrapped, TextureFactory.getInstance().createTexture("sprites_powerup/heart.png"));
    }

    private boolean alreadyAppliedFirstBoost = false;
    private int originalHp;

    @Override
    protected void doPowerUpEffect() {

        if (!alreadyAppliedFirstBoost) {
            Player.getInstance().setHp(
                    Math.min(Player.getInstance().getHp() + Player.getInstance().getOriginalHp() / 2,
                            Player.getInstance().getOriginalHp())
            );
            alreadyAppliedFirstBoost = true;
            originalHp = Player.getInstance().getHp();
        } else {
            Player.getInstance().setHp(originalHp);
        }
    }
}
