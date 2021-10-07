package testUtilities;

import com.mygdx.chalmersdefense.model.customExceptions.IllegalRoundDataException;
import org.junit.Test;

/**
 * @author Joel Båtsman Hilmersson
 * Test class for getting coverage for the exception that never should be thrown if round data is correct
 */
public class TestIllegalRoundDataException {

    @Test(expected = IllegalRoundDataException.class)
    public void testIllegalRoundDataException() {
        throw new IllegalRoundDataException("This is just to test the class and should normally never be thrown if data is correct format");
    }
}
