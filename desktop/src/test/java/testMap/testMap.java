package testMap;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class testMap {
    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Model model = new Model();
    @Test
    public void testMapProjectileCollision() {
        model.dragStart("smurf",0,0);
        model.dragEnd(100,100,190,640);
        assertTrue(model.getAllMapObjects().size() > 0);
        model.startRoundPressed();

        while(model.getAllMapObjects().size() > 1){
            model.updateModel();
        }

        assertEquals(1, model.getAllMapObjects().size());
    }
}
