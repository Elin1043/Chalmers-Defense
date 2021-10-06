package testUtilities;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.utilities.PathRectangle;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * @author Jenny Carlsson
 *
 */

public class TestPathRectangle {

    PathRectangle pathRectangle;

    @Before
    public void init() {
        pathRectangle = new PathRectangle(1,2,3,4);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testGetX() {
        assertEquals(1, pathRectangle.getX(), 0.001);
    }

    @Test
    public void testGetY() {
        assertEquals(2, pathRectangle.getY(), 0.001);
    }

    @Test
    public void testGetHeight() {
        assertEquals(4, pathRectangle.getHeight(), 0.001);
    }

    @Test
    public void testGetWidth() {
        assertEquals(3, pathRectangle.getWidth(), 0.001);
    }

    @Test
    public void testGetSpriteKey() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("getSpriteKey should not be called from pathRectangles");
        pathRectangle.getSpriteKey();
    }

    @Test
    public void testGetAngle() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("getAngle should not be called from pathRectangles");
        pathRectangle.getAngle();
    }


}
