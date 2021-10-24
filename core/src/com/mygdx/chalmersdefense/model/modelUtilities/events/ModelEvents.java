package com.mygdx.chalmersdefense.model.modelUtilities.events;

import com.mygdx.chalmersdefense.utilities.event.IEvent;

public class ModelEvents implements IEvent {

    private final Type eventType;
    private int amount = 0;

    public enum Type{
        ADDMONEYTOPLAYER,
        REMOVEMONEYFROMPLAYER,
        DECREASELIFEOFPLAYER,
        UPDATEMODEL
    }
    public ModelEvents(Type eventType, int... amount) {
        this.eventType = eventType;
        if(amount.length > 0){
            this.amount = amount[0];
        }

    }

    public Type getEventType() {
        return eventType;
    }

    public int getAmount() {
        return amount;
    }
}
