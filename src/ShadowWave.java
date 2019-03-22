
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
    
    // The center of the cast-point-circle
    // in other words, stretches from 2.0f to 3.0f
    private static final int SCALE = 100;
    private float castOriginX = 2.5f;
    // and 4.0f to 5.0f
    private float castOriginY = 4.5f;
    private Point2D.Float originPoint;
    private float originRadius;
    private int bounceDistance = 475;
    // currentLevel should be between 0 and 3
    private int currentLevel;
    private int[] numberOfBounces = {3,4,5,6};
    public ArrayList<Creep> allies;

    public ShadowWave() {
        super();
        this.setCastTime(30);
        this.setCastRange(800);
        this.setHealAmounts(new double[]{80,100,120,140});
        
        currentLevel = 1;
        
        originPoint = new Point2D.Float(castOriginX*SCALE, castOriginY*SCALE);
        originRadius = 1.0f;
        // Allies og andre enheter hører hjemme i en egen klasse
        allies = new ArrayList<>();
        allies.add(new Creep(3.5f*SCALE, 3.5f*SCALE, 1*SCALE)); // 0, innafor
        allies.add(new Creep(5.5f*SCALE, 6.5f*SCALE, 1*SCALE)); // 1, innafor
        allies.add(new Creep(14.5f*SCALE, 1.5f*SCALE, 1*SCALE)); // 2, utenfor
        
        System.out.println(allies.size());
        
        testMethod();
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
            Creep nearestCreep = nearestCreep(originPoint, allies);
            //debugCalculatePath(nearestCreep);
            healAll(calculatePath(nearestCreep), this.healAmount[currentLevel]);
        } else {
            System.out.println("No creeps within cast range");
        }
    }
    // Calculate and return the path of the Shadow Wave as an ArrayList, starting from the target
    private ArrayList<Creep> calculatePath(Creep target) {
        // Legge ett og ett punkt til en liste, utifra at de er nærme hverandre
        ArrayList<Creep> cListCopy = new ArrayList<>();
        for(Creep c : allies) {
            cListCopy.add(new Creep(c.getX(), c.getY(), c.getRadius()));
        }
        ArrayList<Creep> temp = new ArrayList<>();
        // Logikken her
        temp.add(target);
        
        for(int i = 0; i < allies.size(); i++) {
            temp.add(nearestCreep(temp.get(i), cListCopy));
            cListCopy.remove(temp.get(i));
        }
        // TODO: Er dette beste løsningen kanskje?
        temp.remove(temp.get(0));
        System.out.println("Temp size: " + temp.size());
        return temp;
    }
    
    private void debugCalculatePath(Creep target) {
        System.out.println("Target creep: " + target.getPoint());
        for(Creep c : calculatePath(target)) {
            System.out.println("C in calcPath: " + c.getPoint());
        }
    }
    
    // Returns the creep closest to the given creep, out of a list of Creeps
    private Creep nearestCreep(Creep origin, ArrayList<Creep> cList) {
        System.out.println("NearestCreep");
        // Clone the given list of creeps
        ArrayList<Creep> temp = (ArrayList<Creep>) cList.clone();
        // Remove the given creep to avoid duplication
        temp.remove(origin);
        // Set the closest distance to the first element, to avoid null
        float closestPoint = distance(origin.getPoint(), temp.get(0).getPoint());
        Creep closestCreep = temp.get(0);
        // Loop over all elements in the given list
        for(int i = 0; i < temp.size(); i++) {
            Creep currentCreep = temp.get(i);
            // Set the newDist to the distance between the given creep and the current creep
            float newDist = distance(origin.getPoint(), currentCreep.getPoint());
            // Set new distance to be the closest if it is closer
            if(newDist < closestPoint) {
                closestPoint = newDist;
                closestCreep = temp.get(i);
            }
        }
        cList.remove(closestCreep);
        return closestCreep;
    }
    
    // Returns the creep closest to the given point, out of a list of Creeps
    private Creep nearestCreep(Point2D.Float originPoint, ArrayList<Creep> cList) {
        // Clone the given list of creeps
        ArrayList<Creep> temp = new ArrayList<>();
        for(Creep c : cList) {
            temp.add(new Creep(c.getX(), c.getY(), c.getRadius()));
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
    
    private void debugNearestCreep(Creep origin, ArrayList<Creep> cList) {
        System.out.print(origin.getPoint());
        System.out.println(" Closest :" + nearestCreep(origin, allies).getPoint());
    }
    
    // Check if there's anything colliding with the origin, within the castRange
    private boolean isCollidingOrigin() {
        boolean colliding = false;
        int count = 0;
        for(Creep c : allies) {
            //System.out.print("C: " + count);
            //System.out.println(" Creep: " + c.getPoint() + " r: " + c.getRadius());
            count++;
            colliding = LogicUtil.isColliding(originPoint, getCastRange(), 
                                c.getPoint(), c.getRadius());
            System.out.println(colliding);
            if(colliding) {
                return colliding;
            }
        }
        return colliding;
    }
    // Check if an ArrayList of creeps collide with the origin-point
    // Kanskje også flytte til en custom utility class
    private boolean[] isCollidingMultipleOrigin(ArrayList<Creep> creeps) {
        
        boolean[] temp = new boolean[creeps.size()];
        
        for(int i = 0; i < temp.length; i++) {
            Entity currentEntity = creeps.get(i);
            temp[i] = LogicUtil.isColliding(originPoint, originRadius, 
                                   currentEntity.getPoint(), currentEntity.getRadius());
        }
        
        return temp;
    }
    // Returns the distance between two points as a float
    private float distance(Point2D.Float p1, Point2D.Float p2) {
        //return (float) Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
        return (float) Point2D.distance(p1.x, p1.y, p2.x, p2.y);
    }
    
    /*private float distance(Point2D.Float p1, float p1r, Point2D.Float p2, float p2r) {
        if(p1.x < p2.x) {
            if(p1.y < p2.y) {
                return (float) Point2D.distance(p1.x+p1r, p1.y+p1r, p2.x-p2r, p2.y-p2r);
            } else {
                return (float) Point2D.distance(p1.x+p1r, p1.y-p1r, p2.x-p2r, p2.y+p2r);
            }
        } else {
            if(p1.y < p2.y) {
                return (float) Point2D.distance(p1.x-p1r, p1.y+p1r, p2.x+p2r, p2.y-p2r);
            } else {
                return (float) Point2D.distance(p1.x-p1r, p1.y-p1r, p2.x+p2r, p2.y+p2r);
            }
        }
        //return (float) Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));
    }
    * Unfortunatly not working properly
    */
    
    private void testMethod() {
        //System.out.println(isCollidingOrigin());
        //System.out.println(distance(originPoint, allies.get(0).getPoint()));
        //System.out.println(distance(originPoint, allies.get(2).getPoint()));
        //printMultipleBooleans(isCollidingMultipleOrigin(allies));
        //debugNearestCreep(allies.get(2), allies);
        //debugCalculatePath(allies.get(2));
        shadowWave();
        System.out.println("Allies original: " + allies.size());
    }
    // Prints out all booleans in a given array
    private void printMultipleBooleans(boolean[] bList) {
        for(boolean b : bList) {
            System.out.println(b);
        }
    }
    // Prints out all the health-values of all creeps
    private void printCreepHealth() {
        for(Creep c : allies) {
            System.out.println(c.getHealth());
        }
    }
    
    private void healAll(ArrayList<Creep> toHeal, double amount) {
        for(Creep c : toHeal) {
            c.heal(amount);
        }
    }
    
    public static void main(String[] args) {
        ShadowWave sw = new ShadowWave();
    }
    
}
