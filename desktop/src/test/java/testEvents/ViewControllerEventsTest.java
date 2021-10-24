package testEvents;

import com.mygdx.chalmersdefense.utilities.event.events.ViewControllerEvents;
import org.junit.Test;

import static org.junit.Assert.assertSame;


public class ViewControllerEventsTest {

    @Test
    public void testCreateViewControllerEvents() {
        ViewControllerEvents viewEvent = new ViewControllerEvents(ViewControllerEvents.Type.SHOWGAME_SCREEN);
        assertSame(viewEvent.getEventType(), ViewControllerEvents.Type.SHOWGAME_SCREEN);
    }

}

