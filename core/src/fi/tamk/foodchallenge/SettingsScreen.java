package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Locale;

public class SettingsScreen implements Screen {
    FoodChallenge game;

    private Texture background;

    private Rectangle backButton;
    private Rectangle musicButton;
    private Rectangle effexButton;
    private Rectangle langButton;

    private OrthographicCamera camera;

    private String musicOnOff;
    private String sfxOnOff;
    private String language;

    SettingsScreen(FoodChallenge game) {
        this.game = game;

        background = new Texture("Settings.png");

        backButton = new Rectangle(0, 600, 400f, 180f);
        musicButton = new Rectangle(0, 520, 400f, 40f);
        effexButton = new Rectangle(0, 480, 400f, 40f);
        langButton = new Rectangle(0, 370, 400f, 80f);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);

        loadDefaults();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        updateCamera();
        drawEverything();
        checkPresses();
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }

    }

    private void loadDefaults() {
        if (game.prefs.getBoolean("musicOn", true)) {
            musicOnOff = "ON";
        } else {
            musicOnOff = "OFF";
        }

        if (game.prefs.getBoolean("effexOn", true)) {
            sfxOnOff = "ON";
        } else {
            sfxOnOff = "OFF";
        }

        language = game.myBundle.get("currentlang");
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
        game.bitmapFont.draw(game.batch, game.myBundle.get("settings"), 50f, 725f);
        game.bitmapFont.draw(game.batch, game.myBundle.get("music"), 50f, 560f);
        game.bitmapFont.draw(game.batch, musicOnOff, 300f, 560f);
        game.bitmapFont.draw(game.batch, game.myBundle.get("sfx"), 50f, 520f);
        game.bitmapFont.draw(game.batch, sfxOnOff, 300f, 520f);
        game.bitmapFont.draw(game.batch, game.myBundle.get("lng"), 50f, 450f);
        game.bitmapFont.draw(game.batch, language, 50f, 410f);
        game.batch.end();
    }

    private void checkPresses() {
        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (musicButton.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("musicOn")) {
                    musicOnOff = "OFF";
                    game.prefs.putBoolean("musicOn", false);
                } else {
                    musicOnOff = "ON";
                    game.prefs.putBoolean("musicOn", true);
                }

                game.prefs.flush();
            }

            if (effexButton.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("effexOn")) {
                    sfxOnOff = "OFF";
                    game.prefs.putBoolean("effexOn", false);
                } else {
                    sfxOnOff = "ON";
                    game.prefs.putBoolean("effexOn", true);
                }

                game.prefs.flush();
            }

            if (langButton.contains(touchPos.x, touchPos.y)) {
                if (language.equals("English")) {
                    language = "suomi";
                    game.prefs.putString("language", "fin");
                    game.setBundle(game.locale.getDefault());
                } else {
                    language = "English";
                    game.prefs.putString("language", "en");
                    game.setBundle(new Locale("en", "US"));
                }
            }

            if (backButton.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new MenuScreen(game));
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

