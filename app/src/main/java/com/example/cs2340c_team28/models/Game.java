package com.example.cs2340c_team28.models;

/**
 * Class representing a game being played
 *
 * @author Steven Baker, Cameron Loyet
 */
public class Game {
    //create instance of Game
    private static volatile Game uniqueGameInstance;

    /**
     * private constructor for Game class
     *
     * @param difficulty The difficulty of the game to be instantiated
     */
    private Game(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * static method Game method that uses double-checked locking to create a unique Game instance
     * @return the unique game instance that is instantiated
     */
    public static Game getUniqueGameInstance() {
        return uniqueGameInstance;
    }
    public static void createNewGame(Difficulty difficulty) {
        uniqueGameInstance = new Game(difficulty);
    }

    /**
     * The difficulty of the game
     */
    private final Difficulty difficulty;

    /**
     * Get the difficulty of the game
     * @return The game difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

}
