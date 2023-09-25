package com.example.cs2340c_team28.models;

/**
 * Class representing a game being played
 *
 * @author Steven Baker, Cameron Loyet
 */
public class Game {
    //create instance of Game
    private volatile static Game uniqueGameInstance;

    /**
     * private constructor for Game class
     */
    private Game() {}

    /**
     * static method Game method that uses double-checked locking to create a unique Game instance
     * @return the unique game instance that is instantiated
     */
    public static Game getUniqueGameInstance() {
        if (uniqueGameInstance == null) {
            synchronized (Game.class) {
                if (uniqueGameInstance == null) {
                    uniqueGameInstance = new Game();
                }
            }
        }
        return uniqueGameInstance;
    }
        public Game createNewGame(Difficulty difficulty) {
            difficulty = getDifficulty();
            uniqueGameInstance = getUniqueGameInstance();
            return uniqueGameInstance;
        }

    /**
     * The difficulty of the game
     */
    private Difficulty difficulty;

    /**
     * Get the difficulty of the game
     * @return The game difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

}
