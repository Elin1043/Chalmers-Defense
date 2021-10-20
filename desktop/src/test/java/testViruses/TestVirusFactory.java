package testViruses;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for virus factory
 */
public class TestVirusFactory {

    @Test
    public void testCreateVirusOne() {
        IVirus v = VirusFactory.createVirusOne();
        assertEquals(1, v.getLifeDecreaseAmount());
    }

    @Test
    public void testCreateVirusTwo() {
        IVirus v = VirusFactory.createVirusTwo();
        assertEquals(2, v.getLifeDecreaseAmount());
    }

    @Test
    public void testCreateVirusThree() {
        IVirus v = VirusFactory.createVirusThree();
        assertEquals(3, v.getLifeDecreaseAmount());
    }

    @Test
    public void testCreateVirusFour() {
        IVirus v = VirusFactory.createVirusFour();
        assertEquals(4, v.getLifeDecreaseAmount());
    }

    @Test
    public void testCreateVirusFive() {
        IVirus v = VirusFactory.createVirusFive();
        assertEquals(5, v.getLifeDecreaseAmount());
    }

    @Test
    public void testCreateBossVirus() {
        List<IVirus> virusList = new ArrayList<>();
        IVirus v = VirusFactory.createBossVirus(virusList);
        assertEquals(50, v.getLifeDecreaseAmount());
    }
}
