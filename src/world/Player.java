package world;


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
public class Player {
    
    private float posX = 125.0f;
    // and 4.0f to 5.0f
    private float posY = 145.0f;
    private Point2D.Float playerPoint;
    private float playerRadius;
    
    Hero hero;
    
    public Player(Hero hero) {
        this.hero = hero;
    }
    
    public Point2D.Float getOrigin() {
        return new Point2D.Float(posX, posY);
    }
    
    public float getPlayerRadius() {
        return this.playerRadius;
    }
}
