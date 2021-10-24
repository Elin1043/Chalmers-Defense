package com.mygdx.chalmersdefense.model.event;

public interface IEventListener <T extends IEvent>{

    void handle(T event);
}
