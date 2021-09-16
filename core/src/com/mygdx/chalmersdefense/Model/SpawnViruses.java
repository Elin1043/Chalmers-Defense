package com.mygdx.chalmersdefense.Model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
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

    private final String[][] spawnInfo = {{"1|500", "1|80", "1|60", "1|40", "1|40", "1|60", "1|80", "1|100"}};
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

        String[] splitedWave = currentRound[waveIndex].split("[|]");


        switch (Integer.parseInt( splitedWave[0])) {
            case 1 -> listToAddTo.add(VirusFactory.createVirusOne());     // Måste ha blivit kallat 1 gång utan executorservice, annars slutar den gå eftersom den inte kan skapa en ny textur

        }

        executorService.schedule(this::parseRound, Integer.parseInt(splitedWave[1]), TimeUnit.MILLISECONDS);

    }


}
