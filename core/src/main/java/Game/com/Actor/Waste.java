package Game.com.Actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Random;

public class Waste extends MyActor {
    Texture texture1 = new Texture("rac.png");
    Texture texture2 = new Texture("rac2.png");
    Texture texture3 = new Texture("rac3.png");
    Texture texture4 = new Texture("rac4.png");
    Texture[] wastes = new Texture[4];
    public boolean hasEaten = false;
    public Waste(float x, float y, Stage s){
        super(x,y,s);
        setSize(50,50);
        Random random = new Random();
        Texture texture1 = new Texture("rac.png");
        Texture texture2 = new Texture("rac2.png");
        Texture texture3 = new Texture("rac3.png");
        Texture texture4 = new Texture("rac4.png");
        wastes[0] = texture1;
        wastes[1] = texture2;
        wastes[2] = texture3;
        wastes[3] = texture4;
        textureRegion = new TextureRegion(wastes[random.nextInt(0,3)]);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(textureRegion, getX(), getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(), getScaleY(),getRotation());
        batch.setColor(1, 1, 1, 1);
    }
}
