package testProjectiles;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author Elin Forsberg
 * <p>
 * Test class for ProjectileFactory
 */
public class TestProjectileFactory {
    List<IProjectile> projectileList = new ArrayList<>();

    @Test
    public void testCreateZeroOneProjectile() {
        IProjectile projectile = ProjectileFactory.createZeroOneProjectile(0, 0, 0, 1);
        assertEquals("smurfProjectile1", projectile.getSpriteKey());
    }

    @Test
    public void testCreateAcidProjectile() {
        IProjectile projectile = ProjectileFactory.createAcidProjectile(0, 0, 0, 1, projectileList);
        assertEquals("chemistProjectile1", projectile.getSpriteKey());
    }

    @Test
    public void testCreateAcidPool() {
        IProjectile projectile = ProjectileFactory.createAcidPool(0, 0, 1);
        assertEquals("chemistAcid1", projectile.getSpriteKey());
    }

    @Test
    public void testCreateLightningProjectile() {
        IProjectile projectile = ProjectileFactory.createLightningProjectile(0, 0, 0, 1);
        assertEquals("electroProjectile1", projectile.getSpriteKey());
    }

    @Test
    public void testCreateRobotProjectile() {
        IProjectile projectile = ProjectileFactory.createRobotProjectile(0, 0, 0, 1);
        assertEquals("mechaProjectile1", projectile.getSpriteKey());
    }
}