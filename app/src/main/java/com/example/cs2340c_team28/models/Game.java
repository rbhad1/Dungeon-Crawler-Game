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
     * The maximum score the player can have
     */
    private static final int MAX_SCORE = 1000;

    /**
     * The player's score based on their performance in the game
     */
    private int score;

    /**
     * Time in seconds since the game began
     */
    private double time;

    /**
     * System time at which the current game began
     */
    private long startTime = System.currentTimeMillis();

    /**
     * Time since the score was last decremented
     */
    private long scoreTime = startTime;

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

    /**
     * Get the maximum score the player can have
     * @return The maximum score
     */
    public static int getMaxScore() { return MAX_SCORE; }

    /**
     * Get the score of the current game
     * @return The current score
     */
    public int getScore() { return score; }

    /**
     * Get the time of the game
     * @return The current time
     */
    public double getTime() { return time; }

    /**
     * Get the time at which the current game began
     * @return The game's starting time
     */
    public long getStartTime() { return startTime; }

    /**
     * Get the time since the last score decrement
     * @return The time since score last decremented
     */
    public long getScoreTime() { return scoreTime; }

    /**
     * Set the score of the current game
     * @param score The game's new score
     */
    public void setScore(int score) { this.score = score; }

    /**
     * Set the time since the last score decrement
     * @param scoreTime The new time since last decrement
     */
    public void setScoreTime(long scoreTime) { this.scoreTime = scoreTime; }

    /**
     * Set the current time of the game
     * @param time The game's new time
     */
    public void setTime(double time) { this.time = time; }

}
