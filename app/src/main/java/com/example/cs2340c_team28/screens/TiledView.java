package com.example.cs2340c_team28.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.example.cs2340c_team28.models.enemies.Enemy;
import com.example.cs2340c_team28.models.enemies.EnemyHandler;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.movement.MovementListener;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.TileMovementStrategy;
import com.example.cs2340c_team28.viewmodels.GameViewModel;

import java.util.List;

public class TiledView implements Screen {

    /**
     * Renderer for the tilemap
     */
    private OrthogonalTiledMapRenderer renderer;
    /**
     * Camera that allows us to view the screen correctly
     */
    private OrthographicCamera camera;
    /**
     * Stage for the text and buttons
     */
    private Stage stage;
    /**
     * The gameview model
     */
    private GameViewModel gameViewModel;
    /**
     * Texture for the player sprite
     */
    private Texture playerImage;

    /**
     * Batch of sprites to be rendered
     */
    private SpriteBatch batch;
    /**
     * The fitted viewport for the stage
     */
    private FitViewport fitted;
    /**
     * The font for the text
     */
    private BitmapFont font;

    /**
     * TiledView Constructor
     * @param gameViewModel the view model the the tile model takes in
     */
    public TiledView(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }

    private EnemyHandler enemyHandler = new EnemyHandler();

    public List<Enemy> getEnemyList() {
        return enemyHandler.main();
    }

    /**
     * Creates the stage with the buttons and text fields
     */
    public void create() {
        camera = new OrthographicCamera();
        fitted = new FitViewport(9 * 32, 16 * 32, camera);

        Player.getInstance().setX(4, true);
        Player.getInstance().setY(9, true);

        stage = new Stage(fitted);

        MovementListener listener = new MovementListener();
        listener.setMovementStrategy(new TileMovementStrategy());

        stage.addListener(listener);
        Gdx.input.setInputProcessor(stage);

        int spriteId = Player.getInstance().getSpriteId();
        String imageResource;
        switch (spriteId) {
        case 1:
            imageResource = "person1.png";
            break;
        case 2:
            imageResource = "person2.png";
            break;
        default:
            imageResource = "person3.png";
            break;
        }
        playerImage = new Texture(imageResource);
        batch = new SpriteBatch();
        font = new BitmapFont();



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setMap(Game.getInstance().getCurrentMap());

        stage.draw();
        stage.act();

        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "" + Player.getInstance().getName(), 0, 16 * 32);
        font.draw(batch, "" + Game.getInstance().getDifficulty(), 0, 16 * 31);
        font.draw(batch, "Score: " + Game.getInstance().getScore(), 6 * 32, 16 * 32);
        font.draw(batch, "HP: " + Player.getInstance().getHp(), 6 * 32, 16 * 31);
        batch.draw(playerImage, Player.getInstance().getX(false),
                Player.getInstance().getY(false), 32, 32);





        //int UNIT = 32;

        for (Enemy enemy : getEnemyList()) {
            // TODO probably want to randomize start position
            batch.draw(enemy.getTexture(), enemy.getX(false),
                    enemy.getY(false), 32, 32);

        }

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        fitted.setScreenSize(width, height);
        camera.update();
    }

    @Override
    public void show() {
        create();
        renderer = new OrthogonalTiledMapRenderer(Game.getInstance().getCurrentMap());
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
        renderer.dispose();
    }
}
