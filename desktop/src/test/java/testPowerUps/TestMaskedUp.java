package testPowerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.powerUps.IPowerUp;
import com.mygdx.chalmersdefense.model.powerUps.PowerUpFactory;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for testing MaskedUp power-up
 */
public class TestMaskedUp {

    private IPowerUp powerUp;
    private List<ITower> towerList = new ArrayList<>();
    private List<IVirus> virusList = new ArrayList<>();
    private List<IGenericMapObject> genericMapObjectList = new ArrayList<>();

    @Before
    public void createPowerUp(){
        powerUp = PowerUpFactory.createPowerUps( virusList).get(1);
    }

    @Test
    public void testAddGraphicObject(){
        powerUp.powerUpClicked(genericMapObjectList);
        assertTrue(genericMapObjectList.size() > 0);
    }
}
