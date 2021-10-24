package testEvents;

import com.mygdx.chalmersdefense.utilities.event.events.ViewControllerEvents;
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
        ViewControllerEvents viewEvent = new ViewControllerEvents(ViewControllerEvents.EventType.SHOWGAME_SCREEN);
        assertSame(viewEvent.getEventType(), ViewControllerEvents.EventType.SHOWGAME_SCREEN);
    }

}

