package com.mygdx.chalmersdefense.model.path;


import com.mygdx.chalmersdefense.model.path.gamePaths.ClassicPath;
import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.model.viruses.Virus;
import com.mygdx.chalmersdefense.utilities.PositionVector;

/**
 * @author Jenny Carlsson
 */

public class PathFactory {

    private PathFactory () {}

    protected static Path createClassicPath() {
        return new ClassicPath();
    }


}
