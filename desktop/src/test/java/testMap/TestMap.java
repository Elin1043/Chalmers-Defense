package testMap;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Elin Forsberg
 * <p>
 * Test class for map
 */
public class TestMap {
    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Model model = new Model();

    @Test
    public void testMapProjectileCollision() {
        model.dragStart("electro", 0, 0);
        model.dragEnd(100, 100, 190, 640);       // Creates and places smurf
        assertTrue(model.getAllMapObjects().size() > 0);        // To verify Smurf is on the map
        model.startRoundPressed();                                      // Begins to spawn viruses

        while (model.getAllMapObjects().size() > 1) {                     // Loops until one projectile has hit the first virus so the tower is
            model.updateModel();                                        // the only object left on map
        }

        assertEquals(1, model.getAllMapObjects().size());       // To see if it can kill on virus

        int moneyBeforeMoreSpawn = model.getMoney();

        for (int i = 0; i < 10000; i++) {                                // To test more lines in map
            model.updateModel();                                        // Loops so the virus can move and tower shoot more
        }

        assertTrue(model.getMoney() > moneyBeforeMoreSpawn);    // To see if tower has hit more virus in the update loop

    }
}
