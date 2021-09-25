package com.mygdx.chalmersdefense.model;


/**
 * @author Elin Forsberg
 * A factory class for creating different towers
 *
 * 2021-09-23 Modified by Joel Båtsman Hilmersson: Deleted imagePath to the towers
 */

public class TowerFactory {


    public Tower CreateSmurf(int startPosX, int startPosY) { return new Tower(startPosX,startPosY, "SmurfTower", 10,10, 100); }

    public Tower CreateChemist(int startPosX, int startPosY) { return new Tower(startPosX,startPosY, "ChemistTower", 30,10, 200); }

    public Tower CreateHacker(int startPosX, int startPosY) { return new Tower(startPosX,startPosY, "HackerTower", 10,10, 300); }

    public Tower CreateElectro(int startPosX, int startPosY) { return new Tower(startPosX,startPosY, "ElectroTower", 20,10, 400); }

    public Tower CreateMeck(int startPosX, int startPosY) { return new Tower(startPosX,startPosY, "MeckTower", 30,10, 500); }

    public Tower CreateEco(int startPosX, int startPosY) { return new Tower(startPosX,startPosY, "EcoTower", 20,10, 600); }

}
