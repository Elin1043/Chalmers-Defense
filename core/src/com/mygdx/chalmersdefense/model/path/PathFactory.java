package com.mygdx.chalmersdefense.model.path;


import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;

/**
 * @author Jenny Carlsson
 */

public abstract class PathFactory {

    public static Path createClassicPath() {
        return new ClassicPath();
    }


}
