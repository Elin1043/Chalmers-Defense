package com.mygdx.chalmersdefense.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.chalmersdefense.utilities.Preferences;

/**
 * @author Elin Forsberg
 * Class representing Sounds
 */
public class Sounds {
    private final Timer timer = new Timer();    // The timer object

    private Music music;
    private Preferences preferences;

    public Sounds(Preferences preferences){
        this.preferences = preferences;
        // Setup of music
        music = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.wav"));
        music.setLooping(true);
        music.play();

        // Update volume if preferences changes.
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                updateMusicVolume();
            }
        }, 0, 0.01F);
    }

    private void updateMusicVolume() {
        if (preferences.getBoolean("muteMusic")){
            music.setVolume(0);
        } else {
            music.setVolume(preferences.getFloat("musicVolume"));
        }
    }
}
