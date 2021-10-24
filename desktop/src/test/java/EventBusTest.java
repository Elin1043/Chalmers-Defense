import com.mygdx.chalmersdefense.model.event.EventBus;
import com.mygdx.chalmersdefense.model.event.IEvent;
import com.mygdx.chalmersdefense.model.event.IEventListener;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EventBusTest {

    private EventBus eventBus ;

    @Before
    public void setUp() throws Exception {
        eventBus = new EventBus();
    }

    @Test
    public void singleListenerCalled() {
        HelloEvent event = new HelloEvent();
        TestEventListener<HelloEvent> listener = new TestEventListener<>();
        eventBus.listenFor(HelloEvent.class,listener);

        eventBus.emit(event);

        assertTrue(listener.listenerCalled);
    }

    @Test
    public void multipleListenerCalled() {
        HelloEvent event = new HelloEvent();
        TestEventListener<HelloEvent> listener1 = new TestEventListener<>();
        TestEventListener<HelloEvent> listener2 = new TestEventListener<>();
        eventBus.listenFor(HelloEvent.class,listener1);
        eventBus.listenFor(HelloEvent.class,listener2);

        eventBus.emit(event);

        assertTrue(listener1.listenerCalled);
        assertTrue(listener2.listenerCalled);
    }

    @Test
    public void multipleEventTypes() {
        HelloEvent helloEvent = new HelloEvent();
        WorldEvent worldEvent = new WorldEvent();
        TestEventListener<HelloEvent> helloListener = new TestEventListener<>();
        TestEventListener<WorldEvent> worldListener = new TestEventListener<>();
        eventBus.listenFor(HelloEvent.class,helloListener);
        eventBus.listenFor(WorldEvent.class,worldListener);

        eventBus.emit(worldEvent);

        assertFalse(helloListener.listenerCalled);
        assertTrue(worldListener.listenerCalled);
    }

    private static class TestEventListener<T extends IEvent> implements IEventListener<T> {
        boolean listenerCalled;

        @Override
        public void handle(T event, int... amount) {
            listenerCalled = true;
        }
    }

    private static class HelloEvent implements IEvent{
        private String value = "Hello";
    }
    private static class WorldEvent implements IEvent{
        private String value = "World";
    }

}