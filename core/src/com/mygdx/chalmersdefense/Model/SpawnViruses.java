package com.mygdx.chalmersdefense.Model;

import java.util.List;

/**
 * @author Joel Båtsman Hilmersson
 * A class that controlls the spawning of viruses for the different rounds
 */
public class SpawnViruses {

    private final String[][] spawnInfo = {{"1|100", "1|80", "1|60", "1|40", "1|40", "1|60", "1|80", "1|100"}};
    private final List<Virus> listToAddTo;

    String[] currentRound;
    private int waveIndex = 0;

    public SpawnViruses(List<Virus> listToAddTo) {
        this.listToAddTo = listToAddTo;
    }

    public void spawnRound(int round, List<Virus> listToAddTo) {
        currentRound = spawnInfo[round - 1];
        waveIndex = 0;

    }

    private void parseRound(){
        String[] splitedWave = currentRound[waveIndex].split("[|]");

//        switch (splitedWave[0]){
//            //case 1 -> listToAddTo.add(VirusFactory.createVirusOne());
//        }
    }
}
