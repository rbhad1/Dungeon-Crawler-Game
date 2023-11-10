package com.example.cs2340c_team28.helpers;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.enemies.TextureFactory;
import com.example.cs2340c_team28.viewmodels.GameViewModel;


public class GameViewModelTester extends GameViewModel {
    private long testTime;
    @Override
    public long getTime() {
        return testTime;
    }

    /**
     * Set the current time
     * @param time The new time to set
     */
    public void setTime(long time) {
        this.testTime = time;
    }

    /**
     * Increment the current time
     * @param deltaTime The amount by which to increment the time
     */
    public void incrementTime(long deltaTime) {
        this.testTime += deltaTime;
    }

    /**
     * Update the game view model multiple times
     * @param repeats
     * @param deltaTime
     */
    public void cycledUpdate(int repeats, long deltaTime) {
        for (int i = 0; i < repeats; i++) {
            super.updateGameLogic();
            this.incrementTime(deltaTime);
        }
    }

    protected void loadAssets() {
        super.forest = new TmxMapLoader().load("src/main/assets/forest-map.tmx");
        super.water = new TmxMapLoader().load("src/main/assets/water-map.tmx");
        super.dungeon = new TmxMapLoader().load("src/main/assets/dungeon-map.tmx");
        Game.getInstance().setCurrentMap(forest);


    }


    public void doPreinitialization() {
        loadAssets();
        setTime(0);
        setupGame();
        TextureFactory.getInstance().setForUnitTests(true);
    }
}
