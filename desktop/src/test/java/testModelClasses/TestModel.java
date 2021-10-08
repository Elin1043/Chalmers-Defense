package testModelClasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Joel Båtsman Hilmersson
 * <p>
 * Test class for model
 * <p>
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
        model.dragStart("mech", 0, 0);
        model.dragEnd(100, 100, 100, 100);
        assertEquals(1, model.getAllMapObjects().size());
    }

    @Test
    public void testGetTowerSellprice() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100, 100, 100);
        assertEquals(60, model.getClickedTowerSellPrice());

        model.upgradeClickedTower();
        assertEquals(360, model.getClickedTowerSellPrice());
    }

    @Test
    public void testGetTowerTargetMode() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100, 100, 100);

        String[] namesArray = model.getClickedTowerTargetMode().getClass().getName().split("[.]");
        assertEquals((namesArray[namesArray.length - 1]), "First");

    }

    @Test
    public void testChangeTowerTargetModeRight() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100, 100, 100);

        String[] namesArray = model.getClickedTowerTargetMode().getClass().getName().split("[.]");
        assertEquals((namesArray[namesArray.length - 1]), "First");

        model.changeTargetMode(true);

        namesArray = model.getClickedTowerTargetMode().getClass().getName().split("[.]");
        assertEquals((namesArray[namesArray.length - 1]), "Last");

    }

    @Test
    public void testChangeTowerTargetModeLeft() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100, 100, 100);

        String[] namesArray = model.getClickedTowerTargetMode().getClass().getName().split("[.]");
        assertEquals((namesArray[namesArray.length - 1]), "First");

        model.changeTargetMode(false);

        namesArray = model.getClickedTowerTargetMode().getClass().getName().split("[.]");
        assertEquals((namesArray[namesArray.length - 1]), "Closest");

    }




    @Test
    public void testUpdateModel() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100, 100, 100);
        assertTrue(model.getAllMapObjects().size() > 0);
        model.startRoundPressed();

        for (int i = 0; i < 1000; i++) {
            model.updateModel();
        }

        assertTrue(model.getAllMapObjects().size() > 5);
    }

    @Test
    public void testResetModel() {
        int startCapital = model.getMoney();
        int startHealth = model.getLivesLeft();

        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100, 100, 100);

        model.startRoundPressed();  // StartRound
        model.startRoundPressed();  // Speed UP updates     (To get line coverage)
        model.startRoundPressed();  // Slow Down updates    (To get line coverage)
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

    @Test
    public void testOnDrag() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(2, 2, 1440, 300);   // Places a tower first to get more line coverage
        model.dragStart("chemist", 0, 0);
        model.onDrag(10, 10, 0, 0, 1080, 1920);
        model.onDrag(10, 10, 1440, 300, 1080, 1920);
        model.onDrag(10, 10, -500, 456, 1080, 1920);
        model.onDrag(10, 10, 500, -456, 1080, 1920);
        model.onDrag(10, 10, 50, 456, 1080, 1920);
        model.onDrag(10, 10, 20, 780, 1080, 1920);
        model.dragEnd(10, 10, 100, 240);
        assertTrue(model.getAllMapObjects().size() > 1);
    }

    @Test
    public void testDragEnd() {
        model.dragStart("electro", 0, 0);
        assertEquals(1, model.getAllMapObjects().size());
        model.onDrag(10, 10, 0, 0, 1080, 1920);
        model.onDrag(10, 10, -500, 456, 1080, 1920);
        model.onDrag(10, 10, 50, 456, 1080, 1920);
        model.onDrag(10, 10, 0, 0, 1080, 1920);
        model.dragEnd(10, 10, 0, 0);

        assertEquals(0, model.getAllMapObjects().size());
    }

    @Test
    public void testCheckIfTowerClicked() {
        model.checkIfTowerClicked(0, 0);
        assertNull(model.getClickedTower());
        model.dragStart("hacker", 0, 0);
        model.dragEnd(100, 100, 100, 100);
        model.checkIfTowerClicked(100, 100);
        assertNotNull(model.getClickedTower());
    }

    @Test
    public void testGetRangeCircle() {
        assertNotNull(model.getRangeCircle());
    }

    @Test
    public void testGetIsGameLost() {
        model.startRoundPressed();
        while (model.getAllMapObjects().size() > 0) {
            model.updateModel();
        }
        model.startRoundPressed();
        while (model.getAllMapObjects().size() > 0) {
            model.updateModel();
        }

        assertTrue(model.getIsGameLost());
    }

}