package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.chalmersdefense.utilities.Preferences;

/**
 * @author Elin Forsberg
 * Class representing Sounds
 */
final public class Sounds {

    private final Music music;              // Music object
    private final Preferences preferences;  // Preferences on the game

    /**
     * Creates a object for handling sounds
     * @param preferences for the game
     */
    public Sounds(Preferences preferences){
        this.preferences = preferences;

        // Setup of music
        music = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.wav"));
        music.setLooping(true);
        music.play();
        music.setVolume(0);

        //Update volume if preferences changes.
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateMusicVolume();
            }
        }, 0, 0.01F);
    }

    /**
     * Update volume if preferences changes
     */
    private void updateMusicVolume() {
        if (preferences.getBoolean("muteMusic")){
            music.setVolume(0);
        } else {
            music.setVolume(preferences.getFloat("musicVolume"));
        }
    }
}
