/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalutil;

import java.awt.geom.Point2D;

/**
 *
 * @author bjornar.risdalen
 */
public class LogicUtil {
    
    /**
     * @param p1 The first Point2D.Float
     * @param r1 The first point's radius
     * @param p2 The second Point2D.Float
     * @param r2 The seconds point's radius
     * @return Returns true if 2 Point2D.Floats with a radius overlap
     */
    public static boolean isColliding(Point2D.Float p1, float r1,
                                Point2D.Float p2, float r2) {
        
        final float a = r1 + r2;
        final float dx = p1.x - p2.x;
        final float dy = p1.y - p2.y;
        
        return a * a > (dx * dx + dy * dy);
    }
    /**
     * @param p1 The first Point2D.Float
     * @param p2 The second Point2D.Float
     * @return Returns the distance between 2 points as a float
     */
    public static float distance(Point2D.Float p1, Point2D.Float p2) {
        return (float) Point2D.distance(p1.x, p1.y, p2.x, p2.y);
    }
}
