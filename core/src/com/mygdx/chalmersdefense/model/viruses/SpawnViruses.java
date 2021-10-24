package com.mygdx.chalmersdefense.model.viruses;


import java.util.Arrays;
import java.util.List;
import java.util.Random;


/**
 * @author Joel Båtsman Hilmersson
 * A class that controlls the spawning of viruses for the different rounds
 * <p>
 * 2021-10-20 Modified by Elin Forsberg: Changed the spawnInfo to be more correct for each round
 */
final public class SpawnViruses {

    // The time steps are calculated using model update time. Therefore, every timestep in this class is around 5 milliseconds in real time
    //  "1|200"         spawns one virus of type "1 - Red" and then waits 1000 milliseconds to next wave in round
    //  "2*20|250|400" spawns 20 viruses of type "2 - Blue" with 1250-millisecond delay and then waits 2000 milliseconds for the next wave
    //  "1/5|300|400"  spawns a stair of virus types from 1 to 5 with a 1500-millisecond delay, then waits 2000 milliseconds for the next wave
    private final String[][] spawnInfo = {      // The round Spawn info for the game
            /* 1  */    {"1*20|50|60"},
            /* 2  */    {"1*15|40|400", "1*20|50|60"},
            /* 3  */    {"1*25|50|200", "2*5|75|60"},
            /* 4  */    {"1*15|50|300", "1*15|50|200", "1*5|10|200", "2*18|75|60"},
            /* 5  */    {"1*5|50|150", "2*27|100|60"},
            /* 6  */    {"1*15|50|200", "2*15|50|200", "3*4|50|40"},
            /* 7  */    {"1*20|35|100", "2*20|50|200", "3*5|30|40"},
            /* 8  */    {"1/3|50|0", "1/3|50|0", "1/3|50|0", "1/3|50|0", "1/3|50|0"},
            /* 9  */    {"1*10|40|100", "2*20|45|75", "3*14|100|50"},
            /* 10 */    {"1*30|80|100", "1*10|10|800", "6|100"},
            /* 11 */    {"2*50|50|400", "2*50|50|40"},
            /* 12 */    {"1*10|40|40", "2*10|40|40", "3*10|40|40", "4*10|40|40"},
            /* 13 */    {"2*15|50|200", "3*10|50|220", "4*5|70|60"},
            /* 14 */    {"2*50|70|220", "3*23|50|60"},
            /* 15 */    {"1*49|50|500", "2*15|70|400", "3*10|70|400", "4*9|70|400"},
            /* 16 */    {"1*20|50|500", "2*15|70|400", "3*12|70|400", "4*10|70|400", "5*5|100|60"},
            /* 17 */    {"3*40|50|400", "4*8|100|40"},
            /* 18 */    {"2*50|50|1000", "5*5|20|300", "5*5|20|300", "5*10|30|40"},
            /* 19 */    {"3*80|50|50", "5/1|30|50", "5/1|30|50", "5/1|30|50", "5/1|30|100"},
            /* 20 */    {"1/5|70|100", "5/1|70|100", "1/5|70|100", "5/1|70|100", "1/5|70|800", "6*2|300|40"},
            /* 21 */    {"4*40|80|400", "5*14|50|40"},
            /* 22 */    {"1*20|10|400", "1*60|25|60"},
            /* 23 */    {"1*25|35|300", "2*25|35|300", "3*25|35|300", "4*25|35|300", "5*25|35|50"},
            /* 24 */    {"3*30|50|20", "4*15|50|20", "5*20|50|40"},
            /* 25 */    {"4*20|50|400", "3*20|100|600", "5*5|50|400", "2*50|50|40"},
            /* 26 */    {"2*40|10|300", "5*20|10|200", "1*50|2|150", "5*5|50|25", "2*25|30|40"},
            /* 27 */    {"3*15|30|150", "4*30|50|200", "1*100|10|150", "5*25|5|150", "2*10|10|40"},
            /* 28 */    {"2*150|5|100", "3*75|5|100", "4*40|5|40"},
            /* 29 */    {"1*25|25|25", "2*25|25|25", "3*25|25|25", "4*25|25|25", "5*25|25|25", "5*25|25|25", "4*25|25|25", "3*25|25|25", "2*25|25|25", "1*25|25|25"},
            /* 30 */    {"6|200", "3*5|50|200", "4*30|70|250", "6|100", "1*100|10|200", "6|200", "5*25|5|200", "6|200", "2*10|20|50", "5/3|10|50", "3/5|10|50"}
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

            if (round <= spawnInfo.length) {
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

    //Starts the spawnRoundHandler
    private void startSpawnRoundHandler() {
        try {
            parseRound();
        } catch (NumberFormatException e) {
            throw new IllegalVirusSequenceDataException("Data error on index " + waveIndex + " in block: " + Arrays.toString(currentRound));
        }
    }

    //Parse the current round
    private void parseRound() {
        if (isSpawning && (waveIndex < currentRound.length)) {

            mainSpawnHandler();

        } else {
            waveIndex = 0;
            waveAmountSpawned = 0;
            isSpawning = false;
        }
    }

    //Handles the initial wave parsing
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

    //Handles when there is a single virus to be spawned
    private void singleVirusSpawnHandler(String[] splitedWave) {
        addToList(Integer.parseInt(splitedWave[0]));
        waveIndex++;
        scheduleNextSpawnTime(splitedWave, 1);
    }

    //Handles when there are multiple viruses to be spawned
    private void multiVirusSpawnHandler(String[] splitedWave) {

        if (splitedWave[0].split("[*]").length == 2) {

            sameTypeVirusSpawner(splitedWave);

        } else if (splitedWave[0].split("[/]").length == 2) {

            differentTypeVirusSpawner(splitedWave);

        }
    }

    //Handles when there are viruses of the same type to be spawned
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

    //Handles when there are different types of viruses to be spawned
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

    //Schedules the next time to spawn viruses
    private void scheduleNextSpawnTime(String[] splitedWave, int i) {
        spawnTimer = Integer.parseInt(splitedWave[i]);
    }

    //Adds the virus created to the list from Map
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
