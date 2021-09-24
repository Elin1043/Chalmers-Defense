package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.Virus;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 *
 * Interface for every targeting mode to use
 */
public interface ITargetMode {

    /**
     * Retrieves a virus based on which targeting mode used
     * @param virusInRange Viruses to choose a target from
     * @return The targeted virus
     */
    Virus getRightVirus(List<Virus> virusInRange, float towerX, float towerY);
}
