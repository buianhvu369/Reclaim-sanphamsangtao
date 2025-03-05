package Game.com;

import Game.com.Actor.Player;
import Game.com.Background.BagScreen;
import Game.com.Background.GroundScreen;
import Game.com.Background.MenuScreen;
import Game.com.Background.SeaScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Master extends Game{
    public SpriteBatch batch;
    public BitmapFont font;
    Stage stage;
    public GroundScreen groundScreen;
    public SeaScreen seaScreen;
    public BagScreen bagScreen;
    static public int MAN_SPEED = 4;
    public Player player;

    static int status = 1;
    @Override
    public void create() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Lonely Cake.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = Color.RED;
        font = fontGenerator.generateFont(fontParameters);
        fontGenerator.dispose();
        groundScreen = new GroundScreen(this);
        seaScreen = new SeaScreen(this);
        bagScreen = new BagScreen(this);
        this.setScreen(new MenuScreen(this));// goi man hinh moi
    }
    public void render(){
        super.render();
    }
}
