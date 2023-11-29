package com.example.cs2340c_team28.models.powerup;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Movable;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.enemies.Enemy;
import com.example.cs2340c_team28.models.enemies.TextureFactory;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.HashMap;

public class JumpingPowerUp extends PowerUpDecorator {

    public JumpingPowerUp() {
        this(null);
    }

    public JumpingPowerUp(PowerUp wrapped) {
        super(wrapped, TextureFactory.getInstance().createTexture("sprites_powerup/jump.png"));
    }

    private Movement lastPlayerMovement = null;

    @Override
    protected void doPowerUpEffect() {
        // Apply faster to player
        Movement playerMovement = Player.getInstance().getCurrentMovement();
        if (playerMovement != null && !playerMovement.equals(lastPlayerMovement)) {
            playerMovement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
            lastPlayerMovement = playerMovement;
        }
    }

    @Override
    protected boolean canSafelyDeactivate() {

        Game game = Game.getInstance();
        Position position = Player.getInstance().getPosition(true);
        TiledMapTileLayer.Cell newCell = game.getWalkableLayer()
                .getCell(position.getX(), position.getY());
        if (newCell == null || newCell.getTile().getId() == 0) {
            return false;
        }

        if (Player.getInstance().getCurrentMovement() != null) {

            position = Player.getInstance().getCurrentMovement().getEnd(true);
            newCell = game.getWalkableLayer()
                    .getCell(position.getX(), position.getY());
            if (newCell == null || newCell.getTile().getId() == 0) {
                return false;
            }
        }

        return true;
    }
}
