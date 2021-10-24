package testEvents;

import com.mygdx.chalmersdefense.utilities.event.concreteEvents.ViewControllerEvents;
import org.junit.Test;

import static org.junit.Assert.assertSame;


/**
 * @author Elin Forsberg
 *
 * Test class for ViewControllerEvents
 */
public class ViewControllerEventsTest {

    @Test
    public void testCreateViewControllerEvents() {
        ViewControllerEvents viewEvent = new ViewControllerEvents(ViewControllerEvents.Type.SHOWGAME_SCREEN);
        assertSame(viewEvent.getEventType(), ViewControllerEvents.Type.SHOWGAME_SCREEN);
    }

}

