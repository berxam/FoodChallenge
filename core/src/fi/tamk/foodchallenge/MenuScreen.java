package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen implements Screen {

    /**
     * Main class of the package, extends Game.
     */
    private FoodChallenge game;

    /**
     * Background picture.
     */
    private Texture background;

    /**
     * Play button.
     */
    private Rectangle play;

    /**
     * Recipes button.
     */
    private Rectangle recipes;

    /**
     * Statistics button.
     */
    private Rectangle stats;

    /**
     * Settings button.
     */
    private Rectangle settings;

    /**
     * Orthographic camera.
     */
    private OrthographicCamera camera;

    /**
     * Creates background, buttons and camera.
     *
     * @param game  Current instance of the game.
     */
    MenuScreen (FoodChallenge game) {
        this.game = game;

        background = new Texture("MainMenu.png");

        play = new Rectangle(45f, 476f, 303f, 62f);
        recipes = new Rectangle(45f, 378f, 303f, 62f);
        stats = new Rectangle(45f, 280f, 303f, 62f);
        settings = new Rectangle(45f, 180f, 303f, 62f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        updateCamera();
        drawEverything();
        checkPresses();
    }

    /**
     * Clears screen.
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Updates camera.
     */
    private void updateCamera() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Draws background and fonts on buttons.
     */
    private void drawEverything() {
        game.batch.begin();
        game.batch.draw(background, 0, 0, background.getWidth(), background.getHeight());
        game.bitmapFont.draw(game.batch, game.myBundle.get("play"), 80f, 530f);
        game.bitmapFont.draw(game.batch, game.myBundle.get("recipes"), 80f, 425f);
        game.bitmapFont.draw(game.batch, game.myBundle.get("stats"), 80f, 323f);
        game.bitmapFont.draw(game.batch, game.myBundle.get("settings"), 80f, 224f);
        game.batch.end();
    }

    /**
     * Checks if menu buttons are pressed and changes screen accordingly.
     */
    private void checkPresses() {
        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (play.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new SelectLevel(game));
                dispose();
            } else if (recipes.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new RecipeScreen(game));
                dispose();
            } else if (stats.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new StatsScreen(game));
                dispose();
            } else if (settings.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new SettingsScreen(game));
                dispose();
            }
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
