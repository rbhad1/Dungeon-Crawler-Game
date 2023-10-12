package com.example.cs2340c_team28;

import com.example.cs2340c_team28.models.Difficulty;
import com.example.cs2340c_team28.viewmodels.ConfigScreenViewModel;
import com.google.android.material.checkbox.MaterialCheckBox;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.RadioGroup;

/**
 * Unit tests for the config screen
 */
public class ConfigTests {

    @Test
    public void playerNameWhiteSpace() {
        String name = new String("   ");
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        assertFalse(configScreenViewModel.playerNameValid(name));
    }
    @Test
    public void playerNameNull() {
        String name = new String();
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        assertFalse(configScreenViewModel.playerNameValid(name));
    }

    @Test
    public void playerNameInvalidCharacter() {
        String name = new String(" \\ $%#$  ");
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        assertFalse(configScreenViewModel.playerNameValid(name));
    }
    @Test
    public void playerNameTooLong() {
        String name = new String("mmmmmmmmmmmm");
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        assertFalse(configScreenViewModel.playerNameLength(name));
    }
    @Test
    public void playerNameShort() {
        String name = new String("mmmmmm");
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        assertTrue(configScreenViewModel.playerNameLength(name));
    }

    @Test
    public void testGameCannotStart() {
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        assertFalse(configScreenViewModel.isGameCanStart());
    }

    @Test
    public void testGameCanStart() {
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        configScreenViewModel.setPlayerName("Player");
        configScreenViewModel.setDifficulty(Difficulty.EASY);
        assertTrue(configScreenViewModel.isGameCanStart());
    }

    @Test
    public void playerNameValidNotDifficulty() {
        String name = "Player";
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        configScreenViewModel.setPlayerName(name);
        assertTrue(configScreenViewModel.playerNameLength(name));
        assertTrue(configScreenViewModel.playerNameValid(name));
        assertFalse(configScreenViewModel.isGameCanStart());
    }

    @Test
    public void difficultyValidNotPlayer() {
        String name = "+Gamer123";
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel();
        configScreenViewModel.setPlayerName(name);
        assertTrue(configScreenViewModel.playerNameLength(name));
        assertFalse(configScreenViewModel.playerNameValid(name));
        assertFalse(configScreenViewModel.isGameCanStart());
    }
}
