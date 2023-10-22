package com.example.cs2340c_team28.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.MovementListener;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.TileMovementStrategy;
import com.example.cs2340c_team28.viewmodels.GameViewModel;

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
     * Button to open dungeon map
     */
    private TextButton button1;
    /**
     * Button to open water map
     */
    private TextButton button2;
    /**
     * Button to return to forest
     */
    private TextButton button3;
    /**
     * Stage for the text and buttons
     */
    private Stage stage;

    /**
     * Text to display health, difficulty, name, score
     */
    private Label text;
    /**
     * The player's name
     */
    private Label playerName;
    /**
     * The player's health
     */
    private Label playerHealth;
    /**
     * The difficulty level
     */
    private Label difficulty;
    /**
     * The text styling for the Text and buttons
     */
    private Label.LabelStyle textStyle;

    private GameViewModel gameViewModel;
    /**
     * Texture for the player sprite
     */
    private Texture playerImage;

    /**
     * Batch of sprites to be rendered
     */
    private SpriteBatch batch;
    private FitViewport fitted;
    private ExtendViewport extended;

    private BitmapFont font;

    public TiledView(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
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
        int rowHeight = Gdx.graphics.getHeight() / 12;
        int colWidth = Gdx.graphics.getWidth() / 12;
        //creating buttons

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;





        // scoring text
        textStyle = new Label.LabelStyle();
        textStyle.font = new BitmapFont();
        textStyle.fontColor = Color.WHITE;

        text = new Label("Score: " + Game.getInstance().getScore(), textStyle);
        text.setPosition(colWidth * 7, Gdx.graphics.getHeight() - 30);
        text.setFontScale(4f);
        //stage2.addActor(text);

        playerName = new Label(Player.getInstance().getName(), textStyle);
        playerHealth = new Label(Player.getInstance().getHp()
                + "/" + Player.getInstance().getOriginalHp() + " HP", textStyle);
        difficulty = new Label(Game.getInstance().getDifficulty().toString(), textStyle);
        playerName.setPosition(colWidth, Gdx.graphics.getHeight() - 30);
        playerName.setFontScale(4f);
        playerHealth.setPosition(colWidth * 7, Gdx.graphics.getHeight() - 80);
        playerHealth.setFontScale(4f);
        difficulty.setPosition(colWidth, Gdx.graphics.getHeight() - 80);
        difficulty.setFontScale(4f);
        //stage2.addActor(playerName);
        //stage2.addActor(playerHealth);
        //stage2.addActor(difficulty);

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

        //updating time score
        text.setText(Game.getInstance().getScore());

        stage.draw();
        stage.act();


        renderer.setView(camera);
        renderer.render();
        //Player.getInstance().updateMovement();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "" + Player.getInstance().getName(), 0, 16 * 32);
        font.draw(batch, "" + Game.getInstance().getDifficulty(), 0, 16 * 31);
        font.draw(batch, "Score: " + Game.getInstance().getScore(), 6 * 32, 16 * 32);
        font.draw(batch, "HP: " + Player.getInstance().getHp(), 6 * 32, 16 * 31);
        batch.draw(playerImage, Player.getInstance().getX(false),
                Player.getInstance().getY(false), 32, 32);
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
