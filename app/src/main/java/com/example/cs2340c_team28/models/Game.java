package com.example.cs2340c_team28.models;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a game being played
 *
 * @author Steven Baker, Cameron Loyet
 */
public class Game {
    //create instance of Game
    private static final Game INSTANCE = new Game();

    /**
     * Private constructor to adhere to singleton pattern
     */
    private Game() {

    }

    /**
     * The maximum score the player can have
     */
    public static final int MAX_SCORE = 1000;

    /**
     * The player's score based on their performance in the game
     */
    private int score;

    /**
     * System time at which the current game began
     */
    private long startTime;

    /**
     * Time since the score was last decremented
     */
    private long scoreTime = startTime;

    /**
     * static method Game method that uses double-checked locking to create a unique Game instance
     * @return the unique game instance that is instantiated
     */
    public static Game getInstance() {
        return INSTANCE;
    }

    /**
     * The difficulty of the game
     */
    private Difficulty difficulty;

    private TiledMap currentMap;
    private TiledMapTileLayer walkableLayer;
    private TiledMapTileLayer doorLayer;

    /**
     * Get the difficulty of the game
     * @return The game difficulty
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public TiledMap getCurrentMap() {
        return currentMap;
    }

    /**
     * Get the maximum score the player can have
     * @return The maximum score
     */
    public static int getMaxScore() {
        return MAX_SCORE;
    }

    /**
     * Get the score of the current game
     * @return The current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the time at which the current game began
     * @return The game's starting time
     */
    public long getStartTime() {
        return startTime;
    }

    /**
     * Get the time since the last score decrement
     * @return The time since score last decremented
     */
    public long getScoreTime() {
        return scoreTime;
    }

    /**
     * Set the score of the current game
     * @param score The game's new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set the time since the last score decrement
     * @param scoreTime The new time since last decrement
     */
    public void setScoreTime(long scoreTime) {
        this.scoreTime = scoreTime;
    }

    public void setStartTime(long time) {
        this.startTime = time;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setCurrentMap(TiledMap currentMap) {
        this.currentMap = currentMap;
        this.walkableLayer = (TiledMapTileLayer) currentMap.getLayers().get("walkable");
        this.walkableLayer = (TiledMapTileLayer) currentMap.getLayers().get("door");
    }

    public TiledMapTileLayer getWalkableLayer() {
        return walkableLayer;
    }

    public TiledMapTileLayer getDoorLayer() {
        return doorLayer;
    }
}
