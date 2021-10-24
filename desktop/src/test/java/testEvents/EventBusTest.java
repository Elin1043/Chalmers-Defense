package testEvents;

import com.mygdx.chalmersdefense.utilities.event.EventBus;
import com.mygdx.chalmersdefense.utilities.event.IEvent;
import com.mygdx.chalmersdefense.utilities.event.IEventListener;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Elin Forsberg
 * <p>
 * Test class for EventBus
 */
public class EventBusTest {

    private EventBus eventBus;

    @Before
    public void setUp() {
        eventBus = new EventBus();
    }

    @Test
    public void singleListenerCalled() {
        HelloEvent event = new HelloEvent();
        TestEventListener<HelloEvent> listener = new TestEventListener<>();
        eventBus.listenFor(HelloEvent.class, listener);

        eventBus.emit(event);

        assertTrue(listener.listenerCalled);
    }

    @Test
    public void multipleListenerCalled() {
        HelloEvent event = new HelloEvent();
        TestEventListener<HelloEvent> listener1 = new TestEventListener<>();
        TestEventListener<HelloEvent> listener2 = new TestEventListener<>();
        eventBus.listenFor(HelloEvent.class, listener1);
        eventBus.listenFor(HelloEvent.class, listener2);

        eventBus.emit(event);

        assertTrue(listener1.listenerCalled);
        assertTrue(listener2.listenerCalled);
    }


    private static class TestEventListener<T extends IEvent> implements IEventListener<T> {
        boolean listenerCalled;

        @Override
        public void handle(T event) {
            listenerCalled = true;
        }
    }

    private static class HelloEvent implements IEvent {
    }


}