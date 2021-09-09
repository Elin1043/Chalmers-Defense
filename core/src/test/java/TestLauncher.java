import static org.junit.Assert.*;

import com.mygdx.chalmersdefense.Launcher;
import org.junit.*;

public class TestLauncher {

    @Test
    public void testJunit(){
        Launcher l = new Launcher();

        assertEquals(10, l.testJunit(5));
        assertNotEquals(4, l.testJunit(12));

    }
}
