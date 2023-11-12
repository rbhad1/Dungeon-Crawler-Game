package com.example.cs2340c_team28.models.enemies;

import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

public class AirEnemy extends Enemy {
    private boolean up = true;

    public AirEnemy() {
        super.imgRes = "air_sprite.jpg";
        super.assignTexture();
    }

    public void move() {
        if (getCurrentMovement() != null && getCurrentMovement().getStatus() == Movement.Status.IN_PROGRESS) {
            return;
        }
        if (getY(true) == 15) {
            up = false;
        }
        if (getY(true) == 0) {
            up = true;
        }
        int x = getX(true);
        int y = getY(true);
        Movement movement;
        if (up) {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x, y + 1),
                    true, 200);
        } else {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x, y - 1),
                    true, 200);
        }
        movement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
        setCurrentMovement(movement);
    }

}


