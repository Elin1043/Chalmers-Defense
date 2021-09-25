package com.mygdx.chalmersdefense.model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.chalmersdefense.model.path.GamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.path.Path;


/**
 * @author Joel Båtsman Hilmersson
 * A factory class for creating different viruses
 */
public abstract class VirusFactory {

    private static final Texture redVirus = new Texture("redVirus.png");
    private static final Texture blueVirus = new Texture("blueVirus.png");
    private static final Texture greenVirus = new Texture("greenVirus.png");
    private static final Texture yellowVirus = new Texture("yellowVirus.png");
    private static final Texture pinkVirus = new Texture("pinkVirus.png");

    private static final Path path = new ClassicPath();

    static public synchronized Virus createVirusOne(){
        return new Virus(1, new Sprite(redVirus), path);
    }

    static public synchronized Virus createVirusTwo(){
        return new Virus(2, new Sprite(blueVirus), path);
    }

    static public synchronized Virus createVirusThree(){
        return new Virus(3, new Sprite(greenVirus), path);
    }

    static public synchronized Virus createVirusFour(){
        return new Virus(4, new Sprite(yellowVirus), path);
    }

    static public synchronized Virus createVirusFive(){
        return new Virus(5, new Sprite(pinkVirus), path);
    }
}
