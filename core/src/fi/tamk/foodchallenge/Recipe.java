package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Recipe implements Screen {
    private FoodChallenge game;

    private Texture recipeImage;

    private OrthographicCamera camera;

    Recipe(FoodChallenge game, String recipe) {
        this.game = game;

        recipeImage = new Texture(recipe);

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
     * Draws recipe image.
     */
    private void drawEverything() {
        game.batch.begin();
        game.batch.draw(recipeImage,0,0);
        game.batch.end();
    }

    /**
     * Checks if system back button is pressed and goes to menu if so.
     */
    private void checkPresses() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new MenuScreen(game));
            dispose();
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
        recipeImage.dispose();
    }
}
