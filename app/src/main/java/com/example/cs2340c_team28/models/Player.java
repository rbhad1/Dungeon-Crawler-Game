package com.example.cs2340c_team28.models;

import androidx.annotation.NonNull;

/**
 * Class representing a player in the game
 *
 * @author Steven Baker
 */
public class Player {

    private static volatile Player uniquePlayerInstance;

    /**
     * The name of the player
     */
    private final String name;

    /**
     * The health points (hp) of the player
     */
    private int hp;

    private final int originalHp;

    /**
     * An integer id representing the player's sprite graphic,
     *  avoids storing resource name in the data model
     */
    private final int spriteId;

    /**
     * Instantiate the player
     * @param name The player's name
     * @param spriteId Chosen sprite id
     * @param initialHp Player's starting hp
     */
    private Player(String name, int spriteId, int initialHp) {
        this.name = name;
        this.spriteId = spriteId;
        this.hp = initialHp;
        this.originalHp = initialHp;
    }

    public static Player getUniquePlayerInstance() {
        return uniquePlayerInstance;
    }

    public static void createNewPlayer(String name, Difficulty difficulty, int spriteId) {
        uniquePlayerInstance = new Player(
                name,
                spriteId,
                initialHp(difficulty)
        );
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

}
