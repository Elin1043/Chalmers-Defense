package testUtilities;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.utilities.PositionVector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * @author Jenny Carlsson
 *
 * Test Class for PositionVector
 */

public class TestPositionVector {

    PositionVector positionVector;

    @Before
    public void init() {
        positionVector = new PositionVector(2, 2);
    }

    @Test
    public void testGetAndSetX() {
        PositionVector v = positionVector.setX(4);
        assertEquals(4, v.getX(), 0.001);
    }

    @Test
    public void testGetAndSetY() {
        PositionVector v = positionVector.setY(3);
        assertEquals(3, v.getY(), 0.001);
    }

    @Test
    public void testSetVectorPos() {
        PositionVector v = positionVector.setVectorPos(5,6);
        PositionVector v2 = new PositionVector(v);
        assertEquals(6, v2.getY(), 0.001);
    }

    



}
