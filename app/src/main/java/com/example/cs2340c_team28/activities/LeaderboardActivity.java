package com.example.cs2340c_team28.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.badlogic.gdx.Gdx;
import com.example.cs2340c_team28.R;
import com.example.cs2340c_team28.models.Leaderboard;
import com.example.cs2340c_team28.viewmodels.LeaderboardViewModel;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Locale;

public class LeaderboardActivity extends Activity {

    private final LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();

    private final List<Leaderboard.LeaderboardEntry> leaderboardEntries
            = leaderboardViewModel.getLeaderboardEntries();

    private final SimpleDateFormat sdf =
            new SimpleDateFormat("hh:mm a - MMM dd, yyyy", Locale.US);

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
            entryOne.setText(String.format(Locale.US, "1.\t%s\n\tScore: %d\n\t%s",
                    entry1.getPlayerName(),
                    entry1.getScore(),
                    sdf.format(entry1.getDate())));
        } else {
            entryOne.setText("Start playing to view the leaderboard!");
        }

        if (leaderboardEntries.size() >= 2) {
            Leaderboard.LeaderboardEntry entry2 = leaderboardEntries.get(1);
            entryTwo.setText(String.format(Locale.US, "2.\t%s\n\tScore: %d\n\t%s",
                    entry2.getPlayerName(),
                    entry2.getScore(),
                    sdf.format(entry2.getDate())));
        }

        if (leaderboardEntries.size() >= 3) {
            Leaderboard.LeaderboardEntry entry3 = leaderboardEntries.get(2);
            entryThree.setText(String.format(Locale.US, "3.\t%s\n\tScore: %d\n\t%s",
                    entry3.getPlayerName(),
                    entry3.getScore(),
                    sdf.format(entry3.getDate())));
        }

        if (leaderboardEntries.size() >= 4) {
            Leaderboard.LeaderboardEntry entry4 = leaderboardEntries.get(3);
            entryFour.setText(String.format(Locale.US, "4.\t%s\n\tScore: %d\n\t%s",
                    entry4.getPlayerName(),
                    entry4.getScore(),
                    sdf.format(entry4.getDate())));
        }

        if (leaderboardEntries.size() == 5) {
            Leaderboard.LeaderboardEntry entry5 = leaderboardEntries.get(4);
            entryFive.setText(String.format(Locale.US, "5.\t%s\n\tScore: %d\n\t%s",
                    entry5.getPlayerName(),
                    entry5.getScore(),
                    sdf.format(entry5.getDate())));
        }

        Leaderboard.LeaderboardEntry latestAttempt = leaderboardViewModel.getLatestAttempt();
        if (latestAttempt != null) {
            TextView latestAttemptText = findViewById(R.id.latestAttempt);
            latestAttemptText.setText(String.format(Locale.US, "\t%s\n\tScore: %d\n\t%s",
                    latestAttempt.getPlayerName(),
                    latestAttempt.getScore(),
                    sdf.format(latestAttempt.getDate())));
        }


        // click on reset button and navigate to main screen
        Button restartButton = findViewById(R.id.restartButton);
        restartButton.setOnClickListener(view -> {
            Intent intent = new Intent(LeaderboardActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

    }

}
