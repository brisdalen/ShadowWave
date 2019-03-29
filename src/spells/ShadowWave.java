package spells;

import world.Entity;
import world.Creep;
import world.World;
import generalutil.LogicUtil;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * ShadowWave is an attempt to recreate the behaviour and effect of spell "Shadow 
 * Wave" that the hero Dazzle possess in the game Dota 2. 
 * @version 2019-03-22
 * @author bjornar.risdalen
 */
public class ShadowWave extends Heal {
    
    private int bounceDistance = 475;
    private int[] numberOfBounces = {3,4,5,6};
    float playerRadius;

    public ShadowWave() {
        
        super(4);
        this.setCastTime(30);
        this.setCastRange(800);
        this.setHealAmounts(new double[]{80,100,120,140});
        
        playerRadius = World.getPlayerRadius();
        
        super.setLevel(1);
        // Allies og andre enheter hører hjemme i en egen klasse
        /*
        allies = new ArrayList<>();
        allies.add(new Creep(13.5f*SCALE, 13.5f*SCALE, 1*SCALE)); // 0, innafor
        allies.add(new Creep(15.5f*SCALE, 16.5f*SCALE, 1*SCALE)); // 1, innafor
        allies.add(new Creep(24.5f*SCALE, 11.5f*SCALE, 1*SCALE)); // 2, utenfor
        */
        
        shadowWave();
        printCreepHealth();
        //healAll(calculatePath(allies.get(0)), healAmounts[currentLevel]);
        //healAll(allies, healAmounts[currentLevel]);
    }
    /**
     * shadowWave calculates a path from successive points closest to a point,
     * and heals all creeps by a given amount, depending on the currentLevel.
     * Currently, it has no max amount of bounces, nor does it prioritize heros
     * like it would in Dota. 
     */
    public void shadowWave() {
        if(isCollidingOrigin()) {
            Entity nearestEntity = nearestEntity(World.getPlayerPoint(), World.getAllies());
            // Method healAll is inherited from Heal.java
            healAll(calculatePath(nearestEntity), this.healAmount[getLevel()]);
        } else {
            System.out.println("No creeps within cast range");
        }
    }
    // Calculate and return the path of the Shadow Wave as an ArrayList, starting from the target
    private ArrayList<Entity> calculatePath(Entity target) {
        ArrayList<Entity> allies = World.getAllies();
        // Legge ett og ett punkt til en liste, utifra at de er nærme hverandre
        ArrayList<Entity> eListCopy = new ArrayList<>();
        for(Entity e : allies) {
            eListCopy.add(e);
        }
        ArrayList<Entity> temp = new ArrayList<>();
        // Logikken her
        temp.add(target);
        
        for(int i = 0; i < allies.size(); i++) {
            temp.add(nearestEntity(temp.get(i), eListCopy));
            eListCopy.remove(temp.get(i));
        }
        // TODO: Er dette beste løsningen kanskje?
        temp.remove(temp.get(0));
        
        return temp;
    }
    
    private ArrayList<Entity> debugCalculatePath(Entity target) {
        System.out.println("Target creep: " + target.getPoint());
        for(Entity e : calculatePath(target)) {
            System.out.println("C in calcPath: " + e.getPoint());
        }
        
        ArrayList<Entity> temp = calculatePath(target);
        System.out.println("Temp size: " + temp.size());
        
        return temp;
    }
    
    // Returns the creep closest to the given creep, out of a list of Creeps
    private Entity nearestEntity(Entity origin, ArrayList<Entity> eList) {
        // Clone the given list of creeps
        ArrayList<Entity> temp = (ArrayList<Entity>) eList.clone();
        // Remove the given creep to avoid duplication
        temp.remove(origin);
        // Set the closest distance to the first element, to avoid null
        float closestPoint = distance(origin.getPoint(), temp.get(0).getPoint());
        Entity closestEntity = temp.get(0);
        // Loop over all elements in the given list
        for(int i = 0; i < temp.size(); i++) {
            Entity currentEntity = temp.get(i);
            // Set the newDist to the distance between the given creep and the current creep
            float newDist = distance(origin.getPoint(), currentEntity.getPoint());
            // Set new distance to be the closest if it is closer
            if(newDist < closestPoint) {
                closestPoint = newDist;
                closestEntity = temp.get(i);
            }
        }
        eList.remove(closestEntity);
        return closestEntity;
    }
    
    // Returns the creep closest to the given point, out of a list of Creeps
    private Entity nearestEntity(Point2D.Float originPoint, ArrayList<Entity> cList) {
        // Clone the given list of creeps
        ArrayList<Creep> temp = new ArrayList<>();
        for(Entity e : cList) {
            temp.add(new Creep(e.getX(), e.getY(), e.getRadi()));
        }
        // Set the closest distance to the first element, to avoid null
        float closestPoint = distance(originPoint, temp.get(0).getPoint());
        Creep closestCreep = temp.get(0);
        // Loop over all elements in the given list
        for(int i = 0; i < temp.size(); i++) {
            // Set the newDist to the distance between the given creep and the current creep
            float newDist = distance(originPoint, temp.get(i).getPoint());
            // Set new distance to be the closest if it is closer
            if(newDist < closestPoint) {
                closestPoint = newDist;
                closestCreep = temp.get(i);
            }
        }
        cList.remove(closestCreep);
        return closestCreep;
    }
    
    private void debugNearestEntity(Entity origin, ArrayList<Entity> eList) {
        System.out.print(origin.getPoint());
        System.out.println(" Closest :" + nearestEntity(origin, World.getAllies()).getPoint());
    }
    
    // Check if there's anything colliding with the origin, within the castRange
    private boolean isCollidingOrigin() {
        ArrayList<Entity> allies = World.getAllies();
        boolean colliding = false;
        int count = 0;
        for(Entity e : allies) {
            //System.out.print("C: " + count);
            //System.out.println(" Creep: " + c.getPoint() + " r: " + c.getRadius());
            count++;
            colliding = LogicUtil.isColliding(World.getPlayerPoint(), getCastRange(), 
                                e.getPoint(), e.getRadi());
            System.out.println(colliding);
            if(colliding) {
                return colliding;
            }
        }
        return colliding;
    }
    // Check if an ArrayList of creeps collide with the origin-point
    // Kanskje også flytte til en custom utility class
    private boolean[] isCollidingMultipleOrigin(ArrayList<Entity> entities) {
        
        boolean[] temp = new boolean[entities.size()];
        
        for(int i = 0; i < temp.length; i++) {
            Entity currentEntity = entities.get(i);
            temp[i] = LogicUtil.isColliding(World.getPlayerPoint(), playerRadius, 
                                   currentEntity.getPoint(), currentEntity.getRadi());
        }
        
        return temp;
    }
    // Returns the distance between two points as a float
    private float distance(Point2D.Float p1, Point2D.Float p2) {
        //return (float) Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
        return (float) Point2D.distance(p1.x, p1.y, p2.x, p2.y);
    }

    // Prints out all the health-values of all creeps
    private void printCreepHealth() {
        ArrayList<Entity> allies = World.getAllies();
        for(Entity e : allies) {
            System.out.println(e.getHealth());
        }
    }
}
