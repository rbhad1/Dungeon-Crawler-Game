package com.example.cs2340c_team28.viewmodels;

import com.example.cs2340c_team28.models.Leaderboard;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LeaderBoardVM {

    private Leaderboard leaderboard = Leaderboard.getInstance();

    public List<Leaderboard.LeaderboardEntry> getLeaderboardEntries() {
        return leaderboard.getLeaderboardEntries();
    }

     public Leaderboard.LeaderboardEntry getLatestAttempt() {
        return leaderboard.getLatestAttempt();
    }

    /**
     * Add a new entry to the leaderboard
     *
     * @param playerName The player's name
     * @param score      The score
     * @param date       The date of the score
     * @return Whether or not the new score entry was added
     */
    public boolean addNewEntry(String playerName, int score, Date date) {

        Leaderboard.LeaderboardEntry newEntry =
                new Leaderboard.LeaderboardEntry(playerName, score, date);

        // Add the new entry to the list
        leaderboard.getLeaderboardEntries().add(newEntry);

        // Sort the entries in descending order
        leaderboard.getLeaderboardEntries().sort(Collections.reverseOrder());

        // Trim the list to the maximum allowed entries
        if (leaderboard.getLeaderboardEntries().size() > Leaderboard.MAX_ENTRIES) {
            leaderboard.getLeaderboardEntries().subList(Leaderboard.MAX_ENTRIES,
                    leaderboard.getLeaderboardEntries().size()).clear();
        }

        // Set the latest attempt to the new entry
        leaderboard.setLatestAttempt(newEntry);

        // Return true to indicate that we did add a new entry
        return true;
    }


    /**
     * Remove all entries from the leaderboard.
     */
    public void resetLeaderboard() {
        leaderboard.getLeaderboardEntries().clear();
    }

}
