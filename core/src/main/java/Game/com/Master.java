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
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Master extends Game{
    public SpriteBatch batch;
    public BitmapFont font;
    public BitmapFont smallFont;
    Stage stage;
    Random random = new Random();
    public float xyanua = 0;
    public float tui_nylon = 0;
    public float vo_chai_nhua = 0;
    public float canh_cay = 0;
    public float vo_do_hop = 0;
    public float tao = 0;
    public float khau_trang = 0;
    public float canh_non = 0;
    public float tien = 0;//very quan trong
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
        FreeTypeFontGenerator fontGenerator2 = new FreeTypeFontGenerator(Gdx.files.internal("Lonely Cake.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters2.size = 24;
        fontParameters2.color = Color.BLACK;
        smallFont = fontGenerator2.generateFont(fontParameters2);
        fontGenerator2.dispose();
        groundScreen = new GroundScreen(this);
        seaScreen = new SeaScreen(this);
        bagScreen = new BagScreen(this);
        this.setScreen(new MenuScreen(this));// goi man hinh moi
    }
    public void render(){
        super.render();
    }
}
