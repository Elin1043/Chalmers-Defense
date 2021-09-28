package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.ChalmersDefense;
import com.mygdx.chalmersdefense.model.customExceptions.PlayerLostAllLifeException;
import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.model.projectiles.Projectile;
import com.mygdx.chalmersdefense.utilities.Calculate;
import com.mygdx.chalmersdefense.model.towers.Tower;
import com.mygdx.chalmersdefense.model.towers.TowerFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Joel Båtsman Hilmmersson
 * @author Elin Forsberg
 * @author Daniel Persson
 * @author Jenny Carlsson
 *
 *
 * 2021-09-20 Modified by Elin Forsberg: Added methods to handle towers + collisions
 * 2021-09-20 Modified by Joel Båtsman Hilmersson: Made updateVirus loop syncronized
 * 2021-09-24 Modified by Elin Forsberg: Added methods to handle projectiles
 */

public class Model {
    private ChalmersDefense game;



    private final Map map = new Map();
    private final List<Tower> towersList = new ArrayList<>();
    private List<Projectile> projectilesList = new ArrayList<>();

    private final Player player = new Player(100, 300); //Change staring capital later. Just used for testing right now

    private final List<Virus> allViruses = Collections.synchronizedList(new ArrayList<>());
    private final Path path;

    private final SpawnViruses virusSpawner = new SpawnViruses(allViruses);



    /**
     * Constructor of the model class
     * @param game current game session
     */
    public Model(ChalmersDefense game) {
        this.game = game;
        path = new ClassicPath();           // Make a path factory instead?
    }

    /**
     * Update all the model components
     */
    public void updateModel() {
        map.updateMap();
    }


    /**
     * Return the current money value
     * @return the money value
     */
    public int getMoney() { return player.getMoney(); }

    /**
     * Returns the lives left of player
     * @return lives left
     */
    public int getLivesLeft() {return player.getLives(); }

    /**
     * Creates a new tower when user starts dragging from a tower button.
     * @param towerName the name of the tower
     * @param x the X-position of the button
     * @param y the Y-position of the button
     */
    public void dragStart(String towerName, int x, int y) {
        map.dragStart(towerName, x, y);
    }

    /**
     * Handles a tower being dragged.
     * Updates the towers position after mouse and check for collision
     * @param buttonWidth The width of the button dragged from
     * @param buttonHeight The height of the button dragged from
     * @param x The X-position of the mouse
     * @param y The Y-position of the mouse
     * @param windowHeight The height of the window
     * @param windowWidth  The width of the window
     */
    public void onDrag(int buttonWidth, int buttonHeight, int x, int y, int windowHeight, int windowWidth) {
        map.onDrag(buttonWidth, buttonHeight, x, y, windowHeight, windowWidth);
    }


    /**
     * Handles when the tower is let go.
     * Checks if tower can be placed on current position.
     * If not: tower is removed
     * if valid: place the tower
     * @param buttonWidth The width of the button dragged from
     * @param buttonHeight The height of the button dragged from
     * @param x The X-position of the mouse
     * @param y The Y-position of the mouse
     * @param windowHeight The height of the window
     */
    public void dragEnd(int buttonWidth, int buttonHeight, int x, int y, int windowHeight) {
        map.dragEnd(buttonWidth, buttonHeight, x, y, windowHeight);
    }

    /**
     * Return the list of towers on map
     * @return The list of towers
     */
    public List<Tower> getTowers() {
        return map.getTowers();
    }

    /**
     * Handles when a placed tower is clicked
     */
    public void towerClicked() {
        map.towerClicked();
    }

    /**
     * Return the list of projectiles
     * @return list of projectiles
     */
    public List<Projectile> getProjectilesList() {
        return map.getProjectilesList();
    }

    /**
     * Return the list of viruses on path
     * @return the list of viruses
     */
    public List<Virus> getViruses() {
        return map.getViruses();
    }

    // TODO This should be gone later!!
    public SpawnViruses getVirusSpawner() {
        return virusSpawner;
    }


}
