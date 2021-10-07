package testUtilities;


import com.mygdx.chalmersdefense.utilities.GetRangeCircle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * @author Jenny Carlsson
 * <p>
 * Test class for GetRangeCircle
 */

public class TestGetRangeCircle {

    GetRangeCircle getRangeCircle;

    @Before
    public void init() {
        getRangeCircle = new GetRangeCircle();
    }

    @Test
    public void testSetAndGetPosition() {
        getRangeCircle.updatePos(4, 3, 10);
        assertTrue((getRangeCircle.getX() == 4)
                && (getRangeCircle.getY() == 3)
                && (getRangeCircle.getRange() == 10));
    }

    @Test
    public void testSetAndGetColor() {
        getRangeCircle.setEnumColor(GetRangeCircle.Color.RED);
        assertSame(getRangeCircle.getColor(), GetRangeCircle.Color.RED);
    }


}
