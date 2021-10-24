package testUtilities;

import com.mygdx.chalmersdefense.utilities.Preferences;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Jenny Carlsson
 *
 * A test class for testing preferences
 */
public class TestPreferences {
    Preferences pref;

    @Before
    public void init(){
        pref = new Preferences();
        pref.putInteger("ThisInteger", 2);
        pref.putFloat("ThisFloat", 42);
    }

    @Test
    public void TestGetInteger(){
        assertEquals(pref.getInteger("ThisInteger"), 2, 0.0001);
    }

    @Test
    public void TestGetIFloat(){
        assertEquals(pref.getFloat("ThisFloat"), 42, 0.0001);
    }
}
