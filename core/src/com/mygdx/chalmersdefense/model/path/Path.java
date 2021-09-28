package com.mygdx.chalmersdefense.model.path;

import com.mygdx.chalmersdefense.utilities.PositionVector;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * @author
 *
 * 2021-09-24 Modified by Elin Forsberg and Joel Båtsman Hilmersson: Elin created createMapCollision and Joel implemented it here.
 */
public abstract class Path {

    private final int pathWidth;

    protected final ArrayList<PositionVector> pathWaypoints = new ArrayList<>();
    private final ArrayList<Rectangle> collisionRectangles = new ArrayList<>();

    protected Path(int pathWidth) { this.pathWidth = pathWidth; }


    protected abstract void setPathWaypoints();

    public PositionVector getWaypoint(int index) { return pathWaypoints.get(index); }

    /**
     * Method for creating rectangles on path later used for collision
     */
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

    /**
     * Gets the list of rectangles used for collision
     * @return list of rectangles
     */
    public ArrayList<Rectangle> getCollisionRectangles() { return collisionRectangles; }

}