package testTowers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;


/**
 * @author Elin Forsberg
 *
 * Test class for Tower
 */
public class TestTower {

    @Test
    public void testUpdateTower(){
        List<IProjectile> projectilesList = new ArrayList<>();
        ITower tower = TowerFactory.CreateSmurf(0,0);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        assertEquals(1, projectilesList.size());
        tower.update(projectilesList, 0, true);
        assertEquals(1, projectilesList.size());

    }

    @Test
    public void testUpgradeTower(){
        HashMap<String, Long> upgrades = new HashMap<>();
        upgrades.put("attackDmgMul", 0L);
        upgrades.put("attackSpeedMul", 0L);
        upgrades.put("attackRangeMul", 0L);
        ITower tower = TowerFactory.CreateSmurf(0,0);
        tower.placeTower();

        assertEquals("IT-Smurf1", tower.getSpriteKey());
        tower.upgradeTower(upgrades);
        assertEquals("IT-Smurf2", tower.getSpriteKey());
        assertTrue(tower.getUpgradeLevel() > 1);
    }

    @Test
    public void testTowerGetCost(){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        assertTrue(tower.getCost() > 0);
    }

    @Test
    public void testTowerCollision(){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        assertFalse(tower.getCollision());
        tower.setCollision(true);
        assertTrue(tower.getCollision());

    }

    @Test
    public void testTowerGetName (){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        assertEquals(tower.getName(), "IT-Smurf");
    }

    @Test
    public void testTowerGetWidthAndHeight (){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        assertTrue(tower.getWidth() > 0);
        assertTrue(tower.getHeight() > 0);
    }

    @Test
    public void testTowerGetRange (){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        assertTrue(tower.getRange() > 0);
    }

    @Test
    public void testTowerGetTargetMode (){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        assertNotNull(tower.getCurrentTargetMode());
    }

    @Test
    public void testTowerIsPlaced (){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        assertFalse(tower.isPlaced());
    }




}
