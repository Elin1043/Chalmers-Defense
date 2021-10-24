package com.mygdx.chalmersdefense.utilities.event.events;

import com.mygdx.chalmersdefense.utilities.event.IEvent;

public class ViewControllerEvents implements IEvent {
    private final Type eventType;

    public enum Type{
        SHOWMAIN_SCREEN,
        SHOWGAME_SCREEN
    }
    public ViewControllerEvents(Type eventType) {
        this.eventType = eventType;
    }

    public Type getEventType() {
        return eventType;
    }
}
