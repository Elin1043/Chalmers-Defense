package testTowers;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.utilities.Preferences;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for the Eco Tower
 */
public class TestEcoTower {

    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Preferences preferences = new Preferences();
    Model model;

    @Before
    public void init() {
        model = new Model(preferences);    // Need to create model since Player class is package private which we need to create an EcoTower
    }

    @Test
    public void testAddMoneyToPlayer() {
        int startCapital = model.getMoney();
        model.dragStart("eco", 300, 300); // Creates tower
        model.dragEnd(300, 300);

        assertTrue(startCapital > model.getMoney());    // The tower should have removed money from player when placed
        startCapital = model.getMoney();

        model.updateModel();
        assertTrue(model.getMoney() > startCapital);    // The player should now have received money from eco tower
    }

    @Test
    public void testAddMoneyMultipleTimesToPlayer() {
        model.dragStart("eco", 300, 300); // Creates tower
        model.dragEnd(300, 300);

        model.updateModel();
        int startCapital = model.getMoney();

        for (int i = 0; i < 1000; i++) {  // Some high number to let the tower have time to reload and give player more money
            model.updateModel();
        }
        assertTrue(model.getMoney() > startCapital);    // The player should now have received money from eco tower
    }

    @Test
    public void testMoreMoneyWhenUpgraded() {
        model.dragStart("eco", 300, 300); // Creates tower
        model.dragEnd(300, 300);
        model.startRoundPressed();

        int fixedCapital = model.getMoney();
        while (model.getMoney() <= fixedCapital) {
            model.updateModel();
        }

        int moneyDiff1 = model.getMoney() - fixedCapital;
        model.upgradeClickedTower();
        fixedCapital = model.getMoney();

        while (model.getMoney() <= fixedCapital) {
            model.updateModel();
        }

        int moneyDiff2 = model.getMoney() - fixedCapital;
        assertTrue(moneyDiff2 > moneyDiff1);
        model.upgradeClickedTower();
        fixedCapital = model.getMoney();

        while (model.getMoney() <= fixedCapital) {
            model.updateModel();
        }

        int moneyDiff3 = model.getMoney() - fixedCapital;
        assertTrue(moneyDiff3 > moneyDiff2);

    }
}
