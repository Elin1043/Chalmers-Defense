package com.mygdx.chalmersdefense.model.powerUps;


import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

public class PowerUpFactory {

    /**
     * Create a powerUp of the type MaskedUp
     * @param allTowers towers to power up
     * @return new MaskedUp object
     */
    public static IPowerUp createMaskedUpPowerUp(List<ITower> allTowers) {
        return new MaskedUp(allTowers);
    }

    /**
     * Create a powerUp of the type CleanHands
     * @return new CleanHands object
     */
    public static IPowerUp createCleanHandsPowerUp() {
        return new CleanHands();
    }

    /**
     * Create a powerUp of the type Vaccinated
     * @param viruses viruses to hurt
     * @return new Vaccinated object
     */
    public static IPowerUp createVaccinatedPowerUp(List<IVirus> viruses) {
        return new Vaccinated(viruses);
    }

}
