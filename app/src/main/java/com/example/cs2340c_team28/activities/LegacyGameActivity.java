package com.example.cs2340c_team28.activities;

import com.example.cs2340c_team28.R;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LegacyGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        TextView playerName = findViewById(R.id.playerNameField);
        TextView playerHealth = findViewById(R.id.playerHealth);
        TextView difficulty = findViewById(R.id.difficultyField);
        TextView spriteName = findViewById(R.id.spriteName);
        ImageView imageView = findViewById(R.id.imageView2);
        playerName.setText(Player.getInstance().getName());
        playerHealth.setText(Player.getInstance().getHp()
                + "/" +  Player.getInstance().getOriginalHp() + " HP");
        difficulty.setText(Game.getInstance().getDifficulty().toString());

        int spriteId = Player.getInstance().getSpriteId();
        spriteName.setText("Sprite " + spriteId);

        int imageResource;
        switch (spriteId) {
        case 1:
            imageResource = R.drawable.person1;
            break;
        case 2:
            imageResource = R.drawable.person2;
            break;
        default:
            imageResource = R.drawable.person3;
            break;
        }
        imageView.setImageResource(imageResource);


        Button nextScreenButton = findViewById(R.id.nextScreenButton);

        nextScreenButton.setOnClickListener(view -> {
            Intent intent = new Intent(LegacyGameActivity.this, EndScreenActivity.class);
            startActivity(intent);
        });

    }

    /**
     * Overridden to stop the user from returning back into the config screen
     */
    @Override
    public void onBackPressed() {

    }
}