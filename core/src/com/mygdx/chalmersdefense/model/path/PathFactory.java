package com.mygdx.chalmersdefense.model.path;


/**
 * @author Jenny Carlsson
 * <p>
 * Factory for Paths
 * <p>
 * 2021-10-21: Modified by Elin Forsberg: Added a way to get activePath
 */

public abstract class PathFactory {

    private static IPath activePath = new ClassicPath(); // Pointer to the path object (classicPath is standard value)

    /**
     * Create a classic path
     *
     * @return the classic path
     */
    public static IPath createClassicPath() {
        return activePath = new ClassicPath();
    }

    /**
     * Gets the active path
     *
     * @return active path
     */
    public static IPath getActivePath() {
        return activePath;
    }
}
