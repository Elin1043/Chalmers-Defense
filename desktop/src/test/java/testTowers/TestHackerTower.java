package testTowers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for HackerTower
 */
public class TestHackerTower {

    @Test
    public void testCreateProjectile() {
        List<IProjectile> pList = new ArrayList<>();
        ITower t = TowerFactory.CreateHacker(0, 0, pList);
        List<IProjectile> l = new ArrayList<>();

        t.placeTower();

        t.update(l, 10, true);
        assertEquals(1, l.size());
    }
}
