package com.mygdx.chalmersdefense.Model.TargetMode;

import com.mygdx.chalmersdefense.Model.Virus;

import java.util.ArrayList;
import java.util.List;

public class First extends TargetMode{
    @Override
    public Virus getTarget(List<Virus> viruses, float x, float y, double range) {
            if (viruses.size() > 0){ //can only get a target if there are enemies
                List<Virus> inRange = new ArrayList<Virus>();
                for(Virus c: viruses){
                    if(isWithinRange(c,x,y,range)){
                        inRange.add(c);
                    }
                }
                if(inRange.size() <= 0){
                    return null;
                }
                Virus target = inRange.get(0); //start with a Virus so a comparison can be made
                double distanceToWaypoint = 999999999; //the first Virus does not have to be checked, more efficient to hard-code in dummy data
                int waypointNumberOfFirstVirus = 0;

                //cycle through all Virus, get what waypoints they're on, remember the one with the highest waypoints & shortest distance to waypoints
                for (Virus Virus:inRange){
                    if ((Virus.getDistanceToNextPoint() < distanceToWaypoint && Virus.getWayPointIndex() == waypointNumberOfFirstVirus)|| Virus.getWayPointIndex() >= waypointNumberOfFirstVirus){
                        target = Virus;
                        distanceToWaypoint = Virus.getDistanceToNextPoint();
                        waypointNumberOfFirstVirus = Virus.getWayPointIndex();
                    }


                }
                return target;
            }
            return null;
        }
}
