package com.example.cs2340c_team28.activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledView implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();
    }
    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void show() {

        map = new TmxMapLoader().load("screen1.tmx");

        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {
    }

    public void resume() {

    }

    public void dispose() {
        map.dispose();
        renderer.dispose();
    }

}
