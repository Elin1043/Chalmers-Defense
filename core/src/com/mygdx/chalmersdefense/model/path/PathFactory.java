package com.mygdx.chalmersdefense.model.path;


/**
 * @author Jenny Carlsson
 */

public abstract class PathFactory {

    public static Path createClassicPath() {
        return new ClassicPath();
    }


}
