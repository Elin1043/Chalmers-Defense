package testProjectiles;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class TestProjectileFactory {
    List<IProjectile> projectileList = new ArrayList<>();
    @Test
    public void testCreateZeroOneProjectile(){
        IProjectile projectile = ProjectileFactory.createZeroOneProjectile(0,0,0,1);
        assertTrue(Objects.equals(projectile.getSpriteKey(), "smurfProjectile1"));
    }

    @Test
    public void testCreateAcidProjectile(){
        IProjectile projectile = ProjectileFactory.createAcidProjectile(0,0,0,1, projectileList);
        assertTrue(Objects.equals(projectile.getSpriteKey(), "chemistProjectile1"));
    }

    @Test
    public void testCreateAcidPool(){
        IProjectile projectile = ProjectileFactory.createAcidPool(0,0,1);
        assertTrue(Objects.equals(projectile.getSpriteKey(), "chemistAcid1"));
    }

    @Test
    public void testCreateLightningProjectile(){
        IProjectile projectile = ProjectileFactory.createLightningProjectile(0,0,0,1);
        assertTrue(Objects.equals(projectile.getSpriteKey(), "electroProjectile1"));
    }

    @Test
    public void testCreateRobotProjectile(){
        IProjectile projectile = ProjectileFactory.createRobotProjectile(0,0,0,1);
        assertTrue(Objects.equals(projectile.getSpriteKey(), "mechaProjectile1"));
    }
}