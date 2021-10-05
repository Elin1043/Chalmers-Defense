package com.mygdx.chalmersdefense.model.path.gamePaths;


import com.mygdx.chalmersdefense.model.path.Path;


/**
 * @author Jenny Carlsson
 */

public abstract class PathFactory {

    public static Path createClassicPath() {
        return new ClassicPath();
    }


}
