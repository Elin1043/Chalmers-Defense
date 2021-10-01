package com.mygdx.chalmersdefense.model.projectiles;

import com.mygdx.chalmersdefense.model.viruses.IVirus;
import com.mygdx.chalmersdefense.utilities.Calculate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing a lightning projectile
 */
public class LightningProjectile extends Projectile{
    private int range = 150;

    private int hitCountsLeft = 4;

    public LightningProjectile(float x, float y, float angle, int upgradeLevel) {
        super(5 , "electroProjectile" + upgradeLevel, x, y, angle);
    }

//    @Override
//    public IProjectile createProjectile(int speed, float x, float y, float angle) {
//        return new LightningProjectile(speed, x, y, angle);
//    }

    private void countVirusHit(){
        if (hitCountsLeft > 0){
            hitCountsLeft--;
        } else {
            this.setDealtDamage(true);
        }
    }

    @Override
    public boolean remove(){
        return false;
    }

    @Override
    public void virusIsHit(IVirus virus, List<IProjectile> list, List<IVirus> viruses){
        List<IVirus> virusToRemove = new ArrayList<>();
        if(!virus.getIfGotHit()){
            virus.decreaseHealth();
            this.countVirusHit();
            virus.setGotHit(true);

            List<IVirus> virusInRange = Calculate.getVirusesInRange(virus.getX() + virus.getWidth()/2F, virus.getY() + virus.getHeight()/2F, this.getRange(), viruses);

            for (IVirus virusInList: virusInRange) {
                if(virusInList.getIfGotHit()){
                    virusToRemove.add(virusInList);
                }
            }
            virusInRange.removeAll(virusToRemove);


            if(!virusInRange.isEmpty()){
                IVirus tempVirus = virusInRange.get(0);
                this.setAngle(Calculate.angleDeg(tempVirus.getX() + tempVirus.getWidth()/2F, tempVirus.getY() + tempVirus.getHeight()/2F,this.getX() + this.getWidth()/2F, this.getY() + this.getHeight()/2F));

            }
            else{
                list.add(this);
            }
        }
    }

    public int getRange() {
        return range;
    }
}
