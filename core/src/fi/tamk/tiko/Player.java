package fi.tamk.tiko;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    Texture playerTexture;
    Rectangle playerRectangle;
    float playerX;
    float playerY;


    Player() {
        playerTexture = new Texture("SlimPlayerPH.png");
        playerX = 0f;
        playerY = 0f;

        playerRectangle = new Rectangle(0,0,
                playerTexture.getWidth() ,playerTexture.getHeight());
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayer(Texture playerTexture) {
        this.playerTexture = playerTexture;
    }


    public Rectangle getPlayerRectangle() {
        return playerRectangle;
    }

    public void setPlayerRectangle(Rectangle playerRectangle) {
        this.playerRectangle = playerRectangle;

    }

    public float getPlayerX() {
        return playerX;
    }

    public void setPlayerX(float x) {
        playerX = x;
        playerRectangle.x = x;
    }

    public float getPlayerY() {
        return playerY;
    }

    public void setPlayerY(float y) {
        playerX = y;
        playerRectangle.y = y;
    }


}
