package com.mygdx.chalmersdefense.model.path.GamePaths;

import com.mygdx.chalmersdefense.model.path.Path;
import com.mygdx.chalmersdefense.utilities.PositionVector;

public class ClassicPath extends Path {

    public ClassicPath() {
        super();
        startingPoint = new PositionVector(0, 472);
    }

    @Override
    protected void setPathWaypoints() {
        if (pathWaypoints.isEmpty()) {
            pathWaypoints.add(new PositionVector(476, 472));
            pathWaypoints.add(new PositionVector(476, 756));
            pathWaypoints.add(new PositionVector(270, 756));
            pathWaypoints.add(new PositionVector(270, 973));
            pathWaypoints.add(new PositionVector(633, 973));
            pathWaypoints.add(new PositionVector(633, 323));
            pathWaypoints.add(new PositionVector(1379, 323));
            pathWaypoints.add(new PositionVector(1379, 917));
            pathWaypoints.add(new PositionVector(1081, 917));
            pathWaypoints.add(new PositionVector(1081, 684));
            pathWaypoints.add(new PositionVector(861, 684));
            pathWaypoints.add(new PositionVector(861, 1080));

        }
    }

}
