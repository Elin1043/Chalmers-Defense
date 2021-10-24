package com.mygdx.chalmersdefense.utilities.event;

/**
 * Interface for all the EventListeners
 * @param <T> the type of Event to listen to
 */
public interface IEventListener <T extends IEvent>{

    /**
     * Handles the posts
     * @param event the type of event
     */
    void handle(T event);
}
