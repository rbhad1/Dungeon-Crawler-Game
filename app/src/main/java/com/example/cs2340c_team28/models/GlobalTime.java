package com.example.cs2340c_team28.models;

public class GlobalTime {
    private static final GlobalTime INSTANCE = new GlobalTime();

    public static GlobalTime getInstance() {
        return INSTANCE;
    }

    private GlobalTime() { }

    private TimeAccessor timeAccessor;

    public long getTime() {
        return timeAccessor != null ? timeAccessor.getTime() : Long.MIN_VALUE;
    }

    public void setTimeAccessor(TimeAccessor timeAccessor) {
        this.timeAccessor = timeAccessor;
    }

    public interface TimeAccessor {
        long getTime();
    }
}
