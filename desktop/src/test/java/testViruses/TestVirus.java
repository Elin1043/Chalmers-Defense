package testViruses;

import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.viruses.Virus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;

public class TestVirus {
    Path p = new ClassicPath();

    @Test
    public void testVirusGetHit(){
        System.out.println(System.getProperty("java.class.path"));
        Virus v = new Virus(2, p);
        v.decreaseHealth();
        assertEquals(1, v.getLifeDecreaseAmount());
    }

    @Test
    public void testVirusUpdateSpriteKey(){
        Virus v = new Virus(2, p);
        assertEquals("virus2", v.getSpriteKey());
        v.decreaseHealth();
        assertEquals("virus1", v.getSpriteKey());
    }

    @Test
    public void testVirusIsDead(){
        Virus v = new Virus(1, p);
        assertFalse(v.isDead());
        v.decreaseHealth();
        assertTrue(v.isDead());
    }

    @Test
    public void testUpdateVirus(){
        Virus v = new Virus(1, p);
        v.update();
        assertTrue(v.getTotalDistanceTraveled() > 0);
    }

    @Test
    public void testDifferentSpeedVirus(){
        Virus v1 = new Virus(1, p);
        Virus v2 = new Virus(2, p);
        v1.update();
        v2.update();
        assertTrue(v1.getTotalDistanceTraveled() < v2.getTotalDistanceTraveled());
    }

    @Test
    public void testGetVirusAngle(){
        Virus v = new Virus(1, p);
        assertEquals(0, v.getAngle(), 0.00000000001);
    }

    @Test(expected = NullPointerException.class)
    public void testValidVirus(){
        Virus v = new Virus(-1, p);
    }

    @Test
    public void testGetXPosition(){
        Virus v = new Virus(1, p);
        assertEquals(v.getX(), p.getWaypoint(0).getX() - v.getWidth()/2, 0.0);
    }

    @Test
    public void testGetYPosition(){
        Virus v = new Virus(1, p);
        assertEquals(v.getY(), p.getWaypoint(0).getY() - v.getHeight()/2, 0.0);
    }

}
