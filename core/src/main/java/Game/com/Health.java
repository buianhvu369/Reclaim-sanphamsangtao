package Game.com;

import Game.com.Actor.MyActor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Health extends MyActor {
    public Health(float x, float y, Stage s){
        super(x,y,s);
        textureRegion = new TextureRegion(new Texture("mau.png"));
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    public void capnhatmau(float health){
        setScale(1f,health/100f);
    }
}
