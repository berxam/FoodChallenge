package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;

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
    private float bottomCounter = 0f; // How long is the player at the bottom?
    private float scrollSpeed = 2f; // How fast does the screen scroll?
    TiledMap tiledmap;
    TiledMapRenderer tiledMapRenderer;

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
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledmap);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        drawEverything();
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
     * Draws textures.
     */
    public void drawEverything() {
        game.batch.begin();
        // game.batch.draw(background, 0, 0, 800f, 800f);
        game.batch.draw(player.playerTexture,
                player.getPlayerX(),player.playerY,
                player.getPlayerRectangle().getWidth(),
                player.getPlayerRectangle().getHeight());
        game.batch.end();
    }

    /**
     * Moves player according to input.
     *
     * If no input is given, lets it move freely, but stops
     * when hitting the bottom of the screen (1px padding).
     */
    public void movePlayer() {
        if (Gdx.input.isTouched()) {
            int realX = Gdx.input.getX();
            int realY = Gdx.input.getY();

            Vector3 touchPos = new Vector3(realX, realY, 0);
            camera.unproject(touchPos);

            player.playerX = touchPos.x - 30f; // Positions the player
            player.playerY = touchPos.y + 30f; // just above the finger.

            bottomCounter = 0;
        } else {
            if (player.playerY > 2f) {
                player.playerY -= scrollSpeed; // Lets the player fall down.
            } else {
                bottomCounter += 1; // Counts time while in the bottom.

                if (bottomCounter == 100) {
                    System.out.println("KESKITY ETTÄ SAAT HETELMIÄ");
                } else if (bottomCounter == 200) {
                    System.out.println("HEI PLIIS YRITÄ EDES");
                }
            }
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
