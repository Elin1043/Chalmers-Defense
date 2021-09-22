package com.mygdx.chalmersdefense.Model.Path;

import com.mygdx.chalmersdefense.Model.CustomExceptions.NoFurtherWaypointException;
import com.mygdx.chalmersdefense.Utilities.PositionVector;

import java.util.ArrayList;

public abstract class Path {

    protected ArrayList<PositionVector> pathWaypoints;
    protected PositionVector startingPoint;

    protected Path() {
        pathWaypoints = new ArrayList<>();

    }

    protected abstract void setPathWaypoints();

    public PositionVector getWaypoint(int index)  {
        PositionVector waypoint;
        try {
            waypoint = pathWaypoints.get(index);
        } catch (IndexOutOfBoundsException e) {
            //throw new NoFurtherWaypointException(e.getMessage());
            waypoint = pathWaypoints.get(index - 1);
        }

        return waypoint;
    }

    public boolean isAtWaypoint(float x, float y, PositionVector virusVector) {
        if (Math.abs(x - virusVector.getX()) <= 1) {
            return (Math.abs(y - virusVector.getY()) <= 1);
        }
        return false;
    }

    public PositionVector getFirstWaypoint() {
        return new PositionVector(startingPoint);
    }


}
