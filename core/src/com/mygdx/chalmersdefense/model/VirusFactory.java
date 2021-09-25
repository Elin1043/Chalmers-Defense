package com.mygdx.chalmersdefense.model;


import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.path.Path;


/**
 * @author Joel Båtsman Hilmersson
 * A factory class for creating different viruses
 */
public abstract class VirusFactory {

    private static final Path path = new ClassicPath(); // Pointer to the path object

    /**
     * Creates a Virus with one health
     * @return The new Virus object
     */
    static public synchronized Virus createVirusOne(){
        return new Virus(1, path);
    }

    /**
     * Creates a Virus with two health
     * @return The new Virus object
     */
    static public synchronized Virus createVirusTwo(){
        return new Virus(2, path);
    }

    /**
     * Creates a Virus with three health
     * @return The new Virus object
     */
    static public synchronized Virus createVirusThree(){
        return new Virus(3, path);
    }

    /**
     * Creates a Virus with four health
     * @return The new Virus object
     */
    static public synchronized Virus createVirusFour(){
        return new Virus(4, path);
    }

    /**
     * Creates a Virus with five health
     * @return The new Virus object
     */
    static public synchronized Virus createVirusFive(){
        return new Virus(5, path);
    }
}
