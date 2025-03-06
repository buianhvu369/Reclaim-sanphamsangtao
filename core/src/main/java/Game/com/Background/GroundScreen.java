package Game.com.Background;

import Game.com.Actor.Player;
import Game.com.Actor.Waste;
import Game.com.Health;
import Game.com.Master;
import Game.com.ThanhHealth;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GroundScreen implements Screen {
    Master game;
    OrthographicCamera camera;
    Stage stage;
    Health playerHealth;
    ThanhHealth thanhHealth;
    Stage noMoveStage;
    Random random;
    Texture bagButtonImage;
    TextButton bagButton;
    public float timeWaitForNhatRac = 0;
    float wasteX;
    float wasteY;
    public static Array<Waste> wastes = new Array<>();
    public GroundScreen(Master game){
        this.game = game;
        stage = new Stage();
        noMoveStage = new Stage();

        random = new Random();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch = new SpriteBatch();

        bagButtonImage = new Texture("buttonbag.png");

        if(game.player == null) {
            game.player = new Player((float) (Gdx.graphics.getWidth() / 2), (float) (Gdx.graphics.getHeight() / 2), stage);

        }
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        style.up = new TextureRegionDrawable(bagButtonImage);
        bagButton = new TextButton( "",style);
        bagButton.setPosition(Gdx.graphics.getWidth()-bagButtonImage.getWidth(),Gdx.graphics.getHeight()-bagButtonImage.getHeight()) ;
        noMoveStage.addActor(bagButton);
        Gdx.input.setInputProcessor(noMoveStage);
        bagButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(game.bagScreen);
            }
        });
        thanhHealth = new ThanhHealth(27,27,noMoveStage);
        playerHealth = new Health(32,32,noMoveStage);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BROWN);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        if((float) Gdx.graphics.getWidth() / 2 - game.player.getWidth() /2 <= game.player.getX() && game.player.getX()  <= (float) (1000 - Gdx.graphics.getWidth()/2) - game.player.getWidth()/2) {
            stage.getCamera().position.x = game.player.getX() + game.player.getWidth() / 2;
            camera.position.x = game.player.getX() + game.player.getWidth() / 2;
        }
        if((float) Gdx.graphics.getHeight()/2 - game.player.getHeight()/2 <= game.player.getY() &&  game.player.getY() <=  (1000 - (float) Gdx.graphics.getHeight()/2) - game.player.getHeight()/2){
            stage.getCamera().position.y = game.player.getY() + game.player.getHeight() /2;
            camera.position.y = game.player.getY() + game.player.getHeight() /2;
        }
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(wastes.size < 200){
                    Waste waste = new Waste(random.nextInt(900), random.nextInt(900), stage);
                    wastes.add(waste);
                }
            }
        },0);

        for(Waste w: wastes){
            w.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y){
                    if(timeWaitForNhatRac <= 0){
                        wasteX = w.getX();
                        wasteY = w.getY();
                        w.hasEaten = true;
                        switch (game.player.itemUse){
                            case "hands" -> timeWaitForNhatRac = 5f;
                            case "mouth" -> timeWaitForNhatRac = 3f;
                            case "tumcanhnon" -> timeWaitForNhatRac = 8f;
                            case "choikocan" -> timeWaitForNhatRac = 5f;
                            case "choiretien" -> timeWaitForNhatRac = 4f;
                            case "choi" -> timeWaitForNhatRac = 4f;
                            case "choicaocap" -> timeWaitForNhatRac = 3f;
                            case "choivang" -> timeWaitForNhatRac = 0.5f;
                            case "choidung1lan" -> timeWaitForNhatRac = 0.1f;
                            case "mayhutbuipanasoni" -> timeWaitForNhatRac = 1f;
                            case "mayhutbuipanassonic" -> timeWaitForNhatRac = 15f;
                            case "mayhutbuipanasonic" -> timeWaitForNhatRac = 0f;
                            case "luoi" -> timeWaitForNhatRac = 2f;
                            case "vot" -> timeWaitForNhatRac = 2f;
                        }
                    }
                }
            });
        }
        playerHealth.capnhatmau(game.player.health);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                stage.act();
            }
        },0);
        stage.draw();
        noMoveStage.act();
        noMoveStage.draw();
        game.batch.begin();
        if(timeWaitForNhatRac > 0){
            timeWaitForNhatRac -= 1/60f;
            game.smallFont.draw(game.batch, String.valueOf(timeWaitForNhatRac),wasteX,wasteY);
        }else{
            for (Waste w : wastes){
                if(w.hasEaten){
                    wastes.removeIndex(w.getZIndex());
                    w.remove();
                    System.out.println(wastes.size);
                    if(!game.player.itemUse.equals("mouth")){
                        for(int i = 0;i < 3; i ++){
                            int ran = ThreadLocalRandom.current().nextInt(1, 101);//tu 1 den 100
                            if(ran == 1){
                                game.xyanua ++;
                            }else if(ran <= 10 ){
                                game.tao ++;
                            }else if(ran <= 13 ){
                                game.tien += ThreadLocalRandom.current().nextInt(1, 101)*1000;
                            }else if(ran <= 30 ){
                                game.vo_chai_nhua ++;
                            }else if(ran <= 50 ){
                                game.canh_cay ++;
                            }else if(ran <= 65 ){
                                game.tui_nylon ++;
                            }else if(ran <= 80 ){
                                game.vo_do_hop ++;
                            }else if(ran <= 85 ){
                                game.khau_trang ++;
                            }else if(ran <= 100 ){
                                game.canh_non ++;
                            }
                        }
                }
                }
            }
        }
        game.batch.end();
    }
    @Override
    public void resize(int i, int i1) {

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
