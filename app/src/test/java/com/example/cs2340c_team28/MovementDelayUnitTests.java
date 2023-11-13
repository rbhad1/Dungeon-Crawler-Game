package com.example.cs2340c_team28;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import com.example.cs2340c_team28.annotation.Sprint;
import com.example.cs2340c_team28.helpers.GameViewModelTester;
import com.example.cs2340c_team28.helpers.LibGdxTester;
import com.example.cs2340c_team28.models.Movable;
import com.example.cs2340c_team28.models.enemies.AirEnemy;
import com.example.cs2340c_team28.models.movement.Movement;
import com.example.cs2340c_team28.models.movement.Position;
import com.example.cs2340c_team28.viewmodels.GameViewModel;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MovementDelayUnitTests {

    static {
        LibGdxTester.initializeForTests();
    }

    @Test @Sprint(4)
    public void movementDoesDelay() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();
        gameViewModel.setTime(0);
        Movable movable = new AirEnemy();

        try {
            // Break access control rules
            Method handleMovement =
                    GameViewModel.class.getDeclaredMethod("handleMovement", Movable.class);
            handleMovement.setAccessible(true);

            // Set first position and create movement - this is just a standard movement
            movable.setPosition(new Position(0, 0), true);
            Movement movement = new Movement(
                    movable.getPosition(true),
                    new Position(1, 1),
                    true,
                    200
            );
            movement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
            movable.setCurrentMovement(movement);

            // Process the movement
            handleMovement.invoke(gameViewModel, movable);

            gameViewModel.setTime(50);
            assertEquals(Movement.Status.IN_PROGRESS,
                    movable.getCurrentMovement().getStatus());

            gameViewModel.setTime(250);
            handleMovement.invoke(gameViewModel, movable);

            // Test the movement
            assertEquals(Movement.Status.COMPLETE,
                    movable.getCurrentMovement().getStatus());
            assertEquals(
                    new Position(1, 1),
                    movable.getPosition(true)
            );

            // Reset time for GameViewModelTester
            gameViewModel.setTime(0);

            // Create second movement
            movement = new Movement(
                    movable.getPosition(true),
                    new Position(2, 2),
                    true,
                    200
            );
            movement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
            movement.setEndDelay(200);
            movable.setCurrentMovement(movement);

            // Process the movement
            handleMovement.invoke(gameViewModel, movable);

            gameViewModel.setTime(50);
            assertEquals(Movement.Status.IN_PROGRESS,
                    movable.getCurrentMovement().getStatus());

            gameViewModel.setTime(250);
            handleMovement.invoke(gameViewModel, movable);
            assertEquals(Movement.Status.DELAYING,
                    movable.getCurrentMovement().getStatus());

            gameViewModel.setTime(500);
            handleMovement.invoke(gameViewModel, movable);


            // Test the movement
            assertEquals(Movement.Status.COMPLETE,
                    movable.getCurrentMovement().getStatus());
            assertEquals(
                    new Position(2, 2),
                    movable.getPosition(true)
            );

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            fail();
        }

    }

    @Test @Sprint(4)
    public void movementDoesNeverDelay() {
        GameViewModelTester gameViewModel = new GameViewModelTester();
        gameViewModel.doPreinitialization();
        gameViewModel.setTime(0);
        Movable movable = new AirEnemy();

        try {
            // Break access control rules
            Method handleMovement =
                    GameViewModel.class.getDeclaredMethod("handleMovement", Movable.class);
            handleMovement.setAccessible(true);

            movable.setPosition(new Position(0, 0), true);
            Movement movement = new Movement(
                    movable.getPosition(true),
                    new Position(2, 2),
                    true,
                    200
            );
            movement.setCollisionStyle(Movement.CollisionStyle.IGNORE_COLLISIONS);
            movable.setCurrentMovement(movement);

            for (int i = 0; i < 300; i++) {
                gameViewModel.incrementTime(1);
                handleMovement.invoke(gameViewModel, movable);
                System.out.println(i+" "+movement.getStatus());
                assertNotEquals(Movement.Status.DELAYING, movement.getStatus());
            }
            assertEquals(Movement.Status.COMPLETE, movement.getStatus());

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            fail();
        }
    }
}