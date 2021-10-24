package testPowerUps;

import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import com.mygdx.chalmersdefense.model.powerUps.IPowerUp;
import com.mygdx.chalmersdefense.model.powerUps.PowerUpFactory;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for testing Vaccinated power-up
 */
public class TestVaccinated {

    private IPowerUp powerUp;
    private final List<IVirus> virusList = new ArrayList<>();
    private final List<IGenericMapObject> genericMapObjectList = new ArrayList<>();

    @Before
    public void createPowerUp() {
        powerUp = PowerUpFactory.createPowerUps(virusList).get(2);
    }

    @Test
    public void testAddGraphicObject() {
        powerUp.powerUpClicked(genericMapObjectList);
        assertTrue(genericMapObjectList.size() > 0);
    }

    @Test
    public void testDamageVirus() {
        virusList.add(VirusFactory.createVirusOne());
        powerUp.powerUpClicked(genericMapObjectList);

        while (powerUp.getIsActive()) {
            powerUp.decreaseTimer();
        }

        assertTrue(virusList.get(0).isDead());
    }

    @Test
    public void testGetCost() {
        assertEquals(900, powerUp.getCost());
    }
}
