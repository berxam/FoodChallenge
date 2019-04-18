package fi.tamk.foodchallenge;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.Locale;

public class FoodChallenge extends Game {
	SpriteBatch batch;
	Preferences prefs;

    FreeTypeFontGenerator freeTypeFontGenerator;
    BitmapFont bitmapFont;

	Locale locale;
	I18NBundle myBundle;

	@Override
	public void create () {
		batch = new SpriteBatch();
		prefs = Gdx.app.getPreferences("myPrefs");

        freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("HVD_Comic_Serif_Pro.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 36;
        parameter.borderColor = Color.BLACK;
        parameter.color = Color.GREEN;
        parameter.borderWidth = 2;
        bitmapFont = freeTypeFontGenerator.generateFont(parameter);

		setBundle(Locale.getDefault());

		setScreen(new MenuScreen(this));
	}

	public void setBundle(Locale lang) {
		locale = lang;
		myBundle = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale);
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
