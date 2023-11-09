package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

public class GroundEnemy extends Enemy {
    private boolean right = true;

    public GroundEnemy() {
        super.imgRes = "ground_sprite.jpg";
        enemyImage = new Texture(imgRes);
        super.assignTexture();
    }
    public void move() {
        if (getCurrentMovement() != null && getCurrentMovement().getStatus() == Movement.Status.IN_PROGRESS) {
            return;
        }
        if (getX(true) == 8) {
            right = false;
        }
        if (getX(true) == 0) {
            right = true;
        }
        int x = getX(true);
        int y = getY(true);
        Movement movement;
        if (right) {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x + 1, y),
                    true, 200);
        } else {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x - 1, y),
                    true, 200);
        }
        movement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
        setCurrentMovement(movement);
    }

}
