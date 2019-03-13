package fi.tamk.tiko;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    SpriteBatch batch;
    Texture burger;
    Texture carrot;
    Texture slimPlayer;
    OrthographicCamera camera;
    Stage stage;
    PlayerActor PlayerActor;



    GameScreen(FoodChallenge game) {
        stage = new Stage(new FitViewport(4.8f, 8), batch);
        batch = game.getBatch();
      //  carrot = new Texture("Carrot.png");
       // slimPlayer = new Texture("SlimPlayerPH.png");
      //  burger = new Texture("Burger.png");
        stage.addActor(PlayerActor);






    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
