package com.mygdx.chalmersdefense.utilities.event.concreteEvents;

import com.mygdx.chalmersdefense.utilities.event.IEvent;

/**
 * @author Daniel Persson
 * Event for communication between view and controller
 */
public class ViewControllerEvents implements IEvent {
    private final EventType eventType;   //EventType of the event

    /**
     * The different EventTypes
     */
    public enum EventType {
        SHOWMAIN_SCREEN,
        SHOWGAME_SCREEN
    }

    /**
     * Creates an instance of ViewControllerEvents
     *
     * @param eventType the type of event
     */
    public ViewControllerEvents(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Return the EventType
     *
     * @return the EventType
     */
    public EventType getEventType() {
        return eventType;
    }
}
