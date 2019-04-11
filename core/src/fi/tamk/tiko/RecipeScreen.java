package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class RecipeScreen implements Screen {
    FoodChallenge game;

    private Texture background;
    private Rectangle backButton;

    private OrthographicCamera camera;

    RecipeScreen(FoodChallenge game) {
        this.game = game;

        background = new Texture("StatisticsScreen.png");
        backButton = new Rectangle(0, 0, 400f, 800f);

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
     * Clears the screen.
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Updates camera and sets batch projection matrix.
     */
    private void updateCamera() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Draws placeholder "buttons" aka strings if they're unlocked.
     */
    private void drawEverything() {
        game.batch.begin();
        game.batch.draw(background, 0, 0, background.getWidth(), background.getHeight());
        game.bitmapFont.draw(game.batch, "RECIPES", 150f, 725f);

        if (game.prefs.getBoolean("map2.tmx", false)) {
            game.bitmapFont.draw(game.batch, "1st recipe link", 100f, 600f);
        }

        if (game.prefs.getBoolean("map3.tmx", false)) {
            game.bitmapFont.draw(game.batch, "2nd recipe link", 100f, 550f);
        }

        game.batch.end();
    }

    /**
     * Checks if screen is pressed and goes to menu if so.
     *
     * Recipe buttons will be added here and clicking them
     * will take the player to view the recipe.
     */
    private void checkPresses() {
        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (backButton.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new MenuScreen(game));
                dispose();
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
