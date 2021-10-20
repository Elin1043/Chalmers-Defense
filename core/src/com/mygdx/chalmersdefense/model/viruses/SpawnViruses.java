package com.mygdx.chalmersdefense.model.viruses;


import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * @author Joel Båtsman Hilmersson
 * A class that controlls the spawning of viruses for the different rounds
 */
final public class SpawnViruses {


    //  "1|200"         spawns one virus of type "1 - Red" and then waits "200 milliseconds to next wave in round"
    //  "2*20|250|2000" spawns 20 viruses of type "2 - blue" with 250-millisecond delay and then waits 2000 milliseconds for the next wave
    //  "1/5|300|2000"  spawns a stair of virus types from 1 to 5 with a 300-millisecond delay, then waits 2000 milliseconds for the next wave
    private final String[][] spawnInfo = {
            {"1*20|50|600"},                                                                                            //1
            {"1*15|150|400", "1*20|50|600"},                                                                            //2
            {"1*25|50|300", "2*5|100|600"},                                                                             //3
            {"1*15|50|400","1*15|100|600", "1*5|50|400","2*18|100|600"},                                                //4
            {"1*5|50|500","2*27|100|600"},                                                                              //5
            {"1*15|50|400","2*15|50|600", "3*4|50|400"},                                                                //6
            {"1*20|50|600","2*20|100|600", "3*5|100|400"},                                                              //7
            {"1/3|50|0", "1/3|50|0", "1/3|50|0", "1/3|50|0", "1/3|50|0"},                                               //8
            {"1*10|50|500","2*20|100|300","3*14|150|500"},                                                              //9
            {"1*30|100|1000", "6|100"},                                                                                 //10
            {"2*50|50|1000", "2*50|50|400"},                                                                            //11
            {"1*10|50|400", "2*10|50|400", "3*10|50|400", "4*10|50|400"},                                               //12
            {"2*15|50|300", "3*10|50|600", "4*5|100|600"},                                                              //13
            {"2*50|100|400", "3*23|50|600"},                                                                            //14
            {"1*49|50|500","2*15|100|600","3*10|100|600","4*9|100|600"},                                                //15
            {"1*20|50|500","2*15|100|600","3*12|100|600","4*10|100|600","5*5|100|600"},                                 //16
            {"3*40|50|600","4*8|100|600"},                                                                              //17
            {"2*50|50|1200", "5*5|50|400", "5*5|50|400", "5*10|50|400"},                                                //18
            {"3*80|50|500"},                                                                                            //19
            {"1/5|100|100", "5/1|100|100", "1/5|100|100", "5/1|100|100", "1/5|100|800", "6*2|300|400"},                 //20
            {"4*40|150|400", "5*14|50|400"},                                                                            //21
            {"1*15|150|400", "1*20|50|600"},                                                                            //22
            {"1*25|50|500", "2*25|100|500", "3*25|50|500", "4*25|100|500", "5*25|100|500"},                             //23
            {"3*30|50|200","4*15|100|600", "5*20|50|400"},                                                              //24
            {"4*20|50|400","3*20|100|600", "5*5|50|400", "2*50|50|400"},                                                //25
            {"2*25|50|500","5*20|100|600", "1*50|100|400", "5*5|50|400", "2*25|50|400"},                                //26
            {"3*5|50|400","4*30|100|600", "1*100|50|400", "5*25|50|400", "2*10|50|400"},                                //27
            {"2*100|50|400","3*50|100|600", "4*50|50|400"},                                                             //28
            {"1*25|50|500", "2*25|100|500", "3*25|50|500", "4*25|100|500", "5*25|100|500","5*25|100|500", "4*25|100|500", "3*25|50|500", "2*25|100|500" , "1*25|50|500"},  //29
            {"6|200","3*5|50|400","4*30|100|600","6|200", "1*100|50|400", "6|200","5*25|50|400", "6|200","2*10|50|400"} //30
    };

    private final List<IVirus> listToAddVirusesTo;   // What list to add the spawned viruses to
    private boolean isSpawning = false; // If the class is currently spawning viruses

    private String[] currentRound;  // Round data representing current round
    private int waveIndex = 0;      // What block/wave the spawner is in the round data
    private int waveAmountSpawned;  // If multiple virus is spawned from single block, how many have already spawned

    private int spawnTimer;      //Amount of time between the rounds (decrements with updateCycle)

    /**
     * Creates one instance of spawnViruses class
     *
     * @param listToAddTo The list to spawn viruses in
     */
    public SpawnViruses(List<IVirus> listToAddTo) {
        this.listToAddVirusesTo = listToAddTo;
    }

    /**
     * Resets currently spawning queue and resets wave index
     */
    public void resetSpawnViruses() {
        isSpawning = false;
    }

    /**
     * Returns if the class currently spawns viruses
     *
     * @return the spawning status
     */
    public boolean isSpawning() {
        return isSpawning;
    }

    /**
     * Method to start the virus spawning sequence for a given round
     *
     * @param round the round to spawn
     */
    public void spawnRound(int round) {
        if (!isSpawning) {

            if (round < spawnInfo.length) {
                currentRound = spawnInfo[round - 1];        // If there exist specific round data, Takes it
            } else {
                Random rand = new Random();
                currentRound = spawnInfo[rand.nextInt(spawnInfo.length - 1)];     // Otherwise, randomizes a round from round data
            }

            waveIndex = 0;
            waveAmountSpawned = 0;
            isSpawning = true;
            startSpawnRoundHandler();
        }
    }

    /**
     * Decrease the spawnTimer
     * If spawnTimer = 0, start next round
     */
    public void decrementSpawnTimer() {
        if (spawnTimer <= 0) {
            startSpawnRoundHandler();
        } else {
            spawnTimer--;
        }
    }

    private void startSpawnRoundHandler() {
        try {
            parseRound();
        } catch (NumberFormatException e) {
            throw new IllegalVirusSequenceDataException("Data error on index " + waveIndex + " in block: " + Arrays.toString(currentRound));
        }
    }

    private void parseRound() {
        if (isSpawning && (waveIndex < currentRound.length)) {

            mainSpawnHandler();

        } else {
            waveIndex = 0;
            waveAmountSpawned = 0;
            isSpawning = false;
        }
    }

    private void mainSpawnHandler() {
        String[] splitedWave = currentRound[waveIndex].split("[|]");

        if (splitedWave.length == 2) {
            singleVirusSpawnHandler(splitedWave);
        } else if (splitedWave.length == 3) {
            multiVirusSpawnHandler(splitedWave);
        } else {
            throw new IllegalVirusSequenceDataException("Data error on index " + waveIndex + " in block: " + Arrays.toString(currentRound));
        }
    }

    private void singleVirusSpawnHandler(String[] splitedWave) {
        addToList(Integer.parseInt(splitedWave[0]));
        waveIndex++;
        scheduleNextSpawnTime(splitedWave, 1);
    }

    private void multiVirusSpawnHandler(String[] splitedWave) {

        if (splitedWave[0].split("[*]").length == 2) {

            sameTypeVirusSpawner(splitedWave);

        } else if (splitedWave[0].split("[/]").length == 2) {

            differentTypeVirusSpawner(splitedWave);

        }
    }

    private void sameTypeVirusSpawner(String[] splitedWave) {

        String[] spawnInfo = splitedWave[0].split("[*]");

        if (waveAmountSpawned < Integer.parseInt(spawnInfo[1])) {
            addToList(Integer.parseInt(spawnInfo[0]));
            waveAmountSpawned++;

            scheduleNextSpawnTime(splitedWave, 1);
        } else {
            waveAmountSpawned = 0;
            waveIndex++;
            scheduleNextSpawnTime(splitedWave, 2);
        }
    }

    private void differentTypeVirusSpawner(String[] splitedWave) {

        String[] spawnInfo = splitedWave[0].split("[/]");

        int currentVirusType;

        if (Integer.parseInt(spawnInfo[0]) < Integer.parseInt(spawnInfo[1])) {
            currentVirusType = Integer.parseInt(spawnInfo[0]) + waveAmountSpawned;
        } else {
            currentVirusType = Integer.parseInt(spawnInfo[0]) - waveAmountSpawned;
        }

        addToList(currentVirusType);

        if (currentVirusType != Integer.parseInt(spawnInfo[1])) {
            waveAmountSpawned++;
            scheduleNextSpawnTime(splitedWave, 1);
        } else {
            waveAmountSpawned = 0;
            waveIndex++;
            scheduleNextSpawnTime(splitedWave, 2);
        }
    }

    private void scheduleNextSpawnTime(String[] splitedWave, int i) {
        spawnTimer = Integer.parseInt(splitedWave[i]);
    }

    private void addToList(int typeOfVirus) {
        switch (typeOfVirus) {
            case 1 -> listToAddVirusesTo.add(VirusFactory.createVirusOne());
            case 2 -> listToAddVirusesTo.add(VirusFactory.createVirusTwo());
            case 3 -> listToAddVirusesTo.add(VirusFactory.createVirusThree());
            case 4 -> listToAddVirusesTo.add(VirusFactory.createVirusFour());
            case 5 -> listToAddVirusesTo.add(VirusFactory.createVirusFive());
            case 6 -> listToAddVirusesTo.add(VirusFactory.createBossVirus(listToAddVirusesTo));
            default -> throw new IllegalVirusSequenceDataException("Data error on index " + waveIndex + " in block: " + Arrays.toString(currentRound));
        }
    }


}
