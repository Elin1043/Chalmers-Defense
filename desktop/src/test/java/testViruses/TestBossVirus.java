package testViruses;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Elin Forsberg
 * Test class for testing BossVirus
 */
public class TestBossVirus {

    @Test
    public void testVirusBossVirusDecreaseHealth() {
        List<IVirus> virusList = new ArrayList<>();
        IVirus v1 = VirusFactory.createBossVirus(virusList);

        for (int i = 0; i < 5; i++) {
            v1.update();
        }

        v1.decreaseHealth(50F);


        assertEquals(5, virusList.size());
    }

    @Test
    public void testVirusBossVirusSlowDownEffect() {
        List<IVirus> virusList = new ArrayList<>();
        IVirus v1 = VirusFactory.createBossVirus(virusList);
        IVirus v2 = VirusFactory.createBossVirus(virusList);

        v1.decreaseHealth(0.7F);
        for (int i = 0; i < 10; i++) {
            v1.update();
            v2.update();
        }

        assertTrue(v1.getTotalDistanceTraveled() < v2.getTotalDistanceTraveled());
    }
}
