package testTowers;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.model.event.EventBus;
import com.mygdx.chalmersdefense.utilities.Preferences;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Daniel Persson
 * <p>
 * 2021-10-07 Modified by Joel Båtsman Hilmersson: Now also test exceptions in methods
 * <p>
 * Test class for Upgrades class
 */
public class TestUpgrades {
    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Preferences preferences = new Preferences();
    private final Model model = new Model(preferences);


    @Test
    public void testGetUpgradeTitleForTower() {
        String upgradeTitle = model.getTowerUpgradeTitle("IT-Smurf", 1);
        assertEquals("Hot binaries", upgradeTitle);
    }

    @Test
    public void testGetUpgradeDescForTower() {
        String upgradeDesc = model.getTowerUpgradeDesc("IT-Smurf", 1);
        assertEquals("Increases attack speed by 20%", upgradeDesc);
    }

    @Test
    public void testGetUpgradePriceForTower() {
        Integer upgradePrice = model.getTowerUpgradePrice("IT-Smurf", 1);
        assertTrue(Math.abs(500 - upgradePrice) < 0.001);
    }

    @Test
    public void testUpgradeTower() {
        model.dragStart("eco", 300, 300); // Creates tower
        model.dragEnd(300, 300);

        model.upgradeClickedTower();
        int upgradeLevel = Character.getNumericValue(model.getClickedTower().getSpriteKey().charAt(model.getClickedTower().getSpriteKey().length() - 1));
        assertEquals(2, upgradeLevel);
    }

    @Test
    public void testGetTowerUpgradeTitleException() {
        String upgradeTitle = model.getTowerUpgradeTitle("This name should not exist and therefore throw exception", 100);
        assertEquals("", upgradeTitle); // If the exception was thrown, it will return an empty string
    }

    @Test
    public void testGetTowerUpgradeDescException() {
        String upgradeDesc = model.getTowerUpgradeDesc("This name should not exist and therefore throw exception", 100);
        assertEquals("", upgradeDesc); // If the exception was thrown, it will return an empty string
    }

    @Test
    public void testGetTowerUpgradePriceException() {
        int upgradePrice = model.getTowerUpgradePrice("This name should not exist and therefore throw exception", 100);
        assertEquals(0, upgradePrice); // If the exception was thrown, it will return a long = 0
    }
}
