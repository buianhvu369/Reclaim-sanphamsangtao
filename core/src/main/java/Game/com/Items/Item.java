package Game.com.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Item extends Actor {
    public float doben;
    TextureRegion textureRegion;
    Item(float doben2, Stage s, Texture texture){
        s.addActor(this);
        doben = doben2;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(doben < 1){
            this.remove();
        }
    }
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.setColor(1, 1, 1, 1); // Reset lại màu batch để tránh ảnh hưởng đến các Actor khác}
    }
}
