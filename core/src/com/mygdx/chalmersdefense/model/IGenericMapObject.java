package com.mygdx.chalmersdefense.model;

public interface IGenericMapObject extends  IMapObject{

    /**
     * Moves the projectile in calculated direction
     */
    void update();

    /**
     * Return if current projectile can be removed
     *
     * @return if can be removed
     */
    boolean canRemove();
}
