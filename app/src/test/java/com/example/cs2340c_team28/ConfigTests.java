package com.example.cs2340c_team28;

import com.example.cs2340c_team28.viewmodels.ConfigScreenViewModel;

import org.junit.Test;



import static org.junit.Assert.*;

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
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel(null);
        assertFalse(configScreenViewModel.playerNameLength(name));
    }
    @Test
    public void playerNameShort() {
        String name = new String("mmmmmm");
        ConfigScreenViewModel configScreenViewModel = new ConfigScreenViewModel(null);
        assertTrue(configScreenViewModel.playerNameLength(name));
    }
}
