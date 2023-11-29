package com.example.cs2340c_team28;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team28.helpers.GameViewModelTester;
import com.example.cs2340c_team28.helpers.LibGdxTester;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;
import com.example.cs2340c_team28.models.movement.TileMovementStrategy;
import com.example.cs2340c_team28.models.powerup.PowerUp;
import com.example.cs2340c_team28.models.powerup.SuperSpeedDecorator;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Unit tests for the config screen
 */
public class PowerUpTests {

    static {
        LibGdxTester.initializeForTests();
    }

    @Test
    public void checkIfSpeedUpdates() {

        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getForest());

        player.setPosition(new Position(0, 0), true);
        Movement movement = new Movement(player.getPosition(true), new Position(1, 1), true, 200);
        player.setCurrentMovement(movement);

        gameViewModel.cycledUpdate(4, 1);

        long originalMovementDuration = player.getCurrentMovement().getDuration();

        player.setPowerUp(new SuperSpeedDecorator());
        gameViewModel.cycledUpdate(4, 1);

        long newMovementDuration = player.getCurrentMovement().getDuration();

        assertNotEquals(originalMovementDuration, newMovementDuration);

    }

    @Test
    public void testingDurationInDeactivation() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getForest());

        // Set the powerup
        PowerUp powerUp = new SuperSpeedDecorator();
        powerUp.setDuration(400);
        player.setPowerUp(powerUp);

        // Do the initial movement
        player.setPosition(new Position(2, 9), true);
        Movement movement = new Movement(player.getPosition(true), new Position(3, 9), true, 200);
        player.setCurrentMovement(movement);
        long originalMovementDuration = player.getCurrentMovement().getDuration();

        // This should update the movement duration
        gameViewModel.cycledUpdate(4, 1);
        long newMovementDuration = player.getCurrentMovement().getDuration();

        // Make sure the movement duration updated
        assertNotEquals(originalMovementDuration, newMovementDuration);

        // Now increment time so that the movement finishes AND power-up expires
        gameViewModel.cycledUpdate(10, 50);

        // Set another movement
        movement = new Movement(player.getPosition(true), new Position(2, 9), true, 200);
        player.setCurrentMovement(movement);
        originalMovementDuration = movement.getDuration();

        gameViewModel.cycledUpdate(4, 1);

        // Make sure the movement duration isn't changed this time around, since power-up ended
        newMovementDuration = player.getCurrentMovement().getDuration();
        assertEquals(originalMovementDuration, newMovementDuration);
        gameViewModel.cycledUpdate(10, 50);
            }

}
