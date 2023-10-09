package com.example.cs2340c_team28;

import com.example.cs2340c_team28.models.Leaderboard;
import com.example.cs2340c_team28.viewmodels.LeaderBoardVM;

import org.junit.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit tests for the leaderboard
 */
public class LeaderboardTest {

    public Leaderboard leaderboard = Leaderboard.getInstance();
    public LeaderBoardVM leaderboardVM = new LeaderBoardVM();

    /**
     * Verify that we can reset the leaderboard
     * and that upon resetting, it resets the min score so we can add new elements
     */
    @Test
    public void leaderboardDoesClearAndResetMin() {
        // Reset the leaderboard
        leaderboardVM.resetLeaderboard();

        // Add a new entry and verify that leaderboard size is 1
        assertTrue(leaderboardVM.addNewEntry("Player 1", 50, new Date()));
        assertEquals(leaderboard.getLeaderboardEntries().size(), 1);

        // Reset the leaderboard and verify that leaderboard size is 0
        leaderboardVM.resetLeaderboard();
        assertEquals(leaderboard.getLeaderboardEntries().size(), 0);

        // Add a new entry and verify that leaderboard size is 1
        assertTrue(leaderboardVM.addNewEntry("Player 2", 0, new Date()));
        assertEquals(leaderboard.getLeaderboardEntries().size(), 1);
    }

    /**
     * Check that more elements can't be added to the leaderboard than the hard-coded limit
     */
    @Test
    public void leaderboardAdheresToCapacity() {
        System.out.println("Testing leaderboardAdheresToCapacity()...");

        // Reset the leaderboard
        leaderboardVM.resetLeaderboard();

        for (int i = 0; i < Leaderboard.MAX_ENTRIES + 1; i++) {
            // Assert that we can add elements to the leaderboard until we're over capacity
            boolean couldAddElement = leaderboardVM.addNewEntry(
                            "Player", 15, Date.from(Instant.EPOCH));
            boolean shouldHaveAddedElement = i < Leaderboard.MAX_ENTRIES;

            System.out.printf("Round %d, could add element = %s, should have added = %s\n",
                    i, couldAddElement, shouldHaveAddedElement);

            assertEquals(couldAddElement, shouldHaveAddedElement);
        }
    }

    /**
     * Ensure that the leaderboard properly sorts scores that are added to it
     */
    @Test
    public void leaderboardAdhersToSortedOrder() {
        // Reset the leaderboard
        leaderboardVM.resetLeaderboard();

        // Create an array of scores and try adding all of those scores into the leaderboard
        Integer[] scores = new Integer[] {40, 24, 60, 1, 8, 2, 9};
        for (int score: scores) {
            leaderboardVM.addNewEntry("Player", score, new Date());
        }

        // Sort the scores
        Arrays.sort(scores, Collections.reverseOrder());

        // Get the leaderboard entries, make sure they are the right size
        List<Leaderboard.LeaderboardEntry> leaderboardEntries = leaderboard.getLeaderboardEntries();
        assertEquals(leaderboardEntries.size(), Leaderboard.MAX_ENTRIES);

        // Make sure the leaderboard entries and order matches the scores
        for (int i = 0; i < leaderboardEntries.size(); i++) {
            assertEquals(leaderboardEntries.get(i).getScore(), scores[i].intValue());
        }
    }

}
