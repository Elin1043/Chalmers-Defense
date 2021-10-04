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


public class testProjectile {
    List<IProjectile> projectilesList = new ArrayList<>();
    IProjectile projectile;

    @Before
    public void createProjectile(){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        projectile = projectilesList.get(0);
    }

    @Test
    public void testProjectileUpdate(){
        projectile.update(false,0,0);
        assertTrue(projectile.getX() > 0);
        assertTrue(projectile.getY() == 0);
    }

    @Test
    public void testProjectileVirusHit(){
        projectile.update(true,0,0);
        assertTrue(projectile.canRemove());
    }

    @Test
    public void testProjectileGetSpriteKey(){
        assertEquals(projectile.getSpriteKey() , "smurfProjectile1");
    }

    @Test
    public void testProjectileGetAngle(){
        assertTrue(projectile.getAngle() == 0);
    }

    @Test
    public void testProjectileGetWidthAndHeight (){
        assertTrue(projectile.getWidth() > 0);
        assertTrue(projectile.getHeight() > 0);
    }

    @Test
    public void testProjectileHaveHitVirusBefore (){
        assertTrue(!projectile.haveHitBefore(0));
    }

    @Test
    public void testProjectileSetAngle (){
        ITower tower = TowerFactory.CreateElectro(0,0);
        tower.placeTower();
        tower.update(projectilesList, 0, true);
        projectile = projectilesList.get(1);
        projectile.update(true, 0, 10);
        assertTrue(projectile.getAngle() == 10);
    }
}
