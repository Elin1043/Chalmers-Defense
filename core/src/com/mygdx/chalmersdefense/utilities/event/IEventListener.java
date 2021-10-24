package com.mygdx.chalmersdefense.utilities.event;

public interface IEventListener <T extends IEvent>{

    void handle(T event);
}
