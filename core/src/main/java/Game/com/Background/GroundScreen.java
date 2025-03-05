package Game.com.Background;

import Game.com.Actor.Player;
import Game.com.Actor.Waste;
import Game.com.Master;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class GroundScreen implements Screen {
    Master game;
    OrthographicCamera camera;
    Stage stage;
    Random random;

    Texture bagButtonImage;
    TextButton bagButton;

    public static Array<Waste> wastes = new Array<>();
    public GroundScreen(Master game){
        this.game = game;
        stage = new Stage();

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
//        stage.addActor(bagButton);
//        Gdx.input.setInputProcessor(stage);
        bagButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(game.bagScreen);
            }
        });
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
        }
        if((float) Gdx.graphics.getHeight()/2 - game.player.getHeight()/2 <= game.player.getY() &&  game.player.getY() <=  (1000 - (float) Gdx.graphics.getHeight()/2) - game.player.getHeight()/2){
            stage.getCamera().position.y = game.player.getY() + game.player.getHeight() /2;
        }

        if(wastes.size < 200){
            Waste waste = new Waste(random.nextInt(900), random.nextInt(900), stage);
            wastes.add(waste);
        }

        stage.act();
        stage.draw();
        game.batch.begin();
        bagButton.draw(game.batch, 10);
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
