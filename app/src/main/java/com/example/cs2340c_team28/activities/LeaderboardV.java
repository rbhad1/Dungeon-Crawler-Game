package com.example.cs2340c_team28.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.cs2340c_team28.R;
import com.example.cs2340c_team28.models.Leaderboard;
import com.example.cs2340c_team28.viewmodels.LeaderBoardVM;

import java.util.List;

public class LeaderboardV extends Activity {

    private final LeaderBoardVM leaderBoardVM = new LeaderBoardVM();

    private final List<Leaderboard.LeaderboardEntry> leaderboardEntries
            = leaderBoardVM.getLeaderboardEntries();





    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.leadership_board);

        TextView entryOne = findViewById(R.id.entry1);
        TextView entryTwo = findViewById(R.id.entry2);
        TextView entryThree = findViewById(R.id.entry3);
        TextView entryFour = findViewById(R.id.entry4);
        TextView entryFive = findViewById(R.id.entry5);

        if (leaderboardEntries.size() >= 1) {
            Leaderboard.LeaderboardEntry entry1 = leaderboardEntries.get(0);
            entryOne.setText(String.format(entry1.getDate() + "\t" + entry1.getPlayerName() + "\t"
                    + entry1.getScore()));
        } else {
            entryOne.setText("Start playing to view the leaderboard!");
        }

        if (leaderboardEntries.size() >= 2) {
            Leaderboard.LeaderboardEntry entry2 = leaderboardEntries.get(1);
            entryTwo.setText(String.format(entry2.getDate() + "\t" + entry2.getPlayerName() + "\t"
                    + entry2.getScore()));
        }

        if (leaderboardEntries.size() >= 3) {
            Leaderboard.LeaderboardEntry entry3 = leaderboardEntries.get(2);
            entryThree.setText(String.format(entry3.getDate() + "\t" + entry3.getPlayerName() + "\t"
                    + entry3.getScore()));
        }

        if (leaderboardEntries.size() >= 4) {
            Leaderboard.LeaderboardEntry entry4 = leaderboardEntries.get(3);
            entryFour.setText(String.format(entry4.getDate() + "\t" + entry4.getPlayerName() + "\t"
                    + entry4.getScore()));
        }

        if (leaderboardEntries.size() == 5) {
            Leaderboard.LeaderboardEntry entry5 = leaderboardEntries.get(4);
            entryFive.setText(String.format(entry5.getDate() + "\t" + entry5.getPlayerName() + "\t"
                    + entry5.getScore()));
        }

        Leaderboard.LeaderboardEntry latestAttempt = leaderBoardVM.getLatestAttempt();
        if (latestAttempt != null) {
            TextView latestAttemptText = findViewById(R.id.latestAttempt);
            latestAttemptText
                    .setText(String.format(latestAttempt.getDate() + "\t"
                    + latestAttempt.getPlayerName() + latestAttempt.getScore()));
        }


        // click on reset button and navigate to main screen
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(view -> {
            Intent intent = new Intent(LeaderboardV.this, MainActivity.class);
            startActivity(intent);
        });

    }

}
