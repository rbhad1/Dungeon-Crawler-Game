package com.example.cs2340c_team28.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
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

        Leaderboard.LeaderboardEntry entry1 = leaderboardEntries.get(0);
        Leaderboard.LeaderboardEntry entry2 = leaderboardEntries.get(1);
        Leaderboard.LeaderboardEntry entry3 = leaderboardEntries.get(2);
        Leaderboard.LeaderboardEntry entry4 = leaderboardEntries.get(3);
        Leaderboard.LeaderboardEntry entry5 = leaderboardEntries.get(4);


        switch (leaderboardEntries.size()) {
        case 5:
            entryFive.setText(String.format(entry5.getDate() + "\t" + entry5.getPlayerName() + "\t"
                    + entry5.getScore()));
        case 4:
            entryFour.setText(String.format(entry4.getDate() + "\t" + entry4.getPlayerName() + "\t"
                    + entry4.getScore()));
        case 3:
            entryThree.setText(String.format(entry3.getDate() + "\t" + entry3.getPlayerName() + "\t"
                    + entry3.getScore()));
        case 2:
            entryTwo.setText(String.format(entry2.getDate() + "\t" + entry2.getPlayerName() + "\t"
                    + entry2.getScore()));
        case 1:
            entryOne.setText(String.format(entry1.getDate() + "\t" + entry1.getPlayerName() + "\t"
                    + entry1.getScore()));
            break;
        default:
            entryOne.setText("Start playing to view the leaderboard!");
            break;
        }
    }

}
