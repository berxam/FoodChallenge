package fi.tamk.tiko;

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

    private TiledMap tiledmap;
    private TiledMapRenderer tiledMapRenderer;
    private MapLayer burgerLayer;
    private MapLayer carrotLayer;

    private float scrollSpeed = 4f; // How fast does the screen scroll?
    private float scorePosY;
    private float bannerPosY;
    private int HP = 100;

    /**
     * Creates camera, player and texture.
     *
     * @param game Don't even know what for but it must be there.
     */
    GameScreen(FoodChallenge game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);
        player = new Player();

        eatsound = Gdx.audio.newSound(Gdx.files.internal("eatsound.mp3"));
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("summerhit.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        tiledmap = new TmxMapLoader().load("map2.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledmap);
        burgerLayer = tiledmap.getLayers().get("ObjectLayer");
        carrotLayer = tiledmap.getLayers().get("ObjectLayer2");

        banner = new Texture("boringbanner.png");
        scorePosY = 785f;
        bannerPosY = 750f;
    }

    @Override
    public void render(float delta) {
        clearScreen();
        renderTiledMap();
        moveCamera();
        updateBannerPos();
        drawEverything();

        checkCollisions(burgerLayer);
        checkCollisions(carrotLayer);

        movePlayer();
        isGameOver();
    }

    /**
     * Clears the screen and sets projection matrix.
     */
    public void clearScreen() {
        Gdx.gl.glClearColor(1, 1, 0, 1);
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
        game.batch.draw(player.playerTexture,
                player.getPlayerX(),player.getPlayerY(),
                player.getPlayerRectangle().getWidth(),
                player.getPlayerRectangle().getHeight());
        game.batch.draw(banner, 0f, bannerPosY);
        game.bitmapFont.draw(game.batch, "HP: " + HP, 25f, scorePosY);
        game.batch.end();
    }

    /**
     * Checks if objects are being hit by the player.
     *
     * If so, adds score according to object type and calls clearIt().
     */
    public void checkCollisions(MapLayer objectLayer) {
        MapLayer layer = objectLayer;
        MapObjects mapObjects = layer.getObjects();
        Array<RectangleMapObject> mapObjectsArray = mapObjects.getByType(RectangleMapObject.class);

        for (RectangleMapObject rectangleObject : mapObjectsArray) {
            Rectangle objectRectangle = rectangleObject.getRectangle();

            if (player.playerRectangle.getBoundingRectangle().overlaps(objectRectangle)) {
                if (layer == burgerLayer) {
                    HP -= 1;
                    // animaatio pisteistä
                } else if (layer == carrotLayer) {
                    HP += 1;
                }

                System.out.println(HP);

                layer.getObjects().remove(rectangleObject);
                clearIt(objectRectangle.getX(),objectRectangle.getY());
                eatsound.play();
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
        //burgerObject.setCell(indexX,indexY,null );
    }

    /**
     * Moves player according to input.
     *
     * If no input is given, player scrolls with the camera.
     */
    public void movePlayer() {
        if (Gdx.input.isTouched()) {
            int realX = Gdx.input.getX();
            int realY = Gdx.input.getY();

            Vector3 touchPos = new Vector3(realX, realY, 0);
            camera.unproject(touchPos);

            player.setPlayerX(touchPos.x - 30f); // Positions the player
            player.setPlayerY(touchPos.y + 30f); // just above the finger.
        } else {
            player.setPlayerY(player.getPlayerY()+scrollSpeed); // Makes player move with camera.
        }
    }

    /**
     * Checks if player has reached end of map or hp and calls gameIsOver.
     */
    public void isGameOver() {
        if (player.getPlayerY() > 6200f || HP < 0) gameIsOver();

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) gameIsOver();
    }

    /**
     * Saves current score and goes to MenuScreen.
     */
    public void gameIsOver() {
        game.prefs.putInteger("highscore", HP);
        game.prefs.flush();
        game.setScreen(new MenuScreen(game));
        backgroundMusic.stop();
        dispose();
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