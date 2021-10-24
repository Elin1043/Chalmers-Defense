package com.mygdx.chalmersdefense.utilities.event;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Eventbus to be used for communication between the different classes and modules
 */
public class EventBus {

    private final Map<Class, List<IEventListener>> listeners = new HashMap<>(); //Map of all the listeners


    /**
     * Creates a post to send to the listeners
     *
     * @param event the type of Event
     */
    public void emit(IEvent event) {
        Class<? extends IEvent> eventClass = event.getClass();
        List<IEventListener> eventListeners = listeners.get(eventClass);

        for (IEventListener eventListener : eventListeners) {
            eventListener.handle(event);
        }
    }

    /**
     * Used to make a class listen for the different emits
     *
     * @param eventClass The eventClass to listen to
     * @param listener   The listeners handle method
     * @param <T>        the type of eventClass
     */
    public <T extends IEvent> void listenFor(Class<T> eventClass, IEventListener<T> listener) {
        if (!listeners.containsKey(eventClass)) {
            listeners.put(eventClass, new LinkedList<>());
        }
        listeners.get(eventClass).add(listener);
    }

}
