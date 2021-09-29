package testViruses;

import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.viruses.Virus;
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
        Virus v = new Virus(5, p);
        v.decreaseHealth();
        assertEquals(4, v.getLifeDecreaseAmount());
    }

    @Test
    public void testVirusUpdateSpriteKey(){
        Virus v = new Virus(5, p);
        assertEquals("virus5", v.getSpriteKey());
        v.decreaseHealth();
        assertEquals("virus4", v.getSpriteKey());
    }
}
