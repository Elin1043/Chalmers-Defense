package testTowers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import com.mygdx.chalmersdefense.model.targetMode.TargetModeFactory;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;


/**
 * @author Elin Forsberg
 * <p>
 * Test class for Tower
 * <p>
 * 2021-10-11 Modified by Joel Båtsman Hilmersson: Added tests for changing target modes <br>
 */
public class TestTower {

    @Test
    public void testUpdateTower() {
        List<IProjectile> projectilesList = new ArrayList<>();
        ITower tower = TowerFactory.createSmurf(0, 0);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        assertEquals(1, projectilesList.size());
        tower.update(projectilesList, 0, true);
        assertEquals(1, projectilesList.size());

    }

    @Test
    public void testUpgradeTower() {
        HashMap<String, Double> upgrades = new HashMap<>();
        upgrades.put("attackDmgMul", 0.0);
        upgrades.put("attackSpeedMul", 0.0);
        upgrades.put("attackRangeMul", 0.0);
        ITower tower = TowerFactory.createSmurf(0, 0);
        tower.placeTower();

        assertEquals("IT-Smurf1", tower.getSpriteKey());
        tower.upgradeTower(upgrades);
        assertEquals("IT-Smurf2", tower.getSpriteKey());
        assertTrue(tower.getUpgradeLevel() > 1);
    }

    @Test
    public void testTowerGetCost() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        assertTrue(tower.getCost() > 0);
    }

    @Test
    public void testTowerCollision() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        assertFalse(tower.canRemove());
        tower.setIfCanRemove(true);
        assertTrue(tower.canRemove());

    }

    @Test
    public void testTowerGetName() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        assertEquals(tower.getName(), "IT-Smurf");
    }

    @Test
    public void testTowerGetWidthAndHeight() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        assertTrue(tower.getWidth() > 0);
        assertTrue(tower.getHeight() > 0);
    }

    @Test
    public void testTowerGetRange() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        assertTrue(tower.getRange() > 0);
    }

    @Test
    public void testTowerGetTargetMode() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        assertNotNull(tower.getCurrentTargetMode());
    }

    @Test
    public void testTowerIsPlaced() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        assertFalse(tower.isPlaced());
    }

    @Test
    public void testChangeTargetModeRight(){
        ITower tower = TowerFactory.createSmurf(0, 0);
        List<ITargetMode> targetModes = TargetModeFactory.getTargetModes();

        for (int i = 0; i < targetModes.size(); i++) {    // This should spinn target modes around completely and be on the same as it started on
            tower.changeTargetMode(true);
        }
        assertEquals(targetModes.get(0), tower.getCurrentTargetMode());
    }

    @Test
    public void testChangeTargetModeLeft(){
        ITower tower = TowerFactory.createSmurf(0, 0);
        List<ITargetMode> targetModes = TargetModeFactory.getTargetModes();

        for (int i = 0; i < targetModes.size(); i++) {    // This should spinn target modes around completely and be on the same as it started on
            tower.changeTargetMode(false);
        }
        assertEquals(targetModes.get(0), tower.getCurrentTargetMode());
    }

}
