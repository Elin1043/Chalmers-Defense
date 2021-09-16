package com.mygdx.chalmersdefense.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 * @author Joel Båtsman Hilmersson
 * A factory class for creating different viruses
 */
public abstract class VirusFactory {

    private static final Texture redVirus = new Texture("corona_virus_low.png");

    static public synchronized Virus createVirusOne(){
        return new Virus(1, new Sprite(redVirus));
    }
}
