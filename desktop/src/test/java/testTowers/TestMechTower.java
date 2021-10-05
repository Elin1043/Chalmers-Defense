package testTowers;

import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for MechTower
 */
public class TestMechTower {

    @Test
    public void testUpdate(){
        List<ITower> addToList = new ArrayList<>();
        ITower t = TowerFactory.CreateMeck(0, 0, addToList);
        t.placeTower();

        t.update(new ArrayList<>(), 10, true);
        assertTrue(addToList.size() > 0);
    }

}
