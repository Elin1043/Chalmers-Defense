package testTowers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestTowerFactory {

    @Test
    public void testCreateSmurf(){
        ITower tower = TowerFactory.CreateSmurf(0,0);
        assertTrue(tower.getName() == "IT-Smurf");
    }
    @Test
    public void testCreateChemist(){
        List<IProjectile> list = new ArrayList<>();
        ITower tower = TowerFactory.CreateChemist(0,0, list);
        assertTrue(tower.getName() == "Chemist");
    }
    @Test
    public void testCreateHacker(){
        ITower tower = TowerFactory.CreateHacker(0,0);
        assertTrue(tower.getName() == "Hackerman");
    }
    @Test
    public void testCreateElectro(){
        ITower tower = TowerFactory.CreateElectro(0,0);
        assertTrue(tower.getName() == "Electroman");
    }
    @Test
    public void testCreateMeck(){
        List<ITower> list = new ArrayList<>();
        ITower tower = TowerFactory.CreateMeck(0,0,list);
        assertTrue(tower.getName() == "Mechoman");
    }
    @Test
    public void testCreateEco(){
        ITower tower = TowerFactory.CreateEco(0,0, null);
        assertTrue(tower.getName() == "Economist");
    }
}
