package Game.com.Background;

import Game.com.Master;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class BagScreen implements Screen {
    Master game;
    OrthographicCamera camera;
    Stage stage;
    TextButton exit2Button;
    Texture exit2ButtonImage;
    public BagScreen(Master game){
        this.game = game;
        stage = new Stage();

        camera = new OrthographicCamera();

        exit2ButtonImage = new Texture("exit2.png");

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        style.up = new TextureRegionDrawable(exit2ButtonImage);
        exit2Button = new TextButton( "",style);
        exit2Button.setPosition(0,Gdx.graphics.getHeight()-exit2ButtonImage.getHeight()) ;
        stage.addActor(exit2Button);
        Gdx.input.setInputProcessor(stage);
        exit2Button.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(game.groundScreen);
            }
        });
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.WHITE);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();
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
