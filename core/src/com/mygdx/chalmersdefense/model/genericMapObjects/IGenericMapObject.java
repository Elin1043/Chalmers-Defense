package com.mygdx.chalmersdefense.model.genericMapObjects;

import com.mygdx.chalmersdefense.model.IMapObject;

/**
 * @author Elin Forsberg
 * <p>
 * Interface for GenericMapObject
 */
public interface IGenericMapObject extends IMapObject {

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
