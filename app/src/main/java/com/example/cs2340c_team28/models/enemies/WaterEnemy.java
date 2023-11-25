package com.example.cs2340c_team28.models.enemies;

import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;

public class WaterEnemy  extends Enemy {
    private int direction = 0;
    public WaterEnemy() {
        super.imgRes = "sprites_enemy/water_sprite.png";
        super.assignTexture();

    }

    public void move() {
        if (super.shouldNotMove()) {
            return;
        }
        if (getX(true) == 8) {
            direction = 2;
        }
        if (getX(true) == 0) {
            direction = 0;
        }
        if (getY(true) == 15) {
            direction = 3;
        }
        if (getY(true) == 0) {
            direction = 1;
        }
        int x = getX(true);
        int y = getY(true);
        Movement movement;
        if (direction == 0) {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x + 1, y),
                    true, 200);
            direction = 1;
        } else if (direction == 1) {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x, y + 1),
                    true, 200);
            direction = 2;
        } else if (direction == 2) {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x - 1, y),
                    true, 200);
            direction = 3;
        } else {
            movement = new Movement(
                    new Position(x, y),
                    new Position(x, y - 1),
                    true, 200);
            direction = 0;
        }
        movement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
        movement.setEndDelay(100);
        setCurrentMovement(movement);
    }


}
