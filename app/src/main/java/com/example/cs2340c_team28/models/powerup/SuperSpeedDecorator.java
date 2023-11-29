package com.example.cs2340c_team28.models.powerup;

import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Movable;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.enemies.Enemy;
import com.example.cs2340c_team28.models.enemies.TextureFactory;
import com.example.cs2340c_team28.models.movement.Movement;

import java.util.HashMap;

public class SuperSpeedDecorator extends PowerUpDecorator {

    public SuperSpeedDecorator() {
        this(null);
    }

    public SuperSpeedDecorator(PowerUp wrapped) {
        super(wrapped, TextureFactory.getInstance().createTexture("sprites_powerup/speed.png"));
    }

    private Movement lastPlayerMovement = null;
    private final HashMap<Movable, Movement> lastMovements = new HashMap<>();

    @Override
    protected void doPowerUpEffect() {
        // Apply faster to player
        Movement playerMovement = Player.getInstance().getCurrentMovement();
        if (playerMovement != null && !playerMovement.equals(lastPlayerMovement)) {
            playerMovement.setDuration(playerMovement.getDuration() / 2);
            lastPlayerMovement = playerMovement;
        }

        // Apply slower to enemies
        if (Game.getInstance().getEnemyList() != null) {
            for (Enemy enemy : Game.getInstance().getEnemyList()) {
                // Make sure the enemy isn't null
                if (enemy == null) {
                    continue;
                }
                // Get the last movement we changed for that enemy, also get current movement
                Movement lastMovement = lastMovements.getOrDefault(enemy, null);
                Movement currentMovement = enemy.getCurrentMovement();
                // If it's not equal to current movement (or it's null)
                if (currentMovement != null && !currentMovement.equals(lastMovement)) {
                    currentMovement.setDuration(currentMovement.getDuration() * 2);
                    lastMovements.put(enemy, currentMovement);
                }
            }
        }
    }


}
