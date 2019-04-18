package fi.tamk.foodchallenge;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player  {
    Texture playerTexture;
    Texture animationTexture;
    Animation<TextureRegion> walkAnimation;
    TextureRegion currentFrameTexture;
    Sprite playerRectangle;
    float playerX;
    float playerY;
    float statetime;
    Rectangle playerControlRectangle;


    Player() {
        playerTexture = new Texture("SlimPlayerCR.png");
        animationTexture = new Texture("WalkingAnimation.png");
        playerX = 180f;
        playerY = 50f;

        createWalkAnimation();


        playerRectangle = new Sprite(currentFrameTexture,0,0,
                animationTexture.getWidth() / 8, animationTexture.getHeight() / 4);
        setPlayerX(playerX);
        setPlayerY(playerY);

        playerControlRectangle = new Rectangle(getPlayerX(),getPlayerY(),200,200);
    }

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayer(Texture playerTexture) {
        playerTexture = playerTexture;
    }


    public Sprite getPlayerRectangle() {
        return playerRectangle;
    }

    public void setPlayerRectangle(Rectangle playerRectangle) {
        playerRectangle = playerRectangle;

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
        playerY = y;
        playerRectangle.setY(y);
    }

    public void createWalkAnimation() {
        final int FRAME_COLS = 4;
        final int FRAME_ROWS = 2;

        int tileWidth = animationTexture.getWidth() / FRAME_COLS;
        int tileHeigth = animationTexture.getHeight() / FRAME_ROWS;
        TextureRegion[][] tmp = TextureRegion.split(animationTexture,tileWidth,tileHeigth);
        TextureRegion[] allFrames = toTextureArray(tmp,FRAME_COLS,FRAME_ROWS);
        walkAnimation = new Animation<TextureRegion>(4 / 60f, allFrames);
        currentFrameTexture = walkAnimation.getKeyFrame(statetime, true);
    }

    public static TextureRegion[] toTextureArray ( TextureRegion [][]tr, int cols, int rows ) {
        TextureRegion [] frames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j< cols; j++) {
                frames[index++] = tr[i][j];
            }
        }
        return frames;
    }

    public void walk() {
        statetime += Gdx.graphics.getDeltaTime();
        currentFrameTexture = walkAnimation.getKeyFrame(statetime, true);
    }




}
