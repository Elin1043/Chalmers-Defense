package com.mygdx.chalmersdefense.model.towers;

import com.mygdx.chalmersdefense.model.projectiles.IProjectile;
import com.mygdx.chalmersdefense.model.projectiles.ProjectileFactory;
import com.mygdx.chalmersdefense.model.targetMode.ITargetMode;
import com.mygdx.chalmersdefense.utilities.Calculate;
import com.mygdx.chalmersdefense.utilities.PathRectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elin Forsberg
 * Class representing the MechTower
 */
class MechTower extends Tower {

    private final List<ITower> miniTowers = new ArrayList<>();  // List to add minitower to
    private final int reloadSpeed;      // Reload speed
    private final int range;            // Current range of tower, this will be passed to minimechtowers later
    private final List<ITargetMode> targetModes;    // All possible targeted
    List<ITower> towersToAddList;       // The final list to add towers to get them to show up on the map
    private final List<ITower> towersToRemoveList;
    private final List<ITower> allTowers;
    private final List<PathRectangle> pathRectangles;

    private int robotLifeTimer = 2000;    // Lifetime of a robot
    private int robotCooldownTimer = 500;    // Cooldown of a robot

    private int currentReload = 0;  // Current reload


    MechTower(float x, float y, String name, int reloadSpeed, int cost, int range, List<ITargetMode> targetModes, List<ITower> towersToAddList, List<ITower> towersToRemoveList,List<ITower> allTowers,  List<PathRectangle> pathRectangles) {
        super(x, y, name, reloadSpeed, cost, range, targetModes);
        this.reloadSpeed = reloadSpeed;
        this.targetModes = targetModes;
        this.range = range;
        this.towersToAddList = towersToAddList;
        this.towersToRemoveList = towersToRemoveList;
        this.allTowers = allTowers;
        this.pathRectangles = pathRectangles;
    }

    private List<ITower> createMiniTowers() {
        float[] point1 = checkPointCollision();
        float[] point2 = checkPointCollision();
        if(getUpgradeLevel() == 1 || getUpgradeLevel() == 3 ){
            ITower miniTower1 = new MechMiniTower(point1[0], point1[1], reloadSpeed, range, targetModes,this.getCurrentTargetMode(), this.getUpgradeLevel());
            miniTowers.add(miniTower1);
        }
        if(getUpgradeLevel() == 2){
            ITower miniTower1 = new MechMiniTower(point1[0], point1[1], reloadSpeed, range, targetModes,this.getCurrentTargetMode(), this.getUpgradeLevel());
            ITower miniTower2 = new MechMiniTower(point2[0], point2[1], reloadSpeed, range, targetModes,this.getCurrentTargetMode(), this.getUpgradeLevel());
            miniTowers.add(miniTower1);
            miniTowers.add(miniTower2);
        }
        return miniTowers;
    }

    @Override
    void createProjectile(List<IProjectile> projectileList){
        projectileList.add(ProjectileFactory.createRobotProjectile(super.getX(), super.getY(), getAngle(), getUpgradeLevel()));

    }

    @Override
    public void remove(List<ITower> towersList){
        towersList.removeAll(miniTowers);
        towersList.remove(this);
    }

    @Override
    public void update(List<IProjectile> projectilesList, float newAngle, boolean hasTarget) {

        spawnMiniTowers();

        if(this.upgradeLevel == 3){
            setAngle(newAngle);
            if (currentReload < 1 && hasTarget && this.isPlaced()) {
                currentReload = reloadSpeed;
                createProjectile(projectilesList);
            } else {
                currentReload--;
            }
        }
        else{
            this.setAngle(0);
        }

    }

    private void spawnMiniTowers(){
        if (this.isPlaced() && miniTowers.isEmpty() && robotCooldownTimer <= 0) {
            List<ITower> miniTowers = createMiniTowers();
            for (ITower miniTower : miniTowers) {
                miniTower.placeTower();
            }
            robotLifeTimer = 1000;
            towersToAddList.addAll(miniTowers);
        }
        else if(this.isPlaced() && !miniTowers.isEmpty() && robotLifeTimer <= 0){
            towersToRemoveList.addAll(miniTowers);
            miniTowers.clear();
            robotCooldownTimer = 500;
        }

        else if(this.isPlaced() && !miniTowers.isEmpty()){
            robotLifeTimer--;
        }
        else{
            robotCooldownTimer--;
        }
    }

    private float[] checkPointCollision() {
        float[] point = randPoint();
        while(pathCollision(this.getWidth(),this.getHeight(), point[0],point[1]) || towerCollision(this.getWidth(),this.getHeight(), point[0],point[1])){
            point = randPoint();
        }
        return point;

    }

    private boolean pathCollision(double width, double height,double x, double y){
        for (PathRectangle rectangle : pathRectangles) {
            if (Calculate.calculateIntersects(width , height, rectangle.getWidth(), rectangle.getHeight(), x, y, rectangle.getX(),rectangle.getY())) {
                return true;
            }
        }
        return false;
    }

    private boolean towerCollision(double width, double height,double x, double y){
        for (ITower tower : allTowers) {
            if (Calculate.calculateIntersects(width , height, tower.getWidth(), tower.getHeight(), x, y, tower.getX(),tower.getY())) {
                return true;
            }
        }
        return false;
    }

    private float[] randPoint() {
        double len= Math.sqrt(Math.random())*range;
        double deg= Math.random()*2*Math.PI;
        float x = (float) (this.getX()+len*Math.cos(deg));
        float y = (float) (this.getY()+len*Math.sin(deg));

        return new float[]{x,y};
    }

}
