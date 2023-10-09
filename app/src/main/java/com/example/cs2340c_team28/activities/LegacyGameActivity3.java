package com.example.cs2340c_team28.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cs2340c_team28.R;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class LegacyGameActivity3 extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game3);
        TextView playerName = findViewById(R.id.playerNameField);
        TextView playerHealth = findViewById(R.id.playerHealth);
        TextView difficulty = findViewById(R.id.difficultyField);
        TextView spriteName = findViewById(R.id.spriteName);
        ImageView imageView = findViewById(R.id.imageView2);
        playerName.setText(Player.getUniquePlayerInstance().getName());
        playerHealth.setText(Player.getUniquePlayerInstance().getHp()
                + "/" +  Player.getUniquePlayerInstance().getOriginalHp() + " HP");
        difficulty.setText(Game.getUniqueGameInstance().getDifficulty().toString());

        int spriteId = Player.getUniquePlayerInstance().getSpriteId();
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


        Button endScreenButton = findViewById(R.id.nextScreenButton);

        endScreenButton.setOnClickListener(view -> {
            Intent intent = new Intent(LegacyGameActivity3.this, EndScreenActivity.class);
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
