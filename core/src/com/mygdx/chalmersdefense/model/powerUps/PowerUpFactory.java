package com.mygdx.chalmersdefense.model.powerUps;


import com.mygdx.chalmersdefense.model.towers.ITower;
import com.mygdx.chalmersdefense.model.viruses.IVirus;

import java.util.List;

public class PowerUpFactory {

    public static IPowerUp createMaskedUpPowerUp(List<ITower> allTowers) {
        return new MaskedUp(allTowers);
    }

    public static IPowerUp createCleanHandsPowerUp() {
        return new CleanHands();
    }

    public static IPowerUp createVaccinatedPowerUp(List<IVirus> viruses) {
        return new Vaccinated(viruses);
    }

}
