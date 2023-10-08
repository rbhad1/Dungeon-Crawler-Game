package com.example.cs2340c_team28.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.cs2340c_team28.R;
import com.example.cs2340c_team28.models.Leaderboard;
import com.example.cs2340c_team28.viewModel.LeaderBoardVM;

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

        if (Leaderboard.getInstance() == null) {
            entryOne.setText("Start playing to view the leaderboard!");
        } else if (leaderboardEntries.size() < 5) {
            entryOne = findViewById(R.id.entry1);
            entryOne.setText("More players need to play the game to view the leaderboard!");
        } else {
            entryOne = findViewById(R.id.entry1);
            Leaderboard.LeaderboardEntry entry1 = leaderboardEntries.get(0);
            entryOne.setText(String.format(leaderboardEntries.get(0).getDate()
                    + ": " + leaderboardEntries.get(0).getPlayerName() + "   "
                    + leaderboardEntries.get(0).getScore()));


            TextView entryTwo = findViewById(R.id.entry2);
            Leaderboard.LeaderboardEntry entry2 = leaderboardEntries.get(1);
            entryOne.setText(String.format(leaderboardEntries.get(1).getDate() + ": "
                    + leaderboardEntries.get(1).getPlayerName() + "   "
                    + leaderboardEntries.get(1).getScore()));

            TextView entryThree = findViewById(R.id.entry3);
            Leaderboard.LeaderboardEntry entry3 = leaderboardEntries.get(2);
            entryOne.setText(String.format(leaderboardEntries.get(2).getDate() + ": "
                    + leaderboardEntries.get(2).getPlayerName() + "   "
                    + leaderboardEntries.get(2).getScore()));


            TextView entryFour = findViewById(R.id.entry4);
            Leaderboard.LeaderboardEntry entry4 = leaderboardEntries.get(3);
            entryOne.setText(String.format(leaderboardEntries.get(3).getDate() + ": "
                    + leaderboardEntries.get(3).getPlayerName() + "   "
                    + leaderboardEntries.get(3).getScore()));


            TextView entryFive = findViewById(R.id.entry5);
            Leaderboard.LeaderboardEntry entry5 = leaderboardEntries.get(4);
            entryOne.setText(String.format(leaderboardEntries.get(4).getDate() + ": "
                    + leaderboardEntries.get(4).getPlayerName() + "   "
                    + leaderboardEntries.get(4).getScore()));
        }

    }

}
