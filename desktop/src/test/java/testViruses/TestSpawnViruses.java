package testViruses;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.SpawnViruses;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for SpawnViruses class
 */
public class TestSpawnViruses {


    private final List<IVirus> l = new ArrayList<>();
    private SpawnViruses sp = new SpawnViruses(l);

    @Before
    public void createNewSpawnVirus() {
        l.clear();
        sp = new SpawnViruses(l);
    }

    @Test
    public void testIsSpawning() {
        assertFalse(sp.isSpawning());
        sp.spawnRound(1);
        assertTrue(sp.isSpawning());
    }

    @Test
    public void testSpawnRound() {
        sp.spawnRound(1);
        assertEquals(1, l.size());
    }

    @Test
    public void testIfIsSpawningReset() {
        sp.spawnRound(1);
        for (int i = 0; i < 100000; i++) {       // Very big number because then we can be sure the round should be finished spawning
            sp.decrementSpawnTimer();
        }
        assertFalse(sp.isSpawning());
    }

    @Test
    public void testManualIsSpawningReset() {
        sp.spawnRound(1);
        assertTrue(sp.isSpawning());
        sp.resetSpawnViruses();
        assertFalse(sp.isSpawning());
    }

    @Test
    public void testSpawnWholeRound() {
        sp.spawnRound(30);
        while (sp.isSpawning()) {
            sp.decrementSpawnTimer();
        }
        assertEquals(180, l.size());     // If the class has spawned every virus in round data. (This needs to be updated to match the correct amount)
    }

    @Test
    public void testSpawnRandomRound() {
        sp.spawnRound(100000);      // Number is over amount of predefined rounds there are. This causes random round to spawn
        assertTrue(sp.isSpawning());
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSpawnIllegalRound() {
        sp.spawnRound(-1);
    }
}
