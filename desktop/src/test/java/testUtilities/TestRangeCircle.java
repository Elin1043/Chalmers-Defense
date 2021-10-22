package testUtilities;


import com.mygdx.chalmersdefense.utilities.RangeCircle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * @author Jenny Carlsson
 * <p>
 * Test class for GetRangeCircle
 */

public class TestRangeCircle {

    RangeCircle rangeCircle;

    @Before
    public void init() {
        rangeCircle = new RangeCircle(0,0,0);
    }

    @Test
    public void testSetAndGetPosition() {
        rangeCircle.updatePos(4, 3, 10);
        assertTrue((rangeCircle.getX() == 4)
                && (rangeCircle.getY() == 3)
                && (rangeCircle.getRange() == 10));
    }

    @Test
    public void testSetAndGetColor() {
        rangeCircle.setEnumColor(RangeCircle.Color.RED);
        assertSame(rangeCircle.getColor(), RangeCircle.Color.RED);
    }


}
