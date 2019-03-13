package fi.tamk.tiko;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class MenuScreen extends ParentScreen {
    MenuScreen (FoodChallenge game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        checkPresses();
    }

    private void checkPresses() {
        if(Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (play.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new GameScreen(game));
                dispose();
            } else if (settings.contains(touchPos.x, touchPos.y)) {
                game.setScreen(new SettingsScreen(game));
                dispose();
            }
        }
    }
}
