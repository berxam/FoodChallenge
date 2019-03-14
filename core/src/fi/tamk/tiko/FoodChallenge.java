package fi.tamk.tiko;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FoodChallenge extends Game {
	SpriteBatch batch;
	Texture img;
	Rectangle playerRectangle;
	OrthographicCamera camera;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		MenuScreen mainmenu = new MenuScreen(this);
        setScreen(mainmenu);

		img = new Texture("SlimPlayerPH.png");
		playerRectangle = new Rectangle(1,1, 1.5f, 1f);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10, 5);
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
		img.dispose();
	}

    public SpriteBatch getBatch() {
        return batch;
    }
}
