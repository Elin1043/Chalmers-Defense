package testTowers;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * @author Daniel Persson
 * Test class for Upgrades class
 */
public class TestUpgrades {
    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    private Model model = new Model();


    @Test
    public void testGetUpgradeTitleForTower() {
        String upgradeTitle = model.getTowerUpgradeTitle("IT-Smurf", 1);
        assertEquals("Hot zeros", upgradeTitle);
    }

    @Test
    public void testGetUpgradeDescForTower() {
        String upgradeDesc = model.getTowerUpgradeDesc("IT-Smurf", 1);
        assertEquals("Increases attack speed by 20%", upgradeDesc);
    }

    @Test
    public void testGetUpgradePriceForTower() {
        Long upgradePrice = model.getTowerUpgradePrice("IT-Smurf", 1);
        assertTrue(Math.abs(500 - upgradePrice) < 0.001);
    }
}
