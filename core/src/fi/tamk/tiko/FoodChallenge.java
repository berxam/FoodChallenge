package fi.tamk.tiko;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FoodChallenge extends Game {
	SpriteBatch batch;
	Preferences prefs;

    FreeTypeFontGenerator freeTypeFontGenerator;
    BitmapFont bitmapFont;

	@Override
	public void create () {
		batch = new SpriteBatch();
		prefs = Gdx.app.getPreferences("myPrefs");

        freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("ostrich-regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        bitmapFont = freeTypeFontGenerator.generateFont(parameter);

		setScreen(new MenuScreen(this));
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
	    super.render();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		batch.dispose();
	}

    public SpriteBatch getBatch() {
        return batch;
    }
}
