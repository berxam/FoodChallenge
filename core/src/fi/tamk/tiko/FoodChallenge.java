package fi.tamk.tiko;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class FoodChallenge extends Game {
	SpriteBatch batch;
	Texture img;
	Rectangle playerRectangle;
	OrthographicCamera camera;

    public SpriteBatch getBatch() {
        return batch;
    }
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		MenuScreen menuscreen = new MenuScreen(this);
		setScreen(menuscreen);

		img = new Texture("SlimPlayerPH.png");
		playerRectangle = new com.badlogic.gdx.math.Rectangle(1,1,
				1.5f, 1f);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 10, 5);

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(img, playerRectangle.x, playerRectangle.y, playerRectangle.getWidth(),
				playerRectangle.getHeight());


		if(Gdx.input.isTouched()) {
			int realX = Gdx.input.getX();
			int realY = Gdx.input.getY();

			Vector3 touchPos = new Vector3(realX, realY, 0);

			camera.unproject(touchPos);


			playerRectangle.x = touchPos.x;
			playerRectangle.y = touchPos.y;



			//System.out.println("x = " + touchPos.x);
			//System.out.println("y = " + touchPos.y);

		}

		batch.end();




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
}
