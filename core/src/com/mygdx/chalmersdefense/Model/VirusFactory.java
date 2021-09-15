package com.mygdx.chalmersdefense.Model;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 * @author Joel Båtsman Hilmersson
 * A factory class for creating different viruses
 */
public abstract class VirusFactory {

    static public Virus createVirusOne(){
        return new Virus(1, new Sprite(new Texture("corona_virus_low.png")));
    }
}
