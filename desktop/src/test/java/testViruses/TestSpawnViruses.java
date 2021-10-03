package testViruses;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.SpawnViruses;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for SpawnViruses class
 */
public class TestSpawnViruses {


    private final List<IVirus> l = new ArrayList<>();
    private SpawnViruses sp = new SpawnViruses(l);

    @Before
    public void createNewSpawnVirus(){
        l.clear();
        sp = new SpawnViruses(l);
    }

    @Test
    public void testIsSpawning(){
        assertFalse(sp.isSpawning());
        sp.spawnRound(1);
        assertTrue(sp.isSpawning());
    }
}
