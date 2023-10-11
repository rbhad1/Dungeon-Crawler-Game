package com.example.cs2340c_team28.viewmodels;

import com.example.cs2340c_team28.models.Leaderboard;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class LeaderboardViewModel {

    private Leaderboard leaderboard = Leaderboard.getInstance();

    public List<Leaderboard.LeaderboardEntry> getLeaderboardEntries() {
        return leaderboard.getLeaderboardEntries();
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


        // See if we're at capacity already
        if (leaderboard.getLeaderboardEntries().size() == Leaderboard.MAX_ENTRIES) {
            // Compare new element to the "lowest" element on the existing leaderboard
            if (leaderboard.getLeaderboardEntries()
                    .get(leaderboard.getLeaderboardEntries().size() - 1).compareTo(newEntry) < 0) {
                // Remove the last entry to make more space
                leaderboard.getLeaderboardEntries().remove(Leaderboard.MAX_ENTRIES - 1);
            } else {
                // We're at capacity, can't add another element
                return false;
            }
        }

        // Add the new entry to the list and sort
        leaderboard.getLeaderboardEntries().add(newEntry);
        leaderboard.getLeaderboardEntries().sort(Collections.reverseOrder());

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
