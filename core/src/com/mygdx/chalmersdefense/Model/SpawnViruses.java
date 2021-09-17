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

    private final String[][] spawnInfo = {{"1|3000", "2|500", "3|500", "4|1000", "5|1000", "5|500", "1|500", "2|1000"}};
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private List<Virus> listToAddTo;
    private boolean isSpawning = false;

    String[] currentRound;
    private int waveIndex = 0;

    public SpawnViruses(List<Virus> listToAddTo) {
        this.listToAddTo = listToAddTo;
    }

    public void spawnRound(int round) {
        if (!isSpawning) {
            System.out.println("HEJ");
            currentRound = spawnInfo[round - 1];
            waveIndex = 0;
            isSpawning = true;
            parseRound();
        }

    }

    private void parseRound(){

        if (waveIndex < currentRound.length) {

            String[] splitedWave = currentRound[waveIndex].split("[|]");


            switch (Integer.parseInt(splitedWave[0])) {
                case 1 -> listToAddTo.add(VirusFactory.createVirusOne());     // Måste ha blivit kallat 1 gång utan executorservice, annars slutar den gå eftersom den inte kan skapa en ny textur
                case 2 -> listToAddTo.add(VirusFactory.createVirusTwo());
                case 3 -> listToAddTo.add(VirusFactory.createVirusThree());
                case 4 -> listToAddTo.add(VirusFactory.createVirusFour());
                case 5 -> listToAddTo.add(VirusFactory.createVirusFive());
            }

            waveIndex++;
            executorService.schedule(this::parseRound, Integer.parseInt(splitedWave[1]), TimeUnit.MILLISECONDS);

        } else {
            waveIndex = 0;
            isSpawning = false;
        }
    }


}
