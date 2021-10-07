package testTowers;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for the Eco Tower
 */
public class TestEcoTower {

    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Model m;

    @Before
    public void init() {
        m = new Model();    // Need to create model since Player class is package private which we need to create an EcoTower
    }

    @Test
    public void testAddMoneyToPlayer() {
        int startCapital = m.getMoney();
        m.dragStart("eco", 300, 300); // Creates tower
        m.dragEnd(2, 2, 300, 300);

        assertTrue(startCapital > m.getMoney());    // The tower should have removed money from player when placed
        startCapital = m.getMoney();

        m.updateModel();
        assertTrue(m.getMoney() > startCapital);    // The player should now have received money from eco tower
    }

    @Test
    public void testAddMoneyMultipleTimesToPlayer() {
        m.dragStart("eco", 300, 300); // Creates tower
        m.dragEnd(2, 2, 300, 300);

        m.updateModel();
        int startCapital = m.getMoney();

        for (int i = 0; i < 1000; i++) {  // Some high number to let the tower have time to reload and give player more money
            m.updateModel();
        }
        assertTrue(m.getMoney() > startCapital);    // The player should now have received money from eco tower
    }

    @Test
    public void testMoreMoneyWhenUpgraded() {
        m.dragStart("eco", 300, 300); // Creates tower
        m.dragEnd(2, 2, 300, 300);

        int fixedCapital = m.getMoney();
        while (m.getMoney() <= fixedCapital) {
            m.updateModel();
        }

        int moneyDiff1 = m.getMoney() - fixedCapital;
        fixedCapital = m.getMoney();
        m.upgradeClickedTower();

        while (m.getMoney() <= fixedCapital) {
            m.updateModel();
        }

        int moneyDiff2 = m.getMoney() - fixedCapital;
        assertTrue(moneyDiff2 > moneyDiff1);
        fixedCapital = m.getMoney();
        m.upgradeClickedTower();

        while (m.getMoney() <= fixedCapital) {
            m.updateModel();
        }

        int moneyDiff3 = m.getMoney() - fixedCapital;
        assertTrue(moneyDiff3 > moneyDiff2);

    }
}
