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

    public PositionVector getWaypoint(int index) throws NoFurtherWaypointException {
        PositionVector waypoint;
        try {
            waypoint = pathWaypoints.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoFurtherWaypointException(e.getMessage());
        }

        return waypoint;
    }

    public PositionVector getFirstWaypoint() {
        return new PositionVector(startingPoint);
    }

    public int getListSize(){
        return pathWaypoints.size();
    }


}
