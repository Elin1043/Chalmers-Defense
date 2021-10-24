package com.mygdx.chalmersdefense.model.path;

import com.mygdx.chalmersdefense.model.modelUtilities.PositionVector;

/**
 * @author Daniel Persson
 * @author Jenny Carlsson
 * Class representing a ClassicPath
 *
 * <p>
 * 2021-10-05 Modified by Elin Forsberg: Made ClassicPath package-private and final <br>
 */


final class ClassicPath extends Path {

    /**
     * Creates an object of ClassicPath
     */
     ClassicPath() {
        super(80);
        setPathWaypoints();
        super.createMapCollision();
    }


    @Override
     void setPathWaypoints() {
        if (pathWaypoints.isEmpty()) {
            pathWaypoints.add(new PositionVector(-50, 456));
            pathWaypoints.add(new PositionVector(483, 456));
            pathWaypoints.add(new PositionVector(483, 756));
            pathWaypoints.add(new PositionVector(270, 756));
            pathWaypoints.add(new PositionVector(270, 973));
            pathWaypoints.add(new PositionVector(633, 973));
            pathWaypoints.add(new PositionVector(633, 323));
            pathWaypoints.add(new PositionVector(1379, 323));
            pathWaypoints.add(new PositionVector(1379, 901));
            pathWaypoints.add(new PositionVector(1081, 901));
            pathWaypoints.add(new PositionVector(1081, 684));
            pathWaypoints.add(new PositionVector(861, 684));
            pathWaypoints.add(new PositionVector(861, 1300));

        }
    }

    @Override
    public String getImagePath() {
        return "ClassicMap.png";
    }


}
