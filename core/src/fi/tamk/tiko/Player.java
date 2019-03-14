package fi.tamk.tiko;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player  {
    Texture playerTexture;
    Sprite playerRectangle;
    float playerX;
    float playerY;


    Player() {
        playerTexture = new Texture("SlimPlayerPH.png");
        playerX = 0f;
        playerY = 0f;

        playerRectangle = new Sprite(playerTexture,0,0,
                playerTexture.getWidth() / 2 ,playerTexture.getHeight() /2);
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayer(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }


    public Sprite getPlayerRectangle() {
        return playerRectangle;
    }

    public void setPlayerRectangle(Sprite playerRectangle) {
        this.playerRectangle = playerRectangle;

    }

    public float getPlayerX() {
        return playerX;
    }

    public void setPlayerX(float x) {
        playerX = x;
        playerRectangle.setX(x);
    }

    public float getPlayerY() {
        return playerY;
    }

    public void setPlayerY(float y) {
        playerX = y;
        playerRectangle.setY(y);
    }


}
