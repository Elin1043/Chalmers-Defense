package testMap;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Elin Forsberg
 *
 * Test class for map
 */
public class TestMap {
    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Model model = new Model();

    @Test
    public void testMapProjectileCollision() {
        model.dragStart("smurf",0,0);
        model.dragEnd(100,100,190,640);       // Creates and places smurf
        assertTrue(model.getAllMapObjects().size() > 0);        // To verify Smurf is on the map
        model.startRoundPressed();                                      // Begins to spawn viruses

        while(model.getAllMapObjects().size() > 1){                     // Loops until one projectile has hit the first virus so the tower is
            model.updateModel();                                        // the only object left on map
        }

        assertEquals(1, model.getAllMapObjects().size());
    }
}
