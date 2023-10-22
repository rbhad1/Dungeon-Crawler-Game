package com.example.cs2340c_team28.models;

import android.util.Log;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MovementListener extends InputListener {
    private static final String TAG = MovementListener.class.getSimpleName();
    private MovementStrategy movementStrategy;
    // think we only will be using keyDown and keyUp

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                movementStrategy.moveLeft();
                Log.d(TAG, "keyDown: " + keycode);
                break;
            case Input.Keys.RIGHT:
                movementStrategy.moveRight();
                Log.d(TAG, "keyDown: " + keycode);
                break;
            case Input.Keys.UP:
                movementStrategy.moveUp();
                Log.d(TAG, "keyDown: " + keycode);
                break;
            case Input.Keys.DOWN:
                movementStrategy.moveDown();
                Log.d(TAG, "keyDown: " + keycode);
                break;
            default:

        }
        return true;
    }

    @Override
    public boolean keyUp(InputEvent event, int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                //Player.getInstance().setLeftMove(false);
                Log.d(TAG, "keyUp: " + keycode);
                break;
            case Input.Keys.RIGHT:
                //Player.getInstance().setRightMove(false);
                Log.d(TAG, "keyUp: " + keycode);
                break;
            default:
                Log.d(TAG, "keyUp: " + keycode);
        }
        return true;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }
}
