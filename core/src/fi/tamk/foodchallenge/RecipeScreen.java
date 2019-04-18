package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class RecipeScreen implements Screen {
    FoodChallenge game;

    private Texture resepti1;
    private Texture resepti2;
    private Texture resepti3;
    private Texture resepti4;
    private Texture resepti5;
    private Texture background;
    private Rectangle backButton;
    private Rectangle level1;
    private Rectangle level2;
    private Rectangle level3;
    private Rectangle level4;
    private Rectangle level5;
    private Rectangle level6;

    private float topRow = 600f;
    private float secondRow = 510f;
    private float col1 = 20f;
    private float col2 = 115f;
    private float col3 = 210f;
    private float col4 = 305f;

    private OrthographicCamera camera;

    RecipeScreen(FoodChallenge game) {
        this.game = game;

        background = new Texture("LevelSelect.png");
        resepti1 = new Texture("resepti1.png");
        resepti2 = new Texture("resepti2.png");
        resepti3 = new Texture("resepti3.png");
        resepti4 = new Texture("resepti4.png");
        //backButton = new Rectangle(60, 600, 60f, 20f);
        level1 = new Rectangle(col1, topRow, 75f, 75f);
        level2 = new Rectangle(col2, topRow, 75f, 75f);
        level3 = new Rectangle(col3, topRow, 75f, 75f);
        level4 = new Rectangle(col4, topRow, 75f, 75f);
        level5 = new Rectangle(col1, secondRow, 75f, 75f);
        level6 = new Rectangle(col2, secondRow, 75f, 75f);


        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);
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

    /**
     * Clears the screen.
     */
    private void clearScreen() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Updates camera and sets batch projection matrix.
     */
    private void updateCamera() {
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
    }

    /**
     * Draws placeholder "buttons" aka strings if they're unlocked.
     */
    private void drawEverything() {
        game.batch.begin();
        game.batch.draw(background, 0, 0, background.getWidth(), background.getHeight());
        game.bitmapFont.draw(game.batch, game.myBundle.get("recipes"), 50f, 725f);

        if (game.prefs.getBoolean("map2.tmx", false)) {
            //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
        }

        if (game.prefs.getBoolean("map3.tmx", false)) {
           // game.bitmapFont.draw(game.batch, "2nd recipe link", 60f, 550f);
        }

        game.batch.end();
    }

    /**
     * Checks if screen is pressed and goes to menu if so.
     *
     * Recipe buttons will be added here and clicking them
     * will take the player to view the recipe.
     */
    private void checkPresses() {
        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            //if (backButton.contains(touchPos.x, touchPos.y)) {
             //   Gdx.net.openURI("https://www.k-ruoka.fi/reseptit/feta-parsapasteijat");
            //    dispose();
           // }
            if (level1.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map2.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti1.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti2.png"));
                    }


                  //  game.batch.draw(resepti1,0,0);
                    //game.batch.end();

                  //  dispose();
                }
            }

            if (level2.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map3.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    game.setScreen(new Recipe(game,"resepti2.png"));
                    dispose();
                }
            }
            if (level3.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map1_135.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    game.setScreen(new Recipe(game,"resepti3.png"));
                    dispose();
                }
            }
            if (level4.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map2_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    game.setScreen(new Recipe(game,"resepti4.png"));
                    dispose();
                }
            }
            if (level5.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map4_180.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    game.setScreen(new Recipe(game,"resepti5.png"));
                    dispose();
                }
            }
            if (level6.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_5_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    game.setScreen(new Recipe(game,"resepti2.png"));
                    dispose();
                }
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
