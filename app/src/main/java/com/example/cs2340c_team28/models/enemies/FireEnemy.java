package com.example.cs2340c_team28.models.enemies;

import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

import java.util.Random;

public class FireEnemy extends Enemy {
    private boolean right = false;
    private boolean up = false;


    public FireEnemy() {
        super.imgRes = "fire_sprite.jpg";
        super.assignTexture();

    }
    public void move() {
        Random random = new Random();
        int rand = random.nextInt(4);
        if (rand == 0) {
            right = true;
            up = true;
        }
        if (rand == 1) {
            right = true;
            up = false;
        }
        if (rand == 2) {
            right = false;
            up = true;
        }
        if (rand == 3) {
            right = false;
            up = false;
        }

        if (getCurrentMovement() != null && getCurrentMovement().getStatus() == Movement.Status.IN_PROGRESS) {
            return;
        }
        if (getX(true) == 8) {
            right = false;
        }
        if (getX(true) == 0) {
            right = true;
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
        if (right && up) {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x + 1, y + 1),
                    true, 200);
        } else if (right) {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x + 1, y - 1),
                    true, 200);
        } else if (up) {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x - 1, y + 1),
                    true, 200);
        } else {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x - 1, y - 1),
                    true, 200);
        }
        movement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
        setCurrentMovement(movement);
    }

}
