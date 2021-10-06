package testProjectiles;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for MoneyProjectile
 */
public class testMoneyProjectile {

    @Test
    public void testMoneyUpdate(){
        IProjectile p = ProjectileFactory.createMoneyProjectile(1,1,2);

        for (int i = 0; i < 10000; i++){                    // After this amount of time, it should be able to be removed
            p.update(false, 0, 10);
        }

        assertTrue(p.canRemove());
    }

    @Test
    public void testHaveHitBefore(){
        IProjectile p = ProjectileFactory.createMoneyProjectile(1,1,2);
        assertTrue(p.haveHitBefore(0)); // Should always return true to not do damage to viruses
    }
}
