import static org.junit.Assert.*;

import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import org.junit.*;

/**
 * @author Daniel Persson
 */
public class TestRounds {
    private ChalmersDefense game;
    private Model model;

    @Before
    public void init() {
        game = new ChalmersDefense();
        model = new Model();
    }

    @Test
    public void testStartingRound() {
        assertEquals(1, model.getCurrentRound());
    }

}
