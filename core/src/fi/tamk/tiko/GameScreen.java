package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

    private Player player;
    private Texture slimPlayer;
    private Texture background;
    private Texture burger;
    private Texture carrot;
    private OrthographicCamera camera;
    private float scrollSpeed = 1f; // How fast does the screen scroll?
    TiledMap tiledmap;
    TiledMapRenderer tiledMapRenderer;
    int HP = 100;

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
        // background = new Texture("tempbackground.jpg");
        tiledmap = new TmxMapLoader().load("kartta1.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledmap);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        renderTiledMap();
        moveCamera();
        drawEverything();
        checkCollisions();
        movePlayer();
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
     * Draws textures.
     */
    public void drawEverything() {
        game.batch.begin();
        // game.batch.draw(background, 0, 0, 800f, 800f);
        game.batch.draw(player.playerTexture,
                player.getPlayerX(),player.getPlayerY(),
                player.getPlayerRectangle().getWidth(),
                player.getPlayerRectangle().getHeight());
        game.batch.end();
    }

    public void checkCollisions() {
        MapLayer burgerObjectLayer = tiledmap.getLayers().get("ObjectLayer");
        MapLayer carrotObjectLayer = tiledmap.getLayers().get("ObjectLayer2");
        MapObjects mapObjects = burgerObjectLayer.getObjects();
        MapObjects mapObjects1 = carrotObjectLayer.getObjects();
        Array<RectangleMapObject> burgerObjects = mapObjects.getByType(RectangleMapObject.class);
        Array<RectangleMapObject> carrotObjects = mapObjects1.getByType(RectangleMapObject.class);
        for (RectangleMapObject rectangleObject : burgerObjects) {
            Rectangle burgerRectangle = rectangleObject.getRectangle();

           // player.playerRectangle.getBoundingRectangle()

            if (player.playerRectangle.getBoundingRectangle().overlaps(burgerRectangle)) {
                HP -= 1;
                System.out.println(HP);
                burgerObjectLayer.getObjects().remove(rectangleObject);
                clearIt(burgerRectangle.getX(),burgerRectangle.getY());

            }
        }
        for (RectangleMapObject rectangleObject : carrotObjects) {
            Rectangle carrotRectangle = rectangleObject.getRectangle();

            // player.playerRectangle.getBoundingRectangle()

            if (player.playerRectangle.getBoundingRectangle().overlaps(carrotRectangle)) {
                HP +=1;
                System.out.println(HP);
                carrotObjectLayer.getObjects().remove(rectangleObject);
                clearIt(carrotRectangle.getX(), carrotRectangle.getY());

            }
        }

    }

    public void clearIt (float xCoord, float yCoord) {
        int indexX = (int) xCoord / 32;
        int indexY = (int) yCoord / 32;

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

            player.setPlayerX(touchPos.x ); // Positions the player
            player.setPlayerY(touchPos.y ); // just above the finger.
        } else {
            player.setPlayerY(player.getPlayerY()+scrollSpeed); // Lets the player fall down.
        }
    }

    @Override
    public void show() {

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
