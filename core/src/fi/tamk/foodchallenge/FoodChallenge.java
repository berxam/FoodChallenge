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

/**
 * Creates the game and goes to main menu.
 *
 * Acts as main class and is called 'game' in other classes.
 */
public class FoodChallenge extends Game {
	SpriteBatch batch;
	Preferences prefs;

	FreeTypeFontGenerator freeTypeFontGenerator;
	BitmapFont bitmapFont;

	Locale locale;
	I18NBundle myBundle;

	@Override
	public void create () {
		// Create batch and preferences.
		batch = new SpriteBatch();
		prefs = Gdx.app.getPreferences("myPrefs");

		// Create font and style it.
		freeTypeFontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("HVD_Comic_Serif_Pro.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 36;
		parameter.borderColor = Color.BLACK;
		parameter.color = Color.GREEN;
		parameter.borderWidth = 2;
		bitmapFont = freeTypeFontGenerator.generateFont(parameter);

		// Set default settings.
		setBundle(Locale.getDefault());
		prefs.putString("language", "fin");
		prefs.putBoolean("musicOn",true);
		prefs.putBoolean("effexOn", true);

		// Go to main menu.
		setScreen(new MenuScreen(this));
	}

	/**
	 * Sets language. Controlled from SettingsScreen.
	 *
	 * @param lang    Language to be set.
	 */
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
}
