package com.example.cs2340c_team28.helpers;

import static org.junit.Assert.assertNotNull;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;

public class LibGdxTester {
    public static void initializeForTests() {
        HeadlessApplicationConfiguration config = new HeadlessApplicationConfiguration();
        HeadlessApplication headlessApplication =
                new HeadlessApplication(new com.badlogic.gdx.Game() {
                    @Override
                    public void create() {
                        System.out.println("Created a fake game");
                    }
                }, config);
        assertNotNull(Gdx.files);
        Gdx.gl = new GL20Tester();
    }
}
