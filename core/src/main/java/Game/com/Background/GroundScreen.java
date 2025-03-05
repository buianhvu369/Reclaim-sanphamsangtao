package Game.com.Background;

import Game.com.Actor.Player;
import Game.com.Master;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
public class GroundScreen implements Screen {
    Master game;
    OrthographicCamera camera;
    Stage stage;
    public GroundScreen(Master game){
        this.game = game;
        stage = new Stage();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch = new SpriteBatch();
        if(game.player == null) {
            game.player = new Player((float) (Gdx.graphics.getWidth() / 2), (float) (Gdx.graphics.getHeight() / 2), stage);
        }
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
