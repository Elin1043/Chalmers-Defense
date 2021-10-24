package testModelClasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.model.modelUtilities.events.ModelEvents;
import com.mygdx.chalmersdefense.utilities.Preferences;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;
import com.mygdx.chalmersdefense.utilities.event.EventBus;
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
    Preferences preferences = new Preferences();
    Model model;
    EventBus eventBus = new EventBus();

    @Before
    public void init() {
        preferences.putBoolean("autoplay", false);
        model = new Model(preferences);
        eventBus.listenFor(ModelEvents.class, model);
    }

    @Test
    public void testGetAllMapObjects() {
        model.dragStart("mech", 0, 0);
        model.dragEnd(100, 100);
        assertEquals(1, model.getAllMapObjects().size());
    }

    @Test
    public void testGetTowerSellprice() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100);
        assertEquals(120, model.getClickedTowerSellPrice());

        model.upgradeClickedTower();
        assertEquals(420, model.getClickedTowerSellPrice());
    }

    @Test
    public void testGetTowerTargetMode() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100);

        assertEquals(model.getClickedTowerTargetMode(), "First");
    }

    @Test
    public void testChangeTowerTargetModeRight() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100);

        assertEquals(model.getClickedTowerTargetMode(), "First");

        model.changeTargetMode(true);

        assertEquals(model.getClickedTowerTargetMode(), "Last");
    }

    @Test
    public void testChangeTowerTargetModeLeft() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100);

        assertEquals(model.getClickedTowerTargetMode(), "First");

        model.changeTargetMode(false);

        assertEquals(model.getClickedTowerTargetMode(), "Strongest");
    }

    @Test
    public void testAllPowerUpClicked(){
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100);

        model.startRoundPressed();
        model.powerUpClicked("cleanHands");
        model.powerUpClicked("maskedUp");
        model.powerUpClicked("vaccinated");
        eventBus.emit(new ModelEvents(ModelEvents.EventType.UPDATEMODEL));

        assertTrue(model.getPowerUpActive()[0] && model.getPowerUpActive()[1] && model.getPowerUpActive()[2]);
        assertTrue(model.getPowerUpTimer()[0] > 0 && model.getPowerUpTimer()[1] > 0 && model.getPowerUpTimer()[2] > 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testIllegalPowerUpCall(){
        model.startRoundPressed();
        model.powerUpClicked("This Should never be a power-up");
    }

    @Test
    public void testUpdateModel() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(100, 100);
        assertTrue(model.getAllMapObjects().size() > 0);
        model.startRoundPressed();

        for (int i = 0; i < 1000; i++) {
            eventBus.emit(new ModelEvents(ModelEvents.EventType.UPDATEMODEL));
        }

        assertTrue(model.getAllMapObjects().size() > 5);
    }

    @Test
    public void testBeforeResetModel() {
        int startHealth = model.getLivesLeft();

        model.dragStart("chemist", 0, 0);
        model.dragEnd(100, 100);

        model.startRoundPressed();  // StartRound
        assertTrue(model.getAllMapObjects().size() > 0);
        assertEquals(1, model.getCurrentRound());
        for (int i = 0; i < 10000; i++) {
            eventBus.emit(new ModelEvents(ModelEvents.EventType.UPDATEMODEL));
        }
        model.startRoundPressed();
        assertEquals(2, model.getCurrentRound());
        assertTrue(startHealth > model.getLivesLeft());
    }

    @Test
    public void testResetModel() {
        int startCapital = model.getMoney();
        int startHealth = model.getLivesLeft();

        model.dragStart("chemist", 0, 0);
        model.dragEnd(100, 100);

        model.startRoundPressed();  // StartRound
        for (int i = 0; i < 10000; i++) {
            eventBus.emit(new ModelEvents(ModelEvents.EventType.UPDATEMODEL));
        }

        model.resetModel();

        assertEquals(1, model.getCurrentRound());
        assertEquals(startCapital, model.getMoney());
        assertEquals(startHealth, model.getLivesLeft());
        assertEquals(0, model.getAllMapObjects().size());
    }



    @Test (expected = IllegalArgumentException.class)
    public void testDragStartIllegalTowerName() {
        model.dragStart("This should never be a tower name", 0, 0);
    }

    @Test
    public void testOnDrag() {
        model.dragStart("smurf", 0, 0);
        model.dragEnd(1380, 250);   // Places a tower first to get more line coverage

        model.dragStart("chemist", 0, 0);
        model.onDrag(0, 0);
        model.onDrag(1440, 300);
        model.onDrag(-500, 456);
        model.onDrag(500, -456);
        model.onDrag(50, 456);
        model.onDrag(95, 600);
        model.dragEnd(60, 200);
        assertTrue(model.getAllMapObjects().size() > 1);
    }

    @Test
    public void testDragEnd() {
        model.dragStart("electro", 0, 0);
        assertEquals(1, model.getAllMapObjects().size());
        model.onDrag(0, 0);
        model.onDrag(-500, 456);
        model.onDrag(50, 456);
        model.onDrag(0, 0);
        model.dragEnd(0, 0);

        assertEquals(0, model.getAllMapObjects().size());
    }

    @Test
    public void testCheckIfTowerClicked() {
        model.checkIfTowerClicked(0, 0);
        assertNull(model.getClickedTower());
        model.dragStart("hacker", 0, 0);
        model.dragEnd(100, 100);
        model.checkIfTowerClicked(100, 100);
        assertNotNull(model.getClickedTower());
    }

    @Test
    public void testGetRangeCircle() {
        assertNotNull(model.getRangeCircle());
    }

    @Test
    public void testSetShowOverlay() {
        model.setShowOverlay(ScreenOverlayEnum.WINPANEL);
        assertEquals(model.getCurrentOverlay(), ScreenOverlayEnum.WINPANEL);
    }

    @Test
    public void testGetMapImgagePath() {
        assertEquals("ClassicMap.png", model.getMapImagePath());
    }

    @Test
    public void testIsGameSpedUp() {
        model.startGameUpdate(); // Start update Timer
        model.stopGameUpdate(); // Stop update Timer

        model.startRoundPressed();
        model.startRoundPressed(); // SpeedUp game
        model.startRoundPressed(); // SlowDown game
        model.startRoundPressed(); // SpeedUp again
        assertTrue(model.isGameSpedUp());
    }

    @Test
    public void testGetIsGameLost() {

        while (model.getLivesLeft() > 0) {
            model.startRoundPressed();
            eventBus.emit(new ModelEvents(ModelEvents.EventType.UPDATEMODEL));
            while (model.getAllMapObjects().size() > 0) {
                eventBus.emit(new ModelEvents(ModelEvents.EventType.UPDATEMODEL));
            }
        }

        assertEquals(model.getCurrentOverlay(), ScreenOverlayEnum.LOSEPANEL);
    }

}