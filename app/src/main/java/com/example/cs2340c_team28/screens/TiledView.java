package com.example.cs2340c_team28.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.example.cs2340c_team28.models.attack.StandardAttackStrategy;
import com.example.cs2340c_team28.models.enemies.Enemy;
import com.example.cs2340c_team28.models.enemies.EnemyListFactory;
import com.example.cs2340c_team28.models.Game;
import com.example.cs2340c_team28.models.enemies.trackers.TrackerEnemy;
import com.example.cs2340c_team28.models.movement.MovementListener;
import com.example.cs2340c_team28.models.attack.AttackListener;
import com.example.cs2340c_team28.models.Player;
import com.example.cs2340c_team28.models.movement.Position;
import com.example.cs2340c_team28.models.movement.TileMovementStrategy;
import com.example.cs2340c_team28.models.powerup.PickupEffect;
import com.example.cs2340c_team28.viewmodels.GameViewModel;

import java.util.List;
import java.util.Set;

public class TiledView implements Screen {

    /**
     * Height of each tile
     */
    private static final int TILE_SIZE = 32;

    /**
     * Number of tiles in the horizontal direction
     */
    private static final int NUM_TILES_HORIZONTAL = 9;

    /**
     * Number of tiles in the vertical direction
     */
    private static final int NUM_TILES_VERTICAL = 18;

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

    private Button attackButton;
    private int count = 0;

    /**
     * TiledView Constructor
     * @param gameViewModel the view model the the tile model takes in
     */
    public TiledView(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }

    private EnemyListFactory enemyListFactory = new EnemyListFactory();

    public List<Enemy> getEnemyList() {
        return Game.getInstance().getEnemyList();
    }

    /**
     * Creates the stage with the buttons and text fields
     */
    public void create() {
        camera = new OrthographicCamera();
        fitted = new FitViewport(NUM_TILES_HORIZONTAL * TILE_SIZE,
                NUM_TILES_VERTICAL * TILE_SIZE,
                camera);

        Player.getInstance().setX(4, true);
        Player.getInstance().setY(9, true);

        stage = new Stage(fitted);

        MovementListener listener = new MovementListener();
        listener.setMovementStrategy(new TileMovementStrategy());

        AttackListener listener2 = new AttackListener();
        listener2.setAttackStrategy(new StandardAttackStrategy());

        stage.addListener(listener);
        // stage.addListener(listener2);
        stage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                new StandardAttackStrategy().attack();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        Gdx.input.setInputProcessor(stage);

        int spriteId = Player.getInstance().getSpriteId();
        String imageResource;
        switch (spriteId) {
        case 1:
            imageResource = "sprites_player/person1.png";
            break;
        case 2:
            imageResource = "sprites_player/person2.png";
            break;
        default:
            imageResource = "sprites_player/person3.png";
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

        font.draw(batch, "" + Player.getInstance().getName(),
                0, NUM_TILES_VERTICAL * TILE_SIZE);
        font.draw(batch, "" + Game.getInstance().getDifficulty(),
                0, (NUM_TILES_VERTICAL - 0.5f) * TILE_SIZE);
        font.draw(batch, "Score: " + Game.getInstance().getScore(),
                6 * TILE_SIZE, NUM_TILES_VERTICAL * TILE_SIZE);
        font.draw(batch, "HP: " + Player.getInstance().getHp(),
                6 * TILE_SIZE, (NUM_TILES_VERTICAL - 0.5f) * TILE_SIZE);

        if (Player.getInstance().getCanAttack()) {
            font.draw(batch, "Tap anywhere to attack!",
                    2 * TILE_SIZE, (NUM_TILES_VERTICAL - 1.25f) * TILE_SIZE);
        }

        batch.draw(playerImage, Player.getInstance().getX(false),
                Player.getInstance().getY(false), TILE_SIZE, TILE_SIZE);



        //int UNIT = 32;
        if (Player.getInstance().getAttack()) {
            batch.draw(new Texture("result.png"),
                    Player.getInstance().getX(false) - 32,
                    Player.getInstance().getY(false),
                    TILE_SIZE, TILE_SIZE);
            batch.draw(new Texture("result.png"),
                    Player.getInstance().getX(false),
                    Player.getInstance().getY(false) - 32,
                    TILE_SIZE, TILE_SIZE);
            batch.draw(new Texture("result.png"),
                    Player.getInstance().getX(false) + 32,
                    Player.getInstance().getY(false),
                    TILE_SIZE, TILE_SIZE);
            batch.draw(new Texture("result.png"),
                    Player.getInstance().getX(false),
                    Player.getInstance().getY(false) + 32,
                    TILE_SIZE, TILE_SIZE);
            count += 1;
            if (count > 20) {
                Player.getInstance().setAttack(false);
                count = 0;
            }
        }
        if (Player.getInstance().getCanAttack()) {
            batch.draw(new Texture("tnt.png"), Player.getInstance().getX(false) - 16,
                    Player.getInstance().getY(false), TILE_SIZE, TILE_SIZE);
        }

        for (Enemy enemy : Game.getInstance().getEnemyList()) {
            // TODO probably want to randomize start position
            batch.draw(enemy.getTexture(), enemy.getX(false),
                    enemy.getY(false), TILE_SIZE, TILE_SIZE);

            // Special case for BFS enemy
            if (enemy instanceof TrackerEnemy) {
                TrackerEnemy trackerEnemy = (TrackerEnemy) enemy;

                // If chase status is chasing, do additional renders
                if (trackerEnemy.getChaseStatus() == TrackerEnemy.ChaseStatus.CHASING) {

                    // Get target tile and waypoint tile set from the BFS enemy, null-check each
                    Position target = trackerEnemy.getGeneratedPath().getEndingTile();
                    Set<Position> waypointTileSet = trackerEnemy
                            .getGeneratedPath()
                            .getWaypointTileSet();

                    if (target != null && waypointTileSet != null) {
                        // Get the target tile as a graphical position, then draw texture
                        target = target.tileToGraphical();
                        batch.draw(trackerEnemy.getTargetTexture(),
                                target.getX(), target.getY(),
                                32, 32);
                        // Draw each of the waypoints
                        for (Position waypoint : waypointTileSet) {
                            waypoint = waypoint.tileToGraphical();
                            batch.draw(trackerEnemy.getWaypointTexture(),
                                    waypoint.getX(), waypoint.getY(),
                                    32, 32);
                        }
                    }
                }
            }
        }

        // Render pickup effects (power-ups)
        for (PickupEffect pickupEffect : Game.getInstance().getPickupEffectList()) {
            if (!pickupEffect.isCollected()) {
                batch.draw(pickupEffect.getPowerUp().getTexture(),
                        pickupEffect.getX(false), pickupEffect.getY(false));
            }
        }

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
