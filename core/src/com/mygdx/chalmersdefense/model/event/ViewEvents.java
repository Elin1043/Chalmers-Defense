package com.mygdx.chalmersdefense.model.event;

public class ViewEvents implements IEvent {
    private final Type eventType;

    public enum Type{
        SHOWMAIN_SCREEN,
        SHOWGAME_SCREEN
    }
    public ViewEvents(Type eventType) {
        this.eventType = eventType;
    }

    public Type getEventType() {
        return eventType;
    }
}
