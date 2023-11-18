package com.example.cs2340c_team28.models;

/**
 * This class is used to make the time from GameViewModel (or a similar class that holds time)
 * available to every class in the game without having a direct reference to such class.
 * <p>
 * Follows singleton design pattern. Use {@link #getInstance()} to get the instance.
 */
public class GlobalTime {
    private static final GlobalTime INSTANCE = new GlobalTime();

    /**
     * Get the GlobalTime instance
     * @return The current instance of GlobalTime
     */
    public static GlobalTime getInstance() {
        return INSTANCE;
    }

    private GlobalTime() { }

    /**
     * The object from which we actually get the time
     */
    private TimeAccessor timeAccessor;

    /**
     * Safe method to get the time as a long without risk of NullPointerException
     *
     * @return The current time from the {@link TimeAccessor}
     * or {@link Long#MIN_VALUE} if the {@link #timeAccessor} is null
     */
    public long getTime() {
        return timeAccessor != null ? timeAccessor.getTime() : Long.MIN_VALUE;
    }

    /**
     * Set the time accessor used by this calss
     * @param timeAccessor The new time accessor
     */
    public void setTimeAccessor(TimeAccessor timeAccessor) {
        this.timeAccessor = timeAccessor;
    }

    /**
     * An interface representing an object that can return the time
     */
    public interface TimeAccessor {
        /**
         * Get the current time
         * @return The time as a long
         */
        long getTime();
    }
}
