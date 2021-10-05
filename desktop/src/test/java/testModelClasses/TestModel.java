package testModelClasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.IUpdateModel;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.views.GameScreen;
import org.junit.Before;
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

    Model model;

    @Before
    public void init() {
        new LwjglApplication(new ChalmersDefense());
        model = new Model();
    }

    @Test
    public void testGetAllMapObjects() {
        model.dragStart("smurf",0,0);
        model.dragEnd(100,100,100,100);
        assertTrue(model.getAllMapObjects().size() > 0);
    }


}