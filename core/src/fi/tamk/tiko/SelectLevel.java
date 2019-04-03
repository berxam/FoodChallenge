package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class SelectLevel implements Screen {
    FoodChallenge game;

    private Texture background;

    private Rectangle level1;
    private Rectangle level2;
    private Rectangle level3;
    private Rectangle level4;

    private float topRow = 600f;
    private float col1 = 20f;
    private float col2 = 115f;
    private float col3 = 210f;
    private float col4 = 305f;

    private OrthographicCamera camera;

    SelectLevel(FoodChallenge game) {
        this.game = game;

        background = new Texture("testi.png");

        level1 = new Rectangle(col1, topRow, 75f, 75f);
        level2 = new Rectangle(col2, topRow, 75f, 75f);
        level3 = new Rectangle(col3, topRow, 75f, 75f);
        level4 = new Rectangle(col4, topRow, 75f, 75f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);
    }

    @Override
    public void show() {

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
        game.batch.end();
    }

    private void checkPresses() {
        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (level1.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map2.tmx", 6900f));
                dispose();
            }

            if (level2.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map3.tmx", 6900f));
                dispose();
            }
        }
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

