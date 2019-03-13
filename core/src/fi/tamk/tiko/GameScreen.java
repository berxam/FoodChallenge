package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameScreen implements Screen {
    SpriteBatch batch;
    FoodChallenge game;
    Texture burger;
    Texture carrot;
    Texture slimPlayer;
    OrthographicCamera camera;
    Player player;
   // Stage stage;
   // PlayerActor PlayerActor;



    GameScreen(FoodChallenge game) {
        this.game = game;
        batch = game.getBatch();
       // stage = new Stage(new FitViewport(4.8f, 8), batch);
       // Gdx.input.setInputProcessor(stage);
       // PlayerActor = new PlayerActor();
      //  carrot = new Texture("Carrot.png");
       // slimPlayer = new Texture("SlimPlayerPH.png");
      //  burger = new Texture("Burger.png");
       // stage.addActor(PlayerActor);
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 400, 800);
        player = new Player();






    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
       // stage.act(Gdx.graphics.getDeltaTime());
       // stage.draw();
      //  camera = new OrthographicCamera();
       // camera.setToOrtho(false, 4.8f, 8);

        batch.begin();

        batch.draw(player.playerTexture,player.getPlayerX(),player.playerY,
                player.getPlayerRectangle().getWidth(),player.getPlayerRectangle().getHeight());

      //  batch.setProjectionMatrix(camera.combined);



        batch.end();

        if (Gdx.input.isTouched()) {
            int realX = Gdx.input.getX();
            int realY = Gdx.input.getY();

            Vector3 touchPos = new Vector3(realX, realY, 0);

            camera.unproject(touchPos);

            player.playerX = realX;
            player.playerY = realY;

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
