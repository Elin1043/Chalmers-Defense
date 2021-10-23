package testPowerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.powerUps.IPowerUp;
import com.mygdx.chalmersdefense.model.powerUps.PowerUpFactory;
import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for testing PowerUp
 */
public class TestPowerUp {

    private IPowerUp powerUp;
    private List<IVirus> virusList = new ArrayList<>();
    private List<IGenericMapObject> genericMapObjectList = new ArrayList<>();

    @Before
    public void createPowerUp(){
        powerUp = PowerUpFactory.createPowerUps(virusList).get(0);
    }

    @Test
    public void testPowerUpClicked(){
        powerUp.powerUpClicked(genericMapObjectList);
        assertTrue(powerUp.getIsActive());
    }

    @Test
    public void testDecreaseTimer(){
        powerUp.powerUpClicked(genericMapObjectList);

        for (int i = 0; i < 10000; i++){
            powerUp.decreaseTimer();
        }

        assertFalse(powerUp.getIsActive());
    }

    @Test
    public void testGetTimer(){
        assertEquals(-1, powerUp.getTimer());

        powerUp.powerUpClicked(genericMapObjectList);

        assertEquals(2, powerUp.getTimer());

        while (powerUp.getIsActive()){
            powerUp.decreaseTimer();
        }

        assertEquals(2, powerUp.getTimer());
    }

    @Test
    public void testAddGraphicObject(){
        powerUp.powerUpClicked(genericMapObjectList);
        assertTrue(genericMapObjectList.size() > 0);
    }
}
