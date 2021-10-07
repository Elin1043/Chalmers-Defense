package testProjectiles;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Elin Forsberg
 * <p>
 * Test class for AcidProjectile
 */
public class TestAcidProjectile {
    List<IProjectile> projectilesList = new ArrayList<>();
    List<IProjectile> addList = new ArrayList<>();
    IProjectile projectile;

    @Before
    public void createProjectile() {
        ITower tower = TowerFactory.CreateChemist(0, 0, addList);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        projectile = projectilesList.get(0);
    }

    @Test
    public void testVirusHit() {
        assertEquals(0, addList.size());
        projectile.update(true, 0, 0);
        assertTrue(addList.size() > 0);
    }

}
