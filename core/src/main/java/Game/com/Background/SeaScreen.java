package Game.com.Background;

import Game.com.Master;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class SeaScreen implements Screen {
    Master game;
    OrthographicCamera camera;
    Stage stage;
    public SeaScreen(Master game){
        this.game = game;
        stage = new Stage();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch = new SpriteBatch();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLUE);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
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
