package com.example.cs2340c_team28;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team28.helpers.GameViewModelTester;
import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.powerup.Decorator;
import com.example.cs2340c_team28.models.powerup.SuperSpeed;
import com.example.cs2340c_team28.viewmodels.ConfigScreenViewModel;
import com.example.cs2340c_team28.viewmodels.GameViewModel;

import org.junit.Test;

/**
 * Unit tests for the config screen
 */
public class PowerUpTests {

    @Test
    public void checkIfSpeedUpdates() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getForest());


        long playerDuration = player.getCurrentMovement().getDuration();
        Decorator decorator = new Decorator(new SuperSpeed());
        decorator.activate();
        long newDuration = player.getCurrentMovement().getDuration();
        assertNotEquals(playerDuration, newDuration);

    }

}
