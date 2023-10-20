package com.example.cs2340c_team28.viewmodels;

public class GameViewModelTester extends GameViewModel {
    private long testTime;
    @Override
    public long getTime() {
        return testTime;
    }

    public void setTime(long time) {
        this.testTime = time;
    }
}
