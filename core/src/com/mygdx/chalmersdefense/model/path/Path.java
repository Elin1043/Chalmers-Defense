package com.mygdx.chalmersdefense.model.path;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.chalmersdefense.utilities.PositionVector;

import java.util.ArrayList;

public abstract class Path {

    protected final ArrayList<PositionVector> pathWaypoints;
    private final ArrayList<Rectangle> collisionRectangles = new ArrayList<>();

    protected PositionVector startingPoint;

    protected Path() { pathWaypoints = new ArrayList<>(); }


    protected abstract void setPathWaypoints();

    public PositionVector getWaypoint(int index) {
        return pathWaypoints.get(index);
    }

    public PositionVector getFirstWaypoint() {
        return new PositionVector(startingPoint);
    }


    protected void createMapCollision(){
        for (int i = 0; i < pathWaypoints.size() -1; i++) {
            Rectangle rectangle = new Rectangle();
            float posX = getWaypoint(i).getX();
            float posY = getWaypoint(i).getY();
            float nextX = getWaypoint(i+1).getX();
            float nextY = getWaypoint(i+1).getY();
            if(posX == nextX){
                float distY = Math.abs((nextY - posY));
                if(posY < nextY){

                    rectangle.set(posX-40 , posY -40,80, distY + 80);
                }
                else{
                    rectangle.set(posX-40 , posY -distY -40,80, distY + 80);

                }
            }
            else{
                float distX = Math.abs((nextX - posX));
                if(posX < nextX){

                    rectangle.set(posX-40 , posY-40, distX, 80);
                }
                else{
                    rectangle.set(posX-40 - distX  , posY-40, distX, 80);
                }


            }
            collisionRectangles.add(rectangle);
        }
    }

    public ArrayList<Rectangle> getCollisionRectangles() { return collisionRectangles; }

}
