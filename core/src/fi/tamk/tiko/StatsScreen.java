package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class StatsScreen implements Screen {
    FoodChallenge game;

    private Texture background;
    private Rectangle backButton;

    private int highScore;
    private int second;
    private int third;
    private int fourth;
    private int fifth;

    private OrthographicCamera camera;

    StatsScreen(FoodChallenge game) {
        this.game = game;

        background = new Texture("StatisticsScreen.png");
        backButton = new Rectangle(0, 0, 400f, 800f);

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

    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void updateCamera() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    private void drawEverything() {
        game.batch.begin();
        game.batch.draw(background, 0, 0, background.getWidth(), background.getHeight());
        game.bitmapFont.draw(game.batch, "TOP5 SCORES", 50f, 600f);
        game.bitmapFont.draw(game.batch, "1: " + highScore, 50f, 560f);
        game.bitmapFont.draw(game.batch, "2: " + second, 50f, 520f);
        game.bitmapFont.draw(game.batch, "3: " + third, 50f, 480f);
        game.bitmapFont.draw(game.batch, "4: " + fourth, 50f, 440f);
        game.bitmapFont.draw(game.batch, "5: " + fifth, 50f, 400f);
        game.batch.end();
    }

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
