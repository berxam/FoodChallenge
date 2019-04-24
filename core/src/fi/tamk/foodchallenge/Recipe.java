package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Recipe implements Screen {
    FoodChallenge game;

    private Texture recipeImage;
    private Texture background;

    private OrthographicCamera camera;

    Recipe(FoodChallenge game,String recipe) {
        this.game = game;

        background = new Texture("LevelSelect.png");
        recipeImage = new Texture(recipe);
        //backButton = new Rectangle(60, 600, 60f, 20f);



        camera = new OrthographicCamera();
        camera.setToOrtho(false, 400, 800);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        updateCamera();
        drawEverything();
       // checkPresses();
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

        game.batch.draw(recipeImage,0,0);
        //game.bitmapFont.draw(game.batch, "RECIPES", 125f, 725f);

        //if (game.prefs.getBoolean("map2.tmx", false)) {
            //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
       // }

        //if (game.prefs.getBoolean("map3.tmx", false)) {
            // game.bitmapFont.draw(game.batch, "2nd recipe link", 60f, 550f);
       // }

        game.batch.end();
    }

    /**
     * Checks if screen is pressed and goes to menu if so.
     *
     * Recipe buttons will be added here and clicking them
     * will take the player to view the recipe.
     */

    /*
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
                    game.batch.begin();

                    game.batch.draw(resepti1,0,0);
                    game.batch.end();

                    dispose();
                }
            }

            if (level2.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map3.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    Gdx.net.openURI("https://www.k-ruoka.fi/reseptit/feta-parsapasteijat");
                    dispose();
                }
            }
            if (level3.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map1_135.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    Gdx.net.openURI("https://www.k-ruoka.fi/reseptit/feta-parsapasteijat");
                    dispose();
                }
            }
            if (level4.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map2_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    Gdx.net.openURI("https://www.k-ruoka.fi/reseptit/feta-parsapasteijat");
                    dispose();
                }
            }
            if (level5.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map3_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    Gdx.net.openURI("https://www.k-ruoka.fi/reseptit/feta-parsapasteijat");
                    dispose();
                }
            }
            if (level6.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map4_180.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);

                    Gdx.net.openURI("https://www.k-ruoka.fi/reseptit/feta-parsapasteijat");
                    dispose();
                }
            }
        }

    }
    */

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
        recipeImage.dispose();

    }
}
