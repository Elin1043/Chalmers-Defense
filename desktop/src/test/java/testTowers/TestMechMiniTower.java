package testTowers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 */
public class TestMechMiniTower {

    @Test
    public void testCreateProjectile() {
        List<ITower> addToList = new ArrayList<>();
        ITower t = TowerFactory.CreateMech(0, 0, addToList);
        List<IProjectile> pList = new ArrayList<>();

        t.placeTower();
        t.update(new ArrayList<>(), 10, true);

        for (ITower tower : addToList) {
            tower.update(pList, 10, true);
        }

        assertTrue(pList.size() > 0);
    }
}
