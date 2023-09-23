package com.example.cs2340c_team28;

/**
 * Class representing a game being played
 *
 * @author Steven Baker
 */
public class Game {
    /**
     * The player for the game
     */
    private final Player player;

    /**
     * The difficulty of the game
     */
    private final Difficulty difficulty;

    /**
     * Instantiate the game
     * @param player the player object
     * @param difficulty the game difficulty
     */
    public Game(Player player, Difficulty difficulty) {
        this.player = player;
        player.setHp(Player.initialHp(difficulty));
        this.difficulty = difficulty;
    }

    /**
     * Get the object representing the player
     * @return The player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get the difficulty of the game
     * @return The game difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }
}
