package testGenericMapObjects;

import com.mygdx.chalmersdefense.model.genericMapObjects.GenericMapObjectFactory;
import com.mygdx.chalmersdefense.model.genericMapObjects.IGenericMapObject;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for testing GenericMapObject
 */
public class TestGenericMapObject {
    IGenericMapObject object;


    @Before
    public void createGenericMapObject() {
        object = GenericMapObjectFactory.createBubbles(0, 0, 0);
    }

    @Test
    public void testUpdate() {
        object.update();
        assertTrue(object.getX() > 0);
    }

    @Test
    public void testGetPositionAndAngle() {
        assertEquals(0, object.getX(), 0.00001);
        assertEquals(0, object.getY(), 0.00001);
        assertEquals(0, object.getAngle(), 0.00001);
    }

    @Test
    public void testGetSpriteKey() {
        assertEquals("bubbles", object.getSpriteKey());
    }

    @Test
    public void testGetWidthAndHeight() {
        int height = 0;
        int width = 0;

        try {
            BufferedImage towerImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("genericMapObjects/bubbles.png")));
            height = towerImage.getHeight();
            width = towerImage.getWidth();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        assertEquals(height, object.getHeight(), 0.0000001);
        assertEquals(width, object.getWidth(), 0.0000001);
    }

    @Test
    public void testCanRemove() {
        for (int i = 0; i < 10000; i++) {
            object.update();
        }

        assertTrue(object.canRemove());
    }
}
