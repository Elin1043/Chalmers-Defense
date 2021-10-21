package com.mygdx.chalmersdefense.model.path;


/**
 * @author Jenny Carlsson
 *
 * Factory for Paths
 */

public abstract class PathFactory {

    private static IPath activePath = null; // Pointer to the path object
    /**
     * Create a classic path
     * @return the classic path
     */


    public static IPath createClassicPath() {
        return activePath = new ClassicPath();
    }


    public static IPath getActivePath() {
        return activePath;
    }
}
