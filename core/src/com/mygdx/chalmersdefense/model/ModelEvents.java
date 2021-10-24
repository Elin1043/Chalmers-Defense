package com.mygdx.chalmersdefense.model;

import com.mygdx.chalmersdefense.model.event.IEvent;

public class ModelEvents implements IEvent {

    private Type eventType;

    public enum Type{
        ADDTOPLAYER
    }
    public ModelEvents(Type eventType) {
        this.eventType = eventType;
    }

    public Type getEventType() {
        return eventType;
    }
}
