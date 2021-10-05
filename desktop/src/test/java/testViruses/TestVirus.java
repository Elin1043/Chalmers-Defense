package testViruses;

import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.VirusFactory;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for Virus class
 */
public class TestVirus {
    Path p = new ClassicPath();

    @Test
    public void testVirusGetHit(){
        IVirus v = VirusFactory.createVirusTwo();
        v.decreaseHealth();
        assertEquals(1, v.getLifeDecreaseAmount());
    }

    @Test
    public void testVirusUpdateSpriteKey(){
        IVirus v = VirusFactory.createVirusTwo();
        assertEquals("virus2", v.getSpriteKey());
        v.decreaseHealth();
        assertEquals("virus1", v.getSpriteKey());
    }

    @Test
    public void testVirusIsDead(){
        IVirus v = VirusFactory.createVirusOne();
        assertFalse(v.isDead());
        v.decreaseHealth();
        assertTrue(v.isDead());
    }

    @Test
    public void testUpdateVirus(){
        IVirus v = VirusFactory.createVirusOne();
        v.update();
        assertTrue(v.getTotalDistanceTraveled() > 0);
    }

    @Test
    public void testDifferentSpeedVirus(){
        IVirus v1 = VirusFactory.createVirusOne();
        IVirus v2 = VirusFactory.createVirusTwo();
        v1.update();
        v2.update();
        assertTrue(v1.getTotalDistanceTraveled() < v2.getTotalDistanceTraveled());
    }

    @Test
    public void testGetVirusAngle(){
        IVirus v = VirusFactory.createVirusOne();
        assertEquals(0, v.getAngle(), 0.00000000001);
    }

    @Test
    public void testGetXPosition(){
        IVirus v = VirusFactory.createVirusOne();
        assertEquals(v.getX(), p.getWaypoint(0).getX() - v.getWidth()/2, 0.0);
    }

    @Test
    public void testGetYPosition(){
        IVirus v = VirusFactory.createVirusOne();
        assertEquals(v.getY(), p.getWaypoint(0).getY() - v.getHeight()/2, 0.0);
    }

}
