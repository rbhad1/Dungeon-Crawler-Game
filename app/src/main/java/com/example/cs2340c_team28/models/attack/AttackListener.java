package com.example.cs2340c_team28.models.attack;

import android.util.Log;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.IntSet;
import com.example.cs2340c_team28.models.movement.MovementListener;
import com.example.cs2340c_team28.models.movement.MovementStrategy;

public class AttackListener extends InputListener {
    private AttackStrategy attackStrategy;
    private static final String TAG = AttackListener.class.getSimpleName();
    public boolean keyDown(InputEvent event, int keycode) {
        if (keycode == Input.Keys.DOWN) {
            attackStrategy.attack();
        }
        return true;
    }


    public AttackStrategy getAttackStrategy() {
        return attackStrategy;
    }

    public void setAttackStrategy(AttackStrategy attackStrategy) {
        this.attackStrategy = attackStrategy;
    }
}
