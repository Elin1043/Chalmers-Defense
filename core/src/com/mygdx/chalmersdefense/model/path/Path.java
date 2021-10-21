package com.mygdx.chalmersdefense.model.path;

import com.mygdx.chalmersdefense.utilities.PathRectangle;
import com.mygdx.chalmersdefense.utilities.PositionVector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Persson
 * @author Jenny Carlsson
 *
 * Class representing a Path
 * <p>
 * 2021-09-24 Modified by Elin Forsberg and Joel Båtsman Hilmersson: Elin created createMapCollision and Joel implemented it here. <br>
 * 2021-10-03 Modified by Joel Båtsman Hilmersson: Switched to use pathRectangle instead of normal Java rectangle. <br>
 * 2021-10-21 Modified by Elin Forsberg: Implemented IPath interface.
 */


abstract class Path implements IPath {

    private final int pathWidth;

    final List<PositionVector> pathWaypoints = new ArrayList<>();
    private final List<PathRectangle> collisionRectangles = new ArrayList<>();

    Path(int pathWidth) {
        this.pathWidth = pathWidth;
    }

    /**
     * Creates path waypoints
     */
    abstract void setPathWaypoints();


    @Override
    public PositionVector getWaypoint(int index) {
        return pathWaypoints.get(index);
    }

    /**
     * Method for creating rectangles on path later used for collision
     */
    void createMapCollision() {
        for (int i = 0; i < pathWaypoints.size() - 1; i++) {
            float posX = getWaypoint(i).getX();
            float posY = getWaypoint(i).getY();
            float nextX = getWaypoint(i + 1).getX();
            float nextY = getWaypoint(i + 1).getY();

            if (posX == nextX) {
                verticalPathHandler(posX, posY, nextY);
            } else {
                horizontalPathHandler(posX, posY, nextX);
            }
        }
    }

    private void verticalPathHandler(float posX, float posY, float nextY) {
        float distY = Math.abs((nextY - posY));     // The path length in y direction

        if (posY < nextY) {  // The coordinates need to be calculated differently if the path goes up or down
            collisionRectangles.add(createUpRectangle(posX, posY, distY));
        } else {
            collisionRectangles.add(createDownRectangle(posX, posY, distY));
        }
    }

    private void horizontalPathHandler(float posX, float posY, float nextX) {
        float distX = Math.abs((nextX - posX));     // The path length in x direction

        if (posX < nextX) {  // The coordinates need to be calculated differently if the path goes right or left
            collisionRectangles.add(createRightRectangle(posX, posY, distX));
        } else {
            collisionRectangles.add(createLeftRectangle(posX, posY, distX));
        }
    }


    @Override
    public List<PathRectangle> getCollisionRectangles() {
        return collisionRectangles;
    }


    // Create path rectangles differently based on direction
    private PathRectangle createUpRectangle(float posX, float posY, float distY) {
        return new PathRectangle(posX - (pathWidth / 2F), posY - (pathWidth / 2F), pathWidth, distY + pathWidth);
    }

    private PathRectangle createDownRectangle(float posX, float posY, float distY) {
        return new PathRectangle(posX - (pathWidth / 2F), posY - distY - (pathWidth / 2F), pathWidth, distY + pathWidth);
    }

    private PathRectangle createRightRectangle(float posX, float posY, float distX) {
        return new PathRectangle(posX - (pathWidth / 2F), posY - (pathWidth / 2F), distX, pathWidth);
    }

    private PathRectangle createLeftRectangle(float posX, float posY, float distX) {
        return new PathRectangle(posX - distX - (pathWidth / 2F), posY - (pathWidth / 2F), distX, pathWidth);
    }
}
