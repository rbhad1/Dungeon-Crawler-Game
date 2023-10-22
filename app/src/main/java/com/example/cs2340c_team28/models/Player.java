package com.example.cs2340c_team28.models;

import android.util.Log;

import androidx.annotation.NonNull;

/**
 * Class representing a player in the game
 *
 * @author Steven Baker
 */
public class Player {

    private static final Player INSTANCE = new Player();

    /**
     * Private constructor to adhere to singleton pattern
     */
    private Player() {

    }

    /**
     * The name of the player
     */
    private String name;

    /**
     * The health points (hp) of the player
     */
    private int hp;

    private int originalHp;

    /**
     * An integer id representing the player's sprite graphic,
     *  avoids storing resource name in the data model
     */
    private int spriteId;
    private MovementStrategy movementStrategy;
    private int x;
    private int y;
    private boolean leftMove;
    private boolean rightMove;
    private static final String TAG = Player.class.getSimpleName();

    public static Player getInstance() {
        return INSTANCE;
    }

    public String getName() {
        return name;
    }

    /**
     * Get the player's health points
     * @return The current health points for the player
     */
    public int getHp() {
        return hp;
    }

    public int getOriginalHp() {
        return originalHp;
    }


    /**
     * Set the player's health points
     * @param hp The new value of health points to set
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Get the player's sprite id
     * @return The current sprite id for the player
     */
    public int getSpriteId() {
        return spriteId;
    }

    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOriginalHp(int originalHp) {
        this.originalHp = originalHp;
    }

    /**
     * Gets the ideal initial health points based on the given game difficulty
     *
     * @param difficulty The intended difficulty of the game
     * @return The value of health points
     */
    public static int initialHp(@NonNull Difficulty difficulty) {
        switch (difficulty) {
        case EASY:
            return 150;
        case MEDIUM:
            return 100;
        default: // corresponds to HARD
            return 50;
        }
    }
    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setLeftMove(boolean t) {
        if (rightMove && t) {
            rightMove = false;
        }
        leftMove = t;
    }
    public void setRightMove(boolean t) {
        if (leftMove && t) {
            leftMove = false;
        }
        rightMove = t;
    }
    public void updateMovement() {
        Log.d(TAG, String.format("leftmove: %s, rightmove %s", leftMove, rightMove));
        if (leftMove) {
            Player.getInstance().setX(Player.getInstance().getX() - 10);
        }
        if (rightMove) {
            Player.getInstance().setX(Player.getInstance().getX() + 10);
        }
    }
}
