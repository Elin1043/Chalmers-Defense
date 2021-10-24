package testTowers;


import com.mygdx.chalmersdefense.model.modelUtilities.PathRectangle;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;


/**
 * @author Elin Forsberg
 * <p>
 * Test class for TowerFactory
 */
public class TestTowerFactory {

    @Test
    public void testCreateSmurf() {
        ITower tower = TowerFactory.createSmurf(0, 0);
        assertSame("IT-Smurf", tower.getName());
    }

    @Test
    public void testCreateChemist() {
        List<IProjectile> list = new ArrayList<>();
        ITower tower = TowerFactory.createChemist(0, 0, list);
        assertSame("Chemist", tower.getName());
    }

    @Test
    public void testCreateHacker() {
        List<IProjectile> pList = new ArrayList<>();
        ITower tower = TowerFactory.createHacker(0, 0, pList);
        assertSame("Hackerman", tower.getName());
    }

    @Test
    public void testCreateElectro() {
        ITower tower = TowerFactory.createElectro(0, 0);
        assertSame("Electroman", tower.getName());
    }

    @Test
    public void testCreateMech() {
        List<ITower> list = new ArrayList<>();
        List<ITower> listToAdd = new ArrayList<>();
        List<PathRectangle> rectangles = new ArrayList<>();
        ITower tower = TowerFactory.createMech(0, 0,listToAdd, list, rectangles);
        assertSame("Mechoman", tower.getName());
    }

    @Test
    public void testCreateEco() {
        ITower tower = TowerFactory.createEco(0, 0, null);
        assertSame("Economist", tower.getName());
    }
}
