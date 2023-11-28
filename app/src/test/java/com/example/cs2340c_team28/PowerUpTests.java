package com.example.cs2340c_team28;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.example.cs2340c_team28.helpers.GameViewModelTester;
import com.example.cs2340c_team28.helpers.LibGdxTester;
import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.Movable;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.enemies.AirEnemy;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;
import com.example.cs2340c_team28.models.powerup.Decorator;
import com.example.cs2340c_team28.models.powerup.PickupEffect;
import com.example.cs2340c_team28.models.powerup.SuperSpeed;
import com.example.cs2340c_team28.viewmodels.ConfigScreenViewModel;
import com.example.cs2340c_team28.viewmodels.GameViewModel;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Unit tests for the config screen
 */
public class PowerUpTests {

    static {
        LibGdxTester.initializeForTests();
    }

    @Test
    public void checkIfSpeedUpdates() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getForest());
        Movable movable = Player.getInstance();


        Method handleMovement = GameViewModel.class.getDeclaredMethod("handleMovement", Movable.class);
        handleMovement.setAccessible(true);

        movable.setPosition(new Position(0, 0), true);
        Movement movement = new Movement(movable.getPosition(true), new Position(1, 1), true, 200);
        movable.setCurrentMovement(movement);

        handleMovement.invoke(gameViewModel, movable);

        long playerDuration = player.getCurrentMovement().getDuration();


        handleMovement.invoke(gameViewModel, movable);

        Decorator decorator = new Decorator(new SuperSpeed());
        decorator.activate();
        long newDuration = player.getCurrentMovement().getDuration();

        assertNotEquals(playerDuration, newDuration);

    }

    @Test
    public void pickupEffectInForestMap() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();

        Player player = Player.getInstance();
        Game game = Game.getInstance();
        game.setCurrentMap(gameViewModel.getForest());

        Game.getInstance().getPickupEffectList();


    }

}
