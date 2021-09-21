package com.mygdx.chalmersdefense.Model.Path.GamePaths;

import com.mygdx.chalmersdefense.Model.Path.Path;
import com.mygdx.chalmersdefense.Utilities.PositionVector;

import java.awt.*;
import java.util.ArrayList;

public class ClassicPath extends Path {
    Rectangle rec;




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

    private void setRectangles(){
        for (int i = 0; i < pathWaypoints.size(); i++){
            rec = new Rectangle();
            rec.setRect(pathWaypoints.get(i).getX(), pathWaypoints.get(i).getY(), pathWaypoints.get(i+1).getX(), pathWaypoints.get(i+1).getY());
            //rectangles.add(rec);

        }

    }



}
