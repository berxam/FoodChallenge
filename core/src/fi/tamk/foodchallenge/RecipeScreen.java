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
    private Texture resepti6;
    private Texture resepti7;
    private Texture resepti8;
    private Texture resepti9;
    private Texture resepti10;
    private Texture resepti11;
    private Texture resepti12;
    private Texture resepti13;
    private Texture resepti14;
    private Texture resepti15;
    private Texture resepti16;
    private Texture resepti17;
    private Texture resepti18;
    private Texture resepti19;
    private Texture resepti20;
    private Texture resepti21;
    private Texture resepti22;
    private Texture resepti23;
    private Texture resepti24;
    private Texture resepti25;
    private Texture resepti26;
    private Texture resepti27;
    private Texture resepti28;
    private Texture resepti1en;
    private Texture resepti2en;
    private Texture resepti3en;
    private Texture resepti4en;
    private Texture resepti5en;
    private Texture resepti6en;
    private Texture resepti7en;
    private Texture resepti8en;
    private Texture resepti9en;
    private Texture resepti10en;
    private Texture resepti11en;
    private Texture resepti12en;
    private Texture resepti13en;
    private Texture resepti14en;
    private Texture resepti15en;
    private Texture resepti16en;
    private Texture resepti17en;
    private Texture resepti18en;
    private Texture resepti19en;
    private Texture resepti20en;
    private Texture resepti21en;
    private Texture resepti22en;
    private Texture resepti23en;
    private Texture resepti24en;
    private Texture resepti25en;
    private Texture resepti26en;
    private Texture resepti27en;
    private Texture resepti28en;
    private Texture background;
    private Rectangle backButton;
    private Rectangle level1;
    private Rectangle level2;
    private Rectangle level3;
    private Rectangle level4;
    private Rectangle level5;
    private Rectangle level6;
    private Rectangle level7;
    private Rectangle level8;
    private Rectangle level9;
    private Rectangle level10;
    private Rectangle level11;
    private Rectangle level12;
    private Rectangle level13;
    private Rectangle level14;
    private Rectangle level15;
    private Rectangle level16;
    private Rectangle level17;
    private Rectangle level18;
    private Rectangle level19;
    private Rectangle level20;
    private Rectangle level21;
    private Rectangle level22;
    private Rectangle level23;
    private Rectangle level24;
    private Rectangle level25;
    private Rectangle level26;
    private Rectangle level27;
    private Rectangle level28;


    private float topRow = 600f;
    private float secondRow = 510f;
    private float thirdRow = 420f;
    private float fourthRow = 330f;
    private float fifthRow = 240f;
    private float sixthRow = 150f;
    private float seventhRow = 60f;
    private float col1 = 20f;
    private float col2 = 115f;
    private float col3 = 210f;
    private float col4 = 305f;

    private OrthographicCamera camera;

    RecipeScreen(FoodChallenge game) {
        this.game = game;

        background = new Texture("LevelSelect.png");
        resepti1 = new Texture("resepti1.png");
        resepti1en = new Texture("resepti1en.png");
        resepti2 = new Texture("resepti2.png");
        resepti2en = new Texture("resepti2en.png");
        resepti3 = new Texture("resepti3.png");
        resepti3en = new Texture("resepti3en.png");
        resepti4 = new Texture("resepti4.png");
        resepti4en = new Texture("resepti4en.png");
        resepti5 = new Texture("resepti5.png");
        resepti5en = new Texture("resepti5en.png");
        resepti6 = new Texture("resepti6.png");
        resepti7 = new Texture("resepti7.png");
        resepti8 = new Texture("resepti8.png");
        resepti9 = new Texture("resepti9.png");
        resepti10 = new Texture("resepti10.png");
        resepti11 = new Texture("resepti11.png");
        resepti12 = new Texture("resepti12.png");
        resepti13 = new Texture("resepti13.png");
        resepti14 = new Texture("resepti14.png");
        resepti15= new Texture("resepti15.png");
        resepti16= new Texture("resepti16.png");
        resepti17= new Texture("resepti17.png");
        resepti18= new Texture("resepti18.png");
        resepti19= new Texture("resepti19.png");
        resepti20= new Texture("resepti20.png");
        resepti21= new Texture("resepti21.png");
        resepti22= new Texture("resepti22.png");
        resepti23= new Texture("resepti23.png");
        resepti24= new Texture("resepti24.png");
        resepti25= new Texture("resepti25.png");
        resepti26= new Texture("resepti26.png");
        resepti27= new Texture("resepti27.png");
        resepti28= new Texture("resepti28.png");
        resepti6en = new Texture("resepti6en.png");
        resepti7en = new Texture("resepti7en.png");
        resepti8en = new Texture("resepti8en.png");
        resepti9en = new Texture("resepti9en.png");
        resepti10en = new Texture("resepti10en.png");
        resepti11en= new Texture("resepti11en.png");
        resepti12en = new Texture("resepti12en.png");
        resepti13en= new Texture("resepti13en.png");
        resepti14en = new Texture("resepti14en.png");
        resepti15en= new Texture("resepti15en.png");
        resepti16en= new Texture("resepti16en.png");
        resepti17en= new Texture("resepti17en.png");
        resepti18en= new Texture("resepti18en.png");
        resepti19en= new Texture("resepti19en.png");
        resepti20en= new Texture("resepti20en.png");
        resepti21en= new Texture("resepti21en.png");
        resepti22en= new Texture("resepti22en.png");
        resepti23en= new Texture("resepti23en.png");
        resepti24en= new Texture("resepti24en.png");
        resepti25en= new Texture("resepti25en.png");
        resepti26en= new Texture("resepti26en.png");
        resepti27en= new Texture("resepti27en.png");
        resepti28en= new Texture("resepti28en.png");



        //backButton = new Rectangle(60, 600, 60f, 20f);
        level1 = new Rectangle(col1, topRow, 75f, 75f);
        level2 = new Rectangle(col2, topRow, 75f, 75f);
        level3 = new Rectangle(col3, topRow, 75f, 75f);
        level4 = new Rectangle(col4, topRow, 75f, 75f);
        level5 = new Rectangle(col1, secondRow, 75f, 75f);
        level6 = new Rectangle(col2, secondRow, 75f, 75f);
        level7 = new Rectangle(col3, secondRow, 75f, 75f);
        level8 = new Rectangle(col4, secondRow, 75f, 75f);
        level9 = new Rectangle(col1, thirdRow, 75f, 75f);
        level10 = new Rectangle(col2, thirdRow, 75f, 75f);
        level11 = new Rectangle(col3, thirdRow, 75f, 75f);
        level12 = new Rectangle(col4, thirdRow, 75f, 75f);
        level13 = new Rectangle(col1, fourthRow, 75f, 75f);
        level14 = new Rectangle(col2, fourthRow, 75f, 75f);
        level15 = new Rectangle(col3, fourthRow, 75f, 75f);
        level16 = new Rectangle(col4, fourthRow, 75f, 75f);
        level17 = new Rectangle(col1, fifthRow, 75f, 75f);
        level18 = new Rectangle(col2, fifthRow, 75f, 75f);
        level19 = new Rectangle(col3, fifthRow, 75f, 75f);
        level20 = new Rectangle(col4, fifthRow, 75f, 75f);
        level21 = new Rectangle(col1, sixthRow, 75f, 75f);
        level22 = new Rectangle(col2, sixthRow, 75f, 75f);
        level23 = new Rectangle(col3, sixthRow, 75f, 75f);
        level24 = new Rectangle(col4, sixthRow, 75f, 75f);
        level25 = new Rectangle(col1, seventhRow, 75f, 75f);
        level26 = new Rectangle(col2, seventhRow, 75f, 75f);
        level27= new Rectangle(col3, seventhRow, 75f, 75f);
        level28 = new Rectangle(col4, seventhRow, 75f, 75f);



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
                if (game.prefs.getBoolean("map_1_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti1.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti1en.png"));
                    }
                }
            }
            if (level2.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_2_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti2.png"));
                        System.out.println("fin");
                    } else {
                        game.setScreen(new Recipe(game,"resepti2en.png"));
                        System.out.println("eng");
                    }
                }
            }
            if (level3.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_3_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti3.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti3en.png"));
                    }
                }
            }
            if (level4.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_4_180.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti4.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti4en.png"));
                    }
                }
            }
            if (level5.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_5_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti5.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti5en.png"));
                    }
                }
            }
            if (level6.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_6_135.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti6.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti6en.png"));
                    }
                }
            }
            if (level7.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_7_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti7.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti7en.png"));
                    }
                }
            }
            if (level8.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_8_170.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti8.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti8en.png"));
                    }
                }
            }
            if (level9.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_9_200.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti9.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti9en.png"));
                    }
                }
            }
            if (level10.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_10_180.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti10.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti10en.png"));
                    }
                }
            }
            if (level11.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_11_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti11.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti11en.png"));
                    }
                }
            }
            if (level12.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_12_120.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti12.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti12en.png"));
                    }
                }
            }
            if (level13.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_13_180.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti13.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti13en.png"));
                    }
                }
            }
            if (level14.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_14_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti14.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti14en.png"));
                    }
                }
            }
            if (level15.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_15_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti15.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti15en.png"));
                    }
                }
            }
            if (level16.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_16_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti16.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti16en.png"));
                    }
                }
            }
            if (level17.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_17_160.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti17.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti17en.png"));
                    }
                }
            }
            if (level18.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_18_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti18.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti18en.png"));
                    }
                }
            }
            if (level19.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_19_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti19.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti19en.png"));
                    }
                }
            }
            if (level20.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_20_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti20.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti20en.png"));
                    }
                }
            }
            if (level21.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_21_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti21.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti21en.png"));
                    }
                }
            }
            if (level22.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_22_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti22.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti22en.png"));
                    }
                }
            }
            if (level23.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_23_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti23.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti23en.png"));
                    }
                }
            }
            if (level24.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_24_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti24.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti24e.png"));
                    }
                }
            }
            if (level25.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_25_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti25.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti25en.png"));
                    }
                }
            }
            if (level26.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_26_150.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti26.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti26en.png"));
                    }
                }
            }
            if (level27.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_27_100.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti27.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti27en.png"));
                    }
                }
            }
            if (level28.contains(touchPos.x, touchPos.y)) {
                if (game.prefs.getBoolean("map_28_250.tmx", false)) {
                    //game.bitmapFont.draw(game.batch, "1st recipe link", 60f, 600f);
                    //game.batch.begin();
                    if (game.prefs.getString("language").equals("fin")) {
                        game.setScreen(new Recipe(game,"resepti28.png"));
                    } else {
                        game.setScreen(new Recipe(game,"resepti28en.png"));
                    }
                }
            }

            //////////

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
