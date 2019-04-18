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
 * Runs the game.
 */
public class GameScreen implements Screen {

    protected FoodChallenge game;

    private OrthographicCamera camera;
    private Player player;
    private Music backgroundMusic;
    private Sound eatsound;
    private Texture banner;
    private Texture retryTexture;
    private Texture menuTexture;
    private Texture recipeTexture;
    private Rectangle retryButton;
    private Rectangle menuButton;
    private Rectangle recipeButton;

    private String level;
    private TiledMap tiledmap;
    private TiledMapRenderer tiledMapRenderer;
    private MapLayer burgerLayer;
    private MapLayer carrotLayer;
    // different foods here

    private float mapHeight;
    private float scrollSpeed = 4f; // How fast does the screen scroll?
    private float scorePosY;
    private float bannerPosY;
    private float fxOnOff;
    private int HP = 100;

    private boolean gameIsOn = true;
    private boolean completed = false;
    private boolean btnsCreated = false;

    /**
     * Creates camera, player and texture.
     *
     * @param game Don't even know what for but it must be there.
     * @param map Selects the map.
     */
    GameScreen(FoodChallenge game, String map, float height) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 960);
        player = new Player();

        loadMusic();

        level = map; // used in saveScore()
        tiledmap = new TmxMapLoader().load(map);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledmap);
        burgerLayer = tiledmap.getLayers().get("ObjectLayer");
        carrotLayer = tiledmap.getLayers().get("ObjectLayer2");
        mapHeight = height;

        banner = new Texture("boringbanner.png");

        scorePosY = 945f;
        bannerPosY = 910f;
    }

    @Override
    public void render(float delta) {
        if (gameIsOn) {
            clearScreen();
            renderTiledMap();
            moveCamera();
            updateBannerPos();
            player.walk();
            drawEverything();
            checkCollisions();
            movePlayer();
            isGameOver();
        } else {
            System.out.println(player.getPlayerY());
            clearScreen();
           gameIsOver();
        }
    }

    public void loadMusic() {
        fxOnOff = 0.5f;
        float mOnOff = 0.5f;

        if (!game.prefs.getBoolean("effexOn")) fxOnOff = 0f;
        if (!game.prefs.getBoolean("musicOn")) mOnOff = 0f;

        eatsound = Gdx.audio.newSound(Gdx.files.internal("eatsound.mp3"));
        eatsound.setVolume(0,fxOnOff);

        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("summerhit.mp3"));
        backgroundMusic.setVolume(mOnOff);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    /**
     * Clears the screen and sets projection matrix.
     */
    public void clearScreen() {
        Gdx.gl.glClearColor(1f,140/255f,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Renders tiled map.
     */
    public void renderTiledMap() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    /**
     * Moves camera and updates it.
     */
    public void moveCamera() {
        camera.position.y += scrollSpeed;
        camera.update();
    }

    /**
     * Updates banner position according to camera scroll.
     */
    public void updateBannerPos() {
        bannerPosY += scrollSpeed;
        scorePosY += scrollSpeed;
    }

    /**
     * Draws player, banner and font.
     */
    public void drawEverything() {
        game.batch.begin();
        game.batch.draw(player.currentFrameTexture,
                player.getPlayerX(),player.getPlayerY(),
                player.getPlayerRectangle().getWidth(),
                player.getPlayerRectangle().getHeight());
        game.batch.draw(banner, 0f, bannerPosY);
        game.bitmapFont.draw(game.batch, "HP: " + HP, 25f, scorePosY);
        game.batch.end();
    }

    /**
     * Calls checkLayer() for all different foods.
     */
    public void checkCollisions() {
        checkLayer(burgerLayer);
        checkLayer(carrotLayer);
        // different foods here
    }

    /**
     * Checks if objects are being hit by the player.
     *
     * If so, adds score according to object type and calls clearIt().
     */
    public void checkLayer(MapLayer objectLayer) {
        MapLayer layer = objectLayer;
        MapObjects mapObjects = layer.getObjects();
        Array<RectangleMapObject> mapObjectsArray = mapObjects.getByType(RectangleMapObject.class);

        for (RectangleMapObject rectangleObject : mapObjectsArray) {
            Rectangle objectRectangle = rectangleObject.getRectangle();

            if (player.playerRectangle.getBoundingRectangle().overlaps(objectRectangle)) {
                if (layer == burgerLayer) {
                    HP -= 20;
                    // animaatio pisteistä
                } else if (layer == carrotLayer) {
                    HP += 5;
                }

                System.out.println(HP);

                layer.getObjects().remove(rectangleObject);
                clearIt(objectRectangle.getX(),objectRectangle.getY());
                eatsound.play(fxOnOff);
            }
        }
    }

    /**
     * Clears objects when the player hits them.
     *
     * @param xCoord
     * @param yCoord
     */
    public void clearIt(float xCoord, float yCoord) {
        int indexX = (int) xCoord / 48;
        int indexY = (int) yCoord / 48;

        //TiledMapTileLayer burgerObject = (TiledMapTileLayer) tiledmap.getLayers().get("ObjectLayer");
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
    public void movePlayer() {
        player.playerControlRectangle.setX(player.playerRectangle.getX() - 77);
        player.playerControlRectangle.setY(player.playerRectangle.getY() - 85);
        if (Gdx.input.isTouched()) {
            int realX = Gdx.input.getX();
            int realY = Gdx.input.getY();

            Vector3 touchPos = new Vector3(realX, realY, 0);
            camera.unproject(touchPos);

            if(player.playerControlRectangle.contains(touchPos.x,touchPos.y)) {
                player.setPlayerX(touchPos.x - 30 ); // Positions the player
                player.setPlayerY(touchPos.y + 30); // just above the finger.
            }
        }

        player.setPlayerY(player.getPlayerY()+scrollSpeed); // Makes player move with camera.
    }

    /**
     * Checks if player has reached end of map or hp and calls gameIsOver.
     */
    public void isGameOver() {
       // if (player.getPlayerY() > 6900f || HP <= 0) gameIsOver();
        if (player.getPlayerY() > mapHeight || HP <= 0) gameIsOn = false;

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new SelectLevel(game));
            backgroundMusic.stop();
        }
    }

    /**
     * Sets level completed if possible, creates and draws post-game buttons.
     */
    public void gameIsOver() {
        if (HP >= 100) completed = true;
        if (!btnsCreated) createBtns();
        drawButtons();
        checkPresses();
    }

    /**
     * Creates retry & menu buttons.
     */
    private void createBtns() {
        retryTexture = new Texture("gameOver.png");
        menuTexture = new Texture("menuBtn.png");
        retryButton = new Rectangle(65f, bannerPosY-275f,
                retryTexture.getWidth(), retryTexture.getHeight());
        menuButton = new Rectangle(65f, bannerPosY-475f,
                menuTexture.getWidth(), menuTexture.getHeight());

        if (completed) {
        //    create Next Level button
            recipeTexture = new Texture("showRecipe.png");
            recipeButton = new Rectangle(65f, bannerPosY-675f,
                    recipeTexture.getWidth(), recipeTexture.getHeight());
        }

        btnsCreated = true;
    }

    /**
     * Draws buttons when game is over.
     */
    private void drawButtons() {
        game.batch.begin();
        game.batch.draw(retryTexture,
                retryButton.getX(), retryButton.getY(),
                retryTexture.getWidth(),
                retryTexture.getHeight());
        game.batch.draw(menuTexture,
                menuButton.getX(), menuButton.getY(),
                menuTexture.getWidth(),
                menuTexture.getHeight());
        game.bitmapFont.draw(game.batch, getFeedback(), 65f, bannerPosY - 100f);

        if (completed) {
        //     draw Next Level button
             game.batch.draw(recipeTexture,
                     recipeButton.getX(), recipeButton.getY(),
                     recipeTexture.getWidth(), recipeTexture.getHeight());
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
            feedback = "Resepti avattu!";
        } else {
            feedback = "Haha et osaa";
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
                game.setScreen(new SelectLevel(game));
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
    }

    /**
     * Saves current score and unlocks recipe for this level.
     *
     * If current score is top1-4, items below it will be moved down.
     */
    private void saveScore() {
        int old1 = game.prefs.getInteger("highscore");
        int old2 = game.prefs.getInteger("score2");
        int old3 = game.prefs.getInteger("score3");
        int old4 = game.prefs.getInteger("score4");

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

        if (completed) {
            game.prefs.putBoolean(level, true); // unlocks recipe
        }

        game.prefs.flush();
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