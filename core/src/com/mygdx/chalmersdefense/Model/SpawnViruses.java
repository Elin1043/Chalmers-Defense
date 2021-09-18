package com.mygdx.chalmersdefense.Model;


import com.mygdx.chalmersdefense.Model.CustomExceptions.IllegalRoundDataException;

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

    // TODO måste kolla så att rundinformationen innehåller rätt data format. Typ kasta exeption ifall den upptäcker fel

    private final String[][] spawnInfo = {{"1|3000", "2*20|250|2000", "1/6|300|2000", "5/1|300|1000", "5|1000", "5|500", "1|500", "2|1000"},};
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

                addToList(Integer.parseInt(splitedWave[0]));
                scheduleNextSpawnTime(splitedWave, 1);
                waveIndex++;

            } else if (splitedWave.length == 3) {

                multiVirusSpawnHandler(splitedWave);

            } else {

                waveAmountSpawned = 0;
                isSpawning = false;                 // Maybe exception instead (Some error in round coding)

            }

        } else {
            waveIndex = 0;
            waveAmountSpawned = 0;
            isSpawning = false;
        }
    }

    private void multiVirusSpawnHandler(String[] splitedWave) {
        String[] spawnInfo;

        if(splitedWave[0].split("[*]").length == 2) {
            spawnInfo = splitedWave[0].split("[*]");

            if (waveAmountSpawned < Integer.parseInt(spawnInfo[1])) {
                addToList(Integer.parseInt(spawnInfo[0]));
                waveAmountSpawned++;

                scheduleNextSpawnTime(splitedWave, 1);
            } else {
                waveAmountSpawned = 0;
                scheduleNextSpawnTime(splitedWave, 2);
                waveIndex++;
            }

        } else if (splitedWave[0].split("[/]").length == 2) {
            spawnInfo = splitedWave[0].split("[/]");
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
    }

    private void scheduleNextSpawnTime(String[] splitedWave, int i) {
        //executorService.schedule(this::parseRound, Integer.parseInt(splitedWave[i]), TimeUnit.MILLISECONDS);
        executorService.schedule(() -> {
            try {
                parseRound();
            } catch (IllegalRoundDataException e){
                System.out.println(e.getMessage());
                waveIndex++;
                parseRound();
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
            default -> throw new IllegalRoundDataException("Data error on index " + waveIndex + " in block: " + Arrays.toString(currentRound)); // waveIndex = currentRound.length;                 // Maybe exception instead
        }
    }


}
