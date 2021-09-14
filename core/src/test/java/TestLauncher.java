import static org.junit.Assert.*;

import com.mygdx.chalmersdefense.ChalmersDefense;
import org.junit.*;

public class TestLauncher {

    @Test
    public void testJunit(){
        ChalmersDefense l = new ChalmersDefense();

        assertEquals(10, l.testJunit(5));
        assertNotEquals(4, l.testJunit(12));

    }
}
