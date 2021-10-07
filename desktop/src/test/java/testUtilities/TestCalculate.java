package testUtilities;

import com.mygdx.chalmersdefense.utilities.Calculate;
import com.mygdx.chalmersdefense.utilities.PathRectangle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jenny Carlsson
 *
 * Test class for PathRectangle
 */
public class TestCalculate {

    @Test
    public void testCalculateIntersects(){
        PathRectangle rectangle = new PathRectangle(2,2,-1,-1);
        PathRectangle rectangle2 = new PathRectangle(100,100,-2,-1);
        boolean bool = Calculate.objectsIntersects(rectangle, rectangle2);
        assertFalse(bool);
    }


}
