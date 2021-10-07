package com.mygdx.chalmersdefense.model.path;


/**
 * @author Jenny Carlsson
 *
 * Factory for Paths
 */

public abstract class PathFactory {

    /**
     * Create a classic path
     * @return the classic path
     */
    public static Path createClassicPath() {
        return new ClassicPath();
    }


}
