package com.mygdx.chalmersdefense.model.targetMode;

import com.mygdx.chalmersdefense.model.Virus;

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

                for (Virus Virus:inRange){
                    if ((Virus.getTotalDistanceTrawled() > target.getTotalDistanceTrawled())){
                        target = Virus;
                    }


                }
                return target;
            }
            return null;
        }
}
