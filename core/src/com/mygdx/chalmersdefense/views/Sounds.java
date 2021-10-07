package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Sounds {

    public Sounds(){
        // Setup of music
        Music music = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.wav"));
        music.setLooping(true);
        music.setVolume(0.2F);
        music.play();
    }
}
