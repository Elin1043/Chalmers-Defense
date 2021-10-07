package com.mygdx.chalmersdefense.model.path;

import com.mygdx.chalmersdefense.utilities.PathRectangle;
import com.mygdx.chalmersdefense.utilities.PositionVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Persson
 * @author Jenny Carlsson
 *
 * 2021-09-24 Modified by Elin Forsberg and Joel Båtsman Hilmersson: Elin created createMapCollision and Joel implemented it here.
 * 2021-10-03 Modified by Joel Båtsman Hilmersson: Switched to use pathRectangle instead of normal Java rectangle.
 */


public abstract class Path {

    private final int pathWidth;

    protected final List<PositionVector> pathWaypoints = new ArrayList<>();
    private final List<PathRectangle> collisionRectangles = new ArrayList<>();

    protected Path(int pathWidth) { this.pathWidth = pathWidth; }


    protected abstract void setPathWaypoints();


    /**
     * Returns waypoint of given index
     * @param index to get waypoint of
     * @return waypoint
     */
    public PositionVector getWaypoint(int index) { return pathWaypoints.get(index); }

    /**
     * Method for creating rectangles on path later used for collision
     */
    protected void createMapCollision(){
        for (int i = 0; i < pathWaypoints.size() -1; i++) {
            float posX = getWaypoint(i).getX();
            float posY = getWaypoint(i).getY();
            float nextX = getWaypoint(i+1).getX();
            float nextY = getWaypoint(i+1).getY();

            if(posX == nextX){
                float distY = Math.abs((nextY - posY));

                if(posY < nextY){
                    collisionRectangles.add(new PathRectangle(posX - pathWidth/2F , posY - pathWidth/2F, pathWidth, distY + pathWidth));
                }
                else{
                    collisionRectangles.add(new PathRectangle(posX - pathWidth/2F , posY -distY - pathWidth/2F, pathWidth, distY + pathWidth));
                }
            }
            else {
                float distX = Math.abs((nextX - posX));

                if(posX < nextX){
                    collisionRectangles.add(new PathRectangle(posX-pathWidth/2F , posY-pathWidth/2F, distX, pathWidth));
                }
                else{
                    collisionRectangles.add(new PathRectangle(posX-pathWidth/2F - distX  , posY-pathWidth/2F, distX, pathWidth));
                }

            }
        }
    }

    /**
     * Gets the list of rectangles used for collision
     * @return list of rectangles
     */
    public List<PathRectangle> getCollisionRectangles() { return collisionRectangles; }

}
