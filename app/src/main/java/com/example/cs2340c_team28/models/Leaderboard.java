package com.example.cs2340c_team28.models;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.cs2340c_team28.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Leaderboard {

    public static final int MAX_ENTRIES = 5;

    private static final Leaderboard INSTANCE = new Leaderboard();

    private final List<LeaderboardEntry> leaderboardEntries;



    /**
     * Initialize the leaderboard. Private constructor
     */
    private Leaderboard() {
        leaderboardEntries = new ArrayList<>();
    }

    /**
     * Get the Leaderboard instance
     *
     * @return the Leaderboard instance
     */
    public static Leaderboard getInstance() {
        return INSTANCE;
    }

    /**
     * Get the Leaderboard entries
     *
     * @return The List of leaderboard entries
     */
    public List<LeaderboardEntry> getLeaderboardEntries() {
        // Return a copied ArrayList so that other classes can't modify the original ArrayList
        return leaderboardEntries;
    }



    /**
     * Class representing an individual entry in the leaderboard
     */
    public static class LeaderboardEntry implements Comparable<LeaderboardEntry> {

        private final String playerName;
        private final int score;
        private final Date date;

        /**
         * Instantiate new leaderboard entry
         *
         * @param playerName The name of the player
         * @param score      The score at the end of the game
         * @param date       The date and time of the game
         */
        public LeaderboardEntry(String playerName, int score, Date date) {
            this.playerName = playerName;
            this.score = score;
            this.date = date;
        }

        /**
         * Getter for the player name
         *
         * @return The player name
         */
        public String getPlayerName() {
            return playerName;
        }

        /**
         * Getter for the score
         *
         * @return The score
         */
        public int getScore() {
            return score;
        }

        /**
         * Getter for the date/time object
         *
         * @return The date/time object
         */
        public Date getDate() {
            return date;
        }

        /**
         * Compare to
         *
         * @param o the LeaderboardEntry to be compared.
         * @return -1, 0, or 1 based on the comparisons of the two scores
         */
        @Override
        public int compareTo(LeaderboardEntry o) {
            // Compare scores
            int result = Integer.compare(this.score, ((LeaderboardEntry) o).score);

            // If scores are the same, compare dates, otherwise return score result
            return result == 0 ? this.date.compareTo(o.date) : result;
        }
    }



}