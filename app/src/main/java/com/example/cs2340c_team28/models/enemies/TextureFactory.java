package com.example.cs2340c_team28.models.enemies;

import com.badlogic.gdx.graphics.Texture;

public class TextureFactory {

    private static final TextureFactory INSTANCE = new TextureFactory();

    private TextureFactory() {

    }

    public void setForUnitTests(boolean forUnitTests) {
        this.forUnitTests = forUnitTests;
    }

    private boolean forUnitTests = false;

    public Texture createTexture(String filename) {
        return new Texture(forUnitTests ? "src/main/assets/" + filename : filename);
    }

    public static TextureFactory getInstance() {
        return INSTANCE;
    }



}
