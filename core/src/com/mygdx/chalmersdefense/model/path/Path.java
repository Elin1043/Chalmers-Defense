package com.mygdx.chalmersdefense.model.path;

import com.mygdx.chalmersdefense.utilities.PositionVector;

import java.awt.Rectangle;
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

                    rectangle.setRect(posX - pathWidth/2F , posY - pathWidth/2F, pathWidth, distY + pathWidth);
                }
                else{
                    rectangle.setRect(posX - pathWidth/2F , posY -distY - pathWidth/2F, pathWidth, distY + pathWidth);
                }
            }
            else {
                float distX = Math.abs((nextX - posX));

                if(posX < nextX){
                    rectangle.setRect(posX-pathWidth/2F , posY-pathWidth/2F, distX, pathWidth);
                }
                else{
                    rectangle.setRect(posX-pathWidth/2F - distX  , posY-pathWidth/2F, distX, pathWidth);
                }

            }
            collisionRectangles.add(rectangle);
        }
    }

    public ArrayList<Rectangle> getCollisionRectangles() { return collisionRectangles; }

}
