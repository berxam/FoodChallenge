package fi.tamk.tiko;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FoodChallenge extends Game {
	SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

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
