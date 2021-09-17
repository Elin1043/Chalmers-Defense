package com.mygdx.chalmersdefense.Model;


import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Joel Båtsman Hilmersson
 * A class that controlls the spawning of viruses for the different rounds
 */
public class SpawnViruses {

    private final String[][] spawnInfo = {{"1|3000", "2*20|250|2000", "3*5|300|500", "4|1000", "5|1000", "5|500", "1|500", "2|1000"},};
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private List<Virus> listToAddTo;
    private boolean isSpawning = false;

    String[] currentRound;
    private int waveIndex = 0;
    private int waveAmountSpawned;

    public SpawnViruses(List<Virus> listToAddTo) {
        this.listToAddTo = listToAddTo;
    }

    public void spawnRound(int round) {
        if (!isSpawning) {
            currentRound = spawnInfo[round - 1];
            waveIndex = 0;
            isSpawning = true;
            parseRound();
        }

    }

    private void parseRound(){

        if (waveIndex < currentRound.length) {

            String[] splitedWave = currentRound[waveIndex].split("[|]");


            if (splitedWave.length == 2) {

                addToList(splitedWave);
                scheduleNextSpawnTime(splitedWave, 1);
                waveIndex++;

            } else if (splitedWave.length == 3) {

                multiVirusSpawnHandler(splitedWave);

            }

        } else {
            waveIndex = 0;
            waveAmountSpawned = 0;
            isSpawning = false;
        }
    }

    private void multiVirusSpawnHandler(String[] splitedWave) {
        String[] spawnInfo = splitedWave[0].split("[*]");

        if (waveAmountSpawned < Integer.parseInt(spawnInfo[1])) {
            addToList(spawnInfo);
            waveAmountSpawned++;

            scheduleNextSpawnTime(splitedWave, 1);
        } else {
            waveAmountSpawned = 0;
            scheduleNextSpawnTime(splitedWave, 2);
            waveIndex++;
        }
    }

    private void scheduleNextSpawnTime(String[] splitedWave, int i) {
        executorService.schedule(this::parseRound, Integer.parseInt(splitedWave[i]), TimeUnit.MILLISECONDS);
    }

    private void addToList(String[] splitedWave) {
        switch (Integer.parseInt(splitedWave[0])) {
            case 1 -> listToAddTo.add(VirusFactory.createVirusOne());     // Måste ha blivit kallat 1 gång utan executorservice, annars slutar den gå eftersom den inte kan skapa en ny textur
            case 2 -> listToAddTo.add(VirusFactory.createVirusTwo());
            case 3 -> listToAddTo.add(VirusFactory.createVirusThree());
            case 4 -> listToAddTo.add(VirusFactory.createVirusFour());
            case 5 -> listToAddTo.add(VirusFactory.createVirusFive());
            default -> waveIndex = currentRound.length;                 // Maybe exception instead
        }
    }


}
