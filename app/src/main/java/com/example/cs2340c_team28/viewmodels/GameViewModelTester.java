package com.example.cs2340c_team28.viewmodels;

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
}
