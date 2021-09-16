package com.mygdx.chalmersdefense.Model;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Joel Båtsman Hilmersson
 * A class that controlls the spawning of viruses for the different rounds
 */
public class SpawnViruses {

    private final String[][] spawnInfo = {{"1|100", "1|80", "1|60", "1|40", "1|40", "1|60", "1|80", "1|100"}};
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final List<Virus> listToAddTo;

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
            //isSpawning = true;
            parseRound();
        }

    }

    private void parseRound(){
        System.out.println("Tjenare");
        String[] splitedWave = currentRound[waveIndex].split("[|]");

        System.out.println(Arrays.toString(splitedWave));
        System.out.println(Integer.parseInt( splitedWave[0]));
        //switch (Integer.parseInt( splitedWave[0])) {
          //  case 1 -> {
            //    listToAddTo.add(VirusFactory.createVirusOne());
              //  System.out.println("HÄR");
            //}
        //}
        System.out.println("Är den här 1 eller 2 gånger");
        listToAddTo.add(VirusFactory.createVirusOne());
        System.out.println("Är den här 1 eller 2 gånger");
        executorService.schedule(this::parseRound, 5000, TimeUnit.MILLISECONDS);
        System.out.println("DÅ");
    }
}
