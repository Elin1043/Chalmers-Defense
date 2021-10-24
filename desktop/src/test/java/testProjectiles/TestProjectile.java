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
 * <p>
 * Test class for Projectile
 */
public class TestProjectile {
    List<IProjectile> projectilesList = new ArrayList<>();
    IProjectile projectile;

    @Before
    public void createProjectile() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        projectile = projectilesList.get(0);
    }

    @Test
    public void testProjectileUpdate() {
        projectile.update(false, 0, 0);
        assertTrue(projectile.getX() > 0);
        assertEquals(0, projectile.getY(), 0.0);
    }

    @Test
    public void testProjectileVirusHit() {
        projectile.update(true, 0, 0);
        assertTrue(projectile.canRemove());
    }

    @Test
    public void testProjectileGetSpriteKey() {
        assertEquals(projectile.getSpriteKey(), "smurfProjectile1");
    }

    @Test
    public void testProjectileGetAngle() {
        assertEquals(0, projectile.getAngle(), 0.0);
    }

    @Test
    public void testProjectileGetWidthAndHeight() {
        assertTrue(projectile.getWidth() > 0);
        assertTrue(projectile.getHeight() > 0);
    }

    @Test
    public void testProjectileHaveHitVirusBefore() {
        assertFalse(projectile.haveHitBefore(0));
    }

    @Test
    public void testProjectileSetAngle() {
        ITower tower = TowerFactory.createElectro(0, 0);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        projectile = projectilesList.get(1);
        projectile.update(true, 0, 10);
        assertEquals(10, projectile.getAngle(), 0.0);
    }
}
