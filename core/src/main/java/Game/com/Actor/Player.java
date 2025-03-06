package Game.com.Actor;

import Game.com.Background.GroundScreen;
import Game.com.Master;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player extends MyActor {
    Animation<TextureRegion> animationLeft;
    Animation<TextureRegion> animationRight;
    Animation<TextureRegion> animationDie;
    Animation<TextureRegion> animationWin;

    float time;
    String lastDirectionHaiBen = "R";
    String direction = "R";

    Sound walking1;
    Sound walking2;
    float timeSound = 0;
    boolean isPlaying = false;
    boolean isAlive = true;
    public String itemUse = "hands";
    public float health = 100;

    public Player(float x, float y, Stage s) {
        super(x, y, s);
        Texture textureLeft = new Texture("playerleft.png");
        Texture textureRight = new Texture("playerright.png");
//        Texture textureWin = new Texture("man-win.png");
        int cot = 6;
        int hang = 1;
        TextureRegion[][] frameBuffLeft = TextureRegion.split(textureLeft, textureLeft.getWidth() / cot, textureLeft.getHeight() / hang);

        TextureRegion[][] frameBuffRight = TextureRegion.split(textureRight, textureRight.getWidth() / cot, textureRight.getHeight() / hang);
        TextureRegion[] framesLeft = new TextureRegion[cot * hang];
        int index = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                framesLeft[index++] = frameBuffLeft[i][j];
            }
        }

        TextureRegion[] framesRight = new TextureRegion[cot * hang];
        index = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                framesRight[index++] = frameBuffRight[i][j];
            }
        }

//        TextureRegion[][] frameBuffWin = TextureRegion.split(textureWin, textureWin.getWidth() / 3, textureWin.getHeight() / 1);
//        TextureRegion[] framesWin = new TextureRegion[3];
//        int indexWin = 0;
//        for (int i = 0; i < 1; i++) {
//            for (int j = 0; j < 3; j++) {
//                framesWin[indexWin++] = frameBuffWin[i][j];
//            }
//        }


//        TextureRegion[] framesDie = {frames[12], frames[13], frames[14], frames[15], frames[16], frames[17], frames[18]};

        animationLeft = new Animation<TextureRegion>(0.05f, framesLeft);
        animationRight = new Animation<TextureRegion>(0.05f, framesRight);
//        animationDie = new Animation<TextureRegion>(0.3f, framesDie);
//        animationWin = new Animation<TextureRegion>(0.3f, framesWin);

        animationLeft.setPlayMode(Animation.PlayMode.LOOP);
        animationRight.setPlayMode(Animation.PlayMode.LOOP);
//        animationWin.setPlayMode(Animation.PlayMode.LOOP);

        setSize(60, 150);

        time = 0;
        textureRegion = animationRight.getKeyFrame(time);

        walking1 = Gdx.audio.newSound(Gdx.files.internal("walking1.mp3"));
        walking2 = Gdx.audio.newSound(Gdx.files.internal("walking2.mp3"));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    lastDirectionHaiBen = "L";
                    direction = "L";
                    moveBy(-Master.MAN_SPEED, 0);
                    time += delta;
                    textureRegion = animationLeft.getKeyFrame(time);
                    playSoundWalking(1, delta);
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    direction = "R";
                    lastDirectionHaiBen = "R";
                    moveBy(Master.MAN_SPEED, 0);
                    time += delta;
                    textureRegion = animationRight.getKeyFrame(time);
                    playSoundWalking(1, delta);
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    direction = "D";
                    moveBy(0, -Master.MAN_SPEED);
                    time += delta;
                    if(lastDirectionHaiBen.equals("L")){
                        textureRegion = animationLeft.getKeyFrame(time);
                    }
                    if(lastDirectionHaiBen.equals("R")){
                        textureRegion = animationRight.getKeyFrame(time);
                    }
                    playSoundWalking(2, delta);
                } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    direction = "U";
                    moveBy(0, Master.MAN_SPEED);
                    time += delta;
                    if(lastDirectionHaiBen.equals("L")){
                        textureRegion = animationLeft.getKeyFrame(time);
                    }
                    if(lastDirectionHaiBen.equals("R")){
                        textureRegion = animationRight.getKeyFrame(time);
                    }
                    playSoundWalking(2, delta);
                }else if(lastDirectionHaiBen.equals("L")){
                    textureRegion = animationLeft.getKeyFrame(0);
                }else if(lastDirectionHaiBen.equals("R")){
                    textureRegion = animationRight.getKeyFrame(0);
                }
                for(Waste w : GroundScreen.wastes){
                    checkAndUpdateZIndex(this,w);
                }

    }

    public void playSoundWalking(int mode, float _delta) {
        timeSound += _delta;
        if (timeSound > 0.4f) {
            timeSound = 0f;
            isPlaying = false;
        }
        if (!isPlaying) {
            if (mode == 1) {
                walking1.play(0.2f);
            } else {
                walking2.play(0.2f);
            }
            isPlaying = true;
        }
    }
}
