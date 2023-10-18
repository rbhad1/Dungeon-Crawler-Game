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
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.cs2340c_team28.models.ConcreteMovement;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.MovementListener;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.viewmodels.GameViewModel;

public class TiledView implements Screen {
    /**
     * Tile map for background
     */
    private TiledMap map;
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

    public TiledView(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }

    /**
     * Creates the stage with the buttons and text fields
     */
    public void create() {
        stage = new Stage(new ScreenViewport());
        MovementListener listener = new MovementListener();
        listener.setMovementStrategy(new ConcreteMovement());
        stage.addListener(listener);
        int rowHeight = Gdx.graphics.getHeight() / 12;
        int colWidth = Gdx.graphics.getWidth() / 12;
        //creating buttons

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = new BitmapFont();
        textButtonStyle.fontColor = Color.WHITE;
        button1 = new TextButton("To Dungeon", textButtonStyle);
        button1.setSize(colWidth, rowHeight);
        button1.setPosition(colWidth * 9, Gdx.graphics.getHeight() - 300);
        button1.setTransform(true);
        button1.scaleBy(2f);
        stage.addActor(button1);
        button2 = new TextButton("To Water", textButtonStyle);
        button2.setSize(colWidth, rowHeight);
        button2.setPosition(colWidth * 9, Gdx.graphics.getHeight() - 300);
        button2.setTransform(true);
        button2.scaleBy(2f);
        button3 = new TextButton("End Game", textButtonStyle);
        button3.setSize(colWidth, rowHeight);
        button3.setPosition(colWidth * 9, Gdx.graphics.getHeight() - 300);
        button3.setTransform(true);
        button3.scaleBy(2f);
        // button to go to dungeon
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
        // button to go to water
        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                map = new TmxMapLoader().load("water-map.tmx");
                renderer = new OrthogonalTiledMapRenderer(map);
                camera = new OrthographicCamera();
                stage.addActor(button3);
                button2.remove();
            }
        });
        //button to go to forest
        button3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameViewModel.endGame();
            }
        });



        Gdx.input.setInputProcessor(stage);

        // scoring text
        textStyle = new Label.LabelStyle();
        textStyle.font = new BitmapFont();
        textStyle.fontColor = Color.WHITE;

        text = new Label("Score: " + Game.getUniqueGameInstance().getScore(), textStyle);
        text.setPosition(colWidth * 7, Gdx.graphics.getHeight() - 30);
        text.setFontScale(4f);
        stage.addActor(text);

        playerName = new Label(Player.getUniquePlayerInstance().getName(), textStyle);
        playerHealth = new Label(Player.getUniquePlayerInstance().getHp()
                + "/" +  Player.getUniquePlayerInstance().getOriginalHp() + " HP", textStyle);
        difficulty = new Label(Game.getUniqueGameInstance().getDifficulty().toString(), textStyle);
        playerName.setPosition(colWidth, Gdx.graphics.getHeight() - 30);
        playerName.setFontScale(4f);
        playerHealth.setPosition(colWidth * 7, Gdx.graphics.getHeight() - 80);
        playerHealth.setFontScale(4f);
        difficulty.setPosition(colWidth, Gdx.graphics.getHeight() - 80);
        difficulty.setFontScale(4f);
        stage.addActor(playerName);
        stage.addActor(playerHealth);
        stage.addActor(difficulty);

        int spriteId = Player.getUniquePlayerInstance().getSpriteId();
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

    }
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //updating time score
        text.setText(Game.getUniqueGameInstance().getScore());

        stage.draw();
        stage.act();
        int w = Gdx.graphics.getWidth();
        int h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(w / 2.0f, h / 2.0f);
        camera.setToOrtho(true, w / 2.0f, h / 2.0f);

        camera.position.set(270, 500, 0);

        camera.update();
        renderer.setView(camera);
        renderer.render();
        Player.getUniquePlayerInstance().updateMovement();
        batch.begin();
        batch.draw(playerImage, Player.getUniquePlayerInstance().getX(),
                Player.getUniquePlayerInstance().getY());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        camera.update();
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
