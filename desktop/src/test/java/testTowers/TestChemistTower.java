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
 * Test class for ChemistTower
 */
public class TestChemistTower {

    @Test
    public void testCreateProjectile() {
        ITower tower = TowerFactory.createChemist(0, 0, new ArrayList<>());
        List<IProjectile> list = new ArrayList<>();

        tower.placeTower();

        tower.update(list, 10, true);
        assertEquals(1, list.size());
    }
}
