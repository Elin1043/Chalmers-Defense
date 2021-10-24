package com.mygdx.chalmersdefense.model.targetMode;

import java.util.List;

/**
 * @author Elin Forsberg
 * A factory for the different TargetModes
 * <p>
 * 2021-09-24 Modified by Joel Båtsman Hilmersson: The factory now has a list of ITagetMode <br>
 */
public abstract class TargetModeFactory {

    private final static List<ITargetMode> modeList = List.of(new First(), new Last(), new Closest(), new Strongest());  // list of the different targetmodes

    /**
     * Gets the list of targetmodes
     *
     * @return list of targetmodes
     */
    public static List<ITargetMode> getTargetModes() {
        return modeList;
    }
}
