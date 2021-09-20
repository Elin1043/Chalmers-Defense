package com.mygdx.chalmersdefense.Model.Path.GamePaths;

import com.mygdx.chalmersdefense.Model.Path.Path;
import com.mygdx.chalmersdefense.Utilities.PointWrapper;

public class ClassicPath extends Path {

    public ClassicPath() {
        super();
        startingPoint = new PointWrapper(0, 472);
    }

    @Override
    protected void setPathWaypoints() {
        if (pathWaypoints.isEmpty()) {
            pathWaypoints.add(new PointWrapper(476, 472));
            pathWaypoints.add(new PointWrapper(476, 756));
            pathWaypoints.add(new PointWrapper(270, 756));
            pathWaypoints.add(new PointWrapper(270, 973));
            pathWaypoints.add(new PointWrapper(633, 973));
            pathWaypoints.add(new PointWrapper(633, 323));
            pathWaypoints.add(new PointWrapper(1379, 323));
            pathWaypoints.add(new PointWrapper(1379, 917));
            pathWaypoints.add(new PointWrapper(1081, 917));
            pathWaypoints.add(new PointWrapper(1081, 684));
            pathWaypoints.add(new PointWrapper(861, 684));
            pathWaypoints.add(new PointWrapper(861, 1080));

        }
    }

}
