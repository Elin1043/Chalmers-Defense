package com.mygdx.chalmersdefense.model.modelUtilities.events;

import com.mygdx.chalmersdefense.utilities.event.IEvent;

/**
 * @author Elin Forsberg
 * Event class used by the EventBus in the model
 */
public class ModelEvents implements IEvent {

    private final EventType eventType; // The types of event this class can handle
    private int amount = 0;     // Optional value for use by the event caller

    /**
     * The different events this class handles
     */
    public enum EventType {
        ADDMONEYTOPLAYER,
        REMOVEMONEYFROMPLAYER,
        DECREASELIFEOFPLAYER,
        UPDATEMODEL
    }

    /**
     * Creates the ModelEvent for use in the EventBus
     *
     * @param eventType the type of event
     * @param amount    optional parameter for use event caller to send data
     */
    public ModelEvents(EventType eventType, int... amount) {
        this.eventType = eventType;
        if (amount.length > 0) {
            this.amount = amount[0];
        }

    }

    /**
     * Returns the type of event this class represents
     *
     * @return the type of event
     */
    public EventType getEventType() {
        return eventType;
    }

    /**
     * Returns the data connected to the event
     *
     * @return the data
     */
    public int getAmount() {
        return amount;
    }
}
