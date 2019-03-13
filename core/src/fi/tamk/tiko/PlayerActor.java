package fi.tamk.tiko;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerActor extends Actor {
    Texture texture;



    public PlayerActor() {
        texture= new Texture("SlimPlayerPH.png");
        setWidth(texture.getWidth() / 250f);
        setHeight(texture.getHeight()/ 250f);
        setBounds(0, 0, getWidth(), getHeight());
    }
}
