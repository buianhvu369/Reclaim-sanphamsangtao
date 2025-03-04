package Game.com.Background;

import Game.com.Master;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    Texture startButtonImage;
    Texture exitButtonImage;
    Texture backgroundImage;
    Master game;
    OrthographicCamera camera;
    Stage stage;
    public MenuScreen(Master game){
        this.game = game;
        stage = new Stage();
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch = new SpriteBatch();

        startButtonImage = new Texture("start.png");
        exitButtonImage = new Texture("exit.png");
        backgroundImage = new Texture("backgroundmenu.png");

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        style.up = new TextureRegionDrawable(startButtonImage);
        TextButton startButton = new TextButton( "",style);
        startButton.setPosition(Gdx.graphics.getWidth()/2 - startButton.getWidth() - 10,
            Gdx.graphics.getHeight()/2 - startButton.getHeight() - 5) ;
        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);
        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(game.groundScreen);
            }
        });

        TextButton.TextButtonStyle style2 = new TextButton.TextButtonStyle();
        style2.font = game.font;
        style2.fontColor = Color.WHITE;
        style2.up = new TextureRegionDrawable(exitButtonImage);
        TextButton exitButton = new TextButton( "",style2);
        exitButton.setPosition(Gdx.graphics.getWidth()/2 + 10,
            Gdx.graphics.getHeight()/2 - exitButton.getHeight() - 5) ;
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
        exitButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLUE);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundImage,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.font.draw(game.batch,"RECLAIM",(float)(Gdx.graphics.getWidth()/2 - 110),(float)(Gdx.graphics.getHeight()/2 + 150));
        game.batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
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
