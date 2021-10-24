package testModelClasses;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.Model;
import com.mygdx.chalmersdefense.utilities.Preferences;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Daniel Persson
 */
public class TestRounds {
    LwjglApplication app = new LwjglApplication(new ChalmersDefense());
    Preferences preferences = new Preferences();
    Model model;

    @Before
    public void init() {
        preferences.putBoolean("autoplay", false);
        model = new Model(preferences);
    }

    @Test
    public void testStartingRound() {
        assertEquals(1, model.getCurrentRound());
    }

    @Test
    public void testWinningRound() {
        assertEquals(30, model.getWinningRound());
    }

}
