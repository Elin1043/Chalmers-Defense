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
 * @author Joel Båtsman Hilmersson
 * Test class for robot projectiles
 */
public class TestRobotProjectile {

    List<IProjectile> projectilesList = new ArrayList<>();
    IProjectile projectile;

    @Before
    public void createProjectile() {
        List<ITower> miniMechList = new ArrayList<>();
        ITower tower = TowerFactory.createMech(0, 0, miniMechList, new ArrayList<>(), new ArrayList<>());
        tower.placeTower();
        tower.changeTargetMode(true);

        while (miniMechList.size() <= 0){
            tower.update(projectilesList, 0, false);
        }

        ITower miniTower = miniMechList.get(0);

        miniTower.update(projectilesList, 0, true);
        projectile = projectilesList.get(0);
    }

    @Test
    public void testCountVirusHit() {
        assertFalse(projectile.canRemove());
        for (int i = 0; i < 3; i++) {
            projectile.update(true, 0, 0);
        }

        assertTrue(projectile.canRemove());
    }
}
