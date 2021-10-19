package com.mygdx.chalmersdefense.model.powerUps;


import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

/**
 * @author Elin Forsberg
 * Factory for power-ups
 *
 * 2021-10-15 Modified by Joel Båtsman Hilmersson: Factory now only returns a list <br>
 */
public abstract class PowerUpFactory {

    /**
     * Create a powerUp of the type MaskedUp
     * @param allTowers towers to power up
     * @return new MaskedUp object
     */
    private static IPowerUp createMaskedUpPowerUp(List<ITower> allTowers) {
        return new MaskedUp(allTowers);
    }

    /**
     * Create a powerUp of the type CleanHands
     * @return new CleanHands object
     */
    private static IPowerUp createCleanHandsPowerUp() {
        return new CleanHands();
    }

    /**
     * Create a powerUp of the type Vaccinated
     * @param viruses viruses to hurt
     * @return new Vaccinated object
     */
    private static IPowerUp createVaccinatedPowerUp(List<IVirus> viruses) {
        return new Vaccinated(viruses);
    }

    /**
     * Creates a list of all different power-ups
     * @param allTowers towers to power up
     * @param viruses viruses to hurt
     * @return the list with all power-ups
     */
    public static List<IPowerUp> createPowerUps(List<ITower> allTowers, List<IVirus> viruses) {
        return List.of(createCleanHandsPowerUp(), createMaskedUpPowerUp(allTowers), createVaccinatedPowerUp(viruses));
    }

}
