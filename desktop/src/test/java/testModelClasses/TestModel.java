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

    @Test
    public void testResetModel(){
        int startCapital = model.getMoney();
        int startHealth = model.getLivesLeft();

        model.dragStart("smurf",0,0);
        model.dragEnd(100,100,100,100);

        model.startRoundPressed();
        assertTrue(model.getAllMapObjects().size() > 0);
        assertEquals(1, model.getCurrentRound());
        for (int i = 0; i < 10000; i++) {
            model.updateModel();
        }
        model.startRoundPressed();
        assertEquals(2, model.getCurrentRound());
        assertTrue(startCapital > model.getMoney());
        assertTrue(startHealth > model.getLivesLeft());

        model.resetModel();

        assertEquals(1, model.getCurrentRound());
        assertEquals(startCapital, model.getMoney());
        assertEquals(startHealth, model.getLivesLeft());
        assertEquals(0, model.getAllMapObjects().size());
    }

}