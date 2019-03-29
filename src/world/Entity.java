package world;


import java.awt.geom.Point2D;
import javafx.scene.shape.Circle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjornar.risdalen
 */
public abstract class Entity extends Circle {
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

    public Entity(float x, float y, float radius, double maxHealth) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
    }
    
    public void update() {
        updatePosition();
    }
    
    private void updatePosition() {
        setTranslateX(x);
        setTranslateY(y);
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
    
    public void roamX(float distance) {
        float startX = getX();
        // 1: Left, 0: Right
        int direction = 0;
        if(direction == 0) {
            if(getX() >= startX + distance) {
                direction = 1;
                moveLeft();
            } else {
                moveRight();
            }
        }
        if(direction == 1) {
            if(getX() <= startX - distance) {
                direction = 0;
                moveRight();
            } else {
                moveLeft();
            }
        }
    }
    
    public void moveRight() {
        this.x += 1;
    }
    
    public void moveLeft() {
        this.x -= 1;
    }
    
    public void moveRight(float amount) {
        this.x += amount;
    }
    
    public void moveLeft(float amount) {
        this.x -= amount;
    }

    public float getY() {
        return y;
    }

    public float getRadi() {
        return radius;
    }
    
    public double getHealth() {
        return health;
    }
}
