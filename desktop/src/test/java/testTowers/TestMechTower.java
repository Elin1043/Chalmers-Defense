package testTowers;

import com.mygdx.chalmersdefense.model.path.IPath;
import com.mygdx.chalmersdefense.model.path.PathFactory;
import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for MechTower
 *
 * 2021-10-11 Modified by Elin Forsberg: Changed methods to work with new MechoMan class and added tests
 */
public class TestMechTower {

    IPath path = PathFactory.createClassicPath();
    List<ITower> addToList = new ArrayList<>();
    List<ITower> towersList = new ArrayList<>();

    ITower tSmurf = TowerFactory.createSmurf(100, 200);

//    @Test
//    public void testUpdateAndCreateOneTower() {
//        towersList.add(tSmurf);
//        ITower t = TowerFactory.createMech(0, 0, addToList,towersList,path.getCollisionRectangles());
//        t.placeTower();
//
//        t.update(new ArrayList<>(), 10, true);
//        assertEquals(1, addToList.size());
//    }

    @Test
    public void testUpdateAndCreateTwoTower() {
        towersList.add(tSmurf);
        ITower t = TowerFactory.createMech(0, 0, addToList,towersList,path.getCollisionRectangles());
        t.placeTower();

        HashMap<String, Double> upgrades = new HashMap<>();
        upgrades.put("attackSpeedMul",0.2);
        upgrades.put("attackRangeMul",2.0);
        t.upgradeTower(upgrades);

        t.update(new ArrayList<>(), 10, true);
        assertEquals(2, addToList.size());
    }

    @Test
    public void testCreateProjectile() {
        HashMap<String, Double> upgrades = new HashMap<>();
        upgrades.put("attackSpeedMul",0.2);
        upgrades.put("attackRangeMul",2.0);
        towersList.add(tSmurf);
        List<ITower> addToList = new ArrayList<>();
        ITower t = TowerFactory.createMech(0, 0, addToList,towersList,path.getCollisionRectangles());
        List<IProjectile> pList = new ArrayList<>();

        t.placeTower();
        t.upgradeTower(upgrades);
        t.upgradeTower(upgrades);


        t.update(pList, 10, true);

        assertTrue(pList.size() > 0);
    }

}
