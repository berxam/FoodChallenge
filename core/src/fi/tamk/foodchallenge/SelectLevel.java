package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private Rectangle level5;
    private Rectangle level6;
    private Rectangle level7;
    private Rectangle level8;
    private Rectangle level9;
    private Rectangle level10;
    private Rectangle level11;
    private Rectangle level12;
    private Rectangle level13;
    private Rectangle level14;
    private Rectangle level15;
    private Rectangle level16;
    private Rectangle level17;
    private Rectangle level18;
    private Rectangle level19;
    private Rectangle level20;
    private Rectangle level21;
    private Rectangle level22;
    private Rectangle level23;
    private Rectangle level24;
    private Rectangle level25;
    private Rectangle level26;
    private Rectangle level27;
    private Rectangle level28;

    private float topRow = 600f;
    private float secondRow = 510f;
    private float thirdRow = 420f;
    private float fourthRow = 330f;
    private float fifthRow = 240f;
    private float sixthRow = 150f;
    private float seventhRow = 60f;
    private float col1 = 20f;
    private float col2 = 115f;
    private float col3 = 210f;
    private float col4 = 305f;

    private OrthographicCamera camera;

    SelectLevel(FoodChallenge game) {
        this.game = game;

        background = new Texture("LevelSelect.png");

        level1 = new Rectangle(col1, topRow, 75f, 75f);
        level2 = new Rectangle(col2, topRow, 75f, 75f);
        level3 = new Rectangle(col3, topRow, 75f, 75f);
        level4 = new Rectangle(col4, topRow, 75f, 75f);
        level5 = new Rectangle(col1, secondRow, 75f, 75f);
        level6 = new Rectangle(col2, secondRow, 75f, 75f);
        level7 = new Rectangle(col3, secondRow, 75f, 75f);
        level8 = new Rectangle(col4, secondRow, 75f, 75f);
        level9 = new Rectangle(col1, thirdRow, 75f, 75f);
        level10 = new Rectangle(col2, thirdRow, 75f, 75f);
        level11 = new Rectangle(col3, thirdRow, 75f, 75f);
        level12 = new Rectangle(col4, thirdRow, 75f, 75f);
        level13 = new Rectangle(col1, fourthRow, 75f, 75f);
        level14 = new Rectangle(col2, fourthRow, 75f, 75f);
        level15 = new Rectangle(col3, fourthRow, 75f, 75f);
        level16 = new Rectangle(col4, fourthRow, 75f, 75f);
        level17 = new Rectangle(col1, fifthRow, 75f, 75f);
        level18 = new Rectangle(col2, fifthRow, 75f, 75f);
        level19 = new Rectangle(col3, fifthRow, 75f, 75f);
        level20 = new Rectangle(col4, fifthRow, 75f, 75f);
        level21 = new Rectangle(col1, sixthRow, 75f, 75f);
        level22 = new Rectangle(col2, sixthRow, 75f, 75f);
        level23 = new Rectangle(col3, sixthRow, 75f, 75f);
        level24 = new Rectangle(col4, sixthRow, 75f, 75f);
        level25 = new Rectangle(col1, seventhRow, 75f, 75f);
        level26 = new Rectangle(col2, seventhRow, 75f, 75f);
        level27= new Rectangle(col3, seventhRow, 75f, 75f);
        level28 = new Rectangle(col4, seventhRow, 75f, 75f);
        //level6 = new Rectangle(col4, secondRow, 75f, 75f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);
    }

    @Override
    public void show() {
        Gdx.input.setCatchBackKey(true);

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
        game.bitmapFont.draw(game.batch, game.myBundle.get("select"), 50f, 725f);
        game.batch.end();
    }

    private void checkPresses() {
        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (level1.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_1_150.tmx", 7500f));
                dispose();
            }

            if (level2.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_2_150.tmx", 7500f));
                dispose();
            }
            if (level3.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_3_150.tmx", 7500f));
                dispose();
            }
            if (level4.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_4_180.tmx", 8700f));
                dispose();
            }
            if (level5.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_5_150.tmx", 7500f));
                dispose();
            }
            if (level6.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_6_135.tmx", 7500f));
                dispose();
            }
            if (level7.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_7_150.tmx", 7500f));
                dispose();
            }
            if (level8.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_8_170.tmx", 8300f));
                dispose();
            }
            if (level9.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_9_200.tmx", 9800f));
                dispose();
            }
            if (level10.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_10_180.tmx", 8800f));
                dispose();
            }
            if (level11.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_11_150.tmx", 7500f));
                dispose();
            }
            if (level12.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_12_120.tmx", 5900f));
                dispose();
            }
            if (level13.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_13_180.tmx", 8800f));
                dispose();
            }
            if (level14.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_14_150.tmx", 7500f));
                dispose();
            }
            if (level15.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_15_150.tmx", 7500f));
                dispose();
            }
            if (level16.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_16_150.tmx", 7500f));
                dispose();
            }
            if (level17.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_17_160.tmx", 7800f));
                dispose();
            }
            if (level18.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_18_150.tmx", 7500f));
                dispose();
            }
            if (level19.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_19_150.tmx", 7500f));
                dispose();
            }
            if (level20.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_20_150.tmx", 7500f));
                dispose();
            }
            if (level21.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_21_150.tmx", 7500f));
                dispose();
            }
            if (level22.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_22_150.tmx", 7500f));
                dispose();
            }
            if (level23.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_23_150.tmx", 7500f));
                dispose();
            }
            if (level24.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_24_150.tmx", 7500f));
                dispose();
            }
            if (level25.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_25_150.tmx", 7500f));
                dispose();
            }
            if (level26.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_26_150.tmx", 7500f));
                dispose();
            }
            if (level27.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_27_100.tmx", 5000f));
                dispose();
            }
            if (level28.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game, "map_28_250.tmx", 12200f));
                dispose();
            }


        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new MenuScreen(game));
            dispose();
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

