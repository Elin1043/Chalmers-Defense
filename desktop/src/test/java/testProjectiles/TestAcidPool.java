package testProjectiles;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Elin Forsberg
 * <p>
 * Test class for AcidPool
 */
public class TestAcidPool {
    List<IProjectile> projectilesList = new ArrayList<>();
    List<IProjectile> addList = new ArrayList<>();
    IProjectile projectile;
    IProjectile acidPool;

    @Before
    public void createAcidPool() {
        ITower tower = TowerFactory.CreateChemist(0, 0, addList);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        projectile = projectilesList.get(0);
        projectile.update(true, 0, 0);
        acidPool = addList.get(0);
    }

    @Test
    public void testAcidPoolUpdate() {
        while (!acidPool.canRemove()) {
            acidPool.update(false, 0, 0);
        }

        assertTrue(acidPool.canRemove());
    }

    @Test
    public void testAcidPoolVirusIsHit() {
        assertFalse(acidPool.canRemove());
        for (int i = 0; i < 6; i++) {
            acidPool.update(true, 0, 0);
        }
        assertTrue(acidPool.canRemove());
    }


}
