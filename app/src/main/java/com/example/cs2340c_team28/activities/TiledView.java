package com.example.cs2340c_team28.activities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TiledView implements Screen {

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Skin skin;
    private TextButton button1;
    private TextButton button2;
    private Stage stage;
    TextButton.TextButtonStyle textButtonStyle;

    public void create() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;
        button1 = new TextButton("To Dungeon", textButtonStyle);
        button1.setSize(col_width*4,row_height);
        button1.setPosition(col_width,Gdx.graphics.getHeight()/2);
        button1.setTransform(true);
        button1.scaleBy(2f);
        stage.addActor(button1);
        button2 = new TextButton("To Forest", textButtonStyle);
        button2.setSize(col_width*4,row_height);
        button2.setPosition(col_width,Gdx.graphics.getHeight()/2);
        button2.setTransform(true);
        button2.scaleBy(2f);
        stage.addActor(button1);
        button1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                map = new TmxMapLoader().load("dungeon-map.tmx");
                renderer = new OrthogonalTiledMapRenderer(map);
                camera = new OrthographicCamera();
                stage.addActor(button2);
                button1.remove();
            }
        });
        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                map = new TmxMapLoader().load("forest-map.tmx");
                renderer = new OrthogonalTiledMapRenderer(map);
                camera = new OrthographicCamera();
                stage.addActor(button1);
                button2.remove();
            }
        });
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(100, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //create();
        stage.draw();
        stage.act();

        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(w, h);
        camera.setToOrtho(true, w, h);
        //viewport = new StretchViewport(480, 800, camera);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
        camera.update();
        renderer.setView(camera);
        renderer.render();

    }
    // TODO Fix viewport so that image takes up entire screen @Elijah
    @Override
    public void resize(int width, int height) {
        //camera.setToOrtho(false, width, height);
        //viewport.update(width, height);
        //camera.viewportHeight = height;
        //camera.viewportWidth = width;
        //camera.update();
    }
    @Override
    public void show() {
        create();
        map = new TmxMapLoader().load("forest-map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();

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
