package testModelClasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.event.EventBus;
import com.mygdx.chalmersdefense.utilities.Preferences;
import com.mygdx.chalmersdefense.utilities.ScreenOverlayEnum;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Daniel Persson
 */
public class TestRounds {
    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Preferences preferences = new Preferences();
    Model model;

    @Before
    public void init() {
        preferences.putBoolean("autoplay", false);
        model = new Model(preferences);
    }

    @Test
    public void testStartingRound() {
        assertEquals(1, model.getCurrentRound());
    }

    @Test
    public void testWinningRound() {
        assertEquals(30, model.getWinningRound());
    }

    @Test
    public void testWinningPanelShowing() {
//        setupTowers();
//
//        model.startRoundPressed();
//        model.updateModel();
//
//        while (model.showOverlay() != ScreenOverlayEnum.WINPANEL) {
//
//            while (model.getAllMapObjects().size() > 4) {
//                model.updateModel();
//            }
//
//            model.startRoundPressed();
//            model.updateModel();
//        }
//
//        assertSame(model.showOverlay(), ScreenOverlayEnum.WINPANEL);
//        model.setShowOverlay(ScreenOverlayEnum.NONE);
//        assertNotSame(model.showOverlay(), ScreenOverlayEnum.WINPANEL);
    }


    private void setupTowers() {
        model.dragStart("chemist", 0, 0);
        model.dragEnd(750, 980);

        model.dragStart("chemist", 0, 0);
        model.dragEnd(750, 860);

        model.dragStart("chemist", 0, 0);
        model.dragEnd(100, 740);

        model.dragStart("chemist", 0, 0);
        model.dragEnd(750, 620);
    }

}
