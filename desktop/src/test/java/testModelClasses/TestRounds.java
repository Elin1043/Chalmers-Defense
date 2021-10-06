package testModelClasses;

import static org.junit.Assert.*;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.*;

/**
 * @author Daniel Persson
 */
public class TestRounds {
    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Model model;

    @Before
    public void init() {
        model = new Model();
    }

    @Test
    public void testStartingRound() {
        assertEquals(1, model.getCurrentRound());
    }

    @Test
    public void testWinningRound() {
        assertEquals(10, model.getWinningRound());
    }

    @Test
    public void testWinningPanelShowing() {
        setupTowers();

        model.startRoundPressed();

        while (!model.showWinPanel()) {
            while (model.getAllMapObjects().size() > 4) {
                model.updateModel();
            }
            model.startRoundPressed();
        }

        assertTrue(model.showWinPanel());
        model.continueToFreePlay();
        assertFalse(model.showWinPanel());
    }



    private void setupTowers() {
        model.dragStart("chemist",0,0);
        model.dragEnd(100,100,750,980);

        model.dragStart("chemist",0,0);
        model.dragEnd(100,100,750,860);

        model.dragStart("chemist",0,0);
        model.dragEnd(100,100,100,740);

        model.dragStart("chemist",0,0);
        model.dragEnd(100,100,750,620);
    }

}
