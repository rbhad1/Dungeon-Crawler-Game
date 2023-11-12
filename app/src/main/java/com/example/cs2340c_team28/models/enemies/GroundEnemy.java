package com.example.cs2340c_team28.models.enemies;

import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

public class GroundEnemy extends Enemy {
    private boolean right = true;

    public GroundEnemy() {
        super.imgRes = "sprites_enemy/ground_sprite.png";
        super.assignTexture();
    }
    public void move() {
        if (!super.shouldMove()) {
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
