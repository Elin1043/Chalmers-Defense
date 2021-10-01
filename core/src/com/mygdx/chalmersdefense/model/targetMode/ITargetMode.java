package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.Virus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * Interface for every targeting mode to use
 *
 */
public interface ITargetMode {

    /**
     * Retrieves a virus based on which targeting mode used
     * @param virusInRange Viruses to choose a target from
     * @param towerX The x position of the tower
     * @param towerY The y position of the tower
     * @return The targeted virus
     */
    IVirus getRightVirus(List<IVirus> virusInRange, float towerX, float towerY);
}
