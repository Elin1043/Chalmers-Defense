package testModelClasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
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
    Model model;

    @Before
    public void init() {
        model = new Model();
    }

    @Test
    public void testGetAllMapObjects() {
        model.dragStart("smurf",0,0);
        model.dragEnd(100,100,100,100);
        assertTrue(model.getAllMapObjects().size() > 0);
    }

}