package testUtilities;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.utilities.GetRangeCircle;
import com.mygdx.chalmersdefense.utilities.PathRectangle;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * @author Jenny Carlsson
 *
 * Test class for GetRangeCircle
 */

public class testGetRangeCircle {

    GetRangeCircle getRangeCircle;

    @Before
    public void init(){
        getRangeCircle = new GetRangeCircle();
    }

    @Test
    public void testSetAndGetPosition(){
        getRangeCircle.updatePos(4, 3, 10);
        assertTrue((getRangeCircle.getX() == 4)
                    && (getRangeCircle.getY() == 3)
                    && (getRangeCircle.getRange() == 10));
    }

    @Test
    public void testSetAndGetColor(){
        getRangeCircle.setEnumColor(GetRangeCircle.Color.RED);
        assertSame(getRangeCircle.getColor(), GetRangeCircle.Color.RED);
    }


}
