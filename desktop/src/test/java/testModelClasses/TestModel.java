package testModelClasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Test class for model
 *
 * 2021-09-25 Modified by Elin Forsberg: added testing methods
 */
public class TestModel {

    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Model model = new Model();

    @Test
    public void testGetAllMapObjects() {
        model.dragStart("smurf",0,0);
        model.dragEnd(100,100,100,100);
        assertTrue(model.getAllMapObjects().size() > 0);
    }

    @Test
    public void testUpdateModel() {
        model.dragStart("smurf",0,0);
        model.dragEnd(100,100,100,100);
        assertTrue(model.getAllMapObjects().size() > 0);
        model.startRoundPressed();
        for (int i = 0; i < 1000; i++) {
            model.updateModel();
        }

        assertTrue(model.getAllMapObjects().size() > 5);

    }


}