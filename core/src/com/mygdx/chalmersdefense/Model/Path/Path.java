package com.mygdx.chalmersdefense.Model.Path;

import com.mygdx.chalmersdefense.Model.CustomExceptions.NoFurtherWaypointException;
import com.mygdx.chalmersdefense.Utilities.PointWrapper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public abstract class Path {

    protected ArrayList<PointWrapper> pathWaypoints;
    protected PointWrapper startingPoint;

    protected Path() {
        pathWaypoints = new ArrayList<>();

    }

    protected abstract void setPathWaypoints();

    public PointWrapper getWaypoint(int index) throws NoFurtherWaypointException {
        PointWrapper waypoint;
        try {
            waypoint = pathWaypoints.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoFurtherWaypointException(e.getMessage());
        }

        return waypoint;
    }

    public boolean isAtWaypoint(float x, float y, PointWrapper virusVector) {
        if (Math.abs(x - virusVector.getX()) <= 1) {
            return (Math.abs(y - virusVector.getY()) <= 1);
        }
        return false;
    }

    public PointWrapper getFirstWaypoint() {
        return new PointWrapper(startingPoint);
    }


}
