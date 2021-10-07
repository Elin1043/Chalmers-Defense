package testTowers;


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
        ITower tower = TowerFactory.CreateSmurf(0, 0);
        assertSame("IT-Smurf", tower.getName());
    }

    @Test
    public void testCreateChemist() {
        List<IProjectile> list = new ArrayList<>();
        ITower tower = TowerFactory.CreateChemist(0, 0, list);
        assertSame("Chemist", tower.getName());
    }

    @Test
    public void testCreateHacker() {
        ITower tower = TowerFactory.CreateHacker(0, 0);
        assertSame("Hackerman", tower.getName());
    }

    @Test
    public void testCreateElectro() {
        ITower tower = TowerFactory.CreateElectro(0, 0);
        assertSame("Electroman", tower.getName());
    }

    @Test
    public void testCreateMeck() {
        List<ITower> list = new ArrayList<>();
        ITower tower = TowerFactory.CreateMeck(0, 0, list);
        assertSame("Mechoman", tower.getName());
    }

    @Test
    public void testCreateEco() {
        ITower tower = TowerFactory.CreateEco(0, 0, null);
        assertSame("Economist", tower.getName());
    }
}
