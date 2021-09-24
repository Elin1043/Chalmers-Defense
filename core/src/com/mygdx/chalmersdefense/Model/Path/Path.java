package com.mygdx.chalmersdefense.Model.Path;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.chalmersdefense.Model.CustomExceptions.NoFurtherWaypointException;
import com.mygdx.chalmersdefense.Utilities.PositionVector;

import java.util.ArrayList;

public abstract class Path {

    private final int pathWidth;

    protected final ArrayList<PositionVector> pathWaypoints = new ArrayList<>();
    private final ArrayList<Rectangle> collisionRectangles = new ArrayList<>();

    protected Path(int pathWidth) { this.pathWidth = pathWidth; }


    protected abstract void setPathWaypoints();

    public PositionVector getWaypoint(int index) { return pathWaypoints.get(index); }

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

                    rectangle.set(posX - pathWidth/2F , posY - pathWidth/2F, pathWidth, distY + pathWidth);
                }
                else{
                    rectangle.set(posX - pathWidth/2F , posY -distY - pathWidth/2F, pathWidth, distY + pathWidth);
                }
            }
            else {
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
