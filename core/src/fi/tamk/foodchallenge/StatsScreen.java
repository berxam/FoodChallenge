package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class StatsScreen implements Screen {
    private FoodChallenge game;

    private Texture background;

    private int highScore;
    private int second;
    private int third;
    private int fourth;
    private int fifth;

    private OrthographicCamera camera;

    StatsScreen(FoodChallenge game) {
        this.game = game;

        background = new Texture("Statistics.png");

        highScore = game.prefs.getInteger("highscore", 0);
        second = game.prefs.getInteger("score2", 0);
        third = game.prefs.getInteger("score3", 0);
        fourth = game.prefs.getInteger("score4", 0);
        fifth = game.prefs.getInteger("score5", 0);

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
     * Updates camera and sets projection matrix.
     */
    private void updateCamera() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Draws background image and statistics as fonts.
     */
    private void drawEverything() {
        game.batch.begin();
        game.batch.draw(background, 0, 0, background.getWidth(), background.getHeight());
        game.bitmapFont.draw(game.batch, game.myBundle.get("stats"), 50f, 725f);
        game.bitmapFont.draw(game.batch, "TOP5 " + game.myBundle.get("scores"), 50f, 600f);
        game.bitmapFont.draw(game.batch, "1: " + highScore, 50f, 560f);
        game.bitmapFont.draw(game.batch, "2: " + second, 50f, 520f);
        game.bitmapFont.draw(game.batch, "3: " + third, 50f, 480f);
        game.bitmapFont.draw(game.batch, "4: " + fourth, 50f, 440f);
        game.bitmapFont.draw(game.batch, "5: " + fifth, 50f, 400f);
        game.bitmapFont.draw(game.batch, game.myBundle.get("played"), 50f, 340f);
        game.bitmapFont.draw(game.batch, "" + game.prefs.getInteger("gamesPlayed", 0), 50f, 300f);
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

    }
}
