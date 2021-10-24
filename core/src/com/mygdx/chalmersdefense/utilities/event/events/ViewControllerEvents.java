package com.mygdx.chalmersdefense.utilities.event.events;

import com.mygdx.chalmersdefense.utilities.event.IEvent;

public class ViewControllerEvents implements IEvent {
    private final EventType eventType;

    public enum EventType {
        SHOWMAIN_SCREEN,
        SHOWGAME_SCREEN
    }
    public ViewControllerEvents(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }
}
