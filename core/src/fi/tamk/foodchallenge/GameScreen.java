package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Runs the actual game.
 *
 * Render loop contains updating camera, map and banner positions, drawing them,
 * animating and moving the player, checking if it hits food objects
 * and checking if the game is over. If so, draw post game buttons.
 */
public class GameScreen implements Screen {

    /**
     * Main class of the package, extends Game.
     */
    private FoodChallenge game;

    /**
     * Orthographic camera.
     */
    private OrthographicCamera camera;

    /**
     * Player with sprite and animation.
     */
    private Player player;

    /**
     * Music that is looped through the game.
     */
    private Music backgroundMusic;

    /**
     * Sound effect played when player eats something.
     */
    private Sound eatsound;

    /**
     * Texture of top banner.
     */
    private Texture banner;

    /**
     * Texture for post-game buttons.
     */
    private Texture btnTexture;

    /**
     * Retry button, used in post-game screen.
     */
    private Rectangle retryButton;

    /**
     * Menu button, used in post-game screen.
     */
    private Rectangle menuButton;

    /**
     * Recipe button, used in post-game screen.
     */
    private Rectangle recipeButton;

    /**
     * Current level, for saving score and unlocking recipes.
     */
    private String level;

    /**
     * Includes map with tileset and rectangles.
     */
    private TiledMap tiledmap;

    /**
     * Renders the tiled map.
     */
    private TiledMapRenderer tiledMapRenderer;

    /**
     * Rectangles of the unhealthy foods.
     */
    private MapLayer burgerLayer;

    /**
     * Rectangles of the healthy foods.
     */
    private MapLayer carrotLayer;

    /**
     * Height of the current map (ends game when reached).
     */
    private float mapHeight;

    /**
     * Scrolling speed of the screen.
     */
    private float scrollSpeed = 4f;

    /**
     * Position of the score counter on Y-axis.
     */
    private float scorePosY;

    /**
     * Position of the top banner on Y-axis.
     */
    private float bannerPosY;

    /**
     * Volume of sound effects.
     */
    private float fxOnOff;

    /**
     * Score and health of the player.
     */
    private int HP = 100;

    /**
     * Sets game on, this gets rendered in an if statement.
     */
    private boolean gameIsOn = true;

    /**
     * Current game is not yet completed.
     */
    private boolean completed = false;

    /**
     * Creates camera, player, music, map and texture.
     *
     * @param game   Instance of the main class.
     * @param map    Selects the map.
     * @param height Sets height of the map.
     */
    GameScreen(FoodChallenge game, String map, float height) {
        // Set game to this instance.
        this.game = game;

        // Create camera and set it to orthogonal view.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 960);

        // Load and create map and renderer.
        tiledmap = new TmxMapLoader().load(map);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledmap);

        // Create layers for different foods.
        burgerLayer = tiledmap.getLayers().get("ObjectLayer");
        carrotLayer = tiledmap.getLayers().get("ObjectLayer2");

        // Set map properties.
        mapHeight = height;
        level = map;

        // Create player.
        player = new Player();

        // Create top banner and its position on Y-axis.
        banner = new Texture("boringbanner.png");
        scorePosY = 945f;
        bannerPosY = 910f;

        loadMusic();
    }

    @Override
    public void render(float delta) {
        if (gameIsOn) {
            clearScreen();
            renderTiledMap();
            moveCamera();
            updateBannerPos();
            player.walk(); // Walking animation.
            drawEverything();
            checkCollisions(); // Check if foods are eaten.
            movePlayer(); // Moves player.
            isGameOver(); // Check if game is over.
        } else {
            clearScreen();
            drawButtons(); // Draws post game buttons.
            checkPresses(); // Checks if they're pressed.
        }
    }

    /**
     * Loads and sets the volume of eating sound and background music.
     */
    private void loadMusic() {
        // Default volume values.
        fxOnOff = 0.5f;
        float mOnOff = 0.5f;

        // If preferences are set to off, set volume off.
        if (!game.prefs.getBoolean("effexOn")) fxOnOff = 0f;
        if (!game.prefs.getBoolean("musicOn")) mOnOff = 0f;

        // Load eating sound and set volume.
        eatsound = Gdx.audio.newSound(Gdx.files.internal("eatsound.mp3"));
        eatsound.setVolume(0,fxOnOff);

        // Load background music. Set volume & looping. Play.
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("summerhit.mp3"));
        backgroundMusic.setVolume(mOnOff);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    /**
     * Clears the screen and sets projection matrix.
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(1f,140/255f,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Renders tiled map and sets its view according to camera.
     */
    private void renderTiledMap() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    /**
     * Moves camera and updates it.
     */
    private void moveCamera() {
        camera.position.y += scrollSpeed;
        camera.update();
    }

    /**
     * Updates banner position according to camera scroll.
     */
    private void updateBannerPos() {
        bannerPosY += scrollSpeed;
        scorePosY += scrollSpeed;
    }

    /**
     * Draws player, banner and font.
     */
    private void drawEverything() {
        game.batch.begin();
        game.batch.draw(player.currentFrameTexture,
                player.getPlayerX(),player.getPlayerY(),
                player.getPlayerRectangle().getWidth(),
                player.getPlayerRectangle().getHeight());
        game.batch.draw(banner, 0f, bannerPosY);
        game.bitmapFont.draw(game.batch, game.myBundle.get("score") + HP, 25f, scorePosY);
        game.batch.end();
    }

    /**
     * Calls checkLayer() for all different foods.
     */
    private void checkCollisions() {
        checkLayer(burgerLayer);
        checkLayer(carrotLayer);
    }

    /**
     * Checks if objects are being hit by the player.
     *
     * If so, adds score according to object type and calls clearIt().
     *
     * @param objectLayer Which kind of food, healthy or not?
     */
    private void checkLayer(MapLayer objectLayer) {
        MapLayer layer = objectLayer;
        MapObjects mapObjects = layer.getObjects();
        Array<RectangleMapObject> mapObjectsArray = mapObjects.getByType(RectangleMapObject.class);

        for (RectangleMapObject rectangleObject : mapObjectsArray) {
            Rectangle objectRectangle = rectangleObject.getRectangle();

            if (player.playerRectangle.getBoundingRectangle().overlaps(objectRectangle)) {
                if (layer == burgerLayer) {
                    HP -= 20;
                } else if (layer == carrotLayer) {
                    HP += 10;
                }

                layer.getObjects().remove(rectangleObject);
                clearIt(objectRectangle.getX(),objectRectangle.getY());
                eatsound.play(fxOnOff);
            }
        }
    }

    /**
     * Clears food objects when the player hits them.
     *
     * @param xCoord X coordinate of the food object.
     * @param yCoord Y coordinate of the food object.
     */
    private void clearIt(float xCoord, float yCoord) {
        int indexX = (int) xCoord / 48;
        int indexY = (int) yCoord / 48;

        TiledMapTileLayer burgers = (TiledMapTileLayer) tiledmap.getLayers().get("burgers");
        TiledMapTileLayer carrots = (TiledMapTileLayer) tiledmap.getLayers().get("carrots");
        burgers.setCell(indexX,indexY,null);
        carrots.setCell(indexX,indexY,null);
    }

    /**
     * Moves player according to input.
     *
     * If no input is given, player scrolls with the camera.
     */
    private void movePlayer() {
        // Set control rectangle around the player
        player.playerControlRectangle.setX(player.playerRectangle.getX() - 77);
        player.playerControlRectangle.setY(player.playerRectangle.getY() - 85);

        if (Gdx.input.isTouched()) {
            int realX = Gdx.input.getX();
            int realY = Gdx.input.getY();

            Vector3 touchPos = new Vector3(realX, realY, 0);
            camera.unproject(touchPos);

            // If touch position is inside control rectangle, move player.
            if(player.playerControlRectangle.contains(touchPos.x,touchPos.y)) {
                player.setPlayerX(touchPos.x - 30 ); // Positions the player
                player.setPlayerY(touchPos.y + 35); // just above the finger.
            }
        }

        // Move player with camera.
        player.setPlayerY(player.getPlayerY()+scrollSpeed);
    }

    /**
     * Checks if map or HP has ended and if so, gameIsOver().
     *
     * Also checks if systems back button has been pressed.
     */
    private void isGameOver() {
        if (player.getPlayerY() > mapHeight || HP <= 0) gameIsOver();

        checkSystemBackBtn();
    }

    /**
     * Stops game rendering and creates post-game buttons.
     *
     * Sets level completed if possible.
     */
    private void gameIsOver() {
        gameIsOn = false;

        if (HP >= 100) completed = true;

        createBtns();
    }

    /**
     * Creates retry & menu buttons.
     *
     * If the level has been completed, create recipe button.
     */
    private void createBtns() {
        btnTexture = new Texture("gameOver.png");
        retryButton = new Rectangle(65f, bannerPosY-275f,
                btnTexture.getWidth(), btnTexture.getHeight());
        menuButton = new Rectangle(65f, bannerPosY-400f,
                btnTexture.getWidth(), btnTexture.getHeight());

        if (completed) {
            recipeButton = new Rectangle(65f, bannerPosY-575f,
                    btnTexture.getWidth(), btnTexture.getHeight());
        }
    }

    /**
     * Draws buttons and feedback when game is over.
     */
    private void drawButtons() {
        game.batch.begin();

        // Draw buttons
        game.batch.draw(btnTexture,
                retryButton.getX(), retryButton.getY(),
                btnTexture.getWidth(),
                btnTexture.getHeight());
        game.batch.draw(btnTexture,
                menuButton.getX(), menuButton.getY(),
                btnTexture.getWidth(),
                btnTexture.getHeight());

        // Draw text on top of buttons
        game.bitmapFont.draw(game.batch,
                game.myBundle.get("retry"),
                retryButton.getX() + 25f,
                retryButton.getY() + 65f);
        game.bitmapFont.draw(game.batch,
                game.myBundle.get("menu"),
                menuButton.getX() + 25f,
                menuButton.getY() + 65f);

        // If this level is already completed, don't print feedback
        if (!game.prefs.getBoolean(level)) {
            game.bitmapFont.draw(game.batch, getFeedback(), 65f, bannerPosY - 100f);
        }

        // If current game is completed, draw recipe button
        if (completed) {
            game.batch.draw(btnTexture,
                    recipeButton.getX(), recipeButton.getY(),
                    btnTexture.getWidth(), btnTexture.getHeight());
            game.bitmapFont.draw(game.batch,
                    game.myBundle.get("showrecipe"),
                    recipeButton.getX() + 25f,
                    recipeButton.getY() + 65f);
        }

        game.batch.end();
    }

    /**
     * Changes the post-game greeting according to HP.
     *
     * @return feedback Greeting shown when game is over.
     */
    private String getFeedback() {
        String feedback;

        if (completed) {
            feedback = game.myBundle.get("unlocked");
        } else {
            feedback = game.myBundle.get("needmore");
        }

        return feedback;
    }

    /**
     * Checks if post-game buttons are pressed and changes screen accordingly.
     */
    private void checkPresses() {
        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (retryButton.contains(touchPos.x, touchPos.y)) {
                saveScore();
                backgroundMusic.stop();
                game.setScreen(new GameScreen(game, level, mapHeight));
            }

            if (menuButton.contains(touchPos.x, touchPos.y)) {
                saveScore();
                backgroundMusic.stop();
                game.setScreen(new MenuScreen(game));
                dispose();
            }

            if (completed) {
                if (recipeButton.contains(touchPos.x, touchPos.y)) {
                    saveScore();
                    backgroundMusic.stop();
                    game.setScreen(new RecipeScreen(game));
                    dispose();
                }
            }
        }

        checkSystemBackBtn();
    }

    /**
     * Saves current score and unlocks recipe for this level.
     *
     * If current score is top1-4, items below it will be moved down.
     */
    private void saveScore() {
        // Get old high scores from preferences.
        int old1 = game.prefs.getInteger("highscore");
        int old2 = game.prefs.getInteger("score2");
        int old3 = game.prefs.getInteger("score3");
        int old4 = game.prefs.getInteger("score4");

        // Compare current score to old scores and update preferences.
        if (HP > old1) {
            game.prefs.putInteger("highscore", HP);
            game.prefs.putInteger("score2", old1);
            game.prefs.putInteger("score3", old2);
            game.prefs.putInteger("score4", old3);
            game.prefs.putInteger("score5", old4);
        } else if (HP > old2) {
            game.prefs.putInteger("score2", HP);
            game.prefs.putInteger("score3", old2);
            game.prefs.putInteger("score4", old3);
            game.prefs.putInteger("score5", old4);
        } else if (HP > old3) {
            game.prefs.putInteger("score3", HP);
            game.prefs.putInteger("score4", old3);
            game.prefs.putInteger("score5", old4);
        } else if (HP > old4) {
            game.prefs.putInteger("score4", HP);
            game.prefs.putInteger("score5", old4);
        } else if (HP > game.prefs.getInteger("score5")) {
            game.prefs.putInteger("score5", HP);
        } else {
            game.prefs.putInteger("sixth", HP);
        }

        // Unlocks recipe if the level is completed.
        if (completed) {
            game.prefs.putBoolean(level, true);
        }

        // Update the number of games played.
        game.prefs.putInteger("gamesPlayed", game.prefs.getInteger("gamesPlayed") + 1);

        // Save preferences.
        game.prefs.flush();
    }

    /**
     * Checks if the systems back button is pressed.
     *
     * If it is, goes to SelectLevel -screen.
     */
    private void checkSystemBackBtn() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new SelectLevel(game));
            backgroundMusic.stop();
        }
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}