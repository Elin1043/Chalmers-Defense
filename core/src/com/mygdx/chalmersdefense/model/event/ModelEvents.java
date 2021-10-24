package com.mygdx.chalmersdefense.model.event;

public class ModelEvents implements IEvent {

    private final Type eventType;
    private final int amount;

    public enum Type{
        ADDTOPLAYER,
        REMOVEFROMPLAYER,
        DECREASELIFE
    }
    public ModelEvents(Type eventType, int amount) {
        this.eventType = eventType;
        this.amount = amount;
    }

    public Type getEventType() {
        return eventType;
    }

    public int getAmount() {
        return amount;
    }
}
