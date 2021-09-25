package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.model.customExceptions.IllegalRoundDataException;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Joel Båtsman Hilmersson
 * A class that controlls the spawning of viruses for the different rounds
 */
public class SpawnViruses {


    //  "1|200"         spawns one virus of type "1 - Red" and then waits "200 milliseconds to next wave in round"
    //  "2*20|250|2000" spawns 20 viruses of type "2 - blue" with 250-millisecond delay and then waits 2000 milliseconds for the next wave
    //  "1/5|300|2000"  spawns a stair of virus types from 1 to 5 with a 300-millisecond delay, then waits 2000 milliseconds for the next wave
    private final String[][] spawnInfo = {{"1|3000", "2*20|250|2000", "1/5|300|2000", "5/1|300|1000", "5|1000", "5|500", "1|500", "2|1000"}};
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final List<Virus> listToAddTo;
    private boolean isSpawning = false;

    private String[] currentRound;
    private int waveIndex = 0;
    private int waveAmountSpawned;

    public SpawnViruses(List<Virus> listToAddTo) {
        this.listToAddTo = listToAddTo;
    }

    public void spawnRound(int round) {
        if (!isSpawning) {
            currentRound = spawnInfo[round - 1];
            waveIndex = 0;
            waveAmountSpawned = 0;
            isSpawning = true;
            parseRound();
        }

    }

    private synchronized void parseRound() {

        if (waveIndex < currentRound.length) {

            String[] splitedWave = currentRound[waveIndex].split("[|]");


            if (splitedWave.length == 2) {

                singleVirusSpawnHandler(splitedWave);

            } else if (splitedWave.length == 3) {

                multiVirusSpawnHandler(splitedWave);

            } else {

                throw new IllegalRoundDataException("Data error on index " + waveIndex + " in block: " + Arrays.toString(currentRound));

            }

        } else {
            waveIndex = 0;
            waveAmountSpawned = 0;
            isSpawning = false;
        }
    }

    private void singleVirusSpawnHandler(String[] splitedWave) {
        addToList(Integer.parseInt(splitedWave[0]));
        scheduleNextSpawnTime(splitedWave, 1);
        waveIndex++;
    }

    private void multiVirusSpawnHandler(String[] splitedWave) {

        if(splitedWave[0].split("[*]").length == 2) {

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
            scheduleNextSpawnTime(splitedWave, 2);
            waveIndex++;
        }
    }

    private void differentTypeVirusSpawner(String[] splitedWave) {

        String[] spawnInfo = splitedWave[0].split("[/]");

        int currentVirusType;

        if (Integer.parseInt(spawnInfo[0]) < Integer.parseInt(spawnInfo[1])){
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
            scheduleNextSpawnTime(splitedWave, 2);
            waveIndex++;
        }
    }

    private void scheduleNextSpawnTime(String[] splitedWave, int i) {
        executorService.schedule(() -> {
            try {

                parseRound();

            } catch (IllegalRoundDataException e){

                System.out.println(e.getMessage());
                waveIndex++;
                waveAmountSpawned = 0;
                scheduleNextSpawnTime(splitedWave, i);

            }
        }, Integer.parseInt(splitedWave[i]), TimeUnit.MILLISECONDS);
    }

    private void addToList(int typeOfVirus) {
        switch (typeOfVirus) {
            case 1 -> listToAddTo.add(VirusFactory.createVirusOne());     // Måste ha blivit kallat 1 gång utan executorservice, annars slutar den gå eftersom den inte kan skapa en ny textur
            case 2 -> listToAddTo.add(VirusFactory.createVirusTwo());
            case 3 -> listToAddTo.add(VirusFactory.createVirusThree());
            case 4 -> listToAddTo.add(VirusFactory.createVirusFour());
            case 5 -> listToAddTo.add(VirusFactory.createVirusFive());
            default -> throw new IllegalRoundDataException("Data error on index " + waveIndex + " in block: " + Arrays.toString(currentRound));
        }
    }


}
