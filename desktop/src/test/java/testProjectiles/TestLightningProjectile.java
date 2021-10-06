package testProjectiles;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Elin Forsberg
 *
 * Test class for LightningProjectile
 */
public class TestLightningProjectile {
    List<IProjectile> projectilesList = new ArrayList<>();
    IProjectile projectile;

    @Before
    public void createProjectile(){
        ITower tower = TowerFactory.CreateElectro(0,0);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        projectile = projectilesList.get(0);
    }

    @Test
    public void testCountVirusHit(){
        assertFalse(projectile.canRemove());
        for (int i = 0; i < 5; i++) {
            projectile.update(true,0,0);
        }

        assertTrue(projectile.canRemove());
    }

    @Test
    public void testNegativeAngleRemoval(){
        assertFalse(projectile.canRemove());
        projectile.update(true,0,-1);

        assertTrue(projectile.canRemove());
    }
}
