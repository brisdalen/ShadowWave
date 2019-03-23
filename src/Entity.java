
import java.awt.geom.Point2D;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjornar.risdalen
 */
public abstract class Entity {
    private float x;
    private float y;
    private float radius;
    private double maxHealth;
    private double health;
    
    public Entity(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.maxHealth = 1000;
        this.health = maxHealth;
        this.damage(500);
    }
    /**
     * Heals the entity for the
     * @param amount The amount of healing to be done
     */
    public void heal(double amount) {
        // Check if the amount will not put the entity to full HP
        if(amount > 0 && amount < (maxHealth - health)) {
            health += amount;
        }
        // If the amount would put the entity to full HP and beyond,
        // then set it to the maxHealth
        else if(amount > 0) {
            health = maxHealth;
        }
        else {
            System.out.println("No healing was done");
        }
    }
    /**
     * Just the same as heal, but with extra print statements
     * @param amount The amount of healing to be done
     */
    public void debugHeal(double amount) {
        if(amount > 0 && amount < (maxHealth - health)) {
            health += amount;
            System.out.println("HP is now " + health);
        } 
        else if(amount > 0) {
            health = maxHealth;
            System.out.println("HP is now " + health);
        }
        else {
            System.out.println("No healing was done");
        }
    }
    
    public void damage(double damage) {
        // TODO: add control-code
        health -= damage;
    }
    
    public Point2D.Float getPoint() {
        return new Point2D.Float(x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }
    
    public double getHealth() {
        return health;
    }
}
